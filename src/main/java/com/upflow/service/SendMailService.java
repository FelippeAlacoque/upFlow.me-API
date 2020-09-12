package com.upflow.service;

import com.upflow.config.email.Mensagem;

public interface SendMailService {

	void sendMail(Mensagem Mensagem, String novaSenha);

}
