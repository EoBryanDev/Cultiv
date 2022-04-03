package com.Cultiv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartaoCredito  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// ok		
	
	private String tipoCartao;
	
	private String numCartao; // ok
	
	private String titularCartao; // ok
	
	private String mes;
	
	private String ano;
	
	private String codCard;
	
	//private Usuario user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public String getTitularCartao() {
		return titularCartao;
	}

	public void setTitularCartao(String titularCartao) {
		this.titularCartao = titularCartao;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getCodCard() {
		return codCard;
	}

	public void setCodCard(String codCard) {
		this.codCard = codCard;
	}
	/*
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	*/
	
	
	
}