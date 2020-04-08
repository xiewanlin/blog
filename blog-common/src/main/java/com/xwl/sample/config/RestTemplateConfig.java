package com.xwl.sample.config;

import java.nio.charset.Charset;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean(name = "restTemplate")
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
    simpleClientHttpRequestFactory.setReadTimeout(60000);
    simpleClientHttpRequestFactory.setConnectTimeout(10000);
    restTemplate.setRequestFactory(simpleClientHttpRequestFactory);

    List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
    for (HttpMessageConverter<?> httpMessageConverter : list) {
      if (httpMessageConverter instanceof StringHttpMessageConverter) {
        ((StringHttpMessageConverter) httpMessageConverter)
            .setDefaultCharset(Charset.forName("UTF-8"));
      }
    }
    return restTemplate;
  }

}
