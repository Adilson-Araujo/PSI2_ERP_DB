package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class ArmazemVO{
    private int id;
    private String nome;
    private int endereco_id;

    // Construtor, insere os valores.
    public ArmazemVO(int id, String nome, int endereco_id) {
        this.id = id;
        this.nome = nome;
        this.endereco_id = endereco_id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getEnderecoId() {
        return endereco_id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(int endereco_id) {
        this.endereco_id = endereco_id;
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
}
