/**
 * 
 */
package com.zwkj.soqs.service;

import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.po.OrderInfo;





public interface OrderInfoService{
	public ServiceReturns getOrderInfo(OrderInfo orderInfo)throws Exception;
}
