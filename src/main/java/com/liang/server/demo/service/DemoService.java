package com.liang.server.demo.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.liang.server.common.db.dao.UserDAO;
import com.liang.server.common.db.entity.TUser;
import com.liang.server.demo.service.entity.page.DemoPageEntity;

@Service
@Transactional
public class DemoService {
	
	@Autowired
	private UserDAO userDAO;
	
	public String index(Model model) {
		DemoPageEntity entity = new DemoPageEntity();
		entity.setName("hello!");
		entity.setCurrentTime(System.currentTimeMillis());
		model.addAttribute("params", entity);
		model.addAttribute("name", "world");
		
		TUser user = new TUser();
		user.setName("名称_"+System.currentTimeMillis());
		user.setCreateTime(new Date());
		System.out.println("保存："+userDAO.save(user));
		
		return "/demo.html";
	}
	
}