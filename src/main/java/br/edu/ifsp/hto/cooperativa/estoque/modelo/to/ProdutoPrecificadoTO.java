package br.edu.ifsp.hto.cooperativa.estoque.modelo.to;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Especie;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPA;

public class ProdutoPrecificadoTO {
    private final Produto produto;
    private final Especie especie;
    private final PrecoPPA preco_ppa;

    public ProdutoPrecificadoTO(Produto produto, Especie especie, PrecoPPA preco_ppa) {
        this.produto = produto;
        this.especie = especie;
        this.preco_ppa = preco_ppa;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Especie getEspecie() {
        return this.especie;
    }
    
    public PrecoPPA getPrecoPPA() {
        return this.preco_ppa;
    }
}
