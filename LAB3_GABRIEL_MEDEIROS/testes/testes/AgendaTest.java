package testes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import agenda_contatos.Agenda;
import agenda_contatos.Contato;

class AgendaTest {
	
	/**
	 * Agendas que serao ser utilizadas nso testes
	 */
	Agenda a1;
	Agenda a2;
	
	/**
	 * Constroi as agendas
	 */
	@BeforeEach
	void testPreparaAgendas() {
		a1 = new Agenda();
		a2 = new Agenda();
	}

	/**
	 * Verifica se as agendas sao iguais. Logo o equals tambem e verificado.
	 */
	@Test
	void testCadastraContatosEquals() {
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		a2.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		assertFalse(a1.equals(a2));
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		a2.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		assertTrue(a1.equals(a2));
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a2.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		assertFalse(a1.equals(a2));
		a1.cadastrarContato("Fernando", "Tavares", "83 9945 3433", 100, 1);
		assertFalse(a1.equals(a2));
	}

	/**
	 * Testa a funcao listarContatos.
	 */
	@Test
	void testListarContatos() {
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		assertEquals("Posicao: 4 Nome: Gabriel Medeiros\nPosicao: 67 Nome: Daniel Lacerda\n", a1.listarContatos());
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a2.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		assertEquals("Posicao: 4 Nome: Gabriel Medeiros\nPosicao: 23 Nome: Chico Pereira\nPosicao: 67 Nome: Daniel Lacerda\n", a1.listarContatos());
		assertEquals("Posicao: 76 Nome: Chico Lima\n", a2.listarContatos());
	}
	
	/**
	 * Testa a funcao exibirContatos.
	 */
	@Test
	void testExibirContato() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 100, 5);
		assertEquals("Posicao Invalida", a1.exibirContatos(101));
		assertEquals("Posicao Invalida", a1.exibirContatos(0));
		assertEquals("Nome: Daniel Lacerda - 83 9877-2132   Nivel de Amizade: 5(Irmao)", a1.exibirContatos(100));
		assertEquals("Nome: Chico Pereira - 83 9856-2433   Nivel de Amizade: 5(Irmao)", a1.exibirContatos(23));
	}
	
	
	/**
	 * Testa a funcao primeiroContato.
	 */
	@Test
	void testPrimeiroContato() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		assertEquals("Nome: Chico Pereira - 83 9856-2433   Nivel de Amizade: 5(Irmao)", a1.primeiroContato("Chico"));
		assertEquals("Nome: Gabriel Medeiros - 83 8786-2323   Nivel de Amizade: 1(Distante)", a1.primeiroContato("Gabriel"));
		assertEquals("Ninguem possui esse nome", a1.primeiroContato("Fernando"));
	}
	
	/**
	 * Testa a funcao mesmoNome.
	 */
	@Test
	void testMesmoNome() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		assertEquals("Posicao: 23 Nome: Chico Pereira\nPosicao: 76 Nome: Chico Lima\n", a1.mesmoNome("Chico"));
		assertEquals("Ninguem possui esse nome", a1.primeiroContato("Fernando"));
	}
	
	/**
	 * Testa a funcao pesquisarNivel.
	 */
	@Test
	void testPesquisarNivel() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		assertEquals("Nome: Chico Pereira - 83 9856-2433   Nivel de Amizade: 5(Irmao)\n" +
					 "Nome: Daniel Lacerda - 83 9877-2132   Nivel de Amizade: 5(Irmao)\n", a1.pesquisarNivel(5));
	}
	
	/**
	 * Testa a funcao quantidadeNivel.
	 */
	@Test
	void testQuantidadeNivel() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		a1.cadastrarContato("Fernando", "Tavares", "83 9945 3433", 100, 1);
		assertEquals("A quantidade de pessoas com o nivel 5 e 2", a1.quantidadeNivel(5));
		assertEquals("A quantidade de pessoas com o nivel 4 e 0", a1.quantidadeNivel(4));
		assertEquals("A quantidade de pessoas com o nivel 1 e 2", a1.quantidadeNivel(1));
	}
	
	/**
	 * Testa a funcao mediaAmizade.
	 */
	@Test
	void testMediaAmizade() {
		a1.cadastrarContato("Chico", "Pereira", "83 9856-2433", 23, 5);
		a1.cadastrarContato("Chico", "Lima", "83 8765-2323", 76, 2);
		a1.cadastrarContato("Gabriel", "Medeiros", "83 8786-2323", 4, 1);
		a1.cadastrarContato("Daniel", "Lacerda", "83 9877-2132", 67, 5);
		assertEquals("A media referente as amizades e 3", a1.mediaAmizade());
	}
}
