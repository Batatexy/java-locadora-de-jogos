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
		
		//Alterar pra quando tiver mais tabelas, esta ser a ultima opção
		System.out.println("3. Consultas SQL");
		
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
			
			//Alterar pra quando tiver mais tabelas, esta ser a ultima opção
			case "3":
			{
				//mudar tabela para jogos
				variaveis.opcaoSelectBoolean=true;
				DALlocadora.select();
				//DALlocadora.select(variaveis.selectJogos);
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
}
