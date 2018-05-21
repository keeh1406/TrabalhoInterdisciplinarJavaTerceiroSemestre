package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.FuncionarioDao;
import br.com.maiscadastros.dto.FuncionarioDto;
import br.com.maiscadastros.model.Funcionario;

public class FuncionarioController
{
    public FuncionarioDto cadastrarFuncionario(Funcionario pFuncionario)
    {
        // Verificar as informa��es
        if (pFuncionario == null)
        {
            return new FuncionarioDto(false, "Tentativa de inclus�o de funcionario nulo");
        }

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        // Verificando se o funcionario j� existe
        Funcionario tFuncionario = tDao.recoveryByCpf(pFuncionario.getCpf());
        if (tFuncionario != null)
        {
            return new FuncionarioDto(false, "J� existe funcionario com o cpf informado");
        }

        // Incluindo o funcionario
        tFuncionario = tDao.create(pFuncionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario inclu�do com sucesso", tFuncionario);
    }

    public FuncionarioDto recuperarFuncionario(Funcionario funcionario)
    {
        // Verificar as informa��es
        if (funcionario.getId() <=0)
        {
            return new FuncionarioDto(false, "Identificador do Funcionario inv�lido");
        }

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        Funcionario tFuncionario = tDao.recovery(funcionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "N�o existe Funcionario com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario recuperado com sucesso", tFuncionario);
    }

    public FuncionarioDto atualizarFuncionario(Funcionario funcionario)
    {
        // Verificar as informa��es
        if (funcionario == null)
        {
            return new FuncionarioDto(false, "Tentativa de atualiza��o de Funcionario nulo");
        }

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        Funcionario tFuncionario = tDao.recovery(funcionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "N�o existe Funcionario com o identificador informado");
        }

        if (funcionario.getCpf() != tFuncionario.getCpf())
        {
            // Verificando se existe um Funcionario com o novo CPF
            tFuncionario = tDao.recoveryByCpf(funcionario.getCpf());
            if (tFuncionario != null)
            {
                return new FuncionarioDto(false, "J� existe Funcionario com o cpf informado");
            }
        }
        // Atualizando o Funcionario
        tFuncionario = tDao.update(funcionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "N�o existe Funcionario com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario alterado com sucesso", tFuncionario);
    }

    public FuncionarioDto removeFuncionario(Funcionario funcionario)
    {
        // Verificar as informa��es
        if (funcionario.getId() <=0)
        {
            return new FuncionarioDto(false, "Identificador do Funcionario inv�lido");
        }

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        tDao.delete(funcionario);
        return new FuncionarioDto(true, "Funcionario removido com sucesso");
    }
        
        // Incluindo o Funcionario
//        if (tDao.delete(pId))
//        {
//            // Retornando o indicativo de sucesso
//            return new FuncionarioDto(true, "Funcionario removido com sucesso");
//        }

        // Retornando o indicativo de erro
//        return new FuncionarioDto(false, "Erro no processo de remo��o");

    
    public FuncionarioDto pesquisarFuncionariosPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Funcionario> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Lista de Funcionarios recuperada com sucesso", tLista);
    }
    
    public FuncionarioDto pesquisar()
    {
        // Criando a lista de retorno
        List<Funcionario> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Lista de Funcionarios recuperada com sucesso", tLista);
    }
}

