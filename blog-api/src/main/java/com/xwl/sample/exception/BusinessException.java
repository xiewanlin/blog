package com.xwl.sample.exception;

import com.xwl.sample.enums.ProductExceptionEnum;
import java.text.MessageFormat;

public class BusinessException extends RuntimeException {

  /**
   * 错误代码
   */
  private Integer code;


  public BusinessException(Integer code,String msg) {
	    super(msg);
	    this.code = code;
  }

  public BusinessException(ProductExceptionEnum exceptionEnum, Object... params) {
    super(MessageFormat.format(exceptionEnum.getJtMessage(), params));
    this.code = exceptionEnum.getCode();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return "BusinessException{" + "code='" + code + '\'' + ", message='" + getMessage() + '\'' + '}';
  }
}