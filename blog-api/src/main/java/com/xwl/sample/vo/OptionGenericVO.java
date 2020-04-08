package com.xwl.sample.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "下拉选项信息")
public class OptionGenericVO<K, V> {
    @ApiModelProperty(value = "选项值")
    private V value;
    @ApiModelProperty(value = "选项名")
    private K label;

    public OptionGenericVO() {}

    public OptionGenericVO(K label, V value) {
        this.label = label;
        this.value = value;
    }
}
