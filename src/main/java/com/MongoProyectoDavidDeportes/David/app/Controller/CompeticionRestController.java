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

import com.MongoProyectoDavidDeportes.David.app.Entity.Competiciones;
import com.MongoProyectoDavidDeportes.David.app.Exception.NotFoundException;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.CompeticionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/competiciones")
public class CompeticionRestController {
	@Autowired
    private CompeticionRepository competicionRepository;
	
	 @GetMapping("/")
	    public List<Competiciones> getAllCompeticiones() {
	        return competicionRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Competiciones getCompeticionesById(@PathVariable String id) {
	        return competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competicion no encontrada"));
	    }

	    @PostMapping("/")
	    public Competiciones saveCompeticiones(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Competiciones competiciones = mapper.convertValue(body,Competiciones.class);
	        return competicionRepository.save(competiciones);
	    }

	    @PutMapping("/{id}")
	    public Competiciones updateCompeticioness(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Competiciones competiciones = mapper.convertValue(body, Competiciones.class);
	        competiciones.setId(id);
	        return competicionRepository.save(competiciones);
	    }

	    @DeleteMapping("/{id}")
	    public Competiciones deleteCompeticiones(@PathVariable String id) {
	    	Competiciones competiciones = competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competicion no encontrada"));
	    	competicionRepository.deleteById(id);
	        return competiciones;
	    }
}
