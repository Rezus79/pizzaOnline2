package fr.eni.ecole.pizzaonline.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.dal.DetailCommandeRepository;

@Service
public class DetailCommandeServiceImpl implements DetailCommandeService{

	
	@Autowired
	DetailCommandeRepository detailCommandeRepository;

	@Override
	public void creerDetailCommande(Commande commande) {
		// TODO Auto-generated method stub
		detailCommandeRepository.saveAll(commande.getLstDetail());
	}

	@Override
	public List<Commande> consulterDetailCommandes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Commande> getDetailCommandeById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Commande modifierDetailCommande(Commande commande, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerDetailCommande(Long id) {
		// TODO Auto-generated method stub
		
	}

}
