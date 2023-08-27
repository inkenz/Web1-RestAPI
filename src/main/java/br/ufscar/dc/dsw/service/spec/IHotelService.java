package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Hotel;

public interface IHotelService {
	Hotel buscarPorId(long id);
	
	Hotel buscarPorCNPJ(String cnpj);
	
	Hotel buscarPorEmail(String email);
	
	List<Hotel> buscarTodos();
	
	List<Hotel> buscarTodosPorCidade(String cidade);
	
	List<String> buscarCidades();
	
	void salvar(Hotel hotel);

	void excluir(long id);
	
	boolean hotelTemPromocoes(String cnpj);
}