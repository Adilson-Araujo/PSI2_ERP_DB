package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import java.awt.*;

public class TelaCanteiro extends JInternalFrame {

    private String cultura;
    private String nomeCanteiro;
    private java.util.Date inicio;
    private double areaM2;
    private double qtdKg;
    private Long canteiroId;
    private Long areaId;
    private JDesktopPane desktop;

    public TelaCanteiro(JDesktopPane desktop, String cultura, String nomeCanteiro, java.util.Date inicio, double areaM2, double qtdKg, Long canteiroId, Long areaId) {
        super("Canteiro - " + nomeCanteiro, true, true, true, true);
        this.desktop = desktop;
        this.cultura = cultura;
        this.nomeCanteiro = nomeCanteiro;
        this.inicio = inicio;
        this.areaM2 = areaM2;
        this.qtdKg = qtdKg;
        this.canteiroId = canteiroId;
        this.areaId = areaId;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        // cores (mesmas da TelaInicial)
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // ======= NAVBAR SUPERIOR =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL (idêntico à TelaInicial) =======
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(verdeEscuro);
        menuLateral.setPreferredSize(new Dimension(220, 800)); // não usar getHeight() no construtor
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.add(Box.createVerticalStrut(30));
        JLabel tituloMenu = new JLabel("Produção", SwingConstants.CENTER);
        tituloMenu.setForeground(Color.WHITE);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 22));
        tituloMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(tituloMenu);
        menuLateral.add(Box.createVerticalStrut(40));
        String[] botoes = {"Tela inicial", "Área de plantio", "Registrar problemas", "Relatório de produção"};
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 15));
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 50));
            botao.setPreferredSize(new Dimension(180, 50));
            botao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 2));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            // Ação do botão
            botao.addActionListener(e -> {
                try {
                    long associadoId = br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao.getAssociadoIdLogado();
                    if (texto.equals("Tela inicial")) {
                        TelaInicial tela = new TelaInicial(desktop);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        dispose();
                    } else if (texto.equals("Área de plantio")) {
                        TelaGerenciarArea tela = new TelaGerenciarArea(desktop);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        dispose();
                    } else if (texto.equals("Registrar problemas")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController(model);
                        TelaRegistrarProblemas tela = new TelaRegistrarProblemas(desktop, controller);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        dispose();
                    } else if (texto.equals("Relatório de produção")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController(model);
                        TelaRelatorioProducao tela = new TelaRelatorioProducao(desktop, controller);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao navegar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            menuLateral.add(botao);
            menuLateral.add(Box.createVerticalStrut(20));
        }
        add(menuLateral, BorderLayout.WEST);

        // ======= CONTEÚDO PRINCIPAL =======
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // --- Painel esquerdo só com botões (FlowLayout.LEFT) ---
        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtons.setOpaque(false);

        JButton btnVoltar = criarBotaoPadrao("Voltar", verdeClaro);
        JButton btnEditar = criarBotaoPadrao("Editar Canteiro", verdeClaro);
        JButton btnPronto = criarBotaoPadrao("Canteiro pronto", verdeClaro);

        // Define tamanho máximo (para garantir que não cresçam além disso)
        Dimension tam = new Dimension(200, 45);
        btnVoltar.setPreferredSize(tam);
        btnVoltar.setMaximumSize(tam);
        btnEditar.setPreferredSize(tam);
        btnEditar.setMaximumSize(tam);
        btnPronto.setPreferredSize(tam);
        btnPronto.setMaximumSize(tam);

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            if (areaId != null) {
                try {
                    br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController controller = 
                        new br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController();
                    br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO area = 
                        controller.carregarAreaCompletaPorId(areaId);
                    if (area != null) {
                        new br.edu.ifsp.hto.cooperativa.producao.visao.TelaTalhao(desktop, area).setVisible(true);
                        dispose();
                    } else {
                        dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dispose();
                }
            } else {
                dispose();
            }
        });

        // Ação do botão Editar Canteiro
        btnEditar.addActionListener(e -> {
            if (canteiroId != null) {
                try {
                    br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO canteiroDAO = 
                        new br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO();
                    br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO canteiro = 
                        canteiroDAO.buscarPorId(canteiroId.intValue());
                    
                    if (canteiro != null) {
                        TelaEditarCanteiro telaEditar = new TelaEditarCanteiro(desktop, canteiro, areaId);
                        desktop.add(telaEditar);
                        telaEditar.setVisible(true);
                        try { telaEditar.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        // Não fecha a tela atual - a TelaEditarCanteiro vai fechar e reabrir quando salvar
                    } else {
                        JOptionPane.showMessageDialog(TelaCanteiro.this, 
                            "Erro: Canteiro não encontrado!", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaCanteiro.this, 
                        "Erro ao abrir tela de edição: " + ex.getMessage(), 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Ação do botão Canteiro Pronto
        btnPronto.addActionListener(e -> finalizarCanteiro());

        leftButtons.add(btnVoltar);
        leftButtons.add(btnEditar);
        leftButtons.add(btnPronto);

        // Adiciona o painel de botões na coluna 0, mesma linha 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        // Não deixamos weightx aqui para não "esticar" o painel de botões
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        conteudo.add(leftButtons, gbc);

        // --- Título na mesma linha, mas ocupando espaço restante ---
        JLabel lblTitulo = new JLabel(nomeCanteiro != null ? nomeCanteiro : "Canteiro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;  // ocupa espaço restante
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST; // faz o título ficar mais à direita (mude para CENTER se quiser centralizar)
        gbc.gridwidth = 2; // espaço para "respirar" (ajuste conforme necessidade)
        conteudo.add(lblTitulo, gbc);

        // --- Painel resumo (igual à TelaInicial) ---
        JPanel painelResumo = new JPanel(new GridLayout(1, 3, 40, 20));
        painelResumo.setOpaque(false);

        // Altura máxima desejada (exemplo: 120px)
        int alturaMax = 100;
        painelResumo.setPreferredSize(new Dimension(0, alturaMax));
        painelResumo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaMax));
        painelResumo.setMinimumSize(new Dimension(0, alturaMax));

        // Painel container para segurar o tamanho
        JPanel containerResumo = new JPanel();
        containerResumo.setLayout(new BoxLayout(containerResumo, BoxLayout.Y_AXIS));
        containerResumo.setOpaque(false);
        containerResumo.add(painelResumo);

        String dataFmt = inicio != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(inicio) : "--/--/----";
        String[] textosResumo = {
            "Cultura: " + (cultura != null ? cultura : ""),
            "Início: " + dataFmt,
            "m²: " + String.format("%.2f", areaM2),
            "Qtd em Kg: " + String.format("%.2f", qtdKg),
        };

        for (String texto : textosResumo) {
            JPanel box = new JPanel(new BorderLayout());
            box.setBackground(Color.WHITE);
            box.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setForeground(verdeEscuro);
            box.add(lbl, BorderLayout.CENTER);
            painelResumo.add(box);
        }

        // GridBag constraints corretos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weighty = 0;           // ← IMPORTANTE! NÃO DEIXAR EXPANDIR
        gbc.fill = GridBagConstraints.HORIZONTAL;
        conteudo.add(containerResumo, gbc);


        // --- Resto: "A fazer" e tabela (igual ao exemplo original) ---
        JLabel lblAFazer = new JLabel("Planos:");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 22));
        lblAFazer.setForeground(verdeEscuro);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblAFazer, gbc);

        GridBagConstraints gbcFim = new GridBagConstraints();
        gbcFim.gridx = 0;
        gbcFim.gridy = 99; // linha muito abaixo de tudo
        gbcFim.weighty = 1; // ocupa 
        gbcFim.fill = GridBagConstraints.VERTICAL;

        conteudo.add(Box.createVerticalGlue(), gbcFim);

        // =============================
        //  ATIVIDADES DO CANTEIRO
        // =============================
        GridBagConstraints gbcTalhao = new GridBagConstraints();
        gbcTalhao.gridx = 0;
        gbcTalhao.gridy = 3;
        gbcTalhao.gridwidth = 4;
        gbcTalhao.weightx = 1;
        gbcTalhao.weighty = 0;
        gbcTalhao.insets = new Insets(10, 20, 10, 20);
        gbcTalhao.fill = GridBagConstraints.HORIZONTAL;
        gbcTalhao.anchor = GridBagConstraints.NORTHWEST;

        // Carregar atividades do canteiro dinamicamente
        if (canteiroId != null) {
            try {
                br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.AtividadeDAO atividadeDAO = 
                    new br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.AtividadeDAO();
                java.util.List<br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeNoCanteiroVO> atividades = 
                    atividadeDAO.buscarAtividadesDoCanteiro(canteiroId.intValue());
                
                // Sempre criar o painel, mesmo se a lista estiver vazia
                if (atividades == null) {
                    atividades = new java.util.ArrayList<>();
                }
                conteudo.add(criarPainelAtividades(atividades), gbcTalhao);
                
            } catch (Exception ex) {
                JLabel lblErro = new JLabel("Erro ao carregar atividades: " + ex.getMessage(), SwingConstants.CENTER);
                lblErro.setForeground(Color.RED);
                conteudo.add(lblErro, gbcTalhao);
                ex.printStackTrace();
            }
        } else {
            JLabel lblSemId = new JLabel("ID do canteiro não informado.", SwingConstants.CENTER);
            lblSemId.setForeground(Color.GRAY);
            conteudo.add(lblSemId, gbcTalhao);
        }

        // =============================
        //  TALHÕES FECHADOS
        // =============================


        // Para empurrar o rodapé para baixo (espaço)
        GridBagConstraints gbcEspaco = new GridBagConstraints();
        gbcEspaco.gridx = 0;
        gbcEspaco.gridy = 99;
        gbcEspaco.weighty = 1;
        gbcEspaco.fill = GridBagConstraints.VERTICAL;

        conteudo.add(Box.createVerticalGlue(), gbcEspaco);

        // Scroll
        JScrollPane scroll = new JScrollPane(conteudo);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);
    }

    // =============================
    //  COMPONENTES AUXILIARES
    // =============================

    // private JPanel criarTalhaoFechado(String titulo) {
    //     JPanel p = new JPanel(new BorderLayout());
    //     p.setMinimumSize(new Dimension(0, 80));
    //     p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
    //     p.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    //     JButton arrow = new JButton("\u25BC");
    //     arrow.setFocusPainted(false);
    //     arrow.setBorder(null);

    //     JLabel lbl = new JLabel(titulo);
    //     lbl.setFont(new Font("Arial", Font.BOLD, 16));
    //     lbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

    //     p.add(lbl, BorderLayout.WEST);
    //     p.add(arrow, BorderLayout.EAST);
    //     return p;
    // }

    private JPanel criarPainelAtividades(java.util.List<br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeNoCanteiroVO> atividades) {
        // Painel principal (contém header + conteúdo)
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ================================
        // CABEÇALHO (sempre visível)
        // ================================
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        cabecalho.setBackground(new Color(230, 230, 230));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("Atividades do Canteiro");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));

        // Botão Nova Atividade
        JButton novaAtv = new JButton("Nova Atividade");
        novaAtv.setPreferredSize(new Dimension(130, 24));
        
        // Ação do botão Nova Atividade
        novaAtv.addActionListener(e -> abrirDialogoNovaAtividade());

        // Seta do drop-down
        JButton arrow = new JButton("\u25BC");   // ▼
        arrow.setFocusPainted(false);
        arrow.setBorderPainted(false);
        arrow.setContentAreaFilled(false);
        arrow.setOpaque(false);
        arrow.setBorder(null);

        // Painel lateral com Nova Atividade + seta alinhados à direita
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(novaAtv);
        rightPanel.add(arrow);

        cabecalho.add(lbl, BorderLayout.WEST);
        cabecalho.add(rightPanel, BorderLayout.EAST);

        bloco.add(cabecalho);

        // ================================
        // CONTEÚDO EXPANDIDO (começa visível)
        // ================================
        JPanel conteudoExpandido = new JPanel();
        conteudoExpandido.setLayout(new BoxLayout(conteudoExpandido, BoxLayout.Y_AXIS));
        conteudoExpandido.setBackground(Color.WHITE);
        conteudoExpandido.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        conteudoExpandido.add(Box.createVerticalStrut(10));

        // Cards dentro do fundo branco - DINÂMICO
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(Color.WHITE);

        if (atividades != null && !atividades.isEmpty()) {
            for (br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeNoCanteiroVO atividadeNoCanteiro : atividades) {
                br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO atividade = atividadeNoCanteiro.getAtividadeVO();
                if (atividade != null) {
                    String nomeAtividade = atividade.getNomeAtividade() != null ? atividade.getNomeAtividade() : "Atividade";
                    String dataAtividade = atividadeNoCanteiro.getDataAtividade() != null ? 
                        new java.text.SimpleDateFormat("dd/MM/yyyy").format(atividadeNoCanteiro.getDataAtividade()) : "--/--/----";
                    String status = atividade.getStatus() != null ? "Status: " + atividade.getStatus() : "Status: Pendente";
                    
                    cards.add(criarCard(nomeAtividade, dataAtividade, status));
                }
            }
        } else {
            JLabel lblVazio = new JLabel("Nenhuma atividade cadastrada. Clique em 'Nova Atividade' para adicionar.");
            lblVazio.setFont(new Font("Arial", Font.ITALIC, 14));
            lblVazio.setForeground(new Color(100, 100, 100));
            cards.add(lblVazio);
        }

        conteudoExpandido.add(cards);
        bloco.add(conteudoExpandido);

        // ================================
        // TOGGLE — clicar na seta abre/fecha
        // ================================
        arrow.addActionListener(e -> {
            boolean visivel = conteudoExpandido.isVisible();
            conteudoExpandido.setVisible(!visivel);
            arrow.setText(visivel ? "\u25B6" : "\u25BC"); // ▶ fechado → ▼ aberto
            bloco.revalidate();
            bloco.repaint();
        });

        return bloco;
    }

    private void finalizarCanteiro() {
        // Confirmação
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja finalizar este canteiro?\n" +
            "Esta ação irá registrar a produção no estoque e não poderá ser desfeita.",
            "Confirmar Finalização",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        if (canteiroId == null || areaId == null) {
            JOptionPane.showMessageDialog(this, "Dados do canteiro incompletos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Buscar canteiro para obter ordem_producao_id
            br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO canteiroDAO = 
                new br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO();
            br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO canteiro = 
                canteiroDAO.buscarPorId(canteiroId.intValue());
            
            if (canteiro == null || canteiro.getOrdemProducaoId() == null) {
                JOptionPane.showMessageDialog(this, "Canteiro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar ordem de produção para obter especie_id
            br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController areaController = 
                new br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController();
            br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO ordem = 
                areaController.buscarOrdemPorId(canteiro.getOrdemProducaoId());
            
            if (ordem == null || ordem.getEspecieId() == null) {
                JOptionPane.showMessageDialog(this, "Ordem de produção não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar área para obter associado_id
            br.edu.ifsp.hto.cooperativa.producao.modelo.dao.AreaDAO areaDAO = 
                new br.edu.ifsp.hto.cooperativa.producao.modelo.dao.AreaDAO();
            br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO area = 
                areaDAO.buscarPorId(areaId);
            
            if (area == null) {
                JOptionPane.showMessageDialog(this, "Área não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter dados para a movimentação
            int especieId = ordem.getEspecieId().intValue();
            int associadoId = (int) area.getAssociadoId();
            float areaProduzida = canteiro.getAreaCanteiroM2() != null ? 
                canteiro.getAreaCanteiroM2().floatValue() : 0.0f;

            if (areaProduzida <= 0) {
                JOptionPane.showMessageDialog(this, "Área do canteiro inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criar movimentação de produção usando ControleEstoque
            br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque controleEstoque = 
                br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque.getInstance();
            
            br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO movimentacao = 
                controleEstoque.novaProducao(especieId, associadoId, areaProduzida);
            
            // Inserir a movimentação no banco
            controleEstoque.inserirProducao(movimentacao);

            // Atualizar status do canteiro para "finalizado" e marcar como inativo
            canteiro.setStatus("finalizado");
            canteiro.setAtivo(false); // IMPORTANTE: inativa o canteiro para liberar a área
            canteiroDAO.atualizar(canteiro);

            // Atualizar status da ordem de produção para "concluido"
            ordem.setStatus("concluido");
            java.sql.Connection conn = null;
            try {
                conn = br.edu.ifsp.hto.cooperativa.ConnectionFactory.getConnection();
                br.edu.ifsp.hto.cooperativa.producao.modelo.dao.OrdemProducaoDAO ordemDAO = 
                    new br.edu.ifsp.hto.cooperativa.producao.modelo.dao.OrdemProducaoDAO(conn);
                ordemDAO.atualizar(ordem);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (java.sql.SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            JOptionPane.showMessageDialog(
                this, 
                "Canteiro finalizado com sucesso!\n" +
                "Produção registrada no estoque: " + String.format("%.2f", movimentacao.getQuantidade()) + " kg",
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE
            );

            // Voltar para TelaTalhao
            if (areaId != null) {
                br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO areaRecarregada = 
                    areaController.carregarAreaCompletaPorId(areaId);
                if (areaRecarregada != null) {
                    TelaTalhao tela = new TelaTalhao(desktop, areaRecarregada);
                    desktop.add(tela);
                    tela.setVisible(true);
                    try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                    dispose();
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, 
                "Erro ao finalizar canteiro: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    private void abrirDialogoNovaAtividade() {
        if (canteiroId == null) {
            JOptionPane.showMessageDialog(this, "ID do canteiro não disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Buscar todas as atividades disponíveis
            br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.AtividadeDAO atividadeDAO = 
                new br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.AtividadeDAO();
            java.util.List<br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO> atividadesDisponiveis = 
                atividadeDAO.listarTodas();

            if (atividadesDisponiveis == null || atividadesDisponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há atividades disponíveis no sistema.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Criar array para o dropdown
            br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO[] atividadesArray = 
                atividadesDisponiveis.toArray(new br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO[0]);

            // Mostrar dropdown de seleção
            br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO atividadeSelecionada = 
                (br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeVO) JOptionPane.showInputDialog(
                    this,
                    "Selecione a atividade a adicionar:",
                    "Nova Atividade",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    atividadesArray,
                    atividadesArray.length > 0 ? atividadesArray[0] : null
                );

            if (atividadeSelecionada == null) return; // usuário cancelou

            // Solicitar tempo gasto (em horas)
            String tempoStr = JOptionPane.showInputDialog(this, "Tempo estimado (em horas):", "0.0");
            if (tempoStr == null) return; // cancelou
            
            float tempoGastoHoras = 0.0f;
            try {
                tempoGastoHoras = Float.parseFloat(tempoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tempo inválido. Use formato decimal (ex: 2.5)", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Solicitar data da atividade
            String dataStr = JOptionPane.showInputDialog(this, "Data da atividade (dd/MM/yyyy):", 
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            if (dataStr == null) return; // cancelou
            
            java.sql.Date dataAtividade;
            try {
                java.util.Date dataParsed = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
                dataAtividade = new java.sql.Date(dataParsed.getTime());
            } catch (java.text.ParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy (ex: 02/12/2025)", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Inserir na tabela atividade_canteiro
            br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.CanteiroDAO canteiroDAO = 
                new br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.CanteiroDAO();
            canteiroDAO.adicionarAtividade(canteiroId.intValue(), atividadeSelecionada.getId(), tempoGastoHoras, dataAtividade);

            JOptionPane.showMessageDialog(this, "Atividade adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Recarregar a tela
            dispose();
            TelaCanteiro novaTela = new TelaCanteiro(desktop, cultura, nomeCanteiro, inicio, areaM2, qtdKg, canteiroId, areaId);
            desktop.add(novaTela);
            novaTela.setVisible(true);
            try { novaTela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar atividade: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private JPanel criarCard(String titulo, String data, String prioridade) {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200, 150));
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(240, 240, 230));

        JLabel l1 = new JLabel(titulo, SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l2 = new JLabel(data, SwingConstants.CENTER);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l3 = new JLabel(prioridade, SwingConstants.CENTER);
        l3.setFont(new Font("Arial", Font.BOLD, 14));
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton ver = new JButton("Ver");
        ver.setAlignmentX(Component.CENTER_ALIGNMENT);
        ver.setBackground(new Color(150, 160, 80));
        ver.setForeground(Color.WHITE);
        ver.setFont(new Font("Arial", Font.BOLD, 14));
        ver.setFocusPainted(false);
        ver.setPreferredSize(new Dimension(150, 35));
        ver.setMaximumSize(new Dimension(150, 35));  // impede esticar
        
        // Ação do botão Ver: navegar para TelaAtividades
        ver.addActionListener(e -> {
            TelaAtividades telaAtividades = new TelaAtividades(desktop, cultura, nomeCanteiro, inicio, areaM2, qtdKg, canteiroId, areaId);
            desktop.add(telaAtividades);
            telaAtividades.setVisible(true);
            try { telaAtividades.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
            TelaCanteiro.this.dispose();
        });

        p.add(Box.createVerticalStrut(10));
        p.add(l1);
        p.add(Box.createVerticalStrut(10));
        p.add(l2);
        p.add(Box.createVerticalStrut(10));
        p.add(l3);
        p.add(Box.createVerticalStrut(15));
        p.add(ver);

        return p;
    }

    private JButton criarBotaoPadrao(String texto, Color corFundo) {
        JButton b = new JButton(texto);
        b.setBackground(corFundo);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 18));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    // Remover main de teste ou manter conforme necessidade
}
