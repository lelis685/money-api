package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Pessoa;

@Repository
public interface PessoaRepository extends  JpaRepository<Pessoa, Long>{

	List<Pessoa> findByNomeContaining(String nome);
	
}
