package com.example.KlubTenisowy.Weryfikacja;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Compare {
	public static boolean compareDate(String date1, String date2){
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			try{
			      Date d1 = sdformat.parse(date1);
			      Date d2 = sdformat.parse(date2);
			      System.out.println("The date 1 is: " + sdformat.format(d1));
			      System.out.println("The date 2 is: " + sdformat.format(d2));
			
			      if(d1.compareTo(d2) > 0) {
			         return true;
			      }
			      
			      return false;
			}catch(ParseException err) {
				return false;
			}
	}
}
