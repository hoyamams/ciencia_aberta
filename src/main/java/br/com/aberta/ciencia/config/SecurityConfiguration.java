package br.com.aberta.ciencia.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	//@Resource(name = "UsuarioRepository")
    //private UserDetailsService userDetailsService;
	
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        
        	.httpBasic()
        	.and()
        	.authorizeRequests()
        	.antMatchers("/usuario").permitAll()
        	.antMatchers("/").permitAll()
        	.antMatchers("/login").permitAll()
        	.antMatchers("/usuario_update").hasRole("USER")
            .antMatchers("/usuario_search").hasRole("ADMIN")
            .antMatchers("/usuario_list").hasRole("ADMIN") 
            .and()
            .csrf().disable()
            .headers().frameOptions().disable();
       /*.authorizeRequests()
        	.antMatchers("/","/usuario").permitAll()
        	.anyRequest().authenticated()
        	.and()
        .formLogin()
        	.loginPage("/login")
        	.permitAll()
        	.and()
        .logout()
        	.permitAll();
       
        
       .csrf().disable()
          .authorizeRequests()
          .antMatchers("/**")
          .permitAll()
           .and()
           .formLogin()
           .loginPage("/login")
           .permitAll(); 
        */
        //.authorizeRequests()
       // .antMatchers("/**", "/usuario").permitAll();
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   /* @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .usuarioService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/

   /* @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

   
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }*/
}
