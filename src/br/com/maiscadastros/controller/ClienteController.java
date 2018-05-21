package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.dto.ClienteDto;
import br.com.maiscadastros.model.Cliente;

public class ClienteController {
	public ClienteDto cadastrarCliente(Cliente pCliente) {
		// Verificar as informa��es
		if (pCliente == null) {
			return new ClienteDto(false, "Tentativa de inclus�o de cliente nulo");
		}

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Verificando se o cliente j� existe
		Cliente tCliente = tDao.recoveryByCpf(pCliente.getCpf());
		if (tCliente != null) {
			return new ClienteDto(false, "J� existe um Cliente com o cpf informado");
		}

		// Incluindo o cliente
		tCliente = tDao.create(pCliente);
		if (tCliente == null) {
			return new ClienteDto(false, "Erro no processo de inclus�o");
		}

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Cliente inclu�do com sucesso", tCliente);
	}

	public ClienteDto recuperarCliente(Cliente cliente) {
		// Verificar as informa��es
		if (cliente.getId() <= 0) {
			return new ClienteDto(false, "Identificador do cliente inv�lido");
		}

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Recuperando o Cliente
		Cliente tCliente = tDao.recovery(cliente);
		if (tCliente == null) {
			return new ClienteDto(false, "N�o existe cliente com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Cliente recuperado com sucesso", tCliente);
	}

	public ClienteDto atualizarCliente(Cliente cliente) {
		// Verificar as informa��es
		if (cliente == null) {
			return new ClienteDto(false, "Tentativa de atualiza��o de cliente nulo");
		}

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Recuperando o Cliente
		Cliente tCliente = tDao.recovery(cliente);
		if (tCliente == null) {
			return new ClienteDto(false, "N�o existe Cliente com o identificador informado");
		}

		if (cliente.getCpf() != tCliente.getCpf()) {
			// Verificando se existe um Cliente com o novo CPF
			tCliente = tDao.recoveryByCpf(cliente.getCpf());
			if (tCliente != null) {
				return new ClienteDto(false, "J� existe Cliente com o cpf informado");
			}
		}

		// Atualizando o Cliente
		tCliente = tDao.update(cliente);
		if (tCliente == null) {
			return new ClienteDto(false, "N�o existe cliente com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Cliente alterado com sucesso", tCliente);
	}

	public ClienteDto removeCliente(Cliente cliente) {
		// Verificar as informa��es
		if (cliente.getId() <= 0) {
			return new ClienteDto(false, "Identificador do cliente inv�lido");
		}

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Incluindo o Cliente

		tDao.delete(cliente);

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Cliente removido com sucesso");

		// Retornando o indicativo de erro
		// return new ClienteDto(false, "Erro no processo de remo��o");
	}

	public ClienteDto pesquisarClientesPorNome(String pNome) {
		// Criando a lista de retorno
		List<Cliente> tLista = new ArrayList<>();

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Recuperando o Cliente
		tLista = tDao.searchByNome(pNome);

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Lista de Clientes recuperada com sucesso", tLista);
	}

	public ClienteDto pesquisarCliente() {
		// Criando a lista de retorno
		List<Cliente> tLista = new ArrayList<>();

		// Criando o objeto de persist�ncia
		ClienteDao tDao = new ClienteDao();

		// Recuperando o Cliente
		tLista = tDao.search();

		// Retornando o indicativo de sucesso
		return new ClienteDto(true, "Lista de Clientes recuperada com sucesso", tLista);
	}
}
