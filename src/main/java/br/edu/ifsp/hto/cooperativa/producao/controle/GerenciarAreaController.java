package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.AreaDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.TalhaoDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO; // 🔑 Novo Import

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

public class GerenciarAreaController {

    private AreaDAO areaDAO;
    private TalhaoDAO talhaoDAO; // 🔑 Instância do TalhaoDAO
    private CanteiroDAO canteiroDAO; // 🔑 Instância do CanteiroDAO

    public GerenciarAreaController() {
        this.areaDAO = new AreaDAO();
        this.talhaoDAO = new TalhaoDAO(); // 🔑 Inicialização
        this.canteiroDAO = new CanteiroDAO(); // 🔑 Inicialização
    }

    /**
     * Marca um talhão como Inativo através do DAO.
     * Retorna true em sucesso, false em erro.
     */
    public boolean removerTalhao(Long talhaoId) {
        try {
            talhaoDAO.inativarTalhao(talhaoId);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inativar talhão: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método que você já tem para listar áreas (sem detalhes aninhados)
    public List<AreaVO> carregarAreas(long associadoId) {
        try {
            return areaDAO.buscarPorAssociadoId(associadoId); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de áreas: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return List.of(); // Retorna lista vazia em caso de erro
        }
    }
    
    // Método que você já tem para buscar a área pelo ID, agora vamos expandi-lo
    public AreaVO carregarAreaCompletaPorId(Long areaId) {
        try {
            // 🔑 1. FORÇA O RE-CÁLCULO E ATUALIZAÇÃO NO BANCO
            // Isso corrige a inconsistência herdada das inserções antigas.
            areaDAO.calcularEAtualizarAreaUtilizada(areaId); 

            // 2. Busca a Área base (que agora tem o campo area_utilizada correto)
            AreaVO area = areaDAO.buscarPorId(areaId);

            if (area != null) {
                // 3. Carrega os Talhões e Canteiros (lógica em cascata)
                carregarTalhoes(area);
            }
            return area;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar detalhes da área: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Carrega a lista de Talhões de uma Área e, para cada Talhão, carrega seus Canteiros.
     */
    private void carregarTalhoes(AreaVO area) throws SQLException {
        // Busca todos os Talhões ligados a esta Área
        List<TalhaoVO> talhoes = talhaoDAO.buscarPorAreaId(area.getId());
        
        // 🔑 Itera sobre cada Talhão para carregar seus Canteiros
        for (TalhaoVO talhao : talhoes) {
            carregarCanteiros(talhao);
        }

        // 🔑 Anexa a lista de Talhões (agora completa com Canteiros) à Área
        area.setTalhoes(talhoes);
    }

    /**
     * Carrega a lista de Canteiros de um Talhão.
     */
    private void carregarCanteiros(TalhaoVO talhao) throws SQLException {
        // Busca todos os Canteiros ligados a este Talhão (via plano, conforme modelamos no DAO)
        List<CanteiroVO> canteiros = canteiroDAO.buscarPorTalhaoId(talhao.getId());
        
        // 🔑 Anexa a lista de Canteiros ao Talhão
        talhao.setCanteiros(canteiros);
    }
}