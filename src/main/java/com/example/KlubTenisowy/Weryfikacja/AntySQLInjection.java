package com.example.KlubTenisowy.Weryfikacja;

public class AntySQLInjection {
	public static boolean isCorrect(String wiadomosc) {
		char[] znaki = wiadomosc.toCharArray();
		
		boolean zakazanyZnak = false;
		
		for(char znak: znaki) {
			System.out.print(znak);
			switch(znak+"") {
				case "'" :
				case (char)(34)+"" :
				case "=":
					zakazanyZnak = true;
					
			}
		}
		return !zakazanyZnak;
	}
}
