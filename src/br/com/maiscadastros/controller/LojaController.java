package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.model.Loja;

public class LojaController 
{
    public LojaDto cadastrarLoja(Loja pLoja)
    {
        // Verificar as informa��es
        if (pLoja == null)
        {
            return new LojaDto(false, "Tentativa de inclus�o de Loja nulo");
        }

        // Criando o objeto de persist�ncia
        LojaDao tDao = new LojaDao();

        // Verificando se a loja j� existe
        Loja tLoja = tDao.recoveryByCnpj(pLoja.getCnpj());
        if (tLoja != null)
        {
            return new LojaDto(false, "J� existe Loja com o cnpj informado");
        }

        // Incluindo a loja
        tLoja = tDao.create(pLoja);
        if (tLoja == null)
        {
            return new LojaDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja inclu�da com sucesso", tLoja);
    }

    public LojaDto recuperarLoja(Loja loja)
    {
        // Verificar as informa��es
        if (loja.getId() <=0)
        {
            return new LojaDto(false, "Identificador do Loja inv�lido");
        }

        // Criando o objeto de persist�ncia
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        Loja tLoja = tDao.recovery(loja);
        if (tLoja == null)
        {
            return new LojaDto(false, "N�o existe Loja com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja recuperado com sucesso", tLoja);
    }

    public LojaDto atualizarLoja(Loja loja)
    {
        // Verificar as informa��es
        if (loja == null)
        {
            return new LojaDto(false, "Tentativa de atualiza��o de Loja nulo");
        }

        // Criando o objeto de persist�ncia
        LojaDao tDao = new LojaDao();

        // Recuperando a Loja
        Loja tLoja = tDao.recovery(loja);
        if (tLoja == null)
        {
            return new LojaDto(false, "N�o existe Loja com o identificador informado");
        }

        if (loja.getCnpj() != tLoja.getCnpj())
        {
            // Verificando se existe um Loja com o novo CPF
            tLoja = tDao.recoveryByCnpj(loja.getCnpj());
            if (tLoja != null)
            {
                return new LojaDto(false, "J� existe Loja com o cpf informado");
            }
        }

        // Atualizando o Loja
        tLoja = tDao.update(loja);
        if (tLoja == null)
        {
            return new LojaDto(false, "N�o existe Loja com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja alterado com sucesso", tLoja);
    }

    public LojaDto removeLoja(Loja loja)
    {
        // Verificar as informa��es
        if (loja.getId() <=0)
        {
            return new LojaDto(false, "Identificador da Loja inv�lido");
        }

        LojaDao tDao = new LojaDao();

        tDao.delete(loja);
        return new LojaDto(true, "Loja removida com sucesso");
    }
    
    public LojaDto pesquisarLojasPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Loja> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Lista de Lojas recuperada com sucesso", tLista);
    }
    
    public LojaDto pesquisarLoja()
    {
        // Criando a lista de retorno
        List<Loja> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Lista de Lojas recuperada com sucesso", tLista);
    }
}
