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

public class locadora
{
	// 1 - identificar onde que está o arquivo de BD
	public static String jdbcUrl = "jdbc:sqlite:/C:\\Users\\Gabriel Carrascosa\\eclipse-workspace\\locadoraDeJogos\\locadoraDeJogos.db";
	public static String tabelaAtual = "";
	
	public static void main(String[] args) 
	{
		try // o java obriga a usar try em banco de dados
		{ // o main tem que estar assim!
			// 2 - conectar com o BD
			Connection connection = DriverManager.getConnection(jdbcUrl);

			while (variaveis.repetirCodigo)
			{
				variaveis.repetirTabela=true;
				metodosAuxiliares.escolherOpcoesForaDasTabelas(connection);
				
				while (variaveis.repetirCodigo && variaveis.repetirTabela && variaveis.opcao != "3")
				{
					//mostrar o estado inicial do BD
					metodosAuxiliares.mostrarTabela(connection);
					metodosAuxiliares.escolherOpcaoDentroDaTabela(connection);
					metodosAuxiliares.limparTerminal();
				}
			}
			System.out.println("Fim do código");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
}
