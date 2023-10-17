package fr.eni.ecole.pizzaonline.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commande {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateHeureLivraison;
	private LocalDateTime dateHeurePreparation;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DetailCommande> lstDetail = new ArrayList<DetailCommande>();

	@ManyToOne
	private Etat etat;

	public Commande() {
	}
	public Commande(Etat etat) {
		this.etat = etat;
	}

	public Commande(LocalDateTime dateHeureLivraison, LocalDateTime dateHeurePreparation, Etat etat) {
		this.dateHeureLivraison = dateHeureLivraison;
		this.dateHeurePreparation = dateHeurePreparation;
		this.etat = etat;
	}

	public Commande(Long id, LocalDateTime dateHeureLivraison, LocalDateTime dateHeurePreparation, Etat etat) {
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

	public LocalDateTime getDateHeureLivraison() {
		return dateHeureLivraison;
	}

	public void setDateHeureLivraison(LocalDateTime localDateTime) {
		this.dateHeureLivraison = localDateTime;
	}

	public LocalDateTime getDateHeurePreparation() {
		return dateHeurePreparation;
	}

	public void setDateHeurePreparation(LocalDateTime dateHeurePreparation) {
		this.dateHeurePreparation = dateHeurePreparation;
	}

	public List<DetailCommande> getLstDetail() {
		return lstDetail;
	}

	
//	public void setLstDetail(List<DetailCommande> lstDetail) {
//		this.lstDetail = lstDetail;
//	}

	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
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
