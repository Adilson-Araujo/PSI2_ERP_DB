package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import java.awt.*;

public class TelaEditarTalhao extends JFrame {

    public TelaEditarTalhao() {
        setTitle("Editar Talhão");
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

        String[] botoes = { "Tela inicial", "Área de plantio", "Registrar problemas", "Relatório de produção" };
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
        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        // ======= TOPO (Voltar + Título) =======
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

        JLabel lblTitulo = new JLabel("Editar Talhão");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(verdeEscuro);
        painelTopo.add(lblTitulo, BorderLayout.EAST);

        conteudo.add(painelTopo, BorderLayout.NORTH);

        // ======= FORMULÁRIO CENTRAL =======
        JPanel painelForm = new JPanel();
        painelForm.setOpaque(false);
        painelForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // ======= Campo: Nome do Talhão =======
        JLabel lblNomeTalhao = new JLabel("Nome do Talhão:");
        lblNomeTalhao.setFont(new Font("Arial", Font.BOLD, 16));
        lblNomeTalhao.setForeground(verdeEscuro);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelForm.add(lblNomeTalhao, gbc);

        JTextField txtNomeTalhao = new JTextField();
        txtNomeTalhao.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNomeTalhao.setPreferredSize(new Dimension(350, 45));
        txtNomeTalhao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        gbc.gridy = 1;
        painelForm.add(txtNomeTalhao, gbc);

        // ======= Campo: Área Total =======
        JLabel lblAreaTotal = new JLabel("Área total (m2):");
        lblAreaTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblAreaTotal.setForeground(verdeEscuro);
        gbc.gridy = 2;
        painelForm.add(lblAreaTotal, gbc);

        JTextField txtAreaTotal = new JTextField();
        txtAreaTotal.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAreaTotal.setPreferredSize(new Dimension(350, 45));
        txtAreaTotal.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        gbc.gridy = 3;
        painelForm.add(txtAreaTotal, gbc);

        // ======= Botão Salvar =======
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(200, 50));
        gbc.gridy = 4;
        painelForm.add(btnSalvar, gbc);

        // Painel centralizado, levemente deslocado para cima
        JPanel painelCentralizado = new JPanel(new GridBagLayout());
        painelCentralizado.setOpaque(false);

        GridBagConstraints gbcCentro = new GridBagConstraints();
        gbcCentro.gridx = 0;
        gbcCentro.gridy = 0;
        gbcCentro.insets = new Insets(-80, 0, 0, 0); // sobe um pouco o formulário

        painelCentralizado.add(painelForm, gbcCentro);
        conteudo.add(painelCentralizado, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEditarTalhao tela = new TelaEditarTalhao();
            tela.setVisible(true);
        });
    }
}
