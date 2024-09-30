package classesAuxiliares;

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
	   	    
	   	   	case "cpf":
	   	    {
	   	    	nomeColuna = "CPF";
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
	
	public static void criarLinha(int posicao, int[] tamanhoColunas, int columnCount) 
	{
		String linha="";
        int tamanhoLinha=-2;
        
        //Definir tamanho da Linha superior e inferior da tabela:
        for (int i = 1; i <= columnCount; i++)
        {
    		for (int j=0; j<tamanhoColunas[i]+2;j++)
    		{
    			if (posicao == 4)
    				linha += " ";
    			else
    				linha += variaveis.linha;
    		}
    		
    		if (i == columnCount)
    		{
	    		if (posicao == 1)
					linha+="┐";
	    		else if (posicao == 2)
					linha+="┤";
				else if (posicao == 3)
					linha+="┘";
				else if (posicao == 4)
					linha+=variaveis.caracterVertical;
    		}
    		else
    		{
	    		if (posicao == 1)
					linha+="┬";
	    		else if (posicao == 2)
					linha+="┼";
				else if (posicao == 3)
					linha+="┴";
				else if (posicao == 4)
					linha+=variaveis.caracterVertical;
    		}
    		
        }

        
        System.out.println(linha);
        
		

		
	}

}
