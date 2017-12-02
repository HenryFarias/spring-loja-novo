package br.edu.ulbra.gestaoloja.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ulbra.gestaoloja.model.Comentarios;
import br.edu.ulbra.gestaoloja.repository.ComentarioRepository;
import br.edu.ulbra.gestaoloja.service.interfaces.SecurityService;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
    ComentarioRepository comentarioRepository;
	
	@Autowired
    SecurityService security;
    
    @PostMapping(value="/new")
    public void novo(Comentarios comentario) throws IOException {
    	comentario.setUser(this.security.findLoggedInUser());
        this.comentarioRepository.save(comentario);
    }
}
