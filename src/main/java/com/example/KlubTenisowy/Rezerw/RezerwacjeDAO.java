package com.example.KlubTenisowy.Rezerw;

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
public class RezerwacjeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "REZERWACJE_KORTOW";
	private final String idName = "ID_REZERWACJI";
	
	public RezerwacjeDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public List<Rezerwacja> list(){
		
		String sql =  "SELECT  ID_REZERWACJI,DATA_ROZPOCZECIA,DATA_ZAKONCZENIA,ID_KORTU,UWAGI,ID_KLIENTA_INDYWIDUALNEGO, ID_KLIENTA_ZBIOROWEGO FROM " + tableName;
	
		
		List<Rezerwacja> listaBiura = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Rezerwacja.class)); 
		
		
		return listaBiura;
	
		
	}

	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Rezerwacja biuro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_REZERWACJI", "DATA_ROZPOCZECIA", "DATA_ZAKONCZENIA", "ID_KORTU","UWAGI", "ID_KLIENTA_INDYWIDUALNEGO", "ID_KLIENTA_ZBIOROWEGO");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Rezerwacja get(int id) {
		String sql = "SELECT * FROM "+tableName;
		
		List<Rezerwacja> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Rezerwacja.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Rezerwacja biuro) {
		String sql = "UPDATE KORTY SET NAWIERZCHNIA = ?, STAN = ?, DLUGOSC = ?,SZEROKOSC = ?, ULICA = ?,NUMER_BUDYNKU = ?,NUMER_MIESZKANIA = ?,MIEJSCOWOSC = ?,KOD_POCZTOWY = ? WHERE ID_KORTU= ?";
		jdbcTemplate.update(sql);
	}
		
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
}
