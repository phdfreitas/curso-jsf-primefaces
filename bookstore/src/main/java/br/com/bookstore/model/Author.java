package br.com.bookstore.model;

import java.io.Serializable;

public class Author implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String email;

	public Author() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
