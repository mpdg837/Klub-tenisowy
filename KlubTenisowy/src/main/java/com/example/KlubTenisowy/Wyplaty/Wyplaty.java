package com.example.KlubTenisowy.Wyplaty;

public class Wyplaty {
	int idWyplaty;
	int idPracownika;
	String dataWyplaty;
	double stawkaPodstawowa;
	double premia;
	double dodatekOkolicznosciowy;
	
	int index;
	
	public Wyplaty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wyplaty(int idWyplaty, int idPracownika, String dataWyplaty, double stawkaPodstawowa, double premia,
			double dodatekOkolicznosciowy) {
		super();
		this.idWyplaty = idWyplaty;
		this.idPracownika = idPracownika;
		this.dataWyplaty = dataWyplaty;
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

	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
	}

	public String getDataWyplaty() {
		return dataWyplaty;
	}

	public void setDataWyplaty(String dataWyplaty) {
		this.dataWyplaty = dataWyplaty;
	}

	public double getStawkaPodstawowa() {
		return stawkaPodstawowa;
	}

	public void setStawkaPodstawowa(double stawkaPodstawowa) {
		this.stawkaPodstawowa = stawkaPodstawowa;
	}

	public double getPremia() {
		return premia;
	}

	public void setPremia(double premia) {
		this.premia = premia;
	}

	public double getDodatekOkolicznosciowy() {
		return dodatekOkolicznosciowy;
	}

	public void setDodatekOkolicznosciowy(double dodatekOkolicznosciowy) {
		this.dodatekOkolicznosciowy = dodatekOkolicznosciowy;
	}

	public void setIndex(int numer) {
		index = numer;
	}

	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return "Wyplaty [idWyplaty:" + idWyplaty + ", idPracownika:" + idPracownika + ", dataWyplaty:" + dataWyplaty
				+ ", stawkaPodstawowa:" + stawkaPodstawowa + ", premia:" + premia + ", dodatekOkolicznosciowy:"
				+ dodatekOkolicznosciowy + "]";
	}
}
