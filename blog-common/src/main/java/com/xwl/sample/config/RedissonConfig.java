package com.xwl.sample.config;

import java.lang.reflect.Method;
import java.util.List;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

@Configuration
public class RedissonConfig {

  @Autowired
  private RedisProperties redisProperties;


  @Bean(destroyMethod = "shutdown")
  public RedissonClient redisson() {
    Method clusterMethod = ReflectionUtils.findMethod(RedisProperties.class, "getCluster");
    Method timeoutMethod = ReflectionUtils.findMethod(RedisProperties.class, "getTimeout");
    Object timeoutValue = ReflectionUtils.invokeMethod(timeoutMethod, redisProperties);
    int timeout;
    if (!(timeoutValue instanceof Integer)) {
      Method millisMethod = ReflectionUtils.findMethod(timeoutValue.getClass(), "toMillis");
      timeout = ((Long) ReflectionUtils.invokeMethod(millisMethod, timeoutValue)).intValue();
    } else {
      timeout = (Integer) timeoutValue;
    }

    Object clusterObject = ReflectionUtils.invokeMethod(clusterMethod, redisProperties);
    Method nodesMethod = ReflectionUtils.findMethod(clusterObject.getClass(), "getNodes");
    List<String> nodeList = (List) ReflectionUtils.invokeMethod(nodesMethod, clusterObject);

    String[] nodes = new String[nodeList.size()];
    for (int i = 0; i < nodeList.size(); i++) {
      nodes[i] = "redis://" + nodeList.get(i);
    }

    Config config = new Config();
    config.setNettyThreads(8);
    config.useClusterServers()
        .addNodeAddress(nodes)
        .setConnectTimeout(timeout)
        .setTimeout(timeout)
        .setRetryInterval(timeout)
        .setPassword(redisProperties.getPassword());

    return Redisson.create(config);
  }
}
