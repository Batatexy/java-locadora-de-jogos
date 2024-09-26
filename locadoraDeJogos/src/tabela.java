package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class tabela 
{
	public static void jogos(Connection connection, String sql, int id)
	{
		Scanner scanner = new Scanner(System.in);
		
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

		// após, fazer a inicialização do objeto que permite criar
		// queries com parâmetros
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
			// por fim, fazer o preenchimento dos parâmetros
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, desenvolvedor);
			preparedStatement.setString(4, distribuidora);
			preparedStatement.setString(5, genero);
			preparedStatement.setInt(6, ano);
			preparedStatement.setInt(7, console);
			
			// agora, só falta executar a query sql
			if (preparedStatement.executeUpdate() > 0) 
			{
				System.out.println("\nInserção efetuada com sucesso!\n");
			} 
			else 
			{
				System.out.println("\nNenhuma linha do BD foi afetada");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Erro de SQL: " + e.getMessage() + "/n");
		} 
		catch (Exception e1) 
		{
			System.out.println("Erro genérico: " + e1.getMessage() + "/n");
		}
	}
	
	
	
	public static void consoles(Connection connection, String sql, int id)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o título do console: ");
		String nome = scanner.nextLine(); // que é usado pra ler string

		System.out.println("Digite o fabricante do console: ");
		String fabricante = scanner.nextLine();

		System.out.println("Digite a geração do console: ");
		int geracao = scanner.nextInt();

		System.out.println("Digite o ano de lançamento do console: ");
		int ano = scanner.nextInt();

		// após, fazer a inicialização do objeto que permite criar
		// queries com parâmetros
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
			// por fim, fazer o preenchimento dos parâmetros
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, fabricante);
			preparedStatement.setInt(4, geracao);
			preparedStatement.setInt(5, ano);
			
			// agora, só falta executar a query sql
			if (preparedStatement.executeUpdate() > 0) 
			{
				System.out.println("\nInserção efetuada com sucesso!\n");
			} 
			else 
			{
				System.out.println("\nNenhuma linha do BD foi afetada");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Erro de SQL: " + e.getMessage() + "/n");
		} 
		catch (Exception e1) 
		{
			System.out.println("Erro genérico: " + e1.getMessage() + "/n");
		}
	}
	
	
	

	
	
	
	
	
	

}
