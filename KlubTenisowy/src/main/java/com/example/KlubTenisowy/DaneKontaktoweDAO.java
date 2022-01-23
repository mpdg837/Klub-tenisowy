package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DaneKontaktoweDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "DANE_KONTATKOWE";
	private final String idName = "ID_DANE_KONTAKTOWE";
	
	public DaneKontaktoweDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<DaneKontatkowe> list(){
		
		String sql = "SELECT * FROM " + tableName;
		
		List<DaneKontatkowe> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(DaneKontatkowe.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Kluby_tenisowe sale) {
	}
	/* Read – odczytywanie danych z bazy */
	public DaneKontatkowe get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<DaneKontatkowe> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(DaneKontatkowe.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	/* Update – aktualizacja danych */
	public void update(DaneKontatkowe sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}
