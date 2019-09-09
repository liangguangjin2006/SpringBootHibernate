package com.liang.server.common.entity.response;

/**
 * 回复状态
 * @author liang
 */
public enum ResponseState {
	
	SUCCESS(1,"成功"),
	FAIL(0,"失败");
	
	private int state;
	private String msg;
	
	private ResponseState(int state, String msg){
		this.state = state;
		this.msg = msg;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
