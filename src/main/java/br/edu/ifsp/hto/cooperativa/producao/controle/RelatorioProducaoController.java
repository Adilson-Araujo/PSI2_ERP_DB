package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel;
import java.util.Map;

public class RelatorioProducaoController {

    private RelatorioProducaoModel model;

    public RelatorioProducaoController(RelatorioProducaoModel model) {
        this.model = model;
    }

    public String[] getAreasPlantio() {
        return model.getAreas(); 
    }
    
    public Object[][] getDadosRelatorio(String area) {
        return model.getDadosTabela(area);
    }
    
    public Map<String, Object> getEstatisticas(String area) {
        return model.getEstatisticas(area);
    }
}