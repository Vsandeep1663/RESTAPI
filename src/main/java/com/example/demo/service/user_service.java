package com.example.demo.service;

import java.util.List;

import com.example.demo.payload.users_payload;

public interface user_service {
	// applying service to users_payload . it acts as a additional layer for more security. and we are using all crud methods as just userdefined methods and we are overriding them in user_service_imply class 
	users_payload  addusers(users_payload up);
	users_payload updateusers(users_payload up, int id);
	void deleteusers(int id);
	List<users_payload> getallusers();
	users_payload getbyid(int id);

}
