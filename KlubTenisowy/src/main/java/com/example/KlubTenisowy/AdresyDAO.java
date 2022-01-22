package com.example.KlubTenisowy;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdresyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "ADRESY";
	private final String idName = "ID_ADRESY";
	
	public AdresyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Adresy> list(){
		
		String sql = "SELECT * FROM " + tableName;
		
		List<Adresy> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Adresy.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Adresy sale) {
	}
	/* Read – odczytywanie danych z bazy */
	public Adresy get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Adresy> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Adresy.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	/* Update – aktualizacja danych */
	public void update(Adresy sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}
