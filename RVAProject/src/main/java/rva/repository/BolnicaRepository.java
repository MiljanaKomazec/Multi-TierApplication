package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Bolnica;

//jpa je ključan u komuniciranju sa bazom podataka, on izvršava dodavanje izmenu i to
//jpa traži entity i tip podatka koji je njihov id
public interface BolnicaRepository extends JpaRepository<Bolnica, Long> {

	public abstract List<Bolnica> findByNazivContainingIgnoreCase(String naziv);
	
	List<Bolnica> findByBudzetGreaterThanOrderById (Double budzet); 
	
}
