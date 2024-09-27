package src;

public class formatar
{
	public static String texto;
	
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
	
	public static String gerarEspacos(String nome, int maiorTamanho, String alinhamento) 
	{
		int tamanhoNome = nome.length();
		int tamanhoRestante = maiorTamanho - tamanhoNome;
		int soma=0;
		
		switch (alinhamento)
		{
			case "left":
			{
				//Ele coloca o nome, depois soma " " repetidas vezes até chegar no fim da coluna
				texto = " " + nome;
				for (int i=0; i<tamanhoRestante;i++)
				{
					texto += " ";
				}
				texto += " " + variaveis.caracterVertical;
				break;
			}
			
			case "center":
			{
				texto = " ";
				
				//Verificar se é impar, pois na divisão de 2, ficaria sobrando um espaço
				if (tamanhoRestante % 2 != 0)
					soma = 1;
					
				//Mesmo sistema de somar " ", porem, faz metade, adiciona o nome e soma o resto
				for (int i=0; i<tamanhoRestante/2 ;i++)
				{
					texto += " ";
				}
				
				texto+=nome;
				
				for (int i=0; i<tamanhoRestante/2 + soma;i++)
				{
					texto += " ";
				}
				texto += " " + variaveis.caracterVertical;
				break;
			}
			
			case "right":
			{
				//Funciona igual o left, porem ao contrario, ele poem os " " e depois o nome
				texto = " ";
				for (int i=0; i<tamanhoRestante;i++)
				{
					texto += " ";
				}
				
				texto += nome + " " + variaveis.caracterVertical;
				break;
			}
			
			default:
				texto = "";
				break;
		}
		
		return texto;
	}
	
	public static String criarLinha(String texto, int tamanho) 
	{
		String linha = texto;
		
		for (int i=0; i<tamanho;i++)
		{
			linha += texto;
		}
		return linha;
	}
}
