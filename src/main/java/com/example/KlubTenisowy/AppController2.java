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
import com.example.KlubTenisowy.Kor.Kort;
import com.example.KlubTenisowy.Kor.KortyDAO;
import com.example.KlubTenisowy.Pracownic.PracownicyDAO;
import com.example.KlubTenisowy.Pracownic.Pracownik;
import com.example.KlubTenisowy.Rezerw.Rezerwacja;
import com.example.KlubTenisowy.Rezerw.RezerwacjeDAO;
import com.example.KlubTenisowy.Weryfikacja.AntySQLInjection;
import com.example.KlubTenisowy.Weryfikacja.WeryfikacjaDaneOsobowe;
import com.example.KlubTenisowy.Wyplaty.WyplatyDAO;

@Controller
public class AppController2 {
	@Autowired
	private BiuraDAO biuraDao;
	
	@Autowired
	private Klienci_indywidualniDAO Klienci_indywidualniDAO;
	
	@Autowired
	private Klienci_grupowiDAO Klienci_grupowiDAO;
	@Autowired
	private KortyDAO kortyDao;
	
	@Autowired
	private RezerwacjeDAO rezerwacjeDao;
	
	@RequestMapping("/nowe_biuro")
	public String viewNewBiuPage(Model model) {
		Biuro biuro = new Biuro();
		model.addAttribute("biuro",biuro);
		return "nowe_biuro";
		
	}
	
	@RequestMapping(value = "/updateBiuro", method = RequestMethod.POST)
	public String updateBiu(@ModelAttribute("biuro") Biuro biuro) {
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_kort?niedozwoloneZnaki";
		
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowe_biuro?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowe_biuro?nieprawidlowyKodPocztowy";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_budynku())) return "redirect:/nowe_biuro?nieprawidlowyNumerBudynku";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getNumer_mieszkania())) return "redirect:/nowe_biuro?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujPESEL(biuro.getKod_pocztowy())) return "redirect:/nowe_biuro?nieprawidlowyKodPocztowy";
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
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Biuro biuro = biuraDao.get((int)Integer.parseInt(id));
				
					if(biuro != null) {
						
						model.addAttribute("biuro",biuro);
						
						return "edytuj_biuro";
					}else {
						model.addAttribute("biuro",new Biuro());
						return "redirect:/biuro?brak";
					}
					
				}catch(NumberFormatException err) {
					model.addAttribute("biuro",new Biuro());
					return "redirect:/biuro?error";
				}
				
				
			}else {
				model.addAttribute("biuro",new Biuro());
				return "redirect:/biuro?error";
			}
		}else {
			
			return "redirect:/biuro?error";
		}
	
	}
		
	@RequestMapping(value = "/saveBiuro", method = RequestMethod.POST)
	public String saveNewBiu(@ModelAttribute("biuro") Biuro biuro) {
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowe_biuro?niedozwoloneZnaki";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_kort?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_kort?nieprawidlowyKodPocztowy";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowe_biuro?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowe_biuro?nieprawidlowyAdresEmail";
		biuraDao.save(biuro);
		return "redirect:/biura";
		
	}
	@GetMapping("/usun_biuro")
	public String viewDBiuPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		Biuro biuro = biuraDao.get((int)Integer.parseInt(id));
		model.addAttribute("biuro",biuro);
		
		return "usun_biuro";
		
	}
	@RequestMapping(value = "/deleteBiuro", method = RequestMethod.POST)
	public String viewDefBiuPage(@ModelAttribute("biuro") Biuro biuro) {
		System.out.print("======");
		System.out.print(biuro.getId_biura());
		System.out.print("----------");
		biuraDao.delete(biuro.getId_biura());
		
		return "redirect:/biura";
		
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
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_klient_indywidualny?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getImie())) return "redirect:/nowy_klient_indywidualny?pusteImie";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNazwisko())) return "redirect:/nowy_klient_indywidualny?pusteNazwisko";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyKodPocztowy";
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
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/klient_grupowy?niedozwoloneZnaki";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowy_klient_grupowy?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujNIP(biuro.getNip())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerNip";	
		if(!WeryfikacjaDaneOsobowe.weryfikujREGON(biuro.getRegon())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerRegon";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_klient_grupowy?nieprawidlowyKodPocztowy";

		Klienci_grupowiDAO.save(biuro);
		return "redirect:/klienci_grupowi";
		
	}
	@GetMapping("/edytuj_klienta_grupowego")
	public String viewEKgruPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_grupowy klient_grupowy = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
				
					if(klient_grupowy != null) {
						
						model.addAttribute("klient_grupowy",klient_grupowy);
						
						return "edytuj_klienta_grupowego";
					}else {
						model.addAttribute("klient_grupowy",new Klient_grupowy());
						return "redirect:/klient_grupowy?brak";
					}
					
				}catch(NumberFormatException err) {
					model.addAttribute("klient_grupowy",new Klient_grupowy());
					return "redirect:/klient_grupowy?error";
				}
				
				
			}else {
				model.addAttribute("biuro",new Klient_grupowy());
				return "redirect:/klient_grupowy?error";
			}
		}else {
			
			return "redirect:/klient_grupowy?error";
		}
						
	}
	@RequestMapping(value = "/updateKlienta_grupowego", method = RequestMethod.POST)
	public String updateKgru(@ModelAttribute("klient_grupowy") Klient_grupowy biuro) {
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/klient_grupowy?niedozwoloneZnaki";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowy_klient_grupowy?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujNIP(biuro.getNip())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerNip";	
		if(!WeryfikacjaDaneOsobowe.weryfikujREGON(biuro.getRegon())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerRegon";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_klient_grupowy?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_klient_grupowy?nieprawidlowyKodPocztowy";
		Klienci_grupowiDAO.update(biuro);
		return "redirect:/klienci_grupowi";
		
	}
	@GetMapping("/edytuj_klienta_indywidualnego")
	public String viewEgruPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_indywidualny klient_indywidualny = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
				
					if(klient_indywidualny != null) {
						
						model.addAttribute("klient_indywidualny",klient_indywidualny);
						
						return "edytuj_klienta_indywidualnego";
					}else {
						model.addAttribute("klient_indywidualny",new Klient_indywidualny());
						return "redirect:/klient_indywidualny?brak";
					}
					
				}catch(NumberFormatException err) {
					model.addAttribute("klient_indywidualny",new Klient_indywidualny());
					return "redirect:/klient_indywidualny?error";
				}
				
				
			}else {
				model.addAttribute("klient_indywidualny",new Klient_indywidualny());
				return "redirect:/klient_indywidualny?error";
			}
		}else {
			
			return "redirect:/klient_indywidualny?error";
		}
	}
	@RequestMapping(value = "/updateKlienta_indywidualnego", method = RequestMethod.POST)
	public String updateKIgru(@ModelAttribute("klient_indywidualny") Klient_indywidualny biuro) {
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_klient_indywidualny?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getImie())) return "redirect:/nowy_klient_indywidualny?pusteImie";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNazwisko())) return "redirect:/nowy_klient_indywidualny?pusteNazwisko";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumerTelefonu(biuro.getNumer_telefonu())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyNumerTelefonu";	
		if(!WeryfikacjaDaneOsobowe.weryfikujEmail(biuro.getAdres_email())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyAdresEmail";
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_klient_indywidualny?nieprawidlowyKodPocztowy";
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
	@RequestMapping(value = "/usun_klienta_indywidualnego", method = RequestMethod.POST)
	public String viewDefUkliPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		Klienci_indywidualniDAO.delete((int)Integer.parseInt(id));
		
		return "klienci_indywidualni";
		
	}
	@GetMapping("/usun_klienta_grupowego")
	public String viewUKliGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klient_grupowy biuro = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
		model.addAttribute("listaKlientow_grupowych",biuro);
		
		return "usun_klienta_grupowego";
		
	}
	@RequestMapping(value = "/usun_klienta_grupowego", method = RequestMethod.POST)
	public String viewDefUkliGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		Klienci_grupowiDAO.delete((int)Integer.parseInt(id));
		
		return "klienci_grupowy";
		
	}
	@GetMapping("/korty")
	public String viewKorPage(Model model) {
		List<Kort> listaKortu = kortyDao.list();
		model.addAttribute("listaKortu",listaKortu);
		return "korty";
	}
	@RequestMapping("/nowy_kort")
	public String viewNewKorGPage(Model model) {
		Kort kort = new Kort();
		model.addAttribute("kort",kort);
		return "/nowy_kort";
		
	}
	@RequestMapping(value = "/saveKort", method = RequestMethod.POST)
	public String saveNewKor(@ModelAttribute("nowy_kort") Kort biuro) {

		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_kort?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNawierzchnia())) return "redirect:/nowy_kort?pustaNawierzchnia";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getStan())) return "redirect:/nowy_kort?pustyStan";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_kort?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_kort?nieprawidlowyKodPocztowy";
		
		kortyDao.save(biuro);
		return "redirect:/korty";
		
	}
	@GetMapping("/edytuj_kort")
	public String viewEKorPage(@RequestParam(name="kort",required=false,defaultValue="") String id, Model model) {
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Kort kort = kortyDao.get((int)Integer.parseInt(id));
				
					if(kort != null) {
						
						model.addAttribute("kort",kort);
						
						return "edytuj_kort";
					}else {
						model.addAttribute("kort",new Kort());
						return "redirect:/kort?brak";
					}
					
				}catch(NumberFormatException err) {
					model.addAttribute("kort",new Kort());
					return "redirect:/kort?error";
				}
				
				
			}else {
				model.addAttribute("kort",new Biuro());
				return "redirect:/kort?error";
			}
		}else {
			
			return "redirect:/klient_indywidualny?error";
		}
	}
	@RequestMapping(value = "/updateKort", method = RequestMethod.POST)
	public String updateKort(@ModelAttribute("kort") Kort biuro) {
		if(!AntySQLInjection.isCorrect(biuro.toString())) return "redirect:/nowy_kort?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNawierzchnia())) return "redirect:/nowy_kort?pustaNawierzchnia";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getStan())) return "redirect:/nowy_kort?pustyStan";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getNawierzchnia())) return "redirect:/nowy_kort?pustaNawierzchnia";
		if(WeryfikacjaDaneOsobowe.isEmpty(biuro.getStan())) return "redirect:/nowy_kort?pustyStan";
		
		if(!WeryfikacjaDaneOsobowe.weryfikujNumeryMieszkanBlokow(biuro.getNumer_mieszkania())) return "redirect:/nowy_kort?nieprawidlowyNumerMieszkania";
		if(!WeryfikacjaDaneOsobowe.weryfikujKodPocztowy(biuro.getKod_pocztowy())) return "redirect:/nowy_kort?nieprawidlowyKodPocztowy";
		kortyDao.update(biuro);
		return "redirect:/korty";
	}
	@GetMapping("/rezerwacje")
	public String viewRePage(@RequestParam(name="rezerwacja",required=false,defaultValue="") String id, Model model) {
		List<Rezerwacja> listaBiura = rezerwacjeDao.list();
		model.addAttribute("listaRezerwacji",listaBiura);
						
	
		return "rezerwacje";
	}
	@GetMapping("/nowa_rezerwacja")
	public String viewNRePage(@RequestParam(name="rezerwacja",required=false,defaultValue="") String id, Model model) {
		Rezerwacja rezerwacja = new Rezerwacja();
		model.addAttribute("rezerwacja",rezerwacja);
						
	
		return "rezerwacje";
	}
	@RequestMapping(value = "/saveRezerwacja", method = RequestMethod.POST)
	public String saveNewRe(@ModelAttribute("nowa_rezerwacja") Rezerwacja biuro) {
		rezerwacjeDao.save(biuro);
		return "redirect:/rezerwacje";
		
	}
}
