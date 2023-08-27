package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Promocao;

@SuppressWarnings("unchecked")
public interface IPromocaoDAO extends CrudRepository<Promocao, Long>{

	Promocao findById(long id);
	
	//@Query("SELECT p FROM Promocao p WHERE p.CNPJ = 1")
	Promocao findByCNPJ(String CNPJ);
	
	//@Query("SELECT p FROM Promocao p WHERE p.endereco = 1")
	Promocao findByURL(String URL);
	
	List<Promocao> findAll();
	
	Promocao save(Promocao promocao);

	void deleteById(Long id);
}