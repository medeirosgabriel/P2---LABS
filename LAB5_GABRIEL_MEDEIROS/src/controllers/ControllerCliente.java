/**
 * Representacao do controller que ira operar sobre o cliente.
 * Todo controller de cliente possui um HashMap de clientes.
 * 
 * @author Gabriel Medeiros
 */
package controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import entidades.Cliente;
import entidades.Compra;
import entidades.Fornecedor;
import entidades.Produto;

public class ControllerCliente {
	
	/**
	 * HashMap que armazena os Clientes do sistema.
	 * Chave - Cpf do cliente.
	 * Valor - Objeto do tipo Cliente.
	 */
	private HashMap<String, Cliente> clientes;
	
	/**
	 * Construtor da classe ControllerCliente.
	 * Inicializa o HashMap de clientes e
	 * o objeto do tipo Validacao.
	 */
	public ControllerCliente() {
		this.clientes = new HashMap<>();
	}
	
	/**
	 * Cadastra um cliente no sistema.
	 * O cliente e alocado no HashMap clientes.
	 * @param cpf - Cpf do cliente.
	 * @param nome - Nome do cliente.
	 * @param email - Email do cliente.
	 * @param localizacao - Localizacao do cliente.
	 * @return Se o cadastro for feito com sucesso, o metodo
	 * ira retornar o cpf do cliente cadastrado.
	 */
	public String cadastrarCliente(String cpf, String nome, String email, String localizacao) {
		Validacao.validarString(cpf, "Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro no cadastro do cliente: cpf invalido.");
		Validacao.validarString(nome, "Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		Validacao.validarString(email, "Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		Validacao.validarString(localizacao, "Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		if (clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
		}
		clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));
		return cpf;
	}
	
	/**
	 * Exibe a representacao de um cliente.
	 * @param cpf - cpf do cliente
	 * @return A representacao do cliente.
	 */
	public String consultarCliente(String cpf) {
		Validacao.validarString(cpf, "Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro na exibicao do cliente: cpf invalido.");
		if (!clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cliente nao existe.");
		}
		return clientes.get(cpf).toString();
	}
	
	/**
	 * Exibe os clientes cadastrados em ordem alfabetica.
	 * @return Uma string com as representacoes de
	 * todos os clientes cadastrados;
	 */
	public String imprimirClientes() {
		String listaOrdenada = "";
		List<Cliente> listaClientes = new ArrayList<>(this.clientes.values());
		Collections.sort(listaClientes);
		for (Cliente c : listaClientes ) {
			listaOrdenada += c.toString() + " | ";
		}
		return auxiliaImpressaoClientes(listaOrdenada);
	}
	
	/**
	 * Auxilia na impressao dos dados dos clientes
	 * cadastrados.
	 * Formata a string que possui os dados dos
	 * clientes cadastrados.
	 * @param clientes - String com os dados dos clientes.
	 * @return A string formatada.
	 */
	private String auxiliaImpressaoClientes(String clientes) {
		if (clientes.equals("")) {
			return "";
		}else {
			return clientes.substring(0, clientes.length() - 3);
		}
	}
	
	/**
	 * Edita os atributos de um cliente, exceto o cpf.
	 * @param cpf - Cpf do cliente.
	 * @param opcao - Atributo que deve ser alterado.
	 * @param novoValor - Novo valor que vai ser atribuido ao
	 * atributo escolhido.
	 * @return Se a edicao for bem sucedida, o metodo
	 * ira retornar true.
	 */
	public boolean editarCliente(String cpf, String opcao, String novoValor) {
		Validacao.validarString(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarString(opcao, "Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		Validacao.validarString(novoValor, "Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro na edicao do cliente: cpf invalido.");
		if (!clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cliente nao existe.");
		}
		if (opcao.equals("cpf")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser editado.");
		}else if (opcao.equals("nome")) {
			return editarNomeCliente(cpf, novoValor);
		}else if(opcao.equals("email")) {
			return editarEmailCliente(cpf, novoValor);
		}else if(opcao.equals("localizacao")) {
			return editarLocalCliente(cpf, novoValor);
		}else {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
		}
	}
	
	/**
	 * Edita o nome de um cliente
	 * @param cpf - Cpf do cliente
	 * @param nome - Novo nome que vai ser atribuido.
	 * @return Se a edicao for bem sucedida, ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean editarNomeCliente(String cpf, String nome) {
		Validacao.validarString(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarString(nome, "Erro na edicao do cliente: nome nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro na edicao do cliente: cpf invalido.");
		if (clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setNome(nome);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Edita a localizacao de um cliente.
	 * @param cpf - Cpf do cliente.
	 * @param local - Nova localizacao que vai ser atribuida.
	 * @return Se a edicao for bem sucedida, ira retornar true, caso contrario,
	 * ira retornar false.
	 */
	public boolean editarLocalCliente(String cpf, String local) {
		Validacao.validarString(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarString(local, "Erro na edicao do cliente: localizacao nao pode ser vazia ou nula.");
		Validacao.validarCpf(cpf, "Erro na edicao do cliente: cpf invalido.");
		if (clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setLocalTrabalho(local);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Edita a email de um cliente.
	 * @param cpf - Cpf do cliente.
	 * @param email - Novo email que vai ser atribuido.
	 * @return Se a edicao for bem sucedida, ira retornar true, caso contrario,
	 * ira retornar false. 
	 */
	public boolean editarEmailCliente(String cpf, String email) {
		Validacao.validarString(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarString(email, "Erro na edicao do cliente: email nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro na edicao do cliente: cpf invalido.");
		if (clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setEmail(email);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Remove um cliente do sistema
	 * @param cpf - Cpf do cliente que vai ser retirado do sistema.
	 * @return Se a remocao for bem sucedida, ira retornar true, caso contrario,
	 * ira retornar false. 
	 */
	public boolean removerCliente(String cpf) {
		Validacao.validarString(cpf, "Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		if (clientes.containsKey(cpf)) {
			clientes.remove(cpf);
			return true;
		}else {
			throw new IllegalArgumentException("Erro na remocao do cliente: cliente nao existe.");
		}
	}

	/**
	 * Ira retornar o o HashMap que armazena os clientes do
	 * sistema.
	 * @return O HashMap que armazena os clientes.
	 */
	public HashMap<String, Cliente> getClientes() {
		return clientes;
	}
	
	/**
	 * Procura um cliente.
	 * @param cpf - Cpf do cliente.
	 * @param mensagem - Mensagem utilizada caso ocorra uma excecao.
	 * @return Caso o cliente exista, o metodo retorna true.
	 */
	public boolean procuraCliente(String cpf, String mensagem) {
		if (this.clientes.containsKey(cpf)) {
			return true;
		}else {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Adiciona um nova compra na conta de um cliente 
	 * referente a um fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @param produto - produto que vai ser adicionado na conta do cliente.
	 * @param data - data da compra do produto.
	 * @return Se a compra for bem sucedida, o metodo ira retornar true.
	 */
	public boolean adicionaCompra(String cpf, String fornecedor, Produto produto, String data) {
		this.procuraCliente(cpf, "Erro ao cadastrar compra: cliente nao existe.");
		this.clientes.get(cpf).adicionaCompra(this.clientes.get(cpf).getNome(), fornecedor, data, produto);
		return true;
	}
	
	/**
	 * Retorna o debito de um cliente com um determinado fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return O debito do cliente com o fornecedor.
	 */
	public double getDebito(String cpf, String fornecedor) {
		this.procuraCliente(cpf, "Erro ao recuperar debito: cliente nao existe.");
		return this.clientes.get(cpf).getDebito(fornecedor);
	}
	
	/**
	 * Exibe as contas de um cliente com um determinado fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return Uma string que representa todas as compras de um cliente
	 * com um fornecedor.
	 */
	public String exibeContas(String cpf, String fornecedor) {
		this.procuraCliente(cpf, "Erro ao exibir conta do cliente: cliente nao existe.");
		return this.clientes.get(cpf).exibeContas(fornecedor);
	}
	
	/**
	 * Exibe todas as contas de um cliente independente do fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @return Uma string que representa todas as contas de um cliente.
	 */
	public String exibeContasClientes(String cpf) {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).exibeContasClientes();
		}else {
			throw new IllegalArgumentException("Erro ao exibir contas do cliente: cliente nao existe.");
		}
	}
	
	/**
	 * Paga a conta de um cliente.
	 * @param cpf - Cpf do cliente
	 * @param fornecedor - Nome do fornecedor
	 * @return Se a operacao de pagar for bem sucedida, o metodo
	 * ira retornar true;
	 */
	public boolean pagarConta(String cpf, String fornecedor) {
		this.procuraCliente(cpf, "Erro no pagamento de conta: cliente nao existe.");
		return this.clientes.get(cpf).pagarConta(fornecedor);
	}

	/**
	 * Retorna uma lista com as representacoes em string de todas as contas.
	 * @param criterio - Criterio de ordenacao.
	 * @return Uma lista com as representacoes de todas as contas.
	 */
	public List<String> listarCompras(String criterio) {
		List<Compra> compras = new ArrayList<>();
		for (Cliente c : this.clientes.values()) {
			compras.addAll(c.dadosCompras());
		}
		List<String> comprasOrdenadas = new ArrayList<>();
		if (criterio.equals("Cliente")) {
			compras.forEach(c -> comprasOrdenadas.add(c.representacaoOrdenaPorCliente()));
		}else if (criterio.equals("Fornecedor")) {
			compras.forEach(c -> comprasOrdenadas.add(c.representacaoOrdenaPorFornecedor()));
		}else if (criterio.equals("Data")) {
			compras.forEach(c -> comprasOrdenadas.add(c.representacaoOrdenaPorData()));
		}
		return comprasOrdenadas;
	}
}
