/**
* Representacao da conta de um aluno em uma cantina.
* Toda conta é identificada pelo nome da cantina
* Atributos: nome da cantina, conta, total, itens, detalhes.
*
* @author Gabriel Medeiros
*/
package program;
import java.util.ArrayList;
import java.util.List;
public class ContaCantina {
	
	// ATRIBUTOS
	
	/**
	 * Nome da cantina.
	 */
	private String nomeCantina;
	
	/**
	 * Valor que falta pagar.
	 */
	private int conta;
	
	/**
	 * Total ja consumido.
	 */
	private int total;
	
	/**
	 * Quantidade de itens comprados.
	 */
	private int itens;
	
	/**
	 * Detalhes das compras feitas.
	 */
	private List<String> detalhes;
	
	// METODOS
	
	/**
	 * Constroi uma conta na cantina, a partir do nome da cantina.
	 * A conta, o total e os itens comeram com valor 0.
	 * @param nomeCantina - nome da cantina.
	 */
	public ContaCantina(String nomeCantina) {
		this.nomeCantina = nomeCantina;
		this.conta = 0;
		this.total = 0;
		this.itens = 0;
		detalhes = new ArrayList<>();
	}
	
	/**
	 * Faz uma compra na cantina, a partir da quantidade de itens e do valor referente a eles em centavos. 
	 * Logo o valor e acrescentado na conta e a quantidade de itens no atributo itens.
	 * @param qtdItens - quantidade de itens comprados.
	 * @param valorCentavos - valor referente a compra.
	 */
	public void cadastraLanche(int qtdItens, int valorCentavos) {
		this.itens += qtdItens;
		this.conta += valorCentavos;
		this.total += valorCentavos;
	}
	
	/**
	 * Faz uma compra na cantina, a partir da quantidade de itens, do valor referente a eles em centavos e dos detalhes referentes a compra.
	 * Logo o valor e acrescentado na conta, a quantidade de itens no atributo itens e os detalhes na lista detalhes.
	 * @param qtdItens - quantidade de itens.
	 * @param valorCentavos - valor referente a compra. Ex: Almoco com os amigo, Janta do dia 28/05, etc.
	 * @param detalhes - detalhe referente a compra.
	 */
	public void cadastraLanche(int qtdItens, int valorCentavos, String detalhes) {
		this.itens += qtdItens;
		this.conta += valorCentavos;
		this.detalhes.add(detalhes);
	}
	
	/**
	 * Paga toda a conta ou uma parcela, dependendo do valor, ja que nao e possivel pagar 500 centavos 
	 * referente a uma conta de 300 centavos. 
	 * @param valorCentavos - valor que vai ser pago.
	 */
	public void pagaConta(int valorCentavos) {
		if (valorCentavos > conta) {
			System.out.println("Nao e possivel pagar esse valor");
		}else {
			this.conta -= valorCentavos;
		}
	}
	
	/**
	 * Retorna uma string que representa o aluno.
	 * No formato "nome da cantina - quantidade deitens - total".
	 * @return String que representa os dados da conta feita na cantina.
	 */
	public String toString() {
		return this.nomeCantina + " " + this.itens + " " + this.total;
	}
	
	/**
	 * Imprime todos os detalhes referentes as compras.
	 */
	public void listarDetalhes() {
		detalhes.forEach(System.out::println);
	}
	
	/**
	 * Retorna a conta(o que falta pagar).
	 * @return a conta.
	 */
	public int getConta() {
		return this.conta;
	}
	
	/**
	 * Retorna o nome da cantina.
	 * @return o nome da cantina.
	 */
	public String getNomeCantina() {
		return nomeCantina;
	}

	/**
	 * Retorna o total(valor referente a todas as compras).
	 * @return o total.
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Retorna a quantidade de itens ja comprados.
	 * @return a quantidade de itens.
	 */
	public int getItens() {
		return itens;
	}
}
