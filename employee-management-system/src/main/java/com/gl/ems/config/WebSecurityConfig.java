package com.gl.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.ems.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public DaoAuthenticationProvider glAuthPro() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(glUserDetails());
		daoAuthenticationProvider.setPasswordEncoder(glPasswordEncoder1());
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder glPasswordEncoder1() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService glUserDetails() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http.csrf().disable().authorizeRequests().requestMatchers("/api/auth/**").permitAll()
		// .requestMatchers("/api/employees/**").hasAnyRole("ADMIN",
		// "USER").anyRequest().authenticated().and()
		// .httpBasic();
		http.csrf().disable().authorizeRequests().requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/api/employees/list", "/api/employees/getAPI", "/api/employees/search/{firstName}",
						"/api/employees/sort")
				.hasAnyAuthority("USER", "ADMIN").requestMatchers("/api/employees/add").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().httpBasic().and().formLogin().loginProcessingUrl("/login")
				.successForwardUrl("/api/employees/getAPI").permitAll().and().logout().logoutSuccessUrl("/login")
				.permitAll().and().exceptionHandling().accessDeniedPage("/api/employees/unauthUser").and().cors().and()
				.csrf().disable();
		http.authenticationProvider(glAuthPro());
		return http.build();

	}
}
