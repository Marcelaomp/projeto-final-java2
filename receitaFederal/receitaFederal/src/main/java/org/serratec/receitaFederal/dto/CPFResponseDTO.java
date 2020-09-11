package org.serratec.receitaFederal.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.receitaFederal.domain.CPF;
import org.serratec.receitaFederal.enums.Sexo;

public class CPFResponseDTO {

	@NotNull
	@Column(unique = true)
	private String numeroCpf;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String dataNascimento;
	
	@NotNull
	private Sexo sexo;
	
	@NotNull
	private String naturalidade;
	
	@NotNull
	@Size(min = 2, max = 2)
	private String uf;
	
	@NotNull
	@Column(unique = true)
	private String tituloEleitor;
	
	@NotNull
	private String nomeMae;
	
	@NotNull
	@Column(unique = true)
	private String documentoRG; //tabela
	
	@NotNull
	private String endereco; //tabela
	
	@NotNull
	private String contato; //tabela
	
	public static CPFResponseDTO fromCPF(CPF cpf) {
		
		CPFResponseDTO dto = new CPFResponseDTO();
		dto.setNumeroCpf(cpf.getNumeroCpf());
		dto.setNome(cpf.getNome());
		dto.setDataNascimento(cpf.getDataNascimento());
		dto.setSexo(cpf.getSexo());
		dto.setNaturalidade(cpf.getNaturalidade());
		dto.setUf(cpf.getUf());
		dto.setTituloEleitor(cpf.getTituloEleitor());
		dto.setNomeMae(cpf.getNomeMae());
		dto.setDocumentoRG(cpf.getDocumentoRG());
		dto.setEndereco(cpf.getEndereco());
		dto.setContato(cpf.getContato());
		
		return dto;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getDocumentoRG() {
		return documentoRG;
	}

	public void setDocumentoRG(String documentoRG) {
		this.documentoRG = documentoRG;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
}