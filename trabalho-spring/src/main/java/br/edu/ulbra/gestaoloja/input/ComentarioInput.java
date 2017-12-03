package br.edu.ulbra.gestaoloja.input;

import br.edu.ulbra.gestaoloja.model.Produto;
import br.edu.ulbra.gestaoloja.model.User;

public class ComentarioInput {

    private Long id;
    private Boolean gostei;
    private String comentario;
    private Produto produtoId;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Boolean getGostei() {
		return gostei;
	}

	public void setGostei(Boolean gostei) {
		this.gostei = gostei;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Produto getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Produto produtoId) {
		this.produtoId = produtoId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
