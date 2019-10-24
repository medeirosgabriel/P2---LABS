/**
* Representacao da conta de um aluno em um laboratorio.
* Toda conta é identificada pelo nome do laboratorio.
* Atributos: nome do laboratorio, limite de memoria e total de memoria usada.
*
* @author Gabriel Medeiros
*/
package program;
public class ContaLaboratorio {

	// ATRIBUTOS
	
	/**
	 * Nome do laboratorio. No formato LCCX, onde X representa
	 * o numero do laboratorio.
	 */
	private String nomeLaboratorio;
	
	/**
	 *Memoria usada pelo usuario
	 */
	private int usado;
	
	/**
	 * Memoria limite da conta;
	 */
	private int cota;

	// METODOS
	
	/**
	 * Constroi uma conta em um laboratorio, a partir do nome do laboratorio.
	 * Toda conta comeca com o campo cota igual a 2000.
	 * @param nomeLaboratorio - nome do laboratorio no formato LCCX, onde X representa
	 * o numero do laboratorio.
	 */
	public ContaLaboratorio(String nomeLaboratorio) {
		this.nomeLaboratorio = nomeLaboratorio;
		this.cota = 2000;
	}
	
	/**
	 * Constroi uma conta em um laboratorio, a partir do nome do laboratorio e da cota.
	 * @param nomeLaboratorio - Nome do laboratorio no formato LCCX,
	 * onde X representa o numero do laboratorio.
	 * @param cota - Memoria limite da conta.
	 */
	public ContaLaboratorio(String nomeLaboratorio, int cota) {
		this.nomeLaboratorio = nomeLaboratorio;
		this.cota = cota;
	}
	
	/**
	 * Consome espaco na memoria da conta, ou seja,
	 * aumenta o espaco usado.
	 * @param mb - MegaBytes que vao consumir um determinado espaco na memoria.
	 */
	public void consomeEspaco(int mb) {
		this.usado += mb;
	}
	
	/**
	 * Libera espaco na memoria da conta, ou seja,
	 * diminui o espaco usado.
	 * @param mb - MegaBytes que vao ser excluidos da memoria.
	 */
	public void liberaEspaco(int mb) {
		if (this.usado < mb) {
			System.out.println("Essa quantidade ainda nao foi ocupada");
		} else {
			this.usado -= mb;
		}
	}

	/**
	 * Retorna um valor booleano.
	 * Verifica se a memoria usada e maior que a cota(memoria limite).
	 * @return Retorna true se a memoria usada for maior que a cota
	 * e false se for menor.
	 */
	public boolean atingiuCota() {
		if (this.usado >= this.cota) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna uma String representando a conta. Representacao segue o formato 
	 * "nome do laboratorio - memoria usada - memoria limite(cota)".
	 * @return a representacao em String de uma conta.
	 */
	public String toString() {
		return this.nomeLaboratorio + " " + this.usado + "/" + this.cota;
	}
	
	/**
	 * @return o total de memoria usada.
	 */
	public int getUsado() {
		return usado;
	}

	/**
	 * @return a cota(limite de memoria) da conta em determinado laboratorio.
	 */
	public int getCota() {
		return cota;
	}
}