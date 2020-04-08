package com.xwl.sample.aop;

import com.xwl.sample.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FeignServiceAdvice {

  @Around("(execution(* com.xwl..*FeignService.*(..))) || (execution(* com.xwl..*feign..*(..)))")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    long startMillis = System.currentTimeMillis();
    Object rtv = pjp.proceed();
    long endMillis = System.currentTimeMillis();

    long costMillis = endMillis - startMillis;
    Signature signature = pjp.getSignature();
    String target = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getName());

    log.info("call {},args={},resp={},cost={}ms", target, GsonUtil.toJson(pjp.getArgs()), GsonUtil.toJson(rtv), costMillis);
    return rtv;
  }
}
