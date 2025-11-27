package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.producao.modelo.Area;

// import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaTalhao extends JFrame {

    private Area area;

    public TelaTalhao(Area area) {
        this.area = area;

        setTitle("Área - " + area.getNome());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
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
        String[] botoes = {"Área de plantio", "Registrar problemas", "Relatório de produção"};
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
        JButton btnEditar = criarBotaoPadrao("Editar Talhão", verdeClaro);
        JButton btnAdicionar = criarBotaoPadrao("Adicionar Talhão", verdeClaro);

        // Define tamanho máximo (para garantir que não cresçam além disso)
        Dimension tam = new Dimension(200, 45);
        btnVoltar.setPreferredSize(tam);
        btnVoltar.setMaximumSize(tam);
        btnEditar.setPreferredSize(tam);
        btnEditar.setMaximumSize(tam);
        btnAdicionar.setPreferredSize(tam);
        btnAdicionar.setMaximumSize(tam);

        leftButtons.add(btnVoltar);
        leftButtons.add(btnEditar);
        leftButtons.add(btnAdicionar);

        // Adiciona o painel de botões na coluna 0, mesma linha 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        // Não deixamos weightx aqui para não "esticar" o painel de botões
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        conteudo.add(leftButtons, gbc);

        // --- Título na mesma linha, mas ocupando espaço restante ---
        JLabel lblTitulo = new JLabel(area.getNome(), SwingConstants.CENTER);
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

        String[] textosResumo = {
            "Nome: " + area.getNome(),
            // Área Total
            "Área Total: " + String.format("%.2f", area.getAreaTotal()) + " ha", 
            // Área Restante (Cálculo dinâmico)
            "Área Restante: " + String.format("%.2f", area.getAreaTotal() - area.getAreaUtilizada()) + " ha",
            // Área Utilizada (Aparentemente você queria mostrar o pH aqui)
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

        // GridBag constraints corretos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weighty = 0;           // ← IMPORTANTE! NÃO DEIXAR EXPANDIR
        gbc.fill = GridBagConstraints.HORIZONTAL;
        conteudo.add(containerResumo, gbc);


        // --- Resto: "A fazer" e tabela (igual ao exemplo original) ---
        JLabel lblAFazer = new JLabel("Talhões Ativos:");
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
        //  TALHÃO 1 (EXPANDIDO)
        // =============================
        GridBagConstraints gbcTalhao = new GridBagConstraints();
        gbcTalhao.gridx = 0;
        gbcTalhao.gridy = 3;         // PRIMEIRO TALHÃO NA LINHA 3
        gbcTalhao.gridwidth = 4;
        gbcTalhao.weightx = 1;
        gbcTalhao.weighty = 0;
        gbcTalhao.insets = new Insets(10, 20, 10, 20);
        gbcTalhao.fill = GridBagConstraints.HORIZONTAL;
        gbcTalhao.anchor = GridBagConstraints.NORTHWEST;

        conteudo.add(criarPainelTalhaoExpandido(), gbcTalhao);

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

    private JPanel criarPainelTalhaoExpandido() {

        // Painel principal do talhão (contém header + conteúdo)
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

        JLabel lbl = new JLabel("Talhão 1");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));

        // Botão Nova Atividade
        JButton novaAtv = new JButton("Novo Canteiro");
        novaAtv.setPreferredSize(new Dimension(130, 24));

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

        // Painel para alinhar o título à ESQUERDA
        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelTitulo.setBackground(Color.WHITE);

        // Texto "Canteiros"
        JLabel lblCanteiros = new JLabel("Canteiros");
        lblCanteiros.setFont(new Font("Arial", Font.BOLD, 16));

        painelTitulo.add(lblCanteiros);
        conteudoExpandido.add(painelTitulo);

        conteudoExpandido.add(Box.createVerticalStrut(10));

        // Cards dentro do fundo branco
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(Color.WHITE);

        cards.add(criarCard("Canteiro 1", "Milho"));
        cards.add(criarCard("Canteiro 2", "Soja"));
        cards.add(criarCard("Canteiro 3", "Alface"));

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

    private JPanel criarCard(String titulo, String cultura) {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200, 120));
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(240, 240, 230));

        JLabel l1 = new JLabel(titulo, SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l2 = new JLabel(cultura, SwingConstants.CENTER);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton ver = new JButton("Ver");
        ver.setAlignmentX(Component.CENTER_ALIGNMENT);
        ver.setBackground(new Color(150, 160, 80));
        ver.setForeground(Color.WHITE);
        ver.setFont(new Font("Arial", Font.BOLD, 14));
        ver.setFocusPainted(false);
        ver.setPreferredSize(new Dimension(150, 35));
        ver.setMaximumSize(new Dimension(150, 35));  // impede esticar

        p.add(Box.createVerticalStrut(10));
        p.add(l1);
        p.add(Box.createVerticalStrut(10));
        p.add(l2);
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

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         TelaTalhao t = new TelaTalhao("Área de Teste");
    //         t.setVisible(true);
    //     });
    // }
}
