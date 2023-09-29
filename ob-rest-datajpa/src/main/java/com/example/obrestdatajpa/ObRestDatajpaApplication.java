package com.example.obrestdatajpa;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		
		BookRepository repository = context.getBean(BookRepository.class);
		
		Book book1 = new Book(null, "Homo Deus", "Yuval Nova", 450, 29.90, LocalDate.of(2013,12,1), true);
		Book book2 = new Book(null, "Homo Sapiens", "Yuval Nova", 450, 19.90, LocalDate.of(2013,12,1), true);
		
		repository.save(book1);
		repository.save(book2);
		
		System.out.println("Num de libros en base de datos " +repository.findAll().size());
	}

}
