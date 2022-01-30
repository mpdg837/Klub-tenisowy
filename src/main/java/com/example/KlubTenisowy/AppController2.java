package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.example.KlubTenisowy.Weryfikacja.AntySQLInjection;
import com.example.KlubTenisowy.Wyplaty.WyplatyDAO;

import com.example.KlubTenisowy.Weryfikacja.*;

@Controller
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
		
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/biura?error";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getUlica())) return "redirect:/biura?pustaUlica";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getMiejscowosc())) return "redirect:/biura?pustaMiejscowosc";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/biura?zlyNumerTelefonu";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/biura?zlyNumerTelefonu";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_budynku())) return "redirect:/biura?zlyNumerBudynku";
		if(biuro.getNumer_mieszkania().length()>0) {
			if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/biura?zlyNumerMieszkania";
		}
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/biura?zlyEmail";
		
		biuraDao.update(biuro);
		return "redirect:/biura?success";
		
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
		

		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowe_biuro?error";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getUlica())) return "redirect:/nowe_biuro?pustaUlica";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getMiejscowosc())) return "redirect:/nowe_biuro?pustaMiejscowosc";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowe_biuro?zlyNumerTelefonu";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowe_biuro?zlyNumerTelefonu";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_budynku())) return "redirect:/nowe_biuro?zlyNumerBudynku";
		if(biuro.getNumer_mieszkania().length()>0) {
			if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowe_biuro?zlyNumerMieszkania";
		}
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowe_biuro?zlyEmail";
		
		biuraDao.save(biuro);
		return "redirect:/biura?success";
		
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
		
		return "redirect:/biura?success";
		
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
	@RequestMapping("/nowy_klient_grupowy")
	public String viewNewKliGPage(Model model) {
		Klient_grupowy klient_grupowy = new Klient_grupowy();
		model.addAttribute("klient_grupowy",klient_grupowy);
		return "/nowy_klient_grupowy";
		
	}
	@RequestMapping(value = "/saveKlient_grupowy", method = RequestMethod.POST)
	public String saveNewKli(@ModelAttribute("nowy_klient_grupowy") Klient_grupowy biuro) {
		
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_klient_grupowy?error";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNazwa())) return "redirect:/nowy_klient_grupowy?brakNazwy";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getMiejscowosc())) return "redirect:/nowy_klient_grupowy?pustaMiejscowosc";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getUlica())) return "redirect:/nowy_klient_grupowy?pustaUlica";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowy_klient_grupowy?zlyEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_klient_grupowy?zlyKodPocztowy";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getNumer_budynku())) return "redirect:/nowy_klient_grupowy?zlyNumerBudynku";
		if(biuro.getNumer_mieszkania().length()>0) {
			if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getNumer_mieszkania())) return "redirect:/nowy_klient_grupowy?zlyNumerMieszkania";
		}
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowy_klient_grupowy?zlyNumerTelefonu";
		if(biuro.getNip().length()!=10) return "redirect:/nowy_klient_grupowy?nieprawidlowyNIP";
		if(biuro.getRegon().length()!=10) return "redirect:/nowy_klient_grupowy?nieprawidlowyRegon";
		
		Klienci_grupowiDAO.save(biuro);
		return "redirect:/klienci_grupowi";
		
	}
	@GetMapping("/edytuj_klienta_grupowego")
	public String viewEKgruPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klient_grupowy klient_grupowy = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
		model.addAttribute("klient_grupowy",klient_grupowy);
						
	
		return "/edytuj_klienta_grupowego";
	}
	@RequestMapping(value = "/updateKlienta_grupowego", method = RequestMethod.POST)
	public String updateKgru(@ModelAttribute("klient_grupowy") Klient_grupowy biuro) {
		
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/klienci_grupowi?error";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNazwa())) return "redirect:/klienci_grupowi?brakNazwy";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getMiejscowosc())) return "redirect:/klienci_grupowi?pustaMiejscowosc";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getUlica())) return "redirect:/klienci_grupowi?pustaUlica";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/klienci_grupowi?zlyEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/klienci_grupowi?zlyKodPocztowy";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getNumer_budynku())) return "redirect:/klienci_grupowi?zlyNumerBudynku";
		if(biuro.getNumer_mieszkania().length()>0) {
			if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getNumer_mieszkania())) return "redirect:/klienci_grupowi?zlyNumerMieszkania";
		}
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/klienci_grupowi?zlyNumerTelefonu";
		if(biuro.getNip().length()!=10) return "redirect:/klienci_grupowi?nieprawidlowyNIP";
		if(biuro.getRegon().length()!=10) return "redirect:/klienci_grupowi?nieprawidlowyRegon";
		
		Klienci_grupowiDAO.update(biuro);
		return "redirect:/klienci_grupowi?success";
		
	}
	@GetMapping("/edytuj_klienta_indywidualnego")
	public String viewEgruPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		Klient_indywidualny klient_indywidualny = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
		model.addAttribute("klient_indywidualny",klient_indywidualny);
						
	
		return "/edytuj_klienta_indywidualnego";
	}
	@RequestMapping(value = "/updateKlienta_indywidualnego", method = RequestMethod.POST)
	public String updateKIgru(@ModelAttribute("klient_indywidualny") Klient_indywidualny biuro) {
		Klienci_indywidualniDAO.update(biuro);
		return "redirect:/klienci_indywidualni";
		
	}
	@GetMapping("/szczegoly_klienta_grupowego")
	public String viewSKGgruPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klient_grupowy klient_grupowy = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
		model.addAttribute("listaKlientow_grupowych",klient_grupowy);
		
	
		return "/szczegoly_klienta_grupowego";
	}
	@GetMapping("/szczegoly_klienta_indywidualnego")
	public String viewSKgruPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		Klient_indywidualny klient_indywidualny = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
		model.addAttribute("listaKlientow_indywidualnych",klient_indywidualny);
						
	
		return "/szczegoly_klienta_indywidualnego";
	}
	@GetMapping("/usun_klienta_indywidualnego")
	public String viewUKliPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		Klient_indywidualny biuro = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
		model.addAttribute("listaKlientow_indywidualnych",biuro);
		
		return "usun_klienta_indywidualnego";
		
	}
	@GetMapping("/usun_klienta_indywidualnego_ostatecznie")
	public String viewDefUkliPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		Klienci_indywidualniDAO.delete((int)Integer.parseInt(id));
		
		return "klienci_indywidualni";
		
	}
	@GetMapping("/usun_klienta_grupowego")
	public String viewUKliGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klient_grupowy biuro = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
		model.addAttribute("klient_grupowy",biuro);
		
		return "usun_klienta_grupowego";
		
	}
	@GetMapping("/usun_klienta_grupowego_ostatecznie")
	public String viewDefUkliGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klienci_grupowiDAO.delete((int)Integer.parseInt(id));
		
		return "klienci_grupowy";
		
	}
}
