package rva.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Dijagnoza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name= "DIJAGNOZA_ID_GENERATOR", sequenceName="DIJAGNOZA_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DIJAGNOZA_ID_GENERATOR")
	private long id;
	private String naziv;
	private String opis;
	private String oznaka;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dijagnoza", cascade = CascadeType.REMOVE) 
	private List<Pacijent> pacijent;
	
	public Dijagnoza() {
		
	}
	
	
	public List<Pacijent> getPacijent() {
		return pacijent;
	}


	public void setPacijent(List<Pacijent> pacijent) {
		this.pacijent = pacijent;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	
	

}
