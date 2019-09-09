package com.liang.server.common.entity.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 本地json,用于处理添加String空对像时返回空串
 * @author liang
 */
public class LocalJSONObject extends JSONObject {
	
	@Override
	public JSONObject put(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value==null?"":value);
	}

}
