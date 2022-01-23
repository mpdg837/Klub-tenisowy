package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
		
		String sql = "SELECT * FROM " + tableName + " ORDER BY "+ idName+ " ASC";
		
		List<DaneKontatkowe> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(DaneKontatkowe.class)); 
		return lista;
	}
	/* Insert – wstawianie nowego wiersza do bazy */
	
	public int save(DaneKontatkowe sale) {
		
		List<DaneKontatkowe> konta= list();
		
		if(konta.size()>0) {
			DaneKontatkowe kontakt = konta.get(konta.size()-1);
			sale.idDaneKontaktowe = kontakt.idDaneKontaktowe + 1;
		}else {
			
			sale.idDaneKontaktowe = 0;
		}
		
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName(tableName).usingColumns("ID_DANE_KONTAKTOWE","NUMER_TELEFONU","ADRES_EMAIL");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);

		insertActor.execute(param);
		
		return sale.idDaneKontaktowe;
		
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
