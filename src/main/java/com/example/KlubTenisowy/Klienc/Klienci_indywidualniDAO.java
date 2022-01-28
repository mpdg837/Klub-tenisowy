package com.example.KlubTenisowy.Klienc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.KlubTenisowy.Pracownic.Pracownik;

@Repository
public class Klienci_indywidualniDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "KLIENCI_INDYWIDUALNI";
	private final String idName = "ID_KLIENTA_INDYWIDUALNEGO";
	
	public Klienci_indywidualniDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public List<Klient_indywidualny> list1(){
		
		String sql =  "SELECT  NUMER_TELEFONU, ADRES_EMAIL, ULICA, NUMER_BUDYNKU, NUMER_MIESZKANIA, MIEJSCOWOSC, KOD_POCZTOWY FROM " + tableName;
	
		
		List<Klient_indywidualny> listaKlienta_indywidualnego = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_indywidualny.class)); 
		
		
		return listaKlienta_indywidualnego;
	
		
	}

	public List<Klient_indywidualny> list2(){
		
		String sql =  "SELECT  ID_KLIENTA_INDYWIDUALNEGO, IMIE, NAZWISKO, DRUGIE_IMIE, PLEC FROM " + tableName;
	
		
		List<Klient_indywidualny> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_indywidualny.class)); 
		
		
		return listaBiura;
	
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Klient_indywidualny biuro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_KLIENTA_INDYWIDUALNEGO", "IMIE", "NAZWISKO", "DRUGIE_IMIE", "PLEC","NUMER_TELEFONU", "ADRES_EMAIL", "ULICA", "NUMER_BUDYNKU", "NUMER_MIESZKANIA", "MIEJSCOWOSC", "KOD_POCZTOWY");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Klient_indywidualny get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Klient_indywidualny> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_indywidualny.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Klient_indywidualny biuro) {
		String sql = "UPDATE BIURA SET NUMER_TELEFONU = ?,ADRES_EMAIL = ?, ULICA = ?,NUMER_BUDYNKU = ?,NUMER_MIESZKANIA = ?,MIEJSCOWOSC = ?,KOD_POCZTOWY = ? WHERE ID_BIURA = ?";
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
}
