package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class OsobyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "OSOBY";
	private final String idName = "ID_OSOBY";
	
	public OsobyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Osoby> list(){
		
		String sql =  "SELECT * FROM " + tableName + " ORDER BY "+ idName+ " ASC";
		
		List<Osoby> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Osoby.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public int save(Osoby sale) {
		
		List<Osoby> osoby = list();
		if(osoby.size()>0) {
			Osoby osoba = osoby.get(osoby.size()-1);
			sale.idOsoby = osoba.idOsoby + 1;
		}else {
			
			sale.idOsoby = 0;
		}
		
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName(tableName).usingColumns("ID_OSOBY","IMIE","NAZWISKO","DRUGIE_IMIE","PLEC","DATA_URODZENIA");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
		
		insertActor.execute(param);
		
		return sale.idOsoby;
		
	}
	/* Read – odczytywanie danych z bazy */
	public Osoby get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Osoby> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Osoby.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	/* Update – aktualizacja danych */
	public void update(Korty sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}
