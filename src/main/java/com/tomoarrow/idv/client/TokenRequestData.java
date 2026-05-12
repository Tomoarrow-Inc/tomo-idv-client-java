package com.tomoarrow.idv.client;

import java.util.Collections;
import java.util.Map;

/** Prepared OAuth2 token request data. */
public final class TokenRequestData {
    private final Map<String, String> headers;
    private final String body;

    public TokenRequestData(Map<String, String> headers, String body) {
        this.headers = Collections.unmodifiableMap(headers);
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
