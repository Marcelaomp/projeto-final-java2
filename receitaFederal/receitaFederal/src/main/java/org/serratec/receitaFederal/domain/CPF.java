package org.serratec.receitaFederal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.serratec.receitaFederal.enums.Sexo;

@Entity
public class CPF {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true)
	@Size(min = 11, max = 11)
	private String numeroCpf;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Past
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
	@Size(min = 12, max = 12)
	private String tituloEleitor;
	
	@NotNull
	private String nomeMae;
	
	@NotNull
	@Column(unique = true)
	private String documentoRG; 
	
	@NotNull
	private String endereco; //possível tabela
	
	@NotNull
	private String contato; //possível tabela

	public CPF() {
		
	}
	
	public CPF(@NotNull String numeroCpf, @NotNull String nome, @NotNull String dataNascimento,
			@NotNull Sexo sexo, @NotNull String naturalidade, @NotNull String uf, @NotNull String tituloEleitor,
			@NotNull String nomeMae, @NotNull String documentoRG, @NotNull String endereco, @NotNull String contato) {
		super();
		this.numeroCpf = numeroCpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.naturalidade = naturalidade;
		this.uf = uf;
		this.tituloEleitor = tituloEleitor;
		this.nomeMae = nomeMae;
		this.documentoRG = documentoRG;
		this.endereco = endereco;
		this.contato = contato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
