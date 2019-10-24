package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import entidades.Compra;
import entidades.Conta;
import entidades.Produto;

class TesteConta {
	
	Conta conta1 = new Conta("Adidas");

	@Test
	void testeAdicionaCompra() {
		assertTrue(conta1.getCompras().isEmpty());
		conta1.adicionaCompra(new Compra("Gabriel", "Adidas", "23/07/2019", new Produto("Tapioca", "Tapioca com queijo", 8.5)));
		assertEquals(8.5, conta1.getPreco());
		conta1.adicionaCompra(new Compra("Gabriel", "Adidas", "23/04/2012", new Produto("Coca cola", "Light", 2.5)));
		assertEquals(11.0, conta1.getPreco());
		assertFalse(conta1.getCompras().isEmpty());
	}
	
	@Test
	void testeExibeContas() {
		conta1.adicionaCompra(new Compra("Gabriel", "Adidas", "23/07/2019", new Produto("Tapioca", "Tapioca com queijo", 8.5)));
		conta1.adicionaCompra(new Compra("Gabriel", "Adidas", "23/04/2012", new Produto("Coca cola", "Light", 2.5)));
		assertEquals("Adidas | Tapioca - 23-07-2019 | Coca cola - 23-04-2012", conta1.exibeContas());
	}
}
