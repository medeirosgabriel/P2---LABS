/**
* Responsável por toda execucao do bonus 5.4 e 5.5.
* 
*
* @author Gabriel Medeiros
*/
package program;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Sistema {
	
	static Scanner input = new Scanner(System.in);
	
	// ATRIBUTOS
	
	/**
	 * Mapa que armazena os aluno cadastrados. Chave - matricula do aluno  Valor - Objeto do tipo aluno.
	 */
	static Map<Integer, Aluno> alunos = new HashMap<>();
	
	/**
	 * Representa o aluno que está logado no sistema.
	 */
	static Aluno aluno_logado = null;
	
	// METODOS
	
	/**
	 * Imprime o menu principal do sistema.
	 * Sao 19 operacoes diferentes.
	 * Foi usado o switch case com a variavel acao.
	 * Antes das acoes(exceto cadastrar aluno) serem realizadas, e verificado se algum aluno esta logado.
	 */
	public static void menu() {
		int acao = -1;
		while(acao != 19) {
			System.out.println("===== MENU =====");
			System.out.println("1.Cadastra Laboratorio");
			System.out.println("2.Consumir Espaco");
			System.out.println("3.Liberar Espaco");
			System.out.println("4.Verificar Cota");
			System.out.println("5.Informacoes Laboratorio");
			System.out.println("6.Cadastra Disciplina");
			System.out.println("7.Cadastra Nota");
			System.out.println("8.Verificar Aprovacao");
			System.out.println("9.Verificar Disciplina");
			System.out.println("10.Cadastrar Cantina");
			System.out.println("11.Cadastra Lanche");
			System.out.println("12.Pagar Conta");
			System.out.println("13.Informacoes Cantina");
			System.out.println("14.Definir Saude Mental");
			System.out.println("15.Definir Saude fisica");
			System.out.println("16.Status Geral");
			System.out.println("17.Fazer login");
			System.out.println("18.Cadastrar aluno");
			System.out.println("19.Sair");
			System.out.print("Operacao: ");
			acao = input.nextInt();
			input.nextLine();
			System.out.println(" ");
			switch(acao) {
			case 1:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cadastraLaboratorio();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 2:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.consomeEspaco();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 3:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.liberaEspaco();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 4:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.atingiuCota();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 5:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.laboratorioToString();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 6:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cadastraLaboratorio();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 7:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cadastraDisciplinas();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 8:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.aprovado();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 9:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.disciplinaToString();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 10:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cadastraCantina();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 11:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cadastraLanche();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 12:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.pagarConta();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 13:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.cantinaToString();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 14:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.defineSaudeMental();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 15:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.defineSaudeFisica();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 16:
				if (!alunoNull(aluno_logado)) {
					aluno_logado.getStatusGeral();
				}else {
					System.out.println("Nenhum aluno logado.");
				}
				System.out.println(" ");
				break;
			case 17:
				Aluno temp = fazerLogin();
				if (temp != null) {
					aluno_logado = temp;
				}
				System.out.println(" ");
				break;
			case 18:
				cadastraAluno();
				System.out.println(" ");
				break;
			case 19:
				break;
			}
		}
	}
	
	/**
	 * O sistema cadastra um aluno.
	 * O nome do aluno e solicitado junto com a sua matricula.
	 * Sendo assim, o aluno vai ser armazenado no map alunos.
	 */
	private static void cadastraAluno() {
		System.out.println("====== CADASTRANDO ALUNO =====");
		System.out.println("Nome do aluno: ");
		String nome = input.nextLine();
		System.out.println("Matricula do aluno: ");
		int matricula = input.nextInt();
		input.nextLine();
		alunos.put(matricula, new Aluno(nome, matricula));
	}
	
	/**
	 * Verifica se existe um aluno logado.
	 * @param aluno - aluno que vai ser verificado.
	 * @return Se o aluno apontar nao apontar para algum endereco de memoria, significa
	 * que nenhum aluno esta logado, dai o mnetodo retorna true. Caso contrario, ira retornar false.
	 */
	private static boolean alunoNull(Aluno aluno) {
		if (aluno == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Faz o login no sistema.
	 * O nome e a matricula sao solicitados.
	 * Se o aluno estiver cadastrado no sistema, o login sera feito.
	 * Sendo assim, o aluno logado passa a ser outro (essa operacao esta dentro do menu).
	 * @return Se o aluno foi cadastrado a funcao ira retornar o aluno. Caso contrario,
	 * ira retornar null.
	 */
	private static Aluno fazerLogin() {
		System.out.println("===== FAZENDO LOGIN =====");
		System.out.print("Nome do aluno: ");
		String nome_aluno = input.nextLine();
		System.out.print("Matricula do aluno: ");
		int matricula_aluno = input.nextInt();
		input.nextLine();
		if (alunos.containsKey(matricula_aluno)) {
			return alunos.get(matricula_aluno);
		}else {
			System.out.println("Esse aluno ainda não foi cadastrado");
			return null;
		}
	}
}
