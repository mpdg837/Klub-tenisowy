package com.example.KlubTenisowy;

public class Korty {
	private int ID_kortu;
	private String Typ_kortu;
	private int Dlugosc;
	private int Szerokosc;
	private String Stan;
	private int ID_klubu;
	private int ID_adresu;
	
	public Korty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Korty(int iD_kortu, String typ_kortu, int dlugosc, int szerokosc, String stan, int iD_klubu, int iD_adresu ) {
		super();
		ID_kortu = iD_kortu;
		Typ_kortu = typ_kortu;
		Dlugosc = dlugosc;
		Szerokosc = szerokosc;
		Stan = stan;
		ID_klubu = iD_klubu;
		ID_adresu = iD_adresu;
	}
	public int getID_kortu() {
		return ID_kortu;
	}
	public void setID_kortu(int iD_kortu) {
		ID_kortu = iD_kortu;
	}
	public String getTyp_kortu() {
		return Typ_kortu;
	}
	public void setTyp_kortu(String typ_kortu) {
		Typ_kortu = typ_kortu;
	}
	public int getDlugosc() {
		return Dlugosc;
	}
	public void setDlugosc(int dlugosc) {
		Dlugosc = dlugosc;
	}
	public int getSzerokosc() {
		return Szerokosc;
	}
	public void setSzerokosc(int szerokosc) {
		Szerokosc = szerokosc;
	}
	public String getStan() {
		return Stan;
	}
	public void setStan(String stan) {
		Stan = stan;
	}
	public int getID_klubu() {
		return ID_klubu;
	}
	public void setID_klubu(int iD_klubu) {
		ID_klubu = iD_klubu;
	}
	public int getID_adresu() {
		return ID_adresu;
	}
	public void setID_adresu(int iD_adresu) {
		ID_adresu = iD_adresu;
	}
	@Override
	public String toString() {
		return "Korty [ID_kortu=" + ID_kortu + ", Typ_kortu=" + Typ_kortu + ", Dlugosc=" + Dlugosc + ", Szerokosc="
				+ Szerokosc + ", Stan=" + Stan + ", ID_klubu=" + ID_klubu + ", ID_adresu=" + ID_adresu + "]";
	}

	
}
