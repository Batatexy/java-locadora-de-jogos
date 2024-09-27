package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
	
}
