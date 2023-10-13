package fr.eni.ecole.pizzaonline.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.pizzaonline.bo.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	
	Client findByEmail(String email);
}
