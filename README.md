# Tomo IDV Client Java

Java 11+ client SDK for the Tomo Identity Verification API.

Generated API bindings are written from `sdk.openapi.json` into
`com.tomoarrow.idv.client.generated`. Manual code in
`com.tomoarrow.idv.client` provides OAuth2 client assertion helpers shared with
the Node and Kotlin SDKs.

## Development

Run verification from the superproject with Docker. Do not install Gradle or
Java dependencies on the host machine.

```bash
source ./dcp
dcp local sdk java
```

Regenerate the OpenAPI client from the superproject with Docker:

```bash
source ./dcp
dcp contract gen
```
