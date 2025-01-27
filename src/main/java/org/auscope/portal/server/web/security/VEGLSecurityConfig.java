package org.auscope.portal.server.web.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.auscope.portal.server.web.security.aaf.AAFAuthenticationFilter;
import org.auscope.portal.server.web.security.aaf.AAFAuthenticationProvider;
import org.auscope.portal.server.web.security.aaf.AAFAuthenticationSuccessHandler;
import org.auscope.portal.server.web.security.google.GoogleOidcUserService;
import org.auscope.portal.server.web.service.ANVGLUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class VEGLSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${frontEndUrl}")
	private String frontEndUrl;
	
	@Value("${portalUrl}")
	private String portalUrl;
	
	@Value("${aaf.callbackUrl}")
	private String aafCallbackUrl;
	
	@Autowired
	GoogleOidcUserService googleOidcUserService;
	
	@Autowired
	VGLOAuth2UserService oauth2UserService;

	@Autowired
	AAFAuthenticationSuccessHandler aafSuccessHandler;
	
	@Autowired
	private AAFAuthenticationProvider aafAuthenticationProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(aafAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()			
				.antMatchers("/secure/**")
					.authenticated()
				.antMatchers("/**")
					.permitAll()
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl(frontEndUrl)
					.permitAll()
			.and()
				.addFilterBefore(ssoFilterAAF(), BasicAuthenticationFilter.class)
				.userDetailsService(userDetailsService())
	    	.oauth2Login()
	    		.loginPage(frontEndUrl + "/login")
	    		.defaultSuccessUrl(frontEndUrl + "/login/loggedIn")
	    		.userInfoEndpoint()
	    			.userService(oauth2UserService);
	}
	
	@Bean
	public ANVGLUserDetailsService userDetailsService() {
		Map<String, List<String>> rolesByUser = new HashMap<String, List<String>>();
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_ADMINISTRATOR");
		roles.add("ROLE_UBC");
		rolesByUser.put("105810302719127403909", roles);
		ANVGLUserDetailsService userDetailsService = new ANVGLUserDetailsService("ROLE_USER", rolesByUser);
		return userDetailsService;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Arrays.asList(aafAuthenticationProvider));
	}
	
	private Filter ssoFilterAAF() {
		AAFAuthenticationFilter aafFilter = new AAFAuthenticationFilter(this.authenticationManager(), aafCallbackUrl);
		aafFilter.setAuthenticationSuccessHandler(aafSuccessHandler);
		// Failure handler used only for testing at the moment
		aafFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("AAF Authentication failed: " + exception.getLocalizedMessage());
				
			}
		});
		return aafFilter;
	}

}
