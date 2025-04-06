package com.ksoft.evagas.service;

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
	
	public UserDetails getUserByUsername(String username)
	{
		return RestClient
		.create(apiConfig.getBaseUrl())
		.get()
		.uri(apiConfig.getObterUsuario())
		.retrieve()
		.body(UserDetails.class);
		
		
		
	}
	
	

}
