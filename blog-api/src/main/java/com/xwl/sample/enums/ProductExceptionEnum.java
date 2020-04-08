package com.xwl.sample.enums;

public enum ProductExceptionEnum {


    /**
     * ================
     *     公共错误 709000~709999
     * ================
     */

    T2_ERROR(709999,"调用t2服务错误","調用t2服務錯誤","t2 server connection error"),
    SYSTEM_ERROR(709998,"服务器内部错误","服務器內部錯誤","Server error"),
    PARAM_ERROR(709997,"参数错误","參數錯誤","Parameter error"),
    ENUM_NOT_EXIST(709996,"枚举不存在","枚舉不存在","Enumeration not found"),
    SERVER_BUSY_ERROR(709910,"系统繁忙, 请稍后重试","系統繁忙, 請稍後重試","The system is busy, please try again later"),
    INFO_SAVE_FAIL(709992,"信息保存失败","信息保存失敗","Information save failed"),
    INFO_UPDATE_FAIL(709991,"信息变更失败","信息變更失敗","Information change failed"),
    SERVER_CALL_ERROR(709990,"服务调用错误","服務調用錯誤","Service connection error"),
    STATUS_ERROR(709989,"状态错误","狀態錯誤","Status error"),
    PARAM_CHECK_FAIL(709995,"参数{0}校验失败","參數{0}校驗失敗","Parameter {0} verification failed"),
    CLASS_OBJECT_ERROR(709994,"类对象错误","類對象錯誤","Class error"),
    TOKEN_AUTH_ERROR(709993,"身份验证失败","身份驗證失敗","Authentication failed"),
    BASE_ADMIN_INFO_ERROR(709992,"查询中台系统信息失败","查詢中台系統信息失敗","Failed to query the information in the middle platform"),
    USER_INFO_NOT_EXIST(709001,"用户信息不存在","用戶信息不存在","User information not found"),
    PARAM_EXIST(709002,"{0}已存在,不可重复","{0}已存在,不可重複","{0} already exists, cannot duplicate"),
    COUPON_COUNT_OVER(709003,"优惠券已被领完,请修改库存","優惠券已被領完,請修改庫存","The coupon is out of stock, please modify the stock"),
    DATA_VALIDATION_FAIL(709000,"数据校验失败","數據校驗失敗","Data verification failed"),
    PARAM_MUST_NOT_NULL(708003,"参数{0}不能为空","參數{0}不能爲空","Parameter {0} cannot be empty"),
    SAME_NAME(708002,"{0}已存在,请勿重复输入","{0}已存在,請勿重複輸入","{0} already exists, please do not enter it again"),
    ADD_FAIL(708001,"添加{0}信息失败","添加{0}信息失敗","Failed to add {0} information"),
    UPDATE_FAIL(708997,"编辑{0}信息失败","編輯{0}信息失敗","Editing {0} information failed"),
    SELECT_FAIL(708000,"查询失败","查詢失敗","Query failed"),
    INFO_NO_EXISTS(708999,"{0}信息不存在","{0}信息不存在","{0} information not found"),
    INFO_NO_UPDATE(708998,"{0}信息不允许编辑","{0}信息不允許編輯","{0} information is not allowed to edit"),
    EXCEL_FAIL(708997,"操作EXCEL失败","操作EXCEL失敗","Edit EXCEL failed"),
    COMMON_SUCCESS_CODE(0,"调用成功","調用成功","Successful connection");

    private Integer code;
    private String jtMessage;
    private String ftMessage;
    private String english;

    ProductExceptionEnum(Integer code, String jtMessage,String ftMessage,String english) {
        this.code = code;
        this.jtMessage = jtMessage;
        this.ftMessage = ftMessage;
        this.english = english;
    }


    public Integer getCode() {
        return code;
    }

    public String getJtMessage() {
        return jtMessage;
    }

    public String getFtMessage() {
        return ftMessage;
    }

    public String getEnglish() {
        return english;
    }

    public static ProductExceptionEnum getEnumByCode(Integer code) {
        for (ProductExceptionEnum productExceptionEnum : ProductExceptionEnum.values()) {
            if (code.equals(productExceptionEnum.getCode())) {
                return productExceptionEnum;
            }
        }
        return null;
    }

    public String getMessage(Integer language){
        switch (language) {
            case 1:
                return this.getJtMessage();
            case 2:
                return this.getFtMessage();
            case 3:
                return this.getEnglish();
            default:
                return this.getJtMessage();
        }
    }
}
