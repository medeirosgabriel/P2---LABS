package agenda_contatos;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Agenda agenda = new Agenda();

		String opcao = " ";

		Scanner input = new Scanner(System.in);
		
		while(!opcao.equals("S")) {
			
			menu();
			
			opcao = input.nextLine();
			
			if (opcao.equals("C")){
				
				System.out.println();
				System.out.println("====== CADASTRANDO CONTATOS =====");
				
				System.out.print("Posicao: ");
				int posicao = input.nextInt();
				input.nextLine();
				
				if (posicao <= 0 || posicao > 100) {
					
					System.out.println("Posicao invalida");
					System.out.println();
					
				}else {
					
					System.out.print("Nome: ");
					String nome = input.nextLine();
					
					System.out.print("Sobrenome: ");
					String sobrenome = input.nextLine();
					
					System.out.print("Telefone: ");
					String telefone = input.nextLine();
					
					System.out.print("Nivel de amizade: ");
					int nivel = input.nextInt();
					input.nextLine();
					
					agenda.cadastrarContato(nome, sobrenome, telefone, posicao, nivel);
					System.out.println();
					
				}
				
			}else if (opcao.equals("L")) {
				
				System.out.println();
				System.out.println("===== LISTANDO CONTATOS =====");
				System.out.println(agenda.listarContatos());
				System.out.println();
				
			}else if (opcao.equals("E")){
				
				System.out.println();
				System.out.println("====== EXIBINDO CONTATOS =====");
				System.out.print("Contato(posicao): ");
				int posicao = input.nextInt();
				input.nextLine();
				
				System.out.println(agenda.exibirContatos(posicao));
				System.out.println();
				
			}else if (opcao.equals("CO")) {
				
				System.out.println();
				System.out.println("===== CONTATOS COM DETERMINADO NOME =====");
				System.out.print("Nome: ");
				String nome = input.nextLine();
				
				System.out.println(agenda.mesmoNome(nome));;
				System.out.println();
				
			}else if (opcao.equals("P")) {
				
				System.out.println();
				System.out.println("===== PRIMEIRO CONTATO COM DETERMINADO NOME =====");
				System.out.print("Nome: ");
				String nome = input.nextLine();
				
				System.out.println(agenda.primeiroContato(nome));
				System.out.println();
				
			}else if (opcao.equals("PE")) {
				
				System.out.println();
				System.out.println("===== PESSOAS COM DETERMINADO NIVEL DE AMIZADE =====");
				System.out.print("Nivel(1 - 5): ");
				int nivel = input.nextInt();
				input.nextLine();
				
				System.out.println(agenda.pesquisarNivel(nivel));
				System.out.println();
				
			}else if (opcao.equals("Q")) {
				
				System.out.println();
				System.out.println("===== QUANTIDADE DE PESSOAS COM DETERMINADO NIVEL DE AMIZADE =====");
				System.out.print("Nivel(1 - 5): ");
				int nivel = input.nextInt();
				input.nextLine();
				
				System.out.println(agenda.quantidadeNivel(nivel));
				System.out.println();
				
			}else if (opcao.equals("M")) {
				
				System.out.println();
				System.out.println("===== MEDIA DO NIVEL DE AMIZADE =====");
				
				System.out.println(agenda.mediaAmizade());
				System.out.println();
				
			}else {
				System.out.println();
				System.out.println("Opcao invalida!!");
				System.out.println();
			}
		}
	}
   
	/**
	 * Imprime o menu do programa. 
	 */
	private static void menu() {
		
		System.out.println("===== MENU =====");
		System.out.println("(C)adastrar contato");
		System.out.println("(L)istar contatos");
		System.out.println("(E)xibir contatos");
		System.out.println("(CO)ntatos com determinado nome");
		System.out.println("(P)rimeiro contato com determinado nome");
		System.out.println("(PE)squisar nivel");
		System.out.println("(Q)uantidade de contatos em um determinado nivel de amizade");
		System.out.println("(M)edia do nivel de amizade");
		System.out.println("(S)air");
		System.out.print("Opcao: ");
		
	}
}
