package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.dto.ProdutoDto;
import br.com.maiscadastros.model.Produto;

public class ProdutoController 
{
    public ProdutoDto cadastrarProduto(Produto pProduto)
    {
        // Verificar as informa��es
        if (pProduto == null)
        {
            return new ProdutoDto(false, "Tentativa de inclus�o de Produto nulo");
        }

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Verificando se o produto j� existe
        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
        {
            return new ProdutoDto(false, "J� existe um produto com o nome informado");
        }

        // Incluindo o produto
        tProduto = tDao.create(pProduto);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto inclu�do com sucesso", tProduto);
    }

    public ProdutoDto recuperarProduto(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new ProdutoDto(false, "Identificador do Produto inv�lido");
        }

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        Produto tProduto = tDao.recovery(pId);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "N�o existe Produto com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto recuperado com sucesso", tProduto);
    }

    public ProdutoDto atualizarProduto(Produto pProduto)
    {
        // Verificar as informa��es
        if (pProduto == null)
        {
            return new ProdutoDto(false, "Tentativa de atualiza��o de Produto nulo");
        }

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Verificando se existe um Produto com o novo Nome
        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
        {
            return new ProdutoDto(false, "J� existe Produto com o nome informado");
        }

        // Atualizando o Produto
        tProduto = tDao.update(pProduto);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "N�o existe Produto com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto alterado com sucesso", tProduto);
    }

    public ProdutoDto removeProduto(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new ProdutoDto(false, "Identificador do Produto inv�lido");
        }

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Incluindo o Produto
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new ProdutoDto(true, "Produto removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new ProdutoDto(false, "Erro no processo de remo��o");
    }
    
    public ProdutoDto pesquisarProdutosPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Produto> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Lista de Produtos recuperada com sucesso", tLista);
    }
    
    public ProdutoDto pesquisarProduto()
    {
        // Criando a lista de retorno
        List<Produto> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Lista de Produtos recuperada com sucesso", tLista);
    }
    
}
