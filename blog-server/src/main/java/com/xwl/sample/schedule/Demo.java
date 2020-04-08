package com.xwl.sample.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Service;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/8
 */
@JobHandler(value = "ActivityCostCalculateHandler")
@Service
public class Demo extends IJobHandler {

  @Override
  public ReturnT<String> execute(String param) {
    return ReturnT.SUCCESS;
  }
}
