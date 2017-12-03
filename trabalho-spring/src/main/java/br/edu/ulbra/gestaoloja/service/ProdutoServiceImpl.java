package br.edu.ulbra.gestaoloja.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ulbra.gestaoloja.model.Comentarios;
import br.edu.ulbra.gestaoloja.model.Produto;
import br.edu.ulbra.gestaoloja.repository.ComentarioRepository;
import br.edu.ulbra.gestaoloja.service.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
    ComentarioRepository comentarioRepository;
	
	@Override
    public Integer getCometariosPositivos(Produto produto) {
		Iterable<Comentarios> comentarios = comentarioRepository.findAll();
		ArrayList<Comentarios> comentariosPositivos = new ArrayList<Comentarios>(0);
		
		for (Comentarios comentario : comentarios) {
			if (comentario.getGostei().equals(true) && comentario.getProduto().equals(produto)) {
				comentariosPositivos.add(comentario);
			}
		}
		
    	return comentariosPositivos.size();
    }
	
	@Override
    public Integer getCometariosNegativos(Produto produto) {
		Iterable<Comentarios> comentarios = comentarioRepository.findAll();
		ArrayList<Comentarios> comentariosNegativos = new ArrayList<Comentarios>(0);
		
		for (Comentarios comentario : comentarios) {
			if (comentario.getGostei().equals(false) && comentario.getProduto().equals(produto)) {
				comentariosNegativos.add(comentario);
			}
		}
		
    	return comentariosNegativos.size();
    }
	
}
