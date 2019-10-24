/**
 * Reune os metodos dos controllers,
 * a fim de deixar o main mais organizado.
 */
package sistema;

import controllers.ControllerCentral;
import controllers.ControllerCliente;
import controllers.ControllerFornecedor;
import easyaccept.EasyAccept;

public class Facade {
	
	/**
	 * Objeto do tipo ControllerFornecedor.
	 */
	private ControllerFornecedor controllerFornecedor;
	
	/**
	 * Objeto do tipo ControllerCliente.
	 */
	private ControllerCliente controllerCliente;
	
	private ControllerCentral controllerCentral;
	
	
	public static void main(String[] args) {
		
		args = new String[] {"sistema.Facade",
				"acceptance_test/use_case_1.txt",
				"acceptance_test/use_case_2.txt",
				"acceptance_test/use_case_3.txt",
				"acceptance_test/use_case_4.txt",
				"acceptance_test/use_case_5.txt",
				"acceptance_test/use_case_6.txt",
				"acceptance_test/use_case_7.txt",
				"acceptance_test/use_case_8.txt"};
		EasyAccept.main(args);
	}
	
	/**
	 * Constroi um novo Facade.
	 * Inicializa os controllers de Fornecedor e Cliente.
	 */
	public Facade() {
		this.controllerFornecedor = new ControllerFornecedor();
		this.controllerCliente = new ControllerCliente();
		this.controllerCentral = new ControllerCentral(controllerCliente, controllerFornecedor);
	}
	
	/**
	 * Adiciona um cliente no sistema.
	 * @param cpf - Cpf do cliente.
	 * @param nome - Nome do cliente.
	 * @param email - Email do cliente.
	 * @param localizacao - Localizacao do cliente.
	 * @return o cpf do aluno cadastrado.
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) {
		return controllerCliente.cadastrarCliente(cpf, nome, email, localizacao);
	}
	
	/**
	 * Exibe os dados de um determinado cliente
	 * @param cpf - Cpf do cliente
	 * @return A representacao em forma de string.
	 */
	public String exibeCliente(String cpf) {
		return controllerCliente.consultarCliente(cpf);
	}
	
	/**
	 * Edita os dados de um cliente
	 * @param cpf - Cpf do cliente
	 * @param atributo - atributo de cliente
	 * @param novoValor - novo avalor a ser colocado 
	 * @return a representacao em formato de string.
	 */
	public boolean editaCliente(String cpf, String atributo, String novoValor) {
		return controllerCliente.editarCliente(cpf, atributo, novoValor);
	}
	
	/**
	 * Imprime os Clientes
	 */
	public String exibeClientes() {
		return controllerCliente.imprimirClientes();
	}
	
	/**
	 * Remove um Cliente.
	 * @param cpf - vpf do cliente.
	 */
	public void removeCliente(String cpf) {
		controllerCliente.removerCliente(cpf);
	}
	
	/**
	 * Adiciona Fornecedor no sistema.
	 * @param nome - Nome do fornecedor.
	 * @param email - Nome dpo email.
	 * @param telefone - Telefone do cliente.
	 * @return
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		return controllerFornecedor.cadastrarFornecedores(nome, email, telefone);
	}
	
	/**
	 * Exibe os dados de um certo fornecedor.
	 * @param nome - Nome do cliente.
	 * @return A string que contem todas as representacoes
	 * dos fornecedores.
	 */
	public String exibeFornecedor(String nome) {
		return controllerFornecedor.consultarFornecedor(nome);
	}
	
	/**
	 * Edita os dados de um fornecedor.
	 * @param nome - Nome do fornecedor
	 * @param atributo - Atributo do fornecedor
	 * @param novoValor - Novo valor.
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		controllerFornecedor.editarFornecedor(nome, atributo, novoValor);
	}
	
	/**
	 * Imprime uma String com as representacoes dos
	 * Fornecedores.
	 */
	public String exibeFornecedores() {
		return controllerFornecedor.imprimirFornecedores();
	}
	
	/**
	 * Remove um Fornecedor do Sistema
	 * @param nome - Nome do fornecedor
	 */
	public void removeFornecedor(String nome) {
		controllerFornecedor.removerFornecedor(nome);
	}
	/**
	 * Adiciona um novo Produto na colecao do fornecedor;
	 * @param fornecedor - Nome do fornecedor;
	 * @param nome - Nome do aluno.
	 * @param descricao - Descricao do aluno;
	 * @param preco - Preco do produto;
	 */
	public void adicionaProduto( String fornecedor, String nome, String descricao, double preco) {
		controllerFornecedor.cadastrarProduto(fornecedor, nome, descricao, preco);
	}
	

	/**
	 * Exibe os dados de um determinado produto.
	 * @param nomeProduto - Nome do Produto.
	 * @param descricao - Descricao do produto.
	 * @param fornecedor - Fornecedor do produto.
	 * @return
	 */
	public String exibeProduto(String nomeProduto, String descricao, String fornecedor) {
		return controllerFornecedor.consultarProduto(fornecedor, nomeProduto, descricao);
	}
	
	/**
	 * Edita os atributos de um fornecedor.
	 * @param nome - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param fornecedor - Nome do fornecedor.
	 * @param preco - Preco do produto.
	 * @return 
	 */
	public boolean editaProduto(String nome, String descricao, String fornecedor, double preco) {
		return controllerFornecedor.editarPrecoProduto(nome, descricao, fornecedor, preco);
	}
	
	/**
	 * Imprime os produtos cadastrados.
	 * @param nomeFornecedor - Nome do fornecedor.
	 */
	public String exibeProdutosFornecedor(String nomeFornecedor) {
		return controllerFornecedor.imprimirProdutos(nomeFornecedor);
	}
	
	/**
	 * Exibe todos os produtos independente do fornecedor.
	 * @return Uma string que representa todos os produtos.
	 */
	public String exibeProdutos() {
		return controllerFornecedor.imprimirTodosProdutos();
	}
	
	/**
	 * Remove um produto.
	 * @param nomeProduto - Nome do produto.
	 * @param descricaoProduto - descricao do Produto;
	 * @param nomeFornecedor - Nome do fornecedor
	 */
	public void removeProduto(String nomeProduto, String descricaoProduto, String nomeFornecedor) {
		controllerFornecedor.removerProduto(nomeFornecedor, nomeProduto, descricaoProduto);
	}
	
	/**
	 * Adiciona um combo na colecao de um determinado fornecedor.
	 * @param fornecedor - Nome do fornecedor.
	 * @param nome - Nome do produto
	 * @param descricao - Descricao do produto.
	 * @param fator - Fator de desconto do produto.
	 * @param produtos - String com a representacao dos produtos
	 * que compoem um combo.
	 */
	public void adicionaCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		controllerFornecedor.cadastraCombo(fornecedor, nome, descricao, fator, produtos);
	}
	
	/**
	 * Edita um combo.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do combo.
	 * @param fornecedor - Nome do fornecedor.
	 * @param fator - Novo fator de desconto do combo.
	 */
	public void editaCombo(String nome, String descricao, String fornecedor, double fator) {
		controllerFornecedor.editaCombo(nome, descricao, fornecedor, fator);
	}
	
	/**
	 * Adiciona uma compra na conta de um determinado cliente.
	 * @param cpf - cpf do cliente que fez a compra.
	 * @param fornecedor - Nome do fornecedor.
	 * @param data - Data da compra.
	 * @param nomeProduto - Nome do produto comprado.
	 * @param descProduto - Descricao do produto comprado.
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nomeProduto, String descProduto) {
		controllerCentral.adicionaCompra(cpf, fornecedor, data, nomeProduto, descProduto);
	}
	
	/**
	 * Retorna o debito de um cliente com um determinado fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return O debito do cliente com um determiando fornecedor.
	 */
	public String getDebito(String cpf, String fornecedor) {
		String valor =  String.format("%.2f", controllerCentral.getDebito(cpf, fornecedor));
		return valor.replace(",", ".");
	}
	
	/**
	 * Exibe as contas de um cliente com um determinado fornecedor.
	 * @param cpf - Cpf do cleinte.
	 * @param fornecedor - Nome do fornecedor.
	 * @return Uma string que representa todas asa contas de um cliente com um fornecedor.
	 */
	public String exibeContas(String cpf, String fornecedor) {
		return controllerCentral.exibeContas(cpf, fornecedor);
	}
	
	/**
	 * Exibe todas as contas de um cliente independente do fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @return Uma string que representa todas as contas de um cliente.
	 */
	public String exibeContasClientes(String cpf) {
		return controllerCentral.exibeContasClientes(cpf);
	}
	
	public boolean realizaPagamento(String cpf, String fornecedor) {
		return controllerCentral.pagarConta(cpf, fornecedor);
	}
	
	public boolean ordenaPor(String criterio) {
		return this.controllerCentral.ordenaPor(criterio);
	}
	
	public String listarCompras() {
		return this.controllerCentral.listarCompras();
	}
}