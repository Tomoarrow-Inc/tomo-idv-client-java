package com.tomoarrow.idv.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tomoarrow.idv.client.generated.ApiClient;
import com.tomoarrow.idv.client.generated.api.DefaultApi;
import com.tomoarrow.idv.client.generated.model.StartIdvReq;
import org.junit.jupiter.api.Test;

class GeneratedClientTest {
    @Test
    void generatedClientExportsImportableApiAndModel() {
        // This test verifies that OpenAPI code generation was copied into the
        // public package and representative generated classes compile.
        ApiClient apiClient = new ApiClient();
        DefaultApi api = new DefaultApi(apiClient);
        StartIdvReq model = new StartIdvReq().userId("user-123");

        assertNotNull(api);
        assertEquals("user-123", model.getUserId());
    }
}
