package br.com.maiscadastros.jsf.viewbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.maiscadastros.controller.SetorController;
import br.com.maiscadastros.dto.SetorDto;
import br.com.maiscadastros.model.Setor;

public class SetorJavaBean {
	// Atributos - Valores dos componentes visuais
		private Integer id;
		private String nome;
	    private boolean edicao;
	    private String  tela;
	    private List<Setor> listaSetor;
	    
	    @PostConstruct
	    public void init()
	    {
	        Setor tSetor = (Setor) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("SETOR");
	        if (tSetor != null)
	        {
	            id = tSetor.getId();
	            nome = tSetor.getNome();
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

		public List<Setor> getListaSetor() {
			return listaSetor;
		}

		public void setListaSetor(List<Setor> listaSetor) {
			this.listaSetor = listaSetor;
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
		    System.out.println("SetorVB - Cadastrar : " + this);

		    Setor tSetor = new Setor();
		    tSetor.setNome(nome);

		    SetorController tController = new SetorController();

		    SetorDto tDto = tController.cadastrarSetor(tSetor);
		    if (tDto.isOk())
		    {
		        // Ok, incluído
		        id = tDto.getSetor().getId();

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
		        System.out.println("SetorVB - Alterar : " + this);

		        Setor tSetor = new Setor();
		        tSetor.setId(id);
		        tSetor.setNome(nome);

		        SetorController tController = new SetorController();

		        SetorDto tDto = tController.atualizarSetor(tSetor);
		        if (tDto.isOk())
		        {
		            // Ok, alterado
		            id = tDto.getSetor().getId();

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

		    public String consultar(Setor setor)
		    {
		        System.out.println("SetorVB - Consultar : " + this);

		        SetorController tController = new SetorController();

		        SetorDto tDto = tController.recuperarSetor(setor);
		        if (tDto.isOk())
		        {
		            // Ok, recuperado
		            Setor tSetor = tDto.getSetor();
		            id = tSetor.getId();
		            nome = tSetor.getNome();

		            // indicando que a pesquisa deu certo
		            edicao = true;
		            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SETOR", tSetor);
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

		    public String excluir(Setor setor)
		    {
		        System.out.println("SetorVB - Excluir : " + this);

		        SetorController tController = new SetorController();

		        SetorDto tDto = tController.removeSetor(setor);
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
		        System.out.println("SetorVB - Pesquisar : " + this);

		        SetorController tController = new SetorController();

		        SetorDto tDto = tController.pesquisarSetorsPorNome(nome);
		        if (tDto.isOk())
		        {
		            // Ok, recuperado
		            listaSetor = tDto.getLista();
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
