package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.bo.Produit;
import jakarta.persistence.EntityManager;

public interface CommandeService {

	
	void ajouterUneCommande(Commande commande);

	List<Commande> consulterCommandes();
	
	List<Commande> consulterCommandesAPreparer();

	Optional<Commande> getCommandeById(Long id);

	Commande modifierCommande(Commande commande, Long id);
	
	void passerCommandeEnPreparerParID(long id);

	
}
