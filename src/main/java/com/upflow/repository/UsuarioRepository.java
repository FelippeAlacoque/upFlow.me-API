package com.upflow.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upflow.documents.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Optional<Usuario> findByNome(String nome);
	Optional<Usuario> findByLogin (String login);
	Usuario findByEmail (String email);
}
