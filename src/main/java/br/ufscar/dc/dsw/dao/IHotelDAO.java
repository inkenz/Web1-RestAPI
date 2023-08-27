package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Hotel;

@SuppressWarnings("unchecked")
public interface IHotelDAO extends CrudRepository<Hotel, Long>{
	//@Query("SELECT h FROM Hotel h WHERE h.CNPJ = :cnpj")
	Hotel findByCNPJ(String CNPJ);
	
	Hotel findById(long id);
	
	List<Hotel> findAll();
	
	List<Hotel> findAllByCidade(String cidade);
	
	Hotel save(Hotel hotel);

	void deleteByCNPJ(String CNPJ);
}