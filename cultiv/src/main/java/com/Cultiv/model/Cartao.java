package com.Cultiv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

	public Cartao() {
		super();
	}

	private static final long serialVerisonUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String cartao;
	
	private String numCartao;
	
	private String nomeCartao;
	
	private String mesCartao;
	
	private String anoCartao;
	
	private String codCartao;
	
	@ManyToOne
	private Cliente cliente;
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public String getMesCartao() {
		return mesCartao;
	}

	public void setMesCartao(String mesCartao) {
		this.mesCartao = mesCartao;
	}

	public String getAnoCartao() {
		return anoCartao;
	}

	public void setAnoCartao(String anoCartao) {
		this.anoCartao = anoCartao;
	}

	public String getCodCartao() {
		return codCartao;
	}

	public void setCodCartao(String codCartao) {
		this.codCartao = codCartao;
	}
	
	
	
	
}
