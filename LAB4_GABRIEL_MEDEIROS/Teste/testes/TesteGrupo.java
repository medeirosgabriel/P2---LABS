package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Grupo;
import sistema.Sistema;

class TesteGrupo {

	private Sistema sistema;

	@BeforeEach
	void constroiGrupo() {
		sistema = new Sistema();
		sistema.novoGrupo("Programmers");
		sistema.novoGrupo("Students");
	}
	
	@Test
	void alocaAluno() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		sistema.cadastrarAluno("9135543", "Daniel", "Medicina");
		sistema.cadastrarAluno("3545142", "Tadeu", "Direito");
		sistema.alocarAluno("3231342", "Programmers");
		sistema.alocarAluno("9135543", "Programmers");
		sistema.alocarAluno("3545142", "Students");
		assertTrue(sistema.getGrupos().get("Programmers").estaNoGrupo(new Aluno("3231342", "Gabriel", "Computacao")));
		assertTrue(sistema.getGrupos().get("Programmers").estaNoGrupo(new Aluno("9135543", "Daniel", "Medicina")));
		assertFalse(sistema.getGrupos().get("Programmers").estaNoGrupo(new Aluno("3545142", "Tadeu", "Direito")));
		assertTrue(sistema.getGrupos().get("Students").estaNoGrupo(new Aluno("3545142", "Tadeu", "Direito")));
		assertFalse(sistema.getGrupos().get("Students").estaNoGrupo(new Aluno("9135543", "Daniel", "Medicina")));
		assertFalse(sistema.getGrupos().get("Students").estaNoGrupo(new Aluno("3231342", "Gabriel", "Computacao")));
	}
	
	@Test
	void imprimeGrupo() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		sistema.cadastrarAluno("9135543", "Daniel", "Medicina");
		sistema.cadastrarAluno("3545142", "Tadeu", "Direito");
		sistema.alocarAluno("3231342", "Programmers");
		sistema.alocarAluno("9135543", "Programmers");
		sistema.alocarAluno("3545142", "Programmers");
		sistema.alocarAluno("9135543", "Students");
		sistema.alocarAluno("3545142", "Students");
		assertEquals("3231342 - Gabriel - Computacao\n"
				+ "9135543 - Daniel - Medicina\n"
				+ "3545142 - Tadeu - Direito\n", sistema.getGrupos().get("Programmers").imprimirGrupo());
		assertEquals("9135543 - Daniel - Medicina\n"
				+ "3545142 - Tadeu - Direito\n", sistema.getGrupos().get("Students").imprimirGrupo());
	}
}
