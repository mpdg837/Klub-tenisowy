package com.example.KlubTenisowy.Rezerw;

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
import com.example.KlubTenisowy.Wypozyczenia.WypozyczenieSave;

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

	public List<RezerwacjaSave> listCzas(String odDaty, String doDaty, int id){
		String sql =   "SELECT * FROM "+tableName+" WHERE ID_REZERWACJI = "+id+
				" AND DATA_ROZPOCZECIA BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
				+" ORDER BY "+ idName+ " ASC";


		List<RezerwacjaSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 

		String sqlx =   "SELECT * FROM "+tableName+" WHERE ID_REZERWACJI = " + id+
				" AND DATA_ZAKONCZENIA BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
				+" ORDER BY "+ idName+ " ASC";


		List<RezerwacjaSave> listax = jdbcTemplate.query(sqlx,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 
		List<RezerwacjaSave> listao = new ArrayList<>();

		for(RezerwacjaSave wyp: lista) {
			listao.add(wyp);
		}

		for(RezerwacjaSave wyp: listax) {
			listao.add(wyp);
		}

		if(lista.size() > 0) {
			return listao;
		}else {
			return null;
		}

	}

	public List<RezerwacjaSave> listFiltr(int id){
		String sql =  "SELECT * FROM "+tableName+" WHERE ID_KORTU= "+id+" ORDER BY DATA_ROZPOCZECIA ASC";

		List<RezerwacjaSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 

		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}

	}
	public List<RezerwacjaSave> listCzasA(String odDaty, String doDaty){

		String sql =   "SELECT * FROM "+tableName+
				" WHERE DATA_ROZPOCZECIA BETWEEN to_date('"+odDaty+"', 'yyyy-mm-dd') AND to_date('"+doDaty+"', 'yyyy-mm-dd')"
				+" ORDER BY "+ idName+ " ASC";



		List<RezerwacjaSave> listax = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 

		if(listax.size() > 0) {
			return listax;
		}else {
			return null;
		}

	}
	public List<RezerwacjaSave> list(){

		String sql =  "SELECT * FROM "+tableName+" ORDER BY "+ idName+ " ASC";


		List<RezerwacjaSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 

		if(lista.size() > 0) {
			return lista;
		}else {
			return null;
		}

	}

	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Rezerwacja pracownik) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		RezerwacjaSave wyp = new RezerwacjaSave(pracownik.id_rezerwacji,pracownik.data_rozpoczecia,pracownik.data_zakonczenia,pracownik.id_kortu,pracownik.uwagi,pracownik.id_klienta_indywidualnego,pracownik.id_klienta_zbiorowego);
		insert.withTableName(tableName).usingColumns("ID_REZERWACJI","DATA_ROZPOCZECIA","DATA_ZAKONCZENIA","ID_KORTU","UWAGI","ID_KLIENTA_INDYWIDUALNEGO","ID_KLIENTA_ZBIOROWEGO");

		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wyp);
		insert.execute(param);
	}

	/* Read – odczytywanie danych z bazy */



	public RezerwacjaSave get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;

		List<RezerwacjaSave> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(RezerwacjaSave.class)); 

		if(lista.size() > 0) {
			return lista.get(0);
		}else {
			return null;
		}

	}

	/* Update – aktualizacja danych */
	public void update(Rezerwacja pracownik) {
		String updateQuery = "update "+tableName+" set DATA_WYPOZYCZENIA = ?, SPODZIEWANA_DATA_ZWROTU = ?, ID_KORTU = ?,UWAGI = ?, ID_KLIENTA_INDYWIDUALNEGO = ?,ID_KLIENTA_ZBIOROWEGO =? WHERE "+idName+" = ?";

		jdbcTemplate.update(updateQuery, pracownik.id_rezerwacji,pracownik.data_rozpoczecia,pracownik.data_zakonczenia,pracownik.id_kortu,pracownik.uwagi,pracownik.id_klienta_indywidualnego,pracownik.id_klienta_zbiorowego);

	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}


	public void deleteKort(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE ID_KORTU = ?";
		jdbcTemplate.update(sql,id);
	}
	
}