/**
 * Representacao um grupo de alunos.
 * Um grupo é indentificado pelo seu nome.
 * Todo grupo possui um nome e uma ArrayList 
 * que armazena os participantes do grupo.
 * 
 * @author Gabriel Medeiros
 */
package entidades;

import java.util.ArrayList;

import sistema.Excecao;

public class Grupo {
	
	/**
	 * Objeto do tipo excecao que vai verificar
	 * os dados passados para o construtor
	 */
	Excecao excecao = new Excecao();
	
	/**
	 * Armazena os integrantes do grupo.
	 */
	private  ArrayList<Aluno> participantes;
	
	/**
	 * Nome do grupo.
	 */
	private String nome;

	/**
	 * Controi um grupo.
	 * Inicializa a ArrayList.
	 * @param nome - nome do grupo.
	 */
	public Grupo(String nome) {
		excecao.validarEntrada(nome);
		this.participantes = new ArrayList<>();
		this.nome = nome;
	}

	/**
	 * Imprime os integrantes de um grupo.
	 * @return uma string com a representacao de todos os alunos.
	 */
	public String imprimirGrupo() {
		String alunos = "";
		for (Aluno e : participantes) {
			alunos += e + "\n";
		}
		return alunos;
	}
	
	/**
	 * Aloca o aluno no grupo.
	 * @param aluno - aluno que vai ser alocado.
	 */
	public void alocaAluno(Aluno aluno) {
		this.participantes.add(aluno);
	}
	
	/**
	 * Verifica se um aluno ja esta alocado em um determinado grupo.
	 * @param aluno - aluno procurado.
	 * @return true - se o aluno ja estiver alocado. false - se o aluno.
	 * ainda nao foi alocado.
	 */
	public boolean estaNoGrupo(Aluno aluno) {
		return this.participantes.contains(aluno);
	}

	/**
	 * Retorna a arrayListDeAlunos.
	 * @return a colecao de alunos.
	 */
	public ArrayList<Aluno> getParticipantes() {
		return participantes;
	}

	/**
	 * HashCode de Grupo.
	 * Gera uma representacao numerica para o objeto do tipo grupo.
	 * Baseado no nome do grupo.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Equals de Grupo.
	 * Verifica se um obejto do tipó grupo é igual a outro objeto.
	 * Baseado no nome do grupo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
