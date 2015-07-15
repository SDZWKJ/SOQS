package com.zwkj.soqs.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.base.impl.BaseDaoImpl;
import com.zwkj.soqs.dao.AdminDao;
import com.zwkj.soqs.po.AdminInfo;
@Repository
public class AdminDaoImpl extends BaseDaoImpl<AdminInfo> implements AdminDao {

	//验证管理员登录
	public AdminInfo validateLogin(AdminInfo adminInfo) throws SoqsException {
		String loginId = adminInfo.getLoginId();
		String loginPassword = adminInfo.getLoginPassword();
		StringBuilder hql = new StringBuilder(128);
		hql.append("from adminInfo where loginId=:loginId and loginPassword=:loginPassword");
		hql.append(" and 1=1");
		Query query = getSession().createQuery(hql.toString());
		query.setString("loginId", loginId);
		query.setString("loginPassword", loginPassword);
		return (AdminInfo) query.uniqueResult();
	}

}
