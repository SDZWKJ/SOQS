package com.zwkj.soqs.dao;

import com.zwkj.soqs.base.BaseDao;
import com.zwkj.soqs.po.OrderInfo;


public interface OrderInfoDao extends BaseDao<OrderInfo> {
	public OrderInfo findOrderBy(String ID, String orderID, String userID)throws Exception;
	
}
