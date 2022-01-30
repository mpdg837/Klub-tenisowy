package com.example.KlubTenisowy.Kor;

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
	
	public List<Kort> list(){
		
		String sql =  "SELECT  ID_KORTU,NAWIERZCHNIA,STAN,DLUGOSC,SZEROKOSC,ULICA, NUMER_BUDYNKU, NUMER_MIESZKANIA, MIEJSCOWOSC, KOD_POCZTOWY FROM " + tableName;
	
		
		List<Kort> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Kort.class)); 
		
		
		return listaBiura;
	
		
	}

	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Kort biuro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_KORTU", "NAWIERZCHNIA", "STAN", "DLUGOSC","SZEROKOSC", "ULICA", "NUMER_BUDYNKU", "NUMER_MIESZKANIA", "MIEJSCOWOSC", "KOD_POCZTOWY");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Kort get(int id) {
		String sql = "SELECT * FROM "+tableName;
		
		List<Kort> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Kort.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Kort biuro) {
		String sql = "UPDATE KORTY SET NAWIERZCHNIA = ?, STAN = ?, DLUGOSC = ?,SZEROKOSC = ?, ULICA = ?,NUMER_BUDYNKU = ?,NUMER_MIESZKANIA = ?,MIEJSCOWOSC = ?,KOD_POCZTOWY = ? WHERE ID_KORTU= ?";
		jdbcTemplate.update(sql,biuro.nawierzchnia,biuro.stan,biuro.dlugosc,biuro.szerokosc,biuro.ulica,biuro.numer_budynku,biuro.numer_mieszkania,biuro.miejscowosc,biuro.kod_pocztowy,biuro.id_kortu);
	}
		
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
}
