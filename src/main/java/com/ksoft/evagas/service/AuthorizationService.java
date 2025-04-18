package com.ksoft.evagas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ksoft.evagas.domain.user.Usuario;

@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("CARREGANDO USUARIO");
		Optional<Usuario> optUsuario = userService.getUserByEmail(email);
		
		if(optUsuario.isEmpty()) {
			System.out.println("NENHUM USUARIO ENCONTRADO");
			throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
		}
		Usuario usuario = optUsuario.get();
		
		System.out.println("USUARIO ENCONTRADO: " +usuario.getEmail());
		
		List<String> roles = usuario.getRoles()
				.stream().map(r-> r.getRole())
				.collect(Collectors.toList());
		
		UserDetails userDetails = 
				User.withUsername(usuario.getEmail())
				.password(usuario.getPassword())
				.roles(roles.toArray(new String[0]))
				.build();
		
		return userDetails;
	}

}
