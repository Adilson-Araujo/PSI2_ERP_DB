package br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO;

public class CanteiroVO {
    private int id;
    private int ordemProducaoId;
    private String nome;
    private float areaCanteiroM2;
    private String observacoes;
    private float kgGerados;
    private String status;

    public CanteiroVO() {
        
    }

    public CanteiroVO(int ordemProducaoId, String nome, float areaCanteiroM2, String observacoes, float kgGerados) {
        this.ordemProducaoId = ordemProducaoId;
        this.nome = nome;
        this.areaCanteiroM2 = areaCanteiroM2;
        this.observacoes = observacoes;
        this.kgGerados = kgGerados;
    }

    public CanteiroVO(int id, int ordemProducaoId, String nome, float areaCanteiroM2, String observacoes, float kgGerados) {
        this.id = id;
        this.ordemProducaoId = ordemProducaoId;
        this.nome = nome;
        this.areaCanteiroM2 = areaCanteiroM2;
        this.observacoes = observacoes;
        this.kgGerados = kgGerados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdemProducaoId() {
        return ordemProducaoId;
    }

    public void setOrdemProducaoId(int ordemProducaoId) {
        this.ordemProducaoId = ordemProducaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAreaCanteiroM2() {
        return areaCanteiroM2;
    }

    public void setAreaCanteiroM2(float areaCanteiroM2) {
        this.areaCanteiroM2 = areaCanteiroM2;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public float getKgGerados() {
        return kgGerados;
    }

    public void setKgGerados(float kgGerados) {
        this.kgGerados = kgGerados;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
