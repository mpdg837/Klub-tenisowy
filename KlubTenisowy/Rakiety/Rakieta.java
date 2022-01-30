package com.example.KlubTenisowy.Rakiety;

public class Rakieta {
	
	int idRakiety;
	String nazwa;
	double masa;
	double dlugosc;
	double szerokosc;
	
	int index;
	public Rakieta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rakieta(int idRakiety, String nazwa, double masa, double dlugosc, double szerokosc) {
		super();
		this.idRakiety = idRakiety;
		this.nazwa = nazwa;
		this.masa = masa;
		this.dlugosc = dlugosc;
		this.szerokosc = szerokosc;
	}

	public int getIdRakiety() {
		return idRakiety;
	}

	public void setIdRakiety(int idRakiety) {
		this.idRakiety = idRakiety;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public double getMasa() {
		return masa;
	}

	public void setMasa(double masa) {
		this.masa = masa;
	}

	public double getDlugosc() {
		return dlugosc;
	}

	public void setDlugosc(double dlugosc) {
		this.dlugosc = dlugosc;
	}

	public double getSzerokosc() {
		return szerokosc;
	}

	public void setSzerokosc(double szerokosc) {
		this.szerokosc = szerokosc;
	}
	
	@Override
	public String toString() {
		return "Rakieta [idRakiety:" + idRakiety + ", nazwa:" + nazwa + ", masa:" + masa + ", dlugosc:" + dlugosc
				+ ", szerokosc:" + szerokosc + "]";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
