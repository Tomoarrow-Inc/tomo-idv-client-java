package com.tomoarrow.idv.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class ClientAssertionHelperTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void createClientAssertionSignsEs256Jwt() throws Exception {
        // This test proves that the SDK helper creates the OAuth2 client assertion
        // expected by idv-server: correct JWT claims and a verifiable ES256 signature.
        KeyPair keyPair = generateKeyPair();
        String secretKey = privateKeyToEncodedJwk(keyPair);

        String jwt = ClientAssertionHelper.createClientAssertion(
                new ClientAssertionOptions("client-123", secretKey, "https://api.example.test")
        );

        String[] parts = jwt.split("\\.");
        Map<String, Object> header = decodeJson(parts[0]);
        Map<String, Object> payload = decodeJson(parts[1]);

        assertEquals("ES256", header.get("alg"));
        assertEquals("JWT", header.get("typ"));
        assertEquals("client-123", payload.get("iss"));
        assertEquals("client-123", payload.get("sub"));
        assertEquals("https://api.example.test/v1/oauth2/token", payload.get("aud"));
        assertEquals(300, ((Number) payload.get("exp")).longValue() - ((Number) payload.get("iat")).longValue());
        assertNotNull(payload.get("jti"));

        Signature verifier = Signature.getInstance("SHA256withECDSAinP1363Format");
        verifier.initVerify(keyPair.getPublic());
        verifier.update((parts[0] + "." + parts[1]).getBytes(StandardCharsets.UTF_8));
        assertTrue(verifier.verify(base64UrlDecode(parts[2])));
    }

    @Test
    void buildTokenRequestUsesDefaultOauthFields() {
        // This test locks the public form body contract shared with the Node,
        // Kotlin, and Python SDKs.
        TokenRequestData request = ClientAssertionHelper.buildTokenRequest("assertion-value");
        Map<String, String> body = parseFormBody(request.getBody());

        assertEquals("application/x-www-form-urlencoded", request.getHeaders().get("Content-Type"));
        assertEquals("client_credentials", body.get("grant_type"));
        assertEquals("idv.read", body.get("scope"));
        assertEquals("https://api.tomopayment.com/v1/idv", body.get("resource"));
        assertEquals(BodyOptions.DEFAULT_CLIENT_ASSERTION_TYPE, body.get("client_assertion_type"));
        assertEquals("assertion-value", body.get("client_assertion"));
    }

    @Test
    void buildTokenRequestAcceptsOverrides() {
        // This test keeps custom token request options usable for customer-specific
        // OAuth2 deployments without changing the default request behavior.
        TokenRequestData request = ClientAssertionHelper.buildTokenRequest(
                "assertion-value",
                new BodyOptions(
                        "client_credentials",
                        "idv.write",
                        "https://resource.example.test",
                        BodyOptions.DEFAULT_CLIENT_ASSERTION_TYPE
                )
        );
        Map<String, String> body = parseFormBody(request.getBody());

        assertEquals("idv.write", body.get("scope"));
        assertEquals("https://resource.example.test", body.get("resource"));
    }

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
        generator.initialize(new ECGenParameterSpec("secp256r1"));
        return generator.generateKeyPair();
    }

    private static String privateKeyToEncodedJwk(KeyPair keyPair) throws Exception {
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        Map<String, String> jwk = new LinkedHashMap<>();
        jwk.put("kty", "EC");
        jwk.put("crv", "P-256");
        jwk.put("d", base64UrlEncode(fixedLength(privateKey.getS(), 32)));
        jwk.put("x", base64UrlEncode(fixedLength(publicKey.getW().getAffineX(), 32)));
        jwk.put("y", base64UrlEncode(fixedLength(publicKey.getW().getAffineY(), 32)));
        return base64UrlEncode(MAPPER.writeValueAsBytes(jwk));
    }

    private static Map<String, Object> decodeJson(String encoded) throws Exception {
        return MAPPER.readValue(base64UrlDecode(encoded), new TypeReference<Map<String, Object>>() {});
    }

    private static Map<String, String> parseFormBody(String body) {
        return Stream.of(body.split("&"))
                .map(part -> part.split("=", 2))
                .collect(Collectors.toMap(
                        part -> urlDecode(part[0]),
                        part -> urlDecode(part[1])
                ));
    }

    private static String urlDecode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    private static byte[] base64UrlDecode(String value) {
        return Base64.getUrlDecoder().decode(padBase64(value));
    }

    private static String padBase64(String value) {
        int remainder = value.length() % 4;
        return remainder == 0 ? value : value + "=".repeat(4 - remainder);
    }

    private static byte[] fixedLength(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        byte[] result = new byte[length];
        int copyLength = Math.min(bytes.length, length);
        System.arraycopy(bytes, bytes.length - copyLength, result, length - copyLength, copyLength);
        return result;
    }
}
