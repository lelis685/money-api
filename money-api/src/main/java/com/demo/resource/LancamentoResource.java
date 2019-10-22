package com.demo.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.event.RecursoCriadoEvent;
import com.demo.model.Lancamento;
import com.demo.repository.filter.LancamentoFilter;
import com.demo.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {


	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	public ResponseEntity<List<Lancamento>> pesquisar(LancamentoFilter filter){
		return ResponseEntity.ok(lancamentoService.filtrar(filter));
	}
	

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarCategoriaPeloCodigo(@PathVariable("codigo") Long codigo){
		return ResponseEntity.ok(lancamentoService.buscarCategoriaPeloCodigo(codigo));
	}

	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}



}
