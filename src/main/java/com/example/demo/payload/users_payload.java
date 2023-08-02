package com.example.demo.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class users_payload {
	// it is a duplicate layer to model layer.
	private int id;
	@Column(name="Username",nullable = false) // nullable indicates that it doesnot accept null values
	private String name;
	@Email
	private String email;
	@Column(length = 10)
	private String password;

}
