package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.service.spec.ISiteService;
import br.ufscar.dc.dsw.service.spec.IHotelService;

@CrossOrigin
@RestController
public class HotelRestController {
	
	@Autowired
	private IHotelService hservice;
	@Autowired
	private IPromocaoService pservice;
	@Autowired
	private ISiteService sservice;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Hotel hotel, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				hotel.setId(((Integer) id).longValue());
			} else {
				hotel.setId((Long) id);
			}
		}

		if(json.get("nome")!=null)
			hotel.setNome((String) json.get("nome"));
		
		if(json.get("cidade")!=null)
			hotel.setCidade((String) json.get("cidade"));
		
		if(json.get("cnpj")!=null)
			hotel.setCNPJ((String) json.get("cnpj"));
		
		if(json.get("email")!=null)
			hotel.setEmail((String) json.get("email"));
		
		if(json.get("senha")!=null)
			hotel.setSenha((String) json.get("senha"));
	}
	
	@PostMapping(path = "/hoteis")
	@ResponseBody
	public ResponseEntity<Hotel> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Hotel hotel = new Hotel();
				parse(hotel, json);
				hservice.salvar(hotel);
				return ResponseEntity.ok(hotel);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@GetMapping(path = "/hoteis")
	public ResponseEntity<List<Hotel>> lista() {
		List<Hotel> lista = hservice.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/hoteis/{id}")
	public ResponseEntity<Hotel> lista(@PathVariable("id") long id) {
		Hotel hotel= hservice.buscarPorId(id);
		if (hotel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(hotel);
	}
	
	@GetMapping(path = "/hoteis/cidades/{cidade}")
	public ResponseEntity<List<Hotel>> lista(@PathVariable("cidade") String cidade) {
		List<Hotel> lista= hservice.buscarTodosPorCidade(cidade);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping(path = "/hoteis/{id}")
	public ResponseEntity<Hotel> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Hotel hotel = hservice.buscarPorId(id);
				if (hotel == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(hotel, json);
					hservice.salvar(hotel);
					return ResponseEntity.ok(hotel);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/hoteis/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		Hotel hotel = hservice.buscarPorId(id);
		if (hotel == null) {
			return ResponseEntity.notFound().build();
		} else {
			hservice.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping(path = "/promocoes")
	public ResponseEntity<List<Promocao>> listaPromocoes() {
		List<Promocao> lista = pservice.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/promocoes/{id}")
	public ResponseEntity<Promocao> listaPromocoes(@PathVariable("id") long id) {
		Promocao promocao = pservice.buscarPorId(id);
		if (promocao == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(promocao);
	}
	
	@GetMapping(path = "/promocoes/hotel/{id}")
	public ResponseEntity<List<Promocao>> listaPromocoesHotel(@PathVariable("id") long id) {
		Hotel hotel = hservice.buscarPorId(id);
		List<Promocao> lista = pservice.buscarTodosPorHotel(hotel.getCNPJ());
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/promocoes/sites/{id}")
	public ResponseEntity<List<Promocao>> listaPromocoesSite(@PathVariable("id") long id) {
		Site site = sservice.buscarPorId(id);
		List<Promocao> lista = pservice.buscarTodosPorSite(site.getURL());
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

}