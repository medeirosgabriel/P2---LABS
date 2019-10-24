package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Compra;
import entidades.Produto;

class TesteCompra {
	
	private Compra compra1;
	private Compra compra2;
	private Compra compra3;
	
	@BeforeEach
	void constroiCompras() {
		compra1 = new Compra("Gabriel", "Adidas", "23/05/2018", new Produto("Tapioca", "Com queijo", 10.50));
		compra2 = new Compra("Daniel", "Nike", "25/03/2016", new Produto("Pao", "Com presunto", 15.80));
		compra3 = new Compra("Tadeu", "Oakley", "30/03/2013", new Produto("Coca cola", "Light", 10.05));
	}

	@Test
	void testeToString() {
		assertEquals("Tapioca - 23-05-2018", compra1.toString());
		assertEquals("Pao - 25-03-2016", compra2.toString());
		assertEquals("Coca cola - 30-03-2013", compra3.toString());
	}
	
	@Test
	void testeRepresentacaoOrdenaPorCliente() {
		assertEquals("Gabriel, Adidas, Com queijo, 23/05/2018", compra1.representacaoOrdenaPorCliente());
		assertEquals("Daniel, Nike, Com presunto, 25/03/2016", compra2.representacaoOrdenaPorCliente());
		assertEquals("Tadeu, Oakley, Light, 30/03/2013", compra3.representacaoOrdenaPorCliente());
	}
	
	@Test
	void testeRepresentacaoOrdenaPorFornecedor() {
		assertEquals("Adidas, Gabriel, Com queijo, 23/05/2018", compra1.representacaoOrdenaPorFornecedor());
		assertEquals("Nike, Daniel, Com presunto, 25/03/2016", compra2.representacaoOrdenaPorFornecedor());
		assertEquals("Oakley, Tadeu, Light, 30/03/2013", compra3.representacaoOrdenaPorFornecedor());
	}
	
	@Test
	void testeRepresentacaoOrdenaPorData() {
		assertEquals("23/05/2018, Gabriel, Adidas, Com queijo", compra1.representacaoOrdenaPorData());
		assertEquals("25/03/2016, Daniel, Nike, Com presunto", compra2.representacaoOrdenaPorData());
		assertEquals("30/03/2013, Tadeu, Oakley, Light", compra3.representacaoOrdenaPorData());
	}
}
