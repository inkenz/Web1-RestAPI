package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IHotelService;
import br.ufscar.dc.dsw.service.spec.ISiteService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IHotelService hservice;
	
	@Autowired
	private ISiteService sservice;
	
	@Autowired
	private IUsuarioService uservice;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value={"/index","/"})
	public String index1() {
		return "admin/index";
	}

	/*
	 * PARTE PARA O HOTEL
	 * */
	
	@GetMapping("/cadastrarHotel")
	public String cadastrar(Hotel hotel, ModelMap model) {
		Hotel h = new Hotel();
		h.setId(null);
		model.addAttribute("hotel", h);
		
		return "admin/cadastroHotel";
	}
	
	
	@PostMapping("/salvarHotel")
	public String salvarHotel(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroHotel";
		}
		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		attr.addFlashAttribute("sucess", "hotel.create.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/editarHotel/{id}")
	public String preEditarH(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("hotel", hservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroHotel";
	}
	
	@PostMapping("/editarHotel")
	public String editarH(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldError("nome") != null || 
				result.getFieldError("cidade") != null ||
				result.getFieldError("senha") != null ||
				hotel.getEmail().isEmpty()
				) {
			return "admin/cadastroHotel";
		}
		
		
		if(uservice.buscarPorEmail(hotel.getEmail()) != null) {
			if(uservice.buscarPorEmail(hotel.getEmail()).getRole().equals("ROLE_HOTEL")) {
				if(hservice.buscarPorEmail(hotel.getEmail()).getId() != hotel.getId())
					return "admin/cadastroHotel";
			
			}
			else {
				return "admin/cadastroHotel";
			}
		}
		
		
		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/excluirHotel/{id}")
	public String excluirH(@PathVariable("id") String id, ModelMap model) {
		hservice.excluir(Long.parseLong(id));
		
		return "redirect:/hotel/listar";
	}
	
	/*
	 * PARTE PARA O SITE
	 * */
	
	@GetMapping("/cadastrarSite")
	public String cadastrarS(Site site, ModelMap model) {
		Site s = new Site();
		s.setId(null);
		model.addAttribute("site", s);
		
		return "admin/cadastroSite";
	}
	
	
	@PostMapping("/salvarSite")
	public String salvarSite(@Valid Site site, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroSite";
		}
		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "site.create.sucess");
		return "redirect:/site/listar";
	}
	
	@GetMapping("/editarSite/{id}")
	public String preEditarS(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("site", sservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroSite";
	}
	
	@PostMapping("/editarSite")
	public String editarS(@Valid Site site, BindingResult result, RedirectAttributes attr) {
		if (result.getFieldError("nome") != null || 
				result.getFieldError("telefone") != null ||
				result.getFieldError("senha") != null ||
				site.getEmail().isEmpty()
				) {
			return "admin/cadastroSite";
		}
		
	
		if(uservice.buscarPorEmail(site.getEmail()) != null) {
			if(uservice.buscarPorEmail(site.getEmail()).getRole().equals("ROLE_SITE")) {
				if(sservice.buscarPorEmail(site.getEmail()).getId() != site.getId())
					return "admin/cadastroSite";
			}
			else {
				return "admin/cadastroSite";
			}
		}
		
		
		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		return "redirect:/site/listar";
	}
	
	@GetMapping("/excluirSite/{id}")
	public String excluirS(@PathVariable("id") String id, ModelMap model) {
		
		sservice.excluir(Long.parseLong(id));
		
		return "redirect:/site/listar";
	}
	
}
