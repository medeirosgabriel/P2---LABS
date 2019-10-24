package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ControllerCentral;
import controllers.ControllerCliente;
import controllers.ControllerFornecedor;
import entidades.Produto;

class TesteControllerCentral {
	
	private ControllerCentral contCentral;
	private ControllerFornecedor contForn;
	private ControllerCliente contCli;
	
	@BeforeEach
	void constroiControllerCentral() {
		this.contForn = new ControllerFornecedor();
		this.contCli = new ControllerCliente();
		this.contCentral = new ControllerCentral(this.contCli, this.contForn);
	}
	
	@Test
	void testeAdicionaCompra() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		contCli.cadastrarCliente("32131232132", "Gabriel", "gabriel@gmail.com", "SPLAB");
		assertTrue(contCentral.adicionaCompra("32131232132", "Adidas", "23/05/2016", "Tapioca", "Com queijo"));
	}
	
	@Test
	void testeAdicionaCompraCpfInvalido() {
		try {
			contCentral.adicionaCompra("321312", "Adidas", "23/05/2016", "Tapioca", "Com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao cadastrar compra: cpf invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeAdicionaCompraProdutoInexistente() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contCentral.adicionaCompra("32131232132", "Adidas", "23/05/2016", "Tapioca", "Com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao cadastrar compra: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeAdicionaCompraClienteInexistente() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Com queijo", 10.5);
		try {
			contCentral.adicionaCompra("32131232132", "Adidas", "23/05/2016", "Tapioca", "Com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao cadastrar compra: cliente nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeAdicionaCompraParametrosNulosOuVazios() {

		try {
			contCentral.adicionaCompra("32131232132", "       ", "23/05/2016", "Tapioca", "Com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		
		try {
			contCentral.adicionaCompra("32131232132", "Adidas", "23/05/2016", "Tapioca", "      ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.", iae.getMessage());
		}
		
		try {
			contCentral.adicionaCompra("32131232132", "Adidas", "23/05/2016", null, "Com queijo");
		}catch(NullPointerException npe) {
			assertEquals("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.", npe.getMessage());
		}
		
		try {
			contCentral.adicionaCompra(null, "Adidas", "23/05/2016", "Tapioca", "Com queijo");
		}catch(NullPointerException npe) {
			assertEquals("Erro ao cadastrar compra: cpf nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	@Test
	void testGetDebito() {
		
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Fitness", 10.5);
		contCentral.adicionaCompra("32121154612", "Adidas", "23/03/2000", "Tapioca", "Fitness");
		assertEquals(10.5, contCentral.getDebito("32121154612", "Adidas"));
	}
	
	@Test
	void testGetDebitoFornecedorInexistente() {
		try{
			contCentral.getDebito("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao recuperar debito: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testGetDebitoClienteInexistente() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		
		try{
			contCentral.getDebito("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao recuperar debito: cliente nao existe.", iae.getMessage());
		}	
	}
	
	@Test
	void testGetDebitoCpfInvalido() {
		try{
			contCentral.getDebito("321211", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao recuperar debito: cpf invalido.", iae.getMessage());
		}	
	}
	
	@Test
	void testGetDebitoParametrosVaziosOuNulos() {
		try{
			contCentral.getDebito("      ", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}
		
		try{
			contCentral.getDebito("32121154612", null);
		}catch(NullPointerException npe) {
			assertEquals("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void testeExibeContas() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Fitness", 10.5);
		contCentral.adicionaCompra("32121154612", "Adidas", "23/03/2000", "Tapioca", "Fitness");
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 23-03-2000", contCentral.exibeContas("32121154612", "Adidas"));
	}
	
	@Test
	void testeExibeContasFornecedorInexistente() {
		try{
			contCentral.exibeContas("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasClienteInexistente() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		
		try{
			contCentral.exibeContas("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: cliente nao existe.", iae.getMessage());
		}	
	}
	
	@Test
	void testeExibeContasClienteNaoTemContaComOFornecedor() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		try{
			contCentral.exibeContas("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasCpfInvalido() {
		try{
			contCentral.exibeContas("3212115", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: cpf invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasParametrosNulosOuVazios() {
		try{
			contCentral.exibeContas("32121154612", null);
		}catch(NullPointerException npe) {
			assertEquals("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try{
			contCentral.exibeContas("           ", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try{
			contCentral.exibeContas("32121154612", "            ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasClientes() {
		
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		
		contForn.cadastrarProduto("Adidas", "Tapioca", "Fitness", 10.5);
		contCentral.adicionaCompra("32121154612", "Adidas", "23/03/2000", "Tapioca", "Fitness");
		contForn.cadastrarFornecedores("Nike", "nike@gmail.com", "321321332");
		contForn.cadastrarProduto("Nike", "Pao", "Light", 10.5);
		contCentral.adicionaCompra("32121154612", "Nike", "24/06/2000", "Pao", "Light");
		
		assertEquals("Cliente: Gabriel | Adidas | Tapioca - 23-03-2000 | Nike | Pao - 24-06-2000", contCentral.exibeContasClientes("32121154612"));
		
	}
	
	@Test
	void testeExibeContasClientesClienteInexistente() {
		try{
			contCentral.exibeContasClientes("32121154612");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir contas do cliente: cliente nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasClientesClienteNaoTemNenhumaConta() {
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		
		try{
			contCentral.exibeContasClientes("32121154612");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.", iae.getMessage());
		}
	}
	
	@Test
	void testeExibeContasClientesParametroNuloOuVazio() {
		try{
			contCentral.exibeContasClientes(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try{
			contCentral.exibeContasClientes("         ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.", iae.getMessage());
		}
	}
	
	@Test
	void testeOrdenarPor() {
		
		assertTrue(this.contCentral.ordenaPor("Cliente"));
		assertTrue(this.contCentral.ordenaPor("Fornecedor"));
		assertTrue(this.contCentral.ordenaPor("Data"));
	}
	
	@Test
	void testeOrdenarPorCriterioNaoOferecidoPeloSistema() {
		try {
			this.contCentral.ordenaPor("Descricao");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na listagem de compras: criterio nao oferecido pelo sistema.", iae.getMessage());
		}
	}
	
	@Test
	void testeOrdenarPorParametroNuloOuVazio() {
		try {
			this.contCentral.ordenaPor("    ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na listagem de compras: criterio nao pode ser vazio ou nulo." , iae.getMessage());
		}
		
		try {
			this.contCentral.ordenaPor(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na listagem de compras: criterio nao pode ser vazio ou nulo." , npe.getMessage());
		}
	}
	
	@Test
	void testePagarConta() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Fitness", 10.5);
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contCentral.adicionaCompra("32121154612", "Adidas", "23/03/2000", "Tapioca", "Fitness");
		assertTrue(contCentral.pagarConta("32121154612", "Adidas"));
	}
	
	@Test
	void testePagarContaParametrosNulosOuVazios() {
		try {
			this.contCentral.pagarConta(null, "Adidas");
		}catch(NullPointerException npe) {
			assertEquals("Erro no pagamento de conta: cpf nao pode ser vazio ou nulo.",npe.getMessage());
		}
		
		try {
			this.contCentral.pagarConta("32121154612", "      ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: fornecedor nao pode ser vazio ou nulo.",iae.getMessage());
		}
	}
	
	@Test
	void testePagarContaFornecedorInexistente() {
		try {
			this.contCentral.pagarConta("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: fornecedor nao existe.",iae.getMessage());
		}
	}
	
	@Test
	void testePagarContaClienteInexistente() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			this.contCentral.pagarConta("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: cliente nao existe.",iae.getMessage());
		}
	}
	
	@Test
	void testePagarContaNaoHaDebitoComOFornecedor() {
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			this.contCentral.pagarConta("32121154612", "Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no pagamento de conta: nao ha debito do cliente associado a este fornecedor.",iae.getMessage());
		}
	}
	
	@Test
	void testeListarCompras() {
		contForn.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contForn.cadastrarFornecedores("Nike", "nike@gmail.com", "93273323");
		contForn.cadastrarProduto("Adidas", "Tapioca", "Fitness", 13.5);
		contForn.cadastrarProduto("Adidas", "Hamburguer", "Top", 12.5);
		contForn.cadastrarProduto("Adidas", "Coca Cola", "Light", 10.5);
		contForn.cadastrarProduto("Nike", "Suco", "Goiaba", 13.5);
		contForn.cadastrarProduto("Nike", "Sprite", "Com gas", 15.5);
		contCli.cadastrarCliente("32121154612", "Gabriel", "gabriel@gmail.com", "SPLAB");
		contCli.cadastrarCliente("32121153232", "Daniel", "daniel@gmail.com", "SPLAB");
		contCli.cadastrarCliente("32121321122", "Tadeu", "tadeu@gmail.com", "LSD");
		contCli.adicionaCompra("32121154612", "Adidas", new Produto("Tapioca", "Fitness", 13.5), "13/05/2016");
		contCli.adicionaCompra("32121154612", "Adidas", new Produto("Hamburguer", "Top", 12.5), "13/03/2011");
		contCli.adicionaCompra("32121154612", "Nike", new Produto("Suco", "Goiaba", 13.5), "13/04/2012");
		contCli.adicionaCompra("32121153232", "Nike", new Produto("Sprite", "Com gas", 15.5), "13/07/2015");
		contCli.adicionaCompra("32121153232", "Adidas", new Produto("Hamburguer", "Top", 12.5), "13/08/2017");
		contCli.adicionaCompra("32121321122", "Nike", new Produto("Sprite", "Com gas", 15.5), "13/09/2013");
		contCli.adicionaCompra("32121321122", "Adidas", new Produto("Coca Cola", "Light", 10.5), "13/10/2000");
		
		this.contCentral.ordenaPor("Cliente");
		
		assertEquals("Daniel, Adidas, Top, 13/08/2017 | Daniel, Nike, Com gas, 13/07/2015 | Gabriel, Adidas, Fitness, 13/05/2016 | "
				+ "Gabriel, Adidas, Top, 13/03/2011 | Gabriel, Nike, Goiaba, 13/04/2012 | Tadeu, Adidas, Light, 13/10/2000 | "
				+ "Tadeu, Nike, Com gas, 13/09/2013", contCentral.listarCompras());
		
		this.contCentral.ordenaPor("Fornecedor");
		
		assertEquals("Adidas, Daniel, Top, 13/08/2017 | Adidas, Gabriel, Fitness, 13/05/2016 | Adidas, Gabriel, Top, 13/03/2011 | "
				+ "Adidas, Tadeu, Light, 13/10/2000 | Nike, Daniel, Com gas, 13/07/2015 | "
				+ "Nike, Gabriel, Goiaba, 13/04/2012 | Nike, Tadeu, Com gas, 13/09/2013", contCentral.listarCompras());
		
		this.contCentral.ordenaPor("Data");
		
		assertEquals("13/10/2000, Tadeu, Adidas, Light | 13/03/2011, Gabriel, Adidas, Top | 13/04/2012, Gabriel, Nike, Goiaba | "
				+ "13/09/2013, Tadeu, Nike, Com gas | 13/07/2015, Daniel, Nike, Com gas | "
				+ "13/05/2016, Gabriel, Adidas, Fitness | 13/08/2017, Daniel, Adidas, Top", contCentral.listarCompras());
	}
}
