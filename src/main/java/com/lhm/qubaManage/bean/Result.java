package com.lhm.qubaManage.bean;
/**  
 * @package: com.lhm.qubaManage.bean
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:45:57 
 */
public class Result {
	private int code;
	private String msg;
	public Result(int code) {
	    this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
