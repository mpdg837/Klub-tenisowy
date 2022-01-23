package com.example.KlubTenisowy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



	@Repository
	public class Kluby_tenisoweDAO {

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		private final String tableName = "KLUBY_TENISOWE";
		private final String idName = "ID_KLUBU";
		
		public Kluby_tenisoweDAO(JdbcTemplate jdbcTemplate) {
			super();
			this.jdbcTemplate = jdbcTemplate;
		}
		
		/* Import java.util.List */ 
		public List<Kluby_tenisowe> list(){
			
			String sql = "SELECT * FROM " + tableName;
			
			List<Kluby_tenisowe> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Kluby_tenisowe.class)); 
			return lista;
		}
		/* Insert – wstawianie nowego wiersza do bazy */
		public void save(Kluby_tenisowe sale) {
		}
		/* Read – odczytywanie danych z bazy */
		public Kluby_tenisowe get(int id) {
			String sql = "SELECT * FROM WHERE " + idName + " = " + id;
			
			List<Kluby_tenisowe> lista = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Kluby_tenisowe.class)); 
			if(lista.size()>0) {
				return lista.get(0);
			}else {
				return null;
			}
		}
		
		/* Update – aktualizacja danych */
		public void update(Kluby_tenisowe sale) {
		}
		/* Delete – wybrany rekord z danym id */
		public void delete(int id) {
		}
	}
