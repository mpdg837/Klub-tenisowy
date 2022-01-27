package com.example.KlubTenisowy.Rakiety;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.KlubTenisowy.Pracownic.Pracownik;
import com.example.KlubTenisowy.Wyplaty.Wyplaty;
import com.example.KlubTenisowy.Wyplaty.WyplatyPodglad;
import com.example.KlubTenisowy.Wyplaty.WyplatyWyciag;

@Repository
public class RakietyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "RAKIETY";
	private final String idName = "ID_RAKIETY";
	
	public RakietyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	

	public List<Rakieta> list(String szukanie){
		
		String sql =  "SELECT * FROM "+tableName+" WHERE LOWER(NAZWA) LIKE LOWER('"+szukanie+"%') ORDER BY "+ idName+ " ASC";
		
		
		List<Rakieta> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Rakieta.class)); 
	
		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Rakieta pracownik) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_RAKIETY","NAZWA","MASA","DLUGOSC","SZEROKOSC");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Rakieta get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Rakieta> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Rakieta.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Rakieta rakieta) {
		String updateQuery = "update "+tableName+" set NAZWA = ? ,  MASA = ? , DLUGOSC = ?,  "
				+ "SZEROKOSC = ? where "+idName+" = ?";
		
		jdbcTemplate.update(updateQuery, rakieta.nazwa,rakieta.masa,rakieta.dlugosc,rakieta.szerokosc,rakieta.idRakiety);
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
}