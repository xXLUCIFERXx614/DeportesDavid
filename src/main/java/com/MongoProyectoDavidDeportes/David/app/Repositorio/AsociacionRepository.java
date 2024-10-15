package com.MongoProyectoDavidDeportes.David.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoProyectoDavidDeportes.David.app.Entity.Asociaciones;


public interface AsociacionRepository extends MongoRepository<Asociaciones, String> {
	boolean existsByNombre(String nombre);

}
