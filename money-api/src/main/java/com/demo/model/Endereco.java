package com.demo.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@NotNull
	@Size(max=50 ,min=3)
	private String logradouro;
	
	@NotNull
	@Size(max=50 ,min=3)
	private String numero;

	@Size(max=50 ,min=3)
	private String complemento;
	
	@NotNull
	@Size(max=50 ,min=3)
	private String bairro;
	
	@NotNull
	@Size(max=50 ,min=3)
	private String cep;
	
	@NotNull
	@Size(max=50 ,min=3)
	private String cidade;
	
	@NotNull
	@Size(max=50 ,min=3)
	private String estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
