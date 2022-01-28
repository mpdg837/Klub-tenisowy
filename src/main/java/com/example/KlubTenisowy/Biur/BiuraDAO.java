package com.example.KlubTenisowy.Biur;

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
public class BiuraDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "BIURA";
	private final String idName = "ID_BIURA";
	
	public BiuraDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public List<Biuro> list(){
		
		String sql =  "SELECT * FROM BIURA";
	
		
		List<Biuro> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Biuro.class)); 
		
		
		return listaBiura;
	
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Biuro biuro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_BIURA","NUMER_TELEFONU","ADRES_EMAIL","ULICA","NUMER_BUDYNKU","NUMER_MIESZKANIA","MIEJSCOWOSC","KOD_POCZTOWY");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Biuro get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Biuro> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Biuro.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Biuro biuro) {
		String sql = "UPDATE BIURA SET NUMER_TELEFONU = ?,ADRES_EMAIL = ?, ULICA = ?,NUMER_BUDYNKU = ?,NUMER_MIESZKANIA = ?,MIEJSCOWOSC = ?,KOD_POCZTOWY = ? WHERE ID_BIURA = ?";
		jdbcTemplate.update(sql, biuro.numer_telefonu,biuro.adres_email,biuro.ulica,biuro.numer_budynku,biuro.numer_mieszkania,biuro.miejscowosc,biuro.kod_pocztowy,biuro.id_biura);
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
}
