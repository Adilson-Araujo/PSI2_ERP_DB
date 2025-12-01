package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class CategoriaVO{
    private int id;
    private String nome;

    // Construtor, insere os valores.
    public CategoriaVO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
