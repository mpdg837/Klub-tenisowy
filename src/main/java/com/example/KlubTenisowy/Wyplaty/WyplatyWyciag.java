package com.example.KlubTenisowy.Wyplaty;

public class WyplatyWyciag {
	int idWyplaty;
	String imie;
	String dataWyplaty;
	String nazwisko;
	String stanowisko;
	int stawkaPodstawowa;
	int premia;
	int dodatekOkolicznosciowy;
	public WyplatyWyciag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WyplatyWyciag(int idWyplaty, String imie, String dataWyplaty, String nazwisko, String stanowisko,
			int stawkaPodstawowa, int premia, int dodatekOkolicznosciowy) {
		super();
		this.idWyplaty = idWyplaty;
		this.imie = imie;
		this.dataWyplaty = dataWyplaty;
		this.nazwisko = nazwisko;
		this.stanowisko = stanowisko;
		this.stawkaPodstawowa = stawkaPodstawowa;
		this.premia = premia;
		this.dodatekOkolicznosciowy = dodatekOkolicznosciowy;
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
	public String getDataWyplaty() {
		return dataWyplaty;
	}
	public void setDataWyplaty(String dataWyplaty) {
		this.dataWyplaty = dataWyplaty;
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
	public int getStawkaPodstawowa() {
		return stawkaPodstawowa;
	}
	public void setStawkaPodstawowa(int stawkaPodstawowa) {
		this.stawkaPodstawowa = stawkaPodstawowa;
	}
	public int getPremia() {
		return premia;
	}
	public void setPremia(int premia) {
		this.premia = premia;
	}
	public int getDodatekOkolicznosciowy() {
		return dodatekOkolicznosciowy;
	}
	public void setDodatekOkolicznosciowy(int dodatekOkolicznosciowy) {
		this.dodatekOkolicznosciowy = dodatekOkolicznosciowy;
	}
	@Override
	public String toString() {
		return "WyplatyWyciag [idWyplaty=" + idWyplaty + ", imie=" + imie + ", dataWyplaty=" + dataWyplaty
				+ ", nazwisko=" + nazwisko + ", stanowisko=" + stanowisko + ", stawkaPodstawowa=" + stawkaPodstawowa
				+ ", premia=" + premia + ", dodatekOkolicznosciowy=" + dodatekOkolicznosciowy + "]";
	}
	
	
}
