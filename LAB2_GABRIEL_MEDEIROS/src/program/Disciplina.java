/**
* Representacao de uma disciplina.
* Toda disciplina é identificada pelo seu nome.
* Atributos: nome da disciplina, horas de estudo, notas e pesos das provas.
*
* @author Gabriel Medeiros
*/
package program;
import java.util.Arrays;

public class Disciplina {
	
	// ATRIBUTOS
	
	/**
	 * Nome da disciplina.
	 */
	private String nome;
	
	/**
	 * Quantidade de horas de estudo.
	 */
	private int horasEstudo;
	
	/**
	 * Array que armazena as notas.
	 */
	private double[] notas;
	
	/**
	 * Array que armazena os pesos das provas.
	 * O peso padrao e 1 para todas as notas.
	 */
	private int[] pesos = {1, 1, 1, 1};
	
	// METODOS
	
	/**
	 * Constroi uma disciplina, a partir do atributo nome;. 
	 * O array de notas comeca com quatro posicoes preenchidas com zeros.
	 * O campo pesos comeca com {1, 1, 1, 1}.
	 * O campo horasEstudo comeca com 0;
	 * @param nome - nome da cantina
	 */
	public  Disciplina(String nome) {
		this.nome = nome;
		notas = new double[4];
	}
	
	/**
	 * Constroi uma disciplina, a partir do atributo nome, quantidade de notas e pesos.
	 * O array notas recebe um array de quant(quantidade de notas) posicoes.
	 * @param nome - nome da disciplina.
	 * @param quant - quantidade de provas.
	 * @param pesos - array com os pesos referentes as notas.
	 */
	public  Disciplina(String nome, int quant, int[] pesos) {
		this.nome = nome;
		this.pesos = pesos;
		this.notas = new double[quant];
	}
	
	/**
	 * Constroi uma disciplina, a partir do atributo nome e da quantidade de notas.
	 * O array notas recebe um array de tamanho quant(quantidade de notas).
	 * @param nome - nome do aluno.
	 * @param quant - quantidade de notas.
	 */
	public  Disciplina(String nome, int quant) {
		this.nome = nome;
		this.notas = new double[quant];
	}
	
	/**
	 * Define as horas da disciplina.
	 * @param horas - horas da disciplina.
	 */
	public void cadastraHoras(int horas){
		this.horasEstudo = horas;
	}
	
	/**
	 * Armazena a nota no array de notas.
	 * Se a nota e a primeira, ela vai apara posicao 0 do array. Se for a segunda, vai para posicao 1. 
	 * Ou seja, a posicao vai ser igual ao numero da nota menos 1.
	 * @param nota - numero da nota.
	 * @param valorNota = resultado prova(valor da nota).
	 */
	public void cadastraNota(int nota, double valorNota) {
		notas[nota - 1] = valorNota;
	}
	
	/**
	 * Verifica se o aluno foi aprovado na disciplina.
	 * @return true se o aluno foi aprovado ou false, caso ele nao tenha sido.
	 */
	public boolean aprovado() {
		if (calcularMedia() < 7.0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Calcula a media na disciplina atraves dos pesos.
	 * @return a media.
	 */
	public double calcularMedia() {
		double soma = 0;
		int total = 0;
		for (int i = 0; i < notas.length; i++) {
			soma += notas[i]*pesos[i];
			total += pesos[i];
		}
		double media = soma/total;
		return media;
	}
	
	
	/**
	 * Retorna representacao da disciplina.
	 * No formato "nome da disciplina - media - notas".
	 * @return representacao da disciplina.
	 */
	public String toString() {
		return this.nome + " " + this.horasEstudo + " " + calcularMedia() + " " + Arrays.toString(notas);
	}

	/**
	 * Retorna o nome da disciplina.
	 * @return o nome da disciplina.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna as horas de estudo.
	 * @return as horas de estudo.
	 */
	public int getHorasEstudo() {
		return horasEstudo;
	}

	/**
	 * Retorna as notas.
	 * @return as notas.
	 */
	public double[] getNotas() {
		return notas;
	}
}
