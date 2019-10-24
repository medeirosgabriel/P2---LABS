/**
 * Representacao do controller que ira operar sobre a Compra de produtos.
 * Todo controller central possui um Controller de clientes e um controller
 * de fornecedores.
 * 
 * @author Gabriel Medeiros
 */

package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Compra;
import entidades.Produto;

public class ControllerCentral {
	
	private String criterio;

	/**
	 * Objeto do tipo ControllerCliente.
	 */
	private ControllerCliente controllerCliente;
	
	/**
	 * Obejto do tipo ControllerFornecedor.
	 */
	private ControllerFornecedor controllerFornecedor;

	/**
	 * Constroi um objeto do tipo ControllerCentral.
	 * @param contCLi - Objeto do tipo ControllerCliente.
	 * @param contForn - Objeto do tipo ControllerFornecedor.
	 */
	public ControllerCentral(ControllerCliente contCLi, ControllerFornecedor contForn) {
		this.controllerCliente = contCLi;
		this.controllerFornecedor = contForn;
		this.criterio = " ";
	}
	
	/**
	 * Adiciona um nova compra de um cliente na conta de um cliente referente
	 * a um fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param nomeFornecedor - Nome do fornecedor.
	 * @param data - Data da compra.
	 * @param nomeProduto - Nome do produto.
	 * @param descProduto - Descricao do produto.
	 */
	public boolean adicionaCompra(String cpf, String nomeFornecedor,  String data, String nomeProduto, String descProduto) {
		Validacao.validarString(cpf, "Erro ao cadastrar compra: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro ao cadastrar compra: cpf invalido.");
		Validacao.validarString(nomeProduto, "Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		Validacao.validarString(descProduto, "Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
		Validacao.validarString(nomeFornecedor, "Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		Validacao.validarData(data);
		this.controllerFornecedor.procuraFornecedor(nomeFornecedor, "Erro ao cadastrar compra: fornecedor nao existe.");
		Produto produto = this.controllerFornecedor.retornaProduto(nomeFornecedor, nomeProduto, descProduto, 
										"Erro ao cadastrar compra: fornecedor nao existe.", 
										"Erro ao cadastrar compra: produto nao existe.");
		this.controllerCliente.adicionaCompra(cpf, nomeFornecedor, produto, data);
		return true;
	}

	/**
	 * Retorna o debito de um cliente com um fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return O debito do cliente com o fornecedor.
	 */
	public double getDebito(String cpf, String fornecedor) {
		Validacao.validarString(cpf, "Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro ao recuperar debito: cpf invalido." );
		Validacao.validarString(fornecedor, "Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		this.controllerFornecedor.procuraFornecedor(fornecedor, "Erro ao recuperar debito: fornecedor nao existe.");
		return this.controllerCliente.getDebito(cpf, fornecedor);
	}
	
	/**
	 * Exibe as contas do cliente com um determinado fornecedor.
	 * @param cpf - Cpf do cliente.
	 * @param fornecedor - Nome do fornecedor.
	 * @return Uma string que representa as contas do cliente com o fornecedor.
	 */
	public String exibeContas(String cpf, String fornecedor) {
		Validacao.validarString(cpf, "Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro ao exibir conta do cliente: cpf invalido.");
		Validacao.validarString(fornecedor, "Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		this.controllerFornecedor.procuraFornecedor(fornecedor, "Erro ao exibir conta do cliente: fornecedor nao existe." );
		return controllerCliente.exibeContas(cpf, fornecedor);
	}

	/**
	 * Exibe todas as contas do cliente, independente do fornecedor.
	 * @param cpf - Cpf de cliente.
	 * @return Uma string que representa todas as contas de um cliente.
	 */
	public String exibeContasClientes(String cpf) {
		Validacao.validarString(cpf, "Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro ao exibir contas do cliente: cpf invalido.");
		return this.controllerCliente.exibeContasClientes(cpf);
	}
	
	/**
	 * Paga a conta de um cliente
	 * @param cpf - Cpf do cliente
	 * @param fornecedor - Nome do fornecedor
	 * @return Se a operacao de pagar for bem sucedida,
	 * o metodo ira retornar true;
	 */
	public boolean pagarConta(String cpf, String fornecedor) {
		Validacao.validarString(cpf, "Erro no pagamento de conta: cpf nao pode ser vazio ou nulo.");
		Validacao.validarCpf(cpf, "Erro no pagamento de conta: cpf invalido.");
		Validacao.validarString(fornecedor, "Erro no pagamento de conta: fornecedor nao pode ser vazio ou nulo.");
		this.controllerFornecedor.procuraFornecedor(fornecedor, "Erro no pagamento de conta: fornecedor nao existe.");
		return controllerCliente.pagarConta(cpf, fornecedor);
	}
	
	/**
	 * Define o criterio de ordencao.
	 * @param criterio - Criterio de ordenacao
	 * @return Se a operacao for bem sucedida, o metodo ira retornar true.
	 */
	public boolean ordenaPor(String criterio) {
		Validacao.validarString(criterio, "Erro na listagem de compras: criterio nao pode ser vazio ou nulo." );
		if (criterio.equals("Cliente") || criterio.equals("Fornecedor") || criterio.equals("Data")) {
			this.criterio = criterio;
			return true;
		}else {
			throw new IllegalArgumentException("Erro na listagem de compras: criterio nao oferecido pelo sistema.");
		}
	}
	
	/**
	 * Lista as compras de acordo com algum criterio de ordenacao. 
	 * @return Uma string que representa todas as compras ordenadas.
	 */
	public String listarCompras() {
		List<String> dados = new ArrayList<>();
		if (this.criterio.equals("Cliente")) {
			dados = this.controllerCliente.listarCompras("Cliente");
			Collections.sort(dados);
			return dados.stream().collect(Collectors.joining(" | "));
		}else if (this.criterio.equals("Fornecedor")) {
			dados = this.controllerCliente.listarCompras("Fornecedor");
			Collections.sort(dados);
			return dados.stream().collect(Collectors.joining(" | "));
		}else if (this.criterio.equals("Data")) {
			dados = this.controllerCliente.listarCompras("Data");
			return this.ordenaPorData(dados);
		}else {
			throw new IllegalArgumentException("Erro na listagem de compras: criterio ainda nao definido pelo sistema.");
		}
	}
	
	/**
	 * Ordena os dados tendo como criterio de ordenacao a data.
	 * @param dados - Lista com os dados das compras.
	 * @return Uma string com a representacao de cada compra ordenada.
	 */
	public String ordenaPorData(List<String> dados) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Collections.sort(dados, (str1, str2) -> {
			try {
				if ((sdf.parse(str1.split(", ")[0]).compareTo(sdf.parse(str2.split(", ")[0])) == 0)){
					return str1.compareTo(str2);
				}else {
					return sdf.parse(str1.split(", ")[0]).compareTo(sdf.parse(str2.split(", ")[0]));
				}
			} catch (ParseException e) {
				throw new IllegalArgumentException("Data Invalida.");
			}
		});
		return dados.stream().collect(Collectors.joining(" | "));
	}
}