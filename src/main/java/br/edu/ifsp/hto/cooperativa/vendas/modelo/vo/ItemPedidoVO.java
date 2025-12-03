package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoVO {
    private Long id;
    private Long pedidoId;
    private Long produtoId;
    private BigDecimal quantidadeTotal;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    // --- NOVA LISTA PARA A DISTRIBUIÇÃO ---
    private List<AssociadoItemPedidoVO> distribuicoes = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public BigDecimal getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(BigDecimal quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    // Getters e Setters da nova lista
    public List<AssociadoItemPedidoVO> getDistribuicoes() { return distribuicoes; }
    public void setDistribuicoes(List<AssociadoItemPedidoVO> distribuicoes) { this.distribuicoes = distribuicoes; }
}