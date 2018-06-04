package br.com.maiscadastros.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TABELA_VENDA")
public class Venda
{
 // Atributos
	@Id
	@Column (name = "Codigo_Venda")
	private int         id;
	
	@Column (name = "Descricao_Venda")
	private String      descricao;
	
	@Column (name = "Valor_Venda")
	private BigDecimal  valorTotal;
	
	@Column (name = "Forma_Pagamento_Venda")
	private String   pagamento;
	
	@Column (name = "Data_Venda")
	private LocalDate   data;
	
	@Column (name = "Fechado")
	private Boolean   fechado;
	
	@ManyToOne
	@JoinColumn (name = "ID_Produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn (name = "ID_Cliente")
	private Cliente cliente;
	

 // Construtores
    public Venda()
    {
        super();
    }

    public Venda(int pId, String pDescricao, BigDecimal pValorTotal, String pPagamento, LocalDate pData, Boolean pFechado, Produto produto, Cliente cliente)
    {
        super();
        setId(pId);
        setDescricao(pDescricao);
        setValorTotal(pValorTotal);
        setPagamento(pPagamento);
        setData(pData);
        setFechado(pFechado);
        setProduto(produto);
        setCliente(cliente);
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String pDescricao)
    {
        descricao = pDescricao;
    }

    public BigDecimal getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal pValorTotal)
    {
        valorTotal = pValorTotal;
    }

    public String getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(String pPagamento)
    {
        pagamento = pPagamento;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate pData)
    {
        data = pData;
    }
    
    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    /**
	 * @return the fechado
	 */
	public Boolean getFechado() {
		return fechado;
	}

	/**
	 * @param fechado the fechado to set
	 */
	public void setFechado(Boolean fechado) {
		this.fechado = fechado;
	}

	// Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getDescricao());
        tBuilder.append(", ");
        tBuilder.append(getValorTotal());
        tBuilder.append(", ");
        tBuilder.append(getPagamento());
        tBuilder.append(", ");
        tBuilder.append(getData());
        tBuilder.append(", ");
        tBuilder.append(getFechado());
        tBuilder.append(", ");
        tBuilder.append(getProduto());
        tBuilder.append(", ");
        tBuilder.append(getCliente());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
