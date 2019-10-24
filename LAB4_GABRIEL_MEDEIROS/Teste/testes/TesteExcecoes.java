
package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.Excecao;

class TesteExcecoes {
	
	private Excecao excecao;


	@BeforeEach
	void test() {
		excecao = new Excecao();
	}
	

	@Test
	void validarString() {
		
		try {
			excecao.validarEntrada(null);
		}catch(NullPointerException npe) {
			assertEquals("Entrada Nula", npe.getMessage());
		}
		
		try {
			excecao.validarEntrada("12414324", null);
		}catch(NullPointerException npe) {
			assertEquals("Entrada Nula", npe.getMessage());
		}
		
		try {
			excecao.validarEntrada("232322", "      ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Entrada Invalida", iae.getMessage());
		}
		
		try {
			excecao.validarEntrada(null, "     ");
		}catch(NullPointerException npe) {
			assertEquals("Entrada Nula", npe.getMessage());
		}
		
		try {
			excecao.validarEntrada(null, null, "Computacao");;
		}catch(NullPointerException npe) {
			assertEquals("Entrada Nula", npe.getMessage());
		}
		
		try {
			excecao.validarEntrada("      " , "     ", "Medicina");
		}catch(IllegalArgumentException iae) {
			assertEquals("Entrada Invalida", iae.getMessage());
		}
	}
}
