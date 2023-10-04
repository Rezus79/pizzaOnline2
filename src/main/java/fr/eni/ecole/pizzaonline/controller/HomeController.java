package fr.eni.ecole.pizzaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ecole.pizzaonline.bll.ProduitService;
import fr.eni.ecole.pizzaonline.bo.Produit;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	public ProduitService produitService;

	
	@GetMapping
	String index() {
		return "home/index";
	}
	
	@GetMapping("/carte")
	String carte(Model model) {
		model.addAttribute("produits", produitService.consulterProduit());
		return "home/carte";
		
	}
	
	
	@GetMapping("/private/gere")
	String gere(Model model) {
		model.addAttribute("produits", produitService.consulterProduit());
		return "home/gere";
	}
	
	@GetMapping("/ajouter")
	String ajout(Model model) {
		model.addAttribute("produit", new Produit());
		return "home/ajouter";
	}
	
	@PostMapping("/ajouter")
	public String ajouterProduit(@ModelAttribute Produit produit) {
		produitService.creerProduit(produit);
		return "redirect:/gere";
	}
	
	@GetMapping("/suppression")
	public String suppressionProduit(@RequestParam("id")long id){
		produitService.supprimerProduit(id);
		return "redirect:/gere";
	}
	
	@GetMapping("/details")
	public String afficherUnProduit(@RequestParam("id")long id, Model model ) {
		model.addAttribute("produit", produitService.consulterProduitParId(id));
		return "home/details";
	}
	
	@PostMapping("/details")
	public String modifierProduit(@ModelAttribute Produit produit) {
		produitService.modifierProduit(produit);
		
		return "redirect:/gere";
	}
}
