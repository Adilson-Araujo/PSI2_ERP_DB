package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class ProdutoVO{
    private int id;
    private EspecieVO especie;
    private String nome;
    private String descricao;
    
    public ProdutoVO(int id, EspecieVO especie, String nome, String descricao){
        this.id = id;
        this.especie = especie;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public int getId() {
        return id;
    }
    
    public EspecieVO getEspecie() {
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
    
    public void setEspecie(EspecieVO especie) {
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
