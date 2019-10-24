package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Fornecedor;
import entidades.Produto;

class TesteProduto {

	private Produto produto1;
	private Produto produto2;
	private Produto produto3;
	private Produto produto4;

	@BeforeEach
	void constroiFornecedor() {
		produto1 = new Produto("Sanduiche", "Sem verdura", 8.5);
		produto2 = new Produto("Acai", "Completo", 10.5);
		produto3 = new Produto("Acai", "Incompleto", 10.5);
		produto4 = new Produto("Acai", "Completo", 14.5);
	}

	@Test
	void testeToString() {
		assertEquals("Sanduiche - Sem verdura - R$8,50", produto1.toString());
		assertEquals("Acai - Completo - R$10,50", produto2.toString());
		
	}
	
	@Test
	void testeEquals() {
		assertFalse(produto1.equals(produto3));
		assertTrue(produto2.equals(produto4));
		assertFalse(produto1.equals(produto4));
		assertFalse(produto2.equals(produto1));
	}
}
