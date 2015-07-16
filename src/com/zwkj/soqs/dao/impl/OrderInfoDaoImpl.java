package com.zwkj.soqs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zwkj.soqs.base.impl.BaseDaoImpl;
import com.zwkj.soqs.dao.OrderInfoDao;
import com.zwkj.soqs.po.OrderInfo;



@Repository
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {
	public OrderInfo findOrderBy(String ID, String orderID, String userID)
			throws Exception {
		Query query = getSession().createQuery("FROM orderInfo ");
		List<?> list = query.list();
		return (OrderInfo)(list.get(0));
	}
	
}
