package com.xwl.sample.base.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xwl.sample.utils.GsonUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class BaseModel {
  @ApiModelProperty(value = "获取页码", position = -2)
  private Integer pageNum;
  @ApiModelProperty(value = "分页大小", position = -1)
  private Integer pageSize;
  @Override
  public String toString() {
    return GsonUtil.toJson(this);
  }
}
