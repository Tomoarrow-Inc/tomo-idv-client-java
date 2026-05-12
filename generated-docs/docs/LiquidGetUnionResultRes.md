

# LiquidGetUnionResultRes

## oneOf schemas
* [LiquidGetResultRes](LiquidGetResultRes.md)
* [LiquidGetUnionResultResStringMap](LiquidGetUnionResultResStringMap.md)

## Example
```java
// Import classes:
import com.tomoarrow.idv.client.generated.model.LiquidGetUnionResultRes;
import com.tomoarrow.idv.client.generated.model.LiquidGetResultRes;
import com.tomoarrow.idv.client.generated.model.LiquidGetUnionResultResStringMap;

public class Example {
    public static void main(String[] args) {
        LiquidGetUnionResultRes exampleLiquidGetUnionResultRes = new LiquidGetUnionResultRes();

        // create a new LiquidGetResultRes
        LiquidGetResultRes exampleLiquidGetResultRes = new LiquidGetResultRes();
        // set LiquidGetUnionResultRes to LiquidGetResultRes
        exampleLiquidGetUnionResultRes.setActualInstance(exampleLiquidGetResultRes);
        // to get back the LiquidGetResultRes set earlier
        LiquidGetResultRes testLiquidGetResultRes = (LiquidGetResultRes) exampleLiquidGetUnionResultRes.getActualInstance();

        // create a new LiquidGetUnionResultResStringMap
        LiquidGetUnionResultResStringMap exampleLiquidGetUnionResultResStringMap = new LiquidGetUnionResultResStringMap();
        // set LiquidGetUnionResultRes to LiquidGetUnionResultResStringMap
        exampleLiquidGetUnionResultRes.setActualInstance(exampleLiquidGetUnionResultResStringMap);
        // to get back the LiquidGetUnionResultResStringMap set earlier
        LiquidGetUnionResultResStringMap testLiquidGetUnionResultResStringMap = (LiquidGetUnionResultResStringMap) exampleLiquidGetUnionResultRes.getActualInstance();
    }
}
```


