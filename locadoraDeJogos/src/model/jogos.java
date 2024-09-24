package src.model;

public class jogos 
{
	//os atributos da tabela livros
	private String nome, desenvolvedor, distribuidora, genero;
	private int id, ano, console;
	
	//apenas o nosso construtor
	public jogos 
	(	
		int id, 
		String nome,
		String desenvolvedor,
		String distribuidora,
		String genero,
		int ano,
		int console
	) 
	{
		this.id = id;
		this.nome = nome;
		this.desenvolvedor = desenvolvedor;
		this.distribuidora = distribuidora;
		this.genero= genero;
		this.ano = ano;
		this.console = console;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	//aqui apenas os get e set
	public int getId() 
	{
		return this.id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNome() 
	{
		return this.nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getDesenvolvedor() 
	{
		return this.desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) 
	{
		this.desenvolvedor = desenvolvedor;
	}

	public String getDistribuidora() 
	{
		return this.distribuidora;
	}

	public void setDistribuidora(String distribuidora) 
	{
		this.distribuidora = distribuidora;
	}

	public int getAno() 
	{
		return this.ano;
	}

	public void setAno(int ano) 
	{
		this.ano = ano;
	}
	
	public int getConsole() 
	{
		return this.console;
	}

	public void setConsole(int console) 
	{
		this.console = console;
	}
}
