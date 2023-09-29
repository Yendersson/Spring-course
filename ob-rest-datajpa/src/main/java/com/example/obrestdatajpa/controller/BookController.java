package com.example.obrestdatajpa.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

@RestController
public class BookController {
	
	private final Logger log = LoggerFactory.getLogger(BookController.class);
	
	private BookRepository bookRepository;
	
	public BookController(BookRepository bookRepository) { 
		this.bookRepository = bookRepository;
	}
	
	@GetMapping("/api/books")
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/api/books/{id}")
	public ResponseEntity<Book> findOneById(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		
		Optional<Book> bookOpt = bookRepository.findById(id);
		
		if (bookOpt.isPresent()) {
			return ResponseEntity.ok(bookOpt.get());
		} else { 
			return ResponseEntity.notFound().build();
		}
		//return bookOpt.orElse(null);
	}
	
	@PostMapping("/api/books")
	public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
		System.out.println(headers.get("User-Agent"));
		
		if (book.getId() != null) {
			log.warn("trying to create a book with id");
			return ResponseEntity.badRequest().build();
		}
		Book result = bookRepository.save(book);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/api/books")
	public ResponseEntity<Book> update(@RequestBody Book book) {
		if (book.getId() == null) {
			log.warn("Trying to update a non existent book");
			return ResponseEntity.badRequest().build();
		}
		if (!bookRepository.existsById(book.getId())) {
			log.warn("Trying to update a non existent book");
			return ResponseEntity.notFound().build();
		}
		
		Book  result = bookRepository.save(book); 
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/api/books/{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		if (!bookRepository.existsById(id)) {
			log.warn("Trying to update a non existent book");
			return ResponseEntity.notFound().build();
		}
		
		bookRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/books")
	public ResponseEntity<Book> deleteAll(){
		log.info("REST Request Deleting all books");
		bookRepository.deleteAll();
		
		return ResponseEntity.noContent().build(); 
	}
	
}

