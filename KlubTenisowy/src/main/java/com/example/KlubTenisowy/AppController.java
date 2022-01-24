package com.example.KlubTenisowy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.KlubTenisowy.Pracownic.*;
import com.example.KlubTenisowy.Weryfikacja.AntySQLInjection;
import com.example.KlubTenisowy.Weryfikacja.WeryfikacjaDaneOsobowe;

import ch.qos.logback.classic.Logger;

@Controller
public class AppController {

	@Autowired
	private PracownicyDAO pracownicyDao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {

		return "index";
		
	}
	
	@GetMapping("/usun_pracownika_ostatecznie")
	public String viewUPraPage(@RequestParam(name="pracownik",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pracownik pracownik = pracownicyDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						pracownicyDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/pracownicy?success";
					}else {
						return "redirect:/pracownicy?brak";
					}
					
				}catch(Exception err) {
					return "redirect:/pracownicy?error";
				}
				
				
			}else {
				return "redirect:/pracownicy?error";
			}
		}else {
			
			return "redirect:/pracownicy?error";
		}
	
	}
	
	@GetMapping("/usun_pracownik")
	public String viewDPraPage(@RequestParam(name="pracownik",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pracownik pracownik = pracownicyDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						model.addAttribute("pracownik",pracownik);
						
						return "usun_pracownik";
					}else {
						return "redirect:/pracownicy?brak";
					}
					
				}catch(Exception err) {
					return "redirect:/pracownicy?error";
				}
				
				
			}else {
				return "redirect:/pracownicy?error";
			}
		}else {
			
			return "redirect:/pracownicy?error";
		}
	
	}
	
	@GetMapping("/szczegoly_pracownik")
	public String viewSPraPage(@RequestParam(name="pracownik",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pracownik pracownik = pracownicyDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						model.addAttribute("pracownik",pracownik);
						
						return "szczegoly_pracownik";
					}else {
						model.addAttribute("pracownik",new Pracownik());
						return "redirect:/pracownicy?brak";
					}
					
				}catch(Exception err) {
					model.addAttribute("pracownik",new Pracownik());
					return "redirect:/pracownicy?error";
				}
				
				
			}else {
				model.addAttribute("pracownik",new Pracownik());
				return "redirect:/pracownicy?error";
			}
		}else {
			
			return "pracownicy?error";
		}
	
	}
	
	@GetMapping("/edytuj_pracownik")
	public String viewEPraPage(@RequestParam(name="pracownik",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pracownik pracownik = pracownicyDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						model.addAttribute("pracownik",pracownik);
						
						return "edytuj_pracownik";
					}else {
						model.addAttribute("pracownik",new Pracownik());
						return "redirect:/pracownicy?brak";
					}
					
				}catch(Exception err) {
					model.addAttribute("pracownik",new Pracownik());
					return "redirect:/pracownicy?error";
				}
				
				
			}else {
				model.addAttribute("pracownik",new Pracownik());
				return "redirect:/pracownicy?error";
			}
		}else {
			
			return "pracownicy?error";
		}
	
	}
	
	@GetMapping("/pracownicy")
	public String viewPraPage(@RequestParam(name="search",required=false,defaultValue="") String search,
			@RequestParam(name="tsearch",required=false,defaultValue="") String tsearch,
			@RequestParam(name="bsearch",required=false,defaultValue="") String bsearch,
			@RequestParam(name="isearch",required=false,defaultValue="") String isearch,
			Model model) {
		
		String filtr = "";
		String psearch = search;
		if(!tsearch.equals("")) {
			filtr = "T";
			psearch = tsearch;
		}
		
		if(!bsearch.equals("")) {
			filtr = "B";
			psearch = bsearch;
		}
		
		if(!isearch.equals("")) {
			filtr = "I";
			psearch = isearch;
		}
		
		if(psearch.equals("*")) {psearch = "";}
		
		if(AntySQLInjection.isCorrect(psearch)) {
			List<Pracownik> lista = pracownicyDao.list(psearch,filtr);
			
			if(lista != null) {
				
				for(int n=0;n<lista.size();n++) {
					lista.get(n).setIdAbs(n);
				}
				
				model.addAttribute("lista",lista);
				return "pracownicy";
			}else {
				return "redirect:/pracownicy?nieOdnaleziono="+search;
			}
		}else {
			return "redirect:/pracownicy?zlyCiag";
		}
	}
	
	@RequestMapping("/nowy_pracownik")
	public String viewDPraPage(Model model) {
	
		
		Pracownik pracownik = new Pracownik();
		model.addAttribute("pracownik",pracownik);
		
		return "nowy_pracownik";
		
	}
	
	@RequestMapping("/blad_edytuj_pracownik")
	public String viewBEPraPage(Model model) {
	
		
		return "blad_edytuj_pracownik";
		
	}
	
	@RequestMapping(value="/savePracownik", method = RequestMethod.POST)
	public String savePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
		
		
		if(!AntySQLInjection.isCorrect(pracownik.toString())) return "redirect:/nowy_pracownik?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getImie())) return "redirect:/nowy_pracownik?pusteImie";
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getNazwisko())) return "redirect:/nowy_pracownik?pusteNazwisko";
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getDataUrodzenia())) return "redirect:/nowy_pracownik?pustaData";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(pracownik.getNumerTelefonu())) return "redirect:/nowy_pracownik?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(pracownik.getAdresEmail())) return "redirect:/nowy_pracownik?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujPESEL(pracownik.getPESEL())) return "redirect:/nowy_pracownik?nieprawidlowyPESEL";
		
		Date date = new Date(java.time.LocalDate.now().getYear(),java.time.LocalDate.now().getMonthValue(),java.time.LocalDate.now().getDayOfMonth());
		pracownik.setDataZatrudnienia(date);
		pracownicyDao.save(pracownik);
		
		return "redirect:/pracownicy?success";
	}
	
	@RequestMapping(value="/usunPracownik", method = RequestMethod.POST)
	public String usunPracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
		
		pracownicyDao.delete(pracownik.getIdPracownika());
		
		return "redirect:/pracownicy?success";
	}
	
	@RequestMapping(value="/edytujPracownik", method = RequestMethod.POST)
	public String edytujPracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
		
		if(!AntySQLInjection.isCorrect(pracownik.toString())) return "redirect:/blad_edytuj_pracownik?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getImie())) return "redirect:/blad_edytuj_pracownik?pusteImie";
		if(WeryfikacjaDaneOsobowe.isEmpty(pracownik.getNazwisko())) return "redirect:/blad_edytuj_pracownik?pusteNazwisko";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(pracownik.getNumerTelefonu())) return "redirect:/blad_edytuj_pracownik?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(pracownik.getAdresEmail())) return "redirect:/blad_edytuj_pracownik?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujPESEL(pracownik.getPESEL())) return "redirect:/blad_edytuj_pracownik?nieprawidlowyPESEL";
		
		pracownicyDao.update(pracownik);
		
		return "redirect:/pracownicy?success";
	}
}
