package com.Cultiv.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "produto")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
		public Produto() {
			super();
		}
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		private String descricao;
		
		private double preco;
		
		private String grandeza;	
		
		private String marca;
		
		private String categoria;
		
		private double quantidadeEstoque = 0.0;
		
		private String nomeImagens;
		
		

		public String getNomeImagens() {
			return nomeImagens;
		}

		public void setNomeImagens(String nomeImagens) {
			this.nomeImagens = nomeImagens;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public double getPreco() {
			return preco;
		}

		public void setPreco(double preco) {
			this.preco = preco;
		}

		public String getGrandeza() {
			return grandeza;
		}

		public void setGrandeza(String grandeza) {
			this.grandeza = grandeza;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public double getQuantidadeEstoque() {
			return quantidadeEstoque;
		}

		public void setQuantidadeEstoque(double quantidadeEstoque) {
			this.quantidadeEstoque = quantidadeEstoque;
		}
		
		
		
		

		
}
