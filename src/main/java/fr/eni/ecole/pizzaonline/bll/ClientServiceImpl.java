package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.ecole.pizzaonline.bo.Client;
import fr.eni.ecole.pizzaonline.dal.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;
	
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}


	@Override
	public Client CreerClient(Client client) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		client.setMotDePasse(encoder.encode(client.getMotDePasse()));
		return clientRepository.save(client);
	}


	@Override
	public List<Client> consulterClients() {
		return clientRepository.findAll();
	}


	@Override
	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}


	@Override
	public Client findByEmail(String email) {
		return clientRepository.findByEmail(email);
	}


	@Override
	public Client modifierClient(Client client, Long id) {
		Client clientDB = clientRepository.findById(id).get();
		
		if (Objects.nonNull(client.getNom()) && !"".equalsIgnoreCase(client.getNom())) {
			clientDB.setNom(client.getNom());
		}
		if (Objects.nonNull(client.getPrenom()) && !"".equalsIgnoreCase(client.getPrenom())) {
			clientDB.setPrenom(client.getPrenom());
		}
		if (Objects.nonNull(client.getAdresse()) && !"".equalsIgnoreCase(client.getAdresse())) {
			clientDB.setAdresse(client.getAdresse());
		}
		if (Objects.nonNull(client.getRole())) {
			clientDB.setRole(client.getRole());
		}
		if (Objects.nonNull(client.getEmail()) && !"".equalsIgnoreCase(client.getEmail())) {
			clientDB.setEmail(client.getEmail());
		}
		return clientRepository.save(clientDB);
	}


	@Override
	public void supprimerClient(Long id) {
		clientRepository.deleteById(id);
		
	}
	
	
}
