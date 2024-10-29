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

@Entity //ovo govori da se u relacionom pretvara u tabelu
public class Bolnica implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		@Id //ovim govorimo koje je obeležje id
		//sekvenca niz vrednosti koje se dodeljuju ovde primarnom ključu
		@SequenceGenerator(name= "BOLNICA_ID_GENERATOR", sequenceName="BOLNICA_SEQ", allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOLNICA_ID_GENERATOR")
		private long id;
		private String naziv;
		private String adresa;
		private double budzet;
		
		@JsonIgnore
		@OneToMany(mappedBy = "bolnica", cascade = CascadeType.REMOVE) //mapedBy se odnosi na vlasnika tih "odeljenja"
		private List<Odeljenje> odeljenja;
		
		public Bolnica() {
			
		}
		
		
		public List<Odeljenje> getOdeljenja() {
			return odeljenja;
		}


		public void setOdeljenja(List<Odeljenje> odeljenja) {
			this.odeljenja = odeljenja;
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
		public String getAdresa() {
			return adresa;
		}
		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}
		public double getBudzet() {
			return budzet;
		}
		public void setBudzet(double budzet) {
			this.budzet = budzet;
		} 
		
		
}

