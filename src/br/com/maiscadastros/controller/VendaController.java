package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.VendaDao;
import br.com.maiscadastros.dto.VendaDto;
import br.com.maiscadastros.model.Venda;

public class VendaController {
	public VendaDto cadastrarVenda(Venda pVenda) {
		// Verificar as informações
		if (pVenda == null) {
			return new VendaDto(false, "Tentativa de inclusão de Venda nulo");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Verificando se o Venda já existe
		Venda tVenda = tDao.recoveryById(pVenda.getId());
		if (tVenda != null) {
			return new VendaDto(false, "Já existe uma Venda com o id informado");
		}

		// Incluindo o Venda
		tVenda = tDao.create(pVenda);
		if (tVenda == null) {
			return new VendaDto(false, "Erro no processo de inclusão");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda incluída com sucesso", tVenda);
	}

	public VendaDto recuperarVenda(Venda venda) {
		// Verificar as informações
		if (venda.getId() <= 0) {
			return new VendaDto(false, "Identificador do Venda inválido");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		Venda tVenda = tDao.recovery(venda);
		if (tVenda == null) {
			return new VendaDto(false, "Não existe Venda com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda recuperado com sucesso", tVenda);
	}

	public VendaDto atualizarVenda(Venda venda) {
		// Verificar as informações
		if (venda == null) {
			return new VendaDto(false, "Tentativa de atualização de Venda nulo");
		}

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		Venda tVenda = tDao.recovery(venda);
		if (tVenda == null)
        {
            return new VendaDto(false, "Não existe Loja com o identificador informado");
        }

		if (venda.getId() != tVenda.getId()) {
			// Verificando se existe um Venda com o novo CPF
			Venda tVenda2 = tDao.recoveryById(tVenda.getId());
			if (tVenda2 != null) {
				return new VendaDto(false, "Já existe Venda com o id informado");
			}
		}

		// Atualizando o Venda
		tVenda = tDao.update(venda);
		if (tVenda == null) {
			return new VendaDto(false, "Não existe Venda com o identificador informado");
		}

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Venda alterado com sucesso", tVenda);
	}

	public VendaDto removeVenda(Venda venda)
    {
        // Verificar as informações
        if (venda.getId() <=0)
        {
            return new VendaDto(false, "Identificador da Venda inválido");
        }

        // Criando o objeto de persistência
        VendaDao tDao = new VendaDao();

        tDao.delete(venda);
        return new VendaDto(true, "Venda removida com sucesso");
    }

	public VendaDto pesquisarVendasPorDescricao(String pDescricao) {
		// Criando a lista de retorno
		List<Venda> tLista = new ArrayList<>();

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		tLista = tDao.searchByDescricao(pDescricao);

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Lista de Vendas recuperada com sucesso", tLista);
	}

	public VendaDto pesquisar() {
		// Criando a lista de retorno
		List<Venda> tLista = new ArrayList<>();

		// Criando o objeto de persistência
		VendaDao tDao = new VendaDao();

		// Recuperando o Venda
		tLista = tDao.search();

		// Retornando o indicativo de sucesso
		return new VendaDto(true, "Lista de Vendas recuperada com sucesso", tLista);
	}
}
