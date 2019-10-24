package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import controllers.ControllerCliente;
import controllers.ControllerFornecedor;
import entidades.Produto;

import org.junit.jupiter.api.Test;

class TesteControllerCliente {
	
	private ControllerCliente contCliente;
	private ControllerFornecedor contFornecedor;
	
	@BeforeEach
	void constroiControllerCliente() {
		contCliente = new ControllerCliente();
		contFornecedor = new ControllerFornecedor();
	}
	
	@Test
	void testeCadastraCliente() {
		assertEquals("54545222399", contCliente.cadastrarCliente("54545222399", "Gabriel", "gabriel@gmail.com", "LSD"));
		assertTrue(contCliente.getClientes().containsKey("54545222399"));
		assertEquals("98473435677", contCliente.cadastrarCliente("98473435677", "Tadeu", "tadeu@gmail.com", "SPLAB"));
		assertTrue(contCliente.getClientes().containsKey("98473435677"));
	}
	
	@Test
	void testeCadastraClienteClienteJaExiste() {
		try {
			contCliente.cadastrarCliente("54545222399", "Daniel", "daniel@gmail.com", "SPLAB");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: cliente ja existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeCadastraClienteParametrosNulosOuVazios() {
		try {
			contCliente.cadastrarCliente("           ", "Daniel", "daniel@gmail.com", "SPLAB");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}	
		try {
			contCliente.cadastrarCliente("54545222399", "Daniel", "           ", "SPLAB");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contCliente.cadastrarCliente("54545222399", "Daniel", "daniel@gmail.com", "          ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contCliente.cadastrarCliente(null, "Daniel", "daniel@gmail.com", "SPLAB");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contCliente.cadastrarCliente("54545222399", "Daniel", null, "SPLAB");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contCliente.cadastrarCliente("5454522", "Daniel", "daniel@gmail.com", "SPLAB");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: cpf invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeConsultaCliente() {
		contCliente.cadastrarCliente("54545266223", "Gabriel", "gabriel@gmail.com", "LSD");
		assertEquals("Gabriel - LSD - gabriel@gmail.com", contCliente.consultarCliente("54545266223"));
	}
	
	@Test
	void testeConsultaClienteClienteNaoExiste() {
		try {
			contCliente.consultarCliente("43216789234");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao do cliente: cliente nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeConsultaClienteCpfInvalido() {
		try {
			contCliente.consultarCliente("43216");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao do cliente: cpf invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeConsultaClienteParametrosNulosOuInvalidos() {
		try {
			contCliente.consultarCliente("        ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contCliente.consultarCliente(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	@Test
	void testeImprimeClientes() {
		assertEquals("", contCliente.imprimirClientes());
		contCliente.cadastrarCliente("54545222344", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.cadastrarCliente("54353454455", "Daniel", "daniel@gmail.com", "SPLAB");
		contCliente.cadastrarCliente("55534344434", "Tadeu", "tadeu@gmail.com", "SPLAB");
		assertEquals("Daniel - SPLAB - daniel@gmail.com | "
					+ "Gabriel - LSD - gabriel@gmail.com | "
					+ "Tadeu - SPLAB - tadeu@gmail.com", contCliente.imprimirClientes());
	}
	
	@Test
	void testeRemoveCliente() {
		contCliente.cadastrarCliente("54545222366", "Gabriel", "gabriel@gmail.com", "LSD");
		assertTrue(contCliente.removerCliente("54545222366"));
	}
	
	@Test
	void testeRemoveClienteClienteNaoExiste() {
		try {
			contCliente.removerCliente("97438234477");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao do cliente: cliente nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeRemoveClienteParametorsNulosOuVazios() {
		try {
			contCliente.removerCliente("          ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo", iae.getMessage());
		}
		try {
			contCliente.removerCliente(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo", npe.getMessage());
		}
	}
	
	@Test
	void testeEditaCliente() {
		contCliente.cadastrarCliente("54545222333", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.cadastrarCliente("98473435674", "Tadeu", "tadeu@gmail.com", "SPLAB");
		assertTrue(contCliente.editarCliente("54545222333", "email", "fabiano@gmail.com"));
		assertEquals("fabiano@gmail.com", contCliente.getClientes().get("54545222333").getEmail());
		assertTrue(contCliente.editarCliente("98473435674", "email",  "rebeca@gmail.com"));
		assertEquals("rebeca@gmail.com", contCliente.getClientes().get("98473435674").getEmail());
		assertTrue(contCliente.editarCliente("54545222333", "nome", "Humberto"));
		assertEquals("Humberto", contCliente.getClientes().get("54545222333").getNome());
	}
	
	@Test
	void testeEditaClienteClienteNaoExiste() {
		try {
			contCliente.editarCliente("98473443321", "nome", "Luiza");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: cliente nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaClienteAtributoDeOrdenacaoNaoExiste() {
		contCliente.cadastrarCliente("54545222333", "Gabriel", "gabriel@gmail.com", "LSD");
		try {
			contCliente.editarCliente("54545222333", "cidade", "Luiza");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: atributo nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaClienteCpfInvalido() {
		try {
			contCliente.editarCliente("5454522", "cidade", "Luiza");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: cpf invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaClienteParametrosNulosOuVazios() {
		try {
			contCliente.editarCliente("        ", "cidade", "Luiza");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contCliente.editarCliente("54545222333", "cidade", "      ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contCliente.editarCliente("54545222333", "      ", "Luiza");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contCliente.editarCliente("54545222333", null, "Luiza");
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contCliente.editarCliente(null, "cidade", "Luiza");
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	@Test
	void testeEditaEmail() {
		contCliente.cadastrarCliente("54545222333", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.cadastrarCliente("98473435674", "Tadeu", "tadeu@gmail.com", "SPLAB");
		assertTrue(contCliente.editarEmailCliente("54545222333", "fabiano@gmail.com"));
		assertEquals("fabiano@gmail.com", contCliente.getClientes().get("54545222333").getEmail());
		assertTrue(contCliente.editarEmailCliente("98473435674", "rebeca@gmail.com"));
		assertEquals("rebeca@gmail.com", contCliente.getClientes().get("98473435674").getEmail());
	}
	
	@Test
	void testeEditaNome() {
		contCliente.cadastrarCliente("54545222333", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.cadastrarCliente("98473435672", "Tadeu", "tadeu@gmail.com", "SPLAB");
		assertTrue(contCliente.editarNomeCliente("54545222333", "Humberto"));
		assertEquals("Humberto", contCliente.getClientes().get("54545222333").getNome());
		assertTrue(contCliente.editarNomeCliente("98473435672", "Luiza"));
		assertEquals("Luiza", contCliente.getClientes().get("98473435672").getNome());
	}
	
	@Test
	void testeEditaLocal() {
		contCliente.cadastrarCliente("54545244223", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.cadastrarCliente("98476634357", "Tadeu", "tadeu@gmail.com", "SPLAB");
		assertTrue(contCliente.editarLocalCliente("54545244223",  "SPLAB"));
		assertEquals("SPLAB", contCliente.getClientes().get("54545244223").getLocalTrabalho());
		assertTrue(contCliente.editarLocalCliente("98476634357", "IQUANTA"));
		assertEquals("IQUANTA", contCliente.getClientes().get("98476634357").getLocalTrabalho());
	}
	
	
	@Test
	void testeAdicionaCompra() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "423242342");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		contCliente.cadastrarCliente("32131212365", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 9.5), "23/05/2016");
		assertTrue(contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 10.5), "23/05/2016"));
	}
	
	
	
	@Test
	void testGetDebito() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "423242342");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		contCliente.cadastrarCliente("32131212365", "Gabriel", "gabriel@gmail.com", "LSD");
		contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 9.5), "23/05/2016");
		assertEquals(9.5, contCliente.getDebito("32131212365", "Adidas"));
		contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 9.5), "24/05/2016");
		assertEquals(19.0, contCliente.getDebito("32131212365", "Adidas"));
	}

	@Test
	void testeExibeContas() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "423242342");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		contCliente.cadastrarCliente("32131212365", "Gabriel", "gabriel@gmail.com", "LSD");
		assertTrue(contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 10.5), "23/05/2016"));
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 23-05-2016", contCliente.exibeContas("32131212365", "Adidas"));
		contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 9.5), "24/05/2016");
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 23-05-2016 | Tapioca - 24-05-2016", contCliente.exibeContas("32131212365", "Adidas"));
	}
	
	@Test
	void testeExibeContasClientes() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "423242342");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "423242342");
		contFornecedor.cadastrarProduto("Nike", "Pao", "Integral", 11.5);
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		contCliente.cadastrarCliente("32131212365", "Gabriel", "gabriel@gmail.com", "LSD");
		assertTrue(contCliente.adicionaCompra("32131212365", "Adidas", new Produto("Tapioca", "Com queijo", 10.5), "23/05/2016"));
		assertTrue(contCliente.adicionaCompra("32131212365", "Nike", new Produto("Pao", "Integral", 10.5), "26/07/2016"));
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 23-05-2016 | Nike | Pao - 26-07-2016", contCliente.exibeContasClientes("32131212365"));
	}
	
	@Test
	void testeProcuraCliente() {
		contCliente.cadastrarCliente("32131212365", "Gabriel", "gabriel@gmail.com", "LSD");
		assertTrue(contCliente.procuraCliente("32131212365", "Cliente nao cadastrado"));
	}
	
	@Test
	void testeProcuraClienteClienteNaoExiste() {
		try {
			contCliente.procuraCliente("32131212365", "Cliente nao cadastrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Cliente nao cadastrado", iae.getMessage());
		}
	}
	
	@Test
	void testePagarConta() {
		
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Fitness", 10.5);
		contCliente.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contCliente.adicionaCompra("32121154612", "Adidas", new Produto("Tapioca", "Fitness", 10.5), "23/03/2000");
		assertTrue(this.contCliente.pagarConta("32121154612", "Adidas"));
	}
	
	@Test
	void testePagarContaClienteNaoexiste() {
		try {
			this.contCliente.pagarConta("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: cliente nao existe.",iae.getMessage());
		}
	}
	
	@Test
	void testePagarContaClienteNoTemDebitoComOFornecedor() {
		contCliente.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			this.contCliente.pagarConta("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: nao ha debito do cliente associado a este fornecedor.",iae.getMessage());
		}
	}
	
	@Test
	void testeOrdenarPorCliente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "93273323");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Fitness", 13.5);
		contFornecedor.cadastrarProduto("Adidas", "Hamburguer", "Top", 12.5);
		contFornecedor.cadastrarProduto("Adidas", "Coca Cola", "Light", 10.5);
		contFornecedor.cadastrarProduto("Nike", "Suco", "Goiaba", 13.5);
		contFornecedor.cadastrarProduto("Nike", "Sprite", "Com gas", 15.5);
		contCliente.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contCliente.cadastrarCliente("32121153232", "Daniel", "daniel@gmail.com", "SPLAB");
		contCliente.cadastrarCliente("32121321122", "Tadeu", "tadeu@gmail.com", "LSD");
		contCliente.adicionaCompra("32121154612", "Adidas", new Produto("Tapioca", "Fitness", 13.5), "13/05/2016");
		contCliente.adicionaCompra("32121154612", "Adidas", new Produto("Hamburguer", "Top", 12.5), "13/03/2011");
		contCliente.adicionaCompra("32121154612", "Nike", new Produto("Suco", "Goiaba", 13.5), "13/04/2012");
		contCliente.adicionaCompra("32121153232", "Nike", new Produto("Sprite", "Com gas", 15.5), "13/07/2015");
		contCliente.adicionaCompra("32121153232", "Adidas", new Produto("Hamburguer", "Top", 12.5), "13/08/2017");
		contCliente.adicionaCompra("32121321122", "Nike", new Produto("Sprite", "Com gas", 15.5), "13/09/2013");
		contCliente.adicionaCompra("32121321122", "Adidas", new Produto("Coca Cola", "Light", 10.5), "13/10/2000");
		
		assertEquals("[Gabriel, Nike, Goiaba, 13/04/2012, Gabriel, Adidas, Fitness, 13/05/2016, Gabriel, Adidas, Top, 13/03/2011, "
				+ "Tadeu, Nike, Com gas, 13/09/2013, Tadeu, Adidas, Light, 13/10/2000, Daniel, Nike, Com gas, 13/07/2015, "
				+ "Daniel, Adidas, Top, 13/08/2017]", contCliente.listarCompras("Cliente").toString());
	}
}
