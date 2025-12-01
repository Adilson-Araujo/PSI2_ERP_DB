package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

import java.sql.Timestamp;

public class MovimentacaoVO{
    private int id;
    private TipoVO tipo;
    private OrigemVO origem;
    private ProdutoVO produto;
    private ArmazemVO armazem;
    private int associado_id;
    private float quantidade;
    private float quantidade_salva;
    private Timestamp data;
    
    public MovimentacaoVO(int id, TipoVO tipo, OrigemVO origem, ProdutoVO produto, ArmazemVO armazem, int associado_id, float quantidade, Timestamp data){
        this.id = id;
        this.tipo = tipo;
        this.origem = origem;
        this.produto = produto;
        this.armazem = armazem;
        this.associado_id = associado_id;
        this.quantidade = quantidade;
        this.quantidade_salva = quantidade;
        this.data = data;
    }
    
    public int getId() {
        return id;
    }

    public TipoVO getTipo() {
        return tipo;
    }

    public OrigemVO getOrigem() {
        return origem;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public ArmazemVO getArmazem() {
        return armazem;
    }
    
    public int getAssociadoId() {
        return associado_id;
    }
    
    public float getQuantidade() {
        return quantidade;
    }

    public Timestamp getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setTipo(TipoVO tipo) {
        this.tipo = tipo;
    }

    public void setOrigem(OrigemVO origem) {
        this.origem = origem;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public void setArmazem(ArmazemVO armazem) {
        this.armazem = armazem;
    }
    
    public void setAssociadoId(int associado_id){
        this.associado_id = associado_id;
    }
    
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public void setData_(Timestamp data) {
        this.data = data;
    }
    
    // Fora do padrão VO
    
    public float getMudanca(){
        float mudanca = this.quantidade - this.quantidade_salva;
        this.quantidade_salva = quantidade;
        return mudanca;
    }
}
