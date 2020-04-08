package com.xwl.sample.aop;

import com.xwl.sample.enums.ProductExceptionEnum;
import com.xwl.sample.exception.BusinessException;
import com.xwl.sample.manager.UserHeaderManager;
import com.xwl.sample.utils.GsonUtil;
import com.xwl.sample.vo.ProductResponseEntity;
import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
@Slf4j
public class ControllerAdviceInterceptor {

  @Autowired
  private UserHeaderManager userHeaderManager;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
  }

  @Before("initBinder()")
  public void initBinderBefore(JoinPoint jp) {
    Object[] args = jp.getArgs();
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    if (request.getMethod().equals(HttpMethod.POST.name())) {
      String content = GsonUtil.toJson(args != null && args.length > 0 ? args[0] : "");
      if (content != null && content.length() > 5000) {
        content = "内容过大，参数不打印";
      }
      log.info("请求路径:[{}],请求参数[{}]", request.getRequestURI(), content);
    } else {
      log.info("请求路径:[{}],请求参数[{}]", request.getRequestURI(),
          GsonUtil.toJson(request.getParameterMap()));
    }
  }

  @ResponseBody
  @ExceptionHandler(value = BusinessException.class)
  public ProductResponseEntity handleBusinessException(BusinessException e) {
    log.error("产品业务异常", e);
    Integer headerLanguage = userHeaderManager.getHeaderLanguage();
    String msg = getProductResponseMsg(e.getCode(), headerLanguage);
    return new ProductResponseEntity(e.getCode(),
        StringUtils.isNotEmpty(msg) ? msg : e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = Throwable.class)
  public ProductResponseEntity handleThrowable(Throwable e) {
    log.error("产品系统异常 Throwable error", e);
    ProductExceptionEnum exEnum = ProductExceptionEnum.SYSTEM_ERROR;
    Integer headerLanguage = userHeaderManager.getHeaderLanguage();
    String msg = getProductResponseMsg(exEnum.getCode(), headerLanguage);
    return new ProductResponseEntity(exEnum.getCode(),
        StringUtils.isNotEmpty(msg) ? msg : exEnum.getJtMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public ProductResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException e) {
    log.error("产品系统异常 HttpMessageNotReadableException error", e);
    ProductExceptionEnum exEnum = ProductExceptionEnum.PARAM_ERROR;
    return new ProductResponseEntity(exEnum.getCode(), "参数非法");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ProductResponseEntity handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    ProductResponseEntity result = new ProductResponseEntity<>();
    result.setCode(ProductExceptionEnum.DATA_VALIDATION_FAIL.getCode());
    BindingResult bindingResult = exception.getBindingResult();
    List<ObjectError> allErrors = bindingResult.getAllErrors();
    List<String> errorParamList = getErrorParamList(allErrors);
    List<String> errList = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    Integer headerLanguage = userHeaderManager.getHeaderLanguage();
    String msg = MessageFormat
        .format(ProductExceptionEnum.PARAM_CHECK_FAIL.getMessage(headerLanguage), errorParamList)
        + ":";
    msg = msg + String.join(",", errList);
    result.setMsg(msg);
    log.error(result.getMsg(), exception);
    return result;
  }

  /**
   * 将error中的参数获取出来
   * @param allErrors
   * @return
   */
  private List<String> getErrorParamList(List<ObjectError> allErrors) {
    return allErrors.stream().map(objectError -> {
      String paramsStr;
      if (objectError instanceof FieldError) {
        paramsStr = ((FieldError) objectError).getField();
      } else {
        paramsStr = objectError.getObjectName();
      }
      return paramsStr;
    }).collect(Collectors.toList());
  }

  private String getProductResponseMsg(Integer code, Integer headerLanguage) {
    ProductExceptionEnum productExceptionEnum = ProductExceptionEnum.getEnumByCode(code);
    if (productExceptionEnum == null) {
      return null;
    }
    Matcher m = Pattern.compile("\\{(\\d)}").matcher(productExceptionEnum.getJtMessage());
    if (m.find()) {
      //暂时只支持没有占位符的多语言
      return null;
    }
    return productExceptionEnum.getMessage(headerLanguage);
  }
}
