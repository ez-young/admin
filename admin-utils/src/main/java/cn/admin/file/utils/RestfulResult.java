package com.hzhetun.example.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ChenBingKun
 * @date 2017-07-23
 */
public class RestfulResult {
	/**
	 * 状态码
	 */
	public int code;
	
	public long timestamp;

	public String msg;
	/**
	 * 返回的内容
	 * 
	 */
	public Map<String, Object> data=new HashMap<String,Object>();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public RestfulResult(){

    }

	public RestfulResult(int code){
		this.code=code;
		this.timestamp=System.currentTimeMillis()/1000;
	}

	public RestfulResult(int code,String msg){
		this.code=code;
		this.msg=msg;
		this.timestamp=System.currentTimeMillis()/1000;
	}

	public RestfulResult(int code, String key, Object value){
		this(code);
		data.put(key, value);
	}

	public RestfulResult(int code, String msg,String key, Object value){
		this(code,msg);
		data.put(key, value);
	}
	
	public RestfulResult(int code, Map<String,Object> map){
		this(code);
		data.putAll(map);
	}
	
	/**
	 * ok 正常
	 * @return
	 */
	public static RestfulResult success(){
		return new RestfulResult(200);
	}

	public static RestfulResult success(String msg){
		return new RestfulResult(200,msg);
	}

	/**
	 * 404错误
	 * @return
	 */
	public static RestfulResult error404(){
		return new RestfulResult(404);
	}
	
	/**
	 * 400错误
	 * @return
	 */
	public static RestfulResult error400(){
		return new RestfulResult(400);
	}
	
	/**
	 * 500错误
	 * @return
	 */
	public static RestfulResult error500(){
		return new RestfulResult(500);
	}

	public static RestfulResult error500(String msg){
		return new RestfulResult(500,msg);
	}

	public static RestfulResult warning(int code,String msg){
		return new RestfulResult(code,msg);
	}
}
