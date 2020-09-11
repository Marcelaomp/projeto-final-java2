package org.serratec.receitaFederal.repository;

import org.serratec.receitaFederal.domain.CPF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CPFRepository extends JpaRepository<CPF,Integer> {

	CPF findBynumeroCpf(String numeroCpf);
}
