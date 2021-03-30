package com.arkeup.poc.data.dto;

public class LoginDTO {
	
	private String token;

	public LoginDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginDTO() {
	
	}
	
	

}
