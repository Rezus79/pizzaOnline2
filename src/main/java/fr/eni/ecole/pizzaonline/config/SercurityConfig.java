package fr.eni.ecole.pizzaonline.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import fr.eni.ecole.pizzaonline.bll.UtilisateurServiceImpl;
import fr.eni.ecole.pizzaonline.bo.Utilisateur;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
	
	@Autowired
	UtilisateurServiceImpl utilisateurServiceImpl;
	
	

	@Bean
	public PasswordEncoder encoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/private/**").authenticated().anyRequest().permitAll()

		).formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//
//		List<Utilisateur> utilisateurs = utilisateurServiceImpl.consulterUtilisateurs();
//		InMemoryUserDetailsManager mem = new InMemoryUserDetailsManager();
//
//		for (Utilisateur user : utilisateurs) {
//			UserDetails userDetails = User.withUsername(user.getEmail())
//					.password(encoder().encode(user.getMotDePasse())).build();
//			mem.createUser(userDetails);
//			
//		}
//		return mem;
//	}
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
	    List<Utilisateur> utilisateurs = utilisateurServiceImpl.consulterUtilisateurs();
	    InMemoryUserDetailsManager mem = new InMemoryUserDetailsManager();

	    for (Utilisateur utilisateur : utilisateurs) {
	        // Assurez-vous d'utiliser l'encodeur configuré pour hacher le mot de passe
	        String motDePasseEncode = utilisateur.getMotDePasse();

	        UserDetails userDetails = User.withUsername(utilisateur.getEmail())
	                .password(motDePasseEncode) // Utilisez le mot de passe haché
	                
	                .build();
	        
	        mem.createUser(userDetails);
	        
	    }

	    return mem;
	}
	
}
