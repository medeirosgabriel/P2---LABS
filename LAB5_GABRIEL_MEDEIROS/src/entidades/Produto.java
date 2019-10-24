/**
 * Representacao de um Produto.
 * Um produto e identificado pelo nome e pela descricao.
 * Todo produto possui nome, descricao e preco.
 * 
 * @author Gabriel Medeiros.
 */
package entidades;

import controllers.Validacao;

public class Produto implements Comparable<Produto> {
	
	/**
	 * Nome do produto
	 */
	protected String nome;
	
	/**
	 * Descricao do produto
	 */
	protected String descricao;
	
	/**
	 * Preco do produto
	 */
	protected double preco;
	
	
	/**
	 * Constroi um Produto com base no nome, na descricao e no preco.
	 * @param nome - Nome do produto.
	 * @param descricao - Descricao do produto.
	 * @param preco - Preco do produto.
	 */
	public Produto(String nome, String descricao, double preco) {
		Validacao.validarString(nome, "Erro na criacao do produto: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao, "Erro na criacao do produto: descricao nao pode ser vazia ou nula.");
		Validacao.validarPreco(preco, "Erro na criacao do produto: preco invalido.");
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	/**
	 * Retorna o preco do cliente.
	 * @return O preco do cliente.
	 */
	public double getPreco() {
		return this.preco;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera o preco de um Produto.
	 * @param preco - Preco de um produto.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	/**
	 * Gera a representacao em forma de String
	 * para um Produto.
	 */
	@Override
	public String toString() {
		return String.format(this.nome + " - " + this.descricao + " - " + "R$" + "%.2f", this.preco);
	}

	/**
	 * Gera o HashCode do Objeto.
	 * O objeto pode ser representado por um numero inteiro.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Compara dois objetos do tipo Produto.
	 * Os atributos levados em consideracao
	 * sao o nome do produto e a descricao.
	 */
	@Override
	public int compareTo(Produto p) {
		if (this.nome.compareTo(p.getNome()) != 0) {
			return this.nome.compareTo(p.getNome());
		}else {
			return this.descricao.compareTo(p.getNome());
		}
	}
}
