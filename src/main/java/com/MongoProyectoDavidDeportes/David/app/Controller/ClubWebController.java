package com.MongoProyectoDavidDeportes.David.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.MongoProyectoDavidDeportes.David.app.Entity.Asociaciones;
import com.MongoProyectoDavidDeportes.David.app.Entity.Clubes;
import com.MongoProyectoDavidDeportes.David.app.Entity.Competiciones;
import com.MongoProyectoDavidDeportes.David.app.Entity.Entrenadores;
import com.MongoProyectoDavidDeportes.David.app.Entity.Jugadores;
import com.MongoProyectoDavidDeportes.David.app.Exception.NotFoundException;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.AsociacionRepository;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.ClubRepository;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.CompeticionRepository;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.EntrenadorRepository;
import com.MongoProyectoDavidDeportes.David.app.Repositorio.JugadorRepository;

@Controller
@RequestMapping(value = "clubes")
public class ClubWebController {
	@Autowired
    private ClubRepository clubRepository;
	private CompeticionRepository competicionRepository;
	private AsociacionRepository asociacionRepository;
	private JugadorRepository jugadorRepository;
	private EntrenadorRepository entrenadorRepository;
	
	
	public ClubWebController(ClubRepository clubRepository, CompeticionRepository competicionRepository,
			AsociacionRepository asociacionRepository, EntrenadorRepository entrenadorRepository, JugadorRepository jugadorRepository ) {
        this.clubRepository = clubRepository;
        this.competicionRepository = competicionRepository;
        this.asociacionRepository = asociacionRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.jugadorRepository = jugadorRepository;
    }

	  @GetMapping("")
	    public String clubesListTemplate(Model model) {
	        model.addAttribute("clubes", clubRepository.findAll());
	        return "clubes-list";
	    }

	  @GetMapping("/new")
	    public String clubesNewTemplate(Model model) {
	        List<Competiciones> competicionesList = competicionRepository.findAll();
	        List<Asociaciones> asociacionesList = asociacionRepository.findAll();
	        List<Entrenadores> entrenadoresList = entrenadorRepository.findAll();
	        List<Jugadores> jugadoresList = jugadorRepository.findAll();
	        model.addAttribute("clubes", new Clubes());
	        model.addAttribute("competicionesList", competicionesList);
	        model.addAttribute("asociacionesList", asociacionesList);
	        model.addAttribute("entrenadoresList", entrenadoresList );
	        model.addAttribute("jugadoresList", jugadoresList);
	        return "clubes-form";
	    }


	    @GetMapping("/edit/{id}")
	    public String clubesEditTemplate(@PathVariable("id") String id, Model model) {	
	    	Clubes club = clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
	    	List<Jugadores> jugadoresDisponibles = jugadorRepository.findAll();
	    	jugadoresDisponibles.removeAll(club.getJugadores());	    	    
	    	List<Competiciones> competicionesList = competicionRepository.findAll();
	        List<Asociaciones> asociacionesList = asociacionRepository.findAll();
	        List<Entrenadores> entrenadoresList = entrenadorRepository.findAll();
	        List<Jugadores> jugadoresList = jugadorRepository.findAll();
	        model.addAttribute("clubes", club);
    	    model.addAttribute("jugadoresDisponibles", jugadoresDisponibles);
	        model.addAttribute("clubes", new Clubes());
	        model.addAttribute("competicionesList", competicionesList);
	        model.addAttribute("asociacionesList", asociacionesList);
	        model.addAttribute("entrenadoresList", entrenadoresList );
	        model.addAttribute("jugadoresList", jugadoresList);
	        model.addAttribute("clubes",clubRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado")));
	       
	        return "clubes-edit";
	    }

	    @PostMapping("/save")
	    public String clubesSaveProcess(@ModelAttribute("clubes") Clubes clubes,@RequestParam("jugadores") List<Jugadores> jugadores,@RequestParam("competiciones") List<Competiciones> competiciones, Model model) {
	    	clubes.setJugadores(jugadores);
	    	clubes.setCompeticiones(competiciones);
	    	if (clubRepository.existsByNombre(clubes.getNombre())) {
	            model.addAttribute("errorMessage", "El club ya existe.");
	            return "clubes-form"; 
	        }
	    	
	    	
	    	if (clubes.getId().isEmpty()) {
	        	clubes.setId(null);
	        }
	        clubRepository.save(clubes);
	        return "redirect:/clubes";
	    }
	    

	    @PostMapping("/save/edit")
	    public String clubesSaveeDITProcess(@ModelAttribute("clubes") Clubes clubes,@RequestParam("jugadores") List<Jugadores> jugadores,@RequestParam("competiciones") List<Competiciones> competiciones, Model model) {
	    	clubes.setJugadores(jugadores);
	    	clubes.setCompeticiones(competiciones);
	    	if (clubes.getId().isEmpty()) {
	        	clubes.setId(null);
	        }
	        clubRepository.save(clubes);
	        return "redirect:/clubes";
	    }
	    
	    
	   
	    

	    @GetMapping("/delete/{id}")
	    public String clubesDeleteProcess(@PathVariable("id") String id) {
	    	clubRepository.deleteById(id);
	        return "redirect:/clubes";
	    }
}
