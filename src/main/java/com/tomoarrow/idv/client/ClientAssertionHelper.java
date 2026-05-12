package com.tomoarrow.idv.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/** Helper for creating ES256 JWT client assertions and token request bodies. */
public final class ClientAssertionHelper {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Base64.Encoder BASE64_URL_ENCODER = Base64.getUrlEncoder().withoutPadding();
    private static final Base64.Decoder BASE64_URL_DECODER = Base64.getUrlDecoder();

    private ClientAssertionHelper() {}

    public static String createClientAssertion(ClientAssertionOptions options) {
        try {
            Map<String, String> jwk = decodeBase64UrlJwk(options.getSecretKey());
            ECPrivateKey privateKey = jwkToPrivateKey(jwk);
            long now = System.currentTimeMillis() / 1000L;

            Map<String, Object> payload = new LinkedHashMap<>();
            payload.put("iss", options.getClientId());
            payload.put("sub", options.getClientId());
            payload.put("aud", options.getBaseUrl() + "/v1/oauth2/token");
            payload.put("iat", now);
            payload.put("exp", now + 300);
            payload.put("jti", UUID.randomUUID().toString());

            return signJwt(privateKey, payload);
        } catch (Exception error) {
            throw new IllegalArgumentException("Failed to create client assertion", error);
        }
    }

    public static TokenRequestData buildTokenRequest(String clientAssertion) {
        return buildTokenRequest(clientAssertion, new BodyOptions());
    }

    public static TokenRequestData buildTokenRequest(
            String clientAssertion,
            BodyOptions options
    ) {
        Map<String, String> headers = Map.of("Content-Type", "application/x-www-form-urlencoded");
        String body = String.join(
                "&",
                formPair("grant_type", options.getGrantType()),
                formPair("scope", options.getScope()),
                formPair("resource", options.getResource()),
                formPair("client_assertion_type", options.getClientAssertionType()),
                formPair("client_assertion", clientAssertion)
        );
        return new TokenRequestData(headers, body);
    }

    private static String signJwt(ECPrivateKey privateKey, Map<String, Object> payload)
            throws Exception {
        Map<String, String> header = new LinkedHashMap<>();
        header.put("alg", "ES256");
        header.put("typ", "JWT");

        String encodedHeader = base64UrlEncode(MAPPER.writeValueAsBytes(header));
        String encodedPayload = base64UrlEncode(MAPPER.writeValueAsBytes(payload));
        String signingInput = encodedHeader + "." + encodedPayload;

        Signature signer = Signature.getInstance("SHA256withECDSAinP1363Format");
        signer.initSign(privateKey);
        signer.update(signingInput.getBytes(StandardCharsets.UTF_8));
        String encodedSignature = base64UrlEncode(signer.sign());

        return signingInput + "." + encodedSignature;
    }

    private static Map<String, String> decodeBase64UrlJwk(String encodedJwk) throws Exception {
        byte[] decoded = BASE64_URL_DECODER.decode(padBase64(encodedJwk));
        Map<String, String> jwk = MAPPER.readValue(decoded, new TypeReference<Map<String, String>>() {});
        if (!"EC".equals(jwk.get("kty")) || !"P-256".equals(jwk.get("crv")) || !jwk.containsKey("d")) {
            throw new IllegalArgumentException("secretKey must encode an EC P-256 private JWK");
        }
        return jwk;
    }

    private static ECPrivateKey jwkToPrivateKey(Map<String, String> jwk) throws Exception {
        BigInteger d = new BigInteger(1, BASE64_URL_DECODER.decode(padBase64(jwk.get("d"))));
        AlgorithmParameters params = AlgorithmParameters.getInstance("EC");
        params.init(new ECGenParameterSpec("secp256r1"));
        ECParameterSpec ecSpec = params.getParameterSpec(ECParameterSpec.class);
        return (ECPrivateKey) KeyFactory.getInstance("EC").generatePrivate(new ECPrivateKeySpec(d, ecSpec));
    }

    private static String formPair(String key, String value) {
        return URLEncoder.encode(key, StandardCharsets.UTF_8)
                + "="
                + URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private static String base64UrlEncode(byte[] data) {
        return BASE64_URL_ENCODER.encodeToString(data);
    }

    private static String padBase64(String data) {
        int remainder = data.length() % 4;
        if (remainder == 0) {
            return data;
        }
        return data + "=".repeat(4 - remainder);
    }
}
