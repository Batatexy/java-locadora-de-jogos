package src;

import java.util.ArrayList;

import src.model.consoles;
import src.model.jogos;

public class variaveis 
{
	public static ArrayList<consoles> consoles;
	public static ArrayList<jogos> jogos;
	
	//Metodo main
	public static boolean repetirTabela = true, repetirCodigo = true;
	
	//Peculiaridades
	public static int distribuidoraIntJogos = "Distribuidora".length();
	public static int fabricanteIntConsoles = "Fabricante ".length();
	public static int geracaoIntConsoles = "Geração ".length();
	
	public static String opcao, opcaoSelect = "3";
	
	public static String 
	linhaCima = "_", linhaBaixo = "‾", 
	finalCima = "", finalBaixo = "";
	
	//Jogos
	public static int 
	tamanhoIDJogos = 3, tamanhoNomeJogos, tamanhoDesenvolvedorJogos, tamanhoDistribuidoraJogos = distribuidoraIntJogos, 
	tamanhoGeneroJogos, tamanhoAnoJogos = "Ano de Publicação  ".length(), tamanhoConsoleJogos = "Console  ".length();
	
	//Consoles
	public static int 
	tamanhoIDConsoles = 3, tamanhoNomeConsoles, tamanhoFabricanteConsoles, tamanhoGeracaoConsoles = geracaoIntConsoles, 
	tamanhoAnoConsoles = "Ano de Lançamento  ".length();
}
