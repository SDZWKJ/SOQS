package com.zwkj.soqs.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.zwkj.soqs.base.SoqsException;
import com.zwkj.soqs.base.impl.BaseDaoImpl;
import com.zwkj.soqs.dao.TeacherInfoDao;
import com.zwkj.soqs.po.TeacherInfo;

@Repository
public class TeacherInfoDaoImpl extends BaseDaoImpl<TeacherInfo> implements
		TeacherInfoDao {

	@SuppressWarnings("unchecked")
	//获取所有用户的信息
	public List<TeacherInfo> getAllUserInfo() throws SoqsException {
		StringBuilder hql = new StringBuilder();
		//hql.append("from teacherInfo");
		//hql.append(" where 1=1");
		hql.append("select new com.zwkj.soqs.po.TeacherInfo(id,empId,teacherId,teacherName,creator,createdDate,updater,lastUpdatedDate)");
		hql.append(" from teacherInfo");
		hql.append(" where 1=1");
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}

	//根据职工编号或者身份证号码查询用户
	public TeacherInfo findTeacInfoByEmpIdOrId(TeacherInfo teacherInfo)
			throws SoqsException {
		TeacherInfo info = null;
		String empId = teacherInfo.getEmpId();
		String teacherId = teacherInfo.getTeacherId();
		
		StringBuilder hql = new StringBuilder();
		hql.append("from teacherInfo");
		hql.append(" where 1=1");
		if(!StringUtils.isEmpty(empId)){
			hql.append(" and empId=:empId");
		}
		if(!StringUtils.isEmpty(teacherId)){
			hql.append(" or teacherId=:teacherId");
		}
		Query query = getSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(empId)){
			query.setString("empId", empId);
		}
		if(!StringUtils.isEmpty(teacherId)){
			query.setString("teacherId", teacherId);
		}
		
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if(!CollectionUtils.isEmpty(list)){
			info = (TeacherInfo) list.get(0);
		}
		return info;
	}

	//添加用户
	public void insertUser(TeacherInfo teacherInfo) throws SoqsException {
		save(teacherInfo);
	}

}
