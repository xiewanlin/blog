package com.xwl.sample.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

  public final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
  public static ObjectMapper objectMapper = null;

  static {
    objectMapper = new ObjectMapper();

    //反序列化的时候如果多了其他属性,不抛出异常
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //如果是空对象的时候,不抛异常
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  }

  /**
   * serialize object to json
   * @param object
   * @param <T>
   * @return
   */
  public static <T> String toJson(T object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      logger.error("serialize object {} failed, exception is", object, e);
    }
    return null;
  }

  /**
   * parse json to object
   * @param json
   * @param classType
   * @param <T>
   * @return
   */
  public static <T> T parseJson(String json, Class<T> classType) {
    try {
      return objectMapper.readValue(json, classType);
    } catch (Exception e) {
      logger.error("deserialize json {} failed, exception is {}",
          json, classType, e);
    }
    return null;
  }

  public static <T> T parseJson(String json, TypeReference classType) {
    try {
      return objectMapper.readValue(json, classType);
    } catch (Exception e) {
      logger.error("deserialize json {} failed, exception is {}",
          json, classType, e);
    }
    return null;
  }

}
