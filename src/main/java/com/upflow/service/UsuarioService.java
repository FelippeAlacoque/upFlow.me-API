package com.upflow.service;

import java.util.List;
import java.util.Optional;

import com.upflow.documents.Usuario;

public interface UsuarioService {

	List<Usuario> listarTodos();
	
	Optional<Usuario> listarPorId (String id);
	
	Usuario cadastrar (Usuario usuario);
	
	Usuario atualizar (Usuario usuario);
	
	void remover(String id);
	
}
