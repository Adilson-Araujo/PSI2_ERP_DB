package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.sql.Timestamp;

public class RegistrarProblemaVO {

    private long id;
    private long ordemProducaoId;
    private long tipoProblemaId;
    private int quantidadeAfetada;
    private Timestamp dataProblema;
    private String observacoes;

    public RegistrarProblemaVO() {
    }

    public RegistrarProblemaVO(long id, long ordemProducaoId, long tipoProblemaId, int quantidadeAfetada, Timestamp dataProblema, String observacoes) {
        this.id = id;
        this.ordemProducaoId = ordemProducaoId;
        this.tipoProblemaId = tipoProblemaId;
        this.quantidadeAfetada = quantidadeAfetada;
        this.dataProblema = dataProblema;
        this.observacoes = observacoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrdemProducaoId() {
        return ordemProducaoId;
    }

    public void setOrdemProducaoId(long ordemProducaoId) {
        this.ordemProducaoId = ordemProducaoId;
    }

    public long getTipoProblemaId() {
        return tipoProblemaId;
    }

    public void setTipoProblemaId(long tipoProblemaId) {
        this.tipoProblemaId = tipoProblemaId;
    }

    public int getQuantidadeAfetada() {
        return quantidadeAfetada;
    }

    public void setQuantidadeAfetada(int quantidadeAfetada) {
        this.quantidadeAfetada = quantidadeAfetada;
    }

    public Timestamp getDataProblema() {
        return dataProblema;
    }

    public void setDataProblema(Timestamp dataProblema) {
        this.dataProblema = dataProblema;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "RegistrarProblemaVO{" +
                "id=" + id +
                ", ordemProducaoId=" + ordemProducaoId +
                ", tipoProblemaId=" + tipoProblemaId +
                ", quantidadeAfetada=" + quantidadeAfetada +
                ", dataProblema=" + dataProblema +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
