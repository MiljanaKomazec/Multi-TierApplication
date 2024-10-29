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

import rva.model.Bolnica;
import rva.service.BolnicaService;

@CrossOrigin
@RestController  //osluskuje http zahteve ka odredjenjim endpointima
public class BolnicaController {
	
	@Autowired //jer smo u servisu radili dependency injection mora i ovde
	private BolnicaService service;
	
	@GetMapping("/hello")
	public ResponseEntity<?> sayHello(){
		return new ResponseEntity<> ("Hello!", HttpStatus.OK);
	}
	
	@GetMapping("/bolnica")
	public ResponseEntity<List<Bolnica>> getAll() {
		List<Bolnica> bolnice = service.getAll();
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
	}
	
	@GetMapping("/bolnica/{id}")
	public ResponseEntity<?> getBolnicaById(@PathVariable long id) {
		if(service.exsistsById(id)) {
			return ResponseEntity.ok(service.getById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolnica with requested id is not found");
		}
	}
	
	//upitnik u responsenetity znači da možemo da vratimo bilo kog tipa
	@GetMapping("/bolnica/naziv/{naziv}")
	public ResponseEntity<?> getBolnicaByNaziv(@PathVariable String naziv) {
		if(!service.getByNaziv(naziv).get().isEmpty()) { //ovo get je od optional time vidimo sta je u njemu a isempty je funcija od liste jer je lista u optional i proveravamo dal je prazna
			return ResponseEntity.ok(service.getByNaziv(naziv));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolnica with requested naziv " + naziv + " do not exist");
		}
	}
	
	
	@GetMapping("/bolnica/budzet/{budzet}")
	public ResponseEntity<?> getByBudzetGreaterThanOrderById (@PathVariable Double budzet) {
		List<Bolnica> lista = service.getByBudzetGreaterThanOrderById(budzet).get();
		if(!lista.isEmpty()) { 
			return ResponseEntity.ok(lista);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolnica with requested " + budzet + " do not exist");
		}
	}
	
	@PostMapping("/bolnica")
	public ResponseEntity<?> createBolnica(@RequestBody Bolnica bolnica) {
		
		Bolnica savedBolnica;
		if(!service.exsistsById(bolnica.getId())) {
			savedBolnica = service.save(bolnica);
		} else {
			List<Bolnica> lista = service.getAll();
			long najvecaVrednost = 1;
			for(int i = 0; i<lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			
			bolnica.setId(najvecaVrednost);
			savedBolnica = service.save(bolnica);
		}
		
		URI uri = URI.create("/bolnica/" + savedBolnica.getId());
		return ResponseEntity.created(uri).body(savedBolnica);
	
	}
	
	@PutMapping("/bolnica/{id}")
	public ResponseEntity<?> updateBolnica(@RequestBody Bolnica bolnica, @PathVariable long id) {
		if(service.exsistsById(id)) {
			bolnica.setId(id);
			Bolnica updatedBolnica = service.save(bolnica);
			return ResponseEntity.ok(updatedBolnica);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
		
	}
	
	@DeleteMapping("/bolnica/{id}")
	public ResponseEntity<?> deleteBolnica(@PathVariable long id) {
		if(service.exsistsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with requested id " + id + " has not been found");
		}
	}
	
}
