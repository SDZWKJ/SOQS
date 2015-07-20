package com.zwkj.soqs.dao;

import java.util.List;

import com.zwkj.soqs.base.BaseDao;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.po.TeacherInfo;

public interface TeacherInfoDao extends BaseDao<TeacherInfo> {
	//获取所有用户信息
	public List<TeacherInfo> getAllUserInfo() throws SoqsException;
	//根据职工编号或者身份证号码查询用户
	public TeacherInfo findTeacInfoByEmpIdOrId(TeacherInfo teacherInfo) throws SoqsException;
	//添加用户
	public void insertUser(TeacherInfo teacherInfo) throws SoqsException;
}
