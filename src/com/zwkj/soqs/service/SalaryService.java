package com.zwkj.soqs.service;

import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;

public interface SalaryService {
	//根据查询条件获取工资信息
	public ServiceReturns getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException;
	//查询工资时，验证用户身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException;
	//修改密码
	public void midifyPassword(TeacherInfo teacherInfo) throws SoqsException;
	//批量删除
	public int deleteByIdStr(Class<SalaryInfo> clazz, String ids);
	//update salary info
	public void updateSalaryInfo(SalaryInfo salaryInfo) throws SoqsException;
}
