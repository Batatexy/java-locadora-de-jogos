package src.model;

public class consoles 
{
	//os atributos da tabela livros
	private String nome, fabricante;
	private int id, ano, geracao;
	
	//apenas o nosso construtor
	public consoles 
	(	
		int id, 
		String nome,
		String fabricante,
		int geracao,
		int ano
	) 
	{
		this.id = id;
		this.nome = nome;
		this.fabricante = fabricante;
		this.geracao = geracao;
		this.ano = ano;
	}

	//aqui apenas os get e set
	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getFabricante() 
	{
		return fabricante;
	}

	public void setFabricante(String fabricante) 
	{
		this.fabricante = fabricante;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getAno() 
	{
		return ano;
	}

	public void setAno(int ano) 
	{
		this.ano = ano;
	}

	public int getGeracao() 
	{
		return geracao;
	}

	public void setGeracao(int geracao) 
	{
		this.geracao = geracao;
	}
}
