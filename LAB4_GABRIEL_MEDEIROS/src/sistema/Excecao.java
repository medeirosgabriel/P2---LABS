/**
 * Responsavel por lancar as excecoes durante 
 * a execucao do codigo.
 * Foi utilizado Polimorfismo de sobrecarga, a fim de ter um codigo mais "enxuto"
 * nos metodos do sistema.
 * @author Gabriel Medeiros
 */
package sistema;

public class Excecao {
	
	/**
	 * Valida uma string.
	 * @param entrada - string que vai ser avaliada.
	 */
	public void validarEntrada(String entrada) {
		if (entrada == null) {
			throw new NullPointerException("Entrada Nula");
		}else if (entrada.trim().equals("")) {
			throw new IllegalArgumentException("Entrada Invalida");
		}
	}
	
	/**
	 * Valida duas strings ao mesmo tempo.
	 * @param entrada1 - string que vai ser avaliada.
	 * @param entrada2 - string que vai ser avaliada.
	 */
	public void validarEntrada(String entrada1, String entrada2) {
		if (entrada1 == null || entrada2 == null) {
			throw new NullPointerException("Entrada Nula");
		}else if (entrada1.trim().equals("")|| entrada2.trim().equals("")) {
			throw new IllegalArgumentException("Entrada Invalida");
		}
	}
	
	/**
	 * Valida três strings ao mesmo tempo.
	 * @param entrada1 - string que vai ser avaliada.
	 * @param entrada2 - string que vai ser avaliada.
	 * @param entrada3 - string que vai ser avaliada.
	 */
	public void validarEntrada(String entrada1, String entrada2, String entrada3) {
		if (entrada1 == null || entrada2 == null || entrada3 == null) {
			throw new NullPointerException("Entrada Nula");
		}else if (entrada1.trim().equals("")|| entrada2.trim().equals("") || entrada3.trim().equals("")) {
			throw new IllegalArgumentException("Entrada Invalida");
		}
	}
}
