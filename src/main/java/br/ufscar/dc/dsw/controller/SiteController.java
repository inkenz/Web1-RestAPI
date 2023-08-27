package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@Controller
@RequestMapping("/site")
public class SiteController {
	
	@Autowired
	private ISiteService sservice;
	@Autowired
	private IPromocaoService pservice;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Site> l1 = sservice.buscarTodos();
		/*
		for(Hotel hotel: l1) {
			System.out.print(hotel);
		}
		*/
		model.addAttribute("sites",l1);
		return "site/lista";
	}
	
	@GetMapping(value={"/index","/"})
	public String index1() {
		return "site/index";
	}
	
	@GetMapping("/listarPromocoes")
	public String listarPromocoes(ModelMap model) {
		List<Promocao> l1 = pservice.buscarTodosPorSite(getURLAtual());
		
		model.addAttribute("promocoes",l1);
		return "site/listaPromocoes";
	}
	
	private String getURLAtual() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		String email = a.getName();
		
		return sservice.buscarPorEmail(email).getURL();
	}
	

}