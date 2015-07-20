package com.zwkj.soqs.service;

import java.util.List;

import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;

public interface SalaryService {
	//根据查询条件获取工资信息
	public ServiceReturns getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException;
	
	//读取Excel的内容放入list中
	public List<SalaryInfo> readSalaryExcel(String targetPath) throws SoqsException;
	
	//根据查询条件获取工资信息
	public List<SalaryInfo> getSalaryLst(TeacherInfo teacherInfo) throws SoqsException;
	
	//把从excel读取的数据写入数据库
	public void inserSalaryInfo() throws SoqsException;
	
	
	
	//查询工资时，验证用户身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException;
	//修改密码
	public void midifyPassword(TeacherInfo teacherInfo) throws SoqsException;
	//批量删除
	public int deleteByIdStr(Class<SalaryInfo> clazz, String ids);
	//删除整月记录
	public ServiceReturns delMonthRecord(TeacherInfo teacherInfo) throws SoqsException;
	//update salary info
	public void updateSalaryInfo(SalaryInfo salaryInfo) throws SoqsException;
}
