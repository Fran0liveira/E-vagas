package com.ksoft.evagas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ksoft.evagas.config.ApiConfig;
import com.ksoft.evagas.domain.user.RegisterUserDto;
import com.ksoft.evagas.domain.user.Usuario;

@Service
public class UserService {
	
	@Autowired
	private ApiConfig apiConfig;
	
	public boolean cadastrarUsuario(RegisterUserDto usuario) {
		ResponseEntity<Void> response = RestClient.create(apiConfig.getBaseUrl())
		.post()
		.uri(uri-> uri
				.path(apiConfig.getSalvarUsuario())
				.build())
		.body(usuario)
		.retrieve()
		.toBodilessEntity();
		
		System.out.println("STATUS: " +response.getStatusCode());
		
		return true;
		
	}
	
	public Optional<Usuario> getUserByEmail(String email)
	{
		Usuario usuario = RestClient
		.create(apiConfig.getBaseUrl())
		.get()
		.uri(uri-> 
				uri
				.path(apiConfig.getFiltrarUsuario())
				.queryParam("email", email)
				.build()
		)
		.retrieve()
		.body(Usuario.class);
		
		return Optional.ofNullable(usuario);
	}
	
	

}
