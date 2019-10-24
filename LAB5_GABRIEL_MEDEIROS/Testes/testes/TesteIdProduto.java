package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Fornecedor;
import entidades.IdProduto;

class TesteIdProduto {
	
	private IdProduto id1;
	private IdProduto id2;
	private IdProduto id3;
	private IdProduto id4;
	private IdProduto id5;
	private IdProduto id6;

	@BeforeEach
	void constroiFornecedor() {
		id1 = new IdProduto("Tapioca", "Com queijo e presunto");
		id2 = new IdProduto("Tapioca", "Com queijo e presunto");
		id3 = new IdProduto("Sanduiche", "So hamburguer");
		id4 = new IdProduto("Sanduiche", "So hamburguer");
		id5 = new IdProduto("Tapioca", "Com carne");
		id6 = new IdProduto("Hamburguer", "So hamburguer");
	}
	
	@Test
	void testeEquals() {
		assertTrue(id3.equals(id4));
		assertTrue(id1.equals(id2));
		assertFalse(id3.equals(id6));
		assertFalse(id4.equals(id6));
	}
}
