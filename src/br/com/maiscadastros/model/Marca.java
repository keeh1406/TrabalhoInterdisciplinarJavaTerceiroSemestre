package br.com.maiscadastros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABELA_MARCA")
public class Marca {
	// Atributos
	
	@Id
	@Column (name = "Codigo_Marca")
	private int id;
	
	@Column (name = "Nome_Marca")
	private String nome;
    
 // Construtores
    public Marca()
    {
        super();
    }

    public Marca(int pId, String pNome)
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
