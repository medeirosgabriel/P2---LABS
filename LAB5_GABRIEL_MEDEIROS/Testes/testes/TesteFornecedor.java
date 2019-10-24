package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ControllerFornecedor;
import entidades.Fornecedor;
import entidades.IdProduto;
import entidades.Produto;
import entidades.ProdutoCombo;

class TesteFornecedor {
	
	private Fornecedor fornecedor1;
	private Fornecedor fornecedor2;
	private ControllerFornecedor contFornecedor;
	

	@BeforeEach
	void constroiFornecedor() {
		fornecedor1 = new Fornecedor("Nike", "nike@gmail.com", "987631232");
		fornecedor2 = new Fornecedor("Adidas", "adidas@gmail.com", "932462342");
		contFornecedor = new ControllerFornecedor();
	}
	
	@Test
	void testeCadastrarProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 11.7);
		assertTrue(fornecedor1.getProdutos().containsKey(new IdProduto("Tapioca", "Tapioca com queijo")));
		assertTrue(fornecedor1.getProdutos().containsKey(new IdProduto("Misto", "Queijo e presunto")));
	}
	
	@Test
	void testeCadastrarProdutoExistente() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		try {
			fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: produto ja existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeConsultaProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		assertEquals("Tapioca - Tapioca com queijo - R$10,50", fornecedor1.consultarProduto("Tapioca", "Tapioca com queijo"));
	}
	
	@Test
	void testeConsultaProdutoInexistente() {
		try {
			fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaPrecoProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 11.7);
		fornecedor1.editarPrecoProduto("Misto", "Queijo e presunto", 13.4);
		fornecedor1.editarPrecoProduto("Tapioca", "Tapioca com queijo", 11.5);
		assertEquals(13.4, fornecedor1.getProdutos().get(new IdProduto("Misto", "Queijo e presunto")).getPreco());
		assertEquals(11.5, fornecedor1.getProdutos().get(new IdProduto("Tapioca", "Tapioca com queijo")).getPreco());
	}
	
	@Test
	void testeEditaPrecoProdutoInexistente() {
		try {
			fornecedor1.editarPrecoProduto("Misto", "Queijo e presunto", 13.4);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeImprimirProdutos() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 11.7);
		assertEquals("Nike - Misto - Queijo e presunto - R$11,70 | Nike - Tapioca - Tapioca com queijo - R$10,50", fornecedor1.imprimirProdutos());
	}
	
	
	@Test
	void testToString() {
		assertEquals("Nike - nike@gmail.com - 987631232", fornecedor1.toString());
		assertEquals("Adidas - adidas@gmail.com - 932462342", fornecedor2.toString());
	}
	
	@Test
	void testeRemoverProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 10.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 11.7);
		fornecedor1.removerProduto("Tapioca", "Tapioca com queijo");
		assertFalse(fornecedor1.getProdutos().containsKey(new IdProduto("Tapioca", "Tapioca com queijo")));
		fornecedor1.removerProduto("Misto", "Queijo e presunto");
		assertFalse(fornecedor1.getProdutos().containsKey(new IdProduto("Misto", "Queijo e presunto")));
	}
	
	@Test
	void testeRemoverProdutoInexistente() {
		try {
			fornecedor1.removerProduto("Tapioca", "Tapioca com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao de produto: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEquals() {
		Fornecedor fornecedor3 = new Fornecedor("Nike", "nikert@gmail.com", "943223332");
		Fornecedor fornecedor4 = new Fornecedor("Nike", "nikebr@gmail.com", "133253452");
		Fornecedor fornecedor5 = new Fornecedor("Adidas", "adidas@gmail.com", "234324324");
		assertTrue(fornecedor1.equals(fornecedor4));
		assertTrue(fornecedor2.equals(fornecedor5));
		assertTrue(fornecedor3.equals(fornecedor4));
		assertFalse(fornecedor4.equals(fornecedor5));
		assertFalse(fornecedor3.equals(fornecedor5));
	}
	
	@Test
	void testeCadastraCombo() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 8.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 10.5);
		fornecedor1.cadastraCombo("Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
	}
	
	@Test
	void testeCadastraComboExistente() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 8.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 10.5);
		fornecedor1.cadastraCombo("Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		try {
			fornecedor1.cadastraCombo("Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: combo ja existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaCombo() {
		fornecedor1.cadastrarProduto("Tapioca", "Tapioca com queijo", 8.5);
		fornecedor1.cadastrarProduto("Misto", "Queijo e presunto", 10.5);
		fornecedor1.cadastraCombo("Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		fornecedor1.editaCombo("Tapioca e misto", "Bom demais papai", 0.50);
		assertTrue(((ProdutoCombo) fornecedor1.getProdutos().get(new IdProduto("Tapioca e misto", "Bom demais papai"))).getFator() == 0.5);
	}
	
	@Test
	void testeEditaComboInexistente() {
		try {
			fornecedor1.editaCombo("Misto e cafe", "Cafe com leite", 0.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de combo: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void procuraProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Com queijo", 10.5);
		assertTrue(fornecedor1.procuraProduto("Tapioca", "Com queijo", "Produto nao encontrado"));
	}
	
	@Test
	void procuraProdutoInexistente() {
		try {
			fornecedor1.procuraProduto("Tapioca", "Com queijo", "Produto nao encontrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Produto nao encontrado", iae.getMessage());
		}
	}
	
	
	@Test
	void retornaProduto() {
		fornecedor1.cadastrarProduto("Tapioca", "Com queijo", 10.5);
		assertEquals(new Produto("Tapioca", "Com queijo", 10.5).toString(), fornecedor1.retornaProduto("Tapioca", "Com queijo", "Produto nao encontrado").toString());
	}
	
	@Test
	void retornaProdutoInexistente() {
		try {
			fornecedor1.retornaProduto("Tapioca", "Com queijo", "Produto nao encontrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Produto nao encontrado", iae.getMessage());
		}
	}
}
