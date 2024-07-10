package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component  // bean 的 id 預設值為 Class 名稱小寫開頭
public class LoginDao {
	
	public boolean findByUsernameAndPassword(String username, String password) {
		
		if("jerry".equals(username) && "pwdd".equals(password)) {
			return true;
		}
		
		return false;
	}

}
