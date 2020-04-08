package com.xwl.sample.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ConditionalOnExpression("'${spring.profiles.active}'.equals('dev') || '${spring.profiles.active}'.equals('sit') || '${spring.profiles.active}'.equals('uat')")
public class SwaggerConfig {

  @Value("${spring.profiles.active:dev}")
  private String env;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .enable(!"prd".equals(env))
        .apiInfo(apiInfo())
        .groupName("系统API")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.xwl.sample.controller"))
        .paths(PathSelectors.any())
        .build();
  }

//  @Bean
//  public Docket clientApi() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .useDefaultResponseMessages(false)
//        .apiInfo(clientApiInfo())
//        .groupName("2-CLIENT")
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.xwl.sample.controller.client"))
//        .paths(PathSelectors.any())
//        .build();
//  }

  @Bean
  public Docket webApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .apiInfo(clientApiInfo())
        .groupName("WEB-API")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.xwl.sample.controller.web"))
        .paths(PathSelectors.any())
        .build();
  }

//  @Bean
//  public Docket appApi() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .useDefaultResponseMessages(false)
//        .apiInfo(clientApiInfo())
//        .groupName("4-APP")
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.xwl.sample.controller.api"))
//        .paths(PathSelectors.any())
//        .build();
//  }

  private ApiInfo clientApiInfo() {
    return new ApiInfoBuilder()
        .title("CLIENT-API")
        .version("1.0.0")
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("xwl-API")
        .version("1.0.0")
        .build();
  }

}
