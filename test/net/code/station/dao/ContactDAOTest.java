package net.code.station.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.code.station.model.Contact;

class ContactDAOTest {
	
    private DriverManagerDataSource dataSource;
    private ContactDAO dao;
    
    @BeforeEach
    void setupBeforeEach() {
    	dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("administrator");
		dataSource.setPassword("Aprill2015+Aprill2015");
		dao = new ContactDaoImpl(dataSource);
		
    }
    
	@Test
	void testSave() {
		//Contact contact = new Contact("Jule");
		//int result = dao.save(contact);
		
		//assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		//Contact contact = new Contact(2, "Carlen");
		//int result = dao.update(contact);
		//assertTrue(result > 0);
	}

	@Test
	void testGet() {
		Integer id = 1;
		Contact contact = dao.get(id);
		if(contact != null) {
			System.out.println(contact);
		}
		assertNotNull(contact);
	}

	@Test
	void testDelete() {
		Integer id = 4;
		int result = dao.delete(id);
		assertTrue(result > 0);
	}

	@Test
	void testList() {
		List<Contact> listContacts = dao.list();
		
		for (Contact aContact : listContacts) {
			System.out.println(aContact);
		}
		
		assertTrue(!listContacts.isEmpty());
	}

}
