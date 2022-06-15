package com.zkyne.business.auto;

import com.zkyne.business.collector.BusinessExporter;
import com.zkyne.business.config.ExporterConfigOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @className: BizExporterAutoConfiguration
 * @description:
 * @author: zkyne
 * @date: 2020/10/27 18:03
 * @see <a href=""></a>
 */
@Configuration
@ConditionalOnClass({BusinessExporter.class})
@EnableConfigurationProperties({ExporterConfigOptions.class})
public class BusinessExporterAutoConfiguration {

    @Resource
    private DataSource dataSource;


    @Bean
    @ConditionalOnMissingBean(BusinessExporter.class)
    public BusinessExporter singleCollector(ExporterConfigOptions exporterConfigOptions, JdbcTemplate jdbcTemplate){
        return BusinessExporter.builder()
            .exporterConfigOptions(exporterConfigOptions)
            .jdbcTemplate(jdbcTemplate)
            .build();
    }

    @Bean
    @ConditionalOnClass(DataSource.class)
    @ConditionalOnMissingBean(JdbcTemplate.class)
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

}
