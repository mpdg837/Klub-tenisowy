package com.example.KlubTenisowy;

public class DaneKontatkowe {
	int idDaneKontaktowe;
	String numerTelefonu;
	String adresEmail;
	
	public DaneKontatkowe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DaneKontatkowe(int idDaneKontaktowe, String numerTelefonu, String adresEmail) {
		super();
		this.idDaneKontaktowe = idDaneKontaktowe;
		this.numerTelefonu = numerTelefonu;
		this.adresEmail = adresEmail;
	}
	public int getIdDaneKontaktowe() {
		return idDaneKontaktowe;
	}
	public void setIdDaneKontatkowe(int idDaneKontaktowe) {
		this.idDaneKontaktowe = idDaneKontaktowe;
	}
	public String getNumerTelefonu() {
		return numerTelefonu;
	}
	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}
	public String getAdresEmail() {
		return adresEmail;
	}
	public void setAdresEmail(String adresEmail) {
		this.adresEmail = adresEmail;
	}
	@Override
	public String toString() {
		return "DaneKontatkowe [idDaneKontaktowe=" + idDaneKontaktowe + ", numerTelefonu=" + numerTelefonu
				+ ", adresEmail=" + adresEmail + "]";
	}
	
}
