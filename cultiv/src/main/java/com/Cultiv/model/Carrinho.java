package com.Cultiv.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;




@Entity
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCarrinho;	
	
	//@OneToMany(mappedBy = "carrinho",cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "carrinho",cascade = CascadeType.ALL)
	private List<Produto>produtos;
	
	
	
	private double quantidadeItens;
	
	
	//getters e setters.
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public long getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public double getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(double quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
		


}
