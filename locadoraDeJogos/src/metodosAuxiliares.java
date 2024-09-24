package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import src.data.DALlocadora;

public class metodosAuxiliares 
{
	public static String espaco, caracter = " │ ", caracterCenter = "│ ";
	
	public static void mostrarTabela(Connection connection) 
	{
		switch (locadora.tabelaAtual)
		{
			case "Jogos":
			{
				mostrar.jogos(connection);
				break;
			}
			
			case "Consoles":
			{
				mostrar.consoles(connection);
				break;
			}
			
			default:
				break;
		}
	}
	
	public static void escolherOpcoesForaDasTabelas(Connection connection) 
	{
		System.out.println("Escolha umas das tabelas a seguir:");
		System.out.println("0. Sair");
		System.out.println("1. Jogos");
		System.out.println("2. Consoles");
		System.out.println(variaveis.opcaoSelect + ". Consultas SQL");
		
		Scanner scanner = new Scanner(System.in);
		variaveis.opcao = scanner.nextLine(); // que é usado pra ler string
		
		//a partir daqui, apenas as chamadas
		// das funções de banco de dados
		switch (variaveis.opcao)
		{
			case "1":
			{
				//mudar tabela para jogos
				locadora.tabelaAtual = "Jogos";
				break;
			}
			
			case "2":
			{
				//mudar tabela para jogos
				locadora.tabelaAtual = "Consoles";
				break;
			}
			
			case "3":
			{
				//mudar tabela para jogos
				DALlocadora.select();
				break;
			}
			
			default:
			{
				variaveis.repetirCodigo=false;
				break;
			}
		}
	}
	
	public static void escolherOpcaoDentroDaTabela(Connection connection) 
	{
		System.out.println("Escolha umas das opções a seguir:");
		System.out.println("0. Voltar");
		System.out.println("1. Inserir em " + locadora.tabelaAtual);
		System.out.println("2. Alterar em " + locadora.tabelaAtual);
		System.out.println("3. Remover em " + locadora.tabelaAtual);
		
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
			
			default:
			{
				System.out.println("");
				variaveis.repetirTabela=false;
				break;
			}
		}
	}
	
	public static String gerarEspacos(String nome, int tamanhoIndividual, String alinhamento) 
	{
		int tamanhoLength = nome.length();
		int tamanho = tamanhoIndividual - tamanhoLength;
		int soma=0;
		
		switch (alinhamento)
		{
			case "left":
			{
				//Ele coloca o nome, depois soma " " repetidas vezes até chegar no fim da coluna
				espaco = nome;
				for (int i=0; i<tamanho;i++)
				{
					espaco += " ";
				}
				espaco += caracter;
				break;
			}
			
			case "center":
			{
				espaco = " ";
				
				//Verificar se é impar, pois na divisão de 2, ficaria sobrando um espaço
				if (tamanho % 2 != 0)
					soma = 1;
					
				//Mesmo sistema de somar " ", porem, faz metade, adiciona o nome e soma o resto
				for (int i=0; i<tamanho/2;i++)
				{
					espaco += " ";
				}
				
				espaco+=nome;
				
				for (int i=0; i<tamanho/2 + soma;i++)
				{
					espaco += " ";
				}
				espaco += caracterCenter;
				break;
			}
			
			case "right":
			{
				//Funciona igual o left, porem ao contrario, ele poem os " " e depois o nome
				espaco = "";
				for (int i=0; i<tamanho;i++)
				{
					espaco += " ";
				}
				
				espaco += nome +  caracter;
				break;
			}
			
			default:
				espaco = "";
				break;
		}
		
		return espaco;
	}
	
	public static String criarLinha(String texto, String textoFinal, int soma, int colunas) 
	{
		String linha = texto;
		int mult = (int) (2.5 * colunas);
		for (int i=0; i<soma + mult;i++)
		{
			linha += texto;
		}
		return linha + textoFinal;
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
	public static void verificarIDLivre(Connection connection, String coluna) throws SQLException
	{
		int idOne = 1;
		
		while (validarID(connection, idOne)) 
		{
			idOne++;
		}
		
		int idTwo = idOne + 1;
		
		while (validarID(connection, idTwo)) 
		{
			idTwo++;
		}
		
		int idThree = idTwo + 1;
		
		while (validarID(connection, idThree)) 
		{
			idThree++;
		}
		
		System.out.println("IDs Livres: " + idOne + ", " + idTwo + ", " + idThree);
	}
	
	public static void limparTerminal() 
	{
		//Aparenta não funcionar
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
}
