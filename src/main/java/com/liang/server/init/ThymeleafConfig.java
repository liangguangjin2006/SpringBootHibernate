package com.liang.server.init;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

/**
 * @author liang
 * @description Thymeleaf动态配置，主要配置模板文件路径在:/public
 */
@Configuration
public class ThymeleafConfig{
	
	@Autowired
	private SpringResourceTemplateResolver resolver;
	
	@PostConstruct
    public void extension() {
		File file = new File("");
		StringBuffer path = new StringBuffer("file:");
		path.append(file.getAbsolutePath().replace("target/classes/", ""));//删除eclipse调试时的错误路径
		path.append("/public/");
		resolver.setPrefix(path.toString());
    }
	
}
