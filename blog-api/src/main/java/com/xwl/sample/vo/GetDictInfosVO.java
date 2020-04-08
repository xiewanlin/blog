package com.xwl.sample.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "字典信息")
public class GetDictInfosVO {
  @ApiModelProperty(value = "港股行情权限")
  List<OptionVO> hkQuonProductInfos;
  @ApiModelProperty(value = "美股行情权限")
  List<OptionVO> usQuonProductInfos;
  @ApiModelProperty(value = "行情权限审核状态")
  List<OptionVO> quonStates;
  @ApiModelProperty(value = "行情权限来源")
  List<OptionVO> quonSource;
  @ApiModelProperty(value = "注册渠道")
  List<OptionVO> registerChannels;
  @ApiModelProperty(value = "订单状态")
  List<OptionVO> orderState;
}
