package com.example.KlubTenisowy;

import java.sql.Date;

public class Osoby {
	int idOsoby;
	String imie;
	String nazwisko;
	String drugieImie;
	String plec;
	Date dataUrodzenia;
	
	public Osoby() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Osoby(int idOsoby, String imie, String nazwisko, String drugieImie, String plec, Date dataUrodzenia) {
		super();
		this.idOsoby = idOsoby;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.drugieImie = drugieImie;
		this.plec = plec;
		this.dataUrodzenia = dataUrodzenia;
	}

	public int getIdOsoby() {
		return idOsoby;
	}

	public void setIdOsoby(int idOsoby) {
		this.idOsoby = idOsoby;
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

	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	@Override
	public String toString() {
		return "Osoby [idOsoby=" + idOsoby + ", imie=" + imie + ", nazwisko=" + nazwisko + ", drugieImie=" + drugieImie
				+ ", plec=" + plec + ", dataUrodzenia=" + dataUrodzenia + "]";
	}
	
	
}
