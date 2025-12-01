package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.PaletaCores;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JDesktopPane desktopPane; // A "mesa" onde as janelas vão flutuar

    public TelaPrincipal() {
        setTitle("Sistema de Notas Fiscais");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Layout principal da janela

        // 1. HEADER (NORTE)
        add(criarHeader(), BorderLayout.NORTH);

        // 2. MENU LATERAL (OESTE)
        add(criarMenuLateral(), BorderLayout.WEST);

        // 3. ÁREA DE TRABALHO (CENTRO) - MUDANÇA AQUI
        // usando JDesktopPane
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.LIGHT_GRAY); // Cor de fundo da área de janelas
        add(desktopPane, BorderLayout.CENTER);
    }

    private JPanel criarHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(PaletaCores.verdeClaro);
        header.setPreferredSize(new Dimension(0, 40));

        JLabel lblMenuIcon = new JLabel("☰");
        lblMenuIcon.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblMenuIcon.setForeground(PaletaCores.verdeEscuro);
        lblMenuIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        header.add(lblMenuIcon);

        JLabel lblTituloHeader = new JLabel("Nota Fiscal");
        lblTituloHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloHeader.setForeground(PaletaCores.verdeEscuro);
        lblTituloHeader.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(lblTituloHeader);
        return header;
    }

    private JPanel criarMenuLateral() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(PaletaCores.verdeEscuro);
        menu.setPreferredSize(new Dimension(220, 0));
        menu.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        Button btnCadastrarAssociado = new Button("Cadastrar Associado");
        Button btnCadastroVenda = new Button("Cadastro de Venda");
        Button btnGerarNfVenda = new Button("Gerar NF de Venda");

        btnCadastrarAssociado.addActionListener(e -> {
            TelaCadastroAssociado tela = new TelaCadastroAssociado();
            adicionarJanela(tela);
        });

        btnCadastroVenda.addActionListener(e -> {
            TelaCadastroVenda tela = new TelaCadastroVenda();
            adicionarJanela(tela);
        });

        btnGerarNfVenda.addActionListener(e -> {
            TelaGerarNotaFiscalVenda tela = new TelaGerarNotaFiscalVenda();
            adicionarJanela(tela);
        });

        // Adiciona visualmente os botões
        menu.add(configurarBotaoMenu(btnCadastrarAssociado));
        menu.add(Box.createVerticalStrut(10));
        menu.add(configurarBotaoMenu(btnCadastroVenda));
        menu.add(Box.createVerticalStrut(10));
        menu.add(configurarBotaoMenu(btnGerarNfVenda));
        menu.add(Box.createVerticalGlue());

        return menu;
    }

    // Método auxiliar para evitar código repetido e tratar a adição no desktop
    private void adicionarJanela(JInternalFrame frame) {
        // Verifica se a janela já está visível para não abrir duplicada (opcional, mas recomendado)
        for (JInternalFrame f : desktopPane.getAllFrames()) {
            if (f.getClass().equals(frame.getClass())) {
                try {
                    f.setSelected(true); // Se já existe, só foca nela
                } catch (Exception ex) {}
                return;
            }
        }

        desktopPane.add(frame); // Adiciona na "mesa"
        frame.setVisible(true); // Torna visível

        // Tenta focar na janela nova
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    // Método auxiliar para abrir a janela interna
    private void abrirJanela(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
        // Traz para frente caso tenha muitas janelas
        try {
            frame.setSelected(true);
        } catch (Exception ex) {
        }
    }

    private Component configurarBotaoMenu(Button btn) {
        Dimension btnSize = new Dimension(190, 45);
        btn.setPreferredSize(btnSize);
        btn.setMinimumSize(btnSize);
        btn.setMaximumSize(btnSize);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}