package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SprzetyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "SPRZETY";
	private final String idName = "ID_SPRZETU";
	
	public SprzetyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Sprzety> list(){
		
		String sql =  "SELECT * FROM " + tableName + " ORDER BY "+ idName+ " ASC";
		
		List<Sprzety> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Sprzety.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Korty sale) {
	}
	/* Read – odczytywanie danych z bazy */
	public Sprzety get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Sprzety> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Sprzety.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	/* Update – aktualizacja danych */
	public void update(Sprzety sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}

