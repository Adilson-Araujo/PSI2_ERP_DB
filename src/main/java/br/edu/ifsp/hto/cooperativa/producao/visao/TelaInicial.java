package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// import br.edu.ifsp.hto.cooperativa.sessao.modelo.vo.UsuarioVO;

import java.awt.*;

public class TelaInicial extends JFrame {

    private long associadoId;

    public TelaInicial(Long associadoId) {
        this.associadoId = associadoId;
        initializeComponents();
    }

    public void initializeComponents() {
        setTitle("Tela Inicial - Produção");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= CORES =======
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // ======= NAVBAR SUPERIOR =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL =======
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

        // ======= AÇÃO DOS BOTÕES =======
        botao.addActionListener(e -> {

            if (texto.equals("Área de plantio")) {
            // passa o associadoId que recebemos no construtor da TelaInicial
            new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea(this.associadoId).setVisible(true);
            dispose(); // opcional: fecha a TelaInicial
            }

            if (texto.equals("Registrar problemas")) {
                // depois criamos isso
            }

            if (texto.equals("Relatório de produção")) {
                // depois criamos isso
            }
        });

        menuLateral.add(botao);
        menuLateral.add(Box.createVerticalStrut(20));
        
    }


        add(menuLateral, BorderLayout.WEST);

        // ======= CONTEÚDO PRINCIPAL =======
        JPanel conteudo = new JPanel();
        conteudo.setBackground(cinzaFundo);
        conteudo.setLayout(new GridBagLayout());
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(120, 45));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(btnVoltar, gbc);

        JLabel lblTitulo = new JLabel("Tela inicial", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        JPanel painelResumo = new JPanel(new GridLayout(2, 2, 40, 20));
        painelResumo.setOpaque(false);
        String[] textos = {
                "Talhões Criados: 5",
                "Atividades Ativas: 5",
                "Canteiros Ativos: 5",
                "Canteiros Ativos: 5"
        };

        for (String texto : textos) {
            JPanel box = new JPanel(new BorderLayout());
            box.setBackground(Color.WHITE);
            box.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setForeground(verdeEscuro);
            box.add(lbl, BorderLayout.CENTER);
            painelResumo.add(box);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weighty = 0.3;
        conteudo.add(painelResumo, gbc);

        JLabel lblAFazer = new JLabel("A fazer:");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 22));
        lblAFazer.setForeground(verdeEscuro);
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblAFazer, gbc);

        String[] colunas = {"Atividade", "Custo Total", "Data Final", "Prioridade"};
        Object[][] dados = {
                {"Plantar milho", "600,00", "18/02/26", "ALTA"},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""}
        };

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setBackground(verdeClaro);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        gbc.gridy = 3;
        gbc.weighty = 1;
        conteudo.add(scrollTabela, gbc);
    }
    //ABRIR SOMENTE APÓS LOGIN
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         TelaInicial tela = new TelaInicial(0);
    //         tela.setVisible(true);
    //     });
    // }
}
