package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.SetorDao;
import br.com.maiscadastros.dto.SetorDto;
import br.com.maiscadastros.model.Setor;

public class SetorController {
	    public SetorDto cadastrarSetor(Setor pSetor)
	    {
	        // Verificar as informações
	        if (pSetor == null)
	        {
	            return new SetorDto(false, "Tentativa de inclusão de Setor nulo");
	        }

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        // Verificando se o setor já existe
	        Setor tSetor = tDao.recoveryByNome(pSetor.getNome());
	        if (tSetor != null)
	        {
	            return new SetorDto(false, "Já existe um setor com o nome informado");
	        }

	        // Incluindo o setor
	        tSetor = tDao.create(pSetor);
	        if (tSetor == null)
	        {
	            return new SetorDto(false, "Erro no processo de inclusão");
	        }

	        // Retornando o indicativo de sucesso
	        return new SetorDto(true, "Setor incluído com sucesso", tSetor);
	    }

	    public SetorDto recuperarSetor(Setor setor)
	    {
	        // Verificar as informações
	        if (setor.getId() <=0)
	        {
	            return new SetorDto(false, "Identificador do Setor inválido");
	        }

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        // Recuperando o Setor
	        Setor tSetor = tDao.recovery(setor);
	        if (tSetor == null)
	        {
	            return new SetorDto(false, "Não existe Setor com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new SetorDto(true, "Setor recuperado com sucesso", tSetor);
	    }

	    public SetorDto atualizarSetor(Setor pSetor)
	    {
	        // Verificar as informações
	        if (pSetor == null)
	        {
	            return new SetorDto(false, "Tentativa de atualização de Setor nulo");
	        }

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        // Verificando se existe um Setor com o novo Nome
	        Setor tSetor = tDao.recoveryByNome(pSetor.getNome());
	        if (tSetor != null)
	        {
	            return new SetorDto(false, "Já existe Setor com o nome informado");
	        }

	        // Atualizando o Setor
	        tSetor = tDao.update(pSetor);
	        if (tSetor == null)
	        {
	            return new SetorDto(false, "Não existe Setor com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new SetorDto(true, "Setor alterado com sucesso", tSetor);
	    }

	    public SetorDto removeSetor(Setor setor)
	    {
	        // Verificar as informações
	        if (setor.getId() <=0)
	        {
	            return new SetorDto(false, "Identificador do Setor inválido");
	        }

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        tDao.delete(setor);
	        return new SetorDto(true, "Setor removido com sucesso");
	    }
	    
	    public SetorDto pesquisarSetorsPorNome(String pNome)
	    {
	        // Criando a lista de retorno
	        List<Setor> tLista = new ArrayList<>();

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        // Recuperando o Setor
	        tLista = tDao.searchByNome(pNome);

	        // Retornando o indicativo de sucesso
	        return new SetorDto(true, "Lista de Setors recuperada com sucesso", tLista);
	    }
	    
	    public SetorDto pesquisarSetor()
	    {
	        // Criando a lista de retorno
	        List<Setor> tLista = new ArrayList<>();

	        // Criando o objeto de persistência
	        SetorDao tDao = new SetorDao();

	        // Recuperando o Setor
	        tLista = tDao.search();

	        // Retornando o indicativo de sucesso
	        return new SetorDto(true, "Lista de Setores recuperada com sucesso", tLista);
	    }
}
