package fr.eni.ecole.pizzaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.ecole.pizzaonline.bll.UtilisateurService;
import fr.eni.ecole.pizzaonline.bo.Utilisateur;

@Controller
public class UserController {

	
	@Autowired
	UtilisateurService utilisateurService;
	
	@GetMapping("/login")
	public String gerer(Model model) {
		return "home/login";
	}
	

	
	@GetMapping("/creer")
	public String creerUtilisateur(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "home/creer";
	}
	
	@PostMapping("/creer")
	public String creerUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		utilisateurService.CreerUtilisateur(utilisateur);
		return "redirect:/login";
	}
}
