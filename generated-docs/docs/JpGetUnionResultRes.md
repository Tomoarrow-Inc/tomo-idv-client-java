

# JpGetUnionResultRes

## oneOf schemas
* [JpGetResultRes](JpGetResultRes.md)
* [JpGetUnionResultResStringMap](JpGetUnionResultResStringMap.md)

## Example
```java
// Import classes:
import com.tomoarrow.idv.client.generated.model.JpGetUnionResultRes;
import com.tomoarrow.idv.client.generated.model.JpGetResultRes;
import com.tomoarrow.idv.client.generated.model.JpGetUnionResultResStringMap;

public class Example {
    public static void main(String[] args) {
        JpGetUnionResultRes exampleJpGetUnionResultRes = new JpGetUnionResultRes();

        // create a new JpGetResultRes
        JpGetResultRes exampleJpGetResultRes = new JpGetResultRes();
        // set JpGetUnionResultRes to JpGetResultRes
        exampleJpGetUnionResultRes.setActualInstance(exampleJpGetResultRes);
        // to get back the JpGetResultRes set earlier
        JpGetResultRes testJpGetResultRes = (JpGetResultRes) exampleJpGetUnionResultRes.getActualInstance();

        // create a new JpGetUnionResultResStringMap
        JpGetUnionResultResStringMap exampleJpGetUnionResultResStringMap = new JpGetUnionResultResStringMap();
        // set JpGetUnionResultRes to JpGetUnionResultResStringMap
        exampleJpGetUnionResultRes.setActualInstance(exampleJpGetUnionResultResStringMap);
        // to get back the JpGetUnionResultResStringMap set earlier
        JpGetUnionResultResStringMap testJpGetUnionResultResStringMap = (JpGetUnionResultResStringMap) exampleJpGetUnionResultRes.getActualInstance();
    }
}
```


