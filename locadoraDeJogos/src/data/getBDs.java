package src.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import src.locadora;
import src.variaveis;
import src.model.consoles;
import src.model.jogos;

public class getBDs 
{	
	public static void setArrayListConsoles(ArrayList<consoles> consoles)
	{
		variaveis.consoles = consoles;
	}
	
	public static void setArrayListJogos(ArrayList<jogos> jogos)
	{
		variaveis.jogos = jogos;
	}
	
	// um método para consultar o BD
	public static void getBD(Connection connection) 
	{
		// criar uma lista auxiliar de jogos
		ArrayList<consoles> consoles = new ArrayList<consoles>();
		ArrayList<jogos> jogos = new ArrayList<jogos>();
		
		// todos comandos de BD tem que estar dentro de um try catch
		try 
		{
			// 3 - definir qual é o SQL que vai ser executado
			String sql = "SELECT * FROM " + locadora.tabelaAtual;
			
			// 4 - abrir a conexão com o BD
			Statement statement = connection.createStatement();
			
			// 5 - executar o SQL
			ResultSet resultado = statement.executeQuery(sql);
			
			switch (locadora.tabelaAtual)
			{
				case "Jogos":
				{
					// 6 - acessar os resultados e armazená-los em uma lista de objetos
					while (resultado.next())
					{
						int id = resultado.getInt("id");
						String nome = resultado.getString("nome");
						String desenvolvedor = resultado.getString("desenvolvedor");
						String distribuidora = resultado.getString("distribuidora");
						String genero = resultado.getString("genero");
						int ano = resultado.getInt("ano");
						int console = resultado.getInt("console");
						
						// criar um novo objeto de jogo com as informações que vieram do BD
						jogos jogo = new jogos(id, nome, desenvolvedor, distribuidora, genero, ano, console);
						// adiciona o novo objeto de jogo em uma lista de jogo
						jogos.add(jogo);
					}
					
					setArrayListJogos(jogos);
					break;
				}
			
				case "Consoles":
				{
					// 6 - acessar os resultados e armazená-los em uma lista de objetos
					while (resultado.next())
					{
						int id = resultado.getInt("id");
						String nome = resultado.getString("nome");
						String fabricante = resultado.getString("fabricante");
						int geracao = resultado.getInt("geracao");
						int ano = resultado.getInt("ano");
						
						// criar um novo objeto de jogo com as informações que vieram do BD
						consoles console = new consoles(id, nome, fabricante, geracao, ano);
						// adiciona o novo objeto de jogo em uma lista de jogo
						consoles.add(console);
					}
					
					setArrayListConsoles(consoles);
					break;
				}
				
				default:
					break;
			}
		} 
		catch (SQLException e) 
		{
			// System.out.println(e);
			e.printStackTrace();
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}// fim do método get
}
