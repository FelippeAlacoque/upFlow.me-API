package com.upflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.upflow.config.email.MailConfig;
import com.upflow.config.email.Mensagem;
import com.upflow.service.SendMailService;
import com.upflow.service.UsuarioService;

@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public void sendMail(Mensagem mensagem, String novaSenha) {	
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailConfig.getHost());
		mailSender.setPort(mailConfig.getPort());
		mailSender.setUsername(mailConfig.getUsername());
		mailSender.setPassword(mailConfig.getPassword());
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("felippealacoque@gmail.com");
		mailMessage.setTo(mensagem.getEmail());
		mailMessage.setSubject("Recuperação de senha upFlow.me");
		mailMessage.setText("Esta é uma mensagem automática. Você solicitou a recuperação da senha de acesso do usuário / e-mail"
				+ " " + mensagem.getEmail()+ "\n "+ "--> Sua nova senha é: " + novaSenha);
		
		mailSender.send(mailMessage);				
	}

}
