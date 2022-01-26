package com.example.KlubTenisowy.Weryfikacja;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Daty {
	public static boolean isCorrect(String data) {
		DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        
        try {
            sdf.parse(data);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
	
	public static String getActData() {
		//default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();
			
		//creating the instance of LocalDate using the day, month, year info
	        LocalDate localDate = java.time.LocalDate.now();
	        
	        //local date + atStartOfDay() + default time zone + toInstant() = Date
	        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String dateCorrect = simpleDateFormat.format(date);
		
		return dateCorrect;
	}
}
