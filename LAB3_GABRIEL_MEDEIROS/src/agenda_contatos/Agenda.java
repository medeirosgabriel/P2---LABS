/**
 * Representacao de uma agenda.
 * Toda agenda possui um array de contatos
 * Atributos: Colecao de contatos
 * 
 * @author Gabriel Medeiros
 */
package agenda_contatos;

import java.util.Arrays;

public class Agenda {
		
	/**
	 * Array que armazena os contatos da agenda.
	 */
	Contato[] contatos;
	
	/**
	 * Constroi uma agenda. Nao recebe parametros, apenas atribui um array.
	 * de 100 posicoes a contatos.
	 */
	public Agenda() {
		this.contatos = new Contato[100];
	}
	
	/**
	 * Cadastra um contato na agenda.
	 * Coloca o contato na posicao que foi atribuida a ele menos 1.
	 * Se ja existir algum contato na posicao, o mesmo vai ser substituido.
	 * @param nome - nome do contato.
	 * @param sobrenome - sobrenome do contato
	 * @param telefone - telefone do contato
	 * @param posicao - posicao do contato
	 * @param nivelAmizade - nivel de amizade do contato
	 * @return a string que representa a realizacao do cadastro
	 */
	public String cadastrarContato(String nome, String sobrenome, String telefone, int posicao, int nivelAmizade) {
		Contato contato = new Contato(nome, sobrenome, telefone, nivelAmizade);
		contatos[posicao - 1] = contato;
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * Imprime as representacoes dos contatos contidos
	 * na agenda.
	 * @return a string que contem as representacoes de todos os contatos.
	 */
	public String listarContatos() {
		String listaContatos  = "";
		for (int i = 0; i < 100; i ++) {
			if (contatos[i] != null) {
				listaContatos += "Posicao: " + (i + 1) + " " + contatos[i] + "\n";
			}
		}
		if(!listaContatos.equals(""))
			return listaContatos;
		else {
			return "Nao ha contatos na agenda";
		}
	}

	/**
	 * Exibe a representacao de uma determinado contato em uma determinada.
	 * posicao do array de contatos.
	 * @param posicao - posicao do array que vai ser validada.
	 * @return se o contato na posicao passada como parametro for diferente de null, sera retornada.
	 * uma string que representa-o. Caso contrario, sera retornado "Posicao invalida".
	 */
	public String exibirContatos(int posicao) {
		if (posicao > 0 && posicao < 101 && contatos[posicao - 1] != null) {
			return contatos[posicao - 1].representacaoComp();
		}else {
			return "Posicao Invalida";
		}
	}
	
	/**
	 * Retorna um inteiro que vai representar uma agenda, levando em consideracao
	 * os contatos e a posicao dos mesmos.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(contatos);
		return result;
	}

	/**
	 * Verifica se uma agenda e igual a outra. Para serem iguais os contatos devem ser
	 * iguais e devem estar nas mesmas posicoes.
	 * @param agenda - agenda que vai ser analisada.
	 * @return true, se forem iguais, false, se forem diferentes.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (!Arrays.equals(contatos, other.contatos))
			return false;
		return true;
	}
	
	/**
	 * Encontra o primeiro com determinado nome
	 * @param nome - nome que vai servir como base para selecionar o contato
	 * @return se algum contato for encontrado, o metodo retorna a representacao do mesmo.
	 * Caso contrario, ira retornar "Ninguem possui esse nome".
	 */
	public String primeiroContato(String nome) {
		for (Contato e : contatos) {
			if (e != null && e.getNome().equals(nome)) {
				return e.representacaoComp();
			}
		}
		return "Ninguem possui esse nome";
	}
	
	/**
	 * Encontra todos os contatos com um determinado nome.
	 * @param nome - nome que vai servir com base para selecionar os contatos
	 * @return se algum contato for encontrado, o metodo retorna a representacao do mesmo.
	 * Caso contrario, ira retornar "Ninguem possui esse nome".
	 */
	public String mesmoNome(String nome) {
		String listaContatos  = "";
		for (int i = 0; i < 100; i ++) {
			if (contatos[i] != null && contatos[i].getNome().equals(nome)) {
				listaContatos += "Posicao: " + (i + 1) + " " + contatos[i] + "\n";
			}
		}
		if (!listaContatos.equals("")) {
			return listaContatos;
		}else {
			return "Ninguem possui esse nome";
		}
	}
	
	/**
	 * Encontra todos os contatos com um determinado nivel de amizade.
	 * @param nivel - nivel que vai servir como base para selecionar os contatos.
	 * @return se algum contato for encontrado, o metodo retorna a representacao do mesmo.
	 * Caso contrario, ira retornar "Ninguem possui esse nivel de amizade". 
	 */
	public String pesquisarNivel(int nivel) {
		String listaContatos  = "";
		for (Contato e : contatos) {
			if ( e != null && e.getNivelAmizade() == nivel) {
				listaContatos += e.representacaoComp() + "\n";
			}
		}
		if (!listaContatos.equals("")) {
			return listaContatos;
		}else {
			return "Ninguem possui esse nivel de amizade";
		}
	}
	
	/**
	 * Conta quantos contatos possuem um determinado nivel de amizade.
	 * @param nivel - nivel que vai servir como base para contagem.
	 * @return a string que representa a quantidade.
	 */
	
	public String quantidadeNivel(int nivel) {
		int cont = 0;
		for (int i = 0; i < 100; i ++) {
			if (contatos[i] != null && contatos[i].getNivelAmizade() == nivel) {
				cont ++;
			}
		}
		return "A quantidade de pessoas com o nivel " + nivel + " e " + cont;
	}
	
	/**
	 * Faz a media dos niveis de amizade existentes na agenda.
	 * @return a string que representa a quantidade.
	 */
	public String mediaAmizade() {
		int quant = 0;
		int niveis = 0;
		for (int i = 0; i < 100; i ++) {
			if (contatos[i] != null) {
				quant ++;
				niveis += contatos[i].getNivelAmizade();
			}
		}
		if (quant != 0) {
			int media = niveis/quant;
			return "A media referente as amizades e " + media;
		}else {
			return "Nao ha contatos na agenda";
		}
	}
}
