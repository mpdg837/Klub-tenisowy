package com.example.KlubTenisowy.Pracownic;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class PracownicyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = "PRACOWNICY";
	private final String idName = "ID_PRACOWNIKA";
	
	public PracownicyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	

	/* Import java.util.List */ 
	public List<Pracownik> list(String like,String filtr){
		
		
		String sql =  "SELECT * FROM " + tableName + " ORDER BY "+ idName+ " ASC";
		
		switch(filtr) {
			case "T":
				 sql =  "SELECT * FROM " + tableName + " WHERE STANOWISKO = 'Trener' ORDER BY "+ idName+ " ASC";
				break;
			case "B":
				sql =  "SELECT * FROM " + tableName + " WHERE STANOWISKO = 'Pracownik biurowy' ORDER BY "+ idName+ " ASC";
				
				break;
			case "I":
				
				sql =  "SELECT * FROM " + tableName + " WHERE STANOWISKO = 'Inny' ORDER BY "+ idName+ " ASC";
			
				break;

			
		}
		
		String[] lS = like.split("_");
		
		boolean koniec = false;
		
		if(like.length()>0) {
			
			switch(filtr) {
				case "T":
					
					if(lS.length == 1) {
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) LIKE LOWER('"+like+"%') AND STANOWISKO = 'Trener' ORDER BY "+ idName+ " ASC";
					}else if(lS.length == 2){
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) = LOWER('"+lS[0]+"') AND LOWER(NAZWISKO) LIKE LOWER('"+lS[1]+"%') AND STANOWISKO = 'Trener' ORDER BY "+ idName+ " ASC";
					}else {
						if(like.length()>1) {
							koniec = true;
						}
					}
					
					break;
				case "B":
					
					if(lS.length == 1) {
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) LIKE LOWER('"+like+"%') AND STANOWISKO = 'Pracownik biurowy' ORDER BY "+ idName+ " ASC";
					}else if(lS.length == 2){
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) = LOWER('"+lS[0]+"') AND LOWER(NAZWISKO) LIKE LOWER('"+lS[1]+"%') AND STANOWISKO = 'Pracownik biurowy' ORDER BY "+ idName+ " ASC";
					}else {
						if(like.length()>1) {
							koniec = true;
						}
					}
					
					break;
				case "I":
					
					if(lS.length == 1) {
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) LIKE LOWER('"+like+"%') AND STANOWISKO = 'Inny' ORDER BY "+ idName+ " ASC";
					}else if(lS.length == 2){
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) = LOWER('"+lS[0]+"') AND LOWER(NAZWISKO) LIKE LOWER('"+lS[1]+"%') AND STANOWISKO = 'Inny' ORDER BY "+ idName+ " ASC";
					}else {
						if(like.length()>1) {
							koniec = true;
						}
					}
					
					break;
				default:
					if(lS.length == 1) {
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) LIKE LOWER('"+like+"%') ORDER BY "+ idName+ " ASC";
					}else if(lS.length == 2){
						sql =  "SELECT * FROM " + tableName + " WHERE LOWER(IMIE) = LOWER('"+lS[0]+"') AND LOWER(NAZWISKO) LIKE LOWER('"+lS[1]+"%') ORDER BY "+ idName+ " ASC";
					}else {
						if(like.length()>1) {
							koniec = true;
						}
					}
					break;
			}
			
			
		}
		if(!koniec) {
			List<Pracownik> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pracownik.class)); 
			
			if(lista.size() > 0) {
				return lista;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/* Insert – wstawianie nowego wiersza do bazy */
	public void save(Pracownik pracownik) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.withTableName(tableName).usingColumns("ID_PRACOWNIKA","IMIE","NAZWISKO","DRUGIE_IMIE","PLEC","PESEL"
				,"DATA_URODZENIA","DATA_ZATRUDNIENIA","STANOWISKO","NUMER_TELEFONU","ADRES_EMAIL","NUMER_BIURA");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
		insert.execute(param);
	}
	
	/* Read – odczytywanie danych z bazy */
	public Pracownik get(int id) {
		String sql = "SELECT * FROM "+tableName +" WHERE " + idName + " = " + id;
		
		List<Pracownik> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Pracownik.class)); 
		if(lista.size()>0) {
			return lista.get(0);
		}else {
			return null;
		}
		
	}
	
	
	/* Update – aktualizacja danych */
	public void update(Pracownik pracownik) {
		
		if(pracownik.getDataUrodzenia().length() == 0) {
			String updateQuery = "update "+tableName+" set IMIE = ? ,  NAZWISKO = ? , DRUGIE_IMIE = ?,  "
					+ "ADRES_EMAIL = ? ,  PESEL = ? , NUMER_TELEFONU = ? , STANOWISKO = ? where "+idName+" = ?";
			
			jdbcTemplate.update(updateQuery, pracownik.getImie() , pracownik.getNazwisko(), pracownik.getDrugieImie(), pracownik.getAdresEmail(), pracownik.getPESEL(), 
					pracownik.getNumerTelefonu(), pracownik.getStanowisko() , pracownik.getIdPracownika());
			
		}else {
			String updateQuery = "update "+tableName+" set IMIE = ? ,  NAZWISKO = ? , DRUGIE_IMIE = ? ,  DATA_URODZENIA = ? ,  "
					+ "ADRES_EMAIL = ? ,  PESEL = ? , NUMER_TELEFONU = ? , STANOWISKO = ? where "+idName+" = ?";
			jdbcTemplate.update(updateQuery, pracownik.getImie() , pracownik.getNazwisko(), pracownik.getDrugieImie(), 
					pracownik.getDataUrodzenia(), pracownik.getAdresEmail(), pracownik.getPESEL(), pracownik.getNumerTelefonu(), pracownik.getStanowisko() , pracownik.getIdPracownika());
		}
		
	}
	/* Delete – wybrany rekord z danym id */
	public void delete(int id) {
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ?";
		jdbcTemplate.update(sql,id);
	}
}
