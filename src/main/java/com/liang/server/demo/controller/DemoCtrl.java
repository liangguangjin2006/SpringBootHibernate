package com.liang.server.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liang.server.demo.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoCtrl {
	
	@Autowired
	private DemoService demoService;

	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest req) {
		req.getSession().setAttribute("lastTime", System.currentTimeMillis());
		return demoService.index(model);
	}
	
}
