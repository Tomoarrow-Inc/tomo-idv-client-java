

# TencentGetUnionResultRes

## oneOf schemas
* [TencentGetKycRes](TencentGetKycRes.md)
* [TencentGetUnionResultResStringMap](TencentGetUnionResultResStringMap.md)

## Example
```java
// Import classes:
import com.tomoarrow.idv.client.generated.model.TencentGetUnionResultRes;
import com.tomoarrow.idv.client.generated.model.TencentGetKycRes;
import com.tomoarrow.idv.client.generated.model.TencentGetUnionResultResStringMap;

public class Example {
    public static void main(String[] args) {
        TencentGetUnionResultRes exampleTencentGetUnionResultRes = new TencentGetUnionResultRes();

        // create a new TencentGetKycRes
        TencentGetKycRes exampleTencentGetKycRes = new TencentGetKycRes();
        // set TencentGetUnionResultRes to TencentGetKycRes
        exampleTencentGetUnionResultRes.setActualInstance(exampleTencentGetKycRes);
        // to get back the TencentGetKycRes set earlier
        TencentGetKycRes testTencentGetKycRes = (TencentGetKycRes) exampleTencentGetUnionResultRes.getActualInstance();

        // create a new TencentGetUnionResultResStringMap
        TencentGetUnionResultResStringMap exampleTencentGetUnionResultResStringMap = new TencentGetUnionResultResStringMap();
        // set TencentGetUnionResultRes to TencentGetUnionResultResStringMap
        exampleTencentGetUnionResultRes.setActualInstance(exampleTencentGetUnionResultResStringMap);
        // to get back the TencentGetUnionResultResStringMap set earlier
        TencentGetUnionResultResStringMap testTencentGetUnionResultResStringMap = (TencentGetUnionResultResStringMap) exampleTencentGetUnionResultRes.getActualInstance();
    }
}
```


