package com.vega2k.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id @GeneratedValue
	private Long id; 
	
	@Column(unique=true)
	private String username;
	@Column
	private String password;

	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account account = (Account) obj;
		
		return Objects.equals(id, account.id) &&
				Objects.equals(username, account.username) &&
				Objects.equals(password, account.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,username,password);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
