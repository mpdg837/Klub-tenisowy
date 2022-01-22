package com.example.KlubTenisowy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

class AdresyDAOTest {

	private AdresyDAO dao;
	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf");
		datasource.setUsername("mpodgajn");
		datasource.setPassword("mpodgajn");
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
		dao = new AdresyDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {
		List<Adresy> listAdresy = dao.list();
		
		assertTrue(listAdresy.isEmpty());
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
