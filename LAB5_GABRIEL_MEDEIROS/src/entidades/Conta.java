/**
 * Representacao de uma conta.
 * Toda conta possui um fornecedor, um preco e um ArrayList
 * das compras feitas.
 */
package entidades;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Conta {
	
	/**
	 * Nome do fornecedor.
	 */
	private String fornecedor;
	
	/**
	 * Preco da conta.
	 */
	private double preco;
	
	/**
	 * ArrayList que armazena as compras.
	 */
	private List<Compra> compras;

	/**
	 * Constroi uma conta.
	 * @param fornecedor - Nome do fornecedor.
	 */
	public Conta(String fornecedor) {
		this.fornecedor = fornecedor;
		this.compras = new ArrayList<>();
		this.preco = 0;
	}
	
	/**
	 * Retorna a lista de compras.
	 * @return A lista de compras.
	 */
	public List<Compra> getCompras() {
		return this.compras;
	}
	
	/**
	 * Retorna o nome do fornecedor.
	 * @return O nome do fornecedor.
	 */
	public String getFornecedor() {
		return fornecedor;
	}
	
	/**
	 * Adiciona uma compra.
	 * @param compra - Objeto do tipo compra.
	 */
	public void adicionaCompra(Compra compra) {
		this.compras.add(compra);
		this.preco += compra.getPreco();
	}
	
	/**
	 * Retorna o preco total da conta.
	 * @return O preco total da conta.
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Exibe as compras.
	 * @return Uma string representando todas as
	 * compras feitas.
	 */
	public String exibeContas() {
		String compras = this.fornecedor + " | ";
		for (Compra c : this.compras) {
			compras += c.toString() + " | ";
		}
		return auxiliaExibeContas(compras);
	}
	
	/**
	 * Formata a string que representa as compras feitas.
	 * @param compras - String que representa as compras feitas.
	 * @return A string formatada.
	 */
	public String auxiliaExibeContas(String compras) {
		return compras.substring(0, compras.length() - 3);
	}
	
	/**
	 * Recolhe as compras da conta.
	 * @return Uma lista com objetos do tipo Compra.
	 */
	public List<Compra> dadosCompras() {
		List<Compra> compras = new ArrayList<>();
		for (Compra c : this.compras) {
			compras.add(c);
		}
		return compras;
	}
}
