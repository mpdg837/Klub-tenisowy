package com.example.KlubTenisowy.Pracownic;

public class Pracownicy {
	int idPracownika;
	String PESEL;
	String dataZatrudnienia;
	int idKlubu;
	int idBiura;
	int idOsoby;
	int idDaneKontaktowe;
	
	public Pracownicy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pracownicy(int idPracownika, String pESEL, String dataZatrudnienia, int idKlubu, int idBiura, int idOsoby,
			int idDaneKontaktowe) {
		super();
		this.idPracownika = idPracownika;
		PESEL = pESEL;
		this.dataZatrudnienia = dataZatrudnienia;
		this.idKlubu = idKlubu;
		this.idBiura = idBiura;
		this.idOsoby = idOsoby;
		this.idDaneKontaktowe = idDaneKontaktowe;
	}

	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
	}

	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}

	public String getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(String dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

	public int getIdKlubu() {
		return idKlubu;
	}

	public void setIdKlubu(int idKlubu) {
		this.idKlubu = idKlubu;
	}

	public int getIdBiura() {
		return idBiura;
	}

	public void setIdBiura(int idBiura) {
		this.idBiura = idBiura;
	}

	public int getIdOsoby() {
		return idOsoby;
	}

	public void setIdOsoby(int idOsoby) {
		this.idOsoby = idOsoby;
	}

	public int getIdDaneKontaktowe() {
		return idDaneKontaktowe;
	}

	public void setIdDaneKontaktowe(int idDaneKontaktowe) {
		this.idDaneKontaktowe = idDaneKontaktowe;
	}

	@Override
	public String toString() {
		return "Pracownicy [idPracownika=" + idPracownika + ", PESEL=" + PESEL + ", dataZatrudnienia="
				+ dataZatrudnienia + ", idKlubu=" + idKlubu + ", idBiura=" + idBiura + ", idOsoby=" + idOsoby
				+ ", idDaneKontaktowe=" + idDaneKontaktowe + "]";
	}
}
