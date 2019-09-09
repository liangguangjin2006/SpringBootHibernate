package com.liang.server.common.util;

import org.apache.log4j.Logger;

/**
 * 日志。读取classpath:config/log4j.properties配置
 * @author liang
 */
public class Log {

	private Logger logger = null;
	private Class<?> cls = null;
	
	private Log(Class<?> cls){
		this.cls = cls;
		logger = Logger.getLogger(cls);
	}
	
	/**
	 * 获取业务层实例
	 * @return
	 */
	public synchronized static Log getInstance(Class<?> cls){
		return new Log(cls);
	}
	
	private Logger getLogger(){
		return logger;
	}
	
	public void debug(String str){
		getLogger().debug(str);
	}
	public void debug(String str,Throwable throwable){
		getLogger().debug("["+cls.getName()+"] : "+str, throwable);
	}
	
	public void info(String str){
		getLogger().info("["+cls.getName()+"] : "+str);
	}
	public void info(String str,Throwable throwable){
		getLogger().info("["+cls.getName()+"] : "+str, throwable);
	}
	
	public void warn(String str){
		getLogger().warn("["+cls.getName()+"] : "+str);
	}
	public void warn(String str,Throwable throwable){
		getLogger().warn("["+cls.getName()+"] : "+str, throwable);
	}
	
	public void error(String str){
		getLogger().error("["+cls.getName()+"] : "+str);
	}
	public void error(String str,Throwable throwable){
		getLogger().error("["+cls.getName()+"] : "+str, throwable);
	}
	
	public void fatal(String str){
		getLogger().fatal("["+cls.getName()+"] : "+str);
	}
	public void fatal(String str,Throwable throwable){
		getLogger().fatal("["+cls.getName()+"] : "+str, throwable);
	}

}
