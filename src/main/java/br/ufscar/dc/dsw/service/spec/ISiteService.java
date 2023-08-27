package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Site;

public interface ISiteService {
	
	Site buscarPorId(long id);
	
	Site buscarPorEndereco(String endereco);
	
	Site buscarPorEmail(String email);
	

	List<Site> buscarTodos();

	void salvar(Site site);

	void excluir(long id);
	
	boolean siteTemPromocoes(String endereco);
}