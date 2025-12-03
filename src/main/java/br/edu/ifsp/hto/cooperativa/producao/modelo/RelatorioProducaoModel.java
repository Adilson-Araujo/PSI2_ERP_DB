package br.edu.ifsp.hto.cooperativa.producao.modelo;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.RelatorioProducaoDAO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import java.util.List;
import java.util.Map;

public class RelatorioProducaoModel {
    
    private RelatorioProducaoDAO dao;

    public RelatorioProducaoModel() {
        this.dao = new RelatorioProducaoDAO();
    }

    public String[] getAreas() {
        Long associadoId = Sessao.getAssociadoIdLogado();
        List<String> lista = dao.listarAreas(associadoId);
        return lista.toArray(new String[0]);
    }
    
    public Object[][] getDadosTabela(String area) {
        Long associadoId = Sessao.getAssociadoIdLogado();
        List<Object[]> lista = dao.buscarDadosTabela(area, associadoId);
        Object[][] matriz = new Object[lista.size()][];
        for (int i = 0; i < lista.size(); i++) {
            matriz[i] = lista.get(i);
        }
        return matriz;
    }
    
    public Map<String, Object> getEstatisticas(String area) {
        Long associadoId = Sessao.getAssociadoIdLogado();
        return dao.buscarEstatisticas(area, associadoId);
    }
}