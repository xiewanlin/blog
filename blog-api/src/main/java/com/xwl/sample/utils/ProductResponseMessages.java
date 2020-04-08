package com.xwl.sample.utils;


import java.util.HashMap;
import java.util.Map;

public interface ProductResponseMessages {

  String SUCCESS_MSG = "成功";
  String WRONG_MSG = "校验错误";

  String REQUEST_ERROR_MSG = "非法请求";
  String TOKEN_WRONG_MSG = "非法TOKEN";

  String USER_NOT_FOUND = "用户不存在";
  String PAGE_SIZE_OVERSIZE = "最大获取1000条";
  Map<String, String> MESSAGE_MAP = new HashMap<String, String>() {
    {

      put(EXTERNAL_ERROR_CODE.SIGN_IN_SUCCESS, "注册成功");
      put(EXTERNAL_ERROR_CODE.LOG_IN_SUCCESS, "登录成功");

    }
  };

  interface EXTERNAL_ERROR_CODE {
    String SIGN_IN_SUCCESS = ProductResponseCodes.SUCCESS_CODE + "sign-in";
    String LOG_IN_SUCCESS = ProductResponseCodes.SUCCESS_CODE + "log-in";
  }

}
