package com.zwkj.soqs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zwkj.soqs.base.BaseService;
import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.dao.TeacherInfoDao;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.service.TeacherInfoService;
import com.zwkj.soqs.utils.Constants;
import com.zwkj.soqs.utils.Tools;
@Service
@Transactional
public class TeacherInfoServiceImpl extends BaseService<TeacherInfo> implements
		TeacherInfoService {
	
	@Autowired
	private TeacherInfoDao teacherInfoDao;
	//获取所有用户的信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ServiceReturns getAllUserInfo() throws SoqsException {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("allUser", teacherInfoDao.getAllUserInfo());
		return serviceReturns;
	}
	
	//更新用户信息
	public void updateUserById(TeacherInfo teacherInfo)
			throws SoqsException {
		String queryPassword = teacherInfo.getQueryPassword();
		TeacherInfo info = teacherInfoDao.getById(TeacherInfo.class, teacherInfo.getId());
		
		info.setTeacherName(teacherInfo.getTeacherName());
		//info.setTeacherId(teacherInfo.getTeacherId());
		info.setEmpId(teacherInfo.getEmpId());
		if(!Tools.isEmpty(queryPassword)){
			info.setQueryPassword(queryPassword);
		}
		getSession().flush();
	}

	//根据职工编号或者身份证号查询
//	public ServiceReturns findTeacInfoByEmpIdOrId(TeacherInfo teacherInfo)
//			throws SoqsException {
//		//String empId = teacherInfo.getEmpId();
//		//String teacherId = teacherInfo.getTeacherId();
//		serviceReturns = new ServiceReturns();
//		serviceReturns.putData("tInfo", teacherInfoDao.findTeacInfoByEmpIdOrId(teacherInfo));
//		return serviceReturns;
//	}

	//添加用户
	public ServiceReturns insertUser(TeacherInfo teacherInfo)
			throws SoqsException {
		serviceReturns = new ServiceReturns();
		
		TeacherInfo infoFlag = teacherInfoDao.findTeacInfoByEmpIdOrId(teacherInfo);
		if(!Tools.isNull(infoFlag)){
			serviceReturns.putData("returnMessage", Constants.USER_EXIST);
		}else{
			teacherInfoDao.insertUser(teacherInfo);
			serviceReturns.putData("returnMessage", null);
		}
		return serviceReturns;
	}
}
