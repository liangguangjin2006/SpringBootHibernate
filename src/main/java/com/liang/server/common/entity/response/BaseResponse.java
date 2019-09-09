package com.liang.server.common.entity.response;

import org.json.JSONObject;

import com.liang.server.common.entity.json.LocalJSONObject;

/**
 * 接口正文回复基类
 * @author liang
 */
public abstract class BaseResponse {
	
	protected ResponseState state = ResponseState.FAIL;
	protected String msg = null;//消息，因ResponseState更改msg后会全局更改
	
	/**
	 * 获取body
	 * @return
	 */
	public abstract Object getBody();
	
	public ResponseState getState() {
		return state;
	}
	public void setState(ResponseState state) {
		this.state = state;
		msg = state.getMsg();
	}
	
	/**
	 * 设置返回信息,暂时用于失败的业务逻辑，对于不方便添加的多返回参数据，直接提示
	 * @param msg
	 */
	public void setMsg(String msg){
		if(msg != null){
			msg = msg.trim();
		}
		this.msg = msg;
	}
	
	//更改顶层正文数据，但无法覆盖state,msg,和body
	public abstract void onRootExtends(JSONObject json);
	
	/**
	 * 回复
	 * @param state
	 * @param body
	 * @return
	 */
	public JSONObject response(){
		LocalJSONObject json = new LocalJSONObject();
		this.onRootExtends(json);
		json.put("state", state.getState());
		if(msg == null || msg.equals("")){
			json.put("msg", state.getMsg());
		}else{
			json.put("msg", msg);
		}
		json.put("body", getBody());
		return json;
	}
	
	/**
	 * 回复
	 * @param state
	 * @param body
	 * @return
	 */
	public String responseStr(){
		return response().toString();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return responseStr();
	}

}
