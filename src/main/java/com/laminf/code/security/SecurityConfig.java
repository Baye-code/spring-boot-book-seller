package com.laminf.code.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.laminf.code.model.Role;
import com.laminf.code.security.jwt.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${authentication.internal-api-key}")
	private String internalApiKey;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}
	
	@Bean
	public InternalApiAuthenticationFilter internalApiAuthenticationFilter() {	
		return new InternalApiAuthenticationFilter(internalApiKey);	
	}

	// =============================================================================================================

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		
	}

	// =============================================================================================================
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeHttpRequests()
			.antMatchers("/api/authentication/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/book").permitAll()
			//.antMatchers("/api/purchase-history").permitAll()
			.antMatchers("/api/book/**").hasRole(Role.ADMIN.name())
			.antMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name())
			.anyRequest().authenticated();
		
		// jwt filter
		// internal --> jwt --> default filter
		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(internalApiAuthenticationFilter(), JwtAuthorizationFilter.class);
		
	}
	
	// =============================================================================================================
	
	public WebMvcConfigurer corsConfiguger() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("*");
				
			}
		};
	}
	
	// =============================================================================================================

}
