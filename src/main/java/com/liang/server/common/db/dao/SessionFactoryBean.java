package com.liang.server.common.db.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * SessionFactory 相关Bean，不能放BaseDAO。会造成初始化时依赖出错。
 * 
 * @author liang
 */
@Configuration
public class SessionFactoryBean {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	@ConfigurationProperties("spring.datasource.druid")
	public DataSource dataSource(){
	    return DruidDataSourceBuilder.create().build();
	}
	
	/**
	 * 数据库sessionFactory
	 * @return
	 */
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean sb = new LocalSessionFactoryBean();
		sb.setDataSource(dataSource);
		sb.setPackagesToScan("com");
		try {
			sb.afterPropertiesSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.getObject();
	}
	
}
