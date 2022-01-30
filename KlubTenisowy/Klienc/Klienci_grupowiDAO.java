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
public class Klienci_grupowiDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "KlIENCI_GRUPOWI";
	private final String idName = "ID_KLIENTA_GRUPOWEGO";
	
	public Klienci_grupowiDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public List<Klient_grupowy> list1(){
		
		String sql =  "SELECT ID_KLIENTA_GRUPOWEGO, NUMER_TELEFONU, ADRES_EMAIL, ULICA, NUMER_BUDYNKU, NUMER_MIESZKANIA, MIEJSCOWOSC, KOD_POCZTOWY FROM " + tableName +" ORDER BY ID_KLIENTA_GRUPOWEGO ASC";
	
		
		List<Klient_grupowy> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_grupowy.class)); 
		
		
		return listaBiura;
	
		
	}
	public List<Klient_grupowy> list2(){
		
		String sql =  "SELECT  ID_KLIENTA_GRUPOWEGO, NAZWA, NIP, REGON FROM " + tableName+" ORDER BY ID_KLIENTA_GRUPOWEGO ASC";
	
		
		List<Klient_grupowy> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_grupowy.class)); 
		int n=0;
		for(Klient_grupowy klient: listaBiura) {
			klient.index = n;
			n++;
		}
		
		return listaBiura;
	
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Klient_grupowy biuro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_KLIENTA_GRUPOWEGO", "NAZWA", "NIP", "REGON","NUMER_TELEFONU", "ADRES_EMAIL", "ULICA", "NUMER_BUDYNKU", "NUMER_MIESZKANIA", "MIEJSCOWOSC", "KOD_POCZTOWY");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Klient_grupowy get(int id) {
		String sql = "SELECT * FROM "+tableName+ " WHERE "+idName+" = " + id;
		
		List<Klient_grupowy> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Klient_grupowy.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Klient_grupowy biuro) {
		String sql = "UPDATE KLIENCI_GRUPOWi SET NAZWA = ?, NIP = ?, REGON = ?,NUMER_TELEFONU = ?,ADRES_EMAIL = ?, ULICA = ?,NUMER_BUDYNKU = ?,NUMER_MIESZKANIA = ?,MIEJSCOWOSC = ?,KOD_POCZTOWY = ? WHERE ID_KLIENTA_GRUPOWEGO= ?";
		jdbcTemplate.update(sql, biuro.nazwa,biuro.nip,biuro.regon,biuro.numer_telefonu,biuro.adres_email,biuro.ulica,biuro.numer_budynku,biuro.numer_mieszkania,biuro.miejscowosc,biuro.kod_pocztowy,biuro.id_klienta_grupowego);
	}
		
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
}
