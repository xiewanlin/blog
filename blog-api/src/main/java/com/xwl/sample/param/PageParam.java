package com.xwl.sample.param;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

public class PageParam {

    @ApiModelProperty(value = "页码,默认1", example = "1", position = 10)
    private Integer pageNum = 1;
    @ApiModelProperty(value = "每页数据数量,默认20", example = "20", position = 11)
    private Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (ObjectUtils.isEmpty(pageNum)) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (ObjectUtils.isEmpty(pageSize)) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }
    }

    @Override
    public String toString() {
        return "BasicForm{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + '}';
    }
}
