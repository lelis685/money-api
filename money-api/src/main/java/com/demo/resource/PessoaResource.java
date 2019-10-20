package com.demo.resource;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.event.RecursoCriadoEvent;
import com.demo.model.Pessoa;
import com.demo.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		return ResponseEntity.ok(pessoaService.listar());
	}

	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> encontrarPorCodigo(@PathVariable(name = "codigo") Long codigo) {
		return ResponseEntity.ok(pessoaService.buscarPessoaPeloCodigo(codigo));
	}
	

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaService.salvar(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoaSalva));
	}

	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorCodigo(@PathVariable(name = "codigo") Long codigo) {
		pessoaService.deletarPorCodigo(codigo);
	}

	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable(name = "codigo") Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}

	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable(name = "codigo") Long codigo,@RequestBody Map<String, Boolean> ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
