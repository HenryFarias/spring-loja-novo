package br.edu.ulbra.gestaoloja.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoloja.input.ComentarioInput;
import br.edu.ulbra.gestaoloja.model.Comentarios;
import br.edu.ulbra.gestaoloja.repository.ComentarioRepository;
import br.edu.ulbra.gestaoloja.repository.ProdutoRepository;
import br.edu.ulbra.gestaoloja.service.interfaces.SecurityService;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
    ComentarioRepository comentarioRepository;
	
	@Autowired
    ProdutoRepository produtoRepository;
	
	@Autowired
    SecurityService security;
    
    @PostMapping(value="/new")
    public ModelAndView novo(Comentarios comentario) throws IOException {
    	ModelAndView mv = new ModelAndView();
    	
    	comentario.setUser(this.security.findLoggedInUser());
        this.comentarioRepository.save(comentario);
        
        mv.setViewName("redirect:/produto/" + comentario.getProduto().getId() + "/detalhe");
        return mv;
    }
}
