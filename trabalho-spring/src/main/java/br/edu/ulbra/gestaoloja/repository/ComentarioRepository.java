package br.edu.ulbra.gestaoloja.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoloja.model.Comentarios;

public interface ComentarioRepository extends CrudRepository<Comentarios, Long> {

}
