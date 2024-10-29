package rva.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.model.Pacijent;
import rva.service.DijagnozaService;
import rva.service.OdeljenjeService;
import rva.service.PacijentService;

@CrossOrigin
@RestController
@RequestMapping("pacijent")
public class PacijentController {
	@Autowired
	private PacijentService service;
	
	@Autowired
	private DijagnozaService serviced;
	
	@Autowired
	private OdeljenjeService serviceo;
	
	@GetMapping
	public ResponseEntity<List<Pacijent>> getAllPacijent() {
		return ResponseEntity.ok(service.getAllPacijent());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPacijentById(@PathVariable long id) {
		if(service.exsistsById(id)) {
			return ResponseEntity.ok(service.getById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent with requested id is not found");
		}
	}
	
	@GetMapping("/ime/{ime}")
	public ResponseEntity<?> getPacijentByIme(@PathVariable String ime) {
		if(!service.getByIme(ime).get().isEmpty()) { 
			return ResponseEntity.ok(service.getByIme(ime));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent with requested ime " + ime + " do not exist");
		}
	}
	
	@GetMapping("/dijagnoza/{id}")
	public ResponseEntity<?> getPacijentByDijagnoza(@PathVariable long id) {
		Optional<Dijagnoza> dijagnoza = serviced.getById(id);
		if(dijagnoza.isPresent()) {
			return ResponseEntity.ok(service.getByDijagnoza(dijagnoza.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent with requested dijagnoza " + dijagnoza + " do not exist");
		}
	}
	
	@GetMapping("/odeljenje/{id}")
	public ResponseEntity<?> getPacijentByOdeljenje(@PathVariable long id) {
		Optional<Odeljenje> odeljenje = serviceo.getById(id);
		if(odeljenje.isPresent()) {
			return ResponseEntity.ok(service.getByOdeljenje(odeljenje.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent with requested odeljenje " + odeljenje + " do not exist");
		}
	}
	
	@PostMapping
	public ResponseEntity<Pacijent> createPacijent(@RequestBody Pacijent Pacijent)
	{
		
		Pacijent savedPacijent;
		if(!service.exsistsById(Pacijent.getId())) {
			savedPacijent = service.save(Pacijent);
		} else {
			List<Pacijent> lista = service.getAllPacijent	();
			long najvecaVrednost = 1;
			for(int i = 0; i<lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			
			Pacijent.setId(najvecaVrednost);
			savedPacijent = service.save(Pacijent);
		}
		
		URI uri = URI.create("/pacijent/" + savedPacijent.getId());
		return ResponseEntity.created(uri).body(savedPacijent);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePacijent(@RequestBody Pacijent pacijent, @PathVariable long id) {
		if(service.exsistsById(id)) {
			pacijent.setId(id);
			Pacijent updatedPacijent = service.save(pacijent);
			return ResponseEntity.ok(updatedPacijent);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePacijent(@PathVariable long id) {
		if(service.exsistsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
	}
	
}
