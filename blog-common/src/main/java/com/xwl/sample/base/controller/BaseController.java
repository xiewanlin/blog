package com.xwl.sample.base.controller;

import com.xwl.sample.vo.ProductResponseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

    public static final ProductResponseEntity SUCCESS = new ProductResponseEntity(0, "成功");

    protected ProductResponseEntity json() {
        return new ProductResponseEntity(0, "成功");
    }

    protected ProductResponseEntity json(Object data) {
        return new ProductResponseEntity(0, "成功", data);
    }

    protected ProductResponseEntity json(Integer code, String message) {
        return new ProductResponseEntity(code, message);
    }
}
