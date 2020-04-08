package com.xwl.sample.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.xxl.job.core.executor.XxlJobExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xwl.sample.schedule")
@EnableApolloConfig
public class XxlJobConfig {
  private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

  @Autowired
  InetUtilsProperties properties;
  @Value("${spring.job.addresses}")
  private String adminAddresses;
  @Value("${spring.application.name}")
  private String appName;
  @Value("${spring.job.port}")
  private int port;
  @Value("${spring.job.logpath}")
  private String logPath;
  @Value("${spring.job.logretentiondays}")
  private int logRetentionDays;

  @Bean(initMethod = "start", destroyMethod = "destroy")
  public XxlJobExecutor xxlJobUserExecutor() {
    logger.info(">>>>>>>>>>> xxl-job com.sample.config init.");
    XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
    xxlJobExecutor.setAdminAddresses(adminAddresses);

    xxlJobExecutor.setAppName(appName);
    xxlJobExecutor.setPort(port);
    xxlJobExecutor.setLogPath(logPath);
    xxlJobExecutor.setLogRetentionDays(logRetentionDays);
    InetUtils inetUtils = new InetUtils(properties);
    String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
    if (StringUtils.isNotBlank(ip)) {
      xxlJobExecutor.setIp(ip);
    }
    return xxlJobExecutor;
  }

}
