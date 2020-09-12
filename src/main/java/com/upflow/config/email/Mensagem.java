package com.upflow.config.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Mensagem {
	
	public Mensagem() {
		super();
	}

	public Mensagem(@NotNull String name, @NotNull @Email String email, @NotNull String feedback) {
		super();
		this.name = name;
		this.email = email;
		this.feedback = feedback;
	}

	@NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String feedback;

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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
    
    
}