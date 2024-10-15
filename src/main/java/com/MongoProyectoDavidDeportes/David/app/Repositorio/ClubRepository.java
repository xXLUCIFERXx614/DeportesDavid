package com.MongoProyectoDavidDeportes.David.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoProyectoDavidDeportes.David.app.Entity.Clubes;


public interface ClubRepository extends MongoRepository<Clubes, String>{
	boolean existsByNombre(String nombre);

}
