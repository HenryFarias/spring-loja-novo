package br.edu.ulbra.gestaoloja.service.interfaces;

import br.edu.ulbra.gestaoloja.model.Produto;

public interface ProdutoService {
	
	public Integer getCometariosPositivos(Produto produto);
	
	public Integer getCometariosNegativos(Produto produto);
	
}
