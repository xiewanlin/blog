package com.xwl.sample.controller;

import com.xwl.sample.base.controller.BaseController;
import com.xwl.sample.service.HelloService;
import com.xwl.sample.vo.ProductResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/8
 */
@RestController
@RequestMapping(value = "/blog-server/api")
@Api(tags = "博客相关api")
public class HelloWorldController extends BaseController {

  @Autowired
  private HelloService helloService;

  @GetMapping("/article-list/v1")
  @ApiOperation(value = "获取文章列表", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ProductResponseEntity<String> articleList() {
    return json(helloService.sayHello());
  }

}
