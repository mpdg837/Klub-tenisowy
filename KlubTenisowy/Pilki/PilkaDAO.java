package com.example.KlubTenisowy.Pilki;

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
public class PilkaDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "PILKI";
	private final String idName = "ID_PILKI";
	
	public PilkaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	

	public List<Pilka> list(String szukanie){
		
		String sql =  "SELECT * FROM "+tableName+" WHERE LOWER(NAZWA) LIKE LOWER('"+szukanie+"%') ORDER BY "+ idName+ " ASC";
		
		
		List<Pilka> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pilka.class)); 
	
		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Pilka pracownik) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_PILKI","NAZWA","MASA","SREDNICA","MATERIAL");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Pilka get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Pilka> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pilka.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Pilka pracownik) {
		String updateQuery = "update "+tableName+" set NAZWA = ? ,  MASA = ? , SREDNICA = ?,  "
				+ "MATERIAL = ? where "+idName+" = ?";
		
		jdbcTemplate.update(updateQuery, pracownik.nazwa,pracownik.masa,pracownik.srednica,pracownik.material,pracownik.idPilki);
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
}