package fr.eni.ecole.pizzaonline.bo;

import java.io.Serializable;
import java.util.Objects;

public class DetailCommandeId implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Produit produit;
	private Commande commande;
	
	
	public DetailCommandeId() {
	}

	

	public DetailCommandeId(Produit produit, Commande commande) {
		this.produit = produit;
		this.commande = commande;
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


	@Override
	public int hashCode() {
		return Objects.hash(commande, produit);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailCommandeId other = (DetailCommandeId) obj;
		return Objects.equals(commande, other.commande) && Objects.equals(produit, other.produit);
	}
}
