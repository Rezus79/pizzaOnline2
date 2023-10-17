package fr.eni.ecole.pizzaonline.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Etat {

	
		@Id
		private Long id;
		@Column(unique = true, nullable=false)
		private String libelle;
		
		
		public Etat() {}


		public Etat(Long id, String libelle) {
			this.id = id;
			this.libelle = libelle;
		}


		public Etat(String libelle) {
			super();
			this.libelle = libelle;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getLibelle() {
			return libelle;
		}


		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
}
