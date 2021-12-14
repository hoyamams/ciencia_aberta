package br.com.aberta.ciencia.config;


import br.com.aberta.ciencia.filter.CustomAuthenticationFilter;
import br.com.aberta.ciencia.filter.CustomAuthorizationFilter;
import br.com.aberta.ciencia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(allowCredentials = "*", allowedHeaders = "*",originPatterns = "*")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final  UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//log.info ("VALOR STRING"+ String.valueOf(bCryptPasswordEncoder));
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		// ESSAs LINHAs SERVEm PARA SETAR UM PADÃO DIFERENTE DE /LOGIN QUE É DEFAULT DO MODULO DE AUTENTICAÇÃO
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/user_login");
		//fazendo isso no addFilter devo passar a variavel criada e não inicializar uma
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/user_login/**", "/usuario/**","/usuario_list/**","/usuario_busca/**","/usuario_update/**",
				"/perguntas/**","/perguntas_list/**","/pergunta_busca/**","/pergunta_update/**","/perguntas_delete/**",
				"/categoria/**", "/categoria_list/**", "/categoria_delete/**", "/categoria_update/**", "/categoria_busca/**").permitAll();
		//http.authorizeRequests().antMatchers(POST, "/usuario_list/**").hasAnyAuthority("COMUM");
		http.authorizeRequests().anyRequest().authenticated();
		//http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}




}