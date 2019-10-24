/**
 * Representacao de uma Compra.
 * Toda compra possui uma data, um produto e um preco.
 * 
 * @author Gabriel Medeiros
 */
package entidades;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Compra {
	
	
	private String fornecedor;
	
	private String cliente;
	
	/**
	 * Data da compra.
	 */
	private String data;
	
	/**
	 * Produto comprado.
	 */
	private Produto produto;
	
	/**
	 * Preco da compra.
	 */
	private double preco;

	/**
	 * Constroi uma compra.
	 * @param data - Data da compra.
	 * @param produto - Produto que foi comprado.
	 */
	public Compra(String cliente, String fornecedor, String data, Produto produto) {
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.data = data;
		this.produto = produto;
		this.preco = produto.getPreco();
	}

	/**
	 * Retorna a data da compra.
	 * @return A data da compra.
	 */
	public String getData() {
		return data;
	}

	/**
	 * Retorna o produto comprado.
	 * @return O produto comprado.
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * Retorna o preco da compra.
	 * @return O preco da compra.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Retorna a representacao de uma compra.
	 */
	@Override
	public String toString() {
		return produto.getNome() + " - " + this.data.replace("/", "-");
	}
	
	public String representacaoOrdenaPorCliente() {
		return this.cliente + ", " + this.fornecedor + ", " + this.produto.getDescricao() + ", " + this.data;
	}
	
	public String representacaoOrdenaPorFornecedor() {
		return this.fornecedor + ", " + this.cliente + ", " + this.produto.getDescricao() + ", " + this.data;
	}
	
	public String representacaoOrdenaPorData() {
		return this.data + ", " + this.cliente + ", " + this.fornecedor + ", " + this.produto.getDescricao();
	}
}
