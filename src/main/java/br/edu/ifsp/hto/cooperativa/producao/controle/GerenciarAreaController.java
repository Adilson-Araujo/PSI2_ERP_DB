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
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO; // 🔑 Novo Import para fetch especie nome

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
     * Remove (marca como deletado) uma ordem de produção e inativa seus canteiros.
     * Retorna true em sucesso, false em erro.
     */
    public boolean removerOrdem(Long ordemId) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
            CanteiroDAO canteiroDAO = new CanteiroDAO();
            
            // Marca ordem como deletado
            // Busca a ordem para obter o talhaoId
            OrdemProducaoVO ordem = ordemDAO.buscarPorId(ordemId);
            if (ordem == null) {
                conn.close();
                JOptionPane.showMessageDialog(null, "Ordem não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            Long talhaoId = ordem.getTalhaoId();

            // Marca ordem como deletado
            ordemDAO.deletar(ordemId);

            // Inativa todos os canteiros da ordem
            canteiroDAO.inativarCanteirosDaOrdem(ordemId);

            // Após inativar, verifica se existem canteiros ativos restantes neste talhão
            boolean inativarTalhao = false;
            if (talhaoId != null) {
                int restantes = canteiroDAO.contarCanteirosAtivosPorTalhao(talhaoId);
                if (restantes == 0) {
                    inativarTalhao = true;
                }
            }

            if (inativarTalhao && talhaoId != null) {
                // Inativa o talhão (usa TalhaoDAO)
                try {
                    talhaoDAO.inativarTalhao(talhaoId);
                } catch (SQLException ex) {
                    System.err.println("Falha ao inativar talhão " + talhaoId + ": " + ex.getMessage());
                }
            }

            conn.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover ordem de produção: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Retorna lista de ordens de produção ativas de uma área específica.
     */
    public List<OrdemProducaoVO> listarOrdensAtivas(Long areaId) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
            List<OrdemProducaoVO> ordens = ordemDAO.listarPorAreaId(areaId);
            conn.close();
            return ordens;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ordens: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    /**
     * Busca uma ordem de produção por ID.
     */
    public OrdemProducaoVO buscarOrdemPorId(Long ordemId) {
        try {
            java.sql.Connection conn = ConnectionFactory.getConnection();
            OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
            OrdemProducaoVO vo = ordemDAO.buscarPorId(ordemId);
            conn.close();
            return vo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ordem: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
    
    /**
     * Busca um talhão completo pelo ID, incluindo todas as suas propriedades (nome, área, status, etc.).
     * Retorna null se não encontrar ou houver erro.
     */
    public TalhaoVO buscarTalhaoPorId(Long talhaoId) {
        try {
            return talhaoDAO.buscarPorId(talhaoId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar talhão: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return null;
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
                // 3. Carrega os Talhões desta área (com seus campos básicos)
                try {
                    java.util.List<TalhaoVO> talhoes = talhaoDAO.buscarPorAreaId(areaId);
                    area.setTalhoes(talhoes);

                    // Opcional: carregar canteiros por talhão (ajuda a visualizar na tela)
                    for (TalhaoVO t : talhoes) {
                        try {
                            java.util.List<CanteiroVO> canteirosTalhao = canteiroDAO.buscarPorTalhaoId(t.getId());
                            t.setCanteiros(canteirosTalhao);
                        } catch (Exception ex) {
                            // Não interrompe o carregamento se falhar; apenas log
                            System.err.println("Erro ao carregar canteiros do talhão " + t.getId() + ": " + ex.getMessage());
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Erro ao carregar talhões da área " + areaId + ": " + ex.getMessage());
                }

                // 4. Carrega as Ordens de Produção (e seus canteiros)
                carregarOrdensComCanteiros(area);
            }
            return area;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar detalhes da área: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Carrega as ordens de produção ativas de uma área e seus respectivos canteiros.
     */
    private void carregarOrdensComCanteiros(AreaVO area) throws SQLException {
        List<OrdemProducaoVO> ordens = listarOrdensAtivas(area.getId());
        
        // Para cada ordem, carrega seus canteiros e nome da espécie
        for (OrdemProducaoVO ordem : ordens) {
            List<CanteiroVO> canteiros = canteiroDAO.buscarPorOrdemProducaoId(ordem.getId());
            ordem.setCanteiros(canteiros);
            
            // Busca e popula o nome da espécie
            try {
                if (ordem.getEspecieId() != null) {
                    var especie = EspecieDAO.getInstance().buscarPorId(ordem.getEspecieId().intValue());
                    if (especie != null) {
                        ordem.setNomeEspecie(especie.getNome());
                    }
                }
            } catch (Exception e) {
                System.err.println("Erro ao buscar espécie para ordem " + ordem.getId() + ": " + e.getMessage());
            }
        }
        
        // Anexa as ordens à área
        area.setOrdens(ordens);
    }

    /**
     * Cria uma ordem de produção a partir de um plano selecionado.
     * Insere ordem_producao e canteiro, recalcula área utilizada.
     */
    public boolean criarOrdemEProducao(Long areaId, Integer planoId) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            // Ajusta a sequência do id da ordem_producao para evitar conflito de chave duplicada
            try (java.sql.Statement stmt = conn.createStatement()) {
                stmt.execute("SELECT setval('ordem_producao_id_seq', (SELECT COALESCE(MAX(id),0) FROM ordem_producao));");
            }

            // 1. Busca o plano
            PlanoDAO planoDAO = new PlanoDAO();
            PlanoVO plano = planoDAO.buscarPorId(planoId);
            if (plano == null) {
                JOptionPane.showMessageDialog(null, "Plano não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            System.out.println("[DEBUG] Plano encontrado: ID=" + plano.getId() + ", Nome=" + plano.getNomePlano());

            // 2. VALIDAÇÃO PRÉVIA: Verifica se há área disponível no talhão ANTES de criar a ordem
            Long talhaoId = (long) plano.getTalhaoId();
            double areaNecessaria = Double.parseDouble(plano.getAreaCultivo() + "");
            
            System.out.println("[DEBUG VALIDAÇÃO] ========================================");
            System.out.println("[DEBUG VALIDAÇÃO] Iniciando validação de área...");
            System.out.println("[DEBUG VALIDAÇÃO] Talhão ID: " + talhaoId);
            System.out.println("[DEBUG VALIDAÇÃO] Área necessária (do plano): " + areaNecessaria + " m²");
            
            TalhaoVO talhao = null;
            try {
                talhao = talhaoDAO.buscarPorId(talhaoId);
                System.out.println("[DEBUG VALIDAÇÃO] Talhão encontrado: " + (talhao != null ? talhao.getNome() : "NULL"));
            } catch (Exception ex) {
                System.out.println("[DEBUG VALIDAÇÃO] ERRO ao buscar talhão: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, 
                    "Erro ao verificar disponibilidade do talhão: " + ex.getMessage(),
                    "Erro de Validação", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (talhao != null && talhao.getAreaTalhao() != null) {
                double areaTalhao = talhao.getAreaTalhao().doubleValue();
                System.out.println("[DEBUG VALIDAÇÃO] Área total do talhão: " + areaTalhao + " m²");
                
                // Calcula a área já utilizada por canteiros ativos deste talhão
                java.util.List<CanteiroVO> canteirosExistentes = canteiroDAO.buscarPorTalhaoId(talhaoId);
                System.out.println("[DEBUG VALIDAÇÃO] Canteiros existentes no talhão: " + canteirosExistentes.size());
                
                double areaUtilizada = 0.0;
                for (CanteiroVO c : canteirosExistentes) {
                    if (c.getAreaCanteiroM2() != null) {
                        double areaCanteiro = c.getAreaCanteiroM2().doubleValue();
                        System.out.println("[DEBUG VALIDAÇÃO]   - Canteiro ID " + c.getId() + ": " + areaCanteiro + " m²");
                        areaUtilizada += areaCanteiro;
                    }
                }
                
                System.out.println("[DEBUG VALIDAÇÃO] Área utilizada total: " + areaUtilizada + " m²");
                double areaRestante = areaTalhao - areaUtilizada;
                System.out.println("[DEBUG VALIDAÇÃO] Área restante: " + areaRestante + " m²");
                System.out.println("[DEBUG VALIDAÇÃO] Verificando: " + areaNecessaria + " > " + areaRestante + " ?");
                
                // ❌ BLOQUEIA SE NÃO HOUVER ESPAÇO SUFICIENTE
                if (areaNecessaria > areaRestante) {
                    System.out.println("[DEBUG VALIDAÇÃO] ❌ BLOQUEADO! Área insuficiente!");
                    String nomeTalhao = talhao.getNome() != null ? talhao.getNome() : "Talhão " + talhaoId;
                    JOptionPane.showMessageDialog(null, 
                        "❌ Não é possível usar este plano!\n\n" +
                        "Plano: " + plano.getNomePlano() + "\n" +
                        "Talhão: " + nomeTalhao + "\n" +
                        "Área necessária: " + String.format("%.2f", areaNecessaria) + " m²\n" +
                        "Área disponível: " + String.format("%.2f", areaRestante) + " m²\n\n" +
                        "O talhão não possui espaço suficiente para esta ordem de produção.",
                        "Espaço Insuficiente", 
                        JOptionPane.WARNING_MESSAGE);
                    System.out.println("[DEBUG VALIDAÇÃO] ========================================");
                    return false;
                }
                
                System.out.println("[DEBUG VALIDAÇÃO] ✅ Validação OK! Prosseguindo com criação da ordem...");
                System.out.println("[DEBUG VALIDAÇÃO] ========================================");
            } else {
                System.out.println("[DEBUG VALIDAÇÃO] ⚠️ AVISO: Talhão ou área do talhão é NULL - validação pulada!");
                System.out.println("[DEBUG VALIDAÇÃO] ========================================");
            }

            // 3. Cria OrdemProducaoVO e insere (SEM definir ID, deixa o banco gerar)
            OrdemProducaoVO ordem = new OrdemProducaoVO();
            ordem.setId(null); // Garante que o id será gerado pelo banco
            ordem.setPlanoId(planoId);
            ordem.setEspecieId((long) plano.getEspecieId());
            ordem.setTalhaoId(talhaoId);
            ordem.setNomePlano(plano.getNomePlano());
            ordem.setDescricao(plano.getDescricao());
            ordem.setDataInicio(plano.getDataInicio());
            ordem.setDataFim(plano.getDataFim());
            ordem.setObservacoes(plano.getObservacoes());
            ordem.setAreaCultivo(areaNecessaria);
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
            // Nome do canteiro: "Canteiro " + nome da espécie
            String nomeEspecie = ordem.getNomeEspecie();
            if (nomeEspecie == null || nomeEspecie.isEmpty()) {
                // Busca nome da espécie se não estiver preenchido
                try {
                    var especie = EspecieDAO.getInstance().buscarPorId(ordem.getEspecieId().intValue());
                    if (especie != null) {
                        nomeEspecie = especie.getNome();
                    }
                } catch (Exception e) {
                    nomeEspecie = "";
                }
            }
            canteiro.setNome("Canteiro " + nomeEspecie);
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