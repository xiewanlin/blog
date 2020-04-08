package com.xwl.sample.base.model;

import com.xwl.sample.utils.GsonUtil;

public class BaseVO {
  @Override
  public String toString() {
    return GsonUtil.toJson(this);
  }
}
