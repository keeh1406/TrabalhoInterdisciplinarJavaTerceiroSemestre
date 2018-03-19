package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.model.Produto;
import br.com.maiscadastros.util.ExceptionUtil;

public class ProdutoDao
{
    private String comandoCreate   = "INSERT INTO PRODUTO"
                    + "(ID, NOME, DESCRICAO, DATAVALIDADE, ID_LOJA, ID_SETOR, ID_MARCA )"
                    + "VALUES (CONSULTA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, NOME, DESCRICAO, DATAVALIDADE, ID_LOJA, ID_SETOR, ID_MARCA "
                    + "FROM PRODUTO "
                    + "WHERE ID = ?";
    private String comandoRecoveryByNome = "SELECT ID, NOME, DESCRICAO, DATAVALIDADE, MARCA, ID_LOJA, ID_SETOR, ID_MARCA "
                    + "FROM PRODUTO "
                    + "WHERE NOME = ?";
    private String comandoUpdate   = "UPDATE PRODUTO "
                    + "SET NOME = ?, "
                    + "DESCRICAO = ?, "
                    + "DATAVALIDADE = ?, "
                    + "MARCA = ?, "
                    + "ID_LOJA = ?, "
                    + "ID_SETOR = ?, "
                    + "ID_MARCA = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM PRODUTO "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, NOME, DESCRICAO, DATAVALIDADE, MARCA, ID_LOJA, ID_SETOR, ID_MARCA "
                    + "FROM PRODUTO";
    private String comandoSearchByLoja   = "SELECT ID, NOME, DESCRICAO, DATAVALIDADE, MARCA, ID_LOJA, ID_SETOR, ID_MARCA "
                    + "FROM PRODUTO "
                    + "WHERE ID_LOJA = ?";
    private String comandoSearchByNome   = "SELECT ID, NOME, DESCRICAO, DATAVALIDADE, MARCA, ID_LOJA, ID_SETOR, ID_MARCA "
            + "FROM PRODUTO"
    		+ "WHERE UPPER(NOME) LIKE UPPER(?)";
    private String comandoCountByLoja   = "SELECT COUNT(ID_LOJA) "
                    + "FROM PRODUTO "
                    + "WHERE ID_LOJA = ?";

    public Produto create(Produto pProduto)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });


            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pProduto.getNome());
            tComandoJdbc.setString(i++, pProduto.getDescricao());
            tComandoJdbc.setDate(i++, Date.valueOf(pProduto.getDataValidade()));
            tComandoJdbc.setInt(i++, pProduto.getIdLoja());
            tComandoJdbc.setInt(i++, pProduto.getIdSetor());
            tComandoJdbc.setInt(i++, pProduto.getIdMarca());
            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Produto tProduto = pProduto;

                // Recuperando o c�digo gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave prim�ria gerada no objeto
                pProduto.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tProduto;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o da Produto");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Produto recovery(int pId)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecovery);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pId);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            if (tResultSet.next())
            {
                // Criando o objeto
                Produto tProduto = recuperarProduto(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tProduto;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o da Produto");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }
    
    public Produto recoveryByNome(String pNome)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecoveryByNome);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pNome);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            if (tResultSet.next())
            {
                // Criando o objeto
                Produto tProduto = recuperarProduto(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tProduto;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do produto");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Produto update(Produto pProduto)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pProduto.getNome());
            tComandoJdbc.setString(i++, pProduto.getDescricao());
            tComandoJdbc.setDate(i++, Date.valueOf(pProduto.getDataValidade()));
            tComandoJdbc.setInt(i++, pProduto.getIdLoja());
            tComandoJdbc.setInt(i++, pProduto.getIdSetor());
            tComandoJdbc.setInt(i++, pProduto.getIdMarca());
            tComandoJdbc.setInt(i++, pProduto.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Produto tProduto = pProduto;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tProduto;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o da Produto");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public boolean delete(int pId)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoDelete);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pId);

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o indicativo de sucesso
                return true;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remo��o da Produto");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Produto> search()
    {
        List<Produto> tLista = new ArrayList<>();

        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Produto tProduto = recuperarProduto(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tProduto);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o da Produto");
        }

        // Retornando a lista de objetos
        return tLista;
    }
    
    public int countByLoja(int pIdLoja)
    {
        int tQtde = 0;

        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByLoja);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdLoja);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            tResultSet.next();
            tQtde = tResultSet.getInt(1);

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();

        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do produto");
        }

        // Retornando a lista de objetos
        return tQtde;
    }
    
    public List<Produto> searchByIdLoja(int pIdLoja)
    {
        List<Produto> tLista = new ArrayList<>();

        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByLoja);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdLoja);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Produto tProduto = recuperarProduto(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tProduto);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do Produto");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Produto recuperarProduto(ResultSet tResultSet) throws SQLException
    {
        Produto tProduto = new Produto();

        // Recuperando os dados do resultSet
        tProduto.setId(tResultSet.getInt("ID"));
        tProduto.setNome(tResultSet.getString("NOME"));
        tProduto.setDescricao(tResultSet.getString("DESCRICAO"));
        tProduto.setDataValidade(tResultSet.getDate("DATAVALIDADE").toLocalDate());
        tProduto.setIdLoja(tResultSet.getInt("ID_LOJA"));
        tProduto.setIdLoja(tResultSet.getInt("ID_SETOR"));
        tProduto.setIdLoja(tResultSet.getInt("ID_MARCA"));

        return tProduto;
    }
    public List<Produto> searchByNome(String pNome)
    {
        if (pNome == null || pNome.isEmpty())
            return search();

        List<Produto> tLista = new ArrayList<>();

        try
        {
            // Preparando o nome para pesquisa
            String tNome = "%" + pNome + "%";

            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByNome);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, tNome);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Produto tProduto = recuperarProduto(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tProduto);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Produtos");
        }

        // Retornando a lista de objetos
        return tLista;
    }
}
