package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.maiscadastros.dao.MarcaDao;
import br.com.maiscadastros.dto.MarcaDto;
import br.com.maiscadastros.model.Marca;

public class MarcaController {
	    public MarcaDto cadastrarMarca(Marca pMarca)
	    {
	        // Verificar as informações
	        if (pMarca == null)
	        {
	            return new MarcaDto(false, "Tentativa de inclusão de Marca nulo");
	        }

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Verificando se o marca já existe
	        Marca tMarca = tDao.recoveryByNome(pMarca.getNome());
	        if (tMarca != null)
	        {
	            return new MarcaDto(false, "Já existe um marca com o nome informado");
	        }

	        // Incluindo o marca
	        tMarca = tDao.create(pMarca);
	        if (tMarca == null)
	        {
	            return new MarcaDto(false, "Erro no processo de inclusão");
	        }

	        // Retornando o indicativo de sucesso
	        return new MarcaDto(true, "Marca incluído com sucesso", tMarca);
	    }

	    public MarcaDto recuperarMarca(int pId)
	    {
	        // Verificar as informações
	        if (pId <=0)
	        {
	            return new MarcaDto(false, "Identificador do Marca inválido");
	        }

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Recuperando o Marca
	        Marca tMarca = tDao.recovery(pId);
	        if (tMarca == null)
	        {
	            return new MarcaDto(false, "Não existe Marca com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new MarcaDto(true, "Marca recuperado com sucesso", tMarca);
	    }

	    public MarcaDto atualizarMarca(Marca pMarca)
	    {
	        // Verificar as informações
	        if (pMarca == null)
	        {
	            return new MarcaDto(false, "Tentativa de atualização de Marca nulo");
	        }

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Verificando se existe um Marca com o novo Nome
	        Marca tMarca = tDao.recoveryByNome(pMarca.getNome());
	        if (tMarca != null)
	        {
	            return new MarcaDto(false, "Já existe Marca com o nome informado");
	        }

	        // Atualizando o Marca
	        tMarca = tDao.update(pMarca);
	        if (tMarca == null)
	        {
	            return new MarcaDto(false, "Não existe Marca com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new MarcaDto(true, "Marca alterado com sucesso", tMarca);
	    }

	    public MarcaDto removeMarca(int pId)
	    {
	        // Verificar as informações
	        if (pId <=0)
	        {
	            return new MarcaDto(false, "Identificador do Marca inválido");
	        }

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Incluindo o Marca
	        if (tDao.delete(pId))
	        {
	            // Retornando o indicativo de sucesso
	            return new MarcaDto(true, "Marca removido com sucesso");
	        }

	        // Retornando o indicativo de erro
	        return new MarcaDto(false, "Erro no processo de remoção");
	    }
	    
	    public MarcaDto pesquisarMarcasPorNome(String pNome)
	    {
	        // Criando a lista de retorno
	        List<Marca> tLista = new ArrayList<>();

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Recuperando o Marca
	        tLista = tDao.searchByNome(pNome);

	        // Retornando o indicativo de sucesso
	        return new MarcaDto(true, "Lista de Marcas recuperada com sucesso", tLista);
	    }
	    
	    public MarcaDto pesquisarMarca()
	    {
	        // Criando a lista de retorno
	        List<Marca> tLista = new ArrayList<>();

	        // Criando o objeto de persistência
	        MarcaDao tDao = new MarcaDao();

	        // Recuperando o Marca
	        tLista = tDao.search();

	        // Retornando o indicativo de sucesso
	        return new MarcaDto(true, "Lista de Marcas recuperada com sucesso", tLista);
	    }
}
