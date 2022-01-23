package com.example.KlubTenisowy;

public class DaneKontatkowe {
	int idDaneKontatkowe;
	String numerTelefonu;
	String adresEmail;
	public DaneKontatkowe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DaneKontatkowe(int idDaneKontatkowe, String numerTelefonu, String adresEmail) {
		super();
		this.idDaneKontatkowe = idDaneKontatkowe;
		this.numerTelefonu = numerTelefonu;
		this.adresEmail = adresEmail;
	}
	public int getIdDaneKontatkowe() {
		return idDaneKontatkowe;
	}
	public void setIdDaneKontatkowe(int idDaneKontatkowe) {
		this.idDaneKontatkowe = idDaneKontatkowe;
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
		return "DaneKontatkowe [idDaneKontatkowe=" + idDaneKontatkowe + ", numerTelefonu=" + numerTelefonu
				+ ", adresEmail=" + adresEmail + "]";
	}
	
}
