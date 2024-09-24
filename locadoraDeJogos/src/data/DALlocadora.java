package src.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import src.locadora;
import src.metodosAuxiliares;
import src.variaveis;
import src.model.jogos;

public class DALlocadora 
{
	public static void select()
	{
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("Digite a consulta SQL: ");
        String sql = scanner.nextLine();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(locadora.jdbcUrl);
                Statement stmt = conn.createStatement()) 
        {
               // Executa a consulta
               ResultSet rs = stmt.executeQuery(sql);

               // Obtém os metadados da tabela
               int columnCount = rs.getMetaData().getColumnCount();
               
               double value = 0;

               //Fazer um esquema de: le tudo, transferem de string pra int e usa o esquema la de saber o tamanho de cada ID
               //Ver se tem como criar variaveis infinitas, para salvar cada valor



               // Imprime os resultados
               while (rs.next()) 
               {
                   for (int i = 1; i <= columnCount; i++) 
                   {
                       System.out.print(rs.getString(i) + "\t");
                   }
                   System.out.println();
               }
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        }
		
        System.out.println(metodosAuxiliares.criarLinha(variaveis.linhaBaixo, variaveis.finalBaixo, 100, 1));
	}

    //"SELECT * FROM jogos ORDER BY ano";
 

	// um método para inserir dados no BD
	public static void inserirDado(Connection connection) 
	{
		try
		{
		// primeiro, vamos ler o teclado para o usuário informar
		// quais dados serão inseridos no BD
			PreparedStatement preparedStatement;
			Scanner scanner = new Scanner(System.in);
			
			metodosAuxiliares.verificarIDLivre(connection, "id");
			
			System.out.println("Tabela " + locadora.tabelaAtual + ", Digite o ID: (número inteiro): ");
			// String id = scanner.nextLine(); //isso retorna uma string
			int id = scanner.nextInt(); // assim, já retorna um int
			
			while (metodosAuxiliares.validarID(connection, id) == true && id!=0)
			{
				System.out.println("Este ID já existe! Digite um ID válido:");
				id = scanner.nextInt(); // assim, já retorna um int
			}
			
			// quando usamos o next int, o scanner consome apenas o valor
			// inteiro, mas o buffer do teclado sempre coloca um \n no fim
			// de cada instrução que é lida do teclado
			// se a gente ser ler direto, ela vai ficar com o \n
			// que tava sobrando no buffer
			scanner.nextLine(); // para poder consumir o \n e limpar o buffer
			
			if (id!=0)
			{
				//Switch para mudar pro banco certo
				switch (locadora.tabelaAtual)
				{
					case "Jogos":
					{
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
						
						// depois, é necessário fazer a 
						// definição da query sql de inserção de dados
						String sql = " INSERT INTO " + locadora.tabelaAtual + " " +
									 " (id, nome, desenvolvedor, distribuidora,, genero, ano, console) " +
									 " VALUES (?, ?, ?, ?, ?, ?)";
						
						// após, fazer a inicialização do objeto que permite criar
						// queries com parâmetros
						preparedStatement = connection.prepareStatement(sql);
						
						//por fim, fazer o preenchimento dos parâmetros
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, nome);
						preparedStatement.setString(3, desenvolvedor);
						preparedStatement.setString(4, distribuidora);
						preparedStatement.setString(5, genero);
						preparedStatement.setInt(6, ano);
						preparedStatement.setInt(7, console);
						
						break;
					}
					case "Consoles":
					{
						System.out.println("Digite o título do console: ");
						String nome = scanner.nextLine(); // que é usado pra ler string
			
						System.out.println("Digite o fabricante do console: ");
						String fabricante = scanner.nextLine();
						
						System.out.println("Digite a geração do console: ");
						int geracao = scanner.nextInt();
						
						System.out.println("Digite o ano de lançamento do console: ");
						int ano = scanner.nextInt();
						
						// depois, é necessário fazer a 
						// definição da query sql de inserção de dados
						String sql = " INSERT INTO " + locadora.tabelaAtual + " " +
									 " (id, nome, fabricante, geracao, ano) " +
									 " VALUES (?, ?, ?, ?, ?)";
						
						// após, fazer a inicialização do objeto que permite criar
						// queries com parâmetros
						preparedStatement = connection.prepareStatement(sql);
						
						//por fim, fazer o preenchimento dos parâmetros
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, nome);
						preparedStatement.setString(3, fabricante);
						preparedStatement.setInt(4, geracao);
						preparedStatement.setInt(5, ano);
						
						break;
					}
					default:
						preparedStatement = connection.prepareStatement("");
						break;
				}
				
				//agora, só falta executar a query sql
				if (preparedStatement.executeUpdate() > 0) 
				{
					System.out.println("\nInserção efetuada com sucesso!\n");
				} 
				else 
				{ 
					System.out.println("\nNenhuma linha do BD foi afetada");
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
			
			//Remover o s do final dos nomes das tabelas
			String tabelaModificada = locadora.tabelaAtual.substring(0, locadora.tabelaAtual.length() - 1);
			System.out.print("Digite o ID do " + tabelaModificada + " que deseja atualizar: ");
			int id = scanner.nextInt();
			
			// 5 - executar o SQL
			while (!metodosAuxiliares.validarID(connection, id) && id!=0)
			{
				System.out.println("Este ID não existe! Digite um ID existente:");
				id = scanner.nextInt(); // assim, já retorna um int
			}
			
			scanner.nextLine();
			
			if (id!=0)
			{
				//Digitalização da Coluna
				System.out.print("Digite o nome da coluna que deseja alterar: ");
				String coluna = scanner.nextLine();
					
				//Amplificar o metodo para permitir que sejam alteradas mais de 1 coluna
				
				//Digitalização do novo Valor
				System.out.print("Digite o novo valor: ");
				String valor = scanner.nextLine();
				
				String sql = "UPDATE " + locadora.tabelaAtual + " SET " + coluna + " = ? WHERE id = ?";
				System.out.print("\n");
				
				//Caso a coluna seja o Proprio ID, Verificar se já pertence a algum outro dado
				if (valor == "id")
				{
					if (metodosAuxiliares.validarID(connection, id))
					{
						System.out.print("Este ID já existe!");
					}
				}
				
				//Alteração de dados
				PreparedStatement prepareStatement = connection.prepareStatement(sql);		
				prepareStatement.setString(1, valor);
				prepareStatement.setInt(2, id);
				
				if (prepareStatement.executeUpdate()>0)
				{
					System.out.print("Registro atualizado com sucesso!");
				}
				else
				{
					System.out.print("Nenhum registro atualizado");
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
			while (metodosAuxiliares.validarID(connection, id) == false && id!=0)
			{
				System.out.println("Este ID não existe! Digite um ID existente:");
				id = scanner.nextInt(); // assim, já retorna um int
			}
			
			scanner.nextLine();
		
			if (id != 0)
			{
				System.out.print("Tem certeza que deseja EXCLUIR o dado de ID: " + id + "? (Sim / Não)");
				String resposta = scanner.nextLine();
				resposta.toLowerCase();
				
				System.out.println(resposta);
				
				if (resposta == "sim" || resposta == "s")
				{
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, id);
					
					if (preparedStatement.executeUpdate() > 0)
					{
						System.out.println("Registro deletado com sucesso!");
					}
					else
					{
						System.out.println("Nenhum registro alterado");
					}
				}
				else
				{
					System.out.println("Nenhum registro deletado");
				}
			}
		}
		catch(SQLException e)
		{
			System.out.print("Erro ao deletar registro" + id + "/n");
		}
	}
}
