package src;

/*
 * 0 - adicionar o JAR do JDBC do SQLite (cfg) 1 - identificar onde que está o
 * arquivo de BD 2 - conectar com o BD 3 - definir qual é o SQL que vai ser
 * executado 4 - abrir a conexão com o BD 5 - executar o SQL 6 - acessar e
 * exibir os resultados
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import classesAuxiliares.printar;
import classesAuxiliares.variaveis;
import src.data.DALlocadora;
import src.data.verificar;

public class locadora
{
	// 1 - identificar onde que está o arquivo de BD
	public static String jdbcUrl = "jdbc:sqlite:/C:\\Users\\Gabriel Carrascosa\\eclipse-workspace\\locadora-de-jogos\\locadoraDeJogos\\locadoraDeJogos.db";
	public static String tabelaAtual = "";
	
	public static void main(String[] args)
	{
		Connection connection = null;
		
		try // o java obriga a usar try em banco de dados
		{ // o main tem que estar assim!
			// 2 - conectar com o BD
			connection = DriverManager.getConnection(jdbcUrl);

			while (variaveis.repetirCodigo)
			{
				variaveis.repetirTabela=true;
				variaveis.opcaoSelectBoolean=false;
				printar.opcoesForaTabela(connection);
				
				while (variaveis.repetirCodigo && variaveis.repetirTabela && !variaveis.opcaoSelectBoolean)
				{
					//mostrar o estado inicial do BD, agora o proprio metodo select, sem ser a opção
					//selectScanner, consegue verificar qual a tabela está e printa ela:
					String sql = "SELECT * FROM " + locadora.tabelaAtual;
					DALlocadora.select(sql);
					//mostrar opções dentro da tabela: inserir, atualizar ou remover dados:
					printar.opcoesDentroTabela(connection);
				}
			}
			System.out.println("Fim do código");
			
			verificar.fechar(connection);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			verificar.fechar(connection);
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
			verificar.fechar(connection);
		}
		finally
		{
			verificar.fechar(connection);
		}
	}

	//Main fim
}
