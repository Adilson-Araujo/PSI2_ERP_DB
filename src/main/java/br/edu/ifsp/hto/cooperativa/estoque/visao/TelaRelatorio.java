package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaRelatorio {

    public static JInternalFrame gerarFrameInterno(){

        // Criação da janela principal
        final JInternalFrame janela = new JInternalFrame("Relatório");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        /* MENU LATERAL*/

        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelMenu.setBackground(new Color(55, 61, 13)); // Verde escuro do Figma

        // Lista de botões do menu lateral
        String[] opcoes = {
                "Registro de Saída", "Registro de Produção", "Visão de Estoque",
                "Inventário", "Cadastro de Produto", "Histórico", "Relatório"
        };

        // Cria cada botão usando a função "criarBotao"
        for (String opcao : opcoes) {
            painelMenu.add(criarBotao(opcao));
            painelMenu.add(Box.createVerticalStrut(10)); // Espaçamento
        }

        /* pAINEL CENTRAL - CONTEÚDO DA TELA DE RELATÓRIO */
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        /* PAINEL DE FILTROS - MÊS/ANO, FORMATO, GERAR  */

        JPanel painelFiltros = new JPanel(new GridBagLayout());
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Gerar Relatório"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margens entre os componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Label + Campo de texto para Mês/Ano
        JLabel lblMesAno = new JLabel("Mês/Ano:");
        JTextField txtMesAno = new JTextField(10);

        // Label + ComboBox para tipo de arquivo
        JLabel lblTipo = new JLabel("Formato:");
        JComboBox<String> comboFormato = new JComboBox<>(new String[]{"PDF", "Excel", "CSV"});

        // Botão Gerar
        JButton btnGerar = new JButton("Gerar");
        btnGerar.setBackground(new Color(108, 122, 14)); // Verde do Figma
        btnGerar.setForeground(Color.WHITE);

        // Adicionando componentes ao painel de filtros
        gbc.gridx = 0; gbc.gridy = 0;
        painelFiltros.add(lblMesAno, gbc);

        gbc.gridx = 1;
        painelFiltros.add(txtMesAno, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelFiltros.add(lblTipo, gbc);

        gbc.gridx = 1;
        painelFiltros.add(comboFormato, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelFiltros.add(btnGerar, gbc);

        // Adiciona o painel de filtros na esquerda
        painelCentral.add(painelFiltros, BorderLayout.WEST);

        /* TABELA DE PRODUTOS  */

        // Colunas da tabela
        String[] colunas = {"Nome", "Produzido", "Perdido", "Consumido", "Vendido"};

        // Dados modelo iguais ao Figma
        Object[][] dados = {
                {"Alface", "120", "15", "50", "55"},
                {"Cenoura", "90", "8", "40", "30"},
                {"Morango", "200", "20", "70", "60"}
        };

        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int r, int c) { return false; } // Não editável
        };

        JTable tabela = new JTable(model);
        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Produtos"));

        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        /*  PAINEL DE RESUMO (TOTALIZADORES) */

        JPanel painelResumo = new JPanel(new GridLayout(1, 4, 10, 0)); // 4 colunas
        painelResumo.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Cada quadro do resumo é criado com a função criarCaixaResumo()
        JLabel lblProd = criarCaixaResumo("Produzido: 410kg");
        JLabel lblPerd = criarCaixaResumo("Perdido: 43kg");
        JLabel lblCons = criarCaixaResumo("Consumido: 160kg");
        JLabel lblVend = criarCaixaResumo("Vendido: 145kg");

        painelResumo.add(lblProd);
        painelResumo.add(lblPerd);
        painelResumo.add(lblCons);
        painelResumo.add(lblVend);

        painelCentral.add(painelResumo, BorderLayout.SOUTH);

        /* ADICIONA TUDO NA JANELA  */

        janela.add(painelMenu, BorderLayout.WEST);     // Menu lateral
        janela.add(painelCentral, BorderLayout.CENTER); // Conteúdo principal

        janela.setVisible(true); // Exibe a tela
        return janela;
    }

    /*  FUNÇÃO PARA CRIAR OS BOTÕES DO MENU LATERAL */

    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        Dimension tamanho = new Dimension(130, 30);
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botao;
    }

    /* FUNÇÃO PARA CRIAR AS CAIXAS DE RESUMO DO RODAPÉ (PRODUZIDO, PERDIDO...) */
    private static JLabel criarCaixaResumo(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(220, 230, 200)); // Verde claro
        label.setBorder(BorderFactory.createLineBorder(new Color(150, 170, 100)));
        return label;
    }
}
