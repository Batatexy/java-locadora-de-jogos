package src;

import java.sql.Connection;
import java.util.ArrayList;

import src.data.getBDs;
import src.model.consoles;
import src.model.jogos;

public class mostrar 
{
	//método de exibição dos jogos
	public static void jogos(Connection connection) 
	{
		//chamada do método getJogos da classe DAL
		getBDs.getBD(connection);
		
		setTamanho.reset();
		
		//For para verificar quais os maiores nomes em cada coluna
        for (jogos jogo : variaveis.jogos)
        {
        	setTamanho.jogos(jogo.getId(), jogo.getNome(), jogo.getDesenvolvedor(), jogo.getDistribuidora(), jogo.getGenero());
        }

		int somaJogos = variaveis.tamanhoIDJogos + variaveis.tamanhoNomeJogos  + variaveis.tamanhoDesenvolvedorJogos + 
				variaveis.tamanhoDistribuidoraJogos + variaveis.tamanhoConsoleJogos + variaveis.tamanhoAnoJogos + variaveis.tamanhoGeneroJogos;

		System.out.println(metodosAuxiliares.criarLinha(variaveis.linhaCima, variaveis.finalCima, somaJogos, 7));
        
        //Printar cabeçalho
		printCabecalho.jogos();
        
		//Printar jogos
		print.jogos(variaveis.jogos);
		
        System.out.println(metodosAuxiliares.criarLinha(variaveis.linhaBaixo, variaveis.finalBaixo, somaJogos, 7));
	}

	public static void consoles(Connection connection) 
	{
		//chamada do método getJogos da classe DAL
		getBDs.getBD(connection);
		
		setTamanho.reset();
		
		//For para verificar quais os maiores nomes em cada coluna
        for (consoles console : variaveis.consoles)
        {
        	setTamanho.consoles(console.getId(), console.getNome(), console.getFabricante());
        }

		int somaConsoles = variaveis.tamanhoIDConsoles + variaveis.tamanhoNomeConsoles + variaveis.tamanhoFabricanteConsoles + 
				variaveis.tamanhoGeracaoConsoles + variaveis.tamanhoAnoConsoles ;
		//Quantas colunas são
		System.out.println(metodosAuxiliares.criarLinha(variaveis.linhaCima, variaveis.finalCima, somaConsoles, 5));
        
        //Printar cabeçalho
		printCabecalho.consoles();
		
		print.consoles(variaveis.consoles);
		
        System.out.println(metodosAuxiliares.criarLinha(variaveis.linhaBaixo, variaveis.finalBaixo, somaConsoles, 5));
	}
}
