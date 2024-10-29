package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long> {

	public abstract List<Dijagnoza> findByNazivContainingIgnoreCase(String naziv);
	
}
