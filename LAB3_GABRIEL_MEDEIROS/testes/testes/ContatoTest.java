package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import agenda_contatos.Contato;

class ContatoTest {
	
	/**
	 * Contatos utilizados nos testes
	 */
	Contato c1;
	Contato c2;
	Contato c3;
	Contato c4;
	Contato c5;
	Contato c6;
	
	/**
	 * Constroi novos contatos. Cada construtor recebe o nome, o sobrenome, o telefone,
	 * a posicao na agenda e o nivel de amizade como parametros.
	 */
	@BeforeEach
	void preparaContatos(){
		c1 = new Contato("Fernando",  "Tavares", "83 99473-1433", 3);
		c2 = new Contato("Fabricio",  "Oliveira", "83 8764-3234", 1);
		c3 = new Contato("Tadeu",  "Medeiros", "83 9455-5641", 5);
		c4 = new Contato("Fernando",  "Tavares", "83 99435-5467", 3);
		c5 = new Contato("Tadeu",  "Lacerda", "83 9455-5641", 4);
		c6 = new Contato("Fabricio",  "Oliveira", "83 9344-3434", 2);
	}
	
	/**
	 * Testa quando o nome passado e nulo. Deve lancar uma excessao do tipo 
	 * NullPointerException.
	 */
	@Test
	void testCadastraContatosNomeNulo() {
		try {
			c1 = new Contato(null, "Medeiros", "83 4545-2323", 2); // LANCA EXCECAO
		}catch (NullPointerException npe) {
			assertEquals("String Nula",npe.getMessage());
		}
	}
	
	/**
	 * Testa quando o sobrenome passado e nulo. Deve lancar uma excessao do tipo 
	 * NullPointerException.
	 */
	@Test
	void testCadastraContatosSobrenomeNulo() {
		try {
			c1 = new Contato("Gabriel", null, "83 4545-2323", 2); // LANCA EXCECAO 
		}catch (NullPointerException npe) {
			assertEquals("String Nula",npe.getMessage());
		}
	}

	/**
	 * Testa quando o telefone passado e nulo. Deve lancar uma excessao do tipo 
	 * NullPointerException.
	 */
	@Test
	void testCadastraContatosTelefoneNulo() {
		try {
			c1 = new Contato("Gabriel", "Medeiros", null, 2); // LANCA EXCECAO
		}catch(NullPointerException npe) {
			assertEquals("String Nula",npe.getMessage());
		}
	}
	
	/**
	 * Testa quando o nome passado e vazio. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosNomeVazio() {
		try {
			c1 = new Contato("", "Medeiros", "83 4545-2323", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa quando o sobrenome passado e vazio. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosSobrenomeVazio() {
		try {
			c1 = new Contato("Gabriel", "", "83 4545-2323", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa quando o telefone passado e vazio. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosTelefoneVazio() {
		try {
			c1 = new Contato("Gabriel", "Medeiros", "", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa quando o nome passado possui apenas espacos. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosNomeApenasEspaco() {
		try {
			c1 = new Contato("      ", "Medeiros", "83 4545-2323", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa quando o sobrenome passado possui apenas espacos. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosSobrenomeApenasEspaco() {
		try {
			c1 = new Contato("Gabriel", "     ", "83 4545-2323", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa quando o telefone passado possui apenas espacos. Deve lancar uma excessao do tipo 
	 * IllegalArgumentException.
	 */
	@Test
	void testCadastraContatosTelefoneApenasEspaco() {
		try {
			c1 = new Contato("Gabriel", "Medeiros", "     ", 2); // LANCA EXCECAO
		}catch (IllegalArgumentException iae){
			assertEquals("String Invalida",iae.getMessage());
		}
	}
	
	/**
	 * Testa o toString de cada contato.
	 * Formato: posicao + nome + sobrenome
	 */
	@Test
	void testToString() {
		assertEquals("Nome: Fernando Tavares", c1.toString());
		assertEquals("Nome: Fabricio Oliveira", c2.toString());
		assertEquals("Nome: Tadeu Medeiros", c3.toString());
		assertEquals("Nome: Fernando Tavares", c4.toString());
		assertEquals("Nome: Tadeu Lacerda", c5.toString());
		assertEquals("Nome: Fabricio Oliveira", c6.toString());
	}
	
	/**
	 * Testa a representacao completa de cada contato.
	 * Formato: nome + sobrenome + telefone + nivel de amizade.
	 */
	@Test
	void testRepresentacaoCompleta() {
		assertEquals("Nome: Fernando Tavares - 83 99473-1433   Nivel de Amizade: 3(Amigo)", c1.representacaoComp());
		assertEquals("Nome: Fabricio Oliveira - 83 8764-3234   Nivel de Amizade: 1(Distante)", c2.representacaoComp());
		assertEquals("Nome: Tadeu Medeiros - 83 9455-5641   Nivel de Amizade: 5(Irmao)", c3.representacaoComp());
		assertEquals("Nome: Fernando Tavares - 83 99435-5467   Nivel de Amizade: 3(Amigo)", c4.representacaoComp());
		assertEquals("Nome: Tadeu Lacerda - 83 9455-5641   Nivel de Amizade: 4(Amigao)", c5.representacaoComp());
		assertEquals("Nome: Fabricio Oliveira - 83 9344-3434   Nivel de Amizade: 2(Colega)", c6.representacaoComp());
		
	}
	
	/**
	 * Testa o metodo equals. Esse metodo deve levar em consideracao apenas
	 * o nome e sobrenome de cada contato, ou seja, dois contatos sao
	 * iguais se possuirem o mesmo nome e sobrenome.
	 */
	@Test
	void testEquals() {
		assertTrue(c1.equals(c4));
		assertTrue(c2.equals(c6));
		assertFalse(c3.equals(c5));
		assertFalse(c5.equals(c2));
		
	}
}