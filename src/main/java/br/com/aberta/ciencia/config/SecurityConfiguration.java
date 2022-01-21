package br.com.aberta.ciencia.config;


import br.com.aberta.ciencia.filter.CustomAuthenticationFilter;
import br.com.aberta.ciencia.filter.CustomAuthorizationFilter;
import br.com.aberta.ciencia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
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

		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
				.authorizeRequests()
				.antMatchers("/user_login/**","/usuario/**","/respostas/**","/email_send/**","/respostas_list/**",
						"/usuario_busca_login/**","/categoria_list/**","/pesquisa_usuario/**", "/perguntas_list/**","/perguntas_categoria/**",
						"/glossario_list/**","/apresentacao_list/**","/referencias_list/**").permitAll()
				.antMatchers("/usuario_busca/**","/usuario_update/**", "/pergunta_update_divulga/**","/token/refresh/**").hasAnyAuthority("COMUM","ADMIN")
				.antMatchers(
						"/usuario_list/**","/usuario_delete/**",
						"/grau_maturidade/**","/grau_maturidade_update/**","/grau_maturidade_delete/**","/grau_maturidade_busca/**","/grau_maturidade_list/**",
						"/perguntas/**","/pergunta_update/**",  "/pergunta_busca/**",
						"/categoria/**","/categoria_update/**", "/categoria_delete/**","/categoria_busca/**",
						"/glossario/**","/glossario_update/**", "/glossario_delete/**","/glossario_busca/**",
						"/apresentacao/**","/apresentacao_update/**", "/apresentacao_delete/**","/apresentacao_busca/**",
						"/referencias/**","/referencias_update/**", "/referencias_delete/**","/referencias_busca/**")
				.hasAnyAuthority("ADMIN");
		http.cors().and().csrf().disable();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	/*	http.authorizeRequests()
				//.antMatchers(HttpMethod.OPTIONS, "/topicos").permitAll()
				//.antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.POST, "/user_login/**","/usuario/**","/respostas/**","/grau_maturidade/**","/categoria/**","/perguntas/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/user_login/**", "/categoria_update/**","/usuario_update/**","/pergunta_update/**","/grau_maturidade_update/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/user_login/**").permitAll()
				.antMatchers(HttpMethod.GET, "/user_login/**", "/**","/usuario/**", "/usuario_busca_login/**").permitAll()
				.antMatchers(HttpMethod.GET, "/**", "/perguntas/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/categoria_delete/**","/perguntas_delete/**","/usuario_delete/**","/grau_maturidade_delete/**").permitAll();
			//	.and().cors().and().csrf().disable();
*/
	/*	http.cors().disable();
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/user_login/**", "/usuario/**","/usuario_list/**","/usuario_busca/**","/usuario_update/**",
				"/perguntas/**","/perguntas_list/**","/pergunta_busca/**","/pergunta_update/**","/perguntas_delete/**", "/perguntas_categoria/**", "/respostas/**",
				"/grau_maturidade/**", "/grau_maturidade_list/**", "/grau_maturidade_delete/**", "/grau_maturidade_update/**", "/grau_maturidade_busca/**",
				"/categoria/**", "/categoria_list/**", "/categoria_delete/**", "/categoria_update/**", "/categoria_busca/**").permitAll();*/
	//	http.authorizeRequests().antMatchers(GET, "/usuario_list/**","/perguntas_list/**", "/grau_maturidade_list/**","/categoria_list/**").hasAnyAuthority("ADMIN");
	//	http.authorizeRequests().antMatchers(POST,"/categoria/**").hasAnyAuthority("ADMIN");

		//http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}




}