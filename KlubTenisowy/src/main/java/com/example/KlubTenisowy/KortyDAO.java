package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KortyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "KORTY";
	private final String idName = "ID_KORTU";
	
	public KortyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Korty> list(){
		
		String sql = "SELECT * FROM " + tableName;
		
		List<Korty> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Korty.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Korty sale) {
	}
	/* Read – odczytywanie danych z bazy */
	public Korty get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Korty> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Korty.class)); 
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
