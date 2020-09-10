package com.upflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upflow.documents.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
