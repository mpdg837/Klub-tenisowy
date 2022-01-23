package com.example.KlubTenisowy;

public class Sprzety{
	private int ID_sprzetu;
	private String Nazwa;
	private int Rok_produkcji;
	private String Stan;
	private int ID_klienta;
	private int ID_klubu;
	
	public Sprzety() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sprzety(int iD_sprzetu, String nazwa, int rok_produkcji, String stan, int iD_klienta, int iD_klubu) {
		super();
		ID_sprzetu = iD_sprzetu;
		Nazwa = nazwa;
		Rok_produkcji = rok_produkcji;
		Stan = stan;
		ID_klienta = iD_klienta;
		ID_klubu = iD_klubu;
	}
	public int getID_sprzetu() {
		return ID_sprzetu;
	}
	public void setID_sprzetu(int iD_sprzetu) {
		ID_sprzetu = iD_sprzetu;
	}
	public String getNazwa() {
		return Nazwa;
	}
	public void setNazwa(String nazwa) {
		Nazwa = nazwa;
	}
	public int getRok_produkcji() {
		return Rok_produkcji;
	}
	public void setRok_produkcji(int rok_produkcji) {
		Rok_produkcji = rok_produkcji;
	}
	public String getStan() {
		return Stan;
	}
	public void setStan(String stan) {
		Stan = stan;
	}
	public int getID_klienta() {
		return ID_klienta;
	}
	public void setID_klienta(int iD_klienta) {
		ID_klienta = iD_klienta;
	}
	public int getID_klubu() {
		return ID_klubu;
	}
	public void setID_klubu(int iD_klubu) {
		ID_klubu = iD_klubu;
	}
	@Override
	public String toString() {
		return "Sprzety [ID_sprzetu=" + ID_sprzetu + ", Nazwa=" + Nazwa + ", Rok_produkcji=" + Rok_produkcji + ", Stan="
				+ Stan + ", ID_klienta=" + ID_klienta + ", ID_klubu=" + ID_klubu + "]";
	}

	
}
