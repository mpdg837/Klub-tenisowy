package com.example.KlubTenisowy.Wyplaty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.KlubTenisowy.Pracownic.Pracownik;

@Repository
public class WyplatyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "WYPLATY";
	private final String idName = "ID_WYPLATY";
	
	public WyplatyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public WyplatyWyciag getS(int id){
		
		String sql =  "SELECT w.ID_WYPLATY, p.IMIE, w.DATA_WYPLATY, p.NAZWISKO, p.STANOWISKO, w.STAWKA_PODSTAWOWA, w.PREMIA, w.DODATEK_OKOLICZNOSCIOWY  "
				+ "FROM " + tableName + " w, PRACOWNICY p WHERE w.ID_PRACOWNIKA = p.ID_PRACOWNIKA AND w.ID_WYPLATY=" +id+" ORDER BY "+ idName+ " ASC";
	
		
		List<WyplatyWyciag> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(WyplatyWyciag.class)); 
		
		if(lista.size() > 0) {
			return lista.get(0);
		}else {
			return null;
		}
		
		
	}
	public List<WyplatyPodglad> list(String dataPoczatek, String dataKoniec){
		
		String sql =  "SELECT w.ID_WYPLATY, p.IMIE, w.DATA_WYPLATY, p.NAZWISKO, p.STANOWISKO, w.STAWKA_PODSTAWOWA, w.PREMIA, w.DODATEK_OKOLICZNOSCIOWY  "
				+ "FROM " + tableName + " w, PRACOWNICY p WHERE w.ID_PRACOWNIKA = p.ID_PRACOWNIKA ORDER BY "+ idName+ " ASC";
		
		if(dataPoczatek.length() > 0 && dataKoniec.length() > 0) {
			sql =  "SELECT w.ID_WYPLATY, p.IMIE, w.DATA_WYPLATY, p.NAZWISKO, p.STANOWISKO, w.STAWKA_PODSTAWOWA, w.PREMIA, w.DODATEK_OKOLICZNOSCIOWY  "
					+ "FROM " + tableName + " w, PRACOWNICY p WHERE w.ID_PRACOWNIKA = p.ID_PRACOWNIKA AND "
							+ "DATA_WYPLATY BETWEEN to_date('"+dataPoczatek+"', 'yyyy-mm-dd') AND to_date('"+dataKoniec+"', 'yyyy-mm-dd')"
							+ "ORDER BY "+ idName+ " ASC";
		}
		
		List<WyplatyWyciag> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(WyplatyWyciag.class)); 
		List<WyplatyPodglad> podglad = new ArrayList<>();
		
		
		int n=0;
		for(WyplatyWyciag wypl : lista) {
			WyplatyPodglad podg = new WyplatyPodglad();
			
			podg.setIndex(n);
			podg.idWyplaty = wypl.idWyplaty;
			podg.imie = wypl.imie;
			podg.nazwisko = wypl.nazwisko;
			podg.stanowisko = wypl.stanowisko;
			podg.dataWyplaty = wypl.dataWyplaty;
			podg.wyplataLacznie = wypl.getDodatekOkolicznosciowy() + wypl.getPremia() + wypl.getStawkaPodstawowa();
			
			podglad.add(podg);
			
			n++;
		}
		
		if(lista.size() > 0) {
			return podglad;
		}else {
			return null;
		}
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Wyplaty pracownik) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_WYPLATY","ID_PRACOWNIKA","DATA_WYPLATY","STAWKA_PODSTAWOWA","PREMIA","DODATEK_OKOLICZNOSCIOWY");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Wyplaty get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Wyplaty> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Wyplaty.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Pracownik pracownik) {
		
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
	/* Delete – wybrany rekord z danym id */
	public void deletePracownika(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE ID_PRACOWNIKA = ?";
		jdbcTemplate.update(sql,id);
	}
}