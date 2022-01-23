package com.example.KlubTenisowy.Pracownic;

public class PracownikPod {
	int idPracownika;
	int idBiura;
	String nazwaKlubu;
	String imie;
	String nazwisko;
	String numerTelefonu;
	String adresEmail;
	String PESEL;
	public PracownikPod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PracownikPod(int idPracownika, int idBiura, String nazwaKlubu, String imie, String nazwisko,
			String numerTelefonu, String adresEmail, String pESEL) {
		super();
		this.idPracownika = idPracownika;
		this.idBiura = idBiura;
		this.nazwaKlubu = nazwaKlubu;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.numerTelefonu = numerTelefonu;
		this.adresEmail = adresEmail;
		PESEL = pESEL;
	}
	public int getIdPracownika() {
		return idPracownika;
	}
	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
	}
	public int getIdBiura() {
		return idBiura;
	}
	public void setIdBiura(int idBiura) {
		this.idBiura = idBiura;
	}
	public String getNazwaKlubu() {
		return nazwaKlubu;
	}
	public void setNazwaKlubu(String nazwaKlubu) {
		this.nazwaKlubu = nazwaKlubu;
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
	public String getNumerTelefonu() {
		return numerTelefonu;
	}
	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}
	public String getAdresEmail() {
		return adresEmail;
	}
	public void setAdresEmail(String adresEmail) {
		this.adresEmail = adresEmail;
	}
	public String getPESEL() {
		return PESEL;
	}
	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}
	@Override
	public String toString() {
		return "PracownikPod [idPracownika=" + idPracownika + ", idBiura=" + idBiura + ", nazwaKlubu=" + nazwaKlubu
				+ ", imie=" + imie + ", nazwisko=" + nazwisko + ", numerTelefonu=" + numerTelefonu + ", adresEmail="
				+ adresEmail + ", PESEL=" + PESEL + "]";
	}
	
	
	
}
