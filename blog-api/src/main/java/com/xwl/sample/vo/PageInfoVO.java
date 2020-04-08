package com.xwl.sample.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "分页信息")
public class PageInfoVO<T> {
  @ApiModelProperty(value = "当前页码")
  private Integer pageNum;
  @ApiModelProperty(value = "分页大小")
  private Integer pageSize;
  @ApiModelProperty(value = "页码数")
  private Integer pages;
  @ApiModelProperty(value = "记录数")
  private Long total;
  @ApiModelProperty(value = "数据")
  private List<T> list;

  public PageInfoVO() {
  }

  public PageInfoVO(Integer pageNum, Integer pageSize, Integer pages, Long total, List<T> list) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.pages = pages;
    this.total = total;
    this.list = list;
  }
}
