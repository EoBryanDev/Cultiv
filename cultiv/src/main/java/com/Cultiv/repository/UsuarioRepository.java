package com.Cultiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.Cultiv.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findById(long id);
	Usuario findByNome(String nome);
	
	@Query(value = "select * from usuario where email = :email and senha = :senha", nativeQuery = true)
	public Usuario Login(String email, String senha);
}
