package fr.eni.ecole.pizzaonline.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.pizzaonline.bo.Produit;

@Service
public interface ProduitService {

	
List<Produit> consulterProduit();
	
	Produit consulterProduitParId(long id);
	
	void creerProduit(Produit produit);
	
	void supprimerProduit(long id);
	
	Produit modifierProduit(Produit produit);
	
}
