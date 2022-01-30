package com.example.KlubTenisowy.Klienc;

public class Klient_indywidualny {
	int id_klienta_indywidualnego;
	
	String imie;
	String nazwisko;
	String drugieImie;
	String plec;
	String numer_telefonu;
	String adres_email;
	String ulica;
	String numer_budynku;
	String numer_mieszkania;
	String miejscowosc;
	String kod_pocztowy;
	
	int index;
	
	public Klient_indywidualny(int id_klienta_indywidualnego, String imie, String nazwisko, String drugieImie,
			String plec, String numer_telefonu, String adres_email, String ulica, String numer_budynku,
			String numer_mieszkania, String miejscowosc, String kod_pocztowy) {
		super();
		this.id_klienta_indywidualnego = id_klienta_indywidualnego;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.drugieImie = drugieImie;
		this.plec = plec;
		this.numer_telefonu = numer_telefonu;
		this.adres_email = adres_email;
		this.ulica = ulica;
		this.numer_budynku = numer_budynku;
		this.numer_mieszkania = numer_mieszkania;
		this.miejscowosc = miejscowosc;
		this.kod_pocztowy = kod_pocztowy;
	}
	public Klient_indywidualny() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_klienta_indywidualnego() {
		return id_klienta_indywidualnego;
	}
	public void setId_klienta_indywidualnego(int id_klienta_indywidualnego) {
		this.id_klienta_indywidualnego = id_klienta_indywidualnego;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getDrugieImie() {
		return drugieImie;
	}
	public void setDrugieImie(String drugieImie) {
		this.drugieImie = drugieImie;
	}
	public String getPlec() {
		return plec;
	}
	public void setPlec(String plec) {
		this.plec = plec;
	}
	public String getNumer_telefonu() {
		return numer_telefonu;
	}
	public void setNumer_telefonu(String numer_telefonu) {
		this.numer_telefonu = numer_telefonu;
	}
	public String getAdres_email() {
		return adres_email;
	}
	public void setAdres_email(String adres_email) {
		this.adres_email = adres_email;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getNumer_budynku() {
		return numer_budynku;
	}
	public void setNumer_budynku(String numer_budynku) {
		this.numer_budynku = numer_budynku;
	}
	public String getNumer_mieszkania() {
		return numer_mieszkania;
	}
	public void setNumer_mieszkania(String numer_mieszkania) {
		this.numer_mieszkania = numer_mieszkania;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	public String getKod_pocztowy() {
		return kod_pocztowy;
	}
	public void setKod_pocztowy(String kod_pocztowy) {
		this.kod_pocztowy = kod_pocztowy;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Klient_indywidualny [id_klienta_indywidualnego:" + id_klienta_indywidualnego + ", imie:" + imie
				+ ", nazwisko:" + nazwisko + ", drugieImie:" + drugieImie + ", plec:" + plec + ", numer_telefonu:"
				+ numer_telefonu + ", adres_email:" + adres_email + ", ulica:" + ulica + ", numer_budynku:"
				+ numer_budynku + ", numer_mieszkania:" + numer_mieszkania + ", miejscowosc:" + miejscowosc
				+ ", kod_pocztowy:" + kod_pocztowy + "]";
	}
	
}
