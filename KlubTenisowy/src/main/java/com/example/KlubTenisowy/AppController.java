package com.example.KlubTenisowy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.KlubTenisowy.Pracownic.*;

@Controller
public class AppController {
	
	@Autowired
	private AdresyDAO adresyDao;
	@Autowired
	private PracownicyDAO pracownicyDao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {

		List<Adresy> lista = adresyDao.list();
		model.addAttribute("lista",lista);
		return "index";
		
	}
	
	@RequestMapping("/pracownicy")
	public String viewPraPage(Model model) {

		List<PracownikPod> lista = pracownicyDao.listPod();
		model.addAttribute("lista",lista);
		return "pracownicy";
		
	}
	
	@RequestMapping("/nowy_pracownik")
	public String viewDPraPage(Model model) {
		
		DodajPracownika pracownik = new DodajPracownika();
		model.addAttribute("pracownik",pracownik);
		
		return "nowy_pracownik";
		
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("pracownik") DodajPracownika pracownik) {
		
		
		
		return "redirect:/";
	}
}
