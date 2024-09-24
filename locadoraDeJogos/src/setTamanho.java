package src;

public class setTamanho 
{
	//Métodos que devem ser sempre monitorados
	public static void reset() 
	{
		//Resetar todos os valores:
		variaveis.tamanhoIDJogos = 3;
		variaveis.tamanhoNomeJogos = 0; 
		variaveis.tamanhoDesenvolvedorJogos = 0; 
		variaveis.tamanhoDistribuidoraJogos = variaveis.distribuidoraIntJogos; 
		variaveis.tamanhoGeneroJogos = 0;
		
		variaveis.tamanhoIDConsoles = 3;
		variaveis.tamanhoNomeConsoles = 0;
		variaveis.tamanhoFabricanteConsoles = variaveis.fabricanteIntConsoles;
	}
	
	public static void jogos(int idJogos, String nomeJogos, String desenvolvedorJogos, String distribuidoraJogos, String generoJogos)
	{
		int intNome = nomeJogos.length();
		int intDesenvolvedor = desenvolvedorJogos.length();
		int intDistribuidora = distribuidoraJogos.length();
		int intGenero = generoJogos.length();
		
		String stringId = idJogos + " ";
		int intId = stringId.length();

		if (intId>variaveis.tamanhoIDJogos)
			variaveis.tamanhoIDJogos = intId;
		
		if (intNome>variaveis.tamanhoNomeJogos )
			variaveis.tamanhoNomeJogos  = intNome;
		
		if (intDesenvolvedor>variaveis.tamanhoDesenvolvedorJogos )
			variaveis.tamanhoDesenvolvedorJogos  = intDesenvolvedor;
		
		if (intDistribuidora>variaveis.tamanhoDistribuidoraJogos )
			variaveis.tamanhoDistribuidoraJogos  = intDistribuidora;
		
		if (intGenero>variaveis.tamanhoGeneroJogos )
			variaveis.tamanhoGeneroJogos  = intGenero;
	}
	
	public static void consoles(int idConsoles, String nomeConsoles, String fabricanteConsoles)
	{
		int intNome = nomeConsoles.length();
		int intFabricante = fabricanteConsoles.length();
		
		String stringId = idConsoles + " ";
		int intId = stringId.length();

		if (intId>variaveis.tamanhoIDConsoles)
			variaveis.tamanhoIDConsoles = intId;
		
		if (intNome>variaveis.tamanhoNomeConsoles)
			variaveis.tamanhoNomeConsoles = intNome;
		
		if (intFabricante>variaveis.tamanhoFabricanteConsoles)
			variaveis.tamanhoFabricanteConsoles = intFabricante;
	}
}
