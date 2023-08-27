package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorEmail(String email);

	void salvar(Usuario u);

	void excluir(String email);
	
}