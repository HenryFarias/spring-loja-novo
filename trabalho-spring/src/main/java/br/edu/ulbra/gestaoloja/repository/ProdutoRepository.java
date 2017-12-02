package br.edu.ulbra.gestaoloja.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoloja.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
