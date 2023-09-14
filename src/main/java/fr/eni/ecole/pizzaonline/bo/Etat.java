package fr.eni.ecole.pizzaonline.bo;

import jakarta.persistence.Entity;

//@Entity
public class Etat {

	
	//@Id
		private Integer id;
		private String libelle;
		
		
		public Etat() {}


		public Etat(Integer id, String libelle) {
			this.id = id;
			this.libelle = libelle;
		}


		public Etat(String libelle) {
			super();
			this.libelle = libelle;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getLibelle() {
			return libelle;
		}


		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
}
