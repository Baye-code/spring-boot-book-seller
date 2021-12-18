package com.laminf.code.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

public class SecurityUtils {
	
	public static final String ROLE_PREFIX = "ROLE_";
	public static final String AUTH_HEADER = "Authorization";
	public static final String AUTH_HEADER_TYPE = "Bearer";
	public static final String AUTH_TOKEN_PREFIX = AUTH_HEADER_TYPE + " ";
	
	// =============================================================================================================
	
	
	public static SimpleGrantedAuthority convertToAuthority(String role) {
		
		String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
		
		return new SimpleGrantedAuthority(formattedRole);
	}
	
	// =============================================================================================================
	
	public static String extractAuthTokenFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(AUTH_HEADER);
		
		if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}
		
		return null;
	}
	
	// =============================================================================================================
	
	

}