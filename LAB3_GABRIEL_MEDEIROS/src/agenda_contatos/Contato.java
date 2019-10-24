/**
 * Representacao de um contato.
 * Todo contato e indentificado pelo seu nome e sobrenome.
 * Atributos: nome, sobrenome, posicao na agenda e um nivel de amizade.
 * 
 * @author Gabriel Medeiros
 */
package agenda_contatos;

public class Contato {
	
	/**
	 * Array que armazena os niveis de amizade. Os indices tem uma relacao com as Strings
	 * da seguinte forma: Nivel 1(indice) de amizade = Distante
	 * 					  Nivel 2(indice) de amizade = Colega
	 * 					  Nivel 3(indice) de amizade = Amigo
	 * 					  ...
	 * 					  ... 
	 */
	
	String[] niveisAmizade = {" ", "Distante", "Colega", "Amigo", "Amigao", "Irmao"};
	
	/**
	 * Nome do contato
	 */
	private String nome;
	
	/**
	 * Sobrenome do contato
	 */
	private String sobrenome;
	
	/**
	 * Telefone do contato
	 */
	private String telefone;
	
	/**
	 * Nivel de amizade
	 */
	private int nivelAmizade;
	
	/**
	 * Objeto do tipo Excecoes que vai ser utilizado para avaliar
	 * os parametros passados para o construtor.
	 */
	private Excecoes excecao;
	
	/**
	 * Constroi um Contato a partir do nome, do sobrenome, do telefone,
	 * da posicao na agenda e do nivel de amizade. Alem disso, atribui um 
	 * valor do tipo Excecoes a variavel excecao.
	 * @param nome - nome do contato
	 * @param sobrenome - sobrenome do contato
	 * @param telefone - telefone do contato
	 * @param nivelAmizade - nivel de amizade. Varia de 1 ate 5.
	 */
	public Contato(String nome, String sobrenome, String telefone, int nivelAmizade) {
		excecao = new Excecoes();
		excecao.avaliarNulo(nome);
		excecao.avaliarNulo(sobrenome);
		excecao.avaliarNulo(telefone);
		excecao.avaliarString(nome);
		excecao.avaliarString(sobrenome);
		excecao.avaliarString(telefone);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.nivelAmizade = nivelAmizade;
	}
	
	/**
	 * Retorna uma string de representacao.
	 * @return a string que representa um contato no formato: nome + sobrenome.
	 */
	@Override
	public String toString() {
		return "Nome: " + this.nome + " " + this.sobrenome;
	}
	
	/**
	 * Retorna uma string de representacao mais completa que o toString().
	 * @return a string que representa um contato no formato: posicao + nome + sobrenome + nivel de amizade.
	 */
	public String representacaoComp() {
		return "Nome: " + this.nome + " " + this.sobrenome + " - " + this.telefone + "   " + "Nivel de Amizade: " + this.nivelAmizade + "(" + this.niveisAmizade[this.nivelAmizade] + ")";
	}

	/**
	 * @return o nome do contato.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @return o nivel de amizade.
 	 */
	public int getNivelAmizade() {
		return this.nivelAmizade;
	}
	
	/**
	 * Retorna um inteiro que vai representar um contato, levando em consideracao
	 * o nome e o sobrenome.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	/**
	 * Compara um contato contato com um objeto do tipo Object - qualquer objeto,
	 * ja que toda classe herda Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}
}
