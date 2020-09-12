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

import com.mongodb.util.JSON;
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
	public ResponseEntity<Response<Usuario>> logar(@Valid @RequestBody LoginDTO login, BindingResult result) throws UsuarioException{
		if(result.hasErrors()) {
			List<String> listaErros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> listaErros.add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(new Response<Usuario>(listaErros));
		}		
		//return ResponseEntity.ok(new Response<Usuario>(this.usuarioService.cadastrar(usuario)));
		return null;
	}

}
