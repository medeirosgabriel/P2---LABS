/**
 * Representacao de um aluno.
 * Um aluno e indentificado pela matricula.
 * Todo aluno possui uma matricuola, um nome e um curso.
 * 
 * @author Gabriel Medeiros
 */
package entidades;

import sistema.Excecao;

public class Aluno {
	
	/**
	 * Objeto do tipo excecao que vai verificar
	 * os dados passados para o construtor
	 */
	Excecao excecao = new Excecao();
	
	/**
	 * Matricula do aluno.
	 */
	private String matricula;
	
	/**
	 * Nome do aluno.
	 */
	private String nome;
	
	/**
	 * Nome do curso.
	 */
	private String curso;
	
	/**
	 * Constroi um aluno com base na matricula, no nome e no curso do aluno.
	 * @param matricula - matricula do aluno.
	 * @param nome - nome do aluno.
	 * @param curso - curso dos aluno.
	 */
	public Aluno(String matricula, String nome, String curso) {
		excecao.validarEntrada(matricula, nome, curso);
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}

	/**
	 * Retorna a matricula do aluno.
	 * @return a matricula do aluno.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna o nome do aluno.
	 * @return o nome do aluno.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o curso do aluno.
	 * @return o curso do aluno.
	 */
	public String getCurso() {
		return curso;
	}
	
	/**
	 * Gera a representacao de um aluno em forma de string.
	 */
	@Override
	public String toString() {
		return this.matricula + " - " + this.nome + " - " + this.curso;
	}

	/**
	 * Gera a representacao numerica de um aluno.
	 * Baseado na matricula.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Verifica se um objeto do tipo Aluno é igual a outro objeto.
	 * Baseado na matricula.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
