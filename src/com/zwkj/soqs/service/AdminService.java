package com.zwkj.soqs.service;

import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.AdminInfo;

public interface AdminService {
	//验证管理员登录
	public AdminInfo validateLogin(AdminInfo adminInfo) throws SoqsException;
	//导入工资信息
	public void insertSalaryInfo(String path) throws SoqsException;
	//导入用户信息
	public void insertUserInfo(String path) throws SoqsException;
}
