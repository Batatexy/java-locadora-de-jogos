package src;

import java.sql.Connection;
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
	
	public static boolean Verificador(Connection connection, String verificador) throws SQLException
	{
		Statement statement = connection.createStatement();
		ResultSet resultado = statement.executeQuery(verificador);
	
		if (resultado.next())
			return true;
		else
			return false;
	}

	//Metodo que verifica se um ID existe
	public static boolean validarID(Connection connection, int id) throws SQLException
	{
		String verificador = "SELECT true FROM " + locadora.tabelaAtual + " WHERE id = " + id;
		return Verificador(connection, verificador);
	}
	
	//Metodo que verifica se um ID existe
	public static void IDLivre(Connection connection, String coluna) throws SQLException
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
        
		System.out.println("IDs Livres: " + idsString);
	}
}
