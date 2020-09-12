package com.upflow.DTO;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {
		
	String login;
	String senha;
	
	@NotEmpty(message = "Login é obrigatório")
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotEmpty(message = "Senha é obrigatório")
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
