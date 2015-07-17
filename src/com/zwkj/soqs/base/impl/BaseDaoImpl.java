package com.zwkj.soqs.base.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.zwkj.soqs.base.BaseDao;


@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

//	private Class<T> clazz;
	protected Log logger;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		// log日志的支持 
		logger = LogFactory.getLog(this.getClass());
//		// 获取当前new的对象的泛型父类 类型
//		ParameterizedType pt = (ParameterizedType) this.getClass()
//				.getGenericSuperclass();
//		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
		//this.logger.info("保存"+this.clazz.getSimpleName());
	}

	public void delete(Class<T> clazz,Integer id) {
		T entity = hibernateTemplate.load(clazz, id);
		if(entity!=null){
			hibernateTemplate.delete(entity);
		}
//		logger.info("删除"+this.clazz.getSimpleName());
	}

	public void update(T entity) {
		hibernateTemplate.update(entity);
//		logger.info("更新"+this.clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	public T getById(Class<T> clazz,Integer id) {
//		logger.info("根据id查询 "+this.clazz.getSimpleName());
		return hibernateTemplate.load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Class<T> clazz,Integer[] ids) {
//		logger.info("根据ids查询 "+this.clazz.getSimpleName());
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> clazz) {
//		logger.info("查询所有  "+this.clazz.getSimpleName());
		return hibernateTemplate.find("FROM "+clazz.getSimpleName());
	}

	public void delete(T t) throws Exception{
		hibernateTemplate.delete(t);
	}

	
	public List<T> findByExample(T t) throws Exception {
		return hibernateTemplate.findByExample(t);
	}

	public T findOneByExample(T t) throws Exception {
		List<T> list = findByExample(t);
		if(list==null ||list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public HibernateTemplate getHiberanteTemplate() {
		return hibernateTemplate;
	}

	public void setHiberanteTemplate(HibernateTemplate hiberanteTemplate) {
		this.hibernateTemplate = hiberanteTemplate;
	}

	@Override
	public int deleteByIds(Class<T> clazz, List<Integer> ids) {
		String className = clazz.getSimpleName();
		//做一个类名首字母小写处理，因为在PO中我们指定了Entity的name都是首字母小写的格式
		String fClassName = className.substring(0, 1);
		String lClasname = className.substring(1);
		String clazzName = fClassName.toLowerCase()+lClasname;
		//logger.info("className  "+className);
		//logger.info("fClassName  "+fClassName);
		//logger.info("lClasname  "+lClasname);
		//logger.info("clazzName  "+clazzName);
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(//
				"DELETE FROM "+clazzName+" WHERE id IN (:ids)"//
				);
		query.setParameterList("ids", ids);
		return query.executeUpdate();
	}
}
