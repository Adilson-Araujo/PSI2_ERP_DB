package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class Produto{
    private int id;
    private Especie especie;
    private String nome;
    private String descricao;
    
    public Produto(int id, Especie especie, String nome, String descricao){
        this.id = id;
        this.especie = especie;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public int getId() {
        return id;
    }
    
    public Especie getEspecie() {
        return especie;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
       
    @Override
    public String toString() {
        return this.getNome();
    }
}
