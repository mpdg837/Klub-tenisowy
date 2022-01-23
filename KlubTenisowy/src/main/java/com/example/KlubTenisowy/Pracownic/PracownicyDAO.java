package com.example.KlubTenisowy.Pracownic;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PracownicyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "PRACOWNICY";
	private final String idName = "ID_PRACOWNICY";
	
	public PracownicyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* Import java.util.List */ 
	public List<Pracownicy> list(){
		
		String sql = "SELECT * FROM " + tableName;
		
		List<Pracownicy> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pracownicy.class)); 
		return lista;
	}
	
	public List<PracownikPod> listPod(){
		String sql = "SELECT p.ID_PRACOWNIKA, biu.ID_BIURA, k.NAZWA_KLUBU, o.IMIE, o.NAZWISKO, dk.NUMER_TELEFONU, p.PESEL "
				+ "    FROM PRACOWNICY p, OSOBY o, DANE_KONTATKOWE dk, ADRESY adr, KLUBY_TENISOWE k, BIURA biu "
				+ "    WHERE p.ID_KLUBU = k.ID_KLUBU AND p.ID_BIURA = biu.ID_BIURA AND p.ID_OSOBY = o.ID_OSOBY AND p.ID_DANE_KONTAKTOWE = dk.ID_DANE_KONTAKTOWE";
		
		List<PracownikPod> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(PracownikPod.class)); 
		return lista;
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Pracownicy pracownik) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName).usingColumns("PESEL","DATA_ZATRUDNIENIA","ID_KLUBU","ID_BIURA","ID_OSOBY","ID_DANE_KONTAKTOWE");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Pracownicy get(int id) {
		String sql = "SELECT * FROM WHERE " + idName + " = " + id;
		
		List<Pracownicy> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pracownicy.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Pracownicy sale) {
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
	}
}
