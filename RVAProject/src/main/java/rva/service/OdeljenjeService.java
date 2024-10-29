package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.model.Odeljenje;
import rva.repository.OdeljenjeRepository;

@Service
public class OdeljenjeService {

	@Autowired
	private OdeljenjeRepository repo;
	
	public List<Odeljenje> getAllOdeljenje ()  {
		return repo.findAll();
	}
	
	public Optional <Odeljenje> getById(long id) {
		return repo.findById(id); 
	}
	
	public Optional<List<Odeljenje>> getByNaziv(String naziv) {
		Optional<List<Odeljenje>> odeljenja = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return odeljenja;
	}
	
	//pretraga po bolnicama
	public Optional<List<Odeljenje>> getByBolnica(Bolnica bolnica) {
		return Optional.of(repo.findByBolnica(bolnica));
	}
	
	public Odeljenje save (Odeljenje odeljenje) {
		return repo.save(odeljenje);
	}
	
	public boolean exsistsById(long id) {
		if(getById(id).isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
