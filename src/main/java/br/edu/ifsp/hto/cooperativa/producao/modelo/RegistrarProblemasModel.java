package br.edu.ifsp.hto.cooperativa.producao.modelo;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.RegistrarProblemasDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.CulturaDTO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.TipoProblemaDTO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import java.util.Date;
import java.util.List;

public class RegistrarProblemasModel {
    
    private RegistrarProblemasDAO dao = new RegistrarProblemasDAO();

    // Retorna Objetos (DTOs) ao invés de String
    public TipoProblemaDTO[] getProblemas() {
        List<TipoProblemaDTO> lista = dao.listarTipos();
        return lista.toArray(new TipoProblemaDTO[0]);
    }

    public CulturaDTO[] getCulturasAtivas() {
        Long associadoId = Sessao.getAssociadoIdLogado();
        List<CulturaDTO> lista = dao.listarCulturasAtivas(associadoId);
        return lista.toArray(new CulturaDTO[0]);
    }

    public boolean registrar(CulturaDTO cultura, TipoProblemaDTO problema, int qtd, Date data, String obs) {
        if (cultura == null || problema == null) return false;
        return dao.salvar(cultura.getIdOrdemProducao(), problema.getId(), qtd, data, obs);
    }
}