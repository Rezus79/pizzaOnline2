package fr.eni.ecole.pizzaonline.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ecole.pizzaonline.bo.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{

	

}
