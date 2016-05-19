package com.baseapp.framework.bean.response;

import java.io.Serializable;

/**
 * 所有网络请求返回的基类
 * @ClassName ResponseBean 
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author 王博扬
 * @date 2015-12-14 下午4:37:15 
 * @history
 * 1.YYYY-MM-DD
 *    author:
 *    description:
 */
public class ResponseBean implements Serializable {
	private String status;

	private int code;
	
	private String msg;

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

	public void setStatus(String status){ this.status = status; }

	public String getStatus() {
		return status;
	}

	
}
