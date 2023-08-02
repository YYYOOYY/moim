package org.edupoll.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(t -> t.disable());
		http.authorizeHttpRequests(
				t -> t
					.requestMatchers("/", "/user/**", "/moim/post", "/image/**").permitAll()
					.requestMatchers("/status").permitAll()
					.anyRequest().authenticated()
				);
		http.formLogin(t -> t
				.loginPage("/user/login")
				.usernameParameter("loginId").passwordParameter("loginPass")
				);
		http.logout(t-> t.logoutUrl("/logout").logoutSuccessUrl("/user/login"));
		http.exceptionHandling(t -> t.accessDeniedPage("/access/denied"));
		
		return http.build();
	}
}
