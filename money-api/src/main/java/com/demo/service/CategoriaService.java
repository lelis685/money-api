package com.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.demo.model.Categoria;
import com.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	public Categoria salvar(Categoria Categoria) {
		return categoriaRepository.save(Categoria);
	}

	public void deletarPorCodigo(Long codigo) {
		buscarCategoriaPeloCodigo(codigo);
		categoriaRepository.delete(codigo);
	}

	public Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		if (categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}

	public void atualizarPropriedadeNome(Long codigo, Map<String, String> nome) {
		Categoria categoriaSalva = buscarCategoriaPeloCodigo(codigo);
		categoriaSalva.setNome(nome.get("nome"));
		categoriaRepository.save(categoriaSalva);
	}

}
