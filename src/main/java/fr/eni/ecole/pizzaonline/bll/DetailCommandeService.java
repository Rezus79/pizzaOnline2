package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ecole.pizzaonline.bo.Commande;

public interface DetailCommandeService {
	
	void creerDetailCommande(Commande commande);

	List<Commande> consulterDetailCommandes();

	Optional<Commande> getDetailCommandeById(Long id);

	Commande modifierDetailCommande(Commande commande, Long id);

	void supprimerDetailCommande(Long id);
}
