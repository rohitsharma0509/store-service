package com.app.ecom.store.config;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.handler.CustomAccessDeniedHandler;
import com.app.ecom.store.handler.CustomAuthenticationFailureHandler;
import com.app.ecom.store.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(RequestUrls.ADMIN, RequestUrls.ADMIN+"/*").access("hasAuthority('ADMIN')")
		.antMatchers(RequestUrls.FORGET_PASSWORD, RequestUrls.CHANGE_PASSWORD, RequestUrls.RESET_PASSWORD, 
				RequestUrls.REGISTRATION, RequestUrls.REGISTRATION_CONFIRM).permitAll()
		.antMatchers("/resources/**", "/css/**", "/js/**", "/images/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage(RequestUrls.LOGIN).permitAll()
		.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
		//.and().exceptionHandling().accessDeniedPage(RequestUrls.ERROR+"/"+HttpStatus.FORBIDDEN.value())
		.and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
		.and().logout().invalidateHttpSession(true).deleteCookies(FieldNames.REMEMBER_ME).permitAll()
		.and().rememberMe().tokenValiditySeconds(5);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}