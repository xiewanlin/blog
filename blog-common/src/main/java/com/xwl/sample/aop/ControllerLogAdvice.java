package com.xwl.sample.aop;

import com.xwl.sample.vo.ProductResponseEntity;
import com.xwl.sample.utils.JsonUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
@ConditionalOnProperty(name = "com.xwl.aop.log.enabled", havingValue = "true")
public class ControllerLogAdvice {

  private final static Logger LOGGER = LoggerFactory.getLogger(ControllerLogAdvice.class);

  @Around("execution(* com.xwl..*Controller.*(..))")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    RequestAttributes ras = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ras;
    HttpServletRequest request = sra.getRequest();
    String uri = request.getRequestURI();
    Object[] args = pjp.getArgs();
    //输出调用日志
    StringBuilder requestLog = new StringBuilder();
    try {
      this.invokeLog(uri, request, args, requestLog);
    } catch (Exception e) {
      LOGGER.error("error", e);
    }
    long startMillis = System.currentTimeMillis();
    Exception ex = null;
    Object rtv = null;
    try {
      rtv = pjp.proceed();
    } catch (Exception e) {
      ex = e;
    }
    long endMillis = System.currentTimeMillis();
    if (rtv != null) {
      if (rtv instanceof ProductResponseEntity) {
        ProductResponseEntity resp = (ProductResponseEntity) rtv;
        if (resp.getCode() != 0) {
          requestLog.append("，失败：[code=").append(resp.getCode()).append(",msg=")
              .append(resp.getMsg()).append("]");
        } else {
          requestLog.append("，成功");
        }
      } else {
        LOGGER.warn("unknown result");
      }
    } else if (ex != null) {
      String msg = ex.getMessage() != null ? ex.getMessage() : ex.getCause().toString();
      requestLog.append("，失败：exception=").append(msg);
    }
    requestLog.append("，耗时：").append(endMillis - startMillis).append("ms");
    LOGGER.info(requestLog.toString());
    // 输出响应结果
    if (ex != null) {
      throw ex;
    }
    return rtv;
  }

  private void invokeLog(String uri, HttpServletRequest request, Object[] args,
      StringBuilder requestLog) {
    // 调用方法的参数
    requestLog.append(request.getMethod()).append("：").append(uri).append("，param：[");
    for (int i = 0, len = args.length; i < len; i++) {
      Object arg = args[i];
      if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse
          || args[i] instanceof ResponseFacade) {
        continue;
      }
      String reqStr = JsonUtils.toJson(args[i]);
      if (reqStr != null) {
        requestLog.append(reqStr).append(",");
      }
    }
    if (requestLog.charAt(requestLog.length() - 1) != '[') {
      requestLog.setLength(requestLog.length() - 1);
    }
    requestLog.append("]");
  }
}
