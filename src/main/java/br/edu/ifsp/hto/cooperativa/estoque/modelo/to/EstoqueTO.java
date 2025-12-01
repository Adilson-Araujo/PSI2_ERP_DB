package br.edu.ifsp.hto.cooperativa.estoque.modelo.to;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;

public class EstoqueTO {
    private int associado_id;
    private ProdutoVO produto;
    private float quantidade;

    public EstoqueTO(int associado_id, ProdutoVO produto, float quantidade) {
        this.associado_id = associado_id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getAssociadoId() {
        return associado_id;
    }

    public ProdutoVO getProduto() {
        return produto;
    }
    
    public float getQuantidade() {
        return quantidade;
    }
    
    public void setAssociadoId(int associado_id){
        this.associado_id = associado_id;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
}
