package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
	public abstract List<Pacijent> findByImeContainingIgnoreCase(String ime);
	
	List<Pacijent> findByDijagnoza(Dijagnoza dijagnoza);
	
	List<Pacijent> findByOdeljenje(Odeljenje odeljenje);
}
