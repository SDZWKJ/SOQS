
package com.zwkj.soqs.base;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zwkj.soqs.utils.Tools;




@Service
public class BaseService<T> {
	protected static Log LOGGER;
	protected ServiceReturns serviceReturns;
//	如果有必须要记录日志后期可以注入LogDao，方便每个子service方便的记录操作日志，流水号等等
//	暂时不需要*
//	@Resource
//	protected LogDao LogDao;
	
	@Autowired
	protected BaseDao<T> baseDao;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public BaseService(){
		LOGGER = LogFactory.getLog(this.getClass());
	}
	@Transactional
	public ServiceReturns save(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.save(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	@Transactional
	public ServiceReturns update(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.update(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	@Transactional
	public ServiceReturns delete(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.delete(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getAll(Class<T> clazz){
		serviceReturns = new ServiceReturns();
		try {
			serviceReturns.success = true;
			serviceReturns.data.put("details", baseDao.getAll(clazz));
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns get(Class<T> clazz,Integer id){
		serviceReturns = new ServiceReturns();
		try {
			serviceReturns.success = true;
			serviceReturns.data.put("details", baseDao.getById(clazz,id));
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	//根据ids删除
	@Transactional
	public int deleteByIds(Class<T> clazz,List<Integer> ids) {
		return baseDao.deleteByIds(clazz, ids);
	}
	//根据ids字符串删除
	@Transactional
	public int deleteByIdStr(Class<T> clazz,String idStr){
		int returnValue = 0;
		if(!StringUtils.isEmpty(idStr)){
			List<Integer> ids = new ArrayList<Integer>();
			String tmp = StringUtils.substring(idStr, 0,idStr.length()-1);
			StringTokenizer tokenizer = new StringTokenizer(tmp, ",");
			while(tokenizer.hasMoreTokens()){
				ids.add(Integer.parseInt(tokenizer.nextToken()));
			}
			returnValue = baseDao.deleteByIds(clazz, ids);		
		}
		return returnValue;
	}
	
	//getCurrentSession
	public Session getSession(){
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
}
