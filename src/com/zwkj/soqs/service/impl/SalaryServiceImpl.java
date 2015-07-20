package com.zwkj.soqs.service.impl;

import java.util.List;

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
import com.zwkj.soqs.utils.ExcelUtil;

@Service
@Transactional
public class SalaryServiceImpl extends BaseService<SalaryInfo> implements SalaryService {
	@Autowired
	private SalaryDao salaryDao;
	
	private List<SalaryInfo> list;
	//根据查询条件获取工资信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ServiceReturns getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException{
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("allSalaryList", salaryDao.getSalaryInfo(teacherInfo));
		return serviceReturns;
	}
	
	
	//读取工资excel，返回List<SalaryInfo>
	public List<SalaryInfo> readSalaryExcel(String targetPath)
			throws SoqsException {
		ExcelUtil excelUtil = new ExcelUtil();
		List<SalaryInfo> salaryLst = excelUtil.readSalaryExcel(targetPath);
		list = salaryLst;
		return salaryLst;
	}
	
	//根据查询条件获取工资信息list
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SalaryInfo> getSalaryLst(TeacherInfo teacherInfo)
			throws SoqsException {
		return salaryDao.getSalaryInfo(teacherInfo);
	}
	
	//把从excel读取的数据写入数据库
	public void inserSalaryInfo() throws SoqsException {
		SalaryInfo temp = list.get(0);
		TeacherInfo temp2 = new TeacherInfo();
		temp2.setSelYear(temp.getYearSalary());
		temp2.setSelMonth(temp.getMonthSalary());
		
		//int num = salaryDao.delMonthRecord(temp2);
		salaryDao.delMonthRecord(temp2);
		//system.out.println("删除的条数："+num);
		for(SalaryInfo salaryInfo : list){
			salaryDao.save(salaryInfo);
		}
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
	//删除整月记录
	public ServiceReturns delMonthRecord(TeacherInfo teacherInfo)
			throws SoqsException {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("delNum", salaryDao.delMonthRecord(teacherInfo));
		return serviceReturns;
	}
}















