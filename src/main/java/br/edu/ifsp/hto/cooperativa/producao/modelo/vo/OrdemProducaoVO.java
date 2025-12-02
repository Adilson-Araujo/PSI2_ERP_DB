package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.util.Date;
import java.util.List;

public class OrdemProducaoVO {

    private Long id;
    private Integer planoId;
    private Long especieId;
    private String nomeEspecie; // 🔑 NOVO: nome da espécie
    private Long talhaoId;
    private String nomeTalhao; // 🔑 NOVO: nome do talhão associado
    private String nomePlano;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String observacoes;
    private Double areaCultivo;
    private Date dataExecucao;
    private Double quantidadeKg;
    private String status;
    private List<CanteiroVO> canteiros; // 🔑 NOVO: canteiros desta ordem

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlanoId() {
        return planoId;
    }
    public void setPlanoId(Integer planoId) {
        this.planoId = planoId;
    }

    public Long getEspecieId() {
        return especieId;
    }
    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public String getNomeEspecie() {
        return nomeEspecie;
    }
    public void setNomeEspecie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
    }

    public Long getTalhaoId() {
        return talhaoId;
    }
    public void setTalhaoId(Long talhaoId) {
        this.talhaoId = talhaoId;
    }

    public String getNomeTalhao() {
        return nomeTalhao;
    }
    public void setNomeTalhao(String nomeTalhao) {
        this.nomeTalhao = nomeTalhao;
    }

    public String getNomePlano() {
        return nomePlano;
    }
    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getAreaCultivo() {
        return areaCultivo; 
    }
    public void setAreaCultivo(Double areaCultivo) {
        this.areaCultivo = areaCultivo;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }
    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public Double getQuantidadeKg() {
        return quantidadeKg;
    }
    public void setQuantidadeKg(Double quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<CanteiroVO> getCanteiros() {
        return canteiros;
    }
    public void setCanteiros(List<CanteiroVO> canteiros) {
        this.canteiros = canteiros;
    }
}
