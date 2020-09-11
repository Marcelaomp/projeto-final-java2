package org.serratec.receitaFederal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.serratec.receitaFederal.domain.CNPJ;
import org.serratec.receitaFederal.domain.CPF;
import org.serratec.receitaFederal.dto.CNPJRequestDTO;
import org.serratec.receitaFederal.dto.CNPJResponseDTO;
import org.serratec.receitaFederal.repository.CNPJRepository;
import org.serratec.receitaFederal.repository.CPFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cnpj")
public class CNPJController {

	@Autowired
	private CNPJRepository cnpjRepository;

	@Autowired
	private CPFRepository cpfRepository;

	@GetMapping("")
	public ResponseEntity<List<CNPJResponseDTO>> getCNPJ() {

		List<CNPJ> todos = cnpjRepository.findAll();
		List<CNPJResponseDTO> todosDto = new ArrayList<>();

		for (CNPJ pj : todos) {
			todosDto.add(CNPJResponseDTO.fromCNPJ(pj));
		}

		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}

	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody CNPJRequestDTO dto, @RequestHeader("segredo") String segredo) {

		if (segredo.equals("Osegredo")) {
			CNPJ pj = dto.toCNPJ(cpfRepository);

			cnpjRepository.save(pj);

			return new ResponseEntity<>(pj, HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ação não autorizada.");

	}

	@PutMapping("/{cnpj}")
	public ResponseEntity<?> pustCNPJ(@PathVariable String cnpj, @RequestBody CNPJRequestDTO dto,
			@RequestHeader("segredo") String segredo) {

		if (segredo.equals("Osegredo")) {
			CNPJ modificado = dto.toCNPJ(cpfRepository);
			CNPJ existente = cnpjRepository.findBynumeroCnpj(cnpj);

			if (existente != null) {
				existente.setNumeroCnpj(modificado.getNumeroCnpj());
				existente.setDataAbertura(modificado.getDataAbertura());
				existente.setNomeEmpresarial(modificado.getNomeEmpresarial());
				existente.setNomeFantasia(modificado.getNomeFantasia());
				existente.setDescricaoAtividades(modificado.getDescricaoAtividades());
				existente.setEndereco(modificado.getEndereco());
				existente.setContato(modificado.getContato());
				existente.setSituacao(modificado.getSituacao());
				existente.setSocios(modificado.getSocios());

				cnpjRepository.save(existente);

				cnpjRepository.save(existente);
				return ResponseEntity.ok().body(existente);

			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ação não autorizada.");
	}

	@DeleteMapping("/{cnpj}")

	public ResponseEntity<?> deleteCNPJ(@PathVariable String cnpj, @RequestHeader("segredo") String segredo) {

		if (segredo.equals("Osegredo")) {
			CNPJ existente = cnpjRepository.findBynumeroCnpj(cnpj);

			if (existente != null) {
				CNPJResponseDTO deletado = CNPJResponseDTO.fromCNPJ(existente);
				cnpjRepository.delete(existente);
				return ResponseEntity.ok().body(deletado);
			}

			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ação não autorizada.");
	}

	// Delete para deletar socio da lista de socios
	@DeleteMapping("/{cnpj}/{cpf}")
	public ResponseEntity<?> deleteDependente(@PathVariable String cnpj, @PathVariable String cpf,
			@RequestHeader("segredo") String segredo) {
		if (segredo.equals("Osegredo")) {
			CNPJ pj = cnpjRepository.findBynumeroCnpj(cnpj);
			CPF socio = cpfRepository.findBynumeroCpf(cpf);

			if (!pj.getSocios().contains(socio)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sócio não cadastrado na empresa.");
			}

			pj.getSocios().remove(socio);
			cnpjRepository.save(pj);
			return ResponseEntity.status(HttpStatus.OK).body(pj);

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ação não autorizada.");
	}

}