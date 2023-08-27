package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;



@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
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
		
		http.csrf().disable();
		http.authorizeRequests()
				//Controladores REST
				.antMatchers("/sites", "/hoteis", "/promocoes").permitAll()
				.antMatchers("/sites/{\\d+}*", "hoteis/{\\d+}*").permitAll()
				.antMatchers("/promocoes/{\\d+}*").permitAll()
				.antMatchers("/hoteis/cidades/{\\w+}*").permitAll()
				.antMatchers("/hoteis/{\\d+}*").permitAll()
				.antMatchers("/promocoes/sites/{\\d+}*").permitAll()
				.antMatchers("/promocoes/hoteis/{\\d+}*").permitAll()
				.antMatchers("/*").permitAll()
				
				.antMatchers("/", "/index", "/error").permitAll()
				.antMatchers("/login/**", "/site/listar", "/site/lista", "/image/**", "/hotel/listar","/hotel/lista").permitAll()
				.antMatchers("/admin/**").permitAll()
				.antMatchers("/hotel/**").permitAll()
				.antMatchers("/site/**").permitAll()
				
				//.antMatchers(HttpMethod.DELETE,"/hoteis/{\\d+}*").permitAll()
				
				.anyRequest().permitAll()
			.and()	
				.formLogin().loginPage("/login").permitAll()
			.and()
				.logout().logoutSuccessUrl("/").permitAll();
	}
	
}
