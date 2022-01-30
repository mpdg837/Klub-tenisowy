package com.example.KlubTenisowy.Rezerw;

public class Rezerwacja {
	int id_rezerwacji;
	String data_rozpoczecia;
	String data_zakonczenia;
	int id_kortu;
	String uwagi;
	int id_klienta_indywidualnego;
	int id_klienta_zbiorowego;
	public Rezerwacja() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rezerwacja(int id_rezerwacji, String data_rozpoczecia, String data_zakonczenia, int id_kortu, String uwagi,
			int id_klienta_indywidualnego, int id_klienta_zbiorowego) {
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
	public int getId_kortu() {
		return id_kortu;
	}
	public void setId_kortu(int id_kortu) {
		this.id_kortu = id_kortu;
	}
	public String getUwagi() {
		return uwagi;
	}
	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}
	public int getId_klienta_indywidualnego() {
		return id_klienta_indywidualnego;
	}
	public void setId_klienta_indywidualnego(int id_klienta_indywidualnego) {
		this.id_klienta_indywidualnego = id_klienta_indywidualnego;
	}
	public int getId_klienta_zbiorowego() {
		return id_klienta_zbiorowego;
	}
	public void setId_klienta_zbiorowego(int id_klienta_zbiorowego) {
		this.id_klienta_zbiorowego = id_klienta_zbiorowego;
	}
	@Override
	public String toString() {
		return "Rezerwacja [id_rezerwacji=" + id_rezerwacji + ", data_rozpoczecia=" + data_rozpoczecia
				+ ", data_zakonczenia=" + data_zakonczenia + ", id_kortu=" + id_kortu + ", uwagi=" + uwagi
				+ ", id_klienta_indywidualnego=" + id_klienta_indywidualnego + ", id_klienta_zbiorowego="
				+ id_klienta_zbiorowego + "]";
	}
	
}
