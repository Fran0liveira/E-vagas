package com.ksoft.evagas.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ksoft.evagas.domain.Vaga;
import com.ksoft.evagas.domain.user.RegisterUserDto;
import com.ksoft.evagas.domain.user.UserRole;
import com.ksoft.evagas.domain.user.Usuario;
import com.ksoft.evagas.service.UserService;

@Controller
@RequestMapping(value = {"/login"})
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder psdEncoder;
	
	@GetMapping
	public ModelAndView login(Model model, boolean error) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return loadLoginPage(error);
        }
 
        return new ModelAndView("redirect:/");
	}
	
	private ModelAndView loadLoginPage(boolean error) {
		ModelAndView modelAndView = new ModelAndView("login-page");
		if(error) {
			modelAndView.addObject("error", true);
		}
		modelAndView.addObject(new Usuario());
		return modelAndView;
	}
	
	@PostMapping("/cadastrar-usuario")
	public String cadastrar(@ModelAttribute("usuario") RegisterUserDto registerUser) {
		
		registerUser.setSenha(psdEncoder.encode(registerUser.getSenha()));
		
		System.out.println(new Gson().toJson(registerUser));
	
		userService.cadastrarUsuario(registerUser);
		return "login";
	}
	
	@GetMapping("/cadastro-form")
	public String cadastroUsuario(Model model) {
		RegisterUserDto registro = new RegisterUserDto();
		model.addAttribute("usuario", registro);
		return "cadastro-usuario";
	}
	
	@GetMapping("/success")
	public ModelAndView loginSuccess() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<String> authorities = authentication.getAuthorities()
        		.stream()
        		.map(a->a.getAuthority())
        		.collect(Collectors.toList());
		
		System.out.println("auth: " +authorities);
        
        
        if(authorities.contains(UserRole.RECRUTADOR.getRole())) {
        	return new ModelAndView("redirect:/recrutador");
        }else {
        	return new ModelAndView("redirect:/vagas");
        }
	}
}
