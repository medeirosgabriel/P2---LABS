package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ControllerCliente;
import controllers.ControllerFornecedor;
import entidades.IdProduto;
import entidades.Produto;
import entidades.ProdutoCombo;

class TesteControllerFornecedor {

	private ControllerFornecedor contFornecedor;
		
	@BeforeEach
	void constroiCrudFornecedor() {
		contFornecedor = new ControllerFornecedor();
	}
	
	@Test
	void testeCadastraFornecedor() {
		assertEquals("Adidas", contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "974736374"));
		assertTrue(contFornecedor.getFornecedores().containsKey("Adidas"));
		assertEquals("Nike", contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445"));
		assertTrue(contFornecedor.getFornecedores().containsKey("Nike"));
	}
	
	@Test
	void testeCadastraFornecedorExistente() {
		assertEquals("Adidas", contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "974736374"));
		try {
			contFornecedor.cadastrarFornecedores("Nike", "nikeamsterdam@gmail.com", "5324235445");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de fornecedor: fornecedor ja existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeCadastraFornecedorParametrosNulosOuVazios() {
		try {
			contFornecedor.cadastrarFornecedores("           ", "nikeamsterdam@gmail.com", "5324235445");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.cadastrarFornecedores("Nike", "nikeamsterdam@gmail.com", "         ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.cadastrarFornecedores("Nike", null, "5324235445");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.cadastrarFornecedores(null, "nikeamsterdam@gmail.com", "5324235445");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void testeConsultaFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		assertEquals("Adidas - adidas@gmail.com - 93273232", contFornecedor.consultarFornecedor("Adidas"));
		assertEquals("Nike - nike@gmail.com - 66465445", contFornecedor.consultarFornecedor("Nike"));
	}
	
	
	@Test
	void testeConsultaFornecedorInexistente() {
		try {
			contFornecedor.consultarFornecedor("Oakley");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao do fornecedor: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeConsultaFornecedorParametrosNulosOuVazios() {
		try {
			contFornecedor.consultarFornecedor("       ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao do fornecedor: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.consultarFornecedor(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na exibicao do fornecedor: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void testeImprimeFornecedores() {
		assertEquals("", contFornecedor.imprimirFornecedores());
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		assertEquals("Adidas - adidas@gmail.com - 93273232 | "
					+ "Nike - nike@gmail.com - 66465445", contFornecedor.imprimirFornecedores());
	}
	
	@Test
	void testeRemoveFornecedores() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		assertTrue(contFornecedor.removerFornecedor("Adidas"));
		assertTrue(contFornecedor.removerFornecedor("Nike"));
	}
	
	@Test
	void testeRemoveFornecedorInexistente() {
		try {
			contFornecedor.removerFornecedor("Adidas");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao do fornecedor: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		assertTrue(contFornecedor.editarFornecedor("Adidas", "email", "adidasbr@gmail.com"));
		assertEquals("adidasbr@gmail.com", contFornecedor.getFornecedores().get("Adidas").getEmail());
		assertTrue(contFornecedor.editarFornecedor("Adidas", "telefone", "99723212"));
		assertEquals("99723212", contFornecedor.getFornecedores().get("Adidas").getTelefone());
		assertTrue(contFornecedor.editarFornecedor("Adidas", "telefone", "99723212"));
		assertEquals("99723212", contFornecedor.getFornecedores().get("Adidas").getTelefone());
	}
	
	@Test
	void testeEditaFornecedorTentaEditarNome() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.editarFornecedor("Adidas", "nome", "adidasbr@gmail.com");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser editado.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaFornecedorFornecedorInexistente() {
		try {
			contFornecedor.editarFornecedor("Oackley", "email", "adidasbr@gmail.com");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do fornecedor: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaFornecedorAtributoInexistente() {
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		try {
			contFornecedor.editarFornecedor("Nike", "cidade", "adidasbr@gmail.com");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do fornecedor: atributo nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaFornecedorParametrosNulosOuVazios() {
		try {
			contFornecedor.editarFornecedor("         ", "cidade", "adidasbr@gmail.com");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.editarFornecedor("Nike", "            ", "adidasbr@gmail.com");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.editarFornecedor("Nike", "cidade", null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.editarFornecedor(null, "cidade", "adidasbr@gmail.com");
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	@Test
	void testeEditaEmailFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		assertTrue(contFornecedor.editarEmailFornecedor("Adidas", "adidasbr@gmail.com"));
		assertEquals("adidasbr@gmail.com", contFornecedor.getFornecedores().get("Adidas").getEmail());
		assertTrue(contFornecedor.editarEmailFornecedor("Nike", "nikebr@gmail.com"));
		assertEquals("nikebr@gmail.com", contFornecedor.getFornecedores().get("Nike").getEmail());
		assertFalse(contFornecedor.editarEmailFornecedor("Oackley", "oacleybr@gmail.com"));
	}
	
	@Test
	void testeEditaTelefoneFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273245");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465457");
		assertTrue(contFornecedor.editarTelefoneFornecedor("Adidas", "99723212"));
		assertEquals("99723212", contFornecedor.getFornecedores().get("Adidas").getTelefone());
		assertTrue(contFornecedor.editarTelefoneFornecedor("Nike", "434343242"));
		assertEquals("434343242", contFornecedor.getFornecedores().get("Nike").getTelefone());
		assertFalse(contFornecedor.editarTelefoneFornecedor("Oackley", "434343242"));
	}
	
	@Test
	void testeCadastraProduto() {
		assertEquals("Adidas", contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232"));
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5));
		assertTrue(contFornecedor.getFornecedores().get("Adidas").getProdutos().containsKey(new IdProduto("Tapioca", "Tapioca com queijo")));
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.50));
		assertTrue(contFornecedor.getFornecedores().get("Adidas").getProdutos().containsKey(new IdProduto("Misto", "Queijo e presunto")));
	}
	
	@Test
	void testeCadastraProdutoFornecedorInexistente() {
		try {
			contFornecedor.cadastrarProduto("Oakley", "Misto", "Queijo e presunto", 11.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeCadastraProdutoExistente() {
		assertEquals("Adidas", contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232"));
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5));
		try {
			contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 11.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: produto ja existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeCadastraProdutoParametrosVaziosOuNulos() {
		try {
			contFornecedor.cadastrarProduto("         ", "Tapioca", "Tapioca com queijo", 11.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.cadastrarProduto("Adidas", "Tapioca", "            ", 11.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contFornecedor.cadastrarProduto("Adidas", null, "Tapioca com queijo", 11.50);
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.cadastrarProduto("Adidas", "Tapioca", null, 11.50);
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", npe.getMessage());
		}
	}
	
	@Test
	void testeConsultaProduto() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5));
		assertEquals("Tapioca - Tapioca com queijo - R$8,50", contFornecedor.consultarProduto("Adidas", "Tapioca", "Tapioca com queijo"));
	}
	
	@Test
	void testeConsultaProdutoFornecedorInexistente() {
		try {
			contFornecedor.consultarProduto("Nike", "Tapioca", "Tapioca com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: fornecedor nao existe.", iae.getMessage());
		}
	}

	@Test
	void testeConsultaProdutoInexistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.consultarProduto("Adidas", "Cafe", "Cafe com leite");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: produto nao existe.", iae.getMessage());
		}
	}

	@Test
	void testeConsultaProdutoParametrosVaziosOuNulos() {
		try {
			contFornecedor.consultarProduto("         ", "Tapioca", "Tapioca com queijo");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.consultarProduto("Nike", "Tapioca", "          ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contFornecedor.consultarProduto(null, "Tapioca", "Tapioca com queijo");
		}catch(NullPointerException npe) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.consultarProduto("Nike", null, "Tapioca com queijo");
		}catch(NullPointerException npe) {
			assertEquals("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void testeImprimeProdutos() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		assertEquals("Adidas -", contFornecedor.imprimirProdutos("Adidas"));
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		assertEquals("Adidas - Misto - Queijo e presunto - R$10,50 | "
					+ "Adidas - Tapioca - Tapioca com queijo - R$8,50 | "
					+ "Adidas - Tapioca e misto - Bom demais papai - R$14,25", contFornecedor.imprimirProdutos("Adidas"));
	}
	
	
	@Test
	void testeImprimeProdutosFornecedorInexistente() {
		try {
			contFornecedor.imprimirProdutos("Nike");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeImprimeProdutosParametrosVaziosOuNulos() {
		try {
			contFornecedor.imprimirProdutos("      ");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.imprimirProdutos(null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	
	@Test 
	void testeImprimeTodosProdutos(){
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		contFornecedor.cadastrarFornecedores("Nike", "nike@gmail.com", "66465445");
		contFornecedor.cadastrarProduto("Nike", "x-tudo", "Completao", 10.5);
		contFornecedor.cadastrarProduto("Nike", "Hamburguer", "Bom demais", 12.5);
		contFornecedor.cadastrarProduto("Nike", "Suco", "Goiaba", 6.0);
		contFornecedor.cadastrarFornecedores("Oakley", "oakley@gmail.com", "66232445");
		contFornecedor.cadastrarProduto("Oakley", "x-egg", "Ruim demais", 13.5);
		assertEquals("Adidas - Misto - Queijo e presunto - R$10,50 | "
					+ "Adidas - Tapioca - Tapioca com queijo - R$8,50 | "
					+ "Adidas - Tapioca e misto - Bom demais papai - R$14,25 | "
					+ "Nike - Hamburguer - Bom demais - R$12,50 | "
					+ "Nike - Suco - Goiaba - R$6,00 | "
					+ "Nike - x-tudo - Completao - R$10,50 | "
					+ "Oakley - x-egg - Ruim demais - R$13,50", contFornecedor.imprimirTodosProdutos());
	}
	
	@Test
	void testeEditaPrecoProdutos() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5));
		assertTrue(contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5));
		assertTrue(contFornecedor.editarPrecoProduto("Tapioca", "Tapioca com queijo", "Adidas", 10.5));
		assertEquals(10.5, contFornecedor.getFornecedores().get("Adidas").getProdutos().
					get(new IdProduto("Tapioca", "Tapioca com queijo")).getPreco());
		assertTrue(contFornecedor.editarPrecoProduto("Misto", "Queijo e presunto", "Adidas", 11.7));
		assertEquals(11.7, contFornecedor.getFornecedores().get("Adidas").getProdutos().
				get(new IdProduto("Misto", "Queijo e presunto")).getPreco());
	}
	
	@Test
	void testeEditaPrecoProdutosProdutoInexistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.editarPrecoProduto("Coca cola", "Light", "Adidas",  11.7);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaPrecoProdutosFornecedorInexistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		try {
			contFornecedor.editarPrecoProduto("Misto", "Queijo e presunto", "Nike",  11.7);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaPrecoProdutosFatorInvalido() {
		try {
			contFornecedor.editarPrecoProduto("Coca cola", "Light", "Adidas",  -11.7);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: preco invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaPrecoProdutosParametroNuloOuVazio() {
		try {
			contFornecedor.editarPrecoProduto("            ", "Light", "Adidas",  11.7);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.editarPrecoProduto("Coca cola", "           ", "Adidas",  11.7);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de produto: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contFornecedor.editarPrecoProduto("Coca cola", "Light", null,  11.7);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.editarPrecoProduto(null, "Light", "Adidas",  11.7);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao de produto: nome nao pode ser vazio ou nulo.", npe.getMessage());
		}
		try {
			contFornecedor.editarPrecoProduto("Coca cola", null, "Adidas",  11.7);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao de produto: descricao nao pode ser vazia ou nula.", npe.getMessage());
		}
	}
	
	@Test
	void testeRemoveProduto() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		assertTrue(contFornecedor.removerProduto("Adidas", "Tapioca", "Tapioca com queijo"));
		assertFalse(contFornecedor.getFornecedores().get("Adidas").getProdutos().containsKey(new IdProduto("Tapioca", "Tapioca com queijo")));
		assertTrue(contFornecedor.removerProduto("Adidas", "Misto", "Queijo e presunto"));
		assertFalse(contFornecedor.getFornecedores().get("Adidas").getProdutos().containsKey(new IdProduto( "Misto", "Queijo e presunto")));
		assertTrue(contFornecedor.removerProduto("Adidas", "Tapioca e misto", "Bom demais papai"));
	}
	

	@Test
	void testeRemoveProdutoFornecedorInexistente() {
		try {
			contFornecedor.removerProduto("Nike", "Misto", "Queijo e presunto");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao de produto: fornecedor nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeRemoveProdutoProdutoInexistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.removerProduto("Adidas", "Misto", "Light");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao de produto: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeRemoveProdutoParametroVazioOuNulo() {
		try {
			contFornecedor.removerProduto("Adidas", "         ", "Light");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao de produto: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.removerProduto("        ", "Misto", "Light");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.removerProduto("Adidas", "Misto", null);
		}catch(NullPointerException npe) {
			assertEquals("Erro na remocao de produto: descricao nao pode ser vazia ou nula.", npe.getMessage());
		}
		try {
			contFornecedor.removerProduto(null, "Misto", "Light");
		}catch(NullPointerException npe) {
			assertEquals("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	
	@Test
	void testeCadastraCombo() {
		
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		
		
		try {
			contFornecedor.cadastraCombo("Adidas", "The best", "Tapioca e misto", 0.50, "Tapioca e misto - Bom demais papai");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.", iae.getMessage());
		}
	}
	
	@Test
	void testeCadastraComboComboExistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
	}
	
	@Test
	void testeCadastraComboProdutoNaoExiste() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.cadastraCombo("Adidas", "Misto e suco", "Suco laranja", 0.50, "Misto - Queijo e presunto, Suco laranja - Com acucar");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: produto nao existe.", iae.getMessage());
		}
	}
	
	
	@Test
	void testeCadastraComboFatorInvalido() {
		try {
			contFornecedor.cadastraCombo("Adidas", "Misto e suco", "Suco laranja", -0.50, "Misto - Queijo e presunto, Suco laranja - Com acucar");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: fator invalido.", iae.getMessage());
		}
	}
	
	
	@Test
	void testeCadastraComboComComboNaListaDeProdutos() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		try {
			contFornecedor.cadastraCombo("Adidas", "The best", "Tapioca e misto", 0.50, "Tapioca e misto - Bom demais papai");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.", iae.getMessage());
		}
	}
	
	
	@Test
	void testeCadastraComboParametoNuloOuVazio() {
		try {
			contFornecedor.cadastraCombo("             ", "The best", "Tapioca e misto", 0.50, "Tapioca e misto - Bom demais papai");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.cadastraCombo("Adidas", "The best", "             ", 0.50, "Tapioca e misto - Bom demais papai");
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contFornecedor.cadastraCombo("Adidas", "The best", "Tapioca e misto", 0.50, null);
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro de combo: combo deve ter produtos.", npe.getMessage());
		}
		try {
			contFornecedor.cadastraCombo("Adidas", "The best", null, 0.50, "Tapioca e misto - Bom demais papai");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.", npe.getMessage());
		}
		try {
			contFornecedor.cadastraCombo(null, "The best", "Tapioca e misto", 0.50, "Tapioca e misto - Bom demais papai");
		}catch(NullPointerException npe) {
			assertEquals("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void testeEditaCombo() {
		
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		contFornecedor.cadastrarProduto("Adidas", "Misto", "Queijo e presunto", 10.5);
		contFornecedor.cadastraCombo("Adidas", "Tapioca e misto", "Bom demais papai", 0.25, "Misto - Queijo e presunto, Tapioca - Tapioca com queijo");
		contFornecedor.editaCombo("Tapioca e misto", "Bom demais papai", "Adidas", 0.50);
		assertTrue(((ProdutoCombo) contFornecedor.getFornecedores().get("Adidas").getProdutos().get(new IdProduto("Tapioca e misto", "Bom demais papai"))).getFator() == 0.5);
	}
	
	@Test
	void testeEditaComboProdutoNaoExiste() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.editaCombo("Misto e cafe", "Cafe com leite", "Adidas", 0.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de combo: produto nao existe.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaComboFatorInvalido() {
		try {
			contFornecedor.editaCombo("Misto e cafe", "Cafe com leite", "Adidas", -0.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de combo: fator invalido.", iae.getMessage());
		}
	}
	
	@Test
	void testeEditaComboParametroVazioOuNulo() {
		try {
			contFornecedor.editaCombo("         ", "Cafe com leite", "Adidas", 0.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de combo: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			contFornecedor.editaCombo("Misto e cafe", "        ", "Adidas", 0.50);
		}catch(IllegalArgumentException iae) {
			assertEquals("Erro na edicao de combo: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			contFornecedor.editaCombo("Misto e cafe", null, "Adidas", 0.50);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao de combo: descricao nao pode ser vazia ou nula.", npe.getMessage());
		}
		try {
			contFornecedor.editaCombo("Misto e cafe", "Cafe com leite", null, 0.50);
		}catch(NullPointerException npe) {
			assertEquals("Erro na edicao de combo: fornecedor nao pode ser vazio ou nulo.", npe.getMessage());
		}
	}
	
	@Test
	void procuraFornecedor() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		assertTrue(contFornecedor.procuraFornecedor("Adidas", "Deu Certo"));
	}
	
	@Test
	void procuraFornecedorFornecedorInexistente() {
		try {
			contFornecedor.procuraFornecedor("Adidas", "Fornecedor nao cadastrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Fornecedor nao cadastrado", iae.getMessage());
		}
	}
	
	@Test
	void retornaProduto() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		contFornecedor.cadastrarProduto("Adidas", "Tapioca", "Tapioca com queijo", 8.5);
		assertEquals(new Produto("Tapioca", "Tapioca com queijo", 8.5).toString(), 
				contFornecedor.retornaProduto("Adidas", "Tapioca", "Tapioca com queijo", "Fornecedor nao encontrado", "Produto nao encontrado").toString());
	}
	
	@Test
	void retornaProdutoFornecedorInexistente() {
		try {
			contFornecedor.retornaProduto("Adidas", "Tapioca", "Tapioca com queijo", "Fornecedor nao encontrado", "Produto nao encontrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Fornecedor nao encontrado", iae.getMessage());
		}
	}
	
	@Test
	void retornaProdutoProdutoInexistente() {
		contFornecedor.cadastrarFornecedores("Adidas", "adidas@gmail.com", "93273232");
		try {
			contFornecedor.retornaProduto("Adidas", "Tapioca", "Tapioca com queijo", "Fornecedor nao encontrado", "Produto nao encontrado");
		}catch(IllegalArgumentException iae) {
			assertEquals("Produto nao encontrado", iae.getMessage());
		}
	}
}
