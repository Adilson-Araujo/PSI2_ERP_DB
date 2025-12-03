package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import java.awt.*;

public class TelaAtrelarMaterial extends JFrame {

    public TelaAtrelarMaterial() {

        setTitle("Atrelar Material");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= CORES (MESMAS DA OUTRA TELA) =======
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // ======= NAVBAR SUPERIOR (A MESMA) =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL (O MESMO) =======
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
            botao.setMaximumSize(new Dimension(180, 50));
            botao.setPreferredSize(new Dimension(180, 50));
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

        // ======= CONTEÚDO CENTRAL =======
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ======= TÍTULO =======
        JLabel titulo = new JLabel("Atrelar Material", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 42));
        titulo.setForeground(verdeEscuro);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        conteudo.add(titulo, gbc);

        // ======= PAINEL DO FORM =======
        JPanel painelForm = new JPanel();
        painelForm.setBackground(Color.WHITE);
        painelForm.setPreferredSize(new Dimension(800, 450));
        painelForm.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        painelForm.setLayout(new GridBagLayout());

        GridBagConstraints fbc = new GridBagConstraints();
        fbc.insets = new Insets(15, 20, 15, 20);
        fbc.fill = GridBagConstraints.HORIZONTAL;

        // ----- LABEL ATIVIDADE -----
        JLabel lblAtividade = new JLabel("Atividade:");
        lblAtividade.setFont(new Font("Arial", Font.BOLD, 20));
        lblAtividade.setForeground(verdeEscuro);
        fbc.gridx = 0;
        fbc.gridy = 0;
        painelForm.add(lblAtividade, fbc);

        // ----- COMBO ATIVIDADE -----
        JComboBox<String> cbAtividade = new JComboBox<>(new String[]{"Preparar Solo"});
        cbAtividade.setFont(new Font("Arial", Font.PLAIN, 18));
        cbAtividade.setPreferredSize(new Dimension(250, 35));
        fbc.gridx = 0;
        fbc.gridy = 1;
        painelForm.add(cbAtividade, fbc);

        // ----- LABEL MATERIAL -----
        JLabel lblMaterial = new JLabel("Material:");
        lblMaterial.setFont(new Font("Arial", Font.BOLD, 20));
        lblMaterial.setForeground(verdeEscuro);
        fbc.gridx = 0;
        fbc.gridy = 2;
        painelForm.add(lblMaterial, fbc);

        // ----- COMBO MATERIAL -----
        JComboBox<String> cbMaterial = new JComboBox<>(new String[]{
                "Selecione...", "Semente milho", "Água", "Diesel"
        });
        cbMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        cbMaterial.setPreferredSize(new Dimension(300, 35));
        fbc.gridx = 0;
        fbc.gridy = 3;
        painelForm.add(cbMaterial, fbc);

        // ----- BOTÃO SALVAR -----
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 20));
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(200, 45));
        btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        fbc.gridx = 0;
        fbc.gridy = 4;
        fbc.insets = new Insets(50, 20, 10, 20);
        painelForm.add(btnSalvar, fbc);

        // Adicionar form ao centro
        gbc.gridy = 1;
        conteudo.add(painelForm, gbc);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAtrelarMaterial tela = new TelaAtrelarMaterial();
            tela.setVisible(true);
        });
    }
}

