package org.serratec.receitaFederal.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.receitaFederal.enums.SituacaoCadastral;

@Entity
public class CNPJ implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true)
	@Size(min = 14, max = 14)
	private String numeroCnpj;
	
	@NotNull
	@NotBlank
	private String dataAbertura; //pode formatar p/ LocalDate receber tudo sem "/" e botar
	
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String nomeEmpresarial;
	
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String nomeFantasia;
	
	@NotNull
	@NotBlank
	private String descricaoAtividades;
	
	@ManyToMany
    @JoinTable (
                name = "cnpj_cpf",
                joinColumns = @JoinColumn(name = "cnpj_id", referencedColumnName =  "id"),
                inverseJoinColumns = @JoinColumn(name = "cpf_id", referencedColumnName = "id")
                )
    private Set <CPF> socios = new HashSet<>();
	
	@NotNull
	@NotBlank
	private String endereco; //possível tabela
	
	@NotNull
	@NotBlank
	private String contato; //possível tabela
	
	
	@NotNull
	private SituacaoCadastral situacao;
	
	public CNPJ () {
		
	}
	
	public CNPJ(@NotNull String dataAbertura, @NotNull @Size(min = 14, max = 14) String numeroCnpj,
			@NotNull String nomeEmpresarial, @NotNull String nomeFantasia, @NotNull String descricaoAtividades,
			@NotNull Set<CPF> socios, @NotNull String endereco, @NotNull String contato,
			@NotNull SituacaoCadastral situacao) {
		super();
		this.dataAbertura = dataAbertura;
		this.numeroCnpj = numeroCnpj;
		this.nomeEmpresarial = nomeEmpresarial;
		this.nomeFantasia = nomeFantasia;
		this.descricaoAtividades = descricaoAtividades;
		this.socios = socios;
		this.endereco = endereco;
		this.contato = contato;
		this.situacao = situacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getNumeroCnpj() {
		return numeroCnpj;
	}

	public void setNumeroCnpj(String numeroCnpj) {
		this.numeroCnpj = numeroCnpj;
	}

	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getDescricaoAtividades() {
		return descricaoAtividades;
	}

	public void setDescricaoAtividades(String descricaoAtividades) {
		this.descricaoAtividades = descricaoAtividades;
	}

	public Set<CPF> getSocios() {
		return socios;
	}

	public void setSocios(Set<CPF> socios) {
		this.socios = socios;
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

	public SituacaoCadastral getSituacao() {
		if(this.socios.isEmpty()) {
			 return SituacaoCadastral.INATIVA;
		}
		return situacao;
	}

	public void setSituacao(SituacaoCadastral situacao) {
		this.situacao = situacao;
	}

	public CNPJ orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
