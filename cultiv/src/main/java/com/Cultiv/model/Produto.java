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


@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long idProduto;
		
		private String nomeProduto;
		
		private double precoProduto;
		
		private double pesoProduto;		
		
		@ManyToOne
		private Carrinho carrinho;
			
		
		//getters e setters
		public long getIdProduto() {
			return idProduto;
		}

		public void setIdProduto(long idProduto) {
			this.idProduto = idProduto;
		}

		public String getNomeProduto() {
			return nomeProduto;
		}

		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto;
		}

		public double getPrecoProduto() {
			return precoProduto;
		}

		public void setPrecoProduto(double precoProduto) {
			this.precoProduto = precoProduto;
		}

		public double getPesoProduto() {
			return pesoProduto;
		}

		public void setPesoProduto(double pesoProduto) {
			this.pesoProduto = pesoProduto;
		}


		
}
