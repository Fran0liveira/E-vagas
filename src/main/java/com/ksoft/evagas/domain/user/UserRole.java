package com.ksoft.evagas.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
	EMPTY("EMPTY"),
	ADMIN("ADMIN"),
	RECRUTADOR("RECRUTADOR"),
	CANDIDATO("CANDIDATO");
	
	private final String role;
	
	UserRole(String role){
		this.role = role;
	}
	
    public String getRole() {
        return role;
    }
	
//    @JsonValue
//    public String getRole() {
//        return role;
//    }
//
//    @JsonCreator
//    public static UserRole fromString(String value) {
//        for (UserRole role : UserRole.values()) {
//            if (role.getRole().equalsIgnoreCase(value)) {
//                return role;
//            }
//        }
//        throw new IllegalArgumentException("Invalid role: " + value);
//    }
	
}
