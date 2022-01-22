package com.example.KlubTenisowy;


public class Adresy {
	private int idAdresu;
	private String ulica;
	private String numerBudynku;
	private String numerMieszkania;
	private String miejscowosc;
	private String kodPocztowy;
	
	public Adresy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Adresy(int idAdresu, String ulica, String numerBudynku, String numerMieszkania, String miejscowosc,
			String kodPocztowy) {
		super();
		this.idAdresu = idAdresu;
		this.ulica = ulica;
		this.numerBudynku = numerBudynku;
		this.numerMieszkania = numerMieszkania;
		this.miejscowosc = miejscowosc;
		this.kodPocztowy = kodPocztowy;
	}
	
	public int getIdAdresu() {
		return idAdresu;
	}
	public void setIdAdresu(int idAdresu) {
		this.idAdresu = idAdresu;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getNumerBudynku() {
		return numerBudynku;
	}
	public void setNumerBudynku(String numerBudynku) {
		this.numerBudynku = numerBudynku;
	}
	public String getNumerMieszkania() {
		return numerMieszkania;
	}
	public void setNumerMieszkania(String numerMieszkania) {
		this.numerMieszkania = numerMieszkania;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	public String getKodPocztowy() {
		return kodPocztowy;
	}
	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}
	
	@Override
	public String toString() {
		return "Adresy [idAdresu=" + idAdresu + ", ulica=" + ulica + ", numerBudnyku=" + numerBudynku + ", numerMieszkania="
				+ numerMieszkania + ", miejscowosc=" + miejscowosc + ", kodPocztowy=" + kodPocztowy + "]";
	}
}
