package tp.appliSpring;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
*/
//@Configuration
public class WebSecurityConfig /* extends WebSecurityConfigurerAdapter */{
/*
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder() {
	    return this.passwordEncoder;
	 }
	 
	 private BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
	 
	 @Autowired
	 public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
	 auth.inMemoryAuthentication()
	 .withUser("user1").password(passwordEncoder.encode("pwd1")).roles("USER").and()
	 .withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN").and()
	 .withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER").and()
	 .withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
	
	 //String pwdCrypte = passwordEncoder.encode("pwd1"); System.out.println("pwdCrypte="+pwdCrypte);
	 //String pwdCrypte2 = passwordEncoder.encode("pwd1");System.out.println("pwdCrypte="+pwdCrypte2);
	 //String pwdAVerifier="pwd1" ; //ou bien "wrongPwd";
	 //boolean motDePasseOk = passwordEncoder.matches(pwdAVerifier,pwdCrypte);
	 //System.out.println("motDePasseOk = "+motDePasseOk);
	 
	 }
	 
	 @Override
	 @Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	 return super.authenticationManagerBean();
	 }
	 
	 @Override
	 protected void configure(final HttpSecurity http) throws Exception {
	 // config pour Spring-mvc avec WS-REST et tokens (ex : jwt) : 
	 http.authorizeRequests()
	 //.antMatchers("/rest/**").permitAll()
	 .anyRequest().permitAll()
	 //.anyRequest().authenticated()
	 //.anyRequest().hasRole("ADMIN")
	 .and().csrf().disable();
	 //.and().httpBasic()
	 }
*/
}
