package classesAuxiliares;

import java.sql.Connection;
import java.util.Scanner;

import src.locadora;
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
		System.out.println("6. Consulta Personalizada SQL");
		
		Scanner scanner = new Scanner(System.in);
		variaveis.opcaoTabela = scanner.nextInt(); // que é usado pra ler string
		
		//a partir daqui, apenas as chamadas
		// das funções de banco de dados
		switch (variaveis.opcaoTabela)
		{
			case 1:
			{
				//mudar tabela para Jogos
				locadora.tabelaAtual = "Jogos";
				break;
			}
			
			case 2:
			{
				//mudar tabela para Consoles
				locadora.tabelaAtual = "Consoles";
				break;
			}
			
			case 3:
			{
				//mudar tabela para Clientes
				locadora.tabelaAtual = "Clientes";
				break;
			}
			
			case 4:
			{
				//mudar tabela para Funcionarios
				locadora.tabelaAtual = "Funcionarios";
				break;
			}
			
			case 5:
			{
				//mudar tabela para Alugueis
				locadora.tabelaAtual = "Alugueis";
				break;
			}
			
			//Alterar pra quando tiver mais tabelas, esta deve ser a ultima opção
			case 6:
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
		
		//Select Comparativo, não sei que nome devo colocar
		System.out.println("4. Consultar Tabela Por Valor ");
		System.out.println("5. Consulta InnerJoin");
		
		Scanner scanner = new Scanner(System.in);
		variaveis.opcaoDentroTabela = scanner.nextInt(); // que é usado pra ler string
		scanner.nextLine();
		
		//a partir daqui, apenas as chamadas
		// das funções de banco de dados
		switch (variaveis.opcaoDentroTabela)
		{
			case 1:
			{
				//inserir um dado novo
				DALlocadora.inserirDado(connection);
				break;
			}
				
			case 2:
			{
				//alterar dados
				DALlocadora.atualizarDados(connection);
				break;
			}
				
			case 3:
			{
				//deletar um dado pelo ID
				DALlocadora.deletarDados(connection);
				break;
			}
			
			case 4:
			{
				//select Comparativo
				while (variaveis.opcaoColuna!=0)
					opcoesDentroDentroTabela();
				
				variaveis.opcaoColuna=1000;
				break;
			}
			
			case 5:
			{
				while (variaveis.opcaoColuna!=0)
					opcoesDentroDentroTabela();
				
				variaveis.opcaoColuna=1000;
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
	
	public static void opcoesDentroDentroTabela()
	{
		Scanner scanner = new Scanner(System.in);
		
		//Select dinamicos
		switch(locadora.tabelaAtual)
		{
			case "Jogos":
			{
				//Opções de escolha para o selectComparativo
				if (variaveis.opcaoDentroTabela == 4)
				{
					System.out.println("Escolha umas das opções a seguir:");
					System.out.println("0. Voltar");
					System.out.println("1. ID");
					System.out.println("2. Nome");
					System.out.println("3. Desenvolvedor");
					System.out.println("4. Distribuidora");
					System.out.println("5. Gênero");
					System.out.println("6. Ano de Lançamento");
					System.out.println("7. Unidade");
					System.out.println("8. Console");
				}
				//Opções de escolha para o select InnerJoin
				else if (variaveis.opcaoDentroTabela == 5)
				{
					
				}

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						verificarSelect("id");
						break;
					}
					
					case 2:
					{
						verificarSelect("nome");
						break;
					}
					
					case 3:
					{
						verificarSelect("desenvolvedor");
						break;
					}
					
					case 4:
					{
						verificarSelect("distribuidora");
						break;
					}
					
					case 5:
					{
						verificarSelect("genero");
						break;
					}
					
					case 6:
					{
						verificarSelect("ano");
						break;
					}
					
					case 7:
					{
						verificarSelect("unidade");
						break;
					}
					
					case 8:
					{
						verificarSelect("console");
						break;
					}
					
					default:
						break;
				}
				
				break;
			}
			
			
			
			case "Consoles":
			{
				//Opções de escolha para o selectComparativo
				if (variaveis.opcaoDentroTabela == 4)
				{
					System.out.println("Escolha umas das opções a seguir:");
					System.out.println("0. Voltar");
					System.out.println("1. ID");
					System.out.println("2. Nome");
					System.out.println("3. Fabricante");
					System.out.println("4. Geração");
					System.out.println("5. Ano de Lançamento");
				}
				//Opções de escolha para o select InnerJoin
				else if (variaveis.opcaoDentroTabela == 5)
				{
					
				}

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						verificarSelect("id");
						break;
					}
					
					case 2:
					{
						verificarSelect("nome");
						break;
					}
					
					case 3:
					{
						verificarSelect("fabricante");
						break;
					}
					
					case 4:
					{
						verificarSelect("geracao");
						break;
					}
					
					case 5:
					{
						verificarSelect("ano");
						break;
					}
					
					default:
						break;
				}
				
				break;
			}
			
			
			
			
			
			
			
			
			case "Clientes":
			{
				//Opções de escolha para o selectComparativo
				if (variaveis.opcaoDentroTabela == 4)
				{
					System.out.println("Escolha umas das opções a seguir:");
					System.out.println("0. Voltar");
					System.out.println("1. ID");
					System.out.println("2. CPF");
					System.out.println("3. Nome");
					System.out.println("4. Data de Nascimento");
					System.out.println("5. Telefone");
				}
				//Opções de escolha para o select InnerJoin
				else if (variaveis.opcaoDentroTabela == 5)
				{
					
				}

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						verificarSelect("id");
						break;
					}
					
					case 2:
					{
						verificarSelect("cpf");
						break;
					}
					
					case 3:
					{
						verificarSelect("nome");
						break;
					}
					
					case 4:
					{
						verificarSelect("dataNascimento");
						break;
					}
					
					case 5:
					{
						verificarSelect("telefone");
						break;
					}
					
					default:
						break;
				}
				
				break;
			}
			
			
			
			
			
			
			
			
			case "Funcionarios":
			{
				//Opções de escolha para o selectComparativo
				if (variaveis.opcaoDentroTabela == 4)
				{
					System.out.println("Escolha umas das opções a seguir:");
					System.out.println("0. Voltar");
					System.out.println("1. ID");
					System.out.println("2. CPF");
					System.out.println("3. Nome");
					System.out.println("4. Data de Nascimento");
				}
				//Opções de escolha para o select InnerJoin
				else if (variaveis.opcaoDentroTabela == 5)
				{
					
				}
				
				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						verificarSelect("id");
						break;
					}
					
					case 2:
					{
						verificarSelect("cpf");
						break;
					}
					
					case 3:
					{
						verificarSelect("nome");
						break;
					}
					
					case 4:
					{
						verificarSelect("dataNascimento");
						break;
					}
					
					default:
						break;
				}
				
				break;
			}
			
			
			
			
			
			case "Alugueis":
			{
				//Opções de escolha para o selectComparativo
				if (variaveis.opcaoDentroTabela == 4)
				{
					System.out.println("Escolha umas das opções a seguir:");
					System.out.println("0. Voltar");
					System.out.println("1. ID");
					System.out.println("2. Cliente");
					System.out.println("3. Jogo");
					System.out.println("4. Funcionário");
				}
				//Opções de escolha para o select InnerJoin
				else if (variaveis.opcaoDentroTabela == 5)
				{
					
				}

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						verificarSelect("id");
						break;
					}
					
					case 2:
					{
						verificarSelect("cliente");
						break;
					}
					
					case 3:
					{
						verificarSelect("jogo");
						break;
					}
					
					case 4:
					{
						verificarSelect("funcionario");
						break;
					}
					
					default:
						break;
				}
				
				break;
			}
			
			
			
			
			
			
			
			
			
			
			
			default:
				break;
		}
		
		if (variaveis.opcaoColuna!=0)
		{
			System.out.println("Digite qualquer tecla para continuar");
			scanner.nextLine();
		}
		else
		{
			
		}
	}
	
	
	
	public static void verificarSelect(String coluna)
	{
		//Comparativo
		if (variaveis.opcaoDentroTabela == 4)
		{
			DALlocadora.selectComparativo(coluna);
		}
		//InnerJoin
		else if (variaveis.opcaoDentroTabela == 5)
		{
			
		}
	}

	//Printar fim
}
