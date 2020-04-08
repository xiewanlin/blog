package com.xwl.sample.cache;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public abstract class BaseCache<T1, T2> {
  @Resource RedissonClient redissonClient;

  public T1 get(String bucketSuffix, T2 params) throws Exception {
    String bucketName = getBucketName(bucketSuffix);
    RBucket<T1> bucket = redissonClient.getBucket(bucketName);
    if (bucket.isExists()) {
      return bucket.get();
    } else {
      String lockBucketName = bucketName + ":lock";
      RLock lock = redissonClient.getLock(lockBucketName);
      lock.lock(30L, TimeUnit.SECONDS);

      bucket = redissonClient.getBucket(bucketName);
      if (bucket.isExists()) {
        return bucket.get();
      }

      T1 value = loadDataFromDB(params);
      if (value != null) {
        bucket.set(value, getTimeToLive(), getTimeUnit());
      }

      lock.unlock();
      return value;
    }
  }

  public void put(String bucketSuffix, T1 value, long timeToLive, TimeUnit unit) {
    String bucketName = getBucketName(bucketSuffix);
    RBucket<T1> bucket = redissonClient.getBucket(bucketName);
    bucket.set(value, timeToLive, unit);
  }

  public boolean delete(String bucketSuffix) {
    String bucketName = getBucketName(bucketSuffix);
    RBucket<T1> bucket = redissonClient.getBucket(bucketName);
    if (bucket.isExists()) {
      return bucket.delete();
    }
    return true;
  }

  protected abstract T1 loadDataFromDB(T2 params) throws Exception;
  protected abstract long getTimeToLive();
  protected abstract TimeUnit getTimeUnit();
  protected abstract String getBucketName(String bucketSuffix);
}
