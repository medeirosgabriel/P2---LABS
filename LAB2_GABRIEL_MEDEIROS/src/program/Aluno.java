/**
* Representacao de um aluno.
* Toda aluno e identificado pela sua matricula
* Atributos: nome do aluno, matricula.
*
* @author Gabriel Medeiros
*/
package program;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
public class Aluno {
	
	Scanner input = new Scanner(System.in);
	
	// ATRIBUTOS
	
	/**
	 * Nome do aluno.
	 */
	String nome;
	
	/**
	 * Matricula do Aluno.
	 */
	int matricula;
	
	/**
	 * Objeto do tipo saude que vai
	 * representar a saude do aluno.
	 */
	Saude saude = new Saude();
	
	/**
	 * Mapa que armazena as disciplinas. Chave - nome da disciplina, Valor - objeto do tipo  Discplina
	 */
	Map<String, Disciplina> disciplinas = new HashMap<>();
	
	/**
	 * Mapa que armazena as contas dos laboratorio. Chave - nome do laboratorio,  Valor - objeto do tipo ContaLaboratorio
	 */
	Map<String, ContaLaboratorio> laboratorios = new HashMap<>();
	
	/**
	 * Mapa que armazena as contas das cantinas. Chave - nome da cantina, Valor - obejto do tipo ContaCantina
	 */
	Map<String, ContaCantina> cantinas = new HashMap<>();
	
	/**
	 * Constroi um novo aluno, a partir do nome da matricula.
	 * @param nome - nome do aluno.
	 * @param matricula - matricula.
	 */
	public Aluno(String nome, int matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}
	
	// METODOS
	
	/**
	 * Cadastra uma conta no laboratorio. 
	 * O nome do laboratorio e solicitado.
	 * Se ainda nao tiver uma conta cadastrada nele, o metodo vai perguntar se o usuario quer definir a cota(memoria limite).
	 * Sendo assim, depois a conta e inserida no mapa laboratorios, onde elas ficam localizadas.
	 */
	public void cadastraLaboratorio() {
		System.out.println("====== CADASTRANDO LABORATORIO =====");
		System.out.print("Nome do Laboratorio: ");
		String nomeLaboratorio = input.nextLine();
		if (laboratorios.containsKey(nomeLaboratorio)) {
			System.out.println("Esse laboratorio ja foi cadastrado");
		}else {
			System.out.print("Deseja definir a cota(s/n)? ");
			char resposta = input.nextLine().charAt(0);
			if (resposta == 'n') {
				laboratorios.put(nomeLaboratorio, new ContaLaboratorio(nomeLaboratorio));
			}else {
				System.out.print("Qual a cota? ");
				int cota = input.nextInt();
				input.nextLine();
				laboratorios.put(nomeLaboratorio, new ContaLaboratorio(nomeLaboratorio, cota));
			}
		}
	}
	
	/**
	 * Consome o espaco de uma conta em um laboratorio. 
	 * O nome do laboratorio e solicitado.
	 * Se o aluno possuir uma conta nele, vai perguntar a quantidade que o usuario deseja consumir.
	 * Sendo assim, o metodo vai aumentar a quantidade da memória usada atraves do metodo consomeEspaco().
	 */
	public void consomeEspaco() {
		System.out.println("===== CONSUMINDO ESPACO =====");
		System.out.print("Nome do Laboratorio: ");
		String nomeLaboratorio = input.nextLine();
		if (laboratorios.containsKey(nomeLaboratorio)) {
			System.out.print("Qual a quantidade de espaco que voce quer consumir? ");
			int quantidade = input.nextInt();
			input.nextLine();
			laboratorios.get(nomeLaboratorio).consomeEspaco(quantidade);
		}else {
			System.out.println("Este laboratorio nao foi cadastrado");
		}
		
	}
	
	/**
	 * Libera espaco de uma conta em um laboratorio. 
	 * O metodo pergunta o nome do laboratorio,
	 * Se o usuario possuir uma conta nele, a quantidade de memoria que o usuario quer liberar vai ser solicitada.
	 * Se essa quantidade for maior que a memoria ocupada, o metodo nao vai liberar tal espaco.
	 * Caso contrario, ele vai liberar atraves do metodo liberaEspaco().
	 */
	public void liberaEspaco() {
		System.out.println("===== LIBERANDO ESPACO =====");
		System.out.print("Nome do Laboratorio: ");
		String nomeLaboratorio = input.nextLine();
		if (laboratorios.containsKey(nomeLaboratorio)) {
			System.out.println("Qual a quantidade de espaco que voce quer liberar? ");
			int quantidade = input.nextInt();
			input.nextLine();
			if (quantidade > laboratorios.get(nomeLaboratorio).getUsado()) {
				System.out.println("Essa quantidade ainda não foi ocupada");
			}else {
				laboratorios.get(nomeLaboratorio).liberaEspaco(quantidade);
			}
		}else {
			System.out.println("Este laboratorio nao foi cadastrado");
		}
	}
	
	/**
	 * Verifica se a memoria foi ocupada ja atingiu a cota(memoria limite). 
	 * O nome do laboratorio e solicitado.
	 * Se o usuario possuir uma conta nele, o metodo vai retornar a resposta referente a ultrapassagem da cota.
	 */
	public void atingiuCota() {
		System.out.println("===== ATINGIU COTA =====");
		System.out.print("Nome do Laboratorio: ");
		String nomeLaboratorio = input.nextLine();
		if (laboratorios.containsKey(nomeLaboratorio)) {
			if (laboratorios.get(nomeLaboratorio).getUsado() > laboratorios.get(nomeLaboratorio).getCota()) {
				System.out.println("Cota atingida");
			}else {
				System.out.println("Cota nao atingida");
			}
		}else {
			System.out.println("Este laboratorio nao foi cadastrado");
		}
	}
	
	/**
	 * Retorna as informacoes da conta de um laboratorio. 
	 * O nome do laboratorio vai ser solicitado
	 * Se o aluno possuir uma conta no laboratorio o metodo imprime uma string referente
	 * a representacao da conta no fomato " quantidade usada - quantidade limite".
	 */
	public void laboratorioToString() {
		System.out.println("===== INFORMACOES LABORATORIO =====");
		System.out.print("Nome do Laboratorio: ");
		String nomeLaboratorio = input.nextLine();
		if (laboratorios.containsKey(nomeLaboratorio)) {
			System.out.println("Quantidade Usada: " + laboratorios.get(nomeLaboratorio).getUsado() + "   " + "Quantidade Limite: " + laboratorios.get(nomeLaboratorio).getCota());
		}else {
			System.out.println("Este laboratorio nao foi cadastrado");
		}
	}
	
	/**
	 * Cadastra uma disciplina. 
	 * O  nome da disciplina vai ser solicitado.
	 * Se ela ainda nao foi cadastrada, o metodo armazena no mapa disciplinas.
	 */
	public void cadastraDisciplinas() {
		System.out.println("===== CADASTRANDO DISCIPLINAS =====");
		System.out.print("Nome da disciplina: ");
		String nomeDisciplina = input.nextLine();
		if (disciplinas.containsKey(nomeDisciplina)) {
			System.out.println("Essa disciplina ja foi cadastrada");
		}else{
			disciplinas.put(nomeDisciplina, new Disciplina(nomeDisciplina));
		}
	}
	
	/**
	 * Cadastra as horas de uma disciplina. 
	 * O nome da disciplina vai ser solicitado. 
	 * Se essa disciplina ja foi cadastrada, o metodo vai perguntar qual a quantidade de horas. 
	 * Sendo assim, o valor de horas e atribuido a disciplina atravaes do metodo cadastraHoras().
	 */
	public void cadastraHoras() {
		System.out.println("===== CADASTRANDO HORAS =====");
		System.out.print("Nome da disciplina: ");
		String nomeDisciplina = input.nextLine();
		if (!disciplinas.containsKey(nomeDisciplina)) {
			System.out.println("Essa disciplina nao foi cadastrada");
		}else {
			System.out.print("Qual a quantidade de horas? ");
			int horas = input.nextInt();
			input.nextLine();
			disciplinas.get(nomeDisciplina).cadastraHoras(horas);
		}
	}
	
	/**
	 * Cadastra as notas do aluno. 
	 * O nome da disciplina vai ser solicitado. 
	 * Se a disciplina ja foi cadastrada, a numeracao da prova e a nota são solicitados. 
	 * Sendo assim, ele altera o valor da nota da disciplina atraves do metodo cadastraNota().
	 */
	public void cadastraNota() {
		System.out.println("===== CADASTRANDO NOTAS =====");
		System.out.print("Nome da disciplina: ");
		String nomeDisciplina = input.nextLine();
		if (!disciplinas.containsKey(nomeDisciplina)) {
			System.out.println("Essa disciplina nao foi cadastrada");
		}else {
			System.out.print("Qual a prova? ");
			int prova = input.nextInt();
			input.nextLine();
			System.out.print("Qual a nota? ");
			double nota = input.nextDouble();
			input.nextLine();
			disciplinas.get(nomeDisciplina).cadastraNota(prova, nota);;
		}
	}
	
	/**
	 * Verifica a aprovacao do aluno. 
	 * O  nome da disciplina vai ser solicitado
	 * Se a disciplina ja foi cadastrada, a media vai ser calculada atraves do metodo aprovado().
	 * Dependendo do retorno booleano da funcao, é afirmado que que o aluno foi aprovado ou nao.
	 */
	public void aprovado() {
		System.out.println("===== VERIFICANDO APROVACAO =====");
		System.out.print("Nome da disciplina: ");
		String nomeDisciplina = input.nextLine();
		if (!disciplinas.containsKey(nomeDisciplina)) {
			System.out.println("Essa disciplina nao foi cadastrada");
		}else {
			if (disciplinas.get(nomeDisciplina).aprovado()) {;
				System.out.println("Aprovado");
			}else {
				System.out.println("Nao aprovado");
			}
		}
	}
	
	/**
	 * Representa a disciplina atraves de três strings. 
	 * O nome da disciplina e solicitado
	 * Se essa disciplina ja foi cadstrada, ele irá imprimir as strings. 
	 * A primeira se refere ao nome da disciplina, a segunda as horas de estudo e a terceira a media.
	 */
	public void disciplinaToString() {
		System.out.println("===== DISCIPLINA DADOS =====");
		System.out.print("Nome da disciplina: ");
		String nomeDisciplina = input.nextLine();
		if (!disciplinas.containsKey(nomeDisciplina)) {
			System.out.println("Essa disciplina nao foi cadastrada");

		}else {
			System.out.println("Disciplina: " + disciplinas.get(nomeDisciplina).getNome());
			System.out.println("Horas de Estudo: " + disciplinas.get(nomeDisciplina).getHorasEstudo());
			System.out.println("Media: " + disciplinas.get(nomeDisciplina).calcularMedia());
		}
	}
	
	/**
	 * Cadastra a cantina. 
	 * O nome da cantina e solicitado
	 * Caso ela ainda nao tenha sido cadastrada, o método vai armazenar a conta no mapa cantinas. 
	 */
	public void cadastraCantina() {
		System.out.println("===== CADASTRANDO CANTINA =====");
		System.out.print("Nome da cantina: ");
		String nomeCantina = input.nextLine();
		if (cantinas.containsKey(nomeCantina)) {
			System.out.println("Essa cantina ja foi cadastrada");
		}else {
			cantinas.put(nomeCantina, new ContaCantina(nomeCantina));
		}
	}
	
	/**
	 * Faz compras na cantina. 
	 * O nome da cantina e solicitado.
	 * Caso essa cantina ja tenha sido cadastrada, a quantidade de itens e a quantidade de centavos(total) sao solicitadas.
	 * Sendo assim, o metodo cadastra o lanche atraves da funcao cadastraLanche().
	 */
	public void cadastraLanche() {
		System.out.println("===== CADASTRANDO LANCHE =====");
		System.out.print("Nome da cantina: ");
		String nomeCantina = input.nextLine();
		if (!cantinas.containsKey(nomeCantina)) {
			System.out.println("Essa cantina nao foi cadastrada");
		}else {
			System.out.print("Qual a quantidade de itens? ");
			int quantidade = input.nextInt();
			input.nextLine();
			System.out.print("Qual o total(em cetavos)? ");
			int valor = input.nextInt();
			input.nextLine();
			cantinas.get(nomeCantina).cadastraLanche(quantidade, valor);
		}
	}
	
	/**
	 * Paga a conta. 
	 * O nome da cantina e solicitado.
	 * Se ela ja foi cadastrada, a quantidade que o aluno quer pagar tambem e solicitada.
	 * Sendo assim, a quantidade e paga atraves do metodo pagaConta().
	 */
	public void pagarConta() {
		System.out.println("===== PAGANDO A CONTA =====");
		System.out.print("Nome da cantina: ");
		String nomeCantina = input.nextLine();
		if (!cantinas.containsKey(nomeCantina)) {
			System.out.println("Essa cantina nao foi cadastrada");
		}else {
			System.out.print("Qual o total(em centavos) que voce deseja pagar? ");
			int valor = input.nextInt();
			input.nextLine();
			if (valor > cantinas.get(nomeCantina).getConta()) {
				System.out.println("Nao e possivel pagar essa quantia");
			}else {
				cantinas.get(nomeCantina).pagaConta(valor);;
			}
		}
	}
	
	/**
	 * Imprime uma string referente a quantidade que falta pagar. 
	 * O nome da cantina e solicitado.
	 * Caso a cantina tenha sido cadastrada, ele imprime um texto representando o valor que falta ser pago.
	 */
	public void getFaltaPagar() {
		System.out.println("===== FALTA PAGAR =====");
		System.out.print("Nome da cantina: ");
		String nomeCantina = input.nextLine();
		if (!cantinas.containsKey(nomeCantina)) {
			System.out.println("Essa cantina nao foi cadastrada");
		}else {
			System.out.println("Falta pagar " + cantinas.get(nomeCantina).getConta() + " centavos");
		}
	}
	
	/**
	 * Representa a conta da aluno na cantina. 
	 * O nome da cantina e solicitado.
	 * Caso a cantina tenha sido cadastrada, vai ser impressa a string no formato
	 * "nome da cantina + quantidade de itens + quantidade que falta pagar".
	 */
	public void cantinaToString() {
		System.out.println("===== INFORMACOES DA CANTINA =====");
		System.out.print("Nome da cantina: ");
		String nomeCantina = input.nextLine();
		if (!cantinas.containsKey(nomeCantina)) {
			System.out.println("Essa cantina nao foi cadastrada");
		}else {
			System.out.println("Nome da cantina: " + nomeCantina + "   " + "Itens: " + cantinas.get(nomeCantina).getItens() + "   " + "Falta Pagar: " +  cantinas.get(nomeCantina).getConta());
		}
	}
	
	/**
	 * Define a saude mental do aluno
	 * O metodo pede ao usuario que insira a sua situacao mental(boa ou fraca), 
	 * para que o valor seja alterado atraves do metodo defineSaudeMental().
	 */
	public void defineSaudeMental() {
		System.out.println("===== DEFININDO A SAUDE MENTAL =====");
		System.out.print("Como esta a sua saude mental? ");
		String saudeMental = input.nextLine();
		saude.defineSaudeMental(saudeMental);
	}
	
	/**
	 * Define a saude fisica do aluno. 
	 * A situacao fisica (boa ou fraca) e solicitada, 
	 * dai o valor e alterado atraves do método defineSaudeFisica().
	 */
	public void defineSaudeFisica() {
		System.out.println("===== DEFININDO A SAUDE FISICA =====");
		System.out.print("Como esta a sua saude fisica? ");
		String saudeFisica = input.nextLine();
		saude.defineSaudeFisica(saudeFisica);
	}
	
	/**
	 * Imprime o status geral do aluno.
	 * O metodo vai imprimir tres strings que representam o status.
	 * A primeira se refere a saude fisica.
	 * A segunda se refere a saude mental.
	 * A terceira se refere a uma representacao que leva em consideracao os dois estados do aluno.
	 */
	public void getStatusGeral() {
		System.out.println("===== STATUS GERAL =====");
		System.out.println("Saude fisica: " + saude.getSaudeFisica());
		System.out.println("Saude mental: " + saude.getSaudeMental());
		System.out.println("Resumindo: " + saude.getStatusGeral());
	}
}
