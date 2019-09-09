package com.liang.server.common.db.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.liang.server.common.util.Log;

/**
 * 基类DAO
 * @author liang
 * @param <T>
 */
@SuppressWarnings("unchecked")
@EnableTransactionManagement
public abstract class BaseDAO<T> {

	@Autowired
	private SessionFactory sessionFactory;
	private String className;

	protected String allItems = null;

	public BaseDAO() {
		// TODO Auto-generated constructor stub
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		className = entityClass.getName();
		allItems = "FROM " + className + " ";
		
	}

	/**
	 * 获取数据库session
	 * @return
	 */
	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception e) {
			Log.getInstance(this.getClass()).error("", e);
		}
		return session;
	}
	
	/***
	 * 数据库的事务
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		HibernateTransactionManager trans = new HibernateTransactionManager();
		trans.setSessionFactory(sessionFactory);
		return trans;
	}

	public boolean save(T obj) {
		return getSession().save(obj) != null;
	}

	public boolean update(T obj) {
		getSession().update(obj);
		return true;
	}

	public boolean delete(T obj) {
		getSession().delete(obj);
		return true;
	}

	public T findById(Object key) {
		T instance = null;
		if (key instanceof String) {
			instance = (T) getSession().get(className, (String) key);
		} else if (key instanceof Integer) {
			instance = (T) getSession().get(className, (Integer) key);
		} else if (key instanceof Long) {
			instance = (T) getSession().get(className, (Long) key);
		}

//		if (instance != null) {// 清除缓存状态，在业务层修改本对像时，不会修改数据库
//			getSession().evict(instance);
//		}

		return instance;
	}

	public T deleteById(Object keyValue) {
		T instance = this.findById(keyValue);
		getSession().delete(instance);
		return instance;
	}
	
	/**
	 * 创建query
	 * 
	 * @param hql
	 * @return
	 */
	public Query createQuery(StringBuffer hql) {
		return createQuery(hql.toString());
	}

	/**
	 * 创建query
	 * 
	 * @param hql
	 * @return
	 */
	public Query createQuery(String hql) {
		Query query = createQuery(getSession() , hql);
		query.setReadOnly(true);
		return query;
	}

	/**
	 * 指定session创建query
	 * @param session
	 * @param hql
	 * @return
	 */
	public Query createQuery(Session session, String hql){
		if(session == null){
			return null;
		}
		return session.createQuery(hql);
	}
	
	/**
	 * 获取唯一值
	 * @param query
	 * @return
	 */
	public T getUniqueResult(Query query){
		List<?> list = query.list();
		return list.isEmpty()?null:(T)list.get(0);
	}
	
	/**
	 * 获取数量
	 * @param query
	 * @return
	 */
	public long getCount(Query query){
		return ((Long)query.uniqueResult()).longValue();
	}
	
}
