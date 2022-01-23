package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WynagrodzeniaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "WYNAGRODZENIA";
	private final String idName = "ID_WYNAGRODZENIA";
	
	public WynagrodzeniaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Wynagrodzenia> list(){
		
		String sql =  "SELECT * FROM " + tableName + " ORDER BY "+ idName+ " ASC";
		
		List<Wynagrodzenia> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Wynagrodzenia.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Korty sale) {
	}
	/* Read – odczytywanie danych z bazy */
	public Wynagrodzenia get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Wynagrodzenia> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Wynagrodzenia.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	/* Update – aktualizacja danych */
	public void update(Wynagrodzenia sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}
