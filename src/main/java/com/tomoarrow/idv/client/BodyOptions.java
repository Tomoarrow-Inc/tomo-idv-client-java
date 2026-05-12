package com.tomoarrow.idv.client;

/** Token request body options with defaults matching the Node and Kotlin SDKs. */
public final class BodyOptions {
    public static final String DEFAULT_GRANT_TYPE = "client_credentials";
    public static final String DEFAULT_SCOPE = "idv.read";
    public static final String DEFAULT_RESOURCE = "https://api.tomopayment.com/v1/idv";
    public static final String DEFAULT_CLIENT_ASSERTION_TYPE =
            "urn:ietf:params:oauth:client-assertion-type:jwt-bearer";

    private final String grantType;
    private final String scope;
    private final String resource;
    private final String clientAssertionType;

    public BodyOptions() {
        this(DEFAULT_GRANT_TYPE, DEFAULT_SCOPE, DEFAULT_RESOURCE, DEFAULT_CLIENT_ASSERTION_TYPE);
    }

    public BodyOptions(String grantType, String scope, String resource, String clientAssertionType) {
        this.grantType = grantType;
        this.scope = scope;
        this.resource = resource;
        this.clientAssertionType = clientAssertionType;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getScope() {
        return scope;
    }

    public String getResource() {
        return resource;
    }

    public String getClientAssertionType() {
        return clientAssertionType;
    }
}
