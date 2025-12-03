package br.edu.ifsp.hto.cooperativa.producao.visao;

import br.edu.ifsp.hto.cooperativa.producao.controle.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TelaRelatorioProducao extends JFrame {

    private RelatorioProducaoController controller;
    
    // Componentes dinâmicos
    private JComboBox<String> comboArea;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JLabel lblProblemas;
    private JLabel lblPlantacoes;
    private JLabel lblTotalKg;

    public TelaRelatorioProducao(RelatorioProducaoController controller) {
        this.controller = controller;

        configurarTela();
        montarLayout();
    }

    private void configurarTela() {
        setTitle("Tela Inicial - Produção");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void montarLayout() {
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(235, 235, 235);

        // NAVBAR
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // MENU LATERAL
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(verdeEscuro);
        menuLateral.setPreferredSize(new Dimension(220, getHeight()));
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
            menuLateral.add(Box.createVerticalStrut(15));
        }
        
        add(menuLateral, BorderLayout.WEST);

        // CONTEÚDO CENTRAL
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); 
        gbc.fill = GridBagConstraints.NONE;
        
        // --- LINHA 0: TÍTULO ---
        JLabel lblTitulo = new JLabel("Produção", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        // --- LINHA 2: COMBO ---
        comboArea = new JComboBox<>(controller.getAreasPlantio());
        comboArea.setFont(new Font("Arial", Font.PLAIN, 18));
        comboArea.setPreferredSize(new Dimension(350, 45));
        comboArea.setBorder(new RoundedBorder(15));
        
        // Ação ao selecionar uma nova área
        comboArea.addActionListener(e -> {
            String areaSelecionada = (String) comboArea.getSelectedItem();
            atualizarTabela(areaSelecionada);
        });

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 4;
        gbc.weightx = 0;   
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(comboArea, gbc);

        // --- LINHA 3: PAINEL RESUMO (cards) ---
        JPanel painelResumo = new JPanel(new GridLayout(1, 3, 40, 10));
        painelResumo.setOpaque(false);

        lblProblemas = new JLabel("--", SwingConstants.CENTER);
        lblProblemas.setFont(new Font("Arial", Font.BOLD, 24));
        lblProblemas.setForeground(verdeEscuro);
        
        lblPlantacoes = new JLabel("--", SwingConstants.CENTER);
        lblPlantacoes.setFont(new Font("Arial", Font.BOLD, 24));
        lblPlantacoes.setForeground(verdeEscuro);
        
        lblTotalKg = new JLabel("--", SwingConstants.CENTER);
        lblTotalKg.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotalKg.setForeground(verdeEscuro);

        painelResumo.add(criarCard("Problemas Registrados", lblProblemas, verdeEscuro));
        painelResumo.add(criarCard("Plantações Colhidas", lblPlantacoes, verdeEscuro));
        painelResumo.add(criarCard("Total Kg Produzidos", lblTotalKg, verdeEscuro));

        gbc.gridy = 3; 
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 40, 20, 40); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        conteudo.add(painelResumo, gbc);

        // --- LINHA 4: TABELA ---
        String[] colunas = {"Cultura", "Produção em Kg", "% Concluídas"};
        modeloTabela = new DefaultTableModel(colunas, 0);

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(32);
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tabela.getTableHeader().setBackground(verdeClaro);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        gbc.gridy = 4; 
        gbc.gridwidth = 4;
        gbc.weighty = 1; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(10, 20, 20, 20); 
        conteudo.add(new JScrollPane(tabela), gbc);
        
        // Carrega os dados iniciais se houver itens no combo
        if (comboArea.getItemCount() > 0) {
            comboArea.setSelectedIndex(0); // Isso dispara o listener e carrega a tabela
        }
    }
    
    // Método auxiliar para buscar dados novos no banco
    private void atualizarTabela(String area) {
        // Limpa linhas atuais
        modeloTabela.setRowCount(0);
        
        if (area == null) return;

        // Busca novos dados do controller
        Object[][] dados = controller.getDadosRelatorio(area);
        
        // Adiciona na tabela
        for (Object[] linha : dados) {
            modeloTabela.addRow(linha);
        }
        
        // Atualiza os cards
        atualizarCards(area);
    }
    
    // Método para atualizar os cards com estatísticas
    private void atualizarCards(String area) {
        if (area == null) return;
        
        java.util.Map<String, Object> stats = controller.getEstatisticas(area);
        
        lblProblemas.setText(String.valueOf(stats.get("problemas")));
        lblPlantacoes.setText(String.valueOf(stats.get("ordens_concluidas")));
        lblTotalKg.setText(String.format("%.2f kg", stats.get("total_kg")));
    }
    
    // Método auxiliar para criar cards
    private JPanel criarCard(String titulo, JLabel lblValor, Color cor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(cor, 2));
        panel.setPreferredSize(new Dimension(200, 100));
        
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(cor);
        
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Classe interna para cards de resumo
    private class CardResumo extends JPanel {
        public CardResumo(String titulo, String valor, Color cor) {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(cor, 2));
            setPreferredSize(new Dimension(200, 100));
            
            JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
            lblTitulo.setForeground(cor);
            
            JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
            lblValor.setFont(new Font("Arial", Font.BOLD, 24));
            lblValor.setForeground(cor);
            
            add(lblTitulo, BorderLayout.NORTH);
            add(lblValor, BorderLayout.CENTER);
        }
    }
    
    // Classe interna para bordas arredondadas
    private class RoundedBorder extends javax.swing.border.AbstractBorder {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.draw(new java.awt.geom.RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            g2.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
        
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = this.radius + 1;
            insets.top = insets.bottom = this.radius + 2;
            return insets;
        }
    }
}