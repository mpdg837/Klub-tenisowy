package com.example.KlubTenisowy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controllindex {
	@RequestMapping("/")
	public String gettheview() {
		return "index";
	}
	
	

}
