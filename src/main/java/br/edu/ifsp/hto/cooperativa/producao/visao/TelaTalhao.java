package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO; // 🔑 NOVO IMPORT
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO; // 🔑 NOVO IMPORT

import java.awt.*;
import java.util.List; // Import necessário para lidar com List<TalhaoVO>
import java.awt.event.ActionListener; // Import necessário para o listener
import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController; // Controller

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
        this.area = area;
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
                    new br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial().setVisible(true);
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

        JButton btnRemover = criarBotaoPadrao("Remover Talhão", verdeClaro);
        JButton btnAdicionar = criarBotaoPadrao("Novo Talhão", verdeClaro);
        JButton btnEditar = criarBotaoPadrao("Editar Talhão", verdeClaro);
        JButton btnPlano = criarBotaoPadrao("Usar Plano", verdeClaro);

        btnRemover.setPreferredSize(tam); btnRemover.setMaximumSize(tam);
        btnEditar.setPreferredSize(tam); btnEditar.setMaximumSize(tam);
        btnAdicionar.setPreferredSize(tam); btnAdicionar.setMaximumSize(tam);
        btnPlano.setPreferredSize(tam); btnPlano.setMaximumSize(tam);

        leftButtonsBelow.add(btnRemover);
        leftButtonsBelow.add(btnAdicionar);
        leftButtonsBelow.add(btnEditar);
        leftButtonsBelow.add(btnPlano);

        // Ação do botão Remover: chama o controller para inativar e atualiza a view
        btnRemover.addActionListener(e -> {
            if (area.getTalhoes() == null || area.getTalhoes().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há talhões para remover.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Mostra apenas talhões ativos (por segurança)
            java.util.List<TalhaoVO> ativos = new java.util.ArrayList<>();
            for (TalhaoVO t : area.getTalhoes()) {
                if (t != null && (t.getStatus() == null || !t.getStatus().equalsIgnoreCase("Inativo"))) {
                    ativos.add(t);
                }
            }

            if (ativos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há talhões ativos para remover.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] nomes = new String[ativos.size()];
            for (int i = 0; i < ativos.size(); i++) nomes[i] = ativos.get(i).getNome();

            String escolhido = (String) JOptionPane.showInputDialog(this, "Selecione o talhão a remover:", "Remover Talhão",
                    JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);

            if (escolhido == null) return; // cancelou

            TalhaoVO talhaoEscolhido = null;
            for (TalhaoVO t : ativos) if (escolhido.equals(t.getNome())) { talhaoEscolhido = t; break; }

            if (talhaoEscolhido == null) return;

            int conf = JOptionPane.showConfirmDialog(this, "Confirma marcar o talhão '" + talhaoEscolhido.getNome() + "' como Inativo?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf != JOptionPane.YES_OPTION) return;

            boolean ok = controller.removerTalhao(talhaoEscolhido.getId());
            if (ok) {
                // Recarrega a área completa (o DAO agora filtra por status='Ativo')
                AreaVO nova = controller.carregarAreaCompletaPorId(area.getId());
                if (nova != null) {
                    this.area = nova;
                    getContentPane().removeAll();
                    initComponents();
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Talhão inativado, porém falha ao recarregar área.", "Aviso", JOptionPane.WARNING_MESSAGE);
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


        // --- Resto: "Talhões Ativos" ---
        JLabel lblAFazer = new JLabel("Talhões Ativos:");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 22));
        lblAFazer.setForeground(verdeEscuro);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.weighty = 0; gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblAFazer, gbc);

        // ===============================================
        // 🔑 ITERAÇÃO DINÂMICA SOBRE TALHÕES E CANTEIROS
        // ===============================================
        int linhaAtual = 4; 

        if (area.getTalhoes() != null && !area.getTalhoes().isEmpty()) {
            for (TalhaoVO talhao : area.getTalhoes()) {
                
                // Cria o painel do Talhão (que contém o cabeçalho e os canteiros)
                JPanel painelTalhao = criarPainelTalhao(talhao); 
                
                GridBagConstraints gbcTalhao = new GridBagConstraints();
                gbcTalhao.gridx = 0;
                gbcTalhao.gridy = linhaAtual++; // Incrementa a linha
                gbcTalhao.gridwidth = 4;
                gbcTalhao.weightx = 1;
                gbcTalhao.weighty = 0;
                gbcTalhao.insets = new Insets(10, 20, 10, 20);
                gbcTalhao.fill = GridBagConstraints.HORIZONTAL;
                gbcTalhao.anchor = GridBagConstraints.NORTHWEST;

                conteudo.add(painelTalhao, gbcTalhao);
            }
        } else {
            // Exibir mensagem se não houver talhões
            JLabel lblSemTalhoes = new JLabel("Não há talhões cadastrados para esta área.", SwingConstants.CENTER);
            lblSemTalhoes.setFont(new Font("Arial", Font.ITALIC, 18));
            lblSemTalhoes.setForeground(new Color(100, 100, 100));
            
            GridBagConstraints gbcVazio = new GridBagConstraints();
            gbcVazio.gridx = 0;
            gbcVazio.gridy = linhaAtual++;
            gbcVazio.gridwidth = 4;
            gbcVazio.insets = new Insets(40, 20, 40, 20);
            gbcVazio.anchor = GridBagConstraints.CENTER;
            
            conteudo.add(lblSemTalhoes, gbcVazio);
        }
        
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