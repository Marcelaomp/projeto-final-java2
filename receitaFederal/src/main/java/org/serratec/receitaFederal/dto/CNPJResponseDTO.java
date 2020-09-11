package org.serratec.receitaFederal.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.receitaFederal.domain.CNPJ;
import org.serratec.receitaFederal.domain.CPF;
import org.serratec.receitaFederal.enums.SituacaoCadastral;

public class CNPJResponseDTO {
	
	@NotNull
	@Column(unique = true)
	@Size(min = 14, max = 14)
	private String numeroCnpj;
	
	@NotNull
//	@Size(min = 8, max = 8) formatar data receber tudo sem / e botar
	private String dataAbertura;
	
	@NotNull
	@Column(unique = true)
	private String nomeEmpresarial;
	
	@NotNull
	@Column(unique = true)
	private String nomeFantasia;
	
	@NotNull
	private String descricaoAtividades;
	
	@NotNull
	private Set<String> cpfSocios;
	
	@NotNull
	private String endereco; //tabela
	
	@NotNull
	private String contato; //tabela
	
	@NotNull
	private SituacaoCadastral situacao;

	public static CNPJResponseDTO fromCNPJ(CNPJ cnpj) {
		
		CNPJResponseDTO dto = new CNPJResponseDTO();
		dto.setNumeroCnpj(cnpj.getNumeroCnpj());
		dto.setDataAbertura(cnpj.getDataAbertura());
		dto.setNomeEmpresarial(cnpj.getNomeEmpresarial());
		dto.setNomeFantasia(cnpj.getNomeFantasia());
		dto.setDescricaoAtividades(cnpj.getDescricaoAtividades());
		dto.setEndereco(cnpj.getEndereco());
		dto.setContato(cnpj.getContato());
		dto.setSituacao(cnpj.getSituacao());
		
		dto.cpfSocios = new HashSet<>();
		
		for (CPF cpf : cnpj.getSocios()) {
			dto.getCpfSocios().add(cpf.getNome());
		}
		
		return dto;
	}

	public String getNumeroCnpj() {
		return numeroCnpj;
	}

	public void setNumeroCnpj(String numeroCnpj) {
		this.numeroCnpj = numeroCnpj;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
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

	public Set<String> getCpfSocios() {
		return cpfSocios;
	}

	public void setCpfSocios(Set<String> cpfSocios) {
		this.cpfSocios = cpfSocios;
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
		return situacao;
	}

	public void setSituacao(SituacaoCadastral situacao) {
		this.situacao = situacao;
	}
	
}