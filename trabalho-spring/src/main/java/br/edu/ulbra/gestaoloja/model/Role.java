package br.edu.ulbra.gestaoloja.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}
}
