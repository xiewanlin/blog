package com.xwl.sample.param;

import io.swagger.annotations.ApiModelProperty;

public class OrderParam extends PageParam {
  @ApiModelProperty(value = "排序字段", example = "createTime", required = false, position = 100)
  private String orderBy;
  @ApiModelProperty(value = "是否降序排序", example = "true", required = false, position = 101)
  private Boolean desc;

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Boolean getDesc() {
    return desc;
  }

  public void setDesc(Boolean desc) {
    this.desc = desc;
  }
}
