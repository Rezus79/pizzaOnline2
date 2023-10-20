package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.bo.DetailCommande;

public interface DetailCommandeService {
	
	void creerDetailCommande(Commande commande);

	List<Commande> consulterDetailCommandes();

	List<DetailCommande> getDetailCommandeById(Long id);

	Commande modifierDetailCommande(Commande commande, Long id);

	void supprimerDetailCommande(Long id);
}
