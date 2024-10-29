package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.repository.BolnicaRepository;

//ako imamo anotaciju spring pravi kontejner

@Service
public class BolnicaService {

	@Autowired //dependency injection u spring, pravi se anonimna klasa koja ima objekat i mi koristimo to
	private BolnicaRepository repo;
	
	public List<Bolnica> getAll() {
		return repo.findAll();
	}
	
	//uz optional niikad nećemo dobiti null pointer uvek će postojati optional objekat samo može da postoji ili ne vrednost
	public Optional <Bolnica> getById(long id) {
		return repo.findById(id); //jer ovo vraća tip podatka koji je optional zato smo gore napisali optional
	}
	
	public Optional<List<Bolnica>> getByNaziv(String naziv) {
		Optional<List<Bolnica>> bolnice = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return bolnice;
	}
	
	public Bolnica save(Bolnica bolnica) {
		return repo.save(bolnica);
	}
	
	public boolean exsistsById(long id) {
		return getById(id).isPresent(); //uz optional postižemo ovo jer getby vraca optional i on postoji ili ne
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
	
	//Obeležje double/numeric
	public Optional <List<Bolnica>> getByBudzetGreaterThanOrderById (Double budzet) {
		Optional <List<Bolnica>> lista = Optional.of(repo.findByBudzetGreaterThanOrderById(budzet));
		return lista;
	}
	
	
}
