package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.demo.model.Lancamento;
import com.demo.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}


	public Lancamento buscarCategoriaPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findById(codigo).orElse(null);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}
	
	

	
}
