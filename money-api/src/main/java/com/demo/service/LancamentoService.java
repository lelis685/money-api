package com.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.dto.LancamentoDto;
import com.demo.model.Categoria;
import com.demo.model.Lancamento;
import com.demo.model.Pessoa;
import com.demo.repository.LancamentoRepository;
import com.demo.repository.filter.LancamentoFilter;
import com.demo.repository.projection.ResumoLancamento;
import com.demo.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CategoriaService categoriaService;


	public Lancamento salvar(Lancamento lancamento) {
		isPessoaInexistenteOuInativaException(lancamento.getPessoa());
		return lancamentoRepository.save(lancamento);
	}
	

	public Lancamento atualizar(Long codigo, LancamentoDto lancamentoDto) {
		
		Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
		
		Categoria categoria = categoriaService.buscarCategoriaPeloCodigo(lancamentoDto.getCategoria());
		Pessoa pessoa = pessoaService.buscarPessoaPeloCodigo(lancamentoDto.getPessoa());
		
		isPessoaInexistenteOuInativaException(pessoa);
	
		BeanUtils.copyProperties(lancamentoDto, lancamentoSalvo, "codigo");
		
		lancamentoSalvo.setCategoria(categoria);
		lancamentoSalvo.setPessoa(pessoa);
	
		return lancamentoRepository.save(lancamentoSalvo);
	}


	public Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}


	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter,pageable);
	}

	public List<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter,pageable);
	}


	public void deletarLancamentoPeloCodigo(Long codigo) {
		buscarLancamentoPeloCodigo(codigo);
		lancamentoRepository.delete(codigo);
	}
	
	
	private void isPessoaInexistenteOuInativaException(Pessoa pessoa) {
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}



}
