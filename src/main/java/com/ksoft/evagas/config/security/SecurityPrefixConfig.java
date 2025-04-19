package com.ksoft.evagas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

public class SecurityPrefixConfig {
	
	@Bean
	static GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("MYPREFIX_");
	}

}
