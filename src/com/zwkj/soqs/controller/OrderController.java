package com.zwkj.soqs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwkj.soqs.base.BaseController;
import com.zwkj.soqs.po.OrderInfo;
import com.zwkj.soqs.service.OrderInfoService;
import com.zwkj.soqs.utils.Tools;


@Scope("request")
@Controller
public class OrderController extends BaseController{
	@Resource
	private OrderInfoService orderInfoService;

	@RequestMapping(value = "/soqs/1111/index", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getOrderInfo(HttpServletRequest request) {
		try {
			String ID = request.getParameter("ID");
			
			OrderInfo orderInfo = new OrderInfo();
			//orderInfo.setId(Integer.valueOf(ID));
			//orderInfo.setOrderCode(orderCode);
			orderInfo.setUserId("33");
			
			returns = orderInfoService.getOrderInfo(orderInfo);
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
}
