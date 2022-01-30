package com.example.KlubTenisowy.Rezerw;

public class Rezerwacja {
	int id_rezerwacji;
	String data_rozpoczecia;
	String data_zakonczenia;
	Integer id_kortu;
	String uwagi;
	Integer id_klienta_indywidualnego;
	Integer id_klienta_zbiorowego;
	public Rezerwacja() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rezerwacja(int id_rezerwacji, String data_rozpoczecia, String data_zakonczenia, Integer id_kortu, String uwagi,
			Integer id_klienta_indywidualnego, Integer id_klienta_zbiorowego) {
		super();
		this.id_rezerwacji = id_rezerwacji;
		this.data_rozpoczecia = data_rozpoczecia;
		this.data_zakonczenia = data_zakonczenia;
		this.id_kortu = id_kortu;
		this.uwagi = uwagi;
		this.id_klienta_indywidualnego = id_klienta_indywidualnego;
		this.id_klienta_zbiorowego = id_klienta_zbiorowego;
	}
	public int getId_rezerwacji() {
		return id_rezerwacji;
	}
	public void setId_rezerwacji(int id_rezerwacji) {
		this.id_rezerwacji = id_rezerwacji;
	}
	public String getData_rozpoczecia() {
		return data_rozpoczecia;
	}
	public void setData_rozpoczecia(String data_rozpoczecia) {
		this.data_rozpoczecia = data_rozpoczecia;
	}
	public String getData_zakonczenia() {
		return data_zakonczenia;
	}
	public void setData_zakonczenia(String data_zakonczenia) {
		this.data_zakonczenia = data_zakonczenia;
	}
	public Integer getId_kortu() {
		if(id_kortu==null) {
			return -1;
		}else {
			return id_kortu;
		}
	}
	public void setId_kortu(Integer id_kortu) {
		if(id_kortu == -1) {
			this.id_kortu = null;
		}else {
			this.id_kortu = id_kortu;
		}
	}
	public String getUwagi() {
		return uwagi;
	}
	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}
	public Integer getId_klienta_indywidualnego() {
		if(id_klienta_indywidualnego == null) {
			return -1;
		}else {
			return id_klienta_indywidualnego;
		}
		
	}
	public void setId_klienta_indywidualnego(Integer id_klienta_indywidualnego) {
		if(id_klienta_indywidualnego==-1) {
			this.id_klienta_indywidualnego = null;
		}else {
			this.id_klienta_indywidualnego = id_klienta_indywidualnego;
		}
	}
	public Integer getId_klienta_zbiorowego() {
		if(id_klienta_zbiorowego == null) {
			return -1;
		}else {
			return id_klienta_zbiorowego;
		}
	}
	public void setId_klienta_zbiorowego(Integer id_klienta_zbiorowego) {
		if(id_klienta_zbiorowego==-1) {
			this.id_klienta_zbiorowego = null;
		}else {
			this.id_klienta_zbiorowego = id_klienta_zbiorowego;
		}
	}
	@Override
	public String toString() {
		return "Rezerwacja [id_rezerwacji:" + id_rezerwacji + ", data_rozpoczecia:" + data_rozpoczecia
				+ ", data_zakonczenia:" + data_zakonczenia + ", id_kortu:" + id_kortu + ", uwagi:" + uwagi
				+ ", id_klienta_indywidualnego:" + id_klienta_indywidualnego + ", id_klienta_zbiorowego:"
				+ id_klienta_zbiorowego + "]";
	}
	
}
