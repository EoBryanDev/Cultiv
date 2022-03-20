package com.Cultiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Cultiv.model.Carrinho;
import com.Cultiv.model.Produto;
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	//Iterable<Produto> findByCarrinho<Carrinho carrinho> ;
	Produto findByNomeProduto(String nomeProduto);
	Produto findByIdProduto(int idProduto);

}


// um carrinho tem varios produtos carrinho 1 - * OneToMany
// um produto está em um carrinho ManyToOne