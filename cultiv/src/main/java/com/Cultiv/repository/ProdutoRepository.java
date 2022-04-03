package com.Cultiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cultiv.model.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto,Long>{

}
