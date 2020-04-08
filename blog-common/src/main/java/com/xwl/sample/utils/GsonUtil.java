package com.xwl.sample.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

  private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static Gson gson;

  private GsonUtil() {
  }

  public static String toJson(Object o) {
    return getInstance().toJson(o);
  }

  public static <T> T str2Obj(String str, Class<T> clazz) {
    T o = (T) getInstance().fromJson(str, clazz);
    return o;
  }

  public static Gson getInstance() {
    if (gson == null) {
      synchronized (GsonUtil.class) {
        if (gson == null) {
          gson = newInstance(DEFAULT_DATE_FORMAT, true);
        }
      }
    }
    return gson;
  }

  public static Gson newInstance(String dateFormat, boolean serializeNulls) {
    GsonBuilder builder = new GsonBuilder();
    if (serializeNulls) {
      builder.serializeNulls();
    }
    if (dateFormat != null) {
      builder.setDateFormat(dateFormat);
    }
    return builder.create();
  }

}
