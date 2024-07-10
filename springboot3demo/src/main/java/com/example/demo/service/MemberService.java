package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LoginDao;

@Service
public class MemberService {
	
	@Autowired
	private LoginDao loginDao;
	
	public boolean checkLogin(String username, String password) {
		return loginDao.findByUsernameAndPassword(username, password);
	}

}
