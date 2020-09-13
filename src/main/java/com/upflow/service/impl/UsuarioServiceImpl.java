package com.upflow.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.upflow.documents.Usuario;
import com.upflow.exception.UsuarioException;
import com.upflow.repository.UsuarioRepository;
import com.upflow.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario buscarUsuarioPorLogin(String login) throws UsuarioException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario == null)
			throw new UsuarioException("Usuário não encontrado.");
		else
			return usuario;
	}

	@Override
	public List<Usuario> listarTodos() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> listarPorId(String id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public Usuario cadastrar(Usuario usuario) throws UsuarioException {
		validarUsuarioSemelhante(usuario);		
		validarSenha(usuario.getSenha());
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
	
	@Override
	public String gerarSenha(String s) {
		Random random = new Random();
		int numero = random.nextInt(100);
		return s+numero;
	}
	
	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		Usuario user = this.usuarioRepository.findByEmail(email);
		if(user == null)
			throw new ApplicationContextException("Usuario não encontrado.");
		
		return user;		
	}
	
	private void validarUsuarioSemelhante(Usuario usuario) throws UsuarioException {
		List<Usuario> listaUsuarios = this.usuarioRepository.findAll();
		for (Usuario user : listaUsuarios) {
			if(user.getEmail().equalsIgnoreCase(usuario.getEmail()) || user.getLogin().equalsIgnoreCase(usuario.getLogin()))
				throw new UsuarioException("Já existe um usuário cadastrado com o login e/ou e-mail informados.");
		}					
	}
	
	private void validarSenha(String senha) throws UsuarioException {
		if(senha.length() <6)
			throw new UsuarioException("A senha informada deve possuir no mínimo 6 dígitos");
	}

}
