package com.ksoft.evagas.utils;

import org.springframework.core.ParameterizedTypeReference;

public class Body {
	
	public static <T> ParameterizedTypeReference<T> parser(){
		return new ParameterizedTypeReference<>() {};
	}

}
