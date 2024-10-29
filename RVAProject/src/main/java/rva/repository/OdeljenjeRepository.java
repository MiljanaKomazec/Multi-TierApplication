package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Bolnica;
import rva.model.Odeljenje;

public interface OdeljenjeRepository extends JpaRepository<Odeljenje, Long> {
	
	public abstract List<Odeljenje> findByNazivContainingIgnoreCase(String naziv);
	
	List<Odeljenje> findByBolnica(Bolnica bolnica);

}
