package com.ksoft.evagas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ksoft.evagas.config.ApiConfig;
import com.ksoft.evagas.domain.user.Usuario;

@Service
public class UserService {
	
	@Autowired
	private ApiConfig apiConfig;
	
	public Optional<Usuario> getUserByEmail(String email)
	{
		Usuario usuario = RestClient
		.create(apiConfig.getBaseUrl())
		.get()
		.uri(uri-> 
				uri
				.path(apiConfig.getObterUsuario())
				.queryParam("email", email)
				.build()
		)
		.retrieve()
		.body(Usuario.class);
		
		return Optional.ofNullable(usuario);
	}
	
	

}
