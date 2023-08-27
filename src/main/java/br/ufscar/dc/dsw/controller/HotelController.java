package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IHotelService;
import br.ufscar.dc.dsw.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private IHotelService hservice;
	@Autowired
	private ISiteService sservice;
	@Autowired
	private IPromocaoService pservice;
	
	@GetMapping(value={"/index","/"})
	public String index1() {
		return "hotel/index";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value="cidade",required=false) String cidade) {
		List<Hotel> l1;

		List<String> l2 = hservice.buscarCidades();
		if(cidade != null && !cidade.isEmpty()) {
			l1 = hservice.buscarTodosPorCidade(cidade);
		}
		else {
			l1 = hservice.buscarTodos();
		}
		
		model.addAttribute("hoteis",l1);
		model.addAttribute("cidades",l2);
		return "hotel/lista";
	}
	
	@GetMapping("/cadastrarPromocao")
	public String cadastrarPromocao(Promocao promocao, ModelMap model) {
		List<Site> sites = sservice.buscarTodos();
		
		promocao.setCNPJ(getCNPJAtual());
		model.addAttribute("sites",sites);
		model.addAttribute("promocao", promocao);
		return "hotel/cadastroPromocao";
	}
	
	@PostMapping("/salvarPromocao")
	public String salvarSite(@Valid Promocao promocao, BindingResult result, RedirectAttributes attr, ModelMap model) {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print(promocao.getFim());
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print(result.getErrorCount());
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
		
		if (result.getErrorCount() > 0) {
			return cadastrarPromocao(promocao, model);
		}
		
		pservice.salvar(promocao);
		attr.addFlashAttribute("sucess", "promocao.create.sucess");
		return "redirect:/hotel/listarPromocoes";
	}
	
	@GetMapping("/listarPromocoes")
	public String listarPromocoes(ModelMap model) {
		List<Promocao> l1 = pservice.buscarTodosPorHotel(getCNPJAtual());
		
		model.addAttribute("promocoes",l1);
		return "hotel/listaPromocoes";
	}
	
	private String getCNPJAtual() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		String email = a.getName();
		
		return hservice.buscarPorEmail(email).getCNPJ();
	}
	
}