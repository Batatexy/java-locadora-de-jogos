package src.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import src.locadora;

public class verificar 
{
	//int idJogos
	public static int tamanho(String nome, String nomeColuna, int tamanho)
	{
		int intNome = nome.length();
		int intNomeColuna = nomeColuna.length();
		
		if (intNome>tamanho)
			return intNome;
		
		if (intNomeColuna>tamanho)
			return intNomeColuna;
		
		return tamanho;
	}
	
	//Metodo que verifica se um ID existe
	public static boolean validarUnidadeJogos(int id)
	{
		PreparedStatement preparedStatement = null;
		int soma = 0;
		
		//Buscar o id especifico em tabela
		String sql = "SELECT * FROM jogos where id = " + id;
		
		try (Connection connection = DriverManager.getConnection(locadora.jdbcUrl);
		Statement statement = connection.createStatement())
        {
            //Executa a consulta
		    ResultSet resultSet = statement.executeQuery(sql);
		    
		    //Valor de unidades:
		    int numero = resultSet.getInt(7);
		    
		    //Nome do item
		    String nome = resultSet.getString(2);
		    
		    if (numero<=0)
		    {
		    	System.out.println("Não há mais unidades de " + nome);
		    	verificar.fechar(resultSet);
		    	return false;
		    }
		    else
		    {
		    	verificar.fechar(resultSet);
		    	return true;
		    }
        }
		catch (Exception e)
        {
        	e.printStackTrace();
        }
		finally
		{
			verificar.fechar(preparedStatement);
		}
		return false;
	}
	
	//Metodo que verifica se um ID existe e retorna um boolean
	//True é porque existe o ID
	//False é que não existe, está livre
	public static boolean validarID(Connection connection, int id)
	{
		String verificador = "SELECT true FROM " + locadora.tabelaAtual + " WHERE id = " + id;
		
		Statement statement = null;
		ResultSet resultSet = null;
		try 
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(verificador);
			
			if (resultSet.next())
				return true;
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			fechar(statement);
			fechar(resultSet);
		}
		
		return true;
	}
	
	//Metodo que verifica se um ID existe
	public static void IDLivres(Connection connection, String coluna) throws SQLException
	{
		ArrayList<Integer> ids = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
		String idsString = "";
		
        for (int i = 1; i < 4; i++) 
        {
        	ids.set(i, ids.get(i-1) + 1);
        	
    		while (validarID(connection, ids.get(i)))
    		{
    			ids.set(i, ids.get(i) + 1);
    		}
    		
    		idsString += ids.get(i) + ", ";
        }
        
        // Remover a "," do final
     	String idModificado = idsString.substring(0, idsString.length() - 2);
		System.out.println("IDs Livres: " + idModificado);
	}
	
	public static void fechar(Connection connection)
	{
	    if (connection != null) 
	    {
	        try 
	        {
	            connection.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void fechar(ResultSet resultSet)
	{
	    if (resultSet != null) 
	    {
	        try 
	        {
	        	resultSet.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void fechar(PreparedStatement preparedStatement)
	{
	    if (preparedStatement != null) 
	    {
	        try 
	        {
	        	preparedStatement.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void fechar(Statement statement)
	{
	    if (statement != null) 
	    {
	        try 
	        {
	        	statement.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	//Verificar Fim
}
