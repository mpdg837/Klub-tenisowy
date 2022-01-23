package com.example.KlubTenisowy;

public class Wynagrodzenia {
	private int ID_wynagrodzenia;
	private int Data_pod;
	private int Data_dod;
	private int ID_pracownika;
	public Wynagrodzenia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wynagrodzenia(int iD_wynagrodzenia, int data_pod, int data_dod, int iD_pracownika) {
		super();
		ID_wynagrodzenia = iD_wynagrodzenia;
		Data_pod = data_pod;
		Data_dod = data_dod;
		ID_pracownika = iD_pracownika;
	}
	public int getID_wynagrodzenia() {
		return ID_wynagrodzenia;
	}
	public void setID_wynagrodzenia(int iD_wynagrodzenia) {
		ID_wynagrodzenia = iD_wynagrodzenia;
	}
	public int getData_pod() {
		return Data_pod;
	}
	public void setData_pod(int data_pod) {
		Data_pod = data_pod;
	}
	public int getData_dod() {
		return Data_dod;
	}
	public void setData_dod(int data_dod) {
		Data_dod = data_dod;
	}
	public int getID_pracownika() {
		return ID_pracownika;
	}
	public void setID_pracownika(int iD_pracownika) {
		ID_pracownika = iD_pracownika;
	}
	@Override
	public String toString() {
		return "Wynagrodzenia [ID_wynagrodzenia=" + ID_wynagrodzenia + ", Data_pod=" + Data_pod + ", Data_dod="
				+ Data_dod + ", ID_pracownika=" + ID_pracownika + "]";
	}
	
}
