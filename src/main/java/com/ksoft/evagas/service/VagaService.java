package com.ksoft.evagas.service;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestHeadersSpec.ExchangeFunction;
import org.springframework.web.client.RestClient.ResponseSpec;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonSyntaxException;
import com.ksoft.evagas.config.ApiConfig;
import com.ksoft.evagas.domain.Vaga;
import com.ksoft.evagas.utils.Body;

@Service
public class VagaService {
	
	@Autowired
	private ApiConfig apiConfig;
	
	public List<Vaga> getVagas()
	{
		return RestClient.create(apiConfig.getBaseUrl())
		.get()
		.uri(apiConfig.getObterVagas())
		.retrieve()
		.body(Body.parser());
	}
	
	public boolean salvarVaga(Vaga vaga)
	{
		ResponseEntity<?> response = RestClient
				.create(apiConfig.getBaseUrl())
				.post()
				.uri(apiConfig.getPublicarVaga())
				.body(vaga)
				.retrieve()
				.toBodilessEntity();
		
		return response.getStatusCode().is2xxSuccessful();
	}

}
