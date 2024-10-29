package rva.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import rva.model.Dijagnoza;
import rva.service.DijagnozaService;

@CrossOrigin
@RestController
public class DijagnozaController {
	@Autowired 
	private DijagnozaService service;
	
	/*
	@GetMapping("/hello")
	public ResponseEntity<?> sayHello(){
		return new ResponseEntity<> ("Hello!", HttpStatus.OK);
	}
	*/
	
	@GetMapping("/dijagnoza")
	public ResponseEntity<List<Dijagnoza>> getAllDijagnoza() {
		List<Dijagnoza> dijagnoze = service.getAll();
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
	}
	
	@GetMapping("/dijagnoza/{id}")
	public ResponseEntity<?> getDijagnozaById(@PathVariable long id) {
		if(service.exsistsById(id)) {
			return ResponseEntity.ok(service.getById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dijagnoza with requested id is not found");
		}
	}
	

	@GetMapping("/dijagnoza/naziv/{naziv}")
	public ResponseEntity<?> getDijagnozaByNaziv(@PathVariable String naziv) {
		if(!service.getByNaziv(naziv).get().isEmpty()) { 
			return ResponseEntity.ok(service.getByNaziv(naziv));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dijagnoza with requested naziv " + naziv + " do not exist");
		}
	}
	
	@PostMapping("/dijagnoza")
	public ResponseEntity<?> createDijagnoza(@RequestBody Dijagnoza dijagnoza) {
		
		Dijagnoza savedDijagnoza;
		if(!service.exsistsById(dijagnoza.getId())) {
			savedDijagnoza = service.save(dijagnoza);
		} else {
			List<Dijagnoza> lista = service.getAll();
			long najvecaVrednost = 1;
			for(int i = 0; i<lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			
			dijagnoza.setId(najvecaVrednost);
			savedDijagnoza = service.save(dijagnoza);
		}
		
		URI uri = URI.create("/dijagnoza/" + savedDijagnoza.getId());
		return ResponseEntity.created(uri).body(savedDijagnoza);
	
	}
	
	@PutMapping("/dijagnoza/{id}")
	public ResponseEntity<?> updateDijagnoza(@RequestBody Dijagnoza dijagnoza, @PathVariable long id) {
		if(service.exsistsById(id)) {
			dijagnoza.setId(id);
			Dijagnoza updatedDijagnoza = service.save(dijagnoza);
			return ResponseEntity.ok(updatedDijagnoza);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
		
	}
	
	@DeleteMapping("/dijagnoza/{id}")
	public ResponseEntity<?> deleteDijagnoza(@PathVariable long id) {
		if(service.exsistsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
	}

}
