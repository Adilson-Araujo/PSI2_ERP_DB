package br.edu.ifsp.hto.cooperativa.estoque.modelo.to;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPAVO;

public class ProdutoPrecificadoTO {
    private final ProdutoVO produto;
    private final EspecieVO especie;
    private final PrecoPPAVO preco_ppa;

    public ProdutoPrecificadoTO(ProdutoVO produto, EspecieVO especie, PrecoPPAVO preco_ppa) {
        this.produto = produto;
        this.especie = especie;
        this.preco_ppa = preco_ppa;
    }

    public ProdutoVO getProduto() {
        return this.produto;
    }

    public EspecieVO getEspecie() {
        return this.especie;
    }
    
    public PrecoPPAVO getPrecoPPA() {
        return this.preco_ppa;
    }
}
