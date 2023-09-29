package example.com;

import org.springframework.stereotype.Component;

@Component
public class Calculadora {
	
	public Calculadora() {
		System.out.println("Ejecutando constructor Calculadora");
	}

	public String holaMundo() {
		return "Hola Mundo!";
	}
}
