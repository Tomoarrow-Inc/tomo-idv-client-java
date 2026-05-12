package com.tomoarrow.idv.client;

/** OAuth2 client assertion input options. */
public final class ClientAssertionOptions {
    private final String clientId;
    private final String secretKey;
    private final String baseUrl;

    public ClientAssertionOptions(String clientId, String secretKey, String baseUrl) {
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
