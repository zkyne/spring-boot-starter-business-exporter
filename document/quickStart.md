### 三.接入使用

#### Step1采集客户端接入使用

引入依赖

```java
  <dependency>
     <groupId>com.zkyne</groupId>
     <artifactId>spring-boot-starter-business-exporter</artifactId>
     <version>1.0.0</version>
  </dependency>
```

采集配置

```properties
spring.business.exporter.built-in-http-enabled=true
spring.business.exporter.client.request-uri=/prometheus/metrics
spring.business.exporter.client.client-port=9093
# 指标xxx_metric_name
spring.business.exporter.metrics.xxx_metric_name.custom-collector-enabled=false
spring.business.exporter.metrics.xxx_metric_name.custom-collector=com.iqiyi.pcell.biz.collector.DefaultCollector
spring.business.exporter.metrics.xxx_metric_name.excute-sql=select column1,column2,column3 as AliasColumn,column4 from tableName
spring.business.exporter.metrics.xxx_metric_name.name=xxx_metric_name
spring.business.exporter.metrics.xxx_metric_name.help=help
spring.business.exporter.metrics.xxx_metric_name.value-key=column1
spring.business.exporter.metrics.xxx_metric_name.tag-keys=column1,column2,AliasColumn,column4
# 指标yyy
spring.business.exporter.metrics.yyy.custom-collector-enabled=false
spring.business.exporter.metrics.yyy.custom-collector=com.iqiyi.pcell.biz.collector.DefaultCollector
spring.business.exporter.metrics.yyy.excute-sql=select ...
spring.business.exporter.metrics.yyy.name=yyy
spring.business.exporter.metrics.yyy.help=help
spring.business.exporter.metrics.yyy.value-key=...
spring.business.exporter.metrics.yyy.tag-keys=...
```

如果想要指标暴露端口与项目端口一致,需要项目中对外暴露相关接口

```java
	@RequestMapping("/exporter")
    public void exporter(HttpServletResponse response) throws IOException {
        OutputStreamWriter osw = null;
        try{
            response.setContentType(TextFormat.CONTENT_TYPE_004);
            osw = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
            TextFormat.write004(osw, CollectorRegistry.defaultRegistry.metricFamilySamples());
            osw.flush();
            osw.close();
        }catch (Exception e){
            //
        }finally {
            if(osw != null){
                osw.close();
            }
        }
    }
```

#### Step2服务端prometheus配置

1.使用外部的prometheus服务,通过配置`spring.business.exporter.sync-config-to-server-enabled=true`
然后配置`spring.business.exporter.prometheus.*`等配置
将配置数据通过接口同步至外部prometheus服务(暂未实现)

2.使用的是自己搭建的prometheus服务
直接在prometheus服务的prometheus.yml中配置即可使用的是静态采集配置static_configs
