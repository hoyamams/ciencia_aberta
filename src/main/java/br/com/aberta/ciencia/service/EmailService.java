package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Respostas;
import br.com.aberta.ciencia.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RespostasService respostasService;

    private Respostas respostasUsuario;
    private Usuario usuarioEnvioEmail;

    public void enviar(Long idUsuario) throws ResourceNotFoundException {

        usuarioEnvioEmail = usuarioService.findById(idUsuario);
        respostasUsuario = respostasService.findRespostaUsuario(idUsuario);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(usuarioEnvioEmail.getEmailUsuario());
        email.setSubject("Ciência Aberta - Meu resultado");
       email.setText("Olá "+usuarioEnvioEmail.getNomeUsuario()+", \n \n Esse é o resultado de sua pesquisa: " +
                "\n Total de Pontos: "+ respostasUsuario.getPontuacaoUsuario()+".\n Nível Maturidade: "
                + respostasUsuario.getGrauMaturidadeUsuario().getNivelGrauMaturidade()+". " +
               "Descrição Nível Maturidade: " + respostasUsuario.getGrauMaturidadeUsuario().getDescricaoGrauMaturidade()+ "."+
               "\n \n  Obrigado por participar da pesquisa. ");
        mailSender.send(email);
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("cienciaabertaapp@gmail.com");
        mailSender.setPassword("420266Dw@@");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}