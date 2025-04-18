package com.ksoft.evagas.config.security;

import javax.management.relation.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ksoft.evagas.domain.user.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.formLogin(lp-> 
					lp
					.loginPage("/login")
					.loginProcessingUrl("/entrar")
					.usernameParameter("email")
					.passwordParameter("senha")
					.defaultSuccessUrl("/vagas", true)
					.failureUrl("/login?error=true"))
					.logout(lg-> lg.logoutSuccessUrl("/login")
					.permitAll()
				)
				.authorizeHttpRequests(auth-> 
					auth
					.requestMatchers("/login", "/login/**").permitAll()
					.requestMatchers("/assets", "/assets/**").permitAll()
					.requestMatchers("/vagas", "/vagas/**").permitAll()
					.anyRequest()
					.authenticated()
				)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}

}
