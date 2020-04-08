package com.xwl.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

  private static Pattern linePattern = Pattern.compile("_(\\w)");
  private static Pattern humpPattern = Pattern.compile("[A-Z]");

  /**下划线转驼峰*/
  public static String lineToHump(String str) {
    str = str.toLowerCase();
    Matcher matcher = linePattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  /**驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})*/
  public static String humpToLine(String str) {
    return str.replaceAll("[A-Z]", "_$0").toLowerCase();
  }

  /**驼峰转下划线,效率比上面高*/
  public static String humpToLine2(String str) {
    Matcher matcher = humpPattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  public static boolean isEmpty(String str) {
    if (str == null) {
      return true;
    }
    if ("".equals(str.trim())) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  public static void main(String[] args) {
    String lineToHump = lineToHump("f_parent_no_leader");
    System.out.println(lineToHump);
    System.out.println(humpToLine(lineToHump));
    System.out.println(humpToLine2(lineToHump));
  }
}