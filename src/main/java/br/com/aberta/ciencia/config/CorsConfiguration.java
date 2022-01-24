package br.com.aberta.ciencia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
               .allowedHeaders("Authorization", "Content-Type", "Access-Control-Allow-Origin", "Origin", "X-Auth-Token")
               .allowCredentials(false)
                . exposedHeaders("Cache-Control", "Content-Language","Content-Type","Expires","Last-Modified","Pragma");
    }



/*@Override
	public void addCorsMappings(CorsRegistry registry) {
		/*registry.addMapping("/**")
				.allowedOrigins("/**");
				//.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");  *
		registry.addMapping("http://localhost:3000/")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

	}
	*/

}