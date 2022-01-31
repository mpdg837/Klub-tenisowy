package com.example.KlubTenisowy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.example.KlubTenisowy.Biur.BiuraDAO;
import com.example.KlubTenisowy.Biur.Biuro;
import com.example.KlubTenisowy.Klienc.Klienci_grupowiDAO;
import com.example.KlubTenisowy.Klienc.Klienci_indywidualniDAO;
import com.example.KlubTenisowy.Klienc.Klient_grupowy;
import com.example.KlubTenisowy.Klienc.Klient_indywidualny;
import com.example.KlubTenisowy.Kor.Kort;
import com.example.KlubTenisowy.Kor.KortyDAO;
import com.example.KlubTenisowy.Pilki.Pilka;
import com.example.KlubTenisowy.Pracownic.PracownicyDAO;
import com.example.KlubTenisowy.Pracownic.Pracownik;
import com.example.KlubTenisowy.Rakiety.Rakieta;
import com.example.KlubTenisowy.Rezerw.Rezerwacja;
import com.example.KlubTenisowy.Rezerw.RezerwacjaSave;
import com.example.KlubTenisowy.Rezerw.RezerwacjeDAO;
import com.example.KlubTenisowy.Weryfikacja.AntySQLInjection;
import com.example.KlubTenisowy.Weryfikacja.Compare;
import com.example.KlubTenisowy.Weryfikacja.Daty;
import com.example.KlubTenisowy.Weryfikacja.WeryfikacjaDaneOsobowe;
import com.example.KlubTenisowy.Wyplaty.WyplatyDAO;
import com.example.KlubTenisowy.Wypozyczenia.Wypozyczenie;
import com.example.KlubTenisowy.Wypozyczenia.WypozyczenieSave;

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
	
	
	
	@RequestMapping("/index")
	public String viewindex(Model model) {
		

		return "index";
		
	}
	@RequestMapping("/main")
	public String viewmain(Model model) {
		
	
		return "main";
		
	}
	@RequestMapping("/login")
	public String viewlogin(Model model) {
		
		
		return "login";
		
	}
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
	public String viewDPilPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Biuro wyp = biuraDao.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("biuro",wyp);
						
						return "usun_biuro";
					}else {
						return "redirect:/biura?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/biura?error";
				}
				
				
			}else {
				return "redirect:/biura?error";
			}
		}else {
			
			return "redirect:/biura?error";
		}
	
	}
	
	@GetMapping("/usun_biuro_ostatecznie")
	public String viewWWPIPage(@RequestParam(name="biuro",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Biuro pracownik = biuraDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						biuraDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/biura?success";
					}else {
						return "redirect:/biura?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/biura?error";
				}
				
				
			}else {
				return "redirect:/biura?error";
			}
		}else {
			
			return "redirect:/biura?error";
		}
	
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
	public String viewDKliePage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_indywidualny wyp = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("klient_indywidualny",wyp);
						
						return "usun_klienta_indywidualnego";
					}else {
						return "redirect:/klienci_indywidualni?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/klienci_indywidualni?error";
				}
				
				
			}else {
				return "redirect:/klienci_indywidualni?error";
			}
		}else {
			
			return "redirect:/klienci_indywidualni?error";
		}
	
	}
	
	@GetMapping("/usun_klienta_indywidualnego_ostatecznie")
	public String viewODKlieIPage(@RequestParam(name="klient_indywidualny",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_indywidualny pracownik = Klienci_indywidualniDAO.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						Klienci_indywidualniDAO.delete((int)Integer.parseInt(id));
						
						return "redirect:/klienci_indywidualni?success";
					}else {
						return "redirect:/klienci_indywidualni?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/klienci_indywidualnibiura?error";
				}
				
				
			}else {
				return "redirect:/klienci_indywidualni?error";
			}
		}else {
			
			return "redirect:/klienci_indywidualni?error";
		}
	
	}
	@GetMapping("/usun_klienta_grupowego")
	public String viewDKlieGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_grupowy wyp = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("klient_grupowy",wyp);
						
						return "usun_klienta_grupowego";
					}else {
						return "redirect:/klienci_grupowi?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/klienci_grupowi?error";
				}
				
				
			}else {
				return "redirect:/klienci_grupowi?error";
			}
		}else {
			
			return "redirect:/klienci_grupowi?error";
		}
	
	}
	
	@GetMapping("/usun_klienta_grupowego_ostatecznie")
	public String viewODKlieGPage(@RequestParam(name="klient_grupowy",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Klient_grupowy pracownik = Klienci_grupowiDAO.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						Klienci_grupowiDAO.delete((int)Integer.parseInt(id));
						
						return "redirect:/klienci_grupowi?success";
					}else {
						return "redirect:/klienci_grupowi?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/klienci_grupowi?error";
				}
				
				
			}else {
				return "redirect:/klienci_grupowi?error";
			}
		}else {
			
			return "redirect:/klienci_grupowi?error";
		}
	
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
	@GetMapping("/usun_kort")
	public String viewDKPage(@RequestParam(name="kort",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Kort wyp = kortyDao.get((int)Integer.parseInt(id));
				
					if(wyp != null) {
						model.addAttribute("kort",wyp);
						
						return "usun_kort";
					}else {
						return "redirect:/korty?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/korty?error";
				}
				
				
			}else {
				return "redirect:/korty?error";
			}
		}else {
			
			return "redirect:/korty?error";
		}
	
	}
	
	@GetMapping("/usun_kort_ostatecznie")
	public String viewODKPage(@RequestParam(name="kort",required=false,defaultValue="") String id, Model model) {
		
		if(id.length()>0) {
			if(AntySQLInjection.isCorrect(id)) {
				
				try {
					Kort pracownik = kortyDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						kortyDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/korty?success";
					}else {
						return "redirect:/korty?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/korty?error";
				}
				
				
			}else {
				return "redirect:/korty?error";
			}
		}else {
			
			return "redirect:/korty?error";
		}
	
	}
	@GetMapping("/rezerwacje")
	public String viewRePage(@RequestParam(name="dataOd",required=false,defaultValue="") String odDaty,
			@RequestParam(name="dataDo",required=false,defaultValue="") String doDaty,Model model){
		
		List<RezerwacjaSave> lista = new ArrayList<>();
		
		if(!odDaty.equals("") && !doDaty.equals("")) {
			lista = rezerwacjeDao.listCzasA( odDaty, doDaty);
		}else {
			lista = rezerwacjeDao.list();
		}
		
		
		if(lista!=null) {
			for(int n=0;n<lista.size();n++) {
				lista.get(n).setIndex(n);
			}
		}
		model.addAttribute("rezerwacje",lista);
	
		return "rezerwacje";
	}

	@RequestMapping(value="/saveRezerwacja", method = RequestMethod.POST)
	public String savePZamowienie(@ModelAttribute("rezerwacja") Rezerwacja wypozyczenie) {
		
		if(!AntySQLInjection.isCorrect(wypozyczenie.toString())) return "redirect:/korty?niedozwoloneZnaki";
		
		if(WeryfikacjaDaneOsobowe.isEmpty(wypozyczenie.getData_rozpoczecia())) return "redirect:/korty?brakDaty";
		if(WeryfikacjaDaneOsobowe.isEmpty(wypozyczenie.getData_zakonczenia())) return "redirect:/korty?brakDaty";
		
		if(Compare.compareDate(wypozyczenie.getData_rozpoczecia(), Daty.getActData())){
			return "redirect:/korty?tenDzienJuzByl";
		}
		
		if(Compare.compareDate(wypozyczenie.getData_zakonczenia(), Daty.getActData())){
			return "redirect:/korty?tenDzienJuzByl";
		}

		if(Compare.compareDate(wypozyczenie.getData_rozpoczecia(), wypozyczenie.getData_zakonczenia())) {
			return "redirect:/korty?zlaKolejnoscData";
		}
		List<RezerwacjaSave> save= rezerwacjeDao.listCzas(wypozyczenie.getData_rozpoczecia(), wypozyczenie.getData_zakonczenia(),wypozyczenie.getId_kortu());
		if(save!=null) {
			return "redirect:/korty?zlaData";
		}
		
		if(rezerwacjeDao.get(wypozyczenie.getId_kortu())==null) {
			return "redirect:/korty?nieMaTakiegoKortu";
		}
		
		
		rezerwacjeDao.save(wypozyczenie);

		return "redirect:/korty?success";
	}
	@GetMapping("/nowa_rezerwacja")
	public String viewZRAPage(@RequestParam(name="kort",required=false,defaultValue="") String id, Model model) {
		
		Rezerwacja wyp = new Rezerwacja();
		List<Klient_grupowy> grupa = Klienci_grupowiDAO.list2();
		List<Klient_indywidualny> ind = Klienci_indywidualniDAO.list2();

		List<RezerwacjaSave> wypa = new ArrayList<>();
			
		if(!id.equals("")) {
			wypa = rezerwacjeDao.listFiltr(Integer.parseInt(id));
			wyp.setId_kortu(Integer.parseInt(id));
		}
		 
		model.addAttribute("rezerwacje",wypa);
		model.addAttribute("rezerwacja",wyp);
		model.addAttribute("klienciGrupowi",grupa);
		model.addAttribute("klienciIndywidualni",ind);
		
		return "nowa_rezerwacja";
	}
	@GetMapping("/usun_rezerwacje")
	public String viewDDRakPage(@RequestParam(name="rezerwacja",required=false,defaultValue="") String id, Model model) {
		
		//try {
			RezerwacjaSave wyp = rezerwacjeDao.get((int)Integer.parseInt(id));
		
			if(wyp != null) {
				model.addAttribute("rezerwacja",wyp);
				
				return "usun_rezerwacje";
			}else {
				return "redirect:/rezerwacje?brak";
			}
			
		//}catch(NumberFormatException err) {
			//return "redirect:/rezerwacje?error";
		//}
	
	}
	
	@GetMapping("/usun_rezerwacje_ostatecznie")
	public String viewWWXPIPage(@RequestParam(name="rezerwacja",required=false,defaultValue="") String id, Model model) {
		
		
				try {
					RezerwacjaSave pracownik = rezerwacjeDao.get((int)Integer.parseInt(id));
				
					if(pracownik != null) {
						
						rezerwacjeDao.delete((int)Integer.parseInt(id));
						
						return "redirect:/rezerwacje?success";
					}else {
						return "redirect:/rezerwacje?brak";
					}
					
				}catch(NumberFormatException err) {
					return "redirect:/rezerwacje?error";
				}
				
		
	
	}
	@GetMapping("/rezerwacje_user")
	public String viewWWyppoPage(@RequestParam(name="dataOd",required=false,defaultValue="") String odDaty,
			@RequestParam(name="dataDo",required=false,defaultValue="") String doDaty,
			Model model) {
		
		List<RezerwacjaSave> lista = new ArrayList<>();
		
		if(!odDaty.equals("") && !doDaty.equals("")) {
			lista = rezerwacjeDao.listCzasA( odDaty, doDaty);
		}else {
			lista = rezerwacjeDao.list();
		}
		
		
		if(lista!=null) {
			for(int n=0;n<lista.size();n++) {
				lista.get(n).setIndex(n);
			}
		}
		model.addAttribute("rezerwacje",lista);
	
		return "rezerwacje_user";
	}
	@GetMapping("/biura_user")
	public String viewXkortyPage(@RequestParam(name="search",required=false,defaultValue="") String search,
			Model model) {
		
				if(AntySQLInjection.isCorrect(search)) {
				List<Biuro> lista = biuraDao.list();
				
				if(lista == null) {
					lista = new ArrayList<Biuro>();
					
				}
				
				
				model.addAttribute("listaBiura",lista);
			}else {
				return "redirect:/biura_user?niedozwoloneZnaki";
			}
	
		return "biura_user";
	}
	@GetMapping("/korty_user")
	public String viewXBiuraPage(@RequestParam(name="search",required=false,defaultValue="") String search,
			Model model) {
		
			if(AntySQLInjection.isCorrect(search)) {
				List<Kort> lista = kortyDao.list();
				
				if(lista == null) {
					lista = new ArrayList<Kort>();
					
				}
	
				
				model.addAttribute("listaKortu",lista);
			}else {
				return "redirect:/korty_user?niedozwoloneZnaki";
			}
	
		return "korty_user";
	}
}
