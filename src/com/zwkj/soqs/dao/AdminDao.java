package com.zwkj.soqs.dao;

import com.zwkj.soqs.base.BaseDao;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.AdminInfo;

public interface AdminDao extends BaseDao<AdminInfo> {
	//验证用户登录
	public AdminInfo validateLogin(AdminInfo adminInfo) throws SoqsException;

}
