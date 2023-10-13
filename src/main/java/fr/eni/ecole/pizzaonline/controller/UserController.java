package fr.eni.ecole.pizzaonline.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ecole.pizzaonline.bll.ClientService;
import fr.eni.ecole.pizzaonline.bll.UtilisateurService;
import fr.eni.ecole.pizzaonline.bo.Client;
import fr.eni.ecole.pizzaonline.bo.Utilisateur;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/login")
	public String gerer(Model model) {
		
		return "home/login";
	}
	

	
	@GetMapping("/creer")
	public String creerUtilisateur(Model model) {
		model.addAttribute("client", new Client());
		return "home/creer";
	}
	
	@PostMapping("/creer")
	public String creerUtilisateur(@ModelAttribute Client client) {
		clientService.CreerClient(client);
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
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		List<Utilisateur> adminUsers = new ArrayList<Utilisateur>();
		users.addAll(utilisateurService.consulterUtilisateurs());
		
		//ajouter a la liste que les Admin et les Gerant les CLIENT ne sont pas gerer sur cette page
		for(Utilisateur user : users) {
			if(user.getRole().getLibelle().equals("ADMIN")  || user.getRole().getLibelle().equals("GERANT") ) {
				adminUsers.add(user);
			}
		}
		
		model.addAttribute("utilisateurs", adminUsers);
		return "home/gerer_utilisateurs";
	}
	
	@PostMapping("/private/liste_manager")
	public String listeDesUtilisateur(@RequestParam("id")long id) {
		utilisateurService.supprimerUtilisateur(id);
		return "redirect:/private/liste_manager";
			
		}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "nonConnecter");
        cookie.setPath("/"); 
        response.addCookie(cookie);

        // Efface le contexte de sécurité
        SecurityContextHolder.clearContext();

		return "redirect:/";
	}
	
}
