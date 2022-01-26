package com.example.KlubTenisowy.Pilki;

public class Pilka {
	int idPilki;
	String nazwa;
	double masa;
	double srednica;
	String material;
	
	int index;
	
	public Pilka() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pilka(int idPilki, String nazwa, double masa, double srednica, String material) {
		super();
		this.idPilki = idPilki;
		this.nazwa = nazwa;
		this.masa = masa;
		this.srednica = srednica;
		this.material = material;
	}
	public int getIdPilki() {
		return idPilki;
	}
	public void setIdPilki(int idPilki) {
		this.idPilki = idPilki;
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
	public double getSrednica() {
		return srednica;
	}
	public void setSrednica(double srednica) {
		this.srednica = srednica;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "Pilka [idPilki:" + idPilki + ", nazwa:" + nazwa + ", masa:" + masa + ", srednica:" + srednica
				+ ", material:" + material + "]";
	}
	
	

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
