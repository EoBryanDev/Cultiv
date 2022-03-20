package com.Cultiv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Cultiv.model.Carrinho;


public interface CarrinhoRepository extends CrudRepository<Carrinho, Long> {
	
	Carrinho findByIdCarrinho(long id);
	
	
}