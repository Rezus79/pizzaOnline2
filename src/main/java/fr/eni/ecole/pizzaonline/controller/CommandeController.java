package fr.eni.ecole.pizzaonline.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.ecole.pizzaonline.bll.ClientService;
import fr.eni.ecole.pizzaonline.bll.CommandeService;
import fr.eni.ecole.pizzaonline.bll.CommandeServiceImpl;
import fr.eni.ecole.pizzaonline.bll.DetailCommandeService;
import fr.eni.ecole.pizzaonline.bll.ProduitService;
import fr.eni.ecole.pizzaonline.bo.Commande;
import fr.eni.ecole.pizzaonline.bo.DetailCommande;
import fr.eni.ecole.pizzaonline.bo.Etat;
import fr.eni.ecole.pizzaonline.bo.Produit;

@Controller
@RequestMapping("/commande")
@SessionAttributes({"panier","produit"})
public class CommandeController {

	
	
	@Autowired
	public ProduitService produitService;
	
	@Autowired
	public CommandeService commandeService;
	
	@Autowired
	public DetailCommandeService dcs;
	
	@Autowired
	public ClientService cs;

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
	
	@PostMapping("/client/commande")
	String payerPanier(@ModelAttribute("panier") Commande panier, Model model) {
		float total = 0;
		for (DetailCommande dc : panier.getLstDetail()) {
			total = total + dc.getProduit().getPrix() * dc.getQuantite();
		}
		model.addAttribute("total", total);
		
		String clientId = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
		    clientId = authentication.getName();
		}
		
		
		panier.setClient(cs.findByEmail(clientId));
		panier.setDateHeureLivraison(panier.getDateHeureLivraison());
		LocalDateTime dateHeurePreparation = panier.getDateHeureLivraison().minusHours(1);
		panier.setDateHeurePreparation(dateHeurePreparation);
		panier.setEtat(new Etat(1L,"en preparation"));
		commandeService.ajouterUneCommande(panier);
		dcs.creerDetailCommande(panier);
		return "home/commande_valider";
		
	}
	

	@GetMapping("/private/liste_commandes")
	String listeCommande(Model model) {
		List<Commande> lstCommandes = new ArrayList<Commande>();
		lstCommandes.addAll(commandeService.consulterCommandes());
		model.addAttribute("commandes", lstCommandes);
	
		return "home/liste_commandes";
		
	}
	
	@PostMapping("/private/liste_commandes")
	String detailCommande(@RequestParam("id")long id, Model model) {
		model.addAttribute("detailCommande", dcs.getDetailCommandeById(id));
		List<Produit> produits = new ArrayList<Produit>();
		for (DetailCommande dc : dcs.getDetailCommandeById(id)) {
			produits.add(dc.getProduit());
		}
		model.addAttribute("produits", produits);
		return "home/detail_commande";
	}
	
}
