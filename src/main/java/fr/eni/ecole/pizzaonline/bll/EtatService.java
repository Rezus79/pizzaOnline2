package fr.eni.ecole.pizzaonline.bll;

import fr.eni.ecole.pizzaonline.bo.Etat;

public interface EtatService {

	
	Etat creerEtat(Etat etat);
	
	Etat modifierEtat(Etat etat, Long id);

	void supprimerEtat(Long id);
}
