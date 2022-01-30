package com.example.KlubTenisowy.Wypozyczenia;

public class Wypozyczenie {
	int idWypozyczenia;
	String dataWypozyczenia;
	String spodziewanaDataZwrotu;
	String uwagi;
	Integer idPilki;
	Integer idRakiety;
	Integer idKlientaIndywidualnego;
	Integer idKlientaZbiorowego;
	
	public Wypozyczenie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wypozyczenie(int idWypozyczenia, String dataWypozyczenia, String spodziewanaDataZwrotu, String uwagi,
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
		if(idPilki==null) {
			return -1;
		}else {
			return idPilki;
		}
	}

	public void setIdPilki(int idPilki) {
		if(idPilki == -1) {
			this.idPilki = null;
		}else {
			this.idPilki = idPilki;
		}
	}

	public Integer getIdRakiety() {
		if(idRakiety==null) {
			return -1;
		}else {
			return idRakiety;
		}
	}

	public void setIdRakiety(int idRakiety) {
		if(idRakiety == -1) {
			this.idRakiety = null;
		}else {
			this.idRakiety = idRakiety;
		}
	}

	public Integer getIdKlientaIndywidualnego() {
		if(idKlientaIndywidualnego==null) {
			return -1;
		}else {
			return idKlientaIndywidualnego;
		}
	}

	public void setIdKlientaIndywidualnego(int idKlientaIndywidualnego) {
		if(idKlientaIndywidualnego==-1) {
			this.idKlientaIndywidualnego = null;
		}else {
			this.idKlientaIndywidualnego = idKlientaIndywidualnego;
		}
	}

	public int getIdKlientaZbiorowego() {
		if(idKlientaZbiorowego==null) {
			return -1;
		}else {
			return idKlientaZbiorowego;
		}
	}

	public void setIdKlientaZbiorowego(int idKlientaZbiorowego) {
		if(idKlientaZbiorowego==-1) {
			this.idKlientaZbiorowego = null;
		}else {
			this.idKlientaZbiorowego = idKlientaZbiorowego;
		}
	}

	@Override
	public String toString() {
		return "Wypozyczenie [idWypozyczenia:" + idWypozyczenia + ", dataWypozyczenia:" + dataWypozyczenia
				+ ", spodziewanaDataZwrotu:" + spodziewanaDataZwrotu + ", uwagi:" + uwagi + ", idPilki:" + idPilki
				+ ", idRakiety:" + idRakiety + ", idKlientaIndywidualnego:" + idKlientaIndywidualnego
				+ ", idKlientaZbiorowego:" + idKlientaZbiorowego + "]";
	}
	
	
}
