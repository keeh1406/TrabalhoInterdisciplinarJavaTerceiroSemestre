package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.model.Marca;
import br.com.maiscadastros.util.ExceptionUtil;

public class MarcaDao {
	    private String comandoCreate   = "INSERT INTO MARCA"
	                    + "(ID, NOME )"
	                    + "VALUES (CONSULTA_SEQ.NEXTVAL, ?)";
	    private String comandoRecovery = "SELECT ID, NOME "
	                    + "FROM MARCA "
	                    + "WHERE ID = ?";
	    private String comandoRecoveryByNome = "SELECT ID, NOME "
	                    + "FROM MARCA "
	                    + "WHERE NOME = ?";
	    private String comandoUpdate   = "UPDATE MARCA "
	                    + "SET NOME = ? "
	                    + "WHERE ID = ?";
	    private String comandoDelete   = "DELETE FROM MARCA "
	                    + "WHERE ID = ?";
	    private String comandoSearch   = "SELECT ID, NOME "
	                    + "FROM MARCA";
	    private String comandoSearchByNome   = "SELECT ID, NOME "
	            + "FROM MARCA"
	    		+ "WHERE UPPER(NOME) LIKE UPPER(?)";

	    public Marca create(Marca pMarca)
	    {
	        try
	        {
	            // Obter a conexão
	            Connection tConexao = Conexao.getConexao();

	            // Criar o comando
	            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });


	            // Preencher o comando
	            int i = 1;
	            tComandoJdbc.setString(i++, pMarca.getNome());
	            // Executar o comando
	            int tQtd = tComandoJdbc.executeUpdate();

	            // Processar o resultado
	            if (tQtd == 1)
	            {
	                // Copiando o parametro
	                Marca tMarca = pMarca;

	                // Recuperando o código gerado pelo banco de dados
	                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
	                tRsChave.next();

	                // Assinalar a chave primária gerada no objeto
	                pMarca.setId(tRsChave.getInt(1));

	                // Liberar os recursos
	                tRsChave.close();
	                tComandoJdbc.close();

	                // Retornando o objeto inserido
	                return tMarca;
	            }
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Marca");
	        }

	        // Retorna null indicando algum erro de processamento
	        return null;
	    }

	    public Marca recovery(int pId)
	    {
	        try
	        {
	            // Obter a conexão
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
	                Marca tMarca = recuperarMarca(tResultSet);

	                // Liberar os recursos
	                tResultSet.close();
	                tComandoJdbc.close();

	                // Retornando o objeto inserido
	                return tMarca;
	            }
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Marca");
	        }

	        // Retorna null indicando algum erro de processamento
	        return null;
	    }
	    
	    public Marca recoveryByNome(String pNome)
	    {
	        try
	        {
	            // Obter a conexão
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
	                Marca tMarca = recuperarMarca(tResultSet);

	                // Liberar os recursos
	                tResultSet.close();
	                tComandoJdbc.close();

	                // Retornando o objeto inserido
	                return tMarca;
	            }
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do marca");
	        }

	        // Retorna null indicando algum erro de processamento
	        return null;
	    }

	    public Marca update(Marca pMarca)
	    {
	        try
	        {
	            // Obter a conexão
	            Connection tConexao = Conexao.getConexao();

	            // Criar o comando
	            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

	            // Preencher o comando
	            int i = 1;
	            tComandoJdbc.setString(i++, pMarca.getNome());
	            tComandoJdbc.setInt(i++, pMarca.getId());

	            // Executar o comando
	            int tQtd = tComandoJdbc.executeUpdate();

	            // Processar o resultado
	            if (tQtd == 1)
	            {
	                // Copiando o parametro
	                Marca tMarca = pMarca;

	                // Liberar os recursos
	                tComandoJdbc.close();

	                // Retornando o objeto inserido
	                return tMarca;
	            }
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Marca");
	        }

	        // Retorna null indicando algum erro de processamento
	        return null;
	    }

	    public boolean delete(int pId)
	    {
	        try
	        {
	            // Obter a conexão
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
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção da Marca");
	        }

	        // Retorna falso indicando algum erro de processamento
	        return false;
	    }

	    public List<Marca> search()
	    {
	        List<Marca> tLista = new ArrayList<>();

	        try
	        {
	            // Obter a conexão
	            Connection tConexao = Conexao.getConexao();

	            // Criar o comando
	            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

	            // Executar o comando
	            ResultSet tResultSet = tComandoJdbc.executeQuery();

	            // Processar o resultado
	            while (tResultSet.next())
	            {
	                Marca tMarca = recuperarMarca(tResultSet);

	                // Adicionar o o bjeto na lista
	                tLista.add(tMarca);
	            }

	            // Liberar os recursos
	            tResultSet.close();
	            tComandoJdbc.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Marca");
	        }

	        // Retornando a lista de objetos
	        return tLista;
	    }

	    private Marca recuperarMarca(ResultSet tResultSet) throws SQLException
	    {
	        Marca tMarca = new Marca();

	        // Recuperando os dados do resultSet
	        tMarca.setId(tResultSet.getInt("ID"));
	        tMarca.setNome(tResultSet.getString("NOME"));

	        return tMarca;
	    }
	    public List<Marca> searchByNome(String pNome)
	    {
	        if (pNome == null || pNome.isEmpty())
	            return search();

	        List<Marca> tLista = new ArrayList<>();

	        try
	        {
	            // Preparando o nome para pesquisa
	            String tNome = "%" + pNome + "%";

	            // Obter a conexão
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
	                Marca tMarca = recuperarMarca(tResultSet);

	                // Adicionar o o bjeto na lista
	                tLista.add(tMarca);
	            }

	            // Liberar os recursos
	            tResultSet.close();
	            tComandoJdbc.close();
	        }
	        catch (SQLException tExcept)
	        {
	            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Marcas");
	        }

	        // Retornando a lista de objetos
	        return tLista;
	    }
}
