package com.ksoft.evagas.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ksoft.evagas.domain.Vaga;

@Service
public class VagaService {
	
	public List<Vaga> getVagas()
	{
		Vaga vaga1 = new Vaga()
				.setDescricao("Desenvolvedor Java Pleno")
				.setDetalhes("Mais de 3 anos de experiência com JAVA")
				.setEmpresa("KSoft");
		
		Vaga vaga2 = new Vaga()
				.setDescricao("Desenvolvedor Python Senior")
				.setDetalhes("Mais de 5 anos de experiência com Python")
				.setEmpresa("KSoft");
		
		return Arrays.asList(vaga1, vaga2);
	}

}
