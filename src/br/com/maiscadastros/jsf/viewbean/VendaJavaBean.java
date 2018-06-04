package br.com.maiscadastros.jsf.viewbean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.maiscadastros.controller.ClienteController;
import br.com.maiscadastros.controller.ProdutoController;
import br.com.maiscadastros.controller.VendaController;
import br.com.maiscadastros.dto.ClienteDto;
import br.com.maiscadastros.dto.ProdutoDto;
import br.com.maiscadastros.dto.VendaDto;
import br.com.maiscadastros.model.Cliente;
import br.com.maiscadastros.model.Produto;
import br.com.maiscadastros.model.Venda;

@ViewScoped
@ManagedBean(name="VendaVB")
public class VendaJavaBean 
{
    // Atributos - Valores dos componentes visuais
	private Integer id;
	private String descricao;
	private String nomeProduto;
	private String nomeCliente;
	private Integer quantidadeProduto;
	private BigDecimal valorTotal;
	private String pagamento;
	private Boolean fechado;
	private Date data;
	private Produto produto;
	private Cliente cliente;
    private boolean edicao;
    private String  tela;
    private List<Venda> listaVenda;
    private List<Produto> listaProduto;
    private List<Cliente> listaCliente;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNomeProduto()
    {
        return nomeProduto;
    }

    public void setNomeProduto(String pNomeProduto)
    {
        nomeProduto = pNomeProduto;
    }

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public void setNomeLoja(String pNomeCliente)
    {
        nomeCliente = pNomeCliente;
    }
    
	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getFechamento() {
		return fechado;
	}

	public void setFechado(Boolean fechado) {
		this.fechado = fechado;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	@PostConstruct
    public void init()
    {
		Venda tVenda2 = (Venda) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestMap().get("VENDA");
if (tVenda2 != null)
{
    id = tVenda2.getId();
    descricao = tVenda2.getDescricao();
    valorTotal = tVenda2.getValorTotal();
    pagamento = tVenda2.getPagamento();
    data = java.sql.Date.valueOf(tVenda2.getData());
    edicao = true;
}
		
        Venda tVenda = (Venda) FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestMap().get("VENDA");
        if (tVenda != null)
        {
            id = tVenda.getId();
            produto = tVenda.getProduto();
            cliente = tVenda.getCliente();

            ProdutoController tProdutoController = new ProdutoController();

            ProdutoDto tProdutoDto = tProdutoController.recuperarProduto(produto);
            if (tProdutoDto.isOk())
            {
            	Produto tProduto = tProdutoDto.getProduto();
                nomeProduto = tProduto.getNome();
            }
            else
            {
                nomeProduto=null;
            }
            
            ClienteController tClienteController = new ClienteController();

            ClienteDto tClienteDto = tClienteController.recuperarCliente(cliente);
            if (tClienteDto.isOk())
            {
            	Cliente tCliente = tClienteDto.getCliente();
                nomeCliente = tCliente.getNome();
            }
            else
            {
                nomeCliente=null;
            }

        }
        
        ProdutoController tController = new ProdutoController();

        ProdutoDto tDto = tController.pesquisarProduto();
        if (tDto.isOk())
        {
            // Ok, recuperado
            listaProduto = tDto.getLista();
        }
        else
        {
            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
        
        ClienteController tCController = new ClienteController();

        ClienteDto tCDto = tCController.pesquisarCliente();
        if (tDto.isOk())
        {
            // Ok, recuperado
            listaCliente = tCDto.getLista();
        }
        else
        {
            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
    }

	// Métodos da Controller
    public String limpar()
    {
        id = null;
        descricao = null;
        quantidadeProduto = null;
        valorTotal = null;
        pagamento = null;
        fechado = null;
        data = null;
        produto = null;
        cliente = null;
        edicao = false;

        return tela;
    }
	
	// Métodos da Controller
	public String cadastrar()
	{
	    System.out.println("VendaVB - Cadastrar : " + this);

	    Venda tVenda = new Venda();
	    tVenda.setDescricao(descricao);
	    tVenda.setValorTotal(valorTotal);
	    tVenda.setPagamento(pagamento);
	    tVenda.setFechado(fechado);
	    LocalDate tData = new java.sql.Date(data.getTime()).toLocalDate();
	    tVenda.setData(tData);
	    tVenda.setProduto(produto);
	    tVenda.setCliente(cliente);

	    VendaController tController = new VendaController();

	    VendaDto tDto = tController.cadastrarVenda(tVenda);
	    if (tDto.isOk())
	    {
	        // Ok, incluído
	        id = tDto.getVenda().getId();

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
	        System.out.println("VendaVB - Alterar : " + this);

	        Venda tVenda = new Venda();
	        tVenda.setId(id);
	        tVenda.setDescricao(descricao);
	        tVenda.setValorTotal(valorTotal);
	        tVenda.setPagamento(pagamento);
	        tVenda.setFechado(fechado);
	        LocalDate tData = new java.sql.Date(data.getTime()).toLocalDate();
	        tVenda.setData(tData);
	        tVenda.setProduto(produto);
	        tVenda.setCliente(cliente);

	        VendaController tController = new VendaController();

	        VendaDto tDto = tController.atualizarVenda(tVenda);
	        if (tDto.isOk())
	        {
	            // Ok, alterado
	            id = tDto.getVenda().getId();

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

	    public String consultar(Venda venda)
	    {
	        System.out.println("VendaVB - Consultar : " + this);

	        VendaController tController = new VendaController();

	        VendaDto tDto = tController.recuperarVenda(venda);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            Venda tVenda = tDto.getVenda();
	            id = tVenda.getId();
	            descricao = tVenda.getDescricao();
	            valorTotal = tVenda.getValorTotal();
	            pagamento = tVenda.getPagamento();
	            fechado = tVenda.getFechado();
	            data = java.sql.Date.valueOf(tVenda.getData());
	            produto = tVenda.getProduto();
	            cliente = tVenda.getCliente();

	            // indicando que a pesquisa deu certo
	            edicao = true;
	            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VENDA", tVenda);
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

	    public String excluir(Venda venda)
	    {
	        System.out.println("VendaVB - Excluir : " + this);

	        VendaController tController = new VendaController();

	        VendaDto tDto = tController.removeVenda(venda);
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
	        System.out.println("VendaVB - Pesquisar : " + this);

	        VendaController tController = new VendaController();

	        VendaDto tDto = tController.pesquisarVendasPorDescricao(descricao);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            listaVenda = tDto.getLista();
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
	    tBuilder.append(descricao);
	    tBuilder.append(", ");
	    tBuilder.append(nomeProduto);
        tBuilder.append(", ");
	    tBuilder.append(nomeCliente);
	    tBuilder.append(", ");
	    tBuilder.append(valorTotal);
	    tBuilder.append(", ");
	    tBuilder.append(pagamento);
	    tBuilder.append(", ");
	    tBuilder.append(fechado);
	    tBuilder.append(", ");
	    tBuilder.append(data);
	    tBuilder.append(", ");
	    tBuilder.append(produto);
	    tBuilder.append(", ");
	    tBuilder.append(cliente);
	    tBuilder.append("]");
	    return tBuilder.toString();
	}
	}
