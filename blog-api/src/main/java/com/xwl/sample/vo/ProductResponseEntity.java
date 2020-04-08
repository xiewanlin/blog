package com.xwl.sample.vo;


import com.github.pagehelper.PageInfo;
import com.xwl.sample.exception.BusinessException;
import com.xwl.sample.utils.JsonUtils;
import com.xwl.sample.utils.ProductResponseCodes;
import com.xwl.sample.utils.ProductResponseMessages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "ProductResponseEntity", description = "接口统一出参")
public class ProductResponseEntity<T> extends BaseVO {

    @ApiModelProperty(value = "响应码", dataType = "String")
    private Integer code;
    @ApiModelProperty(value = "响应内容", dataType = "String")
    private String msg;
    @ApiModelProperty(value = "响应体", dataType = "String")
    private T data;

    public ProductResponseEntity() {

    }

    public ProductResponseEntity(Integer code) {
        this.code = code;
    }

    public ProductResponseEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ProductResponseEntity(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ProductResponseEntity(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回数据
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ProductResponseEntity<T> ok(T t) {
        ProductResponseEntity<T> productResponseEntity = new ProductResponseEntity<>();
        productResponseEntity.setData(t);
        productResponseEntity.setCode(ProductResponseCodes.SUCCESS_CODE);
        productResponseEntity.setMsg(ProductResponseMessages.SUCCESS_MSG);
        return productResponseEntity;
    }

    /**
     * 分页返回数据
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> ProductResponseEntity<PageInfoVO<T>> ok(PageInfo<T> pageInfo) {
        PageInfoVO<T> retPage = convertOne(PageInfoVO.class, pageInfo);
        return ProductResponseEntity.ok(retPage);
    }

    /**
     * 没有数据，只有提示信息
     * @return
     */
    public static ProductResponseEntity ok() {
        ProductResponseEntity productResponseEntity = new ProductResponseEntity<>();
        productResponseEntity.setCode(ProductResponseCodes.SUCCESS_CODE);
        productResponseEntity.setMsg(ProductResponseMessages.SUCCESS_MSG);
        return productResponseEntity;
    }

    /**
     * 同时指定消息和数据返回
     * @param message
     * @param body
     * @param <T>
     * @return
     */
    public static <T> ProductResponseEntity ok(String message, T body) {
        ProductResponseEntity productResponseEntity = ok(body);
        productResponseEntity.setMsg(message);
        return productResponseEntity;
    }

    public static ProductResponseEntity error(Integer code, String message) {
        ProductResponseEntity productResponseEntity = new ProductResponseEntity();
        productResponseEntity.setCode(code);
        productResponseEntity.setMsg(message);
        return productResponseEntity;
    }

    public static ProductResponseEntity error(Integer code, String message, Object... arg) {
        ProductResponseEntity productResponseEntity = new ProductResponseEntity();
        productResponseEntity.setCode(code);
        productResponseEntity.setMsg(String.format(message, arg));
        return productResponseEntity;
    }

    /**
     * 根据一个业务异常返回数据
     * @param exception
     * @return
     */
    public static ProductResponseEntity<Void> error(BusinessException exception) {
        ProductResponseEntity<Void> productResponseEntity = new ProductResponseEntity<>();
        productResponseEntity.setCode(exception.getCode());
        productResponseEntity.setMsg(exception.getMessage());
        return productResponseEntity;
    }

    public static ProductResponseEntity requestError() {
        return error(ProductResponseCodes.REQUEST_ERROR_CODE, ProductResponseMessages.REQUEST_ERROR_MSG);
    }

    public static ProductResponseEntity tokenError() {
        return error(ProductResponseCodes.TOKEN_WRONG_CODE, ProductResponseMessages.TOKEN_WRONG_MSG);
    }

    public ProductResponseEntity map(String key, Object value) {
        if (this.data == null) {
            this.data = (T) new HashMap<String, Object>(2);
        }

        Map<String, Object> map = (Map<String, Object>) this.data;
        map.put(key, value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean assertTrue() {
        return code.intValue() == ProductResponseCodes.SUCCESS_CODE;
    }

    public static <T> T convertOne(Class<T> target, Object source) {
        if (source == null) {
            return null;
        }
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        // 要返回标准json格式数据
        return JsonUtils.toJson(this);
    }
}
