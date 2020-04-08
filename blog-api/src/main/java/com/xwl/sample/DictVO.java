package com.xwl.sample;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "数据字典")
public class DictVO implements Serializable{
	@ApiModelProperty(value = "选项值")
	private Object type;
	@ApiModelProperty(value = "选项名")
	private Object name;
	@ApiModelProperty(value = "选项名EN")
	private Object nameEn;

	public DictVO(Object type, Object name) {
		this.type = type;
		this.name = name;
	}
	public DictVO(Object type, Object name,Object nameEn) {
		this.type = type;
		this.name = name;
		this.nameEn = nameEn;
	}
}
