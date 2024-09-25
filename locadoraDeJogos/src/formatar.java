package src;

public class formatar
{
	public static String espaco, caracter = " │ ", caracterCenter = "│ ";
	
	public static String alterarNomes(String nomeColuna) 
	{
 	   //Palavras com acento:
 	   switch (nomeColuna)
 	   {
	   	   	case "id":
	   	    {
	   	    	nomeColuna = "ID";
	   	    	break;
	   	    }
   	    
 	   	   	case "genero":
 	   	    {
 	   	    	nomeColuna = "Gênero";
 	   	    	break;
 	   	    }
 	   	    
 	   	   	case "ano":
 	   	    {
 	   	    	nomeColuna = "Ano de Lançamento";
 	   	    	break;
 	   	    }
 	   	    
 	   	   	case "geracao":
 	   	    {
 	   	    	nomeColuna = "Geração";
 	   	    	break;
 	   	    }
 	   	    
 	   	   	case "dataNascimento":
 	   	    {
 	   	    	nomeColuna = "Data de Nascimento";
 	   	    	break;
 	   	    }
 	   	
 	   	   	case "funcionario":
 	   	    {
 	   	    	nomeColuna = "Funcionário";
 	   	    	break;
 	   	    }
 	   	    
 	   	
 	   	    default:
 	   	    	break;
 	   }
 	   return nomeColuna;
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
	
	public static String criarLinha(String texto, int soma, int colunas) 
	{
		String linha = texto;
		int mult = (int) (2.5 * colunas);
		
		for (int i=0; i<soma + mult;i++)
		{
			linha += texto;
		}
		return linha;
	}
}
