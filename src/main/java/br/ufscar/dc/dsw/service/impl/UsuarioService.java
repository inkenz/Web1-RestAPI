package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;

import br.ufscar.dc.dsw.service.spec.IUsuarioService;


@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

	@Autowired
	IUsuarioDAO udao;

	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		return udao.findByEmail(email);
	}

	@Override
	public void salvar(Usuario u) {
		udao.save(u);
	}

	@Override
	public void excluir(String email) {
		udao.deleteByEmail(email);
	}



}