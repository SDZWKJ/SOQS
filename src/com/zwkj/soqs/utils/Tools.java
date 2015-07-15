package com.zwkj.soqs.utils;

import com.zwkj.soqs.base.ControllerReturns;
import com.zwkj.soqs.base.MessageContent;
import com.zwkj.soqs.base.ServiceReturns;



public class Tools {

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static <T> T isNull(T obj, T defaultVal) {
		if (obj == null) {
			return defaultVal;
		}
		return obj;
	}

	public static String isNull(Object obj, String defaultVal) {
		if (obj == null) {
			return defaultVal;
		}
		return obj.toString();
	}

	public static boolean isBlank(String val) {
		if (isNull(val) || "".equals(val)) {
			return true;
		}
		return false;
	}

	public static String decode(String str, String charset) throws Exception {
		return java.net.URLDecoder.decode(str, charset);
	}

	public static String decode(String str) throws Exception {
		return java.net.URLDecoder.decode(str, "UTF-8");
	}
	
	public static ControllerReturns getExceptionControllerRetruns(Exception e){
		ControllerReturns returns = new ControllerReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(new MessageContent(Constants.ERR_COMMON_SYS_ERROR));
		return returns;
	}
	public static ServiceReturns getExceptionServiceReturns(Exception e){
		ServiceReturns returns = new ServiceReturns();
		returns.setSuccess(false);
		returns.setMessageType("error");
		returns.setMessage(new MessageContent(Constants.ERR_COMMON_SYS_ERROR));
		return returns;
	}
}