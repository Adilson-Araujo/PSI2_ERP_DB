package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.math.BigDecimal;
import java.util.List;

public class TalhaoVO {
    private Long id;
    private Long areaId; // Mapeia Area_id
    private String nome;
    private BigDecimal areaTalhao;
    private String observacoes;
    private String status;
    
    // Relação 1:N com Canteiro
    private List<CanteiroVO> canteiros; 

    public TalhaoVO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAreaId() { return areaId; }
    public void setAreaId(Long areaId) { this.areaId = areaId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getAreaTalhao() { return areaTalhao; }
    public void setAreaTalhao(BigDecimal areaTalhao) { this.areaTalhao = areaTalhao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<CanteiroVO> getCanteiros() { return canteiros; }
    public void setCanteiros(List<CanteiroVO> canteiros) { this.canteiros = canteiros; }
}