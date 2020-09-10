package com.upflow.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upflow.documents.Usuario;
import com.upflow.repository.UsuarioRepository;
import com.upflow.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listarTodos() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> listarPorId(String id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Usuario atualizar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public void remover(String id) {		
		this.usuarioRepository.deleteById(id);
	}

}
