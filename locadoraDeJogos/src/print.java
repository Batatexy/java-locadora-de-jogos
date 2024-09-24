package src;

import java.util.ArrayList;

import src.model.consoles;
import src.model.jogos;

public class print 
{
	public static void jogos(ArrayList<jogos> jogos)
	{
		//criar um for para iterar a lista de jogos resultante do método que acessa o BD     
        for (jogos jogo : jogos) //for each / enhanced for
        { 
    		System.out.println
    		(
    			metodosAuxiliares.gerarEspacos(jogo.getId() + " ", variaveis.tamanhoIDJogos, "center")    +
    			metodosAuxiliares.gerarEspacos(jogo.getNome()  , variaveis.tamanhoNomeJogos , "left")  +
    			metodosAuxiliares.gerarEspacos( jogo.getDesenvolvedor()  , variaveis.tamanhoDesenvolvedorJogos , "left")   +
    			metodosAuxiliares.gerarEspacos(jogo.getDistribuidora() , variaveis.tamanhoDistribuidoraJogos , "left") +
    			metodosAuxiliares.gerarEspacos(jogo.getGenero() , variaveis.tamanhoGeneroJogos , "left") +
    			metodosAuxiliares.gerarEspacos(jogo.getAno() + " ", variaveis.tamanhoAnoJogos, "right") +
    			metodosAuxiliares.gerarEspacos(jogo.getConsole() + " ", variaveis.tamanhoConsoleJogos, "right")
    		);
        }
	}
	
	public static void consoles(ArrayList<consoles> consoles)
	{
		//criar um for para iterar a lista de jogos resultante do método que acessa o BD     
        for (consoles console : consoles) //for each / enhanced for
        { 
    		System.out.println
    		(
    			metodosAuxiliares.gerarEspacos(console.getId() + " ", variaveis.tamanhoIDConsoles, "center")    +
    			metodosAuxiliares.gerarEspacos(console.getNome()  , variaveis.tamanhoNomeConsoles , "left")  +
    			metodosAuxiliares.gerarEspacos( console.getFabricante()  , variaveis.tamanhoFabricanteConsoles , "left")   +
    			metodosAuxiliares.gerarEspacos(console.getGeracao() + "º ", variaveis.tamanhoGeracaoConsoles , "left") +
    			metodosAuxiliares.gerarEspacos(console.getAno() + " ", variaveis.tamanhoAnoConsoles, "left")
    		);
        }
	}
}
