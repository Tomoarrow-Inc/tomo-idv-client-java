

# UsGetUnionResultRes

## oneOf schemas
* [UsGetResultRes](UsGetResultRes.md)
* [UsGetUnionResultResStringMap](UsGetUnionResultResStringMap.md)

## Example
```java
// Import classes:
import com.tomoarrow.idv.client.generated.model.UsGetUnionResultRes;
import com.tomoarrow.idv.client.generated.model.UsGetResultRes;
import com.tomoarrow.idv.client.generated.model.UsGetUnionResultResStringMap;

public class Example {
    public static void main(String[] args) {
        UsGetUnionResultRes exampleUsGetUnionResultRes = new UsGetUnionResultRes();

        // create a new UsGetResultRes
        UsGetResultRes exampleUsGetResultRes = new UsGetResultRes();
        // set UsGetUnionResultRes to UsGetResultRes
        exampleUsGetUnionResultRes.setActualInstance(exampleUsGetResultRes);
        // to get back the UsGetResultRes set earlier
        UsGetResultRes testUsGetResultRes = (UsGetResultRes) exampleUsGetUnionResultRes.getActualInstance();

        // create a new UsGetUnionResultResStringMap
        UsGetUnionResultResStringMap exampleUsGetUnionResultResStringMap = new UsGetUnionResultResStringMap();
        // set UsGetUnionResultRes to UsGetUnionResultResStringMap
        exampleUsGetUnionResultRes.setActualInstance(exampleUsGetUnionResultResStringMap);
        // to get back the UsGetUnionResultResStringMap set earlier
        UsGetUnionResultResStringMap testUsGetUnionResultResStringMap = (UsGetUnionResultResStringMap) exampleUsGetUnionResultRes.getActualInstance();
    }
}
```


