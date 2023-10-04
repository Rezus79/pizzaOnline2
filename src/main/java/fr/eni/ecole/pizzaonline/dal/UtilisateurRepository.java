package fr.eni.ecole.pizzaonline.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.pizzaonline.bo.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

	
	Utilisateur findByEmail(String email);
}
