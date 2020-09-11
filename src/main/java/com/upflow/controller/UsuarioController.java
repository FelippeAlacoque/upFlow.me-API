package com.upflow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upflow.documents.Usuario;
import com.upflow.exception.UsuarioException;
import com.upflow.response.Response;
import com.upflow.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/usuarios")
@Api(value="API desafio técnico upFlow.me")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	@ApiOperation(value="Retorna uma lista de usuários")
	public ResponseEntity<Response<List<Usuario>>> listarTodos(){
		return ResponseEntity.ok(new Response<List<Usuario>>(this.usuarioService.listarTodos()));
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value="Retorna um usuário por ID")
	public ResponseEntity<Response<Optional<Usuario>>> listarPorId(@PathVariable(name = "id") String id){
		return ResponseEntity.ok(new Response<Optional<Usuario>>(this.usuarioService.listarPorId(id)));
	}
	
	@PostMapping
	@ApiOperation(value="Cria um usuário")
	public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody Usuario usuario, BindingResult result) throws UsuarioException{
		if(result.hasErrors()) {
			List<String> listaErros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> listaErros.add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(new Response<Usuario>(listaErros));
		}		
		return ResponseEntity.ok(new Response<Usuario>(this.usuarioService.cadastrar(usuario)));
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value="Atualiza os dados um usuário (informando o ID)")
	public ResponseEntity<Response<Usuario>> atualizar(@PathVariable(name = "id") String id,@Valid @RequestBody Usuario usuario, BindingResult result){
		if(result.hasErrors()) {
			List<String> listaErros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> listaErros.add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(new Response<Usuario>(listaErros));
		}
		usuario.setId(id);
		return ResponseEntity.ok(new Response<Usuario>(this.usuarioService.atualizar(usuario)));
	}
	
	@DeleteMapping(path="/{id}")
	@ApiOperation(value="Exclui um usuário (informando o ID)")
	public void remover(@PathVariable(name="id") String id) {
		this.usuarioService.remover(id);				
	}
}
