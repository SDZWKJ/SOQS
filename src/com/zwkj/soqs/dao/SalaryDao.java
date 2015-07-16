package com.zwkj.soqs.dao;

import java.util.List;

import com.zwkj.soqs.base.BaseDao;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;

public interface SalaryDao extends BaseDao<SalaryInfo> {
	//获取所有工资信息
	public List<SalaryInfo> getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException;
	//查询工资时验证身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException;
	//根据teacherId 获取用户信息
	public TeacherInfo findByTeacId(String teacherId) throws SoqsException;
	//根据用户身份证查询半年的工资
	//public List<SalaryInfo> getSalaryListById(SalaryInfo salaryInfo) throws SoqsException;

}
