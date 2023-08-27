package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Site;

@SuppressWarnings("unchecked")
public interface ISiteDAO extends CrudRepository<Site, Long>{
	//@Query("SELECT s FROM Site s WHERE s.URL = :url")
	Site findByURL(String url);
	
	Site findById(long id);
	
	Site findByEmail(String email);
	
	List<Site> findAll();
	
	Site save(Site site);

	void deleteById(Long id);

}