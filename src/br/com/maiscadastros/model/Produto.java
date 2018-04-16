package br.com.maiscadastros.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto
{
    // Atributos
	@Id
	@Column private int         id;
	@Column private String      nome;
	@Column private String      descricao;
	@Column private LocalDate   dataValidade;
	@Column private int         idLoja;
	@Column private int         idSetor;
	@Column private int         idMarca;

 // Construtores
    public Produto()
    {
        super();
    }

    public Produto(int pId, String pNome, String pDescricao, LocalDate pDataValidade, int pIdLoja, int pIdSetor, int pIdMarca)
    {
        super();
        setId(pId);
        setNome(pNome);
        setDescricao(pDescricao);
        setDataValidade(pDataValidade);
        setIdLoja(pIdLoja);
        setIdSetor(pIdSetor);
        setIdMarca(pIdMarca);
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String pDescricao)
    {
        descricao = pDescricao;
    }

    public LocalDate getDataValidade()
    {
        return dataValidade;
    }

    public void setDataValidade(LocalDate pDataValidade)
    {
        dataValidade = pDataValidade;
    }

    public int getIdLoja()
    {
        return idLoja;
    }

    public void setIdLoja(int pIdLoja)
    {
        idLoja = pIdLoja;
    }
    
    public int getIdSetor()
    {
        return idSetor;
    }

    public void setIdSetor(int pIdSetor)
    {
        idSetor = pIdSetor;
    }
    
    public int getIdMarca()
    {
        return idMarca;
    }

    public void setIdMarca(int pIdMarca)
    {
    	idMarca = pIdMarca;
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
        tBuilder.append(", ");
        tBuilder.append(getDescricao());
        tBuilder.append(", ");
        tBuilder.append(getDataValidade());
        tBuilder.append(", ");
        tBuilder.append(getIdLoja());
        tBuilder.append(", ");
        tBuilder.append(getIdSetor());
        tBuilder.append(", ");
        tBuilder.append(getIdMarca());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
