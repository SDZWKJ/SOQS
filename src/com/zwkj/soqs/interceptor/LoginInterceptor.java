package com.zwkj.soqs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zwkj.soqs.po.AdminInfo;
import com.zwkj.soqs.utils.Tools;

/**
 * 后台登录认证的拦截器
 * @author XH
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView view) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//获取请求url
		String url = request.getRequestURI();
		logger.info("url: "+url);
		//判断是否是公开地址(登录提交地址等)
		if(url.indexOf("login.html")>=0 || url.indexOf("validate.html")>=0){
			//如果进行登录提交，放行
			return true;
		}
		//判断session
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("user");
		if(!Tools.isNull(adminInfo)){
			//已经登陆，放行
			return true;
		}
		//验证没有通过，跳转到登录界面
		response.sendRedirect("login.html");
		return false;
	}

}
