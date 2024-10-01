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
		
		if (locadora.tabelaAtual == "Alugueis")
			System.out.println("5. Verificar clientes");
		
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
				if (locadora.tabelaAtual == "Alugueis")
				{
					String sql = "select clientes.nome as \"Nome do Cliente\", jogos.nome as \"Jogo alugado\", " +
							"funcionarios.nome as \"Funcionário Locatário\" " +
							"from alugueis " +
							"join clientes on alugueis.clienteID = clientes.id " +
							"join jogos on alugueis.jogoID = jogos.id " +
							"join funcionarios on alugueis.funcionarioID = funcionarios.id";
					DALlocadora.select(sql);
					
					System.out.println("Digite qualquer tecla para continuar");
					scanner.nextLine();
					
					variaveis.opcaoColuna=1000;
				}
				else
				{
					variaveis.repetirTabela=false;
				}
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

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						DALlocadora.selectComparativo("id");
						break;
					}
					
					case 2:
					{
						DALlocadora.selectComparativo("nome");
						break;
					}
					
					case 3:
					{
						DALlocadora.selectComparativo("desenvolvedor");
						break;
					}
					
					case 4:
					{
						DALlocadora.selectComparativo("distribuidora");
						break;
					}
					
					case 5:
					{
						DALlocadora.selectComparativo("genero");
						break;
					}
					
					case 6:
					{
						DALlocadora.selectComparativo("ano");
						break;
					}
					
					case 7:
					{
						DALlocadora.selectComparativo("unidade");
						break;
					}
					
					case 8:
					{
						DALlocadora.selectComparativo("console");
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

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						DALlocadora.selectComparativo("id");
						break;
					}
					
					case 2:
					{
						DALlocadora.selectComparativo("nome");
						break;
					}
					
					case 3:
					{
						DALlocadora.selectComparativo("fabricante");
						break;
					}
					
					case 4:
					{
						DALlocadora.selectComparativo("geracao");
						break;
					}
					
					case 5:
					{
						DALlocadora.selectComparativo("ano");
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
				
				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						DALlocadora.selectComparativo("id");
						break;
					}
					
					case 2:
					{
						DALlocadora.selectComparativo("cpf");
						break;
					}
					
					case 3:
					{
						DALlocadora.selectComparativo("nome");
						break;
					}
					
					case 4:
					{
						DALlocadora.selectComparativo("dataNascimento");
						break;
					}
					
					case 5:
					{
						DALlocadora.selectComparativo("telefone");
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
				
				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						DALlocadora.selectComparativo("id");
						break;
					}
					
					case 2:
					{
						DALlocadora.selectComparativo("cpf");
						break;
					}
					
					case 3:
					{
						DALlocadora.selectComparativo("nome");
						break;
					}
					
					case 4:
					{
						DALlocadora.selectComparativo("dataNascimento");
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

				variaveis.opcaoColuna = scanner.nextInt();
				scanner.nextLine();
				
				switch(variaveis.opcaoColuna)
				{
					case 1:
					{
						DALlocadora.selectComparativo("id");
						break;
					}
					
					case 2:
					{
						DALlocadora.selectComparativo("clienteID");
						break;
					}
					
					case 3:
					{
						DALlocadora.selectComparativo("jogoID");
						break;
					}
					
					case 4:
					{
						DALlocadora.selectComparativo("funcionarioID");
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
	}
	
	//Printar fim
}
