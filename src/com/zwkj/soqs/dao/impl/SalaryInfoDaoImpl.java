package com.zwkj.soqs.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.base.impl.BaseDaoImpl;
import com.zwkj.soqs.dao.SalaryDao;
import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;
import com.zwkj.soqs.utils.Tools;

@Repository
public class SalaryInfoDaoImpl extends BaseDaoImpl<SalaryInfo> implements SalaryDao {
	//获取所有工资信息
	public List<SalaryInfo> getAllSalaryInfo() throws SoqsException {
		List<SalaryInfo> salaryInfoList = new ArrayList<SalaryInfo>();
		
		StringBuilder sql = new StringBuilder(128);
		sql.append("select a.ID,a.TEACHER_ID,a.TEACHER_NAME,a.YEAR,a.MONTH,a.SALARY");
		sql.append(" from SALARY_INFO a");
		sql.append(" where 1=1");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		List<?> list = sqlQuery.list();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			Object[] obj = null;
			SalaryInfo salaryInfo = null;
			while(itor.hasNext()){
				obj = (Object[]) itor.next();
				salaryInfo = new SalaryInfo();
				salaryInfo.setId(Integer.parseInt(obj[0].toString()));
				salaryInfo.setTeacherId(Tools.isNull(obj[1], ""));
				salaryInfo.setTeacherName(Tools.isNull(obj[2], ""));
				salaryInfo.setYear(Tools.isNull(obj[3], ""));
				salaryInfo.setMonth(Tools.isNull(obj[4], ""));
				salaryInfo.setSalary(Tools.isNull(obj[5], ""));
				salaryInfoList.add(salaryInfo);
			}
		}
		return salaryInfoList;
	}

	//查询工资时验证身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException {
		String teacherId = teacherInfo.getTeacherId();
		String queryPassword = teacherInfo.getQueryPassword();
		 StringBuilder hql = new StringBuilder();
		 hql.append("from teacherInfo where teacherId=:teacherId and queryPassword=:queryPassword");
		 Query query = getSession().createQuery(hql.toString());
		 query.setString("teacherId", teacherId);
		 query.setString("queryPassword", queryPassword);
		return (TeacherInfo) query.uniqueResult();
	}

	//根据用户的身份证查询
	public TeacherInfo findByTeacId(String teacherId) throws SoqsException {
		StringBuilder hql = new StringBuilder();
		hql.append("from teacherInfo where teacherId=:teacherId");
		Query query = getSession().createQuery(hql.toString());
		query.setString("teacherId", teacherId);
		return (TeacherInfo) query.uniqueResult();
	}

	//根据用户的省份证查询半年的工资情况
	public List<SalaryInfo> getSalaryListById(SalaryInfo salaryInfo) throws SoqsException {
		List<SalaryInfo> salaryInfoList = new ArrayList<SalaryInfo>();
		
		String teacherId = salaryInfo.getTeacherId();
		
		StringBuilder sql = new StringBuilder(128);
		sql.append("select a.ID,a.TEACHER_ID,a.TEACHER_NAME,a.YEAR,a.MONTH,a.SALARY");
		sql.append(" from SALARY_INFO a");
		sql.append(" where 1=1");
		if(!StringUtils.isEmpty(teacherId)){
			sql.append(" and a.TEACHER_ID =:teacherId");
		}
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		if(!StringUtils.isEmpty(teacherId)){
			sqlQuery.setParameter("teacherId", teacherId);
		}
		List<?> list = sqlQuery.list();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			Object[] obj = null;
			SalaryInfo salary = null;
			while(itor.hasNext()){
				obj = (Object[]) itor.next();
				salary = new SalaryInfo();
				salary.setId(Integer.parseInt(obj[0].toString()));
				salary.setTeacherId(Tools.isNull(obj[1], ""));
				salary.setTeacherName(Tools.isNull(obj[2], ""));
				salary.setYear(Tools.isNull(obj[3], ""));
				salary.setMonth(Tools.isNull(obj[4], ""));
				salary.setSalary(Tools.isNull(obj[5], ""));
				salaryInfoList.add(salary);
			}
		}
		return salaryInfoList;
	}
}
