package com.zwkj.soqs.service;

import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.TeacherInfo;

public interface TeacherInfoService {
	//获取所有用户的信息
	public ServiceReturns getAllUserInfo() throws SoqsException;
	//批量删除用户
	public int deleteByIdStr(Class<TeacherInfo> clazz,String ids);
	
}
