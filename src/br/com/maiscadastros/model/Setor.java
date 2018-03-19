package br.com.maiscadastros.model;

public class Setor {
	// Atributos
		private int id;
	    private String nome;
	    
	 // Construtores
	    public Setor()
	    {
	        super();
	    }

	    public Setor(int pId, String pNome)
	    {
	        super();
	        setId(pId);
	        setNome(pNome);
	    }
	 // Métodos de acesso
	    public int getId()
	    {
	        return id;
	    }

	    public void setId(int pId)
	    {
	        id = pId;
	    }

	    public String getNome()
	    {
	        return nome;
	    }

	    public void setNome(String pNome)
	    {
	        nome = pNome;
	    }

	    // Métodos gerais
	    @Override
	    public String toString()
	    {
	        StringBuilder tBuilder = new StringBuilder();
	        tBuilder.append("[");
	        tBuilder.append(getId());
	        tBuilder.append(", ");
	        tBuilder.append(getNome());
	        tBuilder.append("]");
	        return tBuilder.toString();
	    }
	}
