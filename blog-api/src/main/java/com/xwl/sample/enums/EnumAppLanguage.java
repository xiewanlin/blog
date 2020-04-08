package com.xwl.sample.enums;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/3
 */
public enum EnumAppLanguage {
  JT(1, "简体"),
  FT(2, "繁体"),
  EN(3, "英文");

  private Integer type;
  private String name;

  private EnumAppLanguage(Integer type, String name) {
    this.type = type;
    this.name = name;
  }

  public static Boolean verifyIsOptions(Integer type) {
    EnumAppLanguage[] var1 = values();
    int var2 = var1.length;

    for(int var3 = 0; var3 < var2; ++var3) {
      EnumAppLanguage enumAppLanguage = var1[var3];
      if (enumAppLanguage.type.equals(type)) {
        return true;
      }
    }

    return false;
  }

  public Integer getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }
}
