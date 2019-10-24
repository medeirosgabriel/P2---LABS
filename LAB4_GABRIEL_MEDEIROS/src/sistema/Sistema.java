/**
 * Representacao de um Sistema que representa um Controle Academico.
 * Todo Controle Academico possui 3 estruturas de dados.
 * 1. Um HashMap para armazenar os alunos.
 * 2. Um HashMap para armazenar os grupos.
 * 3. Uma ArrayList para armazenar os alunos que fizeram questoes.
 * @author Gabriel Medeiros
 */
package sistema;


import java.util.ArrayList;
import java.util.HashMap;

import entidades.Aluno;
import entidades.Grupo;

public class Sistema {
	
	/**
	 * Armazena os alunos do controle academico.
	 * Chave: matricula do aluno.
	 * Valor: Objeto do tipo aluno.
	 */
	private HashMap<String, Aluno> alunos;
	
	/**
	 * Armazena os grupos do conrole academico.
	 * Chave: Nome do grupo.
	 * Valor: Objeto do tipo Grupo.
	 */
	private HashMap<String, Grupo> grupos;
	
	/**
	 * Armazena na ordem os alunos que responderam questoes.
	 */
	private ArrayList<Aluno> alunosQuestoes;
	
	/**
	 * Objeto do tipo excecao. Utilizado para validar os dados passados
	 * como parametro para os metodos.
	 */
	private Excecao excecao = new Excecao();
	
	/**
	 * Constroi um Sistema.
	 * Inicializa as estruturas de dados.
	 */
	public Sistema() {
		this.alunos = new HashMap<>();
		this.grupos = new HashMap<>();
		this.alunosQuestoes = new ArrayList<>();
	}
	
	/**
	 * Cadastra um aluno.
	 * O aluno é armazenado no mapa alunos. 
	 * @param matricula - matricula do aluno.
	 * @param nome - nome do aluno.
	 * @param curso - curso do aluno.
	 * @return uma string informando se o cadastro foi
	 * realizado ou se o cadastro ja havia sido efetuado.
	 */
	public String cadastrarAluno(String matricula, String nome, String curso) {
		Aluno aluno = new Aluno(matricula, nome, curso);
		if (alunos.containsKey(matricula)) {
			return "MATRICULA JA CADASTRADA!";
		}else {
			alunos.put(matricula, aluno);
			return "CADASTRO REALIZADO!";
		}
	}
	
	/**
	 * Exibe as informacoes de um aluno.
	 * @param matricula - matricula do aluno.
	 * @return uma string com as informacoes do aluno ou informando
	 * que o aluno nao foi cadastrado no controle academico.
	 */
	public String consultarAluno(String matricula) {
		excecao.validarEntrada(matricula);
		if (alunos.containsKey(matricula)) {
			Aluno aluno = alunos.get(matricula);
			return "Aluno: " + aluno.toString();
		}else {
			return "ALUNO NAO CADASTRADO";
		}
	}
	
	/**
	 * Cria um grupo de alunos.
	 * @param nome - nome do grupo dos alunos.
	 * @return uma string que informa se o grupo foi cadastrado ou se 
	 * ele ja havia sido cadastrado no controle academico.
	 */
	public String novoGrupo(String nome) {
		if (grupos.containsKey(nome)) {
			return "GRUPO JA CADASTRADO";
		}else {
			Grupo novoGrupo = new Grupo(nome);
			grupos.put(nome, novoGrupo);
			return "CADASTRO REALIZADO";
		}
	}
	
	/**
	 * Aloca um aluno em um grupo.
	 * @param matricula - matricula do aluno.
	 * @param nomeGrupo - nome do grupo.
	 * @return uma string informando se o aluno foi alocado, se o grupo nao foi 
	 * cadastrado ou se o aluno nao foi cadastrado no controle academico.
	 */
	public String alocarAluno(String matricula, String nomeGrupo) {
		excecao.validarEntrada(matricula, nomeGrupo);
		if (alunos.containsKey(matricula)) {
			if (grupos.containsKey(nomeGrupo)){
				if (grupos.get(nomeGrupo).estaNoGrupo(alunos.get(matricula))) {
					return "ALUNO JA ALOCADO";
				}else {
					grupos.get(nomeGrupo).alocaAluno(alunos.get(matricula));
					return "ALUNO ALOCADO";
				}
			}else{
				return "GRUPO NAO CADASTRADO";
			}
		}else {
			return "ALUNO NAO CADASTRADO";
		}
	}
	
	/**
	 * Imprime os integrantes de um grupo.
	 * @param nomeGrupo - nome do grupo.
	 * @return uma string com a representacao de todos 
	 * os alunos que fazem parte do grupo.
	 */
	public String imprimirGrupo(String nomeGrupo) {
		excecao.validarEntrada(nomeGrupo);
		if (grupos.containsKey(nomeGrupo)) {
			return "Alunos do grupo " + nomeGrupo + ":" + "\n" + grupos.get(nomeGrupo).imprimirGrupo();
		}else{
			return "GRUPO NAO CADASTRADO";
		}
	}
	
	/**
	 * Cadastra um aluno que respondeu uma questao.
	 * @param matricula - matricula do aluno.
	 * @return uma string informando se o aluno foi registrado ou se ele nao 
	 * esta cadastrado no controle academico.
	 */
	public String registrarAlunoQuestao(String matricula) {
		excecao.validarEntrada(matricula);
		if (alunos.containsKey(matricula)) {
			alunosQuestoes.add(alunos.get(matricula));
			return "ALUNO REGISTRADO";
		} else {
			return "ALUNO NAO CADASTRADO";
		}
	}
	
	/**
	 * Imprime na ordem os alun sque responderam as questoes.
	 * @return uma string com as informacoes dos alunos que responderam
	 * as questoes.
	 */
	public String imprimirALunosQuestoes() {
		String lista = "Alunos:\n";
		for (int i = 0; i < alunosQuestoes.size(); i++) {
			lista += (i + 1) + ". " + alunosQuestoes.get(i).toString() + "\n";
		}
		return lista;
	}

	/**
	 * Retorna um HashMap que agrupa os alunos.
	 * @return a colecao de alunos.
	 */
	public HashMap<String, Aluno> getAlunos() {
		return alunos;
	}
	
	/**
	 * Retorna um HashMap que agrupa os grupos.
	 * @return a colecao de alunos.
	 */
	public HashMap<String, Grupo> getGrupos() {
		return grupos;
	}

	/**
	 * Retorna um ArrayList que agrupa os alunos que responderam
	 * as questoes.
	 * @return a colecao de alunos que responderam as questoes.
	 */
	public ArrayList<Aluno> getAlunosQuestoes() {
		return alunosQuestoes;
	}
}
