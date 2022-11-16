package org.mygeneric.jwt_basic_security.config;

import java.util.Arrays;

import org.mygeneric.jwt_basic_security.properties.MySecurityProperties;
import org.mygeneric.util.JwtAuthenticationFilter;
import org.mygeneric.util.MyNoAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 extends WebSecurityConfigurerAdapter et coder .configure()
 etait la manière stable pour configurer Spring-security en Spring 4 et Spring 5
 ----
 Depuis les dernières versions >=5.7 et springBoot 2.7 , cette manière est maintenant
 considérée comme "deprecated" (car pas assez souple).
 Le code ci-dessous correspond à la nouvelle manière conseillée:
 */

@Configuration
@Profile("withSecurity")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//necessary for @PreAuthorize("hasRole('ADMIN or ...')")
@ConfigurationPropertiesScan("org.mygeneric.jwt_basic_security.properties")
public class WebSecurityRecentConfig  {
	//plus de extends WebSecurityConfigurerAdapter (now deprecated) !!!
	
	
	@Autowired(required = false)
	public MySecurityProperties mySecurityProperties;
	
	private static final String[] SWAGGER_AUTH_WHITELIST = {
			"/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**"
			};
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	     
	@Bean //**** IMPORTANT *****
	public AuthenticationManager authenticationManagerFromHttpSecurity(HttpSecurity http) throws Exception {
			// Configure AuthenticationManagerBuilder
	        AuthenticationManagerBuilder authenticationManagerBuilder =
	        		http.getSharedObject(AuthenticationManagerBuilder.class); //**** IMPORTANT *****
	        authenticationManagerBuilder.parentAuthenticationManager(null);//**** VERY VERY IMPORTANT in order to avoid infinite loop when .authenticate() fail
	        authenticationManagerBuilder.inMemoryAuthentication()
			.withUser("user1").password(passwordEncoder.encode("pwd1")).roles("USER")
			.and().withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN")
			.and().withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER")
			.and().withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
	       return authenticationManagerBuilder.build(); //**** IMPORTANT *****		
	}

	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private MyNoAuthenticationEntryPoint unauthorizedHandler;
	
	
	//@Override protected void configure(final HttpSecurity http) throws Exception {...}
	//maintenant à remplacer par 
	//@Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { ... return http.build(); }
	
	@Bean //**** IMPORTANT *****
	protected SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authenticationManager ) throws Exception {
			
		String[] apiWhiteList={"/public/**"}; //default value
		String[] apiReadOnlyWhiteList={"/public/**"}; //default value
		String[] apiProtectedList = {"/private/**"}; //default value
		if(mySecurityProperties!=null && mySecurityProperties.getApiWhiteList()!=null)
			apiWhiteList=mySecurityProperties.getApiWhiteList().split(";");
		if(mySecurityProperties!=null && mySecurityProperties.getApiReadOnlyWhiteList()!=null)
			apiReadOnlyWhiteList=mySecurityProperties.getApiReadOnlyWhiteList().split(";");
		if(mySecurityProperties!=null && mySecurityProperties.getApiProtectedList()!=null)
			apiProtectedList=mySecurityProperties.getApiProtectedList().split(";");
		
		System.out.println("apiWhiteList="+Arrays.asList(apiWhiteList));
		System.out.println("apiReadOnlyWhiteList="+Arrays.asList(apiReadOnlyWhiteList));
		System.out.println("apiProtectedList="+Arrays.asList(apiProtectedList));
		
		
		http.authorizeRequests()
		.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
		"/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
		.antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.POST,"/api-login/public/login").permitAll()
		.antMatchers(apiWhiteList).permitAll()
		.antMatchers(HttpMethod.GET,apiReadOnlyWhiteList).permitAll()
		.antMatchers(apiProtectedList).authenticated()
		.and().cors() //enable CORS (avec @CrossOrigin sur class @RestController)
		.and().csrf().disable() 
		// If the user is not authenticated, returns 401
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// This is a stateless application, disable sessions
		.authenticationManager(authenticationManager) //**** IMPORTANT *****
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		// Custom filter for authenticating users using tokens
		.addFilterBefore(jwtAuthenticationFilter,
		UsernamePasswordAuthenticationFilter.class);
		
		return http.build(); //**** IMPORTANT *****
	} 
	
	
}