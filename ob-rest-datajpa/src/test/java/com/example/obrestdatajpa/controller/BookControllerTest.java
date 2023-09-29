package com.example.obrestdatajpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.obrestdatajpa.entities.Book;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
	
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	void setUp() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
		testRestTemplate = new TestRestTemplate(restTemplateBuilder);
	}
	
	@DisplayName("Endpoint GET /api/books")
	@Test
	void findAll() {
		ResponseEntity<Book[]> response = 
				testRestTemplate.getForEntity("/api/books", Book[].class);
		 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		List<Book> books =Arrays.asList(response.getBody());
		System.out.println(books.size());	
	}
	
	@DisplayName("Endpoint GET /api/books/1")
	@Test
	void findOneById() {
		ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/1", Book.class);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@DisplayName("Endpoint POST /api/books")
	@Test
	void create() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		String json = "{\"title\":\"title updated from spring test\", \"author\":\"Autor updated\", \"pages\": 200, \"price\": 20.9, \"releaseData\":\"2013-12-01\", \"online\": true}";
		
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
		
		Book results = response.getBody();
		
		assertEquals(1L, results.getId());
		assertEquals("title updated from spring test", results.getTitle());
	}
	
	@DisplayName("Endpoint PUT /api/books")
	@Test
	void update() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String json = "{\"id\":\"1\",\"title\":\"title updated from spring test\", \"author\":\"Autor updated\", \"pages\": 200, \"price\": 20.9, \"releaseData\":\"2013-12-01\", \"online\": true}";
		
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.PUT, request, Book.class);
	
		Book result = response.getBody();
		assertEquals(1L, result.getId());
	}
	
	@DisplayName("Endpoint DELETE /api/books/2")
	@Test
	void deleteOne() {
		ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/2", Book.class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@DisplayName("Endpoint DELETE /api/books")
	@Test
	void deleteAll() {
		ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books", Book.class);
	
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
}
