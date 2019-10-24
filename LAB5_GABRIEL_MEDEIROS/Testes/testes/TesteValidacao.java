package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controllers.Validacao;

class TesteValidacao {
	
	@Test
	void validarString() {
		Validacao.validarString("Adidas", "Tudo ok");
		try {
			Validacao.validarString("    ","Dado invalido");
		}catch(IllegalArgumentException iae) {
			assertEquals("Dado invalido", iae.getMessage());
		}
		try {
			Validacao.validarString(null,"Dado nulo");
		}catch(NullPointerException npe) {
			assertEquals("Dado nulo", npe.getMessage());
		}
		
	}
	
	@Test
	void validarCpf() {
		Validacao.validarCpf("12543534543", "Tudo ok");
		try {
			Validacao.validarString("     ", "Cpf invalido");
		}catch(IllegalArgumentException iae) {
			assertEquals("Cpf invalido", iae.getMessage());
		}
		try {
			Validacao.validarString("43534543", "Cpf invalido");
		}catch(IllegalArgumentException iae) {
			assertEquals("Cpf invalido", iae.getMessage());
		}
		try {
			Validacao.validarString(null, "Cpf nulo");
		}catch(NullPointerException npe) {
			assertEquals("Cpf nulo", npe.getMessage());
		}
	}
	
	@Test
	void validarPreco() {
		Validacao.validarPreco(15.67, "Tudo ok");
		try {
			Validacao.validarPreco(-15.00, "Preco invalido");
		}catch(IllegalArgumentException iae) {
			assertEquals("Preco invalido", iae.getMessage());
		}
	}
	
	@Test
	void testeValidarFator() {
		Validacao.validarFator(0.5, "tudo ok");
		try {
			Validacao.validarFator(-0.5, "ERROUUUUUU");
		}catch(IllegalArgumentException iae) {
			assertEquals("ERROUUUUUU", iae.getMessage());
		}
	}
}
