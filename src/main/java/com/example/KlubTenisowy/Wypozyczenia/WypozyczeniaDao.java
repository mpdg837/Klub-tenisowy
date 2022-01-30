package com.example.KlubTenisowy.Wypozyczenia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.KlubTenisowy.Pracownic.Pracownik;
import com.example.KlubTenisowy.Weryfikacja.Daty;
import com.example.KlubTenisowy.Wyplaty.Wyplaty;
import com.example.KlubTenisowy.Wyplaty.WyplatyPodglad;
import com.example.KlubTenisowy.Wyplaty.WyplatyWyciag;

@Repository
public class WypozyczeniaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "WYPOZYCZENIA";
	private final String idName = "ID_WYPOZYCZENIA";
	
	public WypozyczeniaDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	/* Import java.util.List */ 
	
	public List<WypozyczenieSave> listCzas(boolean pilka,String odDaty, String doDaty, int id){
		String sql =   "SELECT * FROM "+tableName+" WHERE ID_RAKIETY = "+id+
				" AND DATA_WYPOZYCZENIA BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
				+" ORDER BY "+ idName+ " ASC";
		
		if(pilka) {
			sql =  "SELECT * FROM "+tableName+" WHERE ID_PILKI = "+id+
					" AND DATA_WYPOZYCZENIA BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
					+" ORDER BY "+ idName+ " ASC";
		}
		
		List<WypozyczenieSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(WypozyczenieSave.class)); 
	
		String sqlx =   "SELECT * FROM "+tableName+" WHERE ID_RAKIETY = " + id+
				" AND SPODZIEWANA_DATA_ZWROTU BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
				+" ORDER BY "+ idName+ " ASC";
		
		if(pilka) {
			sqlx =  "SELECT * FROM "+tableName+" WHERE ID_PILKI = " + id+
					" AND SPODZIEWANA_DATA_ZWROTU BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
					+" ORDER BY "+ idName+ " ASC";
		}
		
		List<WypozyczenieSave> listax = jdbcTemplate.query(sqlx,BeanPropertyRowMapper.newInstance(WypozyczenieSave.class)); 
		List<WypozyczenieSave> listao = new ArrayList<>();
		
		for(WypozyczenieSave wyp: lista) {
			listao.add(wyp);
		}

		for(WypozyczenieSave wyp: listax) {
			listao.add(wyp);
		}
		
		if(lista.size() > 0) {
			return listao;
		}else {
			return null;
		}
		
	}
	
	public List<WypozyczenieSave> listFiltr(boolean pilka,int id){
		String sql =  "SELECT * FROM "+tableName+" WHERE ID_RAKIETY = "+id+" ORDER BY "+ idName+ " ASC";
		
		if(pilka) {
			sql =  "SELECT * FROM "+tableName+" WHERE ID_PILKI = "+id+" ORDER BY "+ idName+ " ASC";
		}
		
		List<WypozyczenieSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(WypozyczenieSave.class)); 
	
		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}
		
	}

	public List<Wypozyczenie> list(){
		
		String sql =  "SELECT * FROM "+tableName+" ORDER BY "+ idName+ " ASC";
		
		
		List<Wypozyczenie> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Wypozyczenie.class)); 
	
		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}
		
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Wypozyczenie pracownik) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		WypozyczenieSave wyp = new WypozyczenieSave(pracownik.idWypozyczenia,pracownik.dataWypozyczenia,pracownik.spodziewanaDataZwrotu,pracownik.uwagi,pracownik.idPilki,pracownik.idRakiety,pracownik.idKlientaIndywidualnego,pracownik.idKlientaZbiorowego);
		insert.withTableName(tableName).usingColumns("ID_WYPOZYCZENIA","DATA_WYPOZYCZENIA","SPODZIEWANA_DATA_ZWROTU","UWAGI","ID_PILKI","ID_RAKIETY","ID_KLIENTA_INDYWIDUALNEGO","ID_KLIENTA_ZBIOROWEGO");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wyp);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Wypozyczenie get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Wypozyczenie> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Wypozyczenie.class)); 
		
		if(lista.size() > 0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Wypozyczenie pracownik) {
		String updateQuery = "update "+tableName+" set DATA_WYPOZYCZENIA = ?, SPODZIEWANA_DATA_ZWROTU = ?, UWAGI = ?, ID_PILKI = ?, ID_RAKIETY = ?, ID_KLIENTA_INDYWIDUALNEGO = ?,ID_KLIENTA_ZBIOROWEGO =? WHERE "+idName+" = ?";
		
		jdbcTemplate.update(updateQuery, pracownik.dataWypozyczenia,pracownik.spodziewanaDataZwrotu,pracownik.uwagi,pracownik.idPilki,pracownik.idRakiety,pracownik.idKlientaIndywidualnego,pracownik.idKlientaZbiorowego,pracownik.idWypozyczenia);
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
	
}