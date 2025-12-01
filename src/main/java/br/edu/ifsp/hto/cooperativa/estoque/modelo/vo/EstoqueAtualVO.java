package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class EstoqueAtualVO{
    private int associado_id;
    private ProdutoVO produto;
    private ArmazemVO armazem;
    private float quantidade;
    
    public EstoqueAtualVO(int associado_id, ProdutoVO produto, ArmazemVO armazem, float quantidade){
        this.associado_id = associado_id;
        this.produto = produto;
        this.armazem = armazem;
        this.quantidade = quantidade;
    }
    
    public int getAssociadoId() {
        return this.associado_id;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public ArmazemVO getArmazem() {
        return armazem;
    }

    public float getQuantidade() {
        return quantidade;
    }

    // Setters
    public void setAssociadoId(int associado_id) {
        this.associado_id = associado_id;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public void setArmazem(ArmazemVO armazem) {
        this.armazem = armazem;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
}
