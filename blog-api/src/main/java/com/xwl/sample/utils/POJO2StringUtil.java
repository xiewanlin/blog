package com.xwl.sample.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POJO2StringUtil {

  private static Logger logger = LoggerFactory.getLogger(POJO2StringUtil.class);

  private POJO2StringUtil() {
  }

  public static String object2String(Object obj) {
    if (null == obj) {
      return null;
    }
    if (String.class.isInstance(obj) || Integer.class.isInstance(obj) || Double.class.isInstance(obj) || Float.class.isInstance(obj) || Long.class
        .isInstance(obj) || Short.class.isInstance(obj)) {
      return (String) obj;
    }
    StringBuilder strBuffer = new StringBuilder(obj.getClass().getSimpleName() + "{");
    Field[] fields = obj.getClass().getDeclaredFields();
    if (fields != null && fields.length > 0) {
      for (int i = 0, len = fields.length; i < len; i++) {
        String fieldName = fields[i].getName();
        PropertyDescriptor pd = getPropertyDescriptor(obj.getClass(), fieldName);
        if (null == pd) {
          continue;
        }
        strBuffer.append("\"").append(fieldName).append("\":");
        Method getMethod = pd.getReadMethod();
        if (getMethod != null) {
          try {
            Object val = getPropertyValue(obj, fieldName);
            if (null == val) {
              strBuffer.append("null,");
            } else if (val instanceof Number || val instanceof Boolean) {
              strBuffer.append(val).append(",");
            } else {
              strBuffer.append("\"")
                  .append(String.valueOf(val))
                  .append("\",");
            }
          } catch (Exception e) {
            logger.error(e.getMessage(), e);
            strBuffer.append("#,");
          }
        } else {
          strBuffer.append("#,");
        }
      }
    }
    strBuffer.setLength(strBuffer.length() - 1);
    strBuffer.append("}");
    return strBuffer.toString();
  }

  public static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) {
    Method setMethod = null;
    Method getMethod = null;
    PropertyDescriptor pd = null;
    try {
      Field f = clazz.getDeclaredField(propertyName);
      if (f != null) {
        String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        setMethod = clazz.getDeclaredMethod("set" + methodEnd, new Class[]{f.getType()});
        try {
          getMethod = clazz.getDeclaredMethod("get" + methodEnd, new Class[]{});
        } catch (Exception e) {
          getMethod = clazz.getDeclaredMethod("is" + methodEnd, new Class[]{});
        }
        pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
      }
    } catch (Exception e) {
      return null;
    }
    return pd;
  }

  public static Object getPropertyValue(Object obj, String propertyName) {
    Object value = null;
    try {
      PropertyDescriptor pd = getPropertyDescriptor(obj.getClass(), propertyName);
      Method getMethod = pd.getReadMethod();
      value = getMethod.invoke(obj, new Object[]{});
    } catch (Exception e) {
      value = "#";
    }
    return value;
  }
}
