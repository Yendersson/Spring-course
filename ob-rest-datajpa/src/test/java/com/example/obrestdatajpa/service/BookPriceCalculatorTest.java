package com.example.obrestdatajpa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.obrestdatajpa.entities.Book;

class BookPriceCalculatorTest {
	
	@Test
	void calculatePrice() {
		//configuracion de la prueba
		Book book = new Book(1L, "el senor de los anillos", "author", 300, 49.99, LocalDate.now(), true);
		BookPriceCalculator calculator = new BookPriceCalculator();
		
		//Se ejecuta el comportamiento a testear
		double price = calculator.calculatePrice(book); 
		
		//Comprobaciones aseciones
		assertTrue(price > 0);
		
		
	}
	
}
