package agenda_contatos;
/**
 * Classe que agrupa as excessoes utilizadas no projeto,
 * a fim de um codigo mais limpo nos metodos em que as excecoes sao lancadas.
 * 
 * @author Gabriel Medeiros
 */
public class Excecoes {
	
	/**
	 * Analisa uma string, a fim de verificar se ela e vazia ou composta apenas por espacos.
	 * Se essa condicao for satisfeita, o metodo lanca a excecao IllegalArgumentException.
	 * @param palavra - String que vai ser analisada
	 */
	public void avaliarString(String palavra) {
		if (palavra.equals("") || palavra.trim().equals("")) {
			throw new IllegalArgumentException("String Invalida");
		}
	}
	
	/**
	 * Analisa uma string, a fim de verificar se ela e nula.
	 * Se essa condicao for satisfeita, o metodo lanca a excecao NullPointerException.
	 * @param palavra - String que vai ser analisada
	 */
	public void avaliarNulo(String palavra) {
		if (palavra == null) {
			throw new NullPointerException("String Nula");
		}
	}
}
