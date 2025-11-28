package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.math.BigDecimal;

public class CanteiroVO {
    private Long id;
    private Boolean ativo; // Mapeia ativo
    private Long ordemProducaoId; // Mapeia ordem_producao_id
    private String nome; // Nome do canteiro (preenchido com nome_plano da ordem)
    private BigDecimal areaCanteiroM2; // Preenchido com area_cultivo da ordem
    private String observacoes; // Preenchido com observacoes da ordem
    private BigDecimal kgGerados; // Preenchido com quantidade_kg da ordem
    private String status; // Sempre inicia com 'crescendo'

    public CanteiroVO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Long getOrdemProducaoId() { return ordemProducaoId; }
    public void setOrdemProducaoId(Long ordemProducaoId) { this.ordemProducaoId = ordemProducaoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getAreaCanteiroM2() { return areaCanteiroM2; }
    public void setAreaCanteiroM2(BigDecimal areaCanteiroM2) { this.areaCanteiroM2 = areaCanteiroM2; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public BigDecimal getKgGerados() { return kgGerados; }
    public void setKgGerados(BigDecimal kgGerados) { this.kgGerados = kgGerados; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}