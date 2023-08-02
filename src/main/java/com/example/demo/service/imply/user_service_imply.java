package com.example.demo.service.imply;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptionhandling.ResourceNotFoundException;
import com.example.demo.models.users;
import com.example.demo.payload.users_payload;
import com.example.demo.repository.user_repository;
import com.example.demo.service.user_service;
@Service

public class user_service_imply implements user_service {
	@Autowired
	user_repository user_repo;
	@Autowired
	ModelMapper modelmapper;
	@Override
	public users_payload addusers(users_payload up) {
		// TODO Auto-generated method stub
		users user = this.dto_users(up);
		users savesusers = this.user_repo.save(user);		
		return this.users_dto(savesusers);
	}

	@Override
	public users_payload updateusers(users_payload up, int id) {
		users user = this.user_repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users","id",id));
		user.setName(up.getName());
		user.setEmail(up.getEmail());
		user.setPassword(up.getPassword());
		
		users user1 = this.user_repo.save(user);
		users_payload usp = this.users_dto(user1);	
		return usp;
	}

	@Override
	public void deleteusers(int id) {
		users u=this.user_repo.findById(id).orElseThrow(()->new ResourceNotFoundException("userd", "id", id));
		this.user_repo.delete(u);

	}

	@Override
	public List<users_payload> getallusers() {
		List<users> u=(List<users>) this.user_repo.findAll();
		List<users_payload> up=u.stream().map(ups->this.users_dto(ups)).collect(Collectors.toList());
		return up;
	}

	@Override
	public users_payload getbyid(int id) {
		
		
		users user = this.user_repo.findById(id).orElseThrow(()->new ResourceNotFoundException("userd", "id", id));
		return this.users_dto(user);
	}
	
	// the data flow of below 2 methods are for insertion : user-payload layer-database
	// for retrieval database- user-payload- user
	// the reason we are using is we are giving security to the data i.e we are getting the data from payload layer . so if any cyber attack is done they will get the data with all values as empty or null . If they try to modify the data it won't effect the data in database.
	public users dto_users(users_payload usp) { // data from duplicate layer to original layer
		users user =this.modelmapper.map(usp, users.class);
		return user;
		
	}
	
	
	public users_payload users_dto(users usp) { // data from original layer to duplicate layer
		users_payload up =this.modelmapper.map(usp, users_payload.class);
		return up;
		
	}
	

}
