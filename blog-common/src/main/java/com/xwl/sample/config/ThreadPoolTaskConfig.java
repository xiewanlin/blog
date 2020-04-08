package com.xwl.sample.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description 异步线程配置
 * @author hulvyou
 */
@Configuration
public class ThreadPoolTaskConfig {

  /**
   * 核心线程数（默认线程数）
   */
  private static final int corePoolSize = 10;
  /**
   * 最大线程数
   */
  private static final int maxPoolSize = 50;
  /**
   * 允许线程空闲时间（单位：默认为秒）
   */
  private static final int keepAliveTime = 10;
  /**
   * 缓冲队列数 这个值根据jvm内存适当调整
   */
  private static final int queueCapacity = 200;
  /**
   * 线程池名前缀
   */
  private static final String threadNamePrefix = "Async-Service-";

  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveTime);
    executor.setThreadNamePrefix(threadNamePrefix);
    // 线程池对拒绝任务的处理策略
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    // 初始化
    executor.initialize();
    return executor;
  }
}
