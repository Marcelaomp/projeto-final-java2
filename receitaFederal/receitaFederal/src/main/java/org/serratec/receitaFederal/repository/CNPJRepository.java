package org.serratec.receitaFederal.repository;

import org.serratec.receitaFederal.domain.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CNPJRepository extends JpaRepository<CNPJ,Integer> {
	
	CNPJ findBynumeroCnpj(String numeroCnpj);

}
