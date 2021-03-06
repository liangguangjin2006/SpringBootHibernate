package com.liang.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 程序入口
 */
@EnableRedisHttpSession
@SpringBootApplication
public class App {
	
    public static void main( String[] args ){
    		SpringApplication.run(App.class, args);
    }
    
}
