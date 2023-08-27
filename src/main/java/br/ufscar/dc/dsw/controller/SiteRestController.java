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
import br.ufscar.dc.dsw.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@CrossOrigin
@RestController
public class SiteRestController {
	
	@Autowired
	private ISiteService sservice;
	@Autowired
	private IPromocaoService pservice;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Site site, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				site.setId(((Integer) id).longValue());
			} else {
				site.setId((Long) id);
			}
		}

		site.setNome((String) json.get("nome"));
	}
	
	//Parte do Rest
	@GetMapping(path = "/sites")
	public ResponseEntity<List<Site>> lista() {
		List<Site> lista = sservice.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/sites/{id}")
	public ResponseEntity<Site> lista(@PathVariable("id") long id) {
		Site site = sservice.buscarPorId(id);
		if (site == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(site);
	}

	
	@PostMapping(path = "/sites")
	@ResponseBody
	public ResponseEntity<Site> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Site site = new Site();
				parse(site, json);
				sservice.salvar(site);
				return ResponseEntity.ok(site);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/cidades/{id}/")
	public ResponseEntity<Site> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Site site = sservice.buscarPorId(id);
				if (site == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(site, json);
					sservice.salvar(site);
					return ResponseEntity.ok(site);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/sites/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Site site = sservice.buscarPorId(id);
		if (site == null) {
			return ResponseEntity.notFound().build();
		} else {
			sservice.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}