package co.edu.unbosque.TiendaVirtual;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.unbosque.TiendaVirtual.userDetails.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**"
    };
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(resources).permitAll()
			.antMatchers("/", "/index", "/register", "/process_register").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/")
				.permitAll()
				.usernameParameter("username")
				.defaultSuccessUrl("/dash")
			.and()
			.logout().logoutSuccessUrl("/index.html?logout").permitAll()
			.and()
			.csrf().disable().cors();
		
	}
	
}
