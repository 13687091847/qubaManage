package com.lhm.qubaManage.bean;

import java.io.Serializable;

/**  
 * @package: com.lhm.qubaManage.bean
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:45:57 
 */
@SuppressWarnings("serial")
public class Result<T> implements Serializable {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        this.errorCode = success ? ResultCodeEnum.SUCCESS.getCode() : ResultCodeEnum.COMMON_FAIL_CODE.getCode();
        this.errorMsg = success ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.COMMON_FAIL_CODE.getMsg();
    }

    public Result(boolean success, ResultCodeEnum resultEnum) {
        this.success = success;
        this.errorCode = success ? ResultCodeEnum.SUCCESS.getCode() : (resultEnum == null ? ResultCodeEnum.COMMON_FAIL_CODE.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ResultCodeEnum.SUCCESS.getMsg() : (resultEnum == null ? ResultCodeEnum.COMMON_FAIL_CODE.getMsg() : resultEnum.getMsg());
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.errorCode = success ? ResultCodeEnum.SUCCESS.getCode() : ResultCodeEnum.COMMON_FAIL_CODE.getCode();
        this.errorMsg = success ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.COMMON_FAIL_CODE.getMsg();
        this.data = data;
    }

    public Result(boolean success, ResultCodeEnum resultEnum, T data) {
        this.success = success;
        this.errorCode = success ? ResultCodeEnum.SUCCESS.getCode() : (resultEnum == null ? ResultCodeEnum.COMMON_FAIL_CODE.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ResultCodeEnum.SUCCESS.getMsg() : (resultEnum == null ? ResultCodeEnum.COMMON_FAIL_CODE.getMsg() : resultEnum.getMsg());
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
