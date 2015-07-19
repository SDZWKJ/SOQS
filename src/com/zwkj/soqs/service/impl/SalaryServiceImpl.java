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
	
	//update salary info
	public void updateSalaryInfo(SalaryInfo salaryInfo) throws SoqsException {
		int id = salaryInfo.getId();
		SalaryInfo info = salaryDao.getById(SalaryInfo.class, id);
		
		info.setEmpId(salaryInfo.getEmpId());
		info.setTeacherName(salaryInfo.getTeacherName());
		info.setYfSalary(salaryInfo.getYfSalary());
		info.setSfSalary(salaryInfo.getSfSalary());
		info.setJcSalary(salaryInfo.getJcSalary());
		info.setGwSalary(salaryInfo.getGwSalary());
		info.setXjSalary(salaryInfo.getXjSalary());
		info.setGlSalary(salaryInfo.getGlSalary());
		info.setTgSalary(salaryInfo.getTgSalary());
		info.setJtSalary(salaryInfo.getJtSalary());
		info.setQtjbSalary(salaryInfo.getQtjbSalary());
		info.setSydwjtbthjAllowance(salaryInfo.getSydwjtbthjAllowance());
		info.setZwbtAllowance(salaryInfo.getZwbtAllowance());
		info.setTgjtAllowance(salaryInfo.getTgjtAllowance());
		info.setJxjtAllowance(salaryInfo.getJxjtAllowance());
		info.setJhljtAllowance(salaryInfo.getJhljtAllowance());
		info.setBzrAllowance(salaryInfo.getBzrAllowance());
		info.setGgxbtWybtAllowance(salaryInfo.getGgxbtWybtAllowance());
		info.setQtbzAllowance(salaryInfo.getQtbzAllowance());
		info.setKpAward(salaryInfo.getKpAward());
		info.setQtSalary(salaryInfo.getQtSalary());
		info.setDsznAllowance(salaryInfo.getDsznAllowance());
		info.setBfgzyfSalary(salaryInfo.getBfgzyfSalary());
		info.setZfAllowance(salaryInfo.getZfAllowance());
		info.setKfTotal(salaryInfo.getKfTotal());
		info.setKgjjMoney(salaryInfo.getKgjjMoney());
		info.setKyalbxMoney(salaryInfo.getKyalbxMoney());
		info.setKyilbxMoney(salaryInfo.getKyilbxMoney());
		info.setDbbzjMoney(salaryInfo.getDbbzjMoney());
		info.setIitMoney(salaryInfo.getIitMoney());
		info.setKiitMoney(salaryInfo.getKiitMoney());
		info.setMonthSalary(salaryInfo.getMonthSalary());
		info.setYearSalary(salaryInfo.getYearSalary());
		info.setSfjsTax(salaryInfo.getSfjsTax());
		
		getSession().flush();
	}
}















