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
	public static void selectComparativo(String coluna) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o valor: ");
		String valor = scanner.nextLine();
		
		String sql = "SELECT * FROM " + locadora.tabelaAtual + " WHERE " + coluna + " like \"%" + valor + "%\"";
		select(sql);
	}
	
	public static void selectInnerJoin(String coluna) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o valor: ");
		String valor = scanner.nextLine();
		
		String sql = "SELECT * FROM ";
		select(sql);
	}

	public static void selectScanner() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a consulta SQL: ");
		String sql = scanner.nextLine();

		select(sql);
	}

	public static void select(String sql)
	{
		try (Connection connection = DriverManager.getConnection(locadora.jdbcUrl);
        Statement statement = connection.createStatement())
        {
           //Executa a consulta
		   ResultSet resultSet = statement.executeQuery(sql);

           //Obtém os metadados da quantidade de colunas da tabela
           int columnCount = resultSet.getMetaData().getColumnCount();
           
           int[] tamanhoColunas = new int[variaveis.valor];
           String[] nomeColunasArray = new String[variaveis.valor];
           
           //Resetar os valores da Arraylist
           for (int i = 1; i < columnCount+5; i++) 
           {
        	   if (i==1)
        	   {
        		   tamanhoColunas[i] = 1;
        	   }
        	   else
        	   {
        		   tamanhoColunas[i] = 10;
        	   }
           }
           
           //Guardar o tamanho de cada coluna em um Array
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
           
           int tamanhoLinha=-2;
           
           //Definir tamanho da Linha superior e inferior da tabela:
           for (int i = 1; i <= columnCount; i++) 
           {
        	   tamanhoLinha += tamanhoColunas[i] + 3;
           }
           
           //Printar Linha Superior:
           System.out.println(formatar.criarLinha(variaveis.linhaCima, tamanhoLinha));
           
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
           

           
         //Printar Linha Inferior:
           System.out.println(formatar.criarLinha(variaveis.linhaBaixo, tamanhoLinha));
           
           statement.close();
           resultSet.close();
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
			verificar.IDLivres(connection, "id");
			
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
						String sql = "INSERT INTO jogos " + 
						"(id, nome, desenvolvedor, distribuidora, genero, ano, console, unidade) " + 
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						
						tabela.jogos(connection, sql, id, false);
						
						break;
					}
					case "Consoles": 
					{
						String sql = "INSERT INTO consoles " +
						"(id, nome, fabricante, geracao, ano) " +
						"VALUES (?, ?, ?, ?, ?)";
						
						tabela.consoles(connection, sql, id, false);
	
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
		finally
		{
			
		}
	}
	
	// um método para editar dados do BD
	public static void atualizarDados(Connection connection) 
	{
		PreparedStatement preparedStatement = null;
		
		try 
		{
			Scanner scanner = new Scanner(System.in);
			
			// Remover o s do final dos nomes das tabelas
			String tabelaModificada = locadora.tabelaAtual.substring(0, locadora.tabelaAtual.length() - 1);
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1. Alterar uma coluna de um dado em " + tabelaModificada);
			System.out.println("2. ALterar todas as colunas de um dado em " + tabelaModificada);
			int opcao = scanner.nextInt();
			
			System.out.println("Digite o ID do " + tabelaModificada + " que deseja atualizar: ");
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
					//Digitalização da Coluna
					System.out.println("Digite o nome da coluna que deseja alterar: ");
					String coluna = scanner.nextLine();
					
					//Digitalização do novo Valor
					System.out.println("Digite o novo valor: ");
					String valor = scanner.nextLine();

					String sql = "UPDATE " + locadora.tabelaAtual + " SET " + coluna + " = ? WHERE id = ?";

					//Caso a coluna seja o Proprio ID, Verificar se já pertence a algum outro dado
					if (valor == "id") 
					{
						if (verificar.validarID(connection, id)) 
						{
							System.out.println("Este ID já existe!");
							return;
						}
					}
					
					//Alteração de dados
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, valor);
					preparedStatement.setInt(2, id);
					
					//agora, só falta executar a query sql
					if (preparedStatement.executeUpdate() > 0) 
					{
						System.out.println("Atualização efetuada com sucesso!");
					}
					else 
					{
						System.out.println("Nenhuma linha do BD foi afetada");
					}
				}
				else if (opcao == 2)
				{
					//Switch para mudar pra tabela certa
					switch (locadora.tabelaAtual) 
					{
						case "Jogos": 
						{					
							String sql = "UPDATE jogos SET " +
									"id = ?, " +
									"nome = ?, " +
									"desenvolvedor = ?, " +
									"distribuidora = ?, " + 
									"genero = ?, " +
									"ano = ?, " +
									"console = ? " + 
									"WHERE id = " + id;
							
							tabela.jogos(connection, sql, id, true);
							
							break;
						}
						case "Consoles": 
						{
							String sql = "UPDATE consoles SET " +
									"id = ?, " +
									"nome = ?, " +
									"fabricante = ?, " +
									"geracao = ?, " + 
									"ano = ? " +
									"WHERE id = " + id;
							
							tabela.consoles(connection, sql, id, true);
		
							break;
						}
						
						default:
							break;
						
						
					}
					
				}
				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		finally
		{
			verificar.fechar(preparedStatement);
		}
	}
	
	
	public static void updateAutomatico(Connection connectionOriginal, String coluna, int id, int valor) 
	{
		PreparedStatement preparedStatement = null;
		int soma = 0;
		
		//Buscar o id especifico em tabela
		String sql = "SELECT * FROM " + locadora.tabelaAtual + " where id = " + id;
		
		try (Connection connection = DriverManager.getConnection(locadora.jdbcUrl);
		Statement statement = connection.createStatement())
        {
            //Executa a consulta
		    ResultSet resultSet = statement.executeQuery(sql);
		    
		    //Valor de unidades por exemplos
		    int numero = resultSet.getInt(7);
		    
		    //Nome do item
		    String nome = resultSet.getString(2);
		    
		    //Soma ou Subtração do valor original pelo valor inserido no metodo
		    //Exemplo: 20 unidades - 1
		    soma = numero + valor;
		   
		    verificar.fechar(resultSet);
		   
		    if (numero>0)
		    {
				sql = "UPDATE " + locadora.tabelaAtual + " SET " + coluna + " = ? WHERE id = ?";
				
				//Alteração de dados
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, soma);
				preparedStatement.setInt(2, id);
				
				//agora, só falta executar a query sql
				if (preparedStatement.executeUpdate() > 0)
				{
					System.out.println("Atualização efetuada com sucesso!");
				}
				else 
				{
					System.out.println("Nenhuma linha do BD foi afetada");
				}
		    }
		    else
		    {
		    	System.out.println("Não há mais unidades de " + nome);
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
		
		
	}
	
	

	//um método para deletar dados do BD
	public static void deletarDados(Connection connection) 
	{
		Scanner scanner = new Scanner(System.in);
		String sql = "DELETE FROM " + locadora.tabelaAtual + " WHERE id = ?";

		System.out.println("\nInforme o ID que deseja deletar da tabela " + locadora.tabelaAtual + ": ");
		int id = scanner.nextInt();
		
		PreparedStatement preparedStatement = null;

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
				//Obter um numero entre 1000 e 9999 como verificação
				int numeroAleatorio = ThreadLocalRandom.current().nextInt(1000, 9999);
				System.out.print("Para EXCLUIR o dado " + id + ", digite o número a seguir " + numeroAleatorio + ":") ;
				
				int resposta = scanner.nextInt();
				scanner.nextLine();
				
				if (resposta == numeroAleatorio) 
				{
					preparedStatement = connection.prepareStatement(sql);
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
			System.out.println("Erro ao deletar registro: " + id);
		}
		finally
		{
			verificar.fechar(preparedStatement);
		}
	}

}
