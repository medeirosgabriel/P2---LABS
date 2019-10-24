
package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Grupo;
import sistema.Sistema;

class TesteSistema {
	
	private Sistema sistema;
	
	@BeforeEach
	void constroiSistema() {
		sistema = new Sistema(); 
	}
	
	@Test
	void cadastrarAluno() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		assertTrue(sistema.getAlunos().containsKey("3231342"));
		assertEquals("MATRICULA JA CADASTRADA!", sistema.cadastrarAluno("3231342", "Gabriel", "Computacao"));
	}
	
	@Test
	void consultarAluno() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		assertEquals( "Aluno: 3231342 - Gabriel - Computacao", sistema.consultarAluno("3231342"));
		assertEquals( "ALUNO NAO CADASTRADO", sistema.consultarAluno("7657564"));
		
	}
	
	@Test
	void novoGrupo() {
		sistema.novoGrupo("Programmers");
		sistema.getGrupos().containsKey("Programmers");
		assertEquals("GRUPO JA CADASTRADO", sistema.novoGrupo("Programmers"));
	}
	
	@Test
	void alocarAluno() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		assertEquals("GRUPO NAO CADASTRADO", sistema.alocarAluno("3231342","Programmers"));
		assertEquals("ALUNO NAO CADASTRADO", sistema.alocarAluno("4342423","Programmers"));
		sistema.novoGrupo("Programmers");
		assertEquals("ALUNO ALOCADO",sistema.alocarAluno("3231342","Programmers"));
	}
	
	@Test
	void imprimeGrupo() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		sistema.cadastrarAluno("9135543", "Daniel", "Medicina");
		sistema.cadastrarAluno("3545142", "Tadeu", "Direito");
		assertEquals("GRUPO NAO CADASTRADO", sistema.imprimirGrupo("Programmers"));
		sistema.novoGrupo("Programmers");
		sistema.alocarAluno("3231342", "Programmers");
		sistema.alocarAluno("9135543", "Programmers");
		sistema.alocarAluno("3545142", "Programmers");
		assertEquals("Alunos do grupo Programmers:\n" 
				+ "3231342 - Gabriel - Computacao\n"
				+ "9135543 - Daniel - Medicina\n"
				+ "3545142 - Tadeu - Direito\n", sistema.imprimirGrupo("Programmers"));
	}
	
	@Test
	void registroAlunoQuestao() {
		assertEquals("ALUNO NAO CADASTRADO", sistema.registrarAlunoQuestao("3231342"));
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		sistema.cadastrarAluno("9135543", "Daniel", "Medicina");
		sistema.cadastrarAluno("3545142", "Tadeu", "Direito");
		sistema.registrarAlunoQuestao("3231342");
		sistema.registrarAlunoQuestao("9135543");
		assertEquals("ALUNO NAO CADASTRADO", sistema.registrarAlunoQuestao("3243243"));
		assertTrue(sistema.getAlunosQuestoes().contains(new Aluno("3231342", "Gabriel", "Computacao")));
		assertTrue(sistema.getAlunosQuestoes().contains(new Aluno("9135543", "Daniel", "Medicina")));
		assertFalse(sistema.getAlunosQuestoes().contains(new Aluno("3545142", "Tadeu", "Direito")));
		
	}
	
	@Test
	void imprimeAlunoQuestoes() {
		sistema.cadastrarAluno("3231342", "Gabriel", "Computacao");
		sistema.cadastrarAluno("9135543", "Daniel", "Medicina");
		sistema.cadastrarAluno("3545142", "Tadeu", "Direito");
		sistema.registrarAlunoQuestao("3231342");
		sistema.registrarAlunoQuestao("9135543");
		assertEquals("Alunos:\n"
				+ "1. 3231342 - Gabriel - Computacao\n"
				+ "2. 9135543 - Daniel - Medicina\n", sistema.imprimirALunosQuestoes());
	}

}
