package fr.eni.ecole.pizzaonline.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(DetailCommandeId.class)
public class DetailCommande {

	
	@Id
	@ManyToOne
	private Produit produit;
	@Id
	@ManyToOne
	private Commande commande;

	private Integer quantite;

	public DetailCommande() {
	}

	public DetailCommande(Produit produit, Commande commande, Integer quantite) {
		this.produit = produit;
		this.commande = commande;
		this.quantite = quantite;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
}
