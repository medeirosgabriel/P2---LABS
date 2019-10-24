
package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Grupo;
import sistema.Sistema;

class TesteAluno {
	
	private Aluno aluno1;
	
	private Aluno aluno2;
	
	private Aluno aluno3;

	@BeforeEach
	void constroiAluno() {
		aluno1 = new Aluno("3231342", "Gabriel", "Computacao");
		aluno2 = new Aluno("9135543", "Daniel", "Medicina");
		aluno3 = new Aluno("3545142", "Tadeu", "Direito");
	}

	@Test
	void testeToString() {
		assertEquals("3231342 - Gabriel - Computacao", aluno1.toString());
		assertEquals("9135543 - Daniel - Medicina", aluno2.toString());
		assertEquals("3545142 - Tadeu - Direito", aluno3.toString());
	}

	@Test
	void testeEquals() {
		assertEquals(aluno1, new Aluno("3231342", "Gabriel", "Computacao"));
		assertEquals(aluno2,  new Aluno("9135543", "Daniel", "Psicologia"));
		assertEquals(aluno3,  new Aluno("3545142", "Fernando", "Direito"));
		assertFalse(aluno3.equals(new Aluno("3231342", "Gabriel", "Computacao")));
	}
}