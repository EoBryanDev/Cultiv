package com.Cultiv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "entrada_itens")
public class EntradaItens implements Serializable {

	public EntradaItens() {
		super();
	}

	private static final long serialVerisonUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	private EntradaProduto entrada;
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Produto produto;
	
	private double quantidade= 0.0;

	private double valorCompra = 0.0;
	
	private double valorVenda =0.0;
	
	

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntradaProduto getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaProduto entrada) {
		this.entrada = entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	
	
}
