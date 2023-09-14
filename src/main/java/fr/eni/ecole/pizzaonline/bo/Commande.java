package fr.eni.ecole.pizzaonline.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Commande {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dateHeureLivraison;
	private LocalDate dateHeurePreparation;

	@OneToMany(mappedBy = "commande")
	private List<DetailCommande> lstDetail = new ArrayList<DetailCommande>();

	@Transient
	private Etat etat;

	public Commande() {
	}

	public Commande(LocalDate dateHeureLivraison, LocalDate dateHeurePreparation, Etat etat) {
		this.dateHeureLivraison = dateHeureLivraison;
		this.dateHeurePreparation = dateHeurePreparation;
		this.etat = etat;
	}

	public Commande(Long id, LocalDate dateHeureLivraison, LocalDate dateHeurePreparation, Etat etat) {
		this.id = id;
		this.dateHeureLivraison = dateHeureLivraison;
		this.dateHeurePreparation = dateHeurePreparation;
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateHeureLivraison() {
		return dateHeureLivraison;
	}

	public void setDateHeureLivraison(LocalDate dateHeureLivraison) {
		this.dateHeureLivraison = dateHeureLivraison;
	}

	public LocalDate getDateHeurePreparation() {
		return dateHeurePreparation;
	}

	public void setDateHeurePreparation(LocalDate dateHeurePreparation) {
		this.dateHeurePreparation = dateHeurePreparation;
	}

	public List<DetailCommande> getLstDetail() {
		return lstDetail;
	}

//	public void setLstDetail(List<DetailCommande> lstDetail) {
//		this.lstDetail = lstDetail;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		return Objects.equals(id, other.id);
	}
}
