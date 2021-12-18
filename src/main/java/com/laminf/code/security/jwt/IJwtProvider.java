package com.laminf.code.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.laminf.code.security.UserPrincipal;

public interface IJwtProvider {

	Authentication getAuthentication(HttpServletRequest request);

	boolean validateToken(HttpServletRequest request);

	String generateToken(UserPrincipal auth);

}
