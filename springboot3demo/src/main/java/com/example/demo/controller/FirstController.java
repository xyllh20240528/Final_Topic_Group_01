package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Light;
import com.example.demo.model.LoginDao;
import com.example.demo.model.Phone;
import com.example.demo.service.MemberService;

@RestController
public class FirstController {
	
	@Autowired
	private Phone phone;
	
	@Autowired
	private Light light;
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private LoginDao loginDao;
	
	private LoginDao loginDao;
	
	// 建構子注入法
	public FirstController(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@GetMapping("/666")
	public String index() {
		return "ok666安安";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about畫面";
	}
	
	@GetMapping("/login1")
	public boolean checkLogin() {
		return loginDao.findByUsernameAndPassword("jerry", "pwdd");
	}
	
	@GetMapping("/login2")
	public boolean checkLogin2() {
		return memberService.checkLogin("jerry", "pwdd");
	}
	
	@GetMapping("/light")
	public Light findLight() {
		return light;
	}
	
	@GetMapping("/phone")
	public Phone findPhone() {
		return phone;
	}

}
