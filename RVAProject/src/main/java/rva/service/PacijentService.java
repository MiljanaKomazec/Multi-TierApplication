package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.model.Pacijent;
import rva.repository.PacijentRepository;

@Service
public class PacijentService {
	@Autowired
	private PacijentRepository repo;
	
	public List<Pacijent> getAllPacijent ()  {
		return repo.findAll();
	}
	
	public Optional <Pacijent> getById(long id) {
		return repo.findById(id); 
	}
	
	public Optional<List<Pacijent>> getByIme(String ime) {
		Optional<List<Pacijent>> odeljenja = Optional.of(repo.findByImeContainingIgnoreCase(ime));
		return odeljenja;
	}
	
	//pretraga po bolnicama
	public Optional<List<Pacijent>> getByDijagnoza(Dijagnoza dijagnoza) {
		return Optional.of(repo.findByDijagnoza(dijagnoza));
	}
	
	public Optional<List<Pacijent>> getByOdeljenje(Odeljenje odeljenje) {
		return Optional.of(repo.findByOdeljenje(odeljenje));
	}
	
	public Pacijent save (Pacijent Pacijent) {
		return repo.save(Pacijent);
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
