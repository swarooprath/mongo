package com.swaroopr.mongo.springdata;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String id;
	
	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
}
