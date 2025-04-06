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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String publicarVaga(RedirectAttributes mv, @ModelAttribute("vaga") Vaga vaga) 
	{
		boolean status = vagaService.salvarVaga(vaga);
		System.out.println("SUCESSO: " +status);
		if(status) {
			mv.addFlashAttribute("msgVaga", "Vaga " +vaga.getDescricao() + " criada com sucesso!");
			mv.addFlashAttribute("statusVaga", "SUCCESS");
		}else {
			mv.addFlashAttribute("msgVaga", "Erro ao criar vaga " +vaga.getDescricao() + "!");
			mv.addFlashAttribute("statusVaga", "ERROR");
		}
		
		return "redirect:/vagas";
	}
	
	@GetMapping("form")
	public String criarVaga(Model model) {
		model.addAttribute("vaga", new Vaga());
		return "form-vaga";
	}
}
