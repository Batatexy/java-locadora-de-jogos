package src;

import java.sql.Connection;
import java.util.Scanner;

import src.data.DALlocadora;

public class printar 
{
	public static void opcoesForaTabela(Connection connection) 
	{
		System.out.println("Escolha umas das tabelas a seguir:");
		System.out.println("0. Sair");
		System.out.println("1. Jogos");
		System.out.println("2. Consoles");
		System.out.println("3. Clientes");
		System.out.println("4. Funcionarios");
		System.out.println("5. Alugueis");
		
		//Alterar pra quando tiver mais tabelas, esta deve ser a ultima opção
		System.out.println("9. Consultas SQL");
		
		Scanner scanner = new Scanner(System.in);
		variaveis.opcao = scanner.nextLine(); // que é usado pra ler string
		
		//a partir daqui, apenas as chamadas
		// das funções de banco de dados
		switch (variaveis.opcao)
		{
			case "1":
			{
				//mudar tabela para Jogos
				locadora.tabelaAtual = "Jogos";
				break;
			}
			
			case "2":
			{
				//mudar tabela para Consoles
				locadora.tabelaAtual = "Consoles";
				break;
			}
			
			case "3":
			{
				//mudar tabela para Clientes
				locadora.tabelaAtual = "Clientes";
				break;
			}
			
			case "4":
			{
				//mudar tabela para Funcionarios
				locadora.tabelaAtual = "Funcionarios";
				break;
			}
			
			case "5":
			{
				//mudar tabela para Alugueis
				locadora.tabelaAtual = "Alugueis";
				break;
			}
			
			//Alterar pra quando tiver mais tabelas, esta deve ser a ultima opção
			case "9":
			{
				variaveis.opcaoSelectBoolean=true;
				DALlocadora.selectScanner();
				break;
			}
			
			default:
			{
				variaveis.repetirCodigo=false;
				break;
			}
		}
	}
	
	public static void opcoesDentroTabela(Connection connection) 
	{
		System.out.println("Escolha umas das opções a seguir:");
		System.out.println("0. Voltar");
		System.out.println("1. Inserir em " + locadora.tabelaAtual);
		System.out.println("2. Alterar em " + locadora.tabelaAtual);
		System.out.println("3. Remover em " + locadora.tabelaAtual);
		System.out.println("4. Select Comparativo");
		System.out.println("5. Select InnerJoin");
		System.out.println("6. Remover 1 Unidade em Id 6");
		System.out.println("7. Adicionar 1 Unidade em Id 6");
		
		Scanner scanner = new Scanner(System.in);
		String opcao = scanner.nextLine(); // que é usado pra ler string
		
		//a partir daqui, apenas as chamadas
		// das funções de banco de dados
		switch (opcao)
		{
			case "1":
			{
				//inserir um dado novo
				DALlocadora.inserirDado(connection);
				break;
			}
				
			case "2":
			{
				//alterar dados
				DALlocadora.atualizarDados(connection);
				break;
			}
				
			case "3":
			{
				//deletar um dado pelo ID
				DALlocadora.deletarDados(connection);
				break;
			}
			
			case "4":
			{
				opcoesDentroDentroTabela(opcao);
				break;
			}
			
			case "5":
			{

				opcoesDentroDentroTabela(opcao);
				break;
			}
			
			case "6":
			{
				//Remover
				DALlocadora.updateAutomatico(connection, "unidade", 1, -1);
				break;
			}
			
			case "7":
			{
				//Adicionar
				DALlocadora.updateAutomatico(connection, "unidade", 1, +1);
				break;
			}
			
			default:
			{
				System.out.println("");
				variaveis.repetirTabela=false;
				break;
			}
		}
	}
	
	public static void opcoesDentroDentroTabela(String opcao)
	{
		Scanner scanner = new Scanner(System.in);
		
		//Select dinamicos
		switch(locadora.tabelaAtual)
		{
			case "Jogos":
			{
				System.out.println("Escolha umas das opções a seguir:");
				System.out.println("0. Voltar");
				System.out.println("1. ID");
				System.out.println("2. Nome");
				System.out.println("3. Desenvolvedor");
				System.out.println("4. Distribuidora");
				System.out.println("5. Gênero");
				System.out.println("6. Ano de Lançamento");
				System.out.println("7. Console");
				System.out.println("8. Unidade");

				String opcoes = scanner.nextLine(); // que é usado pra ler string
				
				switch(opcoes)
				{
					case "1":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("id");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "2":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("nome");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "3":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("desenvolvedor");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "4":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("distribuidora");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "5":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("genero");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "6":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("ano");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "7":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("console");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					case "8":
					{
						//Comparativo
						if (opcao == "4")
						{
							DALlocadora.selectComparativo("unidade");
						}
						//InnerJoin
						else if (opcao == "5")
						{
							
						}
						break;
					}
					
					default:
						break;
				}
				
				System.out.println("Digite qualquer tecla para continuar");
				String continuar = scanner.nextLine();
			}
			
			
			
			default:
				break;
		}
		
		
	}
	

	
}
