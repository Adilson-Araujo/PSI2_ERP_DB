package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// import br.edu.ifsp.hto.cooperativa.sessao.modelo.vo.UsuarioVO;

import java.awt.*;
import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

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
            new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea().setVisible(true);
                    dispose(); 
            }
            if (texto.equals("Registrar problemas")) {
                // Criar e abrir tela de registrar problemas
                br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel model = 
                    new br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel();
                br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController controller = 
                    new br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController(model);
                new br.edu.ifsp.hto.cooperativa.producao.visao.TelaRegistrarProblemas(controller).setVisible(true);
                dispose();
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

        String nomeUsuario = "Usuário"; // Valor padrão caso dê erro
        try {
            SessaoControlador sessaoControlador = new SessaoControlador();
            UsuarioTO usuarioLogado = sessaoControlador.obterUsuarioLogado();
            if (usuarioLogado != null && usuarioLogado.usuarioVO != null) {
                nomeUsuario = usuarioLogado.usuarioVO.getNomeUsuario();
            }
        } catch (Exception e) {
            System.err.println("Erro ao recuperar usuário da sessão: " + e.getMessage());
        }

        JLabel lblSaudacao = new JLabel("Olá, " + nomeUsuario + "!");
        lblSaudacao.setFont(new Font("Arial", Font.BOLD, 28));
        lblSaudacao.setForeground(verdeClaro); 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblSaudacao, gbc);
        // -----------------------------------------------------------

        JLabel lblTitulo = new JLabel("Tela inicial", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        br.edu.ifsp.hto.cooperativa.producao.controle.DashboardController dashController = 
                new br.edu.ifsp.hto.cooperativa.producao.controle.DashboardController();
        
        br.edu.ifsp.hto.cooperativa.producao.modelo.vo.DashboardVO dadosDash = 
                dashController.obterDadosIniciais((int) this.associadoId);

        JPanel painelResumo = new JPanel(new GridLayout(2, 2, 40, 20));
        painelResumo.setOpaque(false);

        String[] textos = {
                "Ordens em Execução: " + dadosDash.getQtdTalhoes(),
                "Ordens Concluídas: " + dadosDash.getQtdAtividades(),
                "Canteiros Ativos: " + dadosDash.getQtdCanteiros(),
                "Problemas Ativos: " + dadosDash.getQtdProblemas()
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

        JLabel lblAFazer = new JLabel("Ordens Concluídas (Enviadas ao Estoque):");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 22));
        lblAFazer.setForeground(verdeEscuro);
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(lblAFazer, gbc);

        String[] colunas = {"Espécie", "Descrição", "Data Execução", "Quantidade (kg)"};
        
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (dadosDash.getAtividadesPendentes().isEmpty()) {
            modelo.addRow(new Object[]{"Nenhuma ordem concluída", "-", "-", "-"});
        } else {
            for (br.edu.ifsp.hto.cooperativa.producao.modelo.vo.DashboardVO.AtividadeResumoVO atv : dadosDash.getAtividadesPendentes()) {
                modelo.addRow(new Object[]{
                    atv.getNome(),
                    atv.getPrioridade(), // Descrição
                    atv.getDataFinal(),
                    String.format("%.2f", atv.getCustoTotal()) // Quantidade em kg
                });
            }
        }

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
}