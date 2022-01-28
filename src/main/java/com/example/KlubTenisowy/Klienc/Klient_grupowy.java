package com.example.KlubTenisowy.Klienc;

public class Klient_grupowy {
	int idKlienta_grupowego;
	String nazwa;
	String nip;
	String rego;
	String numer_telefonu;
	String adres_email;
	String ulica;
	String numer_budynku;
	String numer_mieszkania;
	String miejscowosc;
	String kod_pocztowy;
	public Klient_grupowy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Klient_grupowy(int idKlienta_grupowego, String nazwa, String nip, String rego, String numer_telefonu,
			String adres_email, String ulica, String numer_budynku, String numer_mieszkania, String miejscowosc,
			String kod_pocztowy) {
		super();
		this.idKlienta_grupowego = idKlienta_grupowego;
		this.nazwa = nazwa;
		this.nip = nip;
		this.rego = rego;
		this.numer_telefonu = numer_telefonu;
		this.adres_email = adres_email;
		this.ulica = ulica;
		this.numer_budynku = numer_budynku;
		this.numer_mieszkania = numer_mieszkania;
		this.miejscowosc = miejscowosc;
		this.kod_pocztowy = kod_pocztowy;
	}
	public int getIdKlienta_grupowego() {
		return idKlienta_grupowego;
	}
	public void setIdKlienta_grupowego(int idKlienta_grupowego) {
		this.idKlienta_grupowego = idKlienta_grupowego;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getRego() {
		return rego;
	}
	public void setRego(String rego) {
		this.rego = rego;
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
	@Override
	public String toString() {
		return "Klienct_grupowy [idKlienta_grupowego=" + idKlienta_grupowego + ", nazwa=" + nazwa + ", nip=" + nip
				+ ", rego=" + rego + ", numer_telefonu=" + numer_telefonu + ", adres_email=" + adres_email + ", ulica="
				+ ulica + ", numer_budynku=" + numer_budynku + ", numer_mieszkania=" + numer_mieszkania
				+ ", miejscowosc=" + miejscowosc + ", kod_pocztowy=" + kod_pocztowy + "]";
	}
	
}
