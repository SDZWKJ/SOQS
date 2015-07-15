
package com.zwkj.soqs.base;

import java.util.List;


public interface BaseDao<T> {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */

	public void delete(Class<T> clazz,Integer id);
	/**
	 * 根据ids删除
	 * @param clazz
	 * @param ids
	 * @return
	 */
	public int deleteByIds(Class<T> clazz,List<Integer> ids);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */

	public T getById(Class<T> clazz,Integer id);

	/**
	 * 按ids查询
	 * 
	 * @param ids
	 * @return
	 */

	public List<T> getByIds(Class<T> clazz,Integer[] ids);

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	public List<T> getAll(Class<T> clazz);
	
	/**
	 * 删除实体
	 * 
	 * @param id
	 */

	public void delete(T t) throws Exception;
	
	/**
	 * 根据模板去查找
	 * 
	 * @param id
	 */

	public List<T> findByExample(T t) throws Exception;
	
	/**
	 * 根据模板去查找一条
	 * 
	 * @param id
	 */
	
	public T findOneByExample(T t) throws Exception;
	

}
