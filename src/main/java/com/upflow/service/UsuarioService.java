package com.upflow.service;

import java.util.List;
import java.util.Optional;

import com.upflow.documents.Usuario;
import com.upflow.exception.UsuarioException;

public interface UsuarioService {

	List<Usuario> listarTodos();
	
	Optional<Usuario> listarPorId (String id);
	
	Usuario cadastrar (Usuario usuario) throws UsuarioException;
	
	Usuario atualizar (Usuario usuario);
	
	void remover(String id);
	
}
