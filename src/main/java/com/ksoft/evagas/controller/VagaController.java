package com.ksoft.evagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ksoft.evagas.domain.Vaga;
import com.ksoft.evagas.service.VagaService;

@Controller
@RequestMapping("vagas")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Vaga> vagas = vagaService.getVagas();
		model.addAttribute("vagas", vagas);
		
		return "index";
	}
	
	@PostMapping("publicar")
	public String publicarVaga(Model model, @ModelAttribute("vaga") Vaga vaga) 
	{
		System.out.println("Publicando vaga..." + new Gson().toJson(vaga));
		
		model.addAttribute("vaga", vaga);
		return "redirect:/vagas";
	}
	
	@GetMapping("form")
	public String criarVaga(Model model) {
		model.addAttribute("vaga", new Vaga());
		return "form-vaga";
	}
}
