/**
 * Representacao de um Fornecedor.
 * Um fornecedor e identificado pelo nome.
 * Todo fornecedor possui nome, email e telefone.
 * 
 * @author Gabriel Medeiros
 */
package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import controllers.Validacao;

public class Fornecedor implements Comparable<Fornecedor>{

	/**
	 * HashMap que armazena os produtos do fornecedor.
	 */
	private HashMap<IdProduto, Produto> produtos;
	
	/**
	 * Nome do fornecedor
	 */
	private String nome;
	
	/**
	 * Email do fornecedor
	 */
	private String email;
	
	/**
	 * Telefone do fornecedor
	 */
	private String telefone;
	
	/**
	 * Constroi um fornecedor com base nonome, no email e no telefone.
	 * @param nome - Nome do Fornecedor.
	 * @param email - Email do Fornecedor.
	 * @param telefone - Telefone do Fornecedor.
	 */
	public Fornecedor(String nome, String email, String telefone) {
		Validacao.validarString(nome, "Erro na criacao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(email, "Erro na criacao do fornecedor: email nao pode ser vazio ou nulo.");
		Validacao.validarString(telefone,"Erro na criacao do fornecedor: telefone nao pode ser vazio ou nulo.");
		this.produtos = new HashMap<>();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	/**
	 * Gera uma representacao em forma de string
	 * para um fornecedor.
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;
	}

	/**
	 * Retorna o nome do produto.
	 * @return O nome do produto.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o email do produto.
	 * @return O email do produto.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Retorna o telefone do produto.
	 * @return O telefone do produto.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o email do fornecedor.
	 * @param email - Novo email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Altera o telefone do fornecedor.
	 * @param email - Novo telefone.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/**
	 * Gera o HashCode do Objeto.
	 * O objeto pode ser representado por um numero inteiro.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	/**
	 * Verifica se dois objetos sao iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	/**
	 * Retorna um HashMap com os produtos do fornecedor.
	 * @return Um HashMap com os produtos do fornecedor.
	 */
	public HashMap<IdProduto, Produto> getProdutos() {
		return produtos;
	}

	/**
	 * Utilizado para comparar dois objetos do tipo Fornecedor.
	 * O atributo levado em consideracao e o nome.
	 */
	@Override
	public int compareTo(Fornecedor f) {
		return this.nome.compareTo(f.getNome());
	}
	
	/**
	 * Cadastra um produto.
	 * @param produtos - HashMap onde fica armazenado os produtos.
	 * @param nomeProduto - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param preco - Preco do produto.
	 * @return Se o cadastro for bem sucedido, o metodo retorna true.
	 */
	public boolean cadastrarProduto(String nomeProduto, String descricao, double preco) {
		IdProduto idProduto = new IdProduto(nomeProduto, descricao);
		if (this.produtos.containsKey(idProduto)) {
			throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
		}
		this.produtos.put(idProduto, new Produto(nomeProduto, descricao, preco));
		return true;
	}

	/**
	 * Exibe as informacaoes de um determinado produto.
	 * @param produtos - HashMap onde fica armazenado os produtos.
	 * @param nomeProduto - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @return A string que representa o produto procurado.
	 */
	public String consultarProduto(String nomeProduto, String descricao) {
		IdProduto id = new IdProduto(nomeProduto, descricao);
		if (!this.produtos.containsKey(id)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");
		}
		return produtos.get(id).toString();
	}
	
	/**
	 * Forma uma String com a representacao de todos os produtos.
	 * @param produtos - HashMap onde fica armazenado os produtos.
	 * @param fornecedor - Nome do fornecedor.
	 * @return A string com a representacao de todos os produtos. 
	 */
	public String imprimirProdutos() {
		ArrayList<Produto> listaProdutos = new ArrayList<>(produtos.values());
		Collections.sort(listaProdutos);
		String listaOrdenada = "";
		for (Produto p : listaProdutos) {
			listaOrdenada += this.nome + " - " + p.toString() + " | ";
		}
		return auxiliaImpressaoProdutos(listaOrdenada, this.nome);
	}
	
	/**
	 * Formata a String gerada pelo metodo imprimirProduto.
	 * @param produtos - String com a representacao de todos os produtos.
	 * @param fornecedor - Nome do fornecedor.
	 * @return A string formatada com a representacao de todos os produtos.
	 */
	public String auxiliaImpressaoProdutos(String produtos, String fornecedor) {
		if (produtos.equals("")) {
			return fornecedor + " -";
		}else {
			return produtos.substring(0, produtos.length() - 3);
		}
	}

	/**
	 * Edita o preco de um produto.
	 * @param produtos - HashMap onde fica armazenado os produtos.
	 * @param nomeProduto - Nome do produto.
	 * @param descricaoProduto - Descricao do produto.
	 * @param preco - Preco do produto.
	 * @return Se a edicao for bem sucedida, o metodo retorna true.
	 */
	public boolean editarPrecoProduto(String nomeProduto, String descricaoProduto, double preco) {
		IdProduto id = new IdProduto(nomeProduto, descricaoProduto);
		if (!this.produtos.containsKey(id)) {
			throw new IllegalArgumentException("Erro na edicao de produto: produto nao existe.");
		}
		this.produtos.get(id).setPreco(preco);
		return true;
	}
	
	/**
	 * Remove um produto do fornecedor.
	 * @param produtos - HashMap onde fica armazenado os produtos.
	 * @param nomeProduto - Nome do produto.
	 * @param descricaoProduto - Descricao do produto.
	 */
	public boolean removerProduto(String nomeProduto, String descricaoProduto) {
		IdProduto id = new IdProduto(nomeProduto, descricaoProduto);
		if (this.produtos.containsKey(id)) {
			this.produtos.remove(id);
		}else {
			throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");
		}
		return true;
	}
	
	/**
	 * Cadastra um combo.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do combo.
	 * @param fator - Fator de desconto do combo.
	 * @param produtos - Produtos que compoe o combo.
	 * @return Se a operacao de cadastro for bem sucedida, o metodo ira retornar true.
	 */
	public boolean cadastraCombo(String nome, String descricao, double fator, String produtos) {
		IdProduto idProd = new IdProduto(nome, descricao);
		if (!this.produtos.containsKey(idProd)) {
			double total = this.auxiliaCadastraCombo(descricao, produtos);
			double preco = total * (1 - fator);
			this.produtos.put(idProd, new ProdutoCombo(nome, descricao, total, preco, fator));
			return true;
		}else{
			throw new IllegalArgumentException("Erro no cadastro de combo: combo ja existe.");
		}
	}
	
	/**
	 * Auxilia no cadasttro do combo.
	 * @param nome - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param produtos - Produtos que compoem um combo.
	 * @return O preco total do combo.
	 */
	public double auxiliaCadastraCombo(String descricao, String produtos) {
		String[] arrayProdutos = produtos.split(", ");
		double total = 0;
		for (String info : arrayProdutos) {
			String[] dadosProdutos = info.split(" - ");
			IdProduto id = new IdProduto(dadosProdutos[0], dadosProdutos[1]);
			if (this.produtos.containsKey(id)) {
				if (this.produtos.get(id) instanceof ProdutoCombo) {
					throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
				}else {
					total += this.produtos.get(id).getPreco();
				}
			}else{
				throw new IllegalArgumentException("Erro no cadastro de combo: produto nao existe.");
			}
		}
		return total;
	}
	
	/**
	 * Edita o fator de deconto de um combo.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do combo.
	 * @param fator - Novo fator de desconto do combo.
	 * @return Se a operacao de edicao for bem sucedida, o metodo ira retornar true.
	 */
	public boolean editaCombo(String nome, String descricao, double fator) {
		IdProduto idProduto = new IdProduto(nome, descricao);
		if (this.produtos.containsKey(idProduto)) {
			((ProdutoCombo) this.produtos.get(idProduto)).setFator(fator);
			return true;
		}else {
			throw new IllegalArgumentException("Erro na edicao de combo: produto nao existe.");
		}
	}
	
	/**
	 * Procura um produto.
	 * @param produto - Nome do produto.
	 * @param descProduto - Descricao de um produto.
	 * @param mensagem - Mensagem utilizada caso ocorra uma excecao.
	 * @return Se o produto existir, o metodo ira retornar true.
	 */
	public boolean procuraProduto(String produto, String descProduto, String mensagem) {
		IdProduto id = new IdProduto(produto, descProduto);
		if (this.produtos.containsKey(id)) {
			return true;
		}else {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * 
	 * @param produto
	 * @param descProduto
	 * @param mensagem - Mensagem utilizada caso ocorra uma excecao.
	 * @return Se  produto existir, o metodo ira retornar um objeto
	 * do tipo produto.
	 */
	public Produto retornaProduto(String produto, String descProduto, String mensagem){
		IdProduto id = new IdProduto(produto, descProduto);
		this.procuraProduto(produto, descProduto, mensagem);
		return this.produtos.get(id);
	}
}