### 四.特殊定制需求

#### 场景1:我的指标没办法通过一个sql就能查询出来,需要我业务中进行汇总处理

需要自己实现ICollector接口,并配置即可

```java
public class TestCollector implements ICollector {

    @Override
    public List<Map<String, Object>> collectData(String s) {
        List<Map<String,Object>> maps  = Lists.newArrayList();
        // 自行获取相关数据进行组装并返回 
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("userName", "张三");
        map1.put("age", 12);
        map1.put("gender", "男");
        maps.add(map1);
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("userName", "李四");
        map2.put("age", 11);
        map2.put("gender", "男");
        return maps;
    }
}
```

```properties
spring.business.exporter.metrics.xxx.custom-collector-enabled=true
spring.business.exporter.metrics.xxx.custom-collector=com.***.***.TestCollector
```

#### 场景2:我的多个指标的执行sql可能分别是从不同数据源获取而来的

此时建议使用基础依赖business-exporter,然后进行相关定制开发接入,具体参考business-exporter的接入指南

