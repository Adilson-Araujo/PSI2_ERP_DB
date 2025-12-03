package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoVO {

    private Long id = null;
    private Long projetoId;
    private Long associadoId;
    private LocalDateTime dataCriacao;
    private Long statusPedidoId;
    private String statusDescricao;
    private BigDecimal valorTotal;

    private List<ItemPedidoVO> itens = new ArrayList<>();

    public Long getId() { 
        return id == null || id == 0 ? null : id; 
    }

    public void setId(Long id) { 
        this.id = (id != null && id == 0) ? null : id; 
    }

    public Long getProjetoId() {
        return projetoId == null || projetoId == 0 ? null : projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = (projetoId != null && projetoId == 0) ? null : projetoId;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }
    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public Long getAssociadoId() { return associadoId; }
    public void setAssociadoId(Long associadoId) { this.associadoId = associadoId; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Long getStatusPedidoId() { return statusPedidoId; }
    public void setStatusPedidoId(Long statusPedidoId) { this.statusPedidoId = statusPedidoId; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemPedidoVO> getItens() { return itens; }
    public void adicionarItem(ItemPedidoVO item) { itens.add(item); }
}
