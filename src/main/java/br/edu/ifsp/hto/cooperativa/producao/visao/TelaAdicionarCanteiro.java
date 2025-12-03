package br.edu.ifsp.hto.cooperativa.producao.visao;

import java.awt.*;
import javax.swing.*;

public class TelaAdicionarCanteiro extends JFrame {

    public TelaAdicionarCanteiro() {
        setTitle("Adicionar Canteiro");
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

        String[] botoes = {"Tela inicial", "Área de plantio", "Registrar problemas", "Relatório de produção"};
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 15));
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 45));
            botao.setPreferredSize(new Dimension(180, 45));
            botao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 2));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            botao.addActionListener(e -> {
                try {
                    long associadoId = br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao.getAssociadoIdLogado();
                    if (texto.equals("Tela inicial")) {
                        new br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial(associadoId).setVisible(true);
                        dispose();
                    } else if (texto.equals("Área de plantio")) {
                        new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea().setVisible(true);
                        dispose();
                    } else if (texto.equals("Registrar problemas")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController(model);
                        new br.edu.ifsp.hto.cooperativa.producao.visao.TelaRegistrarProblemas(controller).setVisible(true);
                        dispose();
                    } else if (texto.equals("Relatório de produção")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController(model);
                        new br.edu.ifsp.hto.cooperativa.producao.visao.TelaRelatorioProducao(controller).setVisible(true);
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
        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        // ======= TOPO =======
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 50));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(120, 45));
        painelTopo.add(btnVoltar, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Adicionar Canteiro");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(verdeEscuro);
        painelTopo.add(lblTitulo, BorderLayout.EAST);

        conteudo.add(painelTopo, BorderLayout.NORTH);

        // ======= FORMULÁRIO =======
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        Font fonteCampo = new Font("Arial", Font.PLAIN, 16);

        // --- Data de início ---
        gbc.gridy = 0;
        painelForm.add(criarLabel("Data de início:", verdeEscuro), gbc);

        gbc.gridy = 1;
        painelForm.add(criarCampo(fonteCampo, verdeEscuro), gbc);

        // --- Data de fim ---
        gbc.gridy = 2;
        painelForm.add(criarLabel("Data de fim:", verdeEscuro), gbc);

        gbc.gridy = 3;
        painelForm.add(criarCampo(fonteCampo, verdeEscuro), gbc);

        // --- Espécie ---
        gbc.gridy = 4;
        painelForm.add(criarLabel("Espécie:", verdeEscuro), gbc);

        gbc.gridy = 5;
        JComboBox<String> comboEspecie = new JComboBox<>(new String[]{
                "Selecione...", "Alface", "Tomate", "Couve", "Cenoura", "Milho"
        });
        comboEspecie.setFont(fonteCampo);
        comboEspecie.setPreferredSize(new Dimension(350, 45));
        comboEspecie.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        painelForm.add(comboEspecie, gbc);

        // --- Área total ---
        gbc.gridy = 6;
        painelForm.add(criarLabel("Área total:", verdeEscuro), gbc);

        gbc.gridy = 7;
        painelForm.add(criarCampo(fonteCampo, verdeEscuro), gbc);

        // --- Botão Salvar ---
        gbc.gridy = 8;
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setPreferredSize(new Dimension(200, 50));
        painelForm.add(btnSalvar, gbc);

        // ======= SCROLL =======
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setOpaque(false);
        GridBagConstraints gbcCenter = new GridBagConstraints();
        gbcCenter.gridy = 0;
        gbcCenter.insets = new Insets(40, 0, 40, 0);
        painelCentral.add(painelForm, gbcCenter);

        JScrollPane scrollPane = new JScrollPane(painelCentral);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        conteudo.add(scrollPane, BorderLayout.CENTER);
    }

    private JLabel criarLabel(String texto, Color cor) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(cor);
        return lbl;
    }

    private JTextField criarCampo(Font fonte, Color borda) {
        JTextField txt = new JTextField();
        txt.setFont(fonte);
        txt.setPreferredSize(new Dimension(350, 45));
        txt.setBorder(BorderFactory.createLineBorder(borda, 1));
        return txt;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarCanteiro().setVisible(true));
    }
}
