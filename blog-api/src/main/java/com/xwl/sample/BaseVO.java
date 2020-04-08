package com.xwl.sample;

import com.xwl.sample.utils.POJO2StringUtil;
import java.io.Serializable;

public class BaseVO implements Serializable{

  @Override
  public String toString() {
    return POJO2StringUtil.object2String(this);
  }
}
