package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class EspecieVO{
    private int id;
    private CategoriaVO categoria;
    private String nome;
    private String descricao;
    private int tempo_colheita;
    private float rendimento_kg_m2;
    
    public EspecieVO(int id, CategoriaVO categoria, String nome, String descricao, int tempo_colheita, float rendimento_kg_m2){
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.tempo_colheita = tempo_colheita;
        this.rendimento_kg_m2 = rendimento_kg_m2;
    }
    
    public int getId() {
        return id;
    }

    public CategoriaVO getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTempo_colheita() {
        return tempo_colheita;
    }

    public float getRendimento_kg_m2() {
        return rendimento_kg_m2;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setCategoria(CategoriaVO categoria) {
        this.categoria = categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTempo_colheita(int tempo_colheita) {
        this.tempo_colheita = tempo_colheita;
    }

    public void setRendimento_kg_m2(float rendimento_kg_m2) {
        this.rendimento_kg_m2 = rendimento_kg_m2;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    } 
}
