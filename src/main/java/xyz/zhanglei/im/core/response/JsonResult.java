package xyz.zhanglei.im.core.response;

import xyz.zhanglei.im.core.constants.ResponseType;

public class JsonResult <T>{

    // 错误码
    int errorCode;

    // 错误信息
    String errorMessage;

    // 返回数据
    T data;

    public JsonResult(int errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> JsonResult success(String errorMessage, T data) {
        return new JsonResult(ResponseType.SUCCESS, errorMessage, data);
    }

    public static <T> JsonResult fail(String errorMessage, T data) {
        return new JsonResult(ResponseType.FAIL, errorMessage, data);
    }
}
