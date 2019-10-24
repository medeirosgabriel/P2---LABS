/**
 * Representacao do controller que ira operar sobre os fornecedores(principalmente)
 * e seus produtos.
 * Todo controller de fornecedor possui um HashMap de fornecedores.
 * 
 * @author Gabriel Medeiros
 */
package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import entidades.Cliente;
import entidades.Fornecedor;
import entidades.Produto;

public class ControllerFornecedor {
	
	/**
	 * HashMap que armazena os fornecedores.
	 */
	private HashMap<String, Fornecedor> fornecedores;
	
	/**
	 * Construtor da classe ControllerFornecedor.
	 */
	public ControllerFornecedor() {
		this.fornecedores = new HashMap<>();
	}
	
	/**
	 * Cadastra um fornecedor no sistema.
	 * @param nome - Nome do cliente.
	 * @param email - Email do cliente.
	 * @param telefone - Telefone do cliente.
	 * @return Se o cadastro for feito com sucesso, 
	 * o metodo ira retornar true.
	 */
	public String cadastrarFornecedores(String nome, String email, String telefone) {
		Validacao.validarString(nome, "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(email, "Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		Validacao.validarString(telefone, "Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		if (fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		}
		fornecedores.put(nome, new Fornecedor(nome, email, telefone));
		return nome;
	}
	
	/**
	 * Exibe a representacao de um fornecedor.
	 * @param nome - Nome do fornecedor.
	 * @return A representacao do fornecedor.
	 */
	public String consultarFornecedor(String nome) {
		Validacao.validarString(nome, "Erro na exibicao do fornecedor: fornecedor nao pode ser vazio ou nulo.");
		if (!fornecedores.containsKey(nome)) { 
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}else {
			return this.fornecedores.get(nome).toString();
		}
	}
	
	/**
	 * Exibe os fornecedores cadastrados em ordem alfabetica.
	 * @return Uma string com as representacoes de
	 * todos os fornecedores cadastrados;
	 */
	public String imprimirFornecedores() {
		String listaOrdenada = "";
		List<Fornecedor> listaFornecedores = new ArrayList<>(this.fornecedores.values());
		Collections.sort(listaFornecedores);
		for (Fornecedor f : listaFornecedores) {
			listaOrdenada += f.toString() + " | ";
		}
		return this.auxiliaImpressao(listaOrdenada);
	}
	
	/**
	 * Auxilia na impressao dos dados dos fornecedores
	 * cadastrados.
	 * Formata a string que possui os dados dos
	 * fornecedores cadastrados.
	 * @param fornecedores - String com os dados dos fornecedores.
	 * @return A string formatada.
	 */
	private String auxiliaImpressao(String fornecedores) {
		if (fornecedores.equals("")) {
			return "";
		}else {
			return fornecedores.substring(0, fornecedores.length() - 3);
		}
	}
	
	/**
	 * Edita os atributos de um fornecedor, exceto o nome.
	 * @param nome - Nome do fornecedor
	 * @param opcao - Atributo que deve ser alterado.
	 * @param novoValor - Novo valor que vai ser atribuido ao
	 * atributo escolhido.
	 * @return Se a edicao for bem sucedida, o metodo
	 * ira retornar true.
	 */
	public boolean editarFornecedor(String nome, String opcao, String novoValor) {
		Validacao.validarString(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(opcao, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		Validacao.validarString(novoValor, "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		if (!fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: fornecedor nao existe.");
		}
		if (opcao.equals("nome")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		}else if(opcao.equals("email")) {
			return editarEmailFornecedor(nome, novoValor);
		}else if(opcao.equals("telefone")) {
			return editarTelefoneFornecedor(nome, novoValor);
		}else {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		}
	}
	
	/**
	 * Edita o email de um fornecedor.
	 * @param nome - Nome dofornecedor
	 * @param email - Novo email que vai ser atribuido.
	 * @return Se a edicao for bem sucedida, o metodo ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean editarEmailFornecedor(String nome, String email) {
		Validacao.validarString(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(email, "Erro na edicao do fornecedor: email nao pode ser vazio ou nulo.");
		if (fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setEmail(email);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Edita o telefone de um fornecedor.
	 * @param nome - Nome dofornecedor
	 * @param telefone - Novo telefone que vai ser atribuido.
	 * @return Se a edicao for bem sucedida, o metodo ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean editarTelefoneFornecedor(String nome, String telefone) {
		Validacao.validarString(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(telefone, "Erro na edicao do fornecedor: telefone nao pode ser vazio ou nulo.");
		if (fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setTelefone(telefone);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Remove um fornecedor do sistema
	 * @param nome - Nome do fornecedor que vai ser retirado do sistema.
	 * @return Se a remocao for bem sucedida, o metodo ira retornar true, caso contrario,
	 * ira retornar false. 
	 */
	public boolean removerFornecedor(String nome) {
		Validacao.validarString(nome, "Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		if (!fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}else {
			this.fornecedores.remove(nome);
			return true;
		}
	}
	
	/**
	 * Cadastra um produto no sistema.
	 * @param nomeFornecedor - Nome do fornecedor.
	 * @param nomeProduto - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param preco - Preco do produto.
	 * @return Se o cadastro for bem sucedido, o metodo ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean cadastrarProduto(String nomeFornecedor, String nomeProduto, String descricao, double preco) {
		Validacao.validarString(nomeFornecedor,"Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nomeProduto,"Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao,"Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		Validacao.validarPreco(preco,"Erro no cadastro de produto: preco invalido.");
		if (fornecedores.containsKey(nomeFornecedor)) {
			return this.fornecedores.get(nomeFornecedor).cadastrarProduto(nomeProduto, descricao, preco);
		}else {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao existe.");
		}
	}

	/**
	 * Exibe as informacoes de um produto.
	 * @param nomeFornecedor - 	Nome do fornecedor.
	 * @param nomeProduto - Nome do Produto.
	 * @param descricao - Descricao do produto.
	 * @return A representacao do produto.
	 */
	public String consultarProduto(String nomeFornecedor, String nomeProduto, String descricao) {
		Validacao.validarString(nomeFornecedor,"Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nomeProduto,"Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao,"Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		if (fornecedores.containsKey(nomeFornecedor)) {
			return this.fornecedores.get(nomeFornecedor).consultarProduto(nomeProduto, descricao);
		}else {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		}
	}
	
	/**
	 * Imprime todos os produtos de um certo fornecedor.
	 * @param nomeFornecedor - nome do fornecedor.
	 * @return Uma string com a representacao de todos
	 * os produtos de um fornecedor.
	 */
	public String imprimirProdutos(String nomeFornecedor) {
		Validacao.validarString(nomeFornecedor,"Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		if (fornecedores.containsKey(nomeFornecedor)) {
			return this.fornecedores.get(nomeFornecedor).imprimirProdutos();
		}else {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		}
	}
	
	/**
	 * Imprime todos os produtos independente do fornecedor.
	 * @return Uma String com os dados de todos os produtos em ordem
	 * alfabetica.
	 */
	public String imprimirTodosProdutos() {
		String listaOrdenada = "";
		List<Fornecedor> listaFornecedores = new ArrayList<>(this.fornecedores.values());
		Collections.sort(listaFornecedores);
		for (Fornecedor f : listaFornecedores) {
			listaOrdenada += f.imprimirProdutos();
			if (f.imprimirProdutos() != "") {
				listaOrdenada += " | ";
			}
		}
		return this.auxiliaImpressao(listaOrdenada);
	}
	
	
	/**
	 * Edita o preco de um produto.
	 * @param nomeProduto - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param fornecedor - Nome do fornecedor.
	 * @param preco - Preco do produto.
	 * @return Se a edicao for bem sucedida, o metodo ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean editarPrecoProduto(String nomeProduto, String descricao, String fornecedor, double preco) {
		Validacao.validarString(fornecedor,"Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nomeProduto,"Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao,"Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		Validacao.validarPreco(preco,"Erro na edicao de produto: preco invalido.");
		if (fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).editarPrecoProduto(nomeProduto, descricao, preco);
		}else {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao existe.");
		}
	}
	
	/**
	 * Remove um determinado produto do sistema.
	 * @param fornecedor - nome do fornecedor.
	 * @param nomeProduto - nome do produto.
	 * @param descricaoProduto - descricao do produto.
	 * @return Se a remocao for bem sucedida, o metodo ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean removerProduto(String fornecedor, String nomeProduto, String descricaoProduto) {
		Validacao.validarString(fornecedor,"Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nomeProduto,"Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricaoProduto,"Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		if (fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).removerProduto(nomeProduto, descricaoProduto);
		}else {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao existe.");
		}
	} 
	
	/**
	 * Ira retornar o o HashMap que armazena os fornecedores do
	 * sistema.
	 * @return O HashMap que armazena os fornecedores.
	 */
	public HashMap<String, Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	/**
	 * Cadastra um combo no sistema.
	 * @param fornecedor - Nome do fornecedor.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do produto.
	 * @param fator - Fator de desconto do combo.
	 * @param produtos - Produtos que formam o combo.
	 * @return Se a operacao de cadastro for bem sucedida, o metodo ira retornar true.
	 */
	public boolean cadastraCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		Validacao.validarString(fornecedor, "Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nome, "Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao, "Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");
		Validacao.validarString(produtos, "Erro no cadastro de combo: combo deve ter produtos.");
		Validacao.validarFator(fator, "Erro no cadastro de combo: fator invalido.");
		if (this.fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).cadastraCombo(nome, descricao, fator, produtos);
		}else {
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao existe.");
		}
	}

	/**
	 * Edita o fator de desconto de um combo.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do combo.
	 * @param fornecedor - Nome do fornecedor.
	 * @param fator - Fator de desconto do fornecedor.
	 * @return Se a operacao de edicao for bem sucedida, o metodo ira retornar true.
	 */
	public boolean editaCombo(String nome, String descricao, String fornecedor, double fator) {
		Validacao.validarString(fornecedor, "Erro na edicao de combo: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarString(nome, "Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao, "Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		Validacao.validarFator(fator, "Erro na edicao de combo: fator invalido.");
		if (this.fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).editaCombo(nome, descricao, fator);
		}else {
			throw new IllegalArgumentException("Erro na edicao de combo: fornecedor nao existe.");
		}
	}
	
	/**
	 * Procura um fornecedor.
	 * @param fornecedor - Nome do fornecedor.
	 * @param mensagem - Mensagem utilizada caso ocorra uma excecao,
	 * devido a inexistencia do fornecedor.
	 * @return Se o fornecedor existir, o metodo ira retornar true.
	 */
	public boolean procuraFornecedor(String fornecedor, String mensagem) {
		if (this.fornecedores.containsKey(fornecedor)) {
			return true;
		}else {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Procura e retorna um objeto do tipo produto.
	 * @param fornecedor - Nome do fornecedor.
	 * @param nomeProduto - Nome do produto.
	 * @param descProduto - Descricao do produto.
	 * @param mensagem1 - Mensagem utilizada caso ocorra uma excecao,
	 * devido a inexistencia do fornecedor.
	 * @param mensagem2 - Mensagem utilizada caso ocorra uma excecao,
	 * devido a inexistencia do produto.
	 * @return Um objeto do tipo Produto.
	 */
	public Produto retornaProduto(String fornecedor, String nomeProduto, String descProduto, String mensagem1, String mensagem2) {
		this.procuraFornecedor(fornecedor, mensagem1);
		return this.fornecedores.get(fornecedor).retornaProduto(nomeProduto, descProduto, mensagem2);
	}
}