package example.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		//EJEMPLO 1
		
		//1 Crear un objeto de forma normal
		//Calculadora service = new Calculadora();
		
		//2 Recibir un objeto de Spring
		Calculadora calculadoraSpring = (Calculadora) context.getBean("calculadora");
		
		Calculadora calculadora = (Calculadora) context.getBean("calculadora");
		String texto = calculadora.holaMundo();
		System.out.println(texto);
		
		//EJEMPLO 2
		GestorFacturas gestor = (GestorFacturas) context.getBean("gestorFacturas");
		System.out.println(gestor.calculadora.holaMundo());
		
		//CONCEPTO 3
		//Se crea el objeto y se reutiliza en toda la application;
		
		
	}
}
