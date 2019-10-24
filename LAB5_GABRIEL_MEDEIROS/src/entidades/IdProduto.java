/**
 * Representacao do Id do Porduto.
 * Dois produtos sao iguais se possuirem o mesmo Id.
 * O Id e gerado a partir do nome e da descricao do produto.
 * 
 * @author Gabriel Medeiros.
 */
package entidades;

import controllers.Validacao;

public class IdProduto {
	
	/**
	 * Nome do produto.
	 */
	private String nome;
	
	/**
	 * Descricao do produto.
	 */
	private String descricao;
	
	/**
	 * Constroi o Id de um produto a parir
	 * do nome e da descricao.
	 * @param nome - Nome do produto.
	 * @param descricao - Descricao do produto.
	 */
	public IdProduto(String nome, String descricao) {
		Validacao.validarString(nome, "Erro na criacao de Id: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(descricao, "Erro na criacao de Id: descricao nao pode ser vazia ou nula.");
		this.nome = nome;
	}

	/**
	 * Retorna o nome do produto que se encontra no Id.
	 * @return O nome do produto.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna a descricao do produto que se encontra no Id.
	 * @return A descricao do produto.
	 */
	public String getDescricao() {
		return descricao;
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
		IdProduto other = (IdProduto) obj;
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
}
