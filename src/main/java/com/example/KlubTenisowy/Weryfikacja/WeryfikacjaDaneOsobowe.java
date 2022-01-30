package com.example.KlubTenisowy.Weryfikacja;

public class WeryfikacjaDaneOsobowe {
	
	public static boolean weryfikujEmail(String email) {
		
		char[] znaki = email.toCharArray();
		
		boolean zawieraMalpe = false;
		
		for(char znak : znaki) {

			if(znak == 64) zawieraMalpe = true;
			if(znak > 255) return false;
		}
		
		return zawieraMalpe;
	}
	
	public static boolean weryfikujNumerTelefonu(String numer) {
		
		char[] znaki = numer.toCharArray();
		
		boolean isOk = true;
		
		int cyfry = 0;
		
		int n=0;
		
		for(char znak : znaki) {
			String znakS = znak + "";
			
			if(znak >= 48 && znak <=57) {
				cyfry ++;
			}else if(znakS.equals("+") && n==0) {
			}else if(!znakS.equals(" ")) {
				isOk = false;
			}
			n++;
		}
		
		if(cyfry != 9) isOk = false;
		
		return isOk;
	}
	
	public static boolean weryfikujPESEL(String PESEL) {
		char[] znaki = PESEL.toCharArray();
		
		if(PESEL.length() == 11) {
			
			for(char znak : znaki) {
				
				if(znak >= 48 && znak <=57) {
					
				}else {
					return false;
				}
				
			}
		}else if(PESEL.length()==0) {
			return true;
		}else {
			return false;
		}
		
		return true;
	}
	
	public static boolean isEmpty(String string) {
		return string.length() == 0;
	}
	
	public static boolean weryfikujNumeryMieszkanBlokow(String numer) {
		
		char[] znaki = numer.toCharArray();
		
		if(numer.length() <= 5) {
			
			for(char znak : znaki) {
				
				if(znak >= 48 && znak <=57) {
					// cyfry
				}else if(znak >=97 && znak <= 122){
					// male litery
				}else if(znak >= 65 && znak <= 90) {
					// duze litery
				}else {
					return false;
				}
				
			}
		}else if(numer.length()!=0){
			return false;
		}
		
		return true;
	}
	
	public static boolean weryfikujKodPocztowy(String kodPocztowy) {
		char[] znaki = kodPocztowy.toCharArray();
		
		if(kodPocztowy.length() == 6) {
			
			int n=0;
			
			for(char znak : znaki) {
				
				String znakS = znak + "";
					
				if(znak >= 48 && znak <=57) {
					// cyfry
					
				}else if(znakS.equals("-") && n==2) {
					// Myślnik
				}else {
					return false;
				}
				
				n++;
			}
		}else{
			return false;
		}
		
		return true;
	}
	
	public static String konwertujKodPocztowy(String kodPocztowy) {
		StringBuilder build = new StringBuilder();
		char[] znaki = kodPocztowy.toCharArray();
		
		for(int n=0;n<2;n++) {
			build.append(znaki[n]);
		}
		
		for(int n=3;n<6;n++) {
			build.append(znaki[n]);
		}
		
		return build.toString();
	}
	
	
}
