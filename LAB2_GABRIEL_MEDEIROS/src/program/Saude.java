/**
* Representacao da saude de um aluno.
* Toda saude é definida pela saude mental e fisica.
* Atributos: saude fisica, saude mental, emoji.
*
* @author Gabriel Medeiros
*/
package program;
public class Saude {
	
	/**
	 * Saude fisica do aluno. Os valores validos são "boa" e "fraca".
	 */
	private String saudeFisica;
	
	/**
	 * Saude mental do aluno. Os valores validos sao "boa" e "fraca".
	 */
	private String saudeMental;
	
	/**
	 * String que representa o status do aluno, levando em consideração
	 * a saude mental e a saude fisica
	 */
	private String emoji;
	
	/**
	 * Constroi um objeto do tipo Saude.
	 * A saude fisica e mental começam boas.
	 */
	public Saude() {
		this.saudeFisica = "boa";
		this.saudeMental = "boa";
		this.emoji = "<(^_^<)";
	}
	
	
	/**
	 * Define a saude mental, atraves da atribuicao do parametro valor. Alem disso, 
	 * define o emoji, a partir da funcao definirEmoji().
	 * @param valor - String que possui como valor "boa" ou "fraca".
	 */
	public void defineSaudeMental(String valor) {
		this.saudeMental = valor;
		definirEmoji();
	}
	
	/**
	 * Define a saude fisica, atraves da atribuicao do parametro valor.
	 * @param valor - String que possui como valor "boa" ou "fraca".
	 */
	public void defineSaudeFisica(String valor) {
		this.saudeFisica = valor;
		definirEmoji();
	}
	
	/**
	 * Retorna uma string que representa o estado geral da saúde mental e física.
	 * @return vai retornar: 
	 * 			"boa <(^_^<)" se a saude mental e fisica estiverem boas.
	 * 			"ok :v" se a saude mental estiver fraca e a mental boa.
	 * 			"ok :o)" se a saude mental estiver boa e a fisica fraca.
	 * 			"fraca :’(" se a saúde mental e física estiverem ruins.
	 */
	public String getStatusGeral() {
		if (this.saudeFisica.equals("boa") && this.saudeMental.equals("boa")) {
			return "boa " + emoji;
		}else if(this.saudeFisica.equals("fraca") && this.saudeMental.equals("fraca")){
			return "fraca " + emoji;
		}else {
			return "ok " + emoji;
		}
	}

	/**
	 * Vai definir o atributo emoji de acordo com os valores da saude mental e da saude fisica.
	 */
	public void definirEmoji() {
		if (this.saudeFisica.equals("boa") && this.saudeMental.equals("boa")) {
			this.emoji = "<(^_^<)";
		}else if (this.saudeFisica.equals("boa") && this.saudeMental.equals("fraca")) {
			this.emoji = ":v";
		}else if (this.saudeFisica.equals("fraca") && this.saudeMental.equals("fraca")) {
			this.emoji = ":’(";
		}else {
			this.emoji = ":o)";
		}
	}
	
	/**
	 * Retorna a saude fisica.
	 * @return a saúde fisica.
	 */
	public String getSaudeFisica() {
		return saudeFisica;
	}

	/**
	 * Atribui um certo valor ao atributo usado.
	 * @param usado - valor que vai ser atribuido a usado.
	 */
	public void setSaudeFisica(String saudeFisica) {
		this.saudeFisica = saudeFisica;
	}

	/**
	 * Retorna a saude mental.
	 * @return a saude mental.
	 */
	public String getSaudeMental() {
		return saudeMental;
	}

	/**
	 * Atribui um certo valor ao atributo usado.
	 * @param usado - valor que vai ser atribuido a usado.
	 */
	public void setSaudeMental(String saudeMental) {
		this.saudeMental = saudeMental;
	}
}
