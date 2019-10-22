package com.demo.repository.filter;

import java.util.List;

import com.demo.model.Lancamento;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
	
}
