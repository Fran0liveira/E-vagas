package com.ksoft.evagas.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ksoft.evagas.domain.Vaga;

import io.restassured.RestAssured;

public class VagaServiceTest {

    @BeforeEach
    public void setUp() 
    {
        RestAssured.baseURI = "https://dce941e7-8680-4307-9ed7-eba0e9e7bce0.mock.pstmn.io";
    }

    @Test
    public void testGetVagas() {
        RestAssured
        .when()
            .get("/vagas")
        .then()
            .log().all()
            .statusCode(200);
    }
    
    @Test
    public void testPostVagas() 
    {
    	Vaga vaga = new Vaga()
    			.setDescricao("dev js")
    			.setDetalhes("requer experiencia com js")
    			.setEmpresa("Mcdonalds");
    	
        RestAssured
            .given()
            .body(vaga)
        .when()
            .post("/vagas")
        .then()
            .statusCode(201);
    }
}
