package fr.eni.ecole.pizzaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.ecole.pizzaonline.bll.ProduitService;
import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.bo.DetailCommande;
import fr.eni.ecole.pizzaonline.bo.Produit;

@Controller
@RequestMapping("/commande")
@SessionAttributes("panier")
public class CommandeController {

	@Autowired
	public ProduitService produitService;

	@PostMapping("/menu")
	String menu(@RequestParam Long idProduit, @RequestParam Integer quantite, Model model) {
		Commande panier = (Commande) model.getAttribute("panier");
		if (panier == null) {
			panier = new Commande();
			model.addAttribute("panier", panier);
		}
		
		Produit produit = produitService.consulterProduitParId(idProduit);
		DetailCommande dc = null;

		// Recherchez si le produit existe déjà dans le panier
		for (DetailCommande dec : panier.getLstDetail()) {
		    if (dec.getProduit().equals(produit)) {
		        dc = dec; // Le produit a été trouvé, donc assignez le détail de commande existant à dc
		        break;
		    }
		}

		if (dc != null) {
		    // Le produit existe déjà dans le panier, ajoutez simplement la quantité
		    dc.setQuantite(dc.getQuantite() + quantite);
		} else {
		    // Le produit n'existe pas dans le panier, créez un nouveau DetailCommande
		    dc = new DetailCommande(produit, panier, quantite);
		    panier.getLstDetail().add(dc);
		}

		return "redirect:/carte";
	}

	@GetMapping("/panier")
	String panier(@ModelAttribute("panier") Commande panier) {

		return "home/panier";

	}

	@PostMapping("/panier")
	String panier(@ModelAttribute("panier") Commande panier, @RequestParam long idProduit) {
		for (DetailCommande dc : panier.getLstDetail()) {
			if (dc.getProduit().getId() == idProduit && dc.getQuantite() == 1) {
				panier.getLstDetail().remove(dc);
				break;
			} else if (dc.getProduit().getId() == idProduit && dc.getQuantite() > 1) {
				dc.setQuantite(dc.getQuantite() - 1);
				break;
			}

		}
		return "redirect:/commande/panier";

	}

	@GetMapping("/valider")
	String validerPanier(@ModelAttribute("panier") Commande panier, Model model) {
		float total = 0;
		for (DetailCommande dc : panier.getLstDetail()) {
			total = total + dc.getProduit().getPrix() * dc.getQuantite();
		}
		model.addAttribute("total", total);
		return "home/commande";
	}
}
