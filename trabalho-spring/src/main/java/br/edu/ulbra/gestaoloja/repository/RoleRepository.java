package br.edu.ulbra.gestaoloja.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoloja.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	Role findByName(String name);
	
}
