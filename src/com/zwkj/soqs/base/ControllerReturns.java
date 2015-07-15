package com.zwkj.soqs.base;


import java.util.HashMap;
import java.util.Map;

import com.zwkj.soqs.utils.CacheUtils;
import com.zwkj.soqs.utils.GsonSingleton;
import com.zwkj.soqs.utils.Tools;





//@Component
//@Scope("prototype")
//Controller返回类型
public class ControllerReturns{
	protected boolean success = true;
	protected Map<Object, Object> data = new HashMap<Object, Object>();
	protected String forwardUrl = "";
	protected MessageContent message = new MessageContent("10000");
	protected String messageType = "info";//info | error | warning 

	public ControllerReturns(){}
	
	public ControllerReturns(ServiceReturns serviceReturns){
		messageType = serviceReturns.getMessageType();
		message = serviceReturns.getMessage();
		success = serviceReturns.isSuccess();
		forwardUrl = serviceReturns.getForwardUrl();
		data = serviceReturns.getData();
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<Object, Object> getData() {
		return data;
	}
	
	public void putData(String key, Object value) {
		data.put(key, value);
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public MessageContent getMessage() {
		return message;
	}

	public void setMessage(MessageContent message) {
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = new MessageContent(message);
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String generateJsonData(){
		messageType = Tools.isNull(CacheUtils.getSpringRequest().getAttribute("messageType"), "info");
		message = (MessageContent)CacheUtils.getSpringRequest().getAttribute("message");
		//success = Tools.isNull((Boolean)CacheUtils.getSpringRequest().getAttribute("success"),true);
		return GsonSingleton.getGsonInstance().toJson(this);
	}
}
