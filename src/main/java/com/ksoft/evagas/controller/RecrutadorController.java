package com.ksoft.evagas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/recrutador")
public class RecrutadorController {
	
	@GetMapping
	public String areaDoRecrutador() {
		return "recrutador";
	}

}
