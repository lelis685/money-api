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
import com.demo.model.Categoria;
import com.demo.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	public List<Categoria> listar() {
		return categoriaService.listar();
	}

	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> encontrarPorCodigo(@PathVariable(name = "codigo") Long codigo) {
		return ResponseEntity.ok(categoriaService.buscarCategoriaPeloCodigo(codigo));
	}

	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.salvar(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deletarPorCodigo(@PathVariable(name = "codigo") Long codigo) {
		categoriaService.deletarPorCodigo(codigo);
	}
	
	
	@PutMapping("/{codigo}/nome")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorCodigo(@PathVariable(name = "codigo") Long codigo, @RequestBody Map<String, String> nome) {
		categoriaService.atualizarPropriedadeNome(codigo, nome);
	}
	
	

}
