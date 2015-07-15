package com.zwkj.soqs.service;

import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;

public interface SalaryService {
	//获取所有工资信息
	public ServiceReturns getAllSalaryInfo() throws SoqsException;
	//查询工资时，验证用户身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException;
	//修改密码
	public void midifyPassword(TeacherInfo teacherInfo) throws SoqsException;
	//获取验证用户半年的工资
	public ServiceReturns getSalaryListById(SalaryInfo salaryInfo) throws SoqsException;
	//批量删除
	public int deleteByIdStr(Class<SalaryInfo> clazz, String ids);
}
