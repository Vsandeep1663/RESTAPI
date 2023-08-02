package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionhandling.ApiResponse;
import com.example.demo.payload.users_payload;
import com.example.demo.service.user_service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class user_controller {
	@Autowired
	user_service us;
	
	@PostMapping("/insert")
	public ResponseEntity<users_payload>addusers(@Valid @RequestBody users_payload up){
		users_payload user = this.us.addusers(up);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<users_payload> updateusers(@Valid @PathVariable int id , @RequestBody users_payload up){
		users_payload user_pay = this.us.updateusers(up,id);
		return ResponseEntity.ok(user_pay);
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse>deleteusers(@Valid @PathVariable int id){
		this.us.deleteusers(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("id deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("getbyid/{id}")
	public ResponseEntity<users_payload>getbyid(@Valid @PathVariable int id){
		return ResponseEntity.ok(this.us.getbyid(id));
	}
	@GetMapping("getall")
	public ResponseEntity<List<users_payload>> getall(){
		return ResponseEntity.ok(this.us.getallusers());
	}

}
