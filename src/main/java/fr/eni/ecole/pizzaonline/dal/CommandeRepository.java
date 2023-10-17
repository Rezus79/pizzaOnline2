package fr.eni.ecole.pizzaonline.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.bo.Etat;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

	Optional<List<Commande>> findByEtatIs(Etat etat);
}
