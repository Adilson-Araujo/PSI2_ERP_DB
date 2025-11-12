package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.math.BigDecimal;

public class OrdemProducaoVO {

    private long id;
    private long planoId;
    private int quantidade;
    private BigDecimal kgTotal;

    public OrdemProducaoVO() {
    }

    public OrdemProducaoVO(long id, long planoId, int quantidade, BigDecimal kgTotal) {
        this.id = id;
        this.planoId = planoId;
        this.quantidade = quantidade;
        this.kgTotal = kgTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(long planoId) {
        this.planoId = planoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getKgTotal() {
        return kgTotal;
    }

    public void setKgTotal(BigDecimal kgTotal) {
        this.kgTotal = kgTotal;
    }

    @Override
    public String toString() {
        return "OrdemProducaoVO{" +
                "id=" + id +
                ", planoId=" + planoId +
                ", quantidade=" + quantidade +
                ", kgTotal=" + kgTotal +
                '}';
    }
}
