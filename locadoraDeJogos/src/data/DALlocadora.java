package src.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import src.locadora;
import src.tabela;
import src.formatar;
import src.verificar;
import src.variaveis;

public class DALlocadora 
{
	public static void select() 
	{
		selectMetodo();
	}

	public static void selectScanner() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a consulta SQL: ");
		String sql = scanner.nextLine();

		selectMetodo();
	}

	public static void selectMetodo()
	{
		String sql = "SELECT * FROM " + locadora.tabelaAtual;
		
		try (Connection connection = DriverManager.getConnection(locadora.jdbcUrl);
        Statement statement = connection.createStatement())
        {
           //Executa a consulta
           ResultSet resultSet = statement.executeQuery(sql);

           //Obtém os metadados da quantidade de colunas da tabela
           int columnCount = resultSet.getMetaData().getColumnCount();
           
           int[] tamanhoColunas = new int[variaveis.valor];
           String[] nomeColunasArray = new String[variaveis.valor];
           
           while (resultSet.next()) 
           {
               for (int i = 1; i <= columnCount; i++) 
               {
            	   ResultSetMetaData metaData = resultSet.getMetaData();
            	   String nomeColuna = metaData.getColumnName(i);
            	   
            	   nomeColuna = formatar.alterarNomes(nomeColuna);
            	   
	        	   //Capitaliza os nomes restantes:
	        	   nomeColuna = nomeColuna.substring(0,1).toUpperCase().concat(nomeColuna.substring(1));
	        	   
	        	   //Definir o tamanho certo pra cada coluna, analisando o nome de cada item e de cada coluna:
	        	   tamanhoColunas[i] = verificar.tamanho(resultSet.getString(i), nomeColuna, tamanhoColunas[i]);
	        	   nomeColunasArray[i] = nomeColuna;
               }
           }
           
           //Printar Cabeçalho:
           for (int i = 1; i <= columnCount; i++) 
           {
        	   System.out.print(formatar.gerarEspacos(nomeColunasArray[i], tamanhoColunas[i], "center"));
           }
           System.out.println();
           
           //Printar os Nomes dos itens da tabela:
           resultSet = statement.executeQuery(sql);
           while (resultSet.next()) 
           {
               for (int i = 1; i <= columnCount; i++) 
               {
            	   System.out.print(formatar.gerarEspacos(resultSet.getString(i), tamanhoColunas[i], "left"));
               }
               System.out.println();
           }
           
           //Resetar os valores da Arraylist
           for (int i = 1; i < columnCount; i++) 
           {
        	   tamanhoColunas[i] = 0;
           }
           
           System.out.println(formatar.criarLinha(variaveis.linhaBaixo, 100, 1));
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}

	// um método para inserir dados no BD
	public static void inserirDado(Connection connection) 
	{
		try 
		{
			// primeiro, vamos ler o teclado para o usuário informar
			// quais dados serão inseridos no BD
			//PreparedStatement preparedStatement = null;
			Scanner scanner = new Scanner(System.in);

			//Verificar 3 IDs livres:
			verificar.IDLivre(connection, "id");

			System.out.println("Tabela " + locadora.tabelaAtual + ", Digite o ID: (número inteiro): ");
			// String id = scanner.nextLine(); //isso retorna uma string
			int id = scanner.nextInt(); // assim, já retorna um int

			while (verificar.validarID(connection, id) == true && id != 0) 
			{
				System.out.println("Este ID já existe! Digite um ID válido:");
				id = scanner.nextInt(); // assim, já retorna um int
			}

			// quando usamos o next int, o scanner consome apenas o valor inteiro, mas o buffer do teclado sempre coloca um \n no fim de cada instrução que é lida do teclado, se ler direto, vai ficar com um \n que tava sobrando no buffer
			scanner.nextLine(); // para poder consumir o \n e limpar o buffer

			if (id != 0) 
			{
				// Switch para mudar pra tabela certa
				switch (locadora.tabelaAtual) 
				{
					case "Jogos": 
					{
						// depois, é necessário fazer a definição da query sql de inserção de dados
						String sql = "INSERT INTO " + locadora.tabelaAtual + 
						" (id, nome, desenvolvedor, distribuidora, genero, ano, console) " + 
						" VALUES (?, ?, ?, ?, ?, ?, ?)";
						
						tabela.jogos(connection, sql, id);
						
						break;
					}
					case "Consoles": 
					{
						String sql = "INSERT INTO " + locadora.tabelaAtual + 
						" (id, nome, fabricante, geracao, ano) " +
						" VALUES (?, ?, ?, ?, ?)";
						
						tabela.consoles(connection, sql, id);
	
						break;
					}
					
					default:
						break;
					
				}
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
	
	

	// um método para editar dados do BD
	public static void atualizarDados(Connection connection) 
	{
		try 
		{
			Scanner scanner = new Scanner(System.in);
			
			PreparedStatement preparedStatement = null;
			
			// Remover o s do final dos nomes das tabelas
			String tabelaModificada = locadora.tabelaAtual.substring(0, locadora.tabelaAtual.length() - 1);
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1. Alterar uma coluna de um dado em " + tabelaModificada);
			System.out.println("2. ALterar todas as colunas de um dado em " + tabelaModificada);
			int opcao = scanner.nextInt();
			
			System.out.print("Digite o ID do " + tabelaModificada + " que deseja atualizar: ");
			int id = scanner.nextInt();

			// 5 - executar o SQL
			while (!verificar.validarID(connection, id) && id != 0) 
			{
				System.out.println("Este ID não existe! Digite um ID existente:");
				id = scanner.nextInt(); // assim, já retorna um int
			}

			scanner.nextLine();

			if (id != 0) 
			{
				if (opcao == 1)
				{
					// Digitalização da Coluna
					System.out.print("Digite o nome da coluna que deseja alterar: ");
					String coluna = scanner.nextLine();
					
					// Digitalização do novo Valor
					System.out.print("Digite o novo valor: ");
					String valor = scanner.nextLine();

					String sql = "UPDATE " + locadora.tabelaAtual + " SET " + coluna + " = ? WHERE id = ?";
					System.out.print("\n");

					// Caso a coluna seja o Proprio ID, Verificar se já pertence a algum outro dado
					if (valor == "id") {
						if (verificar.validarID(connection, id)) 
						{
							System.out.print("Este ID já existe!");
						}
					}

					// Alteração de dados
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, valor);
					preparedStatement.setInt(2, id);
				}
				else if (opcao == 2)
				{
					// Switch para mudar pra tabela certa
					switch (locadora.tabelaAtual) 
					{
						case "Jogos": 
						{
							// depois, é necessário fazer a
							// definição da query sql de inserção de dados
							String sql = "UPDATE " + locadora.tabelaAtual + " SET " +
							"id = ?, nome = ?, desenvolvedor = ?, distribuidora = ?, genero = ? " + 
							"ano = ?, console = ? WHERE id = " + id +
							" VALUES (?, ?, ?, ?, ?, ?, ?)";
							
							tabela.jogos(connection, sql, id);
							
							break;
						}
						case "Consoles": 
						{
							String sql = " UPDATE SET " + locadora.tabelaAtual + 
							" (id, nome, fabricante, geracao, ano) " +
							" VALUES (?, ?, ?, ?, ?)";
							
							tabela.consoles(connection, sql, id);
		
							break;
						}
						
						default:
						{
							preparedStatement = connection.prepareStatement("");
							break;
						}
					}
				}

				if (opcao==1 || opcao==2)
				{
					if (preparedStatement.executeUpdate() > 0) 
					{
						System.out.print("Registro atualizado com sucesso!");
					} 
					else 
					{
						System.out.print("Nenhum registro atualizado");
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// um método para deletar dados do BD
	public static void deletarDados(Connection connection) 
	{
		Scanner scanner = new Scanner(System.in);
		String sql = "DELETE FROM " + locadora.tabelaAtual + " WHERE id = ?";

		System.out.print("\nInforme o ID que deseja deletar da tabela " + locadora.tabelaAtual + ": ");
		int id = scanner.nextInt();

		try 
		{
			while (verificar.validarID(connection, id) == false && id != 0) 
			{
				System.out.println("Este ID não existe! Digite um ID existente:");
				id = scanner.nextInt(); // assim, já retorna um int
			}

			scanner.nextLine();

			if (id != 0) 
			{
				// Obter um numero entre 1000 e 9999 como verificação
				int numeroAleatorio = ThreadLocalRandom.current().nextInt(1000, 9999);
				System.out.print("Digite o número a seguir para excluir o dado: " + numeroAleatorio + ":") ;
				
				int resposta = scanner.nextInt();
				scanner.nextLine();

				if (resposta == numeroAleatorio) 
				{
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, id);

					if (preparedStatement.executeUpdate() > 0) 
					{
						System.out.println("Registro deletado");
					} 
					else 
					{
						System.out.println("Nenhum registro alterado");
					}
				}
				else 
				{
					System.out.println("Nenhum registro alterado");
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.print("Erro ao deletar registro" + id + "/n");
		}
	}
}
