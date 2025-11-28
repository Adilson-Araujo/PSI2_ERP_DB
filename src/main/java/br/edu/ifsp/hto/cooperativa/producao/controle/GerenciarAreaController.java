package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.AreaDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.TalhaoDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.OrdemProducaoDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.ConnectionFactory; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.PlanoDAO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO; // 🔑 Novo Import
import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque; // 🔑 Novo Import

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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

    /**
     * Cria uma ordem de produção a partir de um plano selecionado.
     * Insere ordem_producao e canteiro, recalcula área utilizada.
     */
    public boolean criarOrdemEProducao(Long areaId, Integer planoId) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();

            // 1. Busca o plano
            PlanoDAO planoDAO = new PlanoDAO();
            PlanoVO plano = planoDAO.buscarPorId(planoId);
            if (plano == null) {
                JOptionPane.showMessageDialog(null, "Plano não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            System.out.println("[DEBUG] Plano encontrado: ID=" + plano.getId() + ", Nome=" + plano.getNomePlano());

            // 2. Cria OrdemProducaoVO e insere (SEM definir ID, deixa o banco gerar)
            OrdemProducaoVO ordem = new OrdemProducaoVO();
            ordem.setPlanoId(planoId);
            ordem.setEspecieId((long) plano.getEspecieId());
            ordem.setTalhaoId((long) plano.getTalhaoId());
            ordem.setNomePlano(plano.getNomePlano());
            ordem.setDescricao(plano.getDescricao());
            ordem.setDataInicio(plano.getDataInicio());
            ordem.setDataFim(plano.getDataFim());
            ordem.setObservacoes(plano.getObservacoes());
            ordem.setAreaCultivo(Double.parseDouble(plano.getAreaCultivo() + ""));
            ordem.setDataExecucao(new Date());
            
            // Calcula quantidade usando ControleEstoque
            ControleEstoque controleEstoque = ControleEstoque.getInstance();
            float quantidadeCalculada = controleEstoque.calcularQuantidade(plano.getEspecieId(), plano.getAreaCultivo());
            ordem.setQuantidadeKg((double) quantidadeCalculada);
            ordem.setStatus("em_execucao");

            System.out.println("[DEBUG] Inserindo ordem: planoId=" + planoId + ", quantidade=" + quantidadeCalculada);
            OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
            ordemDAO.inserir(ordem);
            System.out.println("[DEBUG] Ordem criada com ID: " + ordem.getId());

            // 3. Cria CanteiroVO a partir da ordem de produção
            CanteiroVO canteiro = new CanteiroVO();
            canteiro.setOrdemProducaoId(ordem.getId()); // FK para a ordem criada
            canteiro.setNome(ordem.getNomePlano()); // Nome do plano
            canteiro.setAreaCanteiroM2(java.math.BigDecimal.valueOf(ordem.getAreaCultivo())); // Área da ordem
            canteiro.setObservacoes(ordem.getObservacoes()); // Observações da ordem
            canteiro.setKgGerados(java.math.BigDecimal.valueOf(ordem.getQuantidadeKg())); // Kg da ordem
            canteiro.setStatus("crescendo"); // Status inicial sempre "crescendo"
            canteiro.setAtivo(true); // Sempre ativo ao criar

            System.out.println("[DEBUG] Inserindo canteiro para ordem_producao_id=" + ordem.getId());
            canteiroDAO.inserir(canteiro);
            System.out.println("[DEBUG] Canteiro criado com ID: " + canteiro.getId());

            // 4. Recalcula área utilizada
            areaDAO.calcularEAtualizarAreaUtilizada(areaId);

            JOptionPane.showMessageDialog(null, 
                "Ordem de produção criada com sucesso!\nPlano: " + plano.getNomePlano() +
                "\nOrdem ID: " + ordem.getId() +
                "\nQuantidade: " + String.format("%.2f", quantidadeCalculada) + " kg",
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (SQLException e) {
            System.out.println("[ERROR] SQLException: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar ordem de produção: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            System.out.println("[ERROR] Exception: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}