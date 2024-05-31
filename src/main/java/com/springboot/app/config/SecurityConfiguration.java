package com.springboot.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.app.service.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final JpaUserDetailsService jpaUserDetailsService;

	public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
		this.jpaUserDetailsService = jpaUserDetailsService;
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
			.csrf(Customizer.withDefaults())
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home", "/user/signup", "/user/signup/submit").permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/app/**").hasRole("USER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.userDetailsService(jpaUserDetailsService)
			.formLogin((form) -> form
				.loginPage("/user/login")
				.successHandler(new AuthenticationSuccessHandler())
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
    }

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
