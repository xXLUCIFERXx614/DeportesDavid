package com.MongoProyectoDavidDeportes.David.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoProyectoDavidDeportes.David.app.Entity.Jugadores;


public interface JugadorRepository  extends MongoRepository<Jugadores, String>{
	boolean existsByNombre(String nombre);
	 Jugadores findByNombre(String nombre);
	void deleteByNombre(String nombre);

}
