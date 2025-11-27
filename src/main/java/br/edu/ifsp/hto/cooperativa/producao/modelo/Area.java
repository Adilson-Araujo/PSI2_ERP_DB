package br.edu.ifsp.hto.cooperativa.producao.modelo;

public class Area {

    private long id;
    private String nome;
    private long associadoId;
    private double areaTotal; 
    private double areaUtilizada; 
    private double ph; 

    // Construtor original (mantido por compatibilidade, mas o novo será mais útil)
    public Area(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // NOVO CONSTRUTOR COMPLETO
    public Area(long id, String nome, double areaTotal, double areaUtilizada, double ph) {
        this.id = id;
        this.nome = nome;
        this.areaTotal = areaTotal;
        this.areaUtilizada = areaUtilizada;
        this.ph = ph;
    }

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
    
    // NOVOS GETTERS E SETTERS
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

    @Override
    public String toString() {
        return nome;
    }

    public long getAssociadoId() { return associadoId; }
    public void setAssociadoId(long associadoId) { this.associadoId = associadoId; }
}
