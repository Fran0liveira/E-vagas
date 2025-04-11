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
				.formLogin(lp-> 
						lp
						.loginPage("/login.html")
						.defaultSuccessUrl("/")
						.failureUrl("/login-error.html"))
						.logout(lg-> lg.logoutSuccessUrl("/index.html")
					)
				
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth-> 
					auth
					.requestMatchers("/assets/**").permitAll()
					.requestMatchers("/", "/login/**").permitAll()
					.requestMatchers("/vagas/**").permitAll()
					.requestMatchers("/vagas/form", "/vagas/publicar").hasRole(UserRole.ADMIN.getRole())
					.anyRequest()
					.authenticated()
				)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
