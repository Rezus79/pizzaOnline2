package fr.eni.ecole.pizzaonline.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.pizzaonline.bo.Produit;
import fr.eni.ecole.pizzaonline.dal.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService{
	
	@Autowired
	private ProduitRepository produitRepository;

	@Override
	public List<Produit> consulterProduit() {
		
		return produitRepository.findAll();
	}

	@Override
	public Produit consulterProduitParId(long id) {
		
		return produitRepository.findById(id).get();
	}

	@Override
	public void creerProduit(Produit produit) {
		produitRepository.save(produit);
		
	}

	@Override
	public void supprimerProduit(long id) {
		produitRepository.deleteById(id);
		
	}

	@Override
	public Produit modifierProduit(Produit produit) {
		produitRepository.saveAndFlush(produit);
		return null;
	}

	
	
}
