package org.serratec.receitaFederal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.serratec.receitaFederal.domain.CNPJ;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cnpj")
public class CNPJController {

	@Autowired
	private CNPJRepository cnpjRepository;

	@Autowired
	private CPFRepository cpfRepository;

	@GetMapping("")
	public ResponseEntity<List<CNPJResponseDTO>> getCNPJ(@RequestParam Map<String, String> parametros) {

		List<CNPJ> todos = cnpjRepository.findAll();
		List<CNPJResponseDTO> todosDto = new ArrayList<>();
		
		for (CNPJ pj : todos) {
			todosDto.add(CNPJResponseDTO.fromCNPJ(pj));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}

	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody CNPJRequestDTO dto) {

		CNPJ pj = dto.toCNPJ(cpfRepository);

		cnpjRepository.save(pj);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@PutMapping("/{cnpj}")
	public ResponseEntity<CNPJ> pustCNPJ(@PathVariable String cnpj, @Valid @RequestBody CNPJRequestDTO dto) {

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

	@DeleteMapping("/{cnpj}")

	public ResponseEntity<CNPJ> deleteCNPJ(@PathVariable String cnpj) {

		CNPJ existente = cnpjRepository.findBynumeroCnpj(cnpj);

		if (existente != null) {
			cnpjRepository.delete(existente);

			return ResponseEntity.ok().body(existente);
		}

		return ResponseEntity.notFound().build();

	}

}