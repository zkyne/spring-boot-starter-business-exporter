### 二.相关配置详解

```properties
# 是否启用内置http服务对外暴露数据,默认为false
spring.business.exporter.built-in-http-enabled=false
# 采集客户端对外暴露的prometheus指标采集路径,当built-in-http-enabled为true时生效,默认已支持"/metrics"
spring.business.exporter.client.request-uri=
# 采集客户端对外暴露的prometheus指标采集端口号,当built-in-http-enabled为true时生效,默认端口为9093
spring.business.exporter.client.client-port=9093

# 是否自动将采集相关配置同步到prometheus服务端,默认不开启同步,暂时同步功能未实现
spring.business.exporter.sync-config-to-server-enabled=false
# 预定义配置,prometheus服务的namespace,当sync-config-to-server-enabled为true时生效,
spring.business.exporter.prometheus.namespace=
# 预定义配置,prometheus配置的采集的job_name,当sync-config-to-server-enabled为true时生效,默认为UUID生成
spring.business.exporter.prometheus.job-name=
# 预定义配置,prometheus配置的采集的采集间隔scrape_interval,当sync-config-to-server-enabled为true时生效,默认为1m
spring.business.exporter.prometheus.scrape-interval=1m
# 预定义配置,prometheus配置的采集的采集超时scrape_timeout,当sync-config-to-server-enabled为true时生效,默认为15s
spring.business.exporter.prometheus.scrape-timeout=15s
# 预定义配置,prometheus配置的采集的采集超时targets,当sync-config-to-server-enabled为true时生效,配置当前客户端的域名即可
spring.business.exporter.prometheus.targets=

# 某个metric指标采集,是否开启自定义实现采集器采集,默认不开启,使用内置DefaultCollector采集器采集
spring.business.exporter.metrics.xxx.custom-collector-enabled=false
# 某个metric指标采集,自定义实现采集器,当custom-collector-enabled为true时配置,并且不能配置为DefaultCollector,自定义采集器需要实现ICollector接口,并且需要提供空参构造方法
spring.business.exporter.metrics.xxx.custom-collector=com.iqiyi.pcell.biz.collector.DefaultCollector
# 某个metric指标采集,采集sql配置,当custom-collector-enabled为false时需要配置,返回的结果集格式应为List<Map<String, Object>>
spring.business.exporter.metrics.xxx.excute-sql=select ...
# 某个metric指标采集,指标名称配置,不能为空,且不能重复
spring.business.exporter.metrics.xxx.name=metric_name
# 某个metric指标采集,指标描述配置,默认为"help"
spring.business.exporter.metrics.xxx.help=help
# 某个metric指标采集,指标值的key,即为sql查询结果集当中的某个列的名称,指的是将该列的数值作为该指标的指标值,只能选取数值和时间类型的列作为指标值
spring.business.exporter.metrics.xxx.value-key=key
# 某个metric指标采集,指标值的标签keys,即为sql查询结果集当中的某些列的名称,指的是将这些列的数值作为该指标的标签属性,以便后续分组查询
spring.business.exporter.metrics.xxx.tag-keys=key1,key2,key3,key4
```
