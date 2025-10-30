package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo;

import java.math.BigDecimal;

public class NotaFiscalItemVO {
    private Long id;
    private Integer produtoId;
    private Long notaFiscalEletronicaId;
    private String cfop;
    private String ncm;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public NotaFiscalItemVO() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
    public Long getNotaFiscalEletronicaId() { return notaFiscalEletronicaId; }
    public void setNotaFiscalEletronicaId(Long notaFiscalEletronicaId) { this.notaFiscalEletronicaId = notaFiscalEletronicaId; }
    public String getCfop() { return cfop; }
    public void setCfop(String cfop) { this.cfop = cfop; }
    public String getNcm() { return ncm; }
    public void setNcm(String ncm) { this.ncm = ncm; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return "NotaFiscalItemVO{id=" + id + ", ncm=" + ncm + "}";
    }
}
