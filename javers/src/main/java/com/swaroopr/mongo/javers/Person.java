package com.swaroopr.mongo.javers;

import org.javers.core.metamodel.annotation.Id;

public class Person {
	@Id
	private String login;
	private String name;

	public Person(String login, String name) {
		this.login = login;
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
