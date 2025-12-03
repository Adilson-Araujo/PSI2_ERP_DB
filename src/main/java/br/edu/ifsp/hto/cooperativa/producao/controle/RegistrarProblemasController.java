package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.CulturaDTO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.TipoProblemaDTO;
import java.util.Date;

public class RegistrarProblemasController {

    private RegistrarProblemasModel model;

    public RegistrarProblemasController(RegistrarProblemasModel model) {
        this.model = model;
    }

    public TipoProblemaDTO[] getListaProblemas() {
        return model.getProblemas();
    }

    public CulturaDTO[] getListaCulturas() {
        return model.getCulturasAtivas();
    }
    
    public boolean salvarProblema(CulturaDTO cultura, TipoProblemaDTO problema, String qtdStr, String dataStr, String obs) {
        try {
            int qtd = Integer.parseInt(qtdStr);
            // Simples conversão de data (assumindo dd/MM/yyyy)
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataStr);
            
            return model.registrar(cultura, problema, qtd, data, obs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}