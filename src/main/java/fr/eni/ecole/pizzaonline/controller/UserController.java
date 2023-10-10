package fr.eni.ecole.pizzaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/private/creer_manager")
	public String creerUtilisateurManage(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "home/creer_manager";
	}
	
	@PostMapping("/private/creer_manager")
	public String creerUtilisateurManage(@ModelAttribute Utilisateur utilisateur) {
		utilisateurService.CreerUtilisateur(utilisateur);
		return "redirect:/private/gere";
	}
	
	@GetMapping("/private/liste_manager")
	public String listeDesUtiliasateur(Model model) {
		model.addAttribute("utilisateurs", utilisateurService.consulterUtilisateurs());
		return "home/gerer_utilisateurs";
	}
	
	@PostMapping("/private/liste_manager")
	public String listeDesUtilisateur(@RequestParam("id")long id) {
		utilisateurService.supprimerUtilisateur(id);
		return "redirect:/private/liste_manager";
			
		}
	
	
}
