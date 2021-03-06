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
	//根据条件查询工资信息
	public List<SalaryInfo> getSalaryInfo(TeacherInfo teacherInfo) throws SoqsException {
		List<SalaryInfo> salaryInfoList = new ArrayList<SalaryInfo>();
		
		String empId = teacherInfo.getEmpId();             //教职工编号
		String selYear = teacherInfo.getSelYear();         //年度
		String selMonth = teacherInfo.getSelMonth();       //月份
		String teacherName = teacherInfo.getTeacherName(); //姓名
		
		StringBuilder sql = new StringBuilder(128);
		sql.append("select a.ID,a.EMP_ID,a.TEACHER_NAME,a.YF_SALARY,a.SF_SALARY,a.JC_SALARY");
		sql.append(",a.GW_SALARY,a.XJ_SALARY,a.GL_SALARY,a.TG_SALARY,a.JT_SALARY,a.QTJB_SALARY");
		sql.append(",a.SYDWJTBTHJ_ALLOWANCE,a.ZWBT_ALLOWANCE,a.TGJT_ALLOWANCE,a.JXJT_ALLOWANCE");
		sql.append(",a.JHLJT_ALLOWANCE,a.BZR_ALLOWANCE,a.GGXBT_WYBT_ALLOWANCE,a.QTBZ_ALLOWANCE");
		sql.append(",a.KP_AWARD,a.QT_SALARY,a.DSZN_ALLOWANCE,a.BFGZYF_SALARY,a.ZF_ALLOWANCE");
		sql.append(",a.KF_TOTAL,a.KGJJ_MONEY,a.KYALBX_MONEY,a.KYILBX_MONEY,a.DBBZJ_MONEY,a.IIT_MONEY");
		sql.append(",a.KIIT_MONEY,a.YEAR_SALARY,a.MONTH_SALARY,a.DATE_SALARY,a.SFJS_TAX");
		sql.append(" from SALARY_INFO a");
		sql.append(" where 1=1");
		if(!StringUtils.isEmpty(empId)){
			sql.append(" and a.EMP_ID=:empId");
		}
		if(!StringUtils.isEmpty(selYear)){
			sql.append(" and a.YEAR_SALARY=:selYear");
		}
		if(!StringUtils.isEmpty(selMonth)){
			sql.append(" and a.MONTH_SALARY=:selMonth");
		}
		if(!StringUtils.isEmpty(teacherName)){
			sql.append(" and a.TEACHER_NAME like :teacherName");
		}
		sql.append(" order by a.YEAR_SALARY desc,a.MONTH_SALARY desc");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		if(!StringUtils.isEmpty(empId)){
			sqlQuery.setString("empId", empId);
		}
		if(!StringUtils.isEmpty(selYear)){
			sqlQuery.setString("selYear", selYear);
		}
		if(!StringUtils.isEmpty(selMonth)){
			sqlQuery.setString("selMonth", selMonth);
		}
		if(!StringUtils.isEmpty(teacherName)){
			sqlQuery.setString("teacherName", "%"+teacherName+"%");
		}
		List<?> list = sqlQuery.list();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			Object[] obj = null;
			SalaryInfo salaryInfo = null;
			while(itor.hasNext()){
				obj = (Object[]) itor.next();
				salaryInfo = new SalaryInfo();
				salaryInfo.setId(Integer.parseInt(obj[0].toString()));
				salaryInfo.setEmpId(Tools.isNull(obj[1], ""));
				salaryInfo.setTeacherName(Tools.isNull(obj[2], ""));
				salaryInfo.setYfSalary(Tools.isNull(obj[3], ""));
				salaryInfo.setSfSalary(Tools.isNull(obj[4], ""));
				salaryInfo.setJcSalary(Tools.isNull(obj[5], ""));
				salaryInfo.setGwSalary(Tools.isNull(obj[6], ""));
				salaryInfo.setXjSalary(Tools.isNull(obj[7], ""));
				salaryInfo.setGlSalary(Tools.isNull(obj[8], ""));
				salaryInfo.setTgSalary(Tools.isNull(obj[9], ""));
				salaryInfo.setJtSalary(Tools.isNull(obj[10], ""));
				salaryInfo.setQtjbSalary(Tools.isNull(obj[11], ""));
				salaryInfo.setSydwjtbthjAllowance(Tools.isNull(obj[12], ""));
				salaryInfo.setZwbtAllowance(Tools.isNull(obj[13], ""));
				salaryInfo.setTgjtAllowance(Tools.isNull(obj[14], ""));
				salaryInfo.setJxjtAllowance(Tools.isNull(obj[15], ""));
				salaryInfo.setJhljtAllowance(Tools.isNull(obj[16], ""));
				salaryInfo.setBzrAllowance(Tools.isNull(obj[17], ""));
				salaryInfo.setGgxbtWybtAllowance(Tools.isNull(obj[18], ""));
				salaryInfo.setQtbzAllowance(Tools.isNull(obj[19], ""));
				salaryInfo.setKpAward(Tools.isNull(obj[20], ""));
				salaryInfo.setQtSalary(Tools.isNull(obj[21], ""));
				salaryInfo.setDsznAllowance(Tools.isNull(obj[22], ""));
				salaryInfo.setBfgzyfSalary(Tools.isNull(obj[23], ""));
				salaryInfo.setZfAllowance(Tools.isNull(obj[24], ""));
				salaryInfo.setKfTotal(Tools.isNull(obj[25], ""));
				salaryInfo.setKgjjMoney(Tools.isNull(obj[26], ""));
				salaryInfo.setKyalbxMoney(Tools.isNull(obj[27], ""));
				salaryInfo.setKyilbxMoney(Tools.isNull(obj[28], ""));
				salaryInfo.setDbbzjMoney(Tools.isNull(obj[29], ""));
				salaryInfo.setIitMoney(Tools.isNull(obj[30], ""));
				salaryInfo.setKiitMoney(Tools.isNull(obj[31], ""));
				salaryInfo.setYearSalary(Tools.isNull(obj[32], ""));
				salaryInfo.setMonthSalary(Tools.isNull(obj[33], ""));
				salaryInfo.setDateSalary(null);
				salaryInfo.setSfjsTax(Tools.isNull(obj[35], ""));
				salaryInfoList.add(salaryInfo);
			}
		}
		return salaryInfoList;
	}

	//查询工资时验证身份
	public TeacherInfo verifyId(TeacherInfo teacherInfo) throws SoqsException {
		String teacherId = teacherInfo.getTeacherId();         //身份证 
		String queryPassword = teacherInfo.getQueryPassword(); //查询密码
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

	//删除整月记录
	public int delMonthRecord(TeacherInfo teacherInfo) throws SoqsException {
		String selYear = teacherInfo.getSelYear();
		String selMonth = teacherInfo.getSelMonth();
		
		StringBuilder hql = new StringBuilder(128);
		hql.append("delete from salaryInfo");
		hql.append(" where 1=1");
		
		if(!StringUtils.isEmpty(selYear)){
			hql.append(" and yearSalary=:selYear");
		}
		if(!StringUtils.isEmpty(selMonth)){
			hql.append(" and monthSalary=:selMonth");
		}
		
		Query query = getSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(selYear)){
			query.setString("selYear", selYear);
		}
		if(!StringUtils.isEmpty(selMonth)){
			query.setString("selMonth", selMonth);
		}
		int delNum = query.executeUpdate();
		
		return delNum;
	}

	//根据用户的省份证查询半年的工资情况
	/*
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
	*/
}
