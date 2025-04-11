package com.ksoft.evagas.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@Email
	@NotBlank
	private String email;
	
	@Size(min = 6, message = "A senha precisa ter no mínimo 6 caracteres")
	@Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula")
	@Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula")
	private String senha;

}
