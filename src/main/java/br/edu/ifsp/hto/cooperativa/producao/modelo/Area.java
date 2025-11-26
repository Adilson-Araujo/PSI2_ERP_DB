package br.edu.ifsp.hto.cooperativa.producao.modelo;

public class Area {

    private long id;
    private String nome;
    private long associadoId;

    public Area(long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;  
        // IMPORTANTE: Isso faz o ComboBox mostrar o nome da área
    }

    public long getAssociadoId() { return associadoId; }
    public void setAssociadoId(long associadoId) { this.associadoId = associadoId; }
}
