package com.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Lancamento;
import com.demo.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	
	@Autowired
	private LancamentoService lancamentoService;
	
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listar(){
		return ResponseEntity.ok(lancamentoService.listar());
	}
	

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarCategoriaPeloCodigo(@PathVariable("codigo") Long codigo){
		return ResponseEntity.ok(lancamentoService.buscarCategoriaPeloCodigo(codigo));
	}
	
	
	
	
	
	
}
