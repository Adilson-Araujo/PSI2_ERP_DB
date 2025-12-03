package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO; // 🔑 NOVO IMPORT
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO; // 🔑 NOVO IMPORT
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO; // 🔑 NOVO IMPORT

import java.awt.*;
import java.util.List; // Import necessário para lidar com List<TalhaoVO>
import java.awt.event.ActionListener; // Import necessário para o listener
import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController; // Controller
import java.math.BigDecimal;

public class TelaTalhao extends JFrame {

    private AreaVO area;
    private GerenciarAreaController controller = new GerenciarAreaController();
    // Cores definidas como campos da classe para acesso em todos os métodos
    private final Color verdeEscuro = new Color(63, 72, 23);
    private final Color verdeClaro = new Color(157, 170, 61);
    private final Color cinzaFundo = new Color(240, 240, 240);
    
    // Supondo que você tenha a classe TelaGerenciarArea
    // private TelaGerenciarArea telaAnterior; 

    // O construtor é o mesmo, mas o conteúdo é movido para initComponents()
    public TelaTalhao(AreaVO area) {
        // Recarrega a área completa (inclui talhões, ordens e recalcula área utilizada)
        this.area = area;
        try {
            AreaVO recarregada = controller.carregarAreaCompletaPorId(area.getId());
            if (recarregada != null) this.area = recarregada;
        } catch (Exception ex) {
            // Se falhar, continua com a área fornecida (fallback)
            System.err.println("Aviso: falha ao recarregar área completa: " + ex.getMessage());
        }

        initComponents();
    }
    
    // Se você migrou para o padrão initComponents, encapsule todo o corpo do construtor nele.
    private void initComponents() { 
        setTitle("Área - " + area.getNome());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= NAVBAR SUPERIOR =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL (idêntico à TelaInicial) =======
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(verdeEscuro);
        menuLateral.setPreferredSize(new Dimension(220, 800));
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
            // 🔑 ADIÇÃO: Listener de Evento para o botão
            botao.addActionListener(e -> {
                if (texto.equals("Tela inicial")) {
                    // AÇÃO CORRETA para o botão "Tela inicial"
                    long associadoId = br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao.getAssociadoIdLogado();
                    new br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial(associadoId).setVisible(true);
                    dispose(); // Fecha a tela atual (TelaGerenciarArea)

                } else if (texto.equals("Área de plantio")) {
                    new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea().setVisible(true);
                    dispose();

                } else if (texto.equals("Registrar problemas")) {
                    // Adicionar lógica para Registrar problemas
                
                } else if (texto.equals("Relatório de produção")) {
                    // Adicionar lógica para Relatório de produção
                }
            });
            menuLateral.add(botao);
            menuLateral.add(Box.createVerticalStrut(20));
        }
        add(menuLateral, BorderLayout.WEST);

        // ======= CONTEÚDO PRINCIPAL =======
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        // Não adicionamos o 'conteudo' diretamente ao frame aqui, mas sim o ScrollPane
        // add(conteudo, BorderLayout.CENTER); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // ... (Configurações e adição dos botões (Voltar, Editar, Adicionar)) ...
        // Linha superior: somente o botão Voltar
        JPanel leftButtonsTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtonsTop.setOpaque(false);

        JButton btnVoltar = criarBotaoPadrao("Voltar", verdeClaro);
        btnVoltar.addActionListener(e -> {
            new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea().setVisible(true);
            dispose();
        });

        Dimension tam = new Dimension(180, 45);
        btnVoltar.setPreferredSize(tam); btnVoltar.setMaximumSize(tam);

        leftButtonsTop.add(btnVoltar);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0; gbc.gridwidth = 1;
        conteudo.add(leftButtonsTop, gbc);

        // Linha abaixo: os demais botões (remover, adicionar, editar, plano)
        JPanel leftButtonsBelow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtonsBelow.setOpaque(false);

        JButton btnRemover = criarBotaoPadrao("Remover Ordem", verdeClaro);
        JButton btnEditar = criarBotaoPadrao("Editar Talhão", verdeClaro);
        JButton btnPlano = criarBotaoPadrao("Usar Plano", verdeClaro);

        btnRemover.setPreferredSize(tam); btnRemover.setMaximumSize(tam);
        btnEditar.setPreferredSize(tam); btnEditar.setMaximumSize(tam);
        btnPlano.setPreferredSize(tam); btnPlano.setMaximumSize(tam);

        leftButtonsBelow.add(btnRemover);
        leftButtonsBelow.add(btnEditar);
        leftButtonsBelow.add(btnPlano);

        // Ação do botão Editar Talhão: abre dropdown com talhões e edita o selecionado
        btnEditar.addActionListener(e -> {
            // Mapear talhões disponíveis
            java.util.Map<Long, TalhaoVO> mapTalhoes = new java.util.LinkedHashMap<>();
            if (area.getTalhoes() != null) {
                for (TalhaoVO t : area.getTalhoes()) {
                    mapTalhoes.put(t.getId(), t);
                }
            }

            if (mapTalhoes.isEmpty()) {
                JOptionPane.showMessageDialog(TelaTalhao.this, 
                    "Não há talhões disponíveis para editar.", 
                    "Aviso", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Criar array de nomes para o dropdown
            String[] nomesTalhoes = new String[mapTalhoes.size()];
            java.util.Map<String, TalhaoVO> mapNomeTalhao = new java.util.LinkedHashMap<>();
            int idx = 0;
            for (TalhaoVO t : mapTalhoes.values()) {
                String nome = t.getNome() + " (ID: " + t.getId() + ")";
                nomesTalhoes[idx++] = nome;
                mapNomeTalhao.put(nome, t);
            }

            // Exibir dropdown
            String escolhido = (String) JOptionPane.showInputDialog(
                TelaTalhao.this,
                "Selecione o talhão para editar:",
                "Editar Talhão",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesTalhoes,
                nomesTalhoes[0]
            );

            if (escolhido != null) {
                TalhaoVO talhaoEscolhido = mapNomeTalhao.get(escolhido);
                if (talhaoEscolhido != null) {
                    TelaEditarTalhao telaEditar = new TelaEditarTalhao(talhaoEscolhido, area.getId());
                    telaEditar.setVisible(true);
                    // Não fecha a tela atual - a TelaEditarTalhao vai fechar e reabrir quando salvar
                }
            }
        });

        // Ação do botão Usar Plano: mostra dropdown com planos
        btnPlano.addActionListener(e -> {
            try {
                // Busca planos do planejamento
                br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.PlanoDAO planoDAO = 
                    new br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.PlanoDAO();
                java.util.List<br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO> planos = planoDAO.listarPorAreaId(area.getId(), area.getAssociadoId());

                if (planos == null || planos.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaTalhao.this, "Não há planos disponíveis.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Cria array de planos para exibir no dropdown
                br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO[] planosArray = 
                    planos.toArray(new br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO[0]);

                // Exibe dropdown
                br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO escolhido = 
                    (br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO) JOptionPane.showInputDialog(
                        TelaTalhao.this,
                        "Selecione um plano para usar:",
                        "Usar Plano",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        planosArray,
                        planosArray.length > 0 ? planosArray[0] : null
                    );

                if (escolhido != null) {
                    // Cria a ordem de produção a partir do plano selecionado
                    boolean ok = controller.criarOrdemEProducao(area.getId(), escolhido.getId());
                    if (ok) {
                        // Recarrega a área completa
                        AreaVO nova = controller.carregarAreaCompletaPorId(area.getId());
                        if (nova != null) {
                            TelaTalhao.this.area = nova;
                            getContentPane().removeAll();
                            initComponents();
                            revalidate();
                            repaint();
                        }
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TelaTalhao.this, 
                    "Erro ao buscar planos: " + ex.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Ação do botão Remover: remove ordem de produção (marca como deletado)
        btnRemover.addActionListener(e -> {
            // Coleta todas as ordens ativas da área
            List<OrdemProducaoVO> ordensAtivas = controller.listarOrdensAtivas(area.getId());

            if (ordensAtivas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há ordens ativas para remover.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Cria dropdown com as ordens (exibe informação da ordem: ID, plano e talhão)
            String[] nomes = new String[ordensAtivas.size()];
            java.util.Map<String, OrdemProducaoVO> mapOrdens = new java.util.LinkedHashMap<>();
            
            for (int i = 0; i < ordensAtivas.size(); i++) {
                OrdemProducaoVO ordem = ordensAtivas.get(i);
                String nomePlano = ordem.getNomePlano() != null ? ordem.getNomePlano() : "Plano";
                String nomeTalhao = ordem.getNomeTalhao() != null ? ordem.getNomeTalhao() : "Talhão " + (ordem.getTalhaoId() != null ? ordem.getTalhaoId() : "?");
                String label = "Ordem ID: " + ordem.getId() + " - " + nomePlano + " - " + nomeTalhao;
                nomes[i] = label;
                mapOrdens.put(label, ordem);
            }

            String escolhido = (String) JOptionPane.showInputDialog(this, "Selecione a ordem a remover:", "Remover Ordem",
                    JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);

            if (escolhido == null) return; // cancelou

            OrdemProducaoVO ordemEscolhida = mapOrdens.get(escolhido);
            if (ordemEscolhida == null) return;

            String nomeExibicao = "Ordem ID " + ordemEscolhida.getId() + " - " + (ordemEscolhida.getNomePlano() != null ? ordemEscolhida.getNomePlano() : "Plano");
            int conf = JOptionPane.showConfirmDialog(this, "Confirma remover a ordem '" + nomeExibicao + "'?\nIsto inativará o canteiro relacionado e, se não restarem outros canteiros no talhão, também inativará o talhão.", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf != JOptionPane.YES_OPTION) return;

            boolean ok = controller.removerOrdem(ordemEscolhida.getId());
            if (ok) {
                // Recarrega a área completa
                AreaVO nova = controller.carregarAreaCompletaPorId(area.getId());
                if (nova != null) {
                    this.area = nova;
                    getContentPane().removeAll();
                    initComponents();
                    revalidate();
                    repaint();
                    JOptionPane.showMessageDialog(this, "Ordem removida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Ordem removida, porém falha ao recarregar área.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0; gbc.gridwidth = 1;
        conteudo.add(leftButtonsBelow, gbc);

        JLabel lblTitulo = new JLabel(area.getNome(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.EAST; gbc.gridwidth = 2;
        conteudo.add(lblTitulo, gbc);

        // --- Painel resumo (igual à TelaInicial) ---
        JPanel painelResumo = new JPanel(new GridLayout(1, 3, 40, 20));
        painelResumo.setOpaque(false);

        int alturaMax = 100;
        painelResumo.setPreferredSize(new Dimension(0, alturaMax));
        painelResumo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaMax));
        painelResumo.setMinimumSize(new Dimension(0, alturaMax));

        JPanel containerResumo = new JPanel();
        containerResumo.setLayout(new BoxLayout(containerResumo, BoxLayout.Y_AXIS));
        containerResumo.setOpaque(false);
        containerResumo.add(painelResumo);

        String[] textosResumo = {
            "Nome: " + area.getNome(),
            "Área Total: " + String.format("%.2f", area.getAreaTotal()) + " m²", 
            // Cálculo dinâmico, já que areaTotal e areaUtilizada são double/BigDecimal
            "Área Restante: " + String.format("%.2f", area.getAreaTotal() - area.getAreaUtilizada()) + " m²",
            "pH do solo: " + String.format("%.1f", area.getPh()),
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

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        conteudo.add(containerResumo, gbc);


        // --- Resto: "Ordens de Produção Ativas" ---
        JLabel lblAFazer = new JLabel("Ordens de Produção Ativas:");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 22));
        lblAFazer.setForeground(verdeEscuro);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.weighty = 0; gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblAFazer, gbc);

        // ===============================================
        // NOVA LÓGICA: AGRUPAR CANTEIROS POR TALHAO
        // ===============================================
        int linhaAtual = 4;

        // Mapear talhaoId -> TalhaoVO (usando lista de talhoes da area)
        java.util.Map<Long, TalhaoVO> mapTalhoes = new java.util.LinkedHashMap<>();
        if (area.getTalhoes() != null) {
            for (TalhaoVO t : area.getTalhoes()) {
                mapTalhoes.put(t.getId(), t);
            }
        }

        // Mapear talhaoId -> lista de canteiros (de todas as ordens)
        java.util.Map<Long, java.util.List<CanteiroVO>> mapCanteirosPorTalhao = new java.util.LinkedHashMap<>();
        if (area.getOrdens() != null) {
            for (OrdemProducaoVO ordem : area.getOrdens()) {
                Long talhaoId = ordem.getTalhaoId();
                if (talhaoId == null) continue;

                // Se o talhão não existe no mapa de talhões (possivelmente não foi carregado),
                // buscamos ele do banco de dados para obter todas as informações, incluindo a área.
                if (!mapTalhoes.containsKey(talhaoId)) {
                    TalhaoVO talhaoCompleto = controller.buscarTalhaoPorId(talhaoId);
                    if (talhaoCompleto != null) {
                        mapTalhoes.put(talhaoId, talhaoCompleto);
                    } else {
                        // Se não conseguir buscar, cria um sintético com nome da ordem
                        TalhaoVO synth = new TalhaoVO();
                        synth.setId(talhaoId);
                        String nomeFromOrdem = ordem.getNomeTalhao();
                        if (nomeFromOrdem == null || nomeFromOrdem.trim().isEmpty()) {
                            nomeFromOrdem = "Talhão " + talhaoId;
                        }
                        synth.setNome(nomeFromOrdem);
                        synth.setStatus(ordem.getStatus() != null ? ordem.getStatus() : "");
                        // ⚠️ IMPORTANTE: Define área como 0.0 para evitar null
                        synth.setAreaTalhao(java.math.BigDecimal.ZERO);
                        mapTalhoes.put(talhaoId, synth);
                    }
                }

                if (!mapCanteirosPorTalhao.containsKey(talhaoId)) {
                    mapCanteirosPorTalhao.put(talhaoId, new java.util.ArrayList<>());
                }
                if (ordem.getCanteiros() != null) {
                    mapCanteirosPorTalhao.get(talhaoId).addAll(ordem.getCanteiros());
                }
            }
        }

        if (!mapCanteirosPorTalhao.isEmpty()) {
            for (Long talhaoId : mapCanteirosPorTalhao.keySet()) {
                TalhaoVO talhao = mapTalhoes.get(talhaoId);
                java.util.List<CanteiroVO> canteiros = mapCanteirosPorTalhao.get(talhaoId);
                JPanel painelTalhao = criarPainelTalhaoAgrupado(talhao, canteiros);

                GridBagConstraints gbcTalhao = new GridBagConstraints();
                gbcTalhao.gridx = 0;
                gbcTalhao.gridy = linhaAtual++;
                gbcTalhao.gridwidth = 4;
                gbcTalhao.weightx = 1;
                gbcTalhao.weighty = 0;
                gbcTalhao.insets = new Insets(10, 20, 10, 20);
                gbcTalhao.fill = GridBagConstraints.HORIZONTAL;
                gbcTalhao.anchor = GridBagConstraints.NORTHWEST;

                conteudo.add(painelTalhao, gbcTalhao);
            }
        } else {
            JLabel lblSemOrdens = new JLabel("Não há ordens de produção ativas nesta área.", SwingConstants.CENTER);
            lblSemOrdens.setFont(new Font("Arial", Font.ITALIC, 18));
            lblSemOrdens.setForeground(new Color(100, 100, 100));

            GridBagConstraints gbcVazio = new GridBagConstraints();
            gbcVazio.gridx = 0;
            gbcVazio.gridy = linhaAtual++;
            gbcVazio.gridwidth = 4;
            gbcVazio.insets = new Insets(40, 20, 40, 20);
            gbcVazio.anchor = GridBagConstraints.CENTER;

            conteudo.add(lblSemOrdens, gbcVazio);
        }
            // NOVO MÉTODO: Painel de talhão agrupando canteiros de todas as ordens
        
        // Para empurrar o rodapé para baixo (espaço)
        GridBagConstraints gbcEspaco = new GridBagConstraints();
        gbcEspaco.gridx = 0;
        gbcEspaco.gridy = linhaAtual + 1; // Coloca o espaçador logo após o último talhão
        gbcEspaco.weighty = 1; // Faz ele expandir e empurrar o conteúdo para cima
        gbcEspaco.fill = GridBagConstraints.VERTICAL;

        conteudo.add(Box.createVerticalGlue(), gbcEspaco);

        // Scroll
        JScrollPane scroll = new JScrollPane(conteudo);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);
    } // Fim de initComponents()

    // ====================================================================
    // 🔑 NOVO COMPONENTE: CRIAR PAINEL TALHÃO (Substitui criarPainelTalhaoExpandido)
    // ====================================================================

    // ====================================================================
    // 🔑 NOVO MÉTODO: CRIAR PAINEL ORDEM (para ordens de produção)
    // ====================================================================

    private JPanel criarPainelOrdem(OrdemProducaoVO ordem) {
        
        // Painel principal da ordem (contém header + conteúdo)
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // --- 1. CABEÇALHO (sempre visível) ---
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        cabecalho.setBackground(new Color(230, 230, 230));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nome do Talhão e Status da Ordem
        String nomoCabecalho = ordem.getNomeTalhao() != null ? ordem.getNomeTalhao() : "Ordem " + ordem.getId();
        JLabel lbl = new JLabel(nomoCabecalho + " (ID: " + ordem.getId() + ") | Status: " + ordem.getStatus() + " | Área: " + String.format("%.2f", ordem.getAreaCultivo()) + " m²");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));

        // Seta do drop-down
        JButton arrow = new JButton("\u25BC"); 
        arrow.setFocusPainted(false);
        arrow.setBorderPainted(false);
        arrow.setContentAreaFilled(false);
        arrow.setOpaque(false);
        arrow.setBorder(null);

        // Painel lateral com seta alinhada à direita
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(arrow);

        cabecalho.add(lbl, BorderLayout.WEST);
        cabecalho.add(rightPanel, BorderLayout.EAST);
        bloco.add(cabecalho);

        // --- 2. CONTEÚDO EXPANDIDO (Canteiros da Ordem) ---
        JPanel conteudoExpandido = new JPanel();
        conteudoExpandido.setLayout(new BoxLayout(conteudoExpandido, BoxLayout.Y_AXIS));
        conteudoExpandido.setBackground(Color.WHITE);
        conteudoExpandido.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelTitulo.setBackground(Color.WHITE);

        JLabel lblCanteiros = new JLabel("Canteiros da Ordem:");
        lblCanteiros.setFont(new Font("Arial", Font.BOLD, 16));
        painelTitulo.add(lblCanteiros);
        conteudoExpandido.add(painelTitulo);

        conteudoExpandido.add(Box.createVerticalStrut(10));

        // Painel de cards
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(Color.WHITE);

        // 🔑 ITERAÇÃO DINÂMICA SOBRE CANTEIROS
        if (ordem.getCanteiros() != null && !ordem.getCanteiros().isEmpty()) {
            for (CanteiroVO canteiro : ordem.getCanteiros()) {
                String nomeCanteiro = "Canteiro " + (ordem.getNomeEspecie() != null ? ordem.getNomeEspecie() : ordem.getNomePlano());
                String infoCanteiro = String.format("%.2f", canteiro.getKgGerados()) + " kg";
                JPanel card = criarCard(nomeCanteiro, infoCanteiro, canteiro.getStatus());
                // Abre TelaCanteiro com dados ao clicar no botão de detalhes
                // Procuramos o botão "Ver Detalhes" no card e adicionamos ação
                for (Component comp : card.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton btn = (JButton) comp;
                        if ("Ver Detalhes".equals(btn.getText()) || "Ver".equals(btn.getText())) {
                            btn.addActionListener(ev -> {
                                // Cultura/especie
                                String cultura = ordem.getNomeEspecie() != null ? ordem.getNomeEspecie() : (ordem.getNomePlano() != null ? ordem.getNomePlano() : "");
                                // Nome do canteiro (título)
                                String titulo = nomeCanteiro;
                                // Início (dataExecucao da ordem)
                                java.util.Date inicio = ordem.getDataExecucao();
                                // Área m² (área do canteiro)
                                double areaM2 = canteiro.getAreaCanteiroM2() != null ? canteiro.getAreaCanteiroM2().doubleValue() : 0.0;
                                // Qtd em Kg
                                double qtdKg = canteiro.getKgGerados() != null ? canteiro.getKgGerados().doubleValue() : 0.0;
                                Long canteiroId = canteiro.getId();
                                Long areaId = area != null ? area.getId() : null;

                                TelaCanteiro tela = new TelaCanteiro(cultura, titulo, inicio, areaM2, qtdKg, canteiroId, areaId);
                                tela.setVisible(true);
                                TelaTalhao.this.dispose();
                            });
                        }
                    }
                }
                cards.add(card);
            }
        } else {
            JLabel lblVazio = new JLabel("Nenhum canteiro cadastrado nesta ordem.");
            cards.add(lblVazio);
        }

        conteudoExpandido.add(cards);
        bloco.add(conteudoExpandido);

        return bloco;
    }

    // ====================================================================
    // 🔑 NOVO MÉTODO: CRIAR PAINEL TALHÃO AGRUPADO
    // ====================================================================
    private JPanel criarPainelTalhaoAgrupado(TalhaoVO talhao, java.util.List<CanteiroVO> canteiros) {
        // Painel principal do talhão (contém header + conteúdo)
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // --- 1. CABEÇALHO (sempre visível) ---
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        cabecalho.setBackground(new Color(230, 230, 230));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String nomeTalhao = talhao != null ? talhao.getNome() : "Talhão " + (talhao != null ? talhao.getId() : "?");
        String statusTalhao = talhao != null ? talhao.getStatus() : "";
        double areaTalhao = talhao != null && talhao.getAreaTalhao() != null ? talhao.getAreaTalhao().doubleValue() : 0.0;

        // Calcula área restante (área total do talhão - soma das áreas dos canteiros)
        double areaUtilizadaPorCanteiros = 0.0;
        if (canteiros != null) {
            for (CanteiroVO c : canteiros) {
                if (c.getAreaCanteiroM2() != null) {
                    areaUtilizadaPorCanteiros += c.getAreaCanteiroM2().doubleValue();
                }
            }
        }
        double areaRestante = areaTalhao - areaUtilizadaPorCanteiros;

        JLabel lbl = new JLabel(nomeTalhao + " | Status: " + statusTalhao + " | Área: " + String.format("%.2f", areaTalhao) + " m² | Área Restante: " + String.format("%.2f", areaRestante) + " m²");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));

        // Seta do drop-down
        JButton arrow = new JButton("\u25BC"); 
        arrow.setFocusPainted(false);
        arrow.setBorderPainted(false);
        arrow.setContentAreaFilled(false);
        arrow.setOpaque(false);
        arrow.setBorder(null);

        // Painel lateral com seta alinhada à direita
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(arrow);

        cabecalho.add(lbl, BorderLayout.WEST);
        cabecalho.add(rightPanel, BorderLayout.EAST);
        bloco.add(cabecalho);

        // --- 2. CONTEÚDO EXPANDIDO (Canteiros de todas as ordens deste talhão) ---
        JPanel conteudoExpandido = new JPanel();
        conteudoExpandido.setLayout(new BoxLayout(conteudoExpandido, BoxLayout.Y_AXIS));
        conteudoExpandido.setBackground(Color.WHITE);
        conteudoExpandido.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelTitulo.setBackground(Color.WHITE);

        JLabel lblCanteiros = new JLabel("Canteiros em " + nomeTalhao + ":");
        lblCanteiros.setFont(new Font("Arial", Font.BOLD, 16));
        painelTitulo.add(lblCanteiros);
        conteudoExpandido.add(painelTitulo);

        conteudoExpandido.add(Box.createVerticalStrut(10));

        // Painel de cards
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(Color.WHITE);

        if (canteiros != null && !canteiros.isEmpty()) {
            for (CanteiroVO canteiro : canteiros) {
                String nomeCanteiro = canteiro.getNome();
                String infoCanteiro = String.format("%.2f", canteiro.getKgGerados()) + " kg";
                JPanel card = criarCard(nomeCanteiro, infoCanteiro, canteiro.getStatus());
                for (Component comp : card.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton btn = (JButton) comp;
                        if ("Ver Detalhes".equals(btn.getText()) || "Ver".equals(btn.getText())) {
                            btn.addActionListener(ev -> {
                                // Buscar ordem por ID para obter dados complementares
                                OrdemProducaoVO ordem = controller.buscarOrdemPorId(canteiro.getOrdemProducaoId());
                                String cultura = ordem != null && ordem.getNomeEspecie() != null ? ordem.getNomeEspecie() : (ordem != null && ordem.getNomePlano() != null ? ordem.getNomePlano() : "");
                                String titulo = nomeCanteiro;
                                double areaM2 = canteiro.getAreaCanteiroM2() != null ? canteiro.getAreaCanteiroM2().doubleValue() : 0.0;
                                double qtdKg = canteiro.getKgGerados() != null ? canteiro.getKgGerados().doubleValue() : 0.0;
                                java.util.Date inicio = ordem != null ? ordem.getDataExecucao() : null;
                                Long canteiroId = canteiro.getId();
                                Long areaId = area != null ? area.getId() : null;

                                TelaCanteiro tela = new TelaCanteiro(cultura, titulo, inicio, areaM2, qtdKg, canteiroId, areaId);
                                tela.setVisible(true);
                                // Fecha a tela atual (Talhão) para não ficar duas abertas
                                TelaTalhao.this.dispose();
                            });
                        }
                    }
                }
                cards.add(card);
            }
        } else {
            JLabel lblVazio = new JLabel("Nenhum canteiro cadastrado neste talhão.");
            cards.add(lblVazio);
        }

        conteudoExpandido.add(cards);
        bloco.add(conteudoExpandido);

        // Começa fechado
        conteudoExpandido.setVisible(false);
        arrow.setText("\u25B6"); // ▶

        arrow.addActionListener(e -> {
            boolean visivel = conteudoExpandido.isVisible();
            conteudoExpandido.setVisible(!visivel);
            arrow.setText(visivel ? "\u25B6" : "\u25BC");
            bloco.revalidate();
            bloco.repaint();
        });

        return bloco;
    }

    private JPanel criarPainelTalhao(TalhaoVO talhao) {
        
        // Painel principal do talhão (contém header + conteúdo)
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // --- 1. CABEÇALHO (sempre visível) ---
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        cabecalho.setBackground(new Color(230, 230, 230));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nome e Status do Talhão vindo do VO
        JLabel lbl = new JLabel(talhao.getNome() + " | Status: " + talhao.getStatus() + " | Área: " + String.format("%.2f", talhao.getAreaTalhao()) + " m²");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));

        // Botão Novo Canteiro
        JButton novaAtv = new JButton("Novo Canteiro");
        novaAtv.setPreferredSize(new Dimension(130, 24));
        // 🔑 Ação: Implementar abertura de TelaNovoCanteiro(talhao.getId())

        // Seta do drop-down
        JButton arrow = new JButton("\u25BC"); 
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

        // --- 2. CONTEÚDO EXPANDIDO (Canteiros) ---
        JPanel conteudoExpandido = new JPanel();
        conteudoExpandido.setLayout(new BoxLayout(conteudoExpandido, BoxLayout.Y_AXIS));
        conteudoExpandido.setBackground(Color.WHITE);
        conteudoExpandido.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelTitulo.setBackground(Color.WHITE);

        JLabel lblCanteiros = new JLabel("Canteiros em " + talhao.getNome() + ":");
        lblCanteiros.setFont(new Font("Arial", Font.BOLD, 16));
        painelTitulo.add(lblCanteiros);
        conteudoExpandido.add(painelTitulo);

        conteudoExpandido.add(Box.createVerticalStrut(10));

        // Painel de cards
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(Color.WHITE);

        // 🔑 ITERAÇÃO DINÂMICA SOBRE CANTEIROS
        if (talhao.getCanteiros() != null && !talhao.getCanteiros().isEmpty()) {
            for (CanteiroVO canteiro : talhao.getCanteiros()) {
                // Monta a string de conteúdo (Exemplo: Nome da cultura + Kg gerados)
                String infoCanteiro = String.format("%.2f", canteiro.getKgGerados()) + " kg"; 
                cards.add(criarCard(canteiro.getNome(), infoCanteiro, canteiro.getStatus())); 
            }
        } else {
            JLabel lblVazio = new JLabel("Nenhum canteiro cadastrado neste talhão.");
            cards.add(lblVazio);
        }

        conteudoExpandido.add(cards);
        bloco.add(conteudoExpandido);
        
        // Garante que o painel começa FECHADO (opcional, mas bom padrão)
        conteudoExpandido.setVisible(false);
        arrow.setText("\u25B6"); // ▶ (Seta para a direita, indicando que está fechado)

        // --- 3. TOGGLE ---
        arrow.addActionListener(e -> {
            boolean visivel = conteudoExpandido.isVisible();
            conteudoExpandido.setVisible(!visivel);
            arrow.setText(visivel ? "\u25B6" : "\u25BC"); // ▶ fechado → ▼ aberto
            bloco.revalidate();
            bloco.repaint();
        });

        return bloco;
    }

    // ====================================================================
    // COMPONENTES AUXILIARES (Pequenas modificações no criarCard)
    // ====================================================================

    private JPanel criarCard(String titulo, String info, String status) {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200, 130)); // Aumenta um pouco para caber o status
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(240, 240, 230));

        JLabel l1 = new JLabel(titulo, SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l2 = new JLabel(info, SwingConstants.CENTER);
        l2.setFont(new Font("Arial", Font.PLAIN, 14)); // Muda para PLAIN ou ITALIC
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lStatus = new JLabel("Status: " + status, SwingConstants.CENTER);
        lStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lStatus.setForeground(Color.GRAY);
        lStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton ver = new JButton("Ver Detalhes");
        ver.setAlignmentX(Component.CENTER_ALIGNMENT);
        ver.setBackground(new Color(150, 160, 80));
        ver.setForeground(Color.WHITE);
        ver.setFont(new Font("Arial", Font.BOLD, 14));
        ver.setFocusPainted(false);
        ver.setPreferredSize(new Dimension(150, 25));
        ver.setMaximumSize(new Dimension(150, 25));

        p.add(Box.createVerticalStrut(5));
        p.add(l1);
        p.add(Box.createVerticalStrut(5));
        p.add(l2);
        p.add(lStatus); // Adicionando o status
        p.add(Box.createVerticalStrut(10));
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
    
    // ... (main method for testing, if applicable)
}