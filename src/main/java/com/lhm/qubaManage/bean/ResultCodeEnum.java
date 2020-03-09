package com.lhm.qubaManage.bean;


public enum ResultCodeEnum {

	SUCCESS(1,"成功"),
	PARAMS_IS_NULL(10001,"参数为空"),
	PARAMS_NOT_COMPLETE(10002,"参数不全"),
	PARAMS_TYPE_ERROR(10003,"参数类型错误"),
	PARAMS_IS_INVALID(10004,"参数无效"),
	USER_NOT_EXIST(20001,"用户不存在"),
	USER_NOT_LOGGED_IN(20003,"用户未登陆"),
	USER_ACCOUNT_ERROR(20004,"用户名或密码错误"),
	USER_ACCOUNT_FORBIDDEN(20005,"用户账户已被禁用"),
	USER_HAS_EXIST(20006,"用户已存在"),
	PHONE_OR_EMAIL_EXIST(20007,"该手机号码或者邮箱已被使用"),
	USER_ADD_FAIL(20008,"用户添加失败"),
	SEND_CODE_FAIL(20009,"验证码发送失败"),
	BUSINESS_ERROR(30001,"系统业务出现问题"),
	SYSTEM_INNER_ERROR(40001,"系统内部错误"),
	DATA_NOT_FOUND(50001,"数据未找到"),
	DATA_IS_WRONG(50002,"数据有误"),
	DATA_ALREADY_EXISTED(50003,"数据已存在"),
	INTERFACE_INNER_INVOKE_ERROR(60001,"系统内部接口调用异常"),
	INTERFACE_OUTER_INVOKE_ERROR(60002,"系统外部接口调用异常"),
	INTERFACE_FORBIDDEN(60003,"接口禁止访问"),
	INTERFACE_ADDRESS_INVALID(60004,"接口地址无效"),
	INTERFACE_REQUEST_TIMEOUT(60005,"接口请求超时"),
	INTERFACE_EXCEED_LOAD(60006,"接口负载过高"),
	PERMISSION_NO_ACCESS(70001,"没有访问权限"),
	COMMON_FAIL_CODE(00000,"系统出现未知错误"),
	CODE_ERROR(80001,"验证码错误"),
	USER_LOGIN_ALREADY(80002,"对不起，你的账号已经在其它地方登录，若非本人操作，请及时更换密码");
	
	private final String msg;
	private final int code;
	
	ResultCodeEnum(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public static String getMsg(int code) {  
        for (ResultCodeEnum c : ResultCodeEnum.values()) {  
            if (c.getCode() == code) {  
                return c.msg;  
            }  
        }  
        return null;  
    }  
	public static void main(String[] args) {
		System.out.println(ResultCodeEnum.getMsg(70001));
	}
}
