package com.MongoProyectoDavidDeportes.David.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoProyectoDavidDeportes.David.app.Entity.Entrenadores;



public interface EntrenadorRepository extends MongoRepository<Entrenadores, String>{
	boolean existsByNombre(String nombre);

}
