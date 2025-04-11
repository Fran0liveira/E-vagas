package com.ksoft.evagas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksoft.evagas.domain.Vaga;
import com.ksoft.evagas.domain.user.RegisterDto;
import com.ksoft.evagas.domain.user.Usuario;

@Controller
@RequestMapping(path = {"/", "/login"})
public class LoginController {
	
	String user = "fran@email.com";
	String password = "123";
	
	@GetMapping
	public String login(Model model) {
		model.addAttribute(new Usuario());
		return "login";
	}
	
	@PostMapping("entrar")
	public String fazerLogin(@ModelAttribute("usuario") Usuario usuario) {
		String userLogin = usuario.getEmail();
		String userPassword = usuario.getPassword();
		
		if(user.equals(userLogin) && userPassword.equals(password)) 
		{
			return entrar();
		}else {
			return credenciaisIncorretas();
		}
	}
	
	@GetMapping("/cadastro")
	public String cadastroUsuario(Model model) {
		RegisterDto registro = new RegisterDto();
		model.addAttribute(registro);
		return "cadastro-usuario";
	}
	
	private String entrar() {
		return "index";
	}
	
	private String credenciaisIncorretas() {
		return "login";
	}
}
