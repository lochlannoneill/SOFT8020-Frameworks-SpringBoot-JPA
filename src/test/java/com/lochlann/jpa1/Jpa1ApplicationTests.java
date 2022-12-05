package com.lochlann.jpa1;

import com.lochlann.jpa1.dao.StudioRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")  // * finds DataLoaderTest.java
class Jpa1ApplicationTests {

	@Autowired
	private StudioRepo studioRepo;

	@Order(1)
	@Test
	void countStudios() {
		Assertions.assertEquals(4, studioRepo.count());
	};

	// ! this is causing issues with separate test for 'count'
	@Order(2)
	@Test
	void deleteStudioExistingId() {
		int testId = 4;
		Assertions.assertTrue(studioRepo.findById(testId).isPresent());
		studioRepo.deleteById(testId);
		Assertions.assertTrue(studioRepo.findById(testId).isEmpty());
	};

	@Test
	void deleteStudioNotExistingId() {
		int testId = 99;
		Assertions.assertThrows(EmptyResultDataAccessException.class, ()->studioRepo.deleteById(testId));
	};

}
