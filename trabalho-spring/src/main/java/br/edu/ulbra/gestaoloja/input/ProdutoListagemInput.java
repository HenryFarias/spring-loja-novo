package br.edu.ulbra.gestaoloja.input;

import java.util.Set;

import br.edu.ulbra.gestaoloja.model.Comentarios;

public class ProdutoListagemInput {

    private Long id;
    private String nome;
    private String descricao;
    private String imagePath;
    private Set<Comentarios> comentarios;
    private Integer comentariosPositivos;
    private Integer comentariosNegativos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Set<Comentarios> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getComentariosPositivos() {
		return comentariosPositivos;
	}

	public void setComentariosPositivos(Integer comentariosPositivos) {
		this.comentariosPositivos = comentariosPositivos;
	}

	public Integer getComentariosNegativos() {
		return comentariosNegativos;
	}

	public void setComentariosNegativos(Integer comentariosNegativos) {
		this.comentariosNegativos = comentariosNegativos;
	}
}
