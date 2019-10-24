/**
 * Representacao de um Combo.
 * Um Combo e identificado pelo nome e pela descricao.
 * Todo combo possui nome, descricao, preco, valorBase e um fator de desconto.
 * 
 * @author Gabriel Medeiros
 */
package entidades;

import controllers.Validacao;

public class ProdutoCombo extends Produto {
	
	/**
	 * Fator de desconto.
	 */
	private double fator;
	
	/**
	 * Valor base do produto.
	 */
	private double valorBase;

	/**
	 * Controi um combo.
	 * @param nome - Nome do combo.
	 * @param descricao - Descricao do combo.
	 * @param valorBase - Valor Base do combo.
	 * @param preco - Preco do combo.
	 * @param fator - Fator de desconto.
	 */
	public ProdutoCombo(String nome, String descricao, double valorBase, double preco, double fator) {
		super(nome, descricao, preco);
		Validacao.validarPreco(preco, "Erro na criacao de produto composto: fator invalido");
		this.fator = fator;
		this.valorBase = valorBase;
	}

	/**
	 * Retorna o fator de desconto do combo.
	 * @return O fator de desconto do combo.
	 */
	public double getFator() {
		return fator;
	}

	/**
	 * Altera o fator de desconto do combo.
	 * @param fator - Novo fator de desconto do combo.
	 */
	public void setFator(double fator) {
		this.fator = fator;
		this.preco = this.valorBase * (1 - fator);
	}
}
