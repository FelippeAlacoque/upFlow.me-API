package com.upflow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upflow.config.email.Mensagem;
import com.upflow.documents.Usuario;
import com.upflow.exception.UsuarioException;
import com.upflow.response.Response;
import com.upflow.service.SendMailService;
import com.upflow.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/sendMail")
@Api(value="API desafio técnico upFlow.me")
public class SendMailController {

	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@ApiOperation(value="Envia e-mail")
	public ResponseEntity<Response<Mensagem>> cadastrar(@Valid @RequestBody Mensagem mensagem, BindingResult result) throws UsuarioException{
		if(result.hasErrors()) {
			List<String> listaErros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> listaErros.add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(new Response<Mensagem>(listaErros));
		}
		//verificar se o e-mail cadastrado existe
		Usuario usuario = usuarioService.buscarUsuarioPorEmail(mensagem.getEmail());
		//gera nova senha para enviar por e-mail
		usuario.setSenha(usuarioService.gerarSenha(usuario.getId()));
		
		usuarioService.atualizar(usuario);
		
		sendMailService.sendMail(mensagem, usuario.getSenha());
		
		return ResponseEntity.noContent().build();			
	}	

}
