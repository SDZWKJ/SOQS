package com.zwkj.soqs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zwkj.soqs.base.BaseService;
import com.zwkj.soqs.base.ServiceReturns;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.dao.SalaryDao;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.service.SalaryService;

@Service
@Transactional
public class SalaryServiceImpl extends BaseService<SalaryInfo> implements SalaryService {
	@Autowired
	private SalaryDao salaryDao;
	//根据查询条件获取工资信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ServiceReturns getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException{
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("allSalaryList", salaryDao.getSalaryInfo(teacherInfo));
		return serviceReturns;
	}
	//验证工资查询时的身份
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException {
		return salaryDao.verifyId(teacherInfo);
	}
	//修改密码
	public void midifyPassword(TeacherInfo teacherInfo) throws SoqsException {
		TeacherInfo info = salaryDao.findByTeacId(teacherInfo.getTeacherId());
		info.setQueryPassword(teacherInfo.getNewPassword());
		getSession().flush();
	}
}
