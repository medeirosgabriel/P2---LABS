/**
 * Representacao de um Cliente.
 * Um cliente e identificado pelo cpf.
 * Todo cliente possui cpf, nome, email, local de trabalho e 
 * um HashMap que armazena as contas.
 * 
 *@author Gabriel Medeiros
 */
package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import controllers.Validacao;

public class Cliente implements Comparable<Cliente>{
	
	/**
	 * Cpf do cliente.
	 */
	private String cpf;
	
	/**
	 * Nome do cliente
	 */
	private String nome;
	
	/**
	 * Email do cliente.
	 */
	private String email;
	
	/**
	 * Local de trabalho do cliente.
	 */
	private String localTrabalho;
	
	/**
	 * HashMap que armazena as contas de um cliente.
	 * Chave - Nome do fornecedor.
	 * Valor - Objeto do tipo Cliente.
	 */
	private HashMap<String, Conta> contas;

	
	/**
	 * Controi um novo cliente com base no cpf, no nome, no email
	 * e no local de trabalho do cliente.
	 * @param cpf - Cpf do cliente.
	 * @param nome - Nome do cliente.
	 * @param email - Email do cliente.
	 * @param localTrabalho - Local de trabalho do cliente
	 */
	public Cliente(String cpf, String nome, String email, String localTrabalho) {
		Validacao.validarString(cpf, "Erro na criacao do cliente: cpf invalido.");
		Validacao.validarString(email, "Erro na criacao do cliente: email nao pode ser vazio ou nulo.");
		Validacao.validarString(nome,"Erro na criacao do cliente: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(localTrabalho, "Erro na criacao do cliente: localizacao nao pode ser vazio ou nulo.");
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localTrabalho = localTrabalho;
		this.contas = new HashMap<>();
	}

	/**
	 * Retorna o cpf do cliente.
	 * @return O cpf do cliente.
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Retorna o nome do cliente.
	 * @return O nome do cliente.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o email do cliente.
	 * @return O email do cliente.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Retorna o local de trabalho do cliente.
	 * @return O local de trabalho do cliente.
	 */
	public String getLocalTrabalho() {
		return localTrabalho;
	}
	
	/**
	 * Altera o valor do nome do cliente.
	 * @param nome - Novo nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Altera o valor do email do cliente.
	 * @param email - Novo email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Altera o valor do localTrabalho do cliente.
	 * @param localTrabalho - Novo local de trabalho do cliente.
	 */
	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}
	
	/**
	 * Gera a representacao em forma de String 
	 * para um Cliente.
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.localTrabalho + " - " + this.email;
	}

	/**
	 * Gera o HashCode do Objeto.
	 * O objeto pode ser representado por um numero inteiro.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	/**
	 * Verifica se dois objetos sao iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	/**
	 * Utilizado para comparar dois objetos do tipo Cliente.
	 * O atributo levado em consideracao e o nome.
	 */
	@Override
	public int compareTo(Cliente c1) {
		return this.nome.compareTo(c1.getNome());
	}
	
	/**
	 * Adiciona uma compra na conta de um cliente.
	 * @param fornecedor - Nome do fornecedor
	 * @param data - Data da compra.
	 * @param produto - Produto que vai ser comprado.
	 * @return Se a compra for bem sucedida, o metodo retorna true.
	 */
	public boolean adicionaCompra(String nomeCliente, String fornecedor,String data, Produto produto) {
		Compra compra = new Compra(nomeCliente, fornecedor, data, produto);
		if (this.contas.containsKey(fornecedor)) {
			contas.get(fornecedor).adicionaCompra(compra);
		}else {
			contas.put(fornecedor, new Conta(fornecedor));
			contas.get(fornecedor).adicionaCompra(compra);
		}
		return true;
	}
	
	/**
	 * Retorna o debito do cliente com um determinado fornecedor.
	 * @param fornecedor - Nome do fornecedor.
	 * @return O debito do cliente com o fornecedor.
	 */
	public double getDebito(String fornecedor) {
		if (this.contas.containsKey(fornecedor)) {
			return this.contas.get(fornecedor).getPreco();
		}else {
			throw new IllegalArgumentException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
	}

	/**
	 * Exibe as contas de um cliente com um determinado fornecedor.
	 * @param fornecedor
	 * @return Uma string representando todas as contas de um cliente
	 * com um determinado fornecedor.
	 */
	public String exibeContas(String fornecedor) {
		if (this.contas.containsKey(fornecedor)) {
			return "Cliente: " + this.nome + " | " + this.contas.get(fornecedor).exibeContas();
		}else {
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
	}
	
	/**
	 * Exibe todas as contas de um cliente.
	 * @return Uma string representando todas as compras de
	 * um cliente. 
	 */
	public String exibeContasClientes() {
		String listaContas = "Cliente: " + this.nome + " | ";
		if (!this.contas.isEmpty()) {
			List<String> fornecedoresOrdenados = new ArrayList<>(this.contas.keySet());
			Collections.sort(fornecedoresOrdenados);
			for (String f : fornecedoresOrdenados) {
				listaContas += contas.get(f).exibeContas() + " | ";
			}
			return auxiliaExibeContas(listaContas);
		}else {
			throw new IllegalArgumentException("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.");
		}
	}
	
	/**
	 * Formata a string que representa as compras de um cliente.
	 * @param contas - String que representa as compras de um cliente.
	 * @return A string formatada.
	 */
	public String auxiliaExibeContas(String contas) {
		return contas.substring(0, contas.length() - 3);
	}
	
	/**
	 * Paga a conta dde um cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return Se a operacao de pagar for bem sucedida, o metodo
	 * ira retornar true;
	 */
	public boolean pagarConta(String fornecedor) {
		if (this.contas.containsKey(fornecedor)) {
			this.contas.remove(fornecedor);
			return true;
		}else {
			
			throw new IllegalArgumentException("Erro no pagamento de conta: nao ha debito do cliente associado a este fornecedor.");
		}
	}

	/**
	 * Recolhe as compras de um cliente.
	 * @return Uma lista com objetos do tipo Compra.
	 */
	public List<Compra> dadosCompras() {
		List<Compra> compras = new ArrayList<>();
		for (Conta c : this.contas.values()) {
			compras.addAll(c.dadosCompras());
		}
		return compras;
	}
}
	
