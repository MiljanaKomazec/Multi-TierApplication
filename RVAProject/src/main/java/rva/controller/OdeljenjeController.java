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

import rva.model.Bolnica;
import rva.model.Odeljenje;
import rva.service.BolnicaService;
import rva.service.OdeljenjeService;

@CrossOrigin
@RestController
@RequestMapping("odeljenje") //defaultni resurs za sve mapping anotacije/metode
public class OdeljenjeController {
	
	@Autowired
	private OdeljenjeService service;
	
	@Autowired
	private BolnicaService serviceb;
	
	@GetMapping
	public ResponseEntity<List<Odeljenje>> getAllOdeljenje() {
		return ResponseEntity.ok(service.getAllOdeljenje());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable long id) {
		if(service.exsistsById(id)) {
			return ResponseEntity.ok(service.getById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odeljenje with requested id is not found");
		}
	}
	
	@GetMapping("/naziv/{naziv}")
	public ResponseEntity<?> getOdeljenjeByNaziv(@PathVariable String naziv) {
		if(!service.getByNaziv(naziv).get().isEmpty()) { 
			return ResponseEntity.ok(service.getByNaziv(naziv));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odeljenje with requested naziv " + naziv + " do not exist");
		}
	}
	
	@GetMapping("/bolnica/{id}")
	public ResponseEntity<?> getOdeljenjeByBolnica(@PathVariable long id) {
		Optional<Bolnica> bolnica = serviceb.getById(id);
		if(bolnica.isPresent()) {
			return ResponseEntity.ok(service.getByBolnica(bolnica.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odeljenje with requested bolnica " + bolnica + " do not exist");
		}
	}
	
	@PostMapping
	public ResponseEntity<Odeljenje> createOdeljenje(@RequestBody Odeljenje odeljenje)
	{
		
		Odeljenje savedOdeljenje;
		if(!service.exsistsById(odeljenje.getId())) {
			savedOdeljenje = service.save(odeljenje);
		} else {
			List<Odeljenje> lista = service.getAllOdeljenje	();
			long najvecaVrednost = 1;
			for(int i = 0; i<lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			
			odeljenje.setId(najvecaVrednost);
			savedOdeljenje = service.save(odeljenje);
		}
		
		URI uri = URI.create("/odeljenje/" + savedOdeljenje.getId());
		return ResponseEntity.created(uri).body(savedOdeljenje);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOdeljenje(@RequestBody Odeljenje odeljenje, @PathVariable long id) {
		if(service.exsistsById(id)) {
			odeljenje.setId(id);
			Odeljenje updatedOdeljenje = service.save(odeljenje);
			return ResponseEntity.ok(updatedOdeljenje);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOdeljenje(@PathVariable long id) {
		if(service.exsistsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
	}
	
}
