package br.com.maiscadastros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Usuario
{
    // Atributos
	
	@Id
	@Column private int    id;
	@Column private String email;
	@Column private String senha;
	@Column private String nome;

    // Construtores
    public Usuario()
    {
        super();
    }

    public Usuario(int pId, String pEmail, String pSenha, String pNome)
    {
        super();
        setId(pId);
        setEmail(pEmail);
        setSenha(pSenha);
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String pEmail)
    {
        email = pEmail;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String pSenha)
    {
        senha = pSenha;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getEmail());
        tBuilder.append(", ");
        tBuilder.append(getSenha());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append("]");
        return tBuilder.toString();
    }

}
