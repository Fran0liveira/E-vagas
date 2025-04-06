package com.ksoft.evagas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "api")
@Configuration
public class ApiConfig {
	
	private String baseUrl;
	private String obterVagas;
	private String publicarVaga;
}
