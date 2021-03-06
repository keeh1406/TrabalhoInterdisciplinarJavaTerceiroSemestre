package br.com.maiscadastros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABELA_SETOR")
public class Setor {
	// Atributos
	@Id
	@Column (name = "Codigo_Setor")
	private int id;
	
	@Column (name = "Nome_Setor")
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
	 // M�todos de acesso
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

	    // M�todos gerais
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
