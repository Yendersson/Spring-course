package com.example.opspringcifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionTest {
	
	/**
	 * BCrypt genera su propio salt de 16 bytes
	 * 
	 * El resltadp de cifrar con bcrypt sera un string 60 caracteres:
	 * 
	 * $a version
	 * $10 fuerza (valor que va de 4 a 31, por defecto vale 10)
	 * Los 22 siguientes caracteres son el salt generado
	 */
	@Test
	void bcryptTest() {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("admin");
		System.out.println(hashedPassword);
	}
}
