package fr.eni.ecole.pizzaonline.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import fr.eni.ecole.pizzaonline.bll.ClientServiceImpl;
import fr.eni.ecole.pizzaonline.bll.UtilisateurServiceImpl;
import fr.eni.ecole.pizzaonline.bo.Client;
import fr.eni.ecole.pizzaonline.bo.Utilisateur;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
	
	@Autowired
	UtilisateurServiceImpl utilisateurServiceImpl;
	
	@Autowired
	ClientServiceImpl clientServiceImpl;

	@Bean
	public PasswordEncoder encoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
	


	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(requests ->
	            requests
	            	.requestMatchers("/private/**")
	                .hasAnyRole("ADMIN", "GERANT")
	                .requestMatchers("*/client/**")
	                .hasAnyRole("CLIENT", "GERANT", "ADMIN")
	                .anyRequest()
	                .permitAll()
	        )
	        .formLogin(form ->
	            form
	                .loginPage("/login")
	                .permitAll()
	        )
	        .logout(logout ->
	        logout
	            .logoutUrl("/logout") // L'URL de déconnexion
	            .logoutSuccessUrl("/login?logout") // Redirection après la déconnexion
	            .deleteCookies("JSESSIONID")
	            .invalidateHttpSession(true) // Invalider la session
	            .clearAuthentication(true) // Effacer l'authentification de l'utilisateur
	            .permitAll()
	        );

	    return http.build();
	}
	

	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
	    List<Utilisateur> utilisateurs = utilisateurServiceImpl.consulterUtilisateurs();
	    List<Client> clients = clientServiceImpl.consulterClients();
	    InMemoryUserDetailsManager mem = new InMemoryUserDetailsManager();

	    for (Utilisateur utilisateur : utilisateurs) {
	        // Assurez-vous d'utiliser l'encodeur configuré pour hacher le mot de passe
	        String motDePasseEncode = utilisateur.getMotDePasse();

	        UserDetails userDetails = User.withUsername(utilisateur.getEmail())
	                .password(motDePasseEncode) // Utilisez le mot de passe haché
	                .roles(utilisateur.getRole().getLibelle())
	                .build();
	        
	        mem.createUser(userDetails);
	        
	    }
	    
	    for (Client client : clients) {
	        // Assurez-vous d'utiliser l'encodeur configuré pour hacher le mot de passe
	        String motDePasseEncode = client.getMotDePasse();

	        UserDetails userDetails = User.withUsername(client.getEmail())
	                .password(motDePasseEncode) // Utilisez le mot de passe haché
	                .roles(client.getRole().getLibelle())
	                .build();
	        
	        mem.createUser(userDetails);
	        
	    }

	    return mem;
	}


	
}
