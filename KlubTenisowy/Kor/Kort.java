package com.example.KlubTenisowy.Kor;

public class Kort {
	int id_kortu;
	String nawierzchnia;
	String stan;
	String dlugosc;
	String szerokosc;
	String ulica;
	String numer_budynku;
	String numer_mieszkania;
	String miejscowosc;
	String kod_pocztowy;
	public Kort() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kort(int id_kortu, String nawierzchnia, String stan, String dlugosc, String szerokosc, String ulica,
			String numer_budynku, String numer_mieszkania, String miejscowosc, String kod_pocztowy) {
		super();
		this.id_kortu = id_kortu;
		this.nawierzchnia = nawierzchnia;
		this.stan = stan;
		this.dlugosc = dlugosc;
		this.szerokosc = szerokosc;
		this.ulica = ulica;
		this.numer_budynku = numer_budynku;
		this.numer_mieszkania = numer_mieszkania;
		this.miejscowosc = miejscowosc;
		this.kod_pocztowy = kod_pocztowy;
	}
	public int getId_kortu() {
		return id_kortu;
	}
	public void setId_kortu(int id_kortu) {
		this.id_kortu = id_kortu;
	}
	public String getNawierzchnia() {
		return nawierzchnia;
	}
	public void setNawierzchnia(String nawierzchnia) {
		this.nawierzchnia = nawierzchnia;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getDlugosc() {
		return dlugosc;
	}
	public void setDlugosc(String dlugosc) {
		this.dlugosc = dlugosc;
	}
	public String getSzerokosc() {
		return szerokosc;
	}
	public void setSzerokosc(String szerokosc) {
		this.szerokosc = szerokosc;
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
		return "Kort [id_kortu:" + id_kortu + ", nawierzchnia:" + nawierzchnia + ", stan:" + stan + ", dlugosc:"
				+ dlugosc + ", szerokosc:" + szerokosc + ", ulica:" + ulica + ", numer_budynku:" + numer_budynku
				+ ", numer_mieszkania:" + numer_mieszkania + ", miejscowosc:" + miejscowosc + ", kod_pocztowy:"
				+ kod_pocztowy + "]";
	}
	
}
