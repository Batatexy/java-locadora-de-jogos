package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class tabela 
{
	public static void jogos(Connection connection, String sql, int id, boolean update)
	{
		PreparedStatement preparedStatement = null;
		
		try
		{
			Scanner scanner = new Scanner(System.in);
			
			int mudarID = id;
			
			if (update)
			{
				System.out.println("Digite o novo id do jogo: ");
				mudarID = scanner.nextInt(); // que é usado pra ler string
				
				while (!verificar.validarID(connection, id) && id != 0) 
				{
					System.out.println("Este ID não existe! Digite um ID existente:");
					id = scanner.nextInt(); // assim, já retorna um int
				}
				scanner.nextLine();
			}
			
			System.out.println("Digite o nome do jogo: ");
			String nome = scanner.nextLine(); // que é usado pra ler string
	
			System.out.println("Digite o desenvolvedor do jogo: ");
			String desenvolvedor = scanner.nextLine();
	
			System.out.println("Digite a distribuidora do jogo: ");
			String distribuidora = scanner.nextLine();
	
			System.out.println("Digite o genero do jogo: ");
			String genero = scanner.nextLine();
	
			System.out.println("Digite o ano de publicacao do jogo: ");
			int ano = scanner.nextInt();
	
			System.out.println("Digite o console do jogo: ");
			int console = scanner.nextInt();
			
			System.out.println("Digite a unidade do jogo: ");
			int unidade = scanner.nextInt();
			
			// após, fazer a inicialização do objeto que permite criar querys com parâmetros
			preparedStatement = connection.prepareStatement(sql);
	
			// por fim, fazer o preenchimento dos parâmetros
			preparedStatement.setInt(1, mudarID);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, desenvolvedor);
			preparedStatement.setString(4, distribuidora);
			preparedStatement.setString(5, genero);
			preparedStatement.setInt(6, ano);
			preparedStatement.setInt(7, console);
			preparedStatement.setInt(8, unidade);
			
			// agora, só falta executar a query sql
			if (preparedStatement.executeUpdate() > 0) 
			{
				if (update)
				{
					System.out.println("Atualização efetuada com sucesso!");
				}
				else
				{
					System.out.println("Inserção efetuada com sucesso!");
				}
			}
			else 
			{
				System.out.println("Nenhuma linha do BD foi afetada");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Erro de SQL: " + e.getMessage());
		} 
		catch (Exception e1) 
		{
			System.out.println("Erro genérico: " + e1.getMessage());
		}
		finally
		{
			verificar.fechar(preparedStatement);
		}
	}
	
	
	
	public static void consoles(Connection connection, String sql, int id, boolean update)
	{
		PreparedStatement preparedStatement = null;
		
		try
		{
			Scanner scanner = new Scanner(System.in);
			
			int mudarID = id;
			
			if (update)
			{
				System.out.println("Digite o novo id do Console: ");
				mudarID = scanner.nextInt(); // que é usado pra ler string
	
				while (!verificar.validarID(connection, id) && id != 0) 
				{
					System.out.println("Este ID não existe! Digite um ID existente:");
					id = scanner.nextInt(); // assim, já retorna um int
				}
				scanner.nextLine();
			}
			
			System.out.println("Digite o título do console: ");
			String nome = scanner.nextLine(); // que é usado pra ler string
	
			System.out.println("Digite o fabricante do console: ");
			String fabricante = scanner.nextLine();
	
			System.out.println("Digite a geração do console: ");
			int geracao = scanner.nextInt();
	
			System.out.println("Digite o ano de lançamento do console: ");
			int ano = scanner.nextInt();
	
			// após, fazer a inicialização do objeto que permite criar querys com parâmetros
	
			preparedStatement = connection.prepareStatement(sql);
	
			// por fim, fazer o preenchimento dos parâmetros
			preparedStatement.setInt(1, mudarID);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, fabricante);
			preparedStatement.setInt(4, geracao);
			preparedStatement.setInt(5, ano);
			
			// agora, só falta executar a query sql
			if (preparedStatement.executeUpdate() > 0) 
			{
				if (update)
				{
					System.out.println("Atualização efetuada com sucesso!");
				}
				else
				{
					System.out.println("Inserção efetuada com sucesso!");
				}
			} 
			else 
			{
				System.out.println("Nenhuma linha do BD foi afetada");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Erro de SQL: " + e.getMessage());
		} 
		catch (Exception e1) 
		{
			System.out.println("Erro genérico: " + e1.getMessage());
		}
		finally
		{
			verificar.fechar(preparedStatement);
		}
	}
}
