package com.xwl.sample.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.xwl.sample.mapper"})
public class MybatisConfig {

  /**
   * 配置数据源
   *
   * @return DruidDataSource
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  /**
   * 分页拦截器配置
   */
  @Bean
  public PageInterceptor pageInterceptors() {
    PageInterceptor pageInterceptor = new PageInterceptor();
    Properties properties = new Properties();
    properties.setProperty("helperDialect", "mysql");
    properties.setProperty("reasonable", "false");
    properties.setProperty("supportMethodsArguments", "true");
    properties.setProperty("returnPageInfo", "count=check");
    properties.setProperty("params", "count=countSql");
    pageInterceptor.setProperties(properties);

//    MybatisLogInterceptor mybatisLogInterceptor = new MybatisLogInterceptor();
//    Properties properties1 = new Properties();
//    mybatisLogInterceptor.setProperties(properties1);

    return pageInterceptor;
  }

}
