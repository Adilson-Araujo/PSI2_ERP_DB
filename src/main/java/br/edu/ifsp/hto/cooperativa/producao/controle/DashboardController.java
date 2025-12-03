package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.DashboardDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.DashboardVO;

public class DashboardController {
    
    private DashboardDAO dao;

    public DashboardController() {
        this.dao = new DashboardDAO();
    }

    public DashboardVO obterDadosIniciais(int associadoId) {
        return dao.carregarDadosIniciais(associadoId);
    }
}