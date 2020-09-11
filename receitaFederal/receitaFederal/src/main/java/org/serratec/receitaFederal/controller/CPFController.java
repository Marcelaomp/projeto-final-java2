package org.serratec.receitaFederal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.serratec.receitaFederal.domain.CPF;
import org.serratec.receitaFederal.dto.CPFResponseDTO;
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
@RequestMapping("/cpf")
public class CPFController {

	@Autowired
	private CPFRepository cpfRepository;

	@GetMapping("")
	public ResponseEntity<List<CPFResponseDTO>> getCPF(@RequestParam Map<String, String> parametros) {

		List<CPF> todos = (List<CPF>) cpfRepository.findAll();
		List<CPFResponseDTO> todosDto = new ArrayList<>();
		
		for (CPF pf : todos) {
			todosDto.add(CPFResponseDTO.fromCPF(pf));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}

	@PostMapping
	public ResponseEntity<CPF> postCPF(@Valid @RequestBody CPF novo) {

		CPF existente = cpfRepository.findBynumeroCpf(novo.getNumeroCpf());

		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		}
		cpfRepository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);

	}

	@PutMapping("/{cpf}")
	public ResponseEntity<CPF> pustCPF(@PathVariable String cpf, @Valid @RequestBody CPF modificado) {

		CPF existente = cpfRepository.findBynumeroCpf(cpf);

		if (existente != null) {
			existente.setNumeroCpf(modificado.getNumeroCpf());
			existente.setNome(modificado.getNome());
			existente.setDataNascimento(modificado.getDataNascimento());
			existente.setSexo(modificado.getSexo());
			existente.setNaturalidade(modificado.getNaturalidade());
			existente.setUf(modificado.getUf());
			existente.setTituloEleitor(modificado.getTituloEleitor());
			existente.setNomeMae(modificado.getNomeMae());
			existente.setDocumentoRG(modificado.getDocumentoRG());
			existente.setContato(modificado.getContato());
			existente.setEndereco(modificado.getEndereco());
			
			cpfRepository.save(existente);

			cpfRepository.save(existente);
			return ResponseEntity.ok().body(existente);

		}
		return new ResponseEntity<>(existente, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{cpf}")

	public ResponseEntity<CPF> deleteCPF(@PathVariable String cpf) {

		CPF existente = cpfRepository.findBynumeroCpf(cpf);

		if (existente != null) {
			cpfRepository.delete(existente);

			return ResponseEntity.ok().body(existente);
		}

		return ResponseEntity.notFound().build();

	}

}