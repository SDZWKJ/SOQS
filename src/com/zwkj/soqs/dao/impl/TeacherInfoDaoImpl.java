package com.zwkj.soqs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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

}
