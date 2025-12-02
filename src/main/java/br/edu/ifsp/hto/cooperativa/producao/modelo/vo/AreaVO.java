package br.edu.ifsp.hto.cooperativa.producao.modelo.vo;

import java.util.List;

public class AreaVO {

    private long id;
    private String nome;
    private long associadoId;
    private double areaTotal; 
    private double areaUtilizada; 
    private double ph; 
    private List<TalhaoVO> talhoes;
    private List<OrdemProducaoVO> ordens; // 🔑 NOVO: ordens de produção ativas

    // 🔑 CONSTRUTOR PADRÃO ADICIONADO PARA O DAO
    // Esta adição é NECESSÁRIA porque a existência de outros construtores remove o construtor padrão implícito.
    public AreaVO() {
    }

    // Construtor original (mantido por compatibilidade)
    public AreaVO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // NOVO CONSTRUTOR COMPLETO
    public AreaVO(long id, String nome, double areaTotal, double areaUtilizada, double ph) {
        this.id = id;
        this.nome = nome;
        this.areaTotal = areaTotal;
        this.areaUtilizada = areaUtilizada;
        this.ph = ph;
    }

    // --- GETTERS E SETTERS ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getAreaTotal() {
        return areaTotal;
    }
    
    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }
    
    public double getAreaUtilizada() {
        return areaUtilizada;
    }
    
    public void setAreaUtilizada(double areaUtilizada) {
        this.areaUtilizada = areaUtilizada;
    }
    
    public double getPh() {
        return ph;
    }
    
    public void setPh(double ph) {
        this.ph = ph;
    }

    public List<TalhaoVO> getTalhoes() { return talhoes; }
    public void setTalhoes(List<TalhaoVO> talhoes) { this.talhoes = talhoes; }

    public List<OrdemProducaoVO> getOrdens() { return ordens; }
    public void setOrdens(List<OrdemProducaoVO> ordens) { this.ordens = ordens; }

    @Override
    public String toString() {
        return nome;
    }

    public long getAssociadoId() { return associadoId; }
    public void setAssociadoId(long associadoId) { this.associadoId = associadoId; }
}