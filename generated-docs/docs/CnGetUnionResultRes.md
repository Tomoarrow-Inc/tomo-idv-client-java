

# CnGetUnionResultRes

## oneOf schemas
* [CnGetKycRes](CnGetKycRes.md)
* [CnGetUnionResultResStringMap](CnGetUnionResultResStringMap.md)

## Example
```java
// Import classes:
import com.tomoarrow.idv.client.generated.model.CnGetUnionResultRes;
import com.tomoarrow.idv.client.generated.model.CnGetKycRes;
import com.tomoarrow.idv.client.generated.model.CnGetUnionResultResStringMap;

public class Example {
    public static void main(String[] args) {
        CnGetUnionResultRes exampleCnGetUnionResultRes = new CnGetUnionResultRes();

        // create a new CnGetKycRes
        CnGetKycRes exampleCnGetKycRes = new CnGetKycRes();
        // set CnGetUnionResultRes to CnGetKycRes
        exampleCnGetUnionResultRes.setActualInstance(exampleCnGetKycRes);
        // to get back the CnGetKycRes set earlier
        CnGetKycRes testCnGetKycRes = (CnGetKycRes) exampleCnGetUnionResultRes.getActualInstance();

        // create a new CnGetUnionResultResStringMap
        CnGetUnionResultResStringMap exampleCnGetUnionResultResStringMap = new CnGetUnionResultResStringMap();
        // set CnGetUnionResultRes to CnGetUnionResultResStringMap
        exampleCnGetUnionResultRes.setActualInstance(exampleCnGetUnionResultResStringMap);
        // to get back the CnGetUnionResultResStringMap set earlier
        CnGetUnionResultResStringMap testCnGetUnionResultResStringMap = (CnGetUnionResultResStringMap) exampleCnGetUnionResultRes.getActualInstance();
    }
}
```


