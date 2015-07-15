package com.zwkj.soqs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zwkj.soqs.base.BaseService;
import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.dao.AdminDao;
import com.zwkj.soqs.dao.SalaryDao;
import com.zwkj.soqs.dao.TeacherInfoDao;
import com.zwkj.soqs.po.AdminInfo;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.service.AdminService;
import com.zwkj.soqs.utils.ExcelUtil;
@Service
@Transactional
public class AdminServiceImpl extends BaseService<AdminInfo> implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private SalaryDao salaryDao;
	@Autowired
	private TeacherInfoDao teacherInfoDao;
	
	//验证管理员登录
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public AdminInfo validateLogin(AdminInfo adminInfo) throws SoqsException {
		return adminDao.validateLogin(adminInfo);
	}
	//读取工资excel内容，写入数据库
	public void insertSalaryInfo(String path) throws SoqsException {
		ExcelUtil excelUtil = new ExcelUtil();
		List<SalaryInfo> salaryLst = excelUtil.readSalaryExcel(path);
		if(!CollectionUtils.isEmpty(salaryLst)){
			for(SalaryInfo info : salaryLst){
				SalaryInfo salaryInfo = new SalaryInfo();
				salaryInfo = info;
				salaryDao.save(salaryInfo);
			}
		}
		
	}
	//读取用户excel内容，写入数据库
	public void insertUserInfo(String path) throws SoqsException {
		ExcelUtil excelUtil = new ExcelUtil();
		List<TeacherInfo> userLst = excelUtil.readUserExcel(path);
		if(!CollectionUtils.isEmpty(userLst)){
			for(TeacherInfo info : userLst){
				teacherInfoDao.save(info);
			}
		}
	}
	
}
