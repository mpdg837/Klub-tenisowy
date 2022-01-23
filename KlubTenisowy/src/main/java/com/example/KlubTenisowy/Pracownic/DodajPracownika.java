package com.example.KlubTenisowy.Pracownic;

import com.example.KlubTenisowy.Adresy;
import com.example.KlubTenisowy.DaneKontatkowe;
import com.example.KlubTenisowy.Osoby;

public class DodajPracownika {
	String imie;
	String naziwsko;
	String drugieImie;
	String plec;
	
	int numerKlubu;
	int numerBiura;
	
	String numerTelefonu;
	String adresEmail;
	
	String PESEL;

	public DodajPracownika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DodajPracownika(String imie, String naziwsko, String drugieImie, String plec, int numerKlubu, int numerBiura,
			String numerTelefonu, String adresEmail, String pESEL) {
		super();
		this.imie = imie;
		this.naziwsko = naziwsko;
		this.drugieImie = drugieImie;
		this.plec = plec;
		this.numerKlubu = numerKlubu;
		this.numerBiura = numerBiura;
		this.numerTelefonu = numerTelefonu;
		this.adresEmail = adresEmail;
		PESEL = pESEL;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNaziwsko() {
		return naziwsko;
	}

	public void setNaziwsko(String naziwsko) {
		this.naziwsko = naziwsko;
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

	public int getNumerKlubu() {
		return numerKlubu;
	}

	public void setNumerKlubu(int numerKlubu) {
		this.numerKlubu = numerKlubu;
	}

	public int getNumerBiura() {
		return numerBiura;
	}

	public void setNumerBiura(int numerBiura) {
		this.numerBiura = numerBiura;
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
		return "DodajPracownika [imie=" + imie + ", naziwsko=" + naziwsko + ", drugieImie=" + drugieImie + ", plec="
				+ plec + ", numerKlubu=" + numerKlubu + ", numerBiura=" + numerBiura + ", numerTelefonu="
				+ numerTelefonu + ", adresEmail=" + adresEmail + ", PESEL=" + PESEL + "]";
	}
	
}
