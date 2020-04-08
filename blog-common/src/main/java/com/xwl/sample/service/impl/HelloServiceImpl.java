package com.xwl.sample.service.impl;

import com.xwl.sample.mapper.generated.BlogTagMapper;
import com.xwl.sample.po.generated.BlogTag;
import com.xwl.sample.po.generated.BlogTagExample;
import com.xwl.sample.service.HelloService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/8
 */
@Service
public class HelloServiceImpl implements HelloService {

  @Resource
  private BlogTagMapper blogTagMapper;

  @Override
  public String sayHello() {
    BlogTagExample example = new BlogTagExample();
    example.createCriteria().andIdEqualTo(1L);
    List<BlogTag> blogTagList = blogTagMapper.selectByExample(example);

    return blogTagList.get(0).getName();
  }
}
