/**
 * Classe utilizada para lancar excecoes referentes
 * as Strings que sao utilizadas nos controllers.
 * 
 * @author Gabriel Medeiros
 */
package controllers;

public class Validacao {
	
	/**
	 * Verifica se uma String e nula ou invalida. Caso isso ocorra, o metodo
	 * ira loancar uma excessao com uma mensagem personalizada.
	 * @param palavra - String a ser avaliada.
	 * @param mensagem - mensagem personalizada.
	 */
	public static void validarString(String palavra, String mensagem) {
		if(palavra == null) {
			throw new NullPointerException(mensagem);
		}else if(palavra.trim().equals("")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Verifica se um cpf e nulo ou invalido. Caso isso ocorra, o metodo
	 * ira loancar uma excecao com uma mensagem personalizada.
	 * @param cpf - Cpf a ser analisado.
	 * @param mensagem - Mensagem personalizada.
	 */
	public static void validarCpf(String cpf, String mensagem) {
		if(cpf.length() != 11) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Verifica se um preco e invalido. Caso isso ocorra, o metodo
	 * ira lancar uma excecao com uma mensagem personalizada.
	 * @param preco - Preco a ser analisado.
	 * @param mensagem - Mensagem personalizada.
	 */
	public static void validarPreco(double preco, String mensagem) {
		if(preco < 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Verifica se um fator e invalido. Caso isso ocorra, o metodo
	 * ira lancar uma excecao com uma mensagem personalizada.
	 * @param fator - Fator de desconto.
	 * @param mensagem - Mensagem personalizada.
	 */
	public static void validarFator(double fator, String mensagem) {
		if (fator == 1 || fator <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Valida uma data. Se for invalida, ira lancar uma excecao.
	 * @param data - Data que vai ser validada.
	 */
	public static void validarData(String data) {
		if ( data == null || data.trim().equals("")) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		}else{
			String[] arrayData = data.split("/");
			if (arrayData[0].length() != 2 || arrayData[1].length() != 2 || arrayData[2].length() != 4) {
				throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
			}
		}
	}
}