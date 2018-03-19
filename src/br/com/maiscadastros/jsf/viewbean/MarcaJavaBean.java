package br.com.maiscadastros.jsf.viewbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.maiscadastros.controller.MarcaController;
import br.com.maiscadastros.dto.MarcaDto;
import br.com.maiscadastros.model.Marca;

public class MarcaJavaBean {
    // Atributos - Valores dos componentes visuais
	private Integer id;
	private String nome;
    private boolean edicao;
    private String  tela;
    private List<Marca> listaMarca;
    
    @PostConstruct
    public void init()
    {
        Marca tMarca = (Marca) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("MARCA");
        if (tMarca != null)
        {
            id = tMarca.getId();
            nome = tMarca.getNome();
            edicao = true;
        }
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public List<Marca> getListaMarca() {
		return listaMarca;
	}

	public void setListaMarca(List<Marca> listaMarca) {
		this.listaMarca = listaMarca;
	}

	// Métodos da Controller
    public String limpar()
    {
        id = null;
        nome = null;
        edicao = false;

        return tela;
    }
	
	// Métodos da Controller
	public String cadastrar()
	{
	    System.out.println("MarcaVB - Cadastrar : " + this);

	    Marca tMarca = new Marca();
	    tMarca.setNome(nome);

	    MarcaController tController = new MarcaController();

	    MarcaDto tDto = tController.cadastrarMarca(tMarca);
	    if (tDto.isOk())
	    {
	        // Ok, incluído
	        id = tDto.getMarca().getId();

	        // Colocando a mensagem do sistema
	        FacesContext.getCurrentInstance().addMessage(null,
	                        new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
	    }
	    else
	    {
	        // Erro de inclusão

	        // Colocando a mensagem do sistema
	        FacesContext.getCurrentInstance().addMessage(null,
	                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
	    }
	    return null;
	}
	
	   public String alterar()
	    {
	        System.out.println("MarcaVB - Alterar : " + this);

	        Marca tMarca = new Marca();
	        tMarca.setId(id);
	        tMarca.setNome(nome);

	        MarcaController tController = new MarcaController();

	        MarcaDto tDto = tController.atualizarMarca(tMarca);
	        if (tDto.isOk())
	        {
	            // Ok, alterado
	            id = tDto.getMarca().getId();

	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
	        }
	        else
	        {
	            // Erro de alteração

	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
	        }
	        return null;
	    }

	    public String consultar()
	    {
	        System.out.println("MarcaVB - Consultar : " + this);

	        MarcaController tController = new MarcaController();

	        MarcaDto tDto = tController.recuperarMarca(id);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            Marca tMarca = tDto.getMarca();
	            id = tMarca.getId();
	            nome = tMarca.getNome();

	            // indicando que a pesquisa deu certo
	            edicao = true;
	            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MARCA", tMarca);
	        }
	        else
	        {
	            // Erro de consulta
	            edicao = false;

	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
	        }

	        return tela;
	    }

	    public String excluir()
	    {
	        System.out.println("MarcaVB - Excluir : " + this);

	        MarcaController tController = new MarcaController();

	        MarcaDto tDto = tController.removeMarca(id);
	        if (tDto.isOk())
	        {
	            // Ok, exluido
	            limpar();

	            // indicando que a pesquisa deu certo
	            edicao = false;

	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

	        }
	        else
	        {
	            // Erro de consulta
	            edicao = false;

	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
	        }

	        return null;
	    }
	    
	    public String pesquisar()
	    {
	        System.out.println("MarcaVB - Pesquisar : " + this);

	        MarcaController tController = new MarcaController();

	        MarcaDto tDto = tController.pesquisarMarcasPorNome(nome);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            listaMarca = tDto.getLista();
	        }
	        else
	        {
	            // Colocando a mensagem do sistema
	            FacesContext.getCurrentInstance().addMessage(null,
	                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
	        }

	        return null;
	    }

	// Métodos Gerais
	@Override
	public String toString()
	{
	    StringBuilder tBuilder = new StringBuilder();
	    tBuilder.append(" [");
	    tBuilder.append(id);
	    tBuilder.append(", ");
	    tBuilder.append(nome);
	    tBuilder.append("]");
	    return tBuilder.toString();
	}
}
