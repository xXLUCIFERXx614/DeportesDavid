package com.MongoProyectoDavidDeportes.David.app.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoProyectoDavidDeportes.David.app.Entity.Competiciones;

public interface CompeticionRepository extends MongoRepository<Competiciones, String>{
	
	boolean existsByNombre(String nombre);
	 List<Competiciones> findByNombre(String nombre);

}
