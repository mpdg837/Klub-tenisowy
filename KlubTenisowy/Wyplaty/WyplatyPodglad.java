package com.example.KlubTenisowy.Wyplaty;

public class WyplatyPodglad {
	int idWyplaty;
	String imie;
	String dataWyplaty;
	String nazwisko;
	String stanowisko;
	double wyplataLacznie;
	
	int index;
	
	public WyplatyPodglad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WyplatyPodglad(int idWyplaty,String dataWyplaty, String imie, String nazwisko, String stanowisko, double wyplataLacznie) {
		super();
		this.idWyplaty = idWyplaty;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.stanowisko = stanowisko;
		this.wyplataLacznie = wyplataLacznie;
		this.dataWyplaty = dataWyplaty;
	}

	public int getIdWyplaty() {
		return idWyplaty;
	}

	public void setIdWyplaty(int idWyplaty) {
		this.idWyplaty = idWyplaty;
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

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

	public double getWyplataLacznie() {
		return wyplataLacznie;
	}

	public void setWyplataLacznie(double wyplataLacznie) {
		this.wyplataLacznie = wyplataLacznie;
	}

	
	public String getDataWyplaty() {
		return dataWyplaty;
	}

	public void setDataWyplaty(String dataWyplaty) {
		this.dataWyplaty = dataWyplaty;
	}
	
	

	@Override
	public String toString() {
		return "WyplatyPodglad [idWyplaty:" + idWyplaty + ", imie:" + imie + ", dataWyplaty:" + dataWyplaty
				+ ", nazwisko:" + nazwisko + ", stanowisko:" + stanowisko + ", wyplataLacznie:" + wyplataLacznie + "]";
	}

	public void setIndex(int numer) {
		index = numer;
	}

	public int getIndex() {
		return index;
	}
	
	
	
}
