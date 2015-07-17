package com.zwkj.soqs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.datetime.DateFormatter;

import com.zwkj.soqs.base.ControllerReturns;
import com.zwkj.soqs.base.MessageContent;
import com.zwkj.soqs.base.ServiceReturns;



public class Tools {
	
	//返回当前时间
    public static Date getToday(){
    	Calendar CALENDAR=Calendar.getInstance();
    	return CALENDAR.getTime();
    }
    //日期格式化
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		DateFormatter format = new DateFormatter("yyyy-MM-dd HH:mm:ss");
		return format.print(date, Locale.getDefault());
	}
	
	public static String formatDate(Date date,String patten) {
		if (date == null) {
			return "";
		}
		DateFormatter format = new DateFormatter(patten);
		return format.print(date, Locale.getDefault());
	}

	public static Date parseToDate(String source) throws ParseException {
		return parseToDate(source,Constants.DATE_PATTEN);
	}
	
	public static Date parseToDate(Date source) throws ParseException {
		String tmp = formatDate(source,Constants.DATE_PATTEN);
		return parseToDate(tmp);
	}
	
	public static Date parseToDate(String source,String patten) throws ParseException {
		if (isEmpty(source)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		
		return sdf.parse(source);
	}
    
	public static boolean isEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}
    
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