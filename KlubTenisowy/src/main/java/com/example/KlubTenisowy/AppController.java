package com.example.KlubTenisowy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	@Autowired
	private AdresyDAO dao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {

		List<Adresy> lista = dao.list();
		model.addAttribute("lista",lista);
		return "index";
		
	}
}
