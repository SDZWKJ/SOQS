/**
 * 
 */
package com.zwkj.soqs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zwkj.soqs.base.BaseService;
import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.dao.OrderInfoDao;
import com.zwkj.soqs.po.OrderInfo;
import com.zwkj.soqs.service.OrderInfoService;
import com.zwkj.soqs.utils.Constants;




@Service
@Transactional
public class OrderInfoServiceImpl extends BaseService<OrderInfo> implements OrderInfoService {
	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getOrderInfo(OrderInfo orderInfo) throws Exception {
		serviceReturns = new ServiceReturns();
		//先验证改订单的合法性
		OrderInfo order = orderInfoDao.findOrderBy(orderInfo.getUserId(), orderInfo.getUserId(), orderInfo.getUserId());
		if(order==null){
			//不合法
			serviceReturns.setSuccess(false);
			serviceReturns.setMessageType(Constants.MESSAGE_TYPE_WARNING);
			serviceReturns.setMessage(Constants.ERR_ORDER_NOT_EXISTS);
			return serviceReturns;
		}
		//合法，则返回该订单信息
		serviceReturns.putData("detail",order);
		return serviceReturns;
	}
}
