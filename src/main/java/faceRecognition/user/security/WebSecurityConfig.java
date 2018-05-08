package faceRecognition.user.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Web security configuration class
 * 
 * @author Sarath Muraleedharan
 *
 */
@Configurable
@EnableWebSecurity
// Modifying or overriding the default spring boot security.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
				// ignoring the "/", "/index.html", "/app/**", "/register",
				// "/favicon.ico"
				.antMatchers("/", "/index.html", "/address/**", "/user/email", "/company/*", "/user/login","/employee/*",
						"/user/user","/employee/byFirstOrLastName/*","/user/employee/*" ,"/user/employee", "user/securityQuestion", "/favicon.ico","/images/**");
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// starts authorizing configurations
				.authorizeRequests()
				// authenticate all remaining URLS
				.anyRequest().fullyAuthenticated().and()
				// adding JWT filter
				.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
				// enabling the basic authentication
				.httpBasic().and()
				// configuring the session as state less. Which means there is
				// no session in the server
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// disabling the CSRF - Cross Site Request Forgery
				.csrf().disable();
	}

}
////
//// import org.springframework.beans.factory.annotation.Configurable;
//// import
//// org.springframework.security.config.annotation.web.builders.HttpSecurity;
//// import
//// org.springframework.security.config.annotation.web.builders.WebSecurity;
//// import
//// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//// import
//// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//// import org.springframework.security.config.http.SessionCreationPolicy;
//// import
//// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//// import org.springframework.web.bind.annotation.CrossOrigin;
////
////
///// **
//// * Spring Web security configuration class
//// *
//// *
//// */
//// @Configurable
//// @EnableWebSecurity
////// @CrossOrigin(origins = "http://localhost:4200")
////// Modifying or overriding the default spring boot security.
//// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////
//// // This method is for overriding some configuration of the WebSecurity
//// // If you want to ignore some request or request patterns then you can
//// // specify that inside this method
////// @Override
////// public void configure(WebSecurity web) throws Exception {
//////
////// web.ignoring()
////// // ignoring the "/", "/index.html", "/app/**", "/register",
////// // "/favicon.ico"
////// .antMatchers("/",
//// "/user/securityQuestion","/address/country","/address/state","/address/city",
//// "/app/**", "/user/email", "/user/login", "/favicon.ico");
////// }
////
//// // This method is used for override HttpSecurity of the web Application.
//// // We can specify our authorization criteria inside this method.
//// @Override
//// protected void configure(HttpSecurity http) throws Exception {
//// http.authorizeRequests()
//// .antMatchers("/user/logged","/user/company/employee/*").access("hasRole('ROLE_ADMIN')")
//// .anyRequest().permitAll().and()
//// .addFilterBefore(new JWTFilter(),
//// UsernamePasswordAuthenticationFilter.class)
//// .httpBasic().and()
//// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//// .and().csrf().disable();;
//////
//////
////// http
////// // starts authorizing configurations
////// .authorizeRequests()
////// // authenticate all remaining URLS
////// .anyRequest().fullyAuthenticated().and()
////// // adding JWT filter
////// .addFilterBefore(new JWTFilter(),
//// UsernamePasswordAuthenticationFilter.class)
////// // enabling the basic authentication
////// .httpBasic().and()
////// // configuring the session as state less. Which means there is
////// // no session in the server
////// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
////// // disabling the CSRF - Cross Site Request Forgery
////// //.csrf().disable();
//// }
////
//// }