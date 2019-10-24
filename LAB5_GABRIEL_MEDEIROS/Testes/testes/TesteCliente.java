package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ControllerFornecedor;
import entidades.Cliente;
import entidades.Produto;

class TesteCliente {

	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Cliente cliente4;
	private ControllerFornecedor contFornecedor;
	
	
	@BeforeEach
	void constroiFornecedor() {
		cliente1 = new Cliente("14815482398", "Gabriel", "gabriel@gmail.com", "LSD");
		cliente2 = new Cliente("21581453254", "Fabio", "fabio@gmail.com", "SPLAB");
		cliente3 = new Cliente("21581453254", "Tadeu", "tadeu@gmail.com", "SPLAB");
		cliente4 = new Cliente("14815482398", "Mariana", "mariana@gmail.com", "SPLAB");
		contFornecedor = new ControllerFornecedor();
	}
	
	@Test
	void testeToString() {
		assertEquals("Gabriel - LSD - gabriel@gmail.com", cliente1.toString());
		assertEquals("Fabio - SPLAB - fabio@gmail.com", cliente2.toString());
	}

	@Test
	void testeEquals() {
		assertFalse(cliente1.equals(cliente2));
		assertTrue(cliente1.equals(cliente4));
		assertTrue(cliente2.equals(cliente3));
		assertFalse(cliente3.equals(cliente4));
	}
	
	@Test
	void testGetDebito() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		cliente1.adicionaCompra("Gabriel","Adidas", "21/04/2019", new Produto("Tapioca", "Tapioca com queijo", 8.5));
		cliente1.adicionaCompra("Gabriel", "Adidas", "21/04/2019", new Produto("Misto", "Queijo e presunto", 10.5));
		assertEquals(19.00, cliente1.getDebito("Adidas"));
	}

	@Test
	void testGetDebitoClienteSemDebitoComFornecedor() {
		try {
			cliente1.getDebito("Nike");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao recuperar debito: cliente nao tem debito com fornecedor.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContas() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		cliente1.adicionaCompra("Gabriel", "Adidas", "21/04/2019", new Produto("Tapioca", "Tapioca com queijo", 8.5));
		cliente1.adicionaCompra("Gabriel", "Adidas", "24/06/2019", new Produto("Misto", "Queijo e presunto", 10.5));
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 21-04-2019 | Misto - 24-06-2019", cliente1.exibeContas("Adidas"));
	}
	
	@Test
	void testeExibeContasClienteNaoTemContaComFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			cliente1.exibeContas("Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.", iae.getMessage());
		}
	}
	
	
	@Test
	void testeExibeContasClientes() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		cliente1.adicionaCompra("Gabriel", "Adidas", "21/04/2019", new Produto("Tapioca", "Tapioca com queijo", 8.5));
		cliente1.adicionaCompra("Gabriel", "Adidas", "24/06/2019", new Produto("Misto", "Queijo e presunto", 10.5));
		cliente1.adicionaCompra("Gabriel", "Nike", "27/08/2018", new Produto("Coca cola", "Light", 4.5));
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 21-04-2019 | Misto - 24-06-2019 | Nike | Coca cola - 27-08-2018", cliente1.exibeContasClientes());
	}
	
	@Test
	void testeExibeContasClientesClienteSemContaComFornecedor() {
		try {
			cliente1.exibeContasClientes();
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.", iae.getMessage());
		}
	}

	@Test
	void testeAuxiliaExibeContas() {
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 21-04-2019 | Misto - 24-06-2019", cliente1.auxiliaExibeContas("Cliente: Gabriel | Adidas | Tapioca - 21-04-2019 | Misto - 24-06-2019 | "));
	}
}
