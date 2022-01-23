package com.example.KlubTenisowy;

public class Kluby_tenisowe {
	private int ID_klubu;
	private String Nazwa_klubu;
	private String Data_zalozenia;
	private int ID_adresu;
	private int ID_Dane_kontaktowe;
	private int ID_Firmy;
	public Kluby_tenisowe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kluby_tenisowe(int iD_klubu, String nazwa_klubu, String data_zalozenia, int iD_adresu,
			int iD_Dane_kontaktowe, int iD_Firmy) {
		super();
		ID_klubu = iD_klubu;
		Nazwa_klubu = nazwa_klubu;
		Data_zalozenia = data_zalozenia;
		ID_adresu = iD_adresu;
		ID_Dane_kontaktowe = iD_Dane_kontaktowe;
		ID_Firmy = iD_Firmy;
	}
	public int getID_klubu() {
		return ID_klubu;
	}
	public void setID_klubu(int iD_klubu) {
		ID_klubu = iD_klubu;
	}
	public String getNazwa_klubu() {
		return Nazwa_klubu;
	}
	public void setNazwa_klubu(String nazwa_klubu) {
		Nazwa_klubu = nazwa_klubu;
	}
	public String getData_zalozenia() {
		return Data_zalozenia;
	}
	public void setData_zalozenia(String data_zalozenia) {
		Data_zalozenia = data_zalozenia;
	}
	public int getID_adresu() {
		return ID_adresu;
	}
	public void setID_adresu(int iD_adresu) {
		ID_adresu = iD_adresu;
	}
	public int getID_Dane_kontaktowe() {
		return ID_Dane_kontaktowe;
	}
	public void setID_Dane_kontaktowe(int iD_Dane_kontaktowe) {
		ID_Dane_kontaktowe = iD_Dane_kontaktowe;
	}
	public int getID_Firmy() {
		return ID_Firmy;
	}
	public void setID_Firmy(int iD_Firmy) {
		ID_Firmy = iD_Firmy;
	}
	@Override
	public String toString() {
		return "Kluby_tenisowe [ID_klubu=" + ID_klubu + ", Nazwa_klubu=" + Nazwa_klubu + ", Data_zalozenia="
				+ Data_zalozenia + ", ID_adresu=" + ID_adresu + ", ID_Dane_kontaktowe=" + ID_Dane_kontaktowe
				+ ", ID_Firmy=" + ID_Firmy + "]";
	}
	
}
