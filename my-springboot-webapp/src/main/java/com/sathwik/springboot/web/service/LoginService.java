package com.sathwik.springboot.web.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String userid, String password) {
		return userid.equalsIgnoreCase("Sathwik")
				&& password.equalsIgnoreCase("dummy");
	}

}