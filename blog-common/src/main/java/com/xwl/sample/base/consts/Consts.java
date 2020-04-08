package com.xwl.sample.base.consts;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/3
 */
public interface Consts {
  int SUCCESS_CODE = 0; // 成功响应 code
  String SUCCESS_MSG = "成功"; // 成功响应 msg
  int DEFAULT_PAGE_SIZE = 20; // 默认分页大小
  String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 默认时间格式
  String AUTHENTICATION_HEADER = "Authorization";
  int EXCEL_EXPORT_TYPE_QUOTATION = 1; // 行情权限导出类型

  //请求头语言类型
  String LANGUAGE = "X-Lang";
}
