package src;

public class printCabecalho
{
	public static void jogos() 
	{
		System.out.println
		(
			metodosAuxiliares.gerarEspacos("ID", variaveis.tamanhoIDJogos, "center") +
			metodosAuxiliares.gerarEspacos("Nome", variaveis.tamanhoNomeJogos , "center") + 
			metodosAuxiliares.gerarEspacos("Desenvolvedor", variaveis.tamanhoDesenvolvedorJogos , "center") +
			metodosAuxiliares.gerarEspacos("Distribuidora", variaveis.tamanhoDistribuidoraJogos , "center") +
			metodosAuxiliares.gerarEspacos("Genero", variaveis.tamanhoGeneroJogos , "center") +
			metodosAuxiliares.gerarEspacos("Ano de Publicação ", variaveis.tamanhoAnoJogos , "center") +
			metodosAuxiliares.gerarEspacos("Console ", variaveis.tamanhoConsoleJogos, "center")
		);
	}
	
	public static void consoles() 
	{
		System.out.println
		(
			metodosAuxiliares.gerarEspacos("ID", variaveis.tamanhoIDConsoles, "center") +
			metodosAuxiliares.gerarEspacos("Nome", variaveis.tamanhoNomeConsoles , "center") + 
			metodosAuxiliares.gerarEspacos("Fabricante ", variaveis.tamanhoFabricanteConsoles , "center") +
			metodosAuxiliares.gerarEspacos("Geracao ", variaveis.tamanhoGeracaoConsoles , "center") +
			metodosAuxiliares.gerarEspacos("Ano de Lançamento ", variaveis.tamanhoAnoConsoles , "center")
		);
	}
}
