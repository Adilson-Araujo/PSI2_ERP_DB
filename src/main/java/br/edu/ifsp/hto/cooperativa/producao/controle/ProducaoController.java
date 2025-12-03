package br.edu.ifsp.hto.cooperativa.producao.controle;


import br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel;

public class ProducaoController {

    private RelatorioProducaoModel model;

    public ProducaoController(RelatorioProducaoModel model) {
        this.model = model;
    }

    public String[] getAreasPlantio() {
        return model.getAreas(); 
    }
}