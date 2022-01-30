package com.example.KlubTenisowy.Wypozyczenia;

public class WypozyczenieSave {
	int idWypozyczenia;
	String dataWypozyczenia;
	String spodziewanaDataZwrotu;
	String uwagi;
	Integer idPilki;
	Integer idRakiety;
	Integer idKlientaIndywidualnego;
	Integer idKlientaZbiorowego;
	
	int index;
	
	public WypozyczenieSave() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WypozyczenieSave(int idWypozyczenia, String dataWypozyczenia, String spodziewanaDataZwrotu, String uwagi,
			Integer idPilki, Integer idRakiety, Integer idKlientaIndywidualnego, Integer idKlientaZbiorowego) {
		super();
		this.idWypozyczenia = idWypozyczenia;
		this.dataWypozyczenia = dataWypozyczenia;
		this.spodziewanaDataZwrotu = spodziewanaDataZwrotu;
		this.uwagi = uwagi;
		this.idPilki = idPilki;
		this.idRakiety = idRakiety;
		this.idKlientaIndywidualnego = idKlientaIndywidualnego;
		this.idKlientaZbiorowego = idKlientaZbiorowego;
		
		
	}
	public int getIdWypozyczenia() {
		return idWypozyczenia;
	}
	public void setIdWypozyczenia(int idWypozyczenia) {
		this.idWypozyczenia = idWypozyczenia;
	}
	public String getDataWypozyczenia() {
		return dataWypozyczenia;
	}
	public void setDataWypozyczenia(String dataWypozyczenia) {
		this.dataWypozyczenia = dataWypozyczenia;
	}
	public String getSpodziewanaDataZwrotu() {
		return spodziewanaDataZwrotu;
	}
	public void setSpodziewanaDataZwrotu(String spodziewanaDataZwrotu) {
		this.spodziewanaDataZwrotu = spodziewanaDataZwrotu;
	}
	public String getUwagi() {
		return uwagi;
	}
	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}
	public Integer getIdPilki() {
		return idPilki;
	}
	public void setIdPilki(Integer idPilki) {
		this.idPilki = idPilki;
	}
	public Integer getIdRakiety() {
		return idRakiety;
	}
	public void setIdRakiety(Integer idRakiety) {
		this.idRakiety = idRakiety;
	}
	public Integer getIdKlientaIndywidualnego() {
		return idKlientaIndywidualnego;
	}
	public void setIdKlientaIndywidualnego(Integer idKlientaIndywidualnego) {
		this.idKlientaIndywidualnego = idKlientaIndywidualnego;
	}
	public Integer getIdKlientaZbiorowego() {
		return idKlientaZbiorowego;
	}
	public void setIdKlientaZbiorowego(Integer idKlientaZbiorowego) {
		this.idKlientaZbiorowego = idKlientaZbiorowego;
	}
	@Override
	public String toString() {
		return "WypozyczenieSave [idWypozyczenia=" + idWypozyczenia + ", dataWypozyczenia=" + dataWypozyczenia
				+ ", spodziewanaDataZwrotu=" + spodziewanaDataZwrotu + ", uwagi=" + uwagi + ", idPilki=" + idPilki
				+ ", idRakiety=" + idRakiety + ", idKlientaIndywidualnego=" + idKlientaIndywidualnego
				+ ", idKlientaZbiorowego=" + idKlientaZbiorowego + "]";
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
