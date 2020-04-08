package com.xwl.sample.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "下拉选项信息")
public class OptionVO {
  @ApiModelProperty(value = "选项值")
  private Integer value;
  @ApiModelProperty(value = "选项名")
  private String label;

  public OptionVO() {}

  public OptionVO(Integer value, String label) {
    this.value = value;
    this.label = label;
  }
}
