package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Marca;

public class MarcaDto {
	private boolean       ok;
    private String        mensagem;
    private Marca       Marca;
    private List<Marca> lista;


    public MarcaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public MarcaDto(boolean pOk, String pMensagem, Marca pMarca)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Marca = pMarca;
    }

    public MarcaDto(boolean pOk, String pMensagem, List<Marca> pLista)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        lista = pLista;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean pOk)
    {
        ok = pOk;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String pMensagem)
    {
        mensagem = pMensagem;
    }

    public Marca getMarca()
    {
        return Marca;
    }

    public void setMarca(Marca pMarca)
    {
        Marca = pMarca;
    }

    public List<Marca> getLista()
    {
        return lista;
    }

    public void setLista(List<Marca> pLista)
    {
        lista = pLista;
    }
}
