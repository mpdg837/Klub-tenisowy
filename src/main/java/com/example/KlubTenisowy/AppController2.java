package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.KlubTenisowy.Biur.BiuraDAO;
import com.example.KlubTenisowy.Biur.Biuro;
import com.example.KlubTenisowy.Klienc.Klienci_grupowiDAO;
import com.example.KlubTenisowy.Klienc.Klienci_indywidualniDAO;
import com.example.KlubTenisowy.Klienc.Klient_grupowy;
import com.example.KlubTenisowy.Klienc.Klient_indywidualny;
import com.example.KlubTenisowy.Pracownic.PracownicyDAO;
import com.example.KlubTenisowy.Wyplaty.WyplatyDAO;

public class AppController2 {
	@Autowired
	private BiuraDAO biuraDao;
	
	@Autowired
	private Klienci_indywidualniDAO Klienci_indywidualniDAO;
	
	@Autowired
	private Klienci_grupowiDAO Klienci_grupowiDAO;
	
	@RequestMapping("/nowe_biuro")
	public String viewNewBiuPage(Model model) {
		Biuro biuro = new Biuro();
		model.addAttribute("biuro",biuro);
		return "nowe_biuro";
		
	}
	
	@RequestMapping(value = "/updateBiuro", method = RequestMethod.POST)
	public String updateBiu(@ModelAttribute("biuro") Biuro biuro) {
		biuraDao.update(biuro);
		return "redirect:/biura";
		
	}
	@GetMapping("/biura")
	public String viewBiuPage(Model model) {
		List<Biuro> listaBiura = biuraDao.list();
		model.addAttribute("listaBiura",listaBiura);
		return "biura";
	}
	
	@GetMapping("/edytuj_biuro")
	public String viewEBiuPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		Biuro biuro = biuraDao.get((int)Integer.parseInt(id));
		model.addAttribute("biuro",biuro);
						
	
		return "edytuj_biuro";
	}
		
	@RequestMapping(value = "/saveBiuro", method = RequestMethod.POST)
	public String saveNewBiu(@ModelAttribute("biuro") Biuro biuro) {
		biuraDao.save(biuro);
		return "redirect:/biura";
		
	}
	@GetMapping("/usun_biuro")
	public String viewDBiuPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		Biuro biuro = biuraDao.get((int)Integer.parseInt(id));
		model.addAttribute("biuro",biuro);
		
		return "usun_biuro";
		
	}
	@GetMapping("/usun_biuro_ostatecznie")
	public String viewDefBiuPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		biuraDao.delete((int)Integer.parseInt(id));
		
		return "biura";
		
	}
	@GetMapping("/klienci_indywidualni")
	public String viewKliPage(Model model) {
		List<Klient_indywidualny> listaKlientow_indywidualnych = Klienci_indywidualniDAO.list2();
		model.addAttribute("listaKlientow_indywidualnych",listaKlientow_indywidualnych);
		
		return "/klienci_indywidualni";
	}
	@GetMapping("/klienci_grupowi")
	public String viewKliGPage(Model model) {
		List<Klient_grupowy> listaKlientow_grupowych = Klienci_grupowiDAO.list2();
		model.addAttribute("listaKlientow_grupowych",listaKlientow_grupowych);
		return "/klienci_grupowi";
	}
	@RequestMapping("/nowy_klient_indywidualny")
	public String viewNewKliPage(Model model) {
		Klient_indywidualny klient_indywidualny = new Klient_indywidualny();
		model.addAttribute("klient_indywidualny",klient_indywidualny);
		return "/nowy_klient_indywidualny";
		
	}
	@RequestMapping(value = "/saveKlient_indywidulany", method = RequestMethod.POST)
	public String saveNewKli(@ModelAttribute("nowy_klient_indywidualny") Klient_indywidualny biuro) {
		Klienci_indywidualniDAO.save(biuro);
		return "redirect:/klienci_indywidualni";
		
	}
	
}
