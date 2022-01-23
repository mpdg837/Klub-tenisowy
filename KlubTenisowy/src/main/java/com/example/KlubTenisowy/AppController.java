package com.example.KlubTenisowy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.KlubTenisowy.Pracownic.*;
import com.example.KlubTenisowy.Weryfikacja.WeryfikacjaDaneOsobowe;

@Controller
public class AppController {
	@Autowired
	private OsobyDAO osobyDao;
	@Autowired
	private DaneKontaktoweDAO daneDao;
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
	
	@RequestMapping(value="/savePracownik", method = RequestMethod.POST)
	public String savePracownik(@ModelAttribute("pracownik") DodajPracownika pracownik) {
		
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getImie())) return "redirect:/nowy_pracownik/?pusteImie";
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getNazwisko())) return "redirect:/nowy_pracownik/?pusteNazwisko";
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getDataUrodzenia())) return "redirect:/nowy_pracownik/?pustaData";

		if(!WeryfikacjaDaneOsobowe.weryfikujPESEL(pracownik.getPESEL())) return "redirect:/nowy_pracownik/?nieprawidlowyPESEL";

		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(pracownik.getAdresEmail())) return "redirect:/nowy_pracownik/?nieprawidlowyEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(pracownik.getNumerTelefonu())) return "redirect:/nowy_pracownik/?nieprawidlowyNumerTelefonu";		
		
		
		int osoba = osobyDao.save(new Osoby(0,pracownik.getImie(),pracownik.getNazwisko(),pracownik.getDrugieImie(),pracownik.getPlec(),pracownik.getDataUrodzenia()));
		int dane = daneDao.save(new DaneKontatkowe(0,pracownik.getNumerTelefonu(),pracownik.getAdresEmail()));
		
		Date date = Calendar.getInstance().getTime();  

		return "redirect:/pracownicy/?"+osoba+"="+dane + "oba";
	}
}
