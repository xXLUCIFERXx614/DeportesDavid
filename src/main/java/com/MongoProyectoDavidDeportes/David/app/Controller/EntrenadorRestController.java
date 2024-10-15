package com.MongoProyectoDavidDeportes.David.app.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MongoProyectoDavidDeportes.David.app.Entity.Entrenadores;
import com.MongoProyectoDavidDeportes.David.app.Exception.NotFoundException;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.EntrenadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/entrenadores")
public class EntrenadorRestController {
	@Autowired
    private EntrenadorRepository entrenadorRepository;
	
	 @GetMapping("/")
	    public List<Entrenadores> getAllEntrenadores() {
	        return entrenadorRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Entrenadores getEntrenadoresById(@PathVariable String id) {
	        return entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
	    }

	    @PostMapping("/")
	    public Entrenadores saveEntrenadores(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Entrenadores entrenadores = mapper.convertValue(body, Entrenadores.class);
	        return entrenadorRepository.save(entrenadores);
	    }

	    @PutMapping("/{id}")
	    public Entrenadores updateEntrenadores(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Entrenadores entrenadores = mapper.convertValue(body, Entrenadores.class);
	        entrenadores.setId(id);
	        return entrenadorRepository.save(entrenadores);
	    }

	    @DeleteMapping("/{id}")
	    public Entrenadores deleteEntrenadores(@PathVariable String id) {
	    	Entrenadores entrenadores = entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
	    	entrenadorRepository.deleteById(id);
	        return entrenadores;
	    }
}
