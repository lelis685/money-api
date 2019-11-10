package com.demo.repository.filter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.model.Lancamento;
import com.demo.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public List<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
