package fr.eni.ecole.pizzaonline.bll;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.dal.CommandeRepository;
import fr.eni.ecole.pizzaonline.dal.EtatRepository;

@Service
public class CommandeServiceImpl implements CommandeService{

	
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	EtatRepository etatRepository;

	
	@Override
	public void ajouterUneCommande(Commande commande) {
		commande.setDateHeureLivraison(LocalDateTime.now().plusHours(2));
		commande.setDateHeurePreparation(LocalDateTime.now().plusHours(1));
		commandeRepository.save(commande);
	}
	
	@Override
	public List<Commande> consulterCommandesAPreparer() {
		return commandeRepository.findByEtatIs(etatRepository.findById(2l).get()).get(); 
	}
	
	@Override
	public void passerCommandeEnPreparerParID(long id) {
		Commande commandePreparer = commandeRepository.findById(id).get();
		commandePreparer.setEtat(etatRepository.findById(3l).get());
		commandeRepository.save(commandePreparer);
	}
	
	
	
	
	

	@Override
	public List<Commande> consulterCommandes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Commande> getCommandeById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Commande modifierCommande(Commande commande, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
