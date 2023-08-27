package br.ufscar.dc.dsw.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IPromocaoService;


@Service
@Transactional(readOnly = false)
public class PromocaoService implements IPromocaoService {

	@Autowired
	IPromocaoDAO pdao;

	@Transactional(readOnly = true)
	public Promocao buscarPorId(long id) {
		return pdao.findById(id);
	}


	@Transactional(readOnly = true)
	public List<Promocao> buscarTodosPorHotel(String CNPJ) {
		List<Promocao> lp = new ArrayList<Promocao>();
	
		for(Promocao p: pdao.findAll()) {
			if(p.getCNPJ().equals(CNPJ)) lp.add(p);
		}
		
		return lp;
	}
	
	@Transactional(readOnly = true)
	public List<Promocao> buscarTodos() {
		
		return pdao.findAll();
	}


	@Transactional(readOnly = true)
	public List<Promocao> buscarTodosPorSite(String URL) {
		List<Promocao> lp = new ArrayList<Promocao>();
		
		for(Promocao p: pdao.findAll()) {
			if(p.getURL().equals(URL)) lp.add(p);
		}	
		return lp;
	}

	public void salvar(Promocao promocao) {
		pdao.save(promocao);
	}

	public void excluir(Long id) {
		pdao.deleteById(id);
	}

}