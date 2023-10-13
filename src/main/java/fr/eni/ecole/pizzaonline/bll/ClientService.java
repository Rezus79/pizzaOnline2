package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ecole.pizzaonline.bo.Client;


public interface ClientService {

	
	Client CreerClient(Client client);
	
	List<Client> consulterClients();
	
	Optional<Client> getClientById(Long id);
	
	Client findByEmail(String email);
	
	Client modifierClient(Client client, Long id);
	
	void supprimerClient(Long id);
}
