/**
 * Main - reponsavel pela entrada e saida do programa.
 */
package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entidades.Aluno;
import entidades.Grupo;

public class Program {
	
	/**
	 * Imprime o menu do Sistema.
	 */
	public static void menu() {
		System.out.println("================== MENU =================");
		System.out.println("(C)adastrar Aluno");
		System.out.println("(E)xibir Aluno");
		System.out.println("(N)ovo Grupo");
		System.out.println("(A)locar Aluno no Grupo e Imprimir Grupos");
		System.out.println("(R)egistrar Aluno que Respondeu");
		System.out.println("(I)mprimir Alunos que Responderam");
		System.out.println("(O)ra, vamos fechar o programa!");
		System.out.print("Opcao > ");
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Sistema sistema = new Sistema();
		Excecao excecao = new Excecao();
		
		String opcao = "";
		
		while (!opcao.equals("O")) {
		

			menu();
			opcao = input.nextLine();
			excecao.validarEntrada(opcao);
			
			if (opcao.equals("C")) {
				
				System.out.print("Matricula: ");
				String matricula = input.nextLine();
				System.out.print("Nome: ");
				String nome = input.nextLine();
				System.out.print("Curso: ");
				String curso = input.nextLine();
				System.out.println(sistema.cadastrarAluno(matricula, nome, curso));
				System.out.println();
				
			} else if (opcao.equals("E")) {
				
				System.out.print("Matricula: ");
				String matricula = input.nextLine();;
				System.out.println(sistema.consultarAluno(matricula));
				System.out.println();
				
			} else if (opcao.equals("N")) {
				
				System.out.print("Grupo: ");
				String grupo = input.nextLine();
				System.out.println(sistema.novoGrupo(grupo));
				System.out.println();
				
			} else if (opcao.equals("A")) {
				
				System.out.print("(A)locar Aluno ou (I)mprimir Grupo? ");
				String acao = "";
				acao = input.nextLine();
				
				if (acao.equals("A")) {
					
					System.out.print("Matricula: ");
					String matricula = input.nextLine();
					
					System.out.print("Grupo: ");
					String grupo = input.nextLine();
					
					System.out.println(sistema.alocarAluno(matricula, grupo));
					System.out.println();
					
				}else if(acao.equals("I")) {
					
					System.out.print("Grupo: ");
					String grupo = input.nextLine();
					System.out.println(sistema.imprimirGrupo(grupo));
					
				}else {
					
					System.out.println("Entrada Invalida");
					System.out.println();
					
				}
				
			} else if (opcao.equals("R")) {
				
				System.out.print("Matricula: ");
				String matricula = input.nextLine();
				System.out.println(sistema.registrarAlunoQuestao(matricula));
				System.out.println();

			} else if (opcao.equals("I")) {
				
				System.out.println(sistema.imprimirALunosQuestoes());
				
			} else if (opcao.equals("O")) {

			}else {
				
				System.out.println("Entrada Invalida");
				System.out.println();
			}
		}
	}
}
