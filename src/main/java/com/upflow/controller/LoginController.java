package com.upflow.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upflow.DTO.LoginDTO;
import com.upflow.documents.Usuario;
import com.upflow.exception.UsuarioException;
import com.upflow.response.Response;
import com.upflow.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/usuarios/login")
@Api(value="API desafio técnico upFlow.me")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@ApiOperation(value="Login de usuário")
	public ResponseEntity<Response<Usuario>> logar(@RequestBody LoginDTO login, BindingResult result) throws UsuarioException{
		List<String> listaErros = new ArrayList<String>();
		if(result.hasErrors()) {			
			result.getAllErrors().forEach(erro -> listaErros.add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(new Response<Usuario>(listaErros));
		}else {
			//validar se usuário está na base e login e senha informados conferem com os dados
			return ResponseEntity.ok(new Response<Usuario>(this.usuarioService.buscarUsuarioPorLogin(login.getLogin())));
		}		
	}

}
