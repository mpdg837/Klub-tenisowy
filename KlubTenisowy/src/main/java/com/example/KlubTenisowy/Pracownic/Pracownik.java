package com.example.KlubTenisowy.Pracownic;

import java.util.Date;

public class Pracownik {
	int idPracownika;
	
	String imie;
	String nazwisko;
	String drugieImie;
	String plec;
	String PESEL;
	String dataUrodzenia;
	Date dataZatrudnienia;
	String stanowisko;
	String numerTelefonu;
	String adresEmail;
	Integer numerBiura;
	
	int idAbs;
	
	public Pracownik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pracownik(int idPracownika, String imie, String nazwisko, String drugieImie, String plec, String pESEL,
			String dataUrodzenia, Date dataZatrudnienia, String stanowisko, String numerTelefonu, String adresEmail,
			Integer numerBiura) {
		super();
		this.idPracownika = idPracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.drugieImie = drugieImie;
		this.plec = plec;
		PESEL = pESEL;
		this.dataUrodzenia = dataUrodzenia;
		this.dataZatrudnienia = dataZatrudnienia;
		this.stanowisko = stanowisko;
		this.numerTelefonu = numerTelefonu;
		this.adresEmail = adresEmail;
		this.numerBiura = numerBiura;
	}

	@Override
	public String toString() {
		return "Pracownik [idPracownika:" + idPracownika + ", imie:" + imie + ", nazwisko:" + nazwisko + ", drugieImie:"
				+ drugieImie + ", plec:" + plec + ", PESEL:" + PESEL + ", dataUrodzenia:" + dataUrodzenia
				+ ", dataZatrudnienia:" + dataZatrudnienia + ", stanowisko:" + stanowisko + ", numerTelefonu:"
				+ numerTelefonu + ", adresEmail:" + adresEmail + ", numerBiura:" + numerBiura + "]";
	}

	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
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

	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}

	public String getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(String dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public Date getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(Date dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
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

	public Integer getNumerBiura() {
		return numerBiura;
	}

	public void setNumerBiura(Integer numerBiura) {
		this.numerBiura = numerBiura;
	}
	
	public int getIdAbs() {
		return idAbs;
	}

	public void setIdAbs(int idAbs) {
		this.idAbs = idAbs;
	}
	
	
}
