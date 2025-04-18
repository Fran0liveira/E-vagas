package com.ksoft.evagas.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder.Default;
import lombok.Data;

@Data
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	
	private List<UserRole> roles = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(roles.contains(UserRole.ADMIN)) {
			return Arrays.asList(
					auth(UserRole.ADMIN),
					auth(UserRole.RECRUTADOR),
					auth(UserRole.CANDIDATO));
		}
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		if(roles.contains(UserRole.RECRUTADOR)){
			list.add(auth(UserRole.RECRUTADOR));
		}
		if(roles.contains(UserRole.CANDIDATO)){
			list.add(auth(UserRole.CANDIDATO));
		}
		return list;
	}
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return email;
	}
	
	private SimpleGrantedAuthority auth(UserRole role) {
		return new SimpleGrantedAuthority(role.getRole());
	}
}
