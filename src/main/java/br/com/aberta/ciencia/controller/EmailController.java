package br.com.aberta.ciencia.controller;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.GrauMaturidade;
import br.com.aberta.ciencia.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmailController {

    @Autowired
  EmailService emailService;

    @GetMapping("/email_send/{id}")
    public void enviaEmail(@PathVariable (value = "id") Long idUsuario) throws ResourceNotFoundException {
        emailService.enviar(idUsuario);

    }

}