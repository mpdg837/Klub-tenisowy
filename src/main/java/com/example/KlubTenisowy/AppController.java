package com.example.KlubTenisowy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.KlubTenisowy.Biur.BiuraDAO;
import com.example.KlubTenisowy.Biur.Biuro;
import com.example.KlubTenisowy.Pilki.Pilka;
import com.example.KlubTenisowy.Pilki.PilkaDAO;
import com.example.KlubTenisowy.Pracownic.*;
import com.example.KlubTenisowy.Weryfikacja.AntySQLInjection;
import com.example.KlubTenisowy.Weryfikacja.Daty;
import com.example.KlubTenisowy.Weryfikacja.WeryfikacjaDaneOsobowe;
import com.example.KlubTenisowy.Wyplaty.Wyplaty;
import com.example.KlubTenisowy.Wyplaty.WyplatyDAO;
import com.example.KlubTenisowy.Wyplaty.WyplatyPodglad;
import com.example.KlubTenisowy.Wyplaty.WyplatyWyciag;
import com.example.KlubTenisowy.Klienc.Klienci_indywidualniDAO;
import com.example.KlubTenisowy.Klienc.Klient_grupowy;
import com.example.KlubTenisowy.Klienc.Klient_indywidualny;
import com.example.KlubTenisowy.Klienc.Klienci_grupowiDAO;

import ch.qos.logback.classic.Logger;

@Controller
public class AppController {
	
	@Autowired
	private PracownicyDAO pracownicyDao;
	
	@Autowired
	private WyplatyDAO wyplatyDao;
	

	
	
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
						
						wyplatyDao.deletePracownika((int)Integer.parseInt(id));
						pracownicyDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/pracownicy?success";
					}else {
						return "redirect:/pracownicy?brak";
					}
					
				}catch(NumberFormatException err) {
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
					Wyplaty wyp = wyplatyDao.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("wyplata",wyp);
						
						return "usun_pracownik";
					}else {
						return "redirect:/w?brak";
					}
					
				}catch(NumberFormatException err) {
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
					
				}catch(NumberFormatException err) {
					model.addAttribute("pracownik",new Pracownik());
					return "redirect:/pracownicy?error";
				}
				
				
			}else {
				model.addAttribute("pracownik",new Pracownik());
				return "redirect:/pracownicy?error";
			}
		}else {
			
			return "redirect:/pracownicy?error";
		}
	
	}
	@GetMapping("/error")
	public String viewPraPage(Model model) {
		
		return "error";
	}
	
	@GetMapping("/wyplaty")
	public String viewPraPage(@RequestParam(name="dataOd",required=false,defaultValue="") String odDaty,
			@RequestParam(name="dataDo",required=false,defaultValue="") String doDaty,
			Model model) {
		
		if(odDaty.length()>0 && doDaty.length()>0) {
			if(AntySQLInjection.isCorrect( odDaty) && AntySQLInjection.isCorrect(doDaty)) {
				
				if(!Daty.isCorrect(odDaty)) return "redirect:/wyplaty?zlaData";
				if(!Daty.isCorrect(doDaty)) return "redirect:/wyplaty?zlaData";
				
				List<WyplatyPodglad> lista = wyplatyDao.list(odDaty,doDaty);
				
				if(lista == null) {
					lista = new ArrayList<WyplatyPodglad>();
					
				}
				
				
				model.addAttribute("lista",lista);
			}else {
				return "redirect:/wyplaty?zlyCiag";
			}
		}else {
			
			if(odDaty.length()>0 || doDaty.length()>0) {
				 return "redirect:/wyplaty?brakDaty";
			}
			
			List<WyplatyPodglad> lista = wyplatyDao.list("","");
			
			if(lista == null) {
				lista = new ArrayList<WyplatyPodglad>();
				
			}
			
			model.addAttribute("lista",lista);
		}
		
		return "wyplaty";
	}
	
	@GetMapping("/dodaj_wyplate")
	public String viewDWyPage(@RequestParam(name="id",required=false,defaultValue="") String id,Model model) {
	
		
		Wyplaty wyplata = new Wyplaty();
		if(id.length()>0) {
			try {
				
				wyplata.setIdPracownika(Integer.parseInt(id));
			}catch(Exception err) {
				return "redirect:/dodaj_wyplate?nieMaPracownika";
			}
		}
		
		List<Pracownik> prac = pracownicyDao.list("", "");
		
		model.addAttribute("wyplata",wyplata);
		model.addAttribute("pracownicy",prac);
		
		return "dodaj_wyplate";
		
	}
	
	@RequestMapping(value="/saveWyplata", method = RequestMethod.POST)
	public String saveWyplata(@ModelAttribute("wyplata") Wyplaty wyplata) {
		
		if(!AntySQLInjection.isCorrect(wyplata.toString())) return "redirect:/nowy_pracownik?niedozwoloneZnaki";
		
		Pracownik prac = pracownicyDao.get(wyplata.getIdPracownika());
		
		if(prac == null) return "redirect:/dodaj_wyplate?nieMaPracownika";
		if(wyplata.getStawkaPodstawowa() <= 0) return "redirect:/dodaj_wyplate?podanoNiedodatniaStawkePodstawowa";
		if(wyplata.getPremia() < 0) return "redirect:/dodaj_wyplate?podanoNiedodatniaPremie";
		if(wyplata.getDodatekOkolicznosciowy() < 0) return "redirect:/dodaj_wyplate?podanoNiedodatniDodatek";
		
		
		wyplata.setDataWyplaty(Daty.getActData());
		
		wyplatyDao.save(wyplata);
		
		return "redirect:/wyplaty?success";
	}
	
	
	@GetMapping("/usun_wyplate")
	public String viewUWypPage(@RequestParam(name="wyplata",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Wyplaty wyp = wyplatyDao.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("wyplata",wyp);
						
						return "usun_wyplate";
					}else {
						return "redirect:/usun_wyplate?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/usun_wyplate?error";
				}
				
				
			}else {
				return "redirect:/usun_wyplate?error";
			}
		}else {
			
			return "redirect:/usun_wyplate?error";
		}
	
	}
	
	@GetMapping("/usun_wyplate_ostatecznie")
	public String viewUWypOPage(@RequestParam(name="wyplata",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Wyplaty wyplata = wyplatyDao.get((int)Integer.parseInt(id));
				
					if(wyplata != null) {
						
						wyplatyDao.delete((int)Integer.parseInt(id));
					
						
						return "redirect:/wyplaty?success";
					}else {
						return "redirect:/wyplaty?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/wyplaty?error";
				}
				
				
			}else {
				return "redirect:/wyplaty?error";
			}
		}else {
			
			return "redirect:/wyplaty?error";
		}
	
	}
	
	@GetMapping("/szczegoly_wyplata")
	public String viewSWypPage(@RequestParam(name="wyplata",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					WyplatyWyciag wyplata = wyplatyDao.getS((int)Integer.parseInt(id));
				
					if(wyplata != null) {
						model.addAttribute("wyplata",wyplata);
						
						return "szczegoly_wyplata";
					}else {
						model.addAttribute("pracownik",new Pracownik());
						return "redirect:/wyplaty?brak";
					}
					
				}catch(NumberFormatException err) {
					model.addAttribute("pracownik",new Pracownik());
					return "redirect:/wyplaty?error";
				}
				
				
			}else {
				model.addAttribute("pracownik",new Pracownik());
				return "redirect:/wyplaty?error";
			}
		}else {
			
			return "wyplaty?error";
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
		
		
		 
		pracownik.setDataZatrudnienia(Daty.getActData());
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
	
	// Pilki
	
	@Autowired
	private PilkaDAO pilkiDao;
	
	@GetMapping("/pilki")
	public String viewPilkiPage(@RequestParam(name="search",required=false,defaultValue="") String search,
			Model model) {
		
			if(AntySQLInjection.isCorrect(search)) {
				List<Pilka> lista = pilkiDao.list(search);
				
				if(lista == null) {
					lista = new ArrayList<Pilka>();
					
				}
				
				int n=0;
				for(Pilka pilka : lista) {
					
					pilka.setIndex(n);
					
					n++;
				}
				
				
				model.addAttribute("lista",lista);
			}else {
				return "redirect:/pilki?niedozwoloneZnaki";
			}
	
		return "pilki";
	}
	
	@GetMapping("/nowa_pilka")
	public String viewDWyPage(Model model) {
	
		
		Pilka pilka = new Pilka();
		
		model.addAttribute("pilka",pilka);

		return "nowa_pilka";
		
	}
	
	@RequestMapping(value="/savePilka", method = RequestMethod.POST)
	public String savePilka(@ModelAttribute("pilka") Pilka pilka) {
		
		
		if(!AntySQLInjection.isCorrect(pilka.toString())) return "redirect:/nowa_pilka?niedozwoloneZnaki";
		if(WeryfikacjaDaneOsobowe.isEmpty(pilka.getNazwa())) return "redirect:/nowa_pilka?pustaNazwa";
		if(pilka.getMasa() <= 0) return "redirect:/nowa_pilka?podanoNiedodatniaMase";
		if(pilka.getSrednica() <= 0) return "redirect:/nowa_pilka?podanoNiedodatniaSrednice";
		
		pilkiDao.save(pilka);
		
		return "redirect:/pilki?success";
	}
	
	@GetMapping("/usun_pilke")
	public String viewDPilPage(@RequestParam(name="pilka",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pilka wyp = pilkiDao.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("pilka",wyp);
						
						return "usun_pilke";
					}else {
						return "redirect:/pilki?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/pilki?error";
				}
				
				
			}else {
				return "redirect:/pilki?error";
			}
		}else {
			
			return "redirect:/pilki?error";
		}
	
	}
	
	@GetMapping("/usun_pilke_ostatecznie")
	public String viewWWPIPage(@RequestParam(name="pilka",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pilka pracownik = pilkiDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						pilkiDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/pilki?success";
					}else {
						return "redirect:/pilki?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/pilki?error";
				}
				
				
			}else {
				return "redirect:/pilki?error";
			}
		}else {
			
			return "redirect:/pilki?error";
		}
	
	}
	
	@GetMapping("/edytuj_pilke")
	public String viewPPraPage(@RequestParam(name="pilka",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Pilka pracownik = pilkiDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						model.addAttribute("pilka",pracownik);
						
						return "edytuj_pilke";
					}else {
						model.addAttribute("pilka",new Pracownik());
						return "redirect:/pilki?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/pilki?error";
				}
				
				
			}else {
				return "redirect:/pilki?error";
			}
		}else {
			return "redirect:/pilki?error";
		}
	
	}
	
	@RequestMapping(value="/edytujPilka", method = RequestMethod.POST)
	public String edtyujPilka(@ModelAttribute("pilka") Pilka pilka) {
		
		
		if(!AntySQLInjection.isCorrect(pilka.toString())) return "redirect:/pilki?niedozwoloneZnaki";
		if(WeryfikacjaDaneOsobowe.isEmpty(pilka.getNazwa())) return "redirect:/pilki?pustaNazwa";
		if(pilka.getMasa() <= 0) return "redirect:/pilki?podanoNiedodatniaMase";
		if(pilka.getSrednica() <= 0) return "redirect:/pilki?podanoNiedodatniaSrednice";
		
		pilkiDao.update(pilka);
		
		return "redirect:/pilki?success";
	}
	
}
