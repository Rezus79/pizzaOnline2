package fr.eni.ecole.pizzaonline.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.pizzaonline.bo.Etat;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long>{

}
