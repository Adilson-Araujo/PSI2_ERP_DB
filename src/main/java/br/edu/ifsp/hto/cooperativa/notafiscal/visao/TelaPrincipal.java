package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.PaletaCores;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame { // Não precisa herdar de ViewBase

    private CardLayout cardLayout;
    private JPanel painelCentral; // O painel que troca de telas

    public TelaPrincipal() {
        setTitle("Sistema de Notas Fiscais");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Layout principal da janela

        // 1. HEADER (NORTE) - Barra verde clara
        add(criarHeader(), BorderLayout.NORTH);

        // 2. MENU LATERAL (OESTE) - Barra verde escura
        add(criarMenuLateral(), BorderLayout.WEST);

        // 3. PAINEL CENTRAL (CENTRO) - Onde as telas vão aparecer
        // Reutilizando seu CardLayout original
        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        painelCentral.setBackground(PaletaCores.branco); // Fundo branco padrão

        // Adicionar suas telas (agora como JPanels)
        // Usamos o .getContentPane() como você fez, mas o ideal é a classe ser um JPanel
        painelCentral.add(new TelaCadastroAssociado().getContentPane(), "CADASTRO ASSOCIADO");
        painelCentral.add(new TelaCadastroVenda().getContentPane(), "CADASTRO NFE");
        painelCentral.add(new TelaGerarNotaFiscalVenda().getContentPane(), "GERAR");

        // Adiciona o painel central ao centro do BorderLayout
        add(painelCentral, BorderLayout.CENTER);
    }

    /**
     * Cria o cabeçalho superior (Verde Claro)
     */
    private JPanel criarHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(PaletaCores.verdeClaro);
        header.setPreferredSize(new Dimension(0, 40)); // Altura fixa

        // Ícone de Menu (como na imagem)
        JLabel lblMenuIcon = new JLabel("☰");
        lblMenuIcon.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblMenuIcon.setForeground(PaletaCores.verdeEscuro);
        lblMenuIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        header.add(lblMenuIcon, BorderLayout.WEST);

        // 3. Crie o novo título "Nota Fiscal"
        JLabel lblTituloHeader = new JLabel("Nota Fiscal");
        lblTituloHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloHeader.setForeground(PaletaCores.verdeEscuro);
        lblTituloHeader.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto

        header.add(lblTituloHeader, BorderLayout.CENTER);
        return header;
    }

    /**
     * Cria o menu lateral com os botões (Verde Escuro)
     */
    private JPanel criarMenuLateral() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS)); // Um botão abaixo do outro
        menu.setBackground(PaletaCores.verdeEscuro);
        menu.setPreferredSize(new Dimension(220, 0)); // Largura fixa
        menu.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); // Espaço em cima/baixo

        // Criando os botões (os mesmos que você tinha)
        // A sua classe 'Button.java' (fundo branco, texto verde) é EXATAMENTE
        // o estilo dos botões da imagem de referência. Perfeito.
        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnCadastrarAssociado = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Cadastrar Associado");
        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnCadastroVenda = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Cadastro de Venda");
        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnGerarNfVenda = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Gerar NF de Venda");

        // Adiciona ações para trocar os cards
        btnCadastrarAssociado.addActionListener(e -> cardLayout.show(painelCentral, "CADASTRO ASSOCIADO"));
        btnCadastroVenda.addActionListener(e -> cardLayout.show(painelCentral, "CADASTRO NFE"));
        btnGerarNfVenda.addActionListener(e -> cardLayout.show(painelCentral, "GERAR"));

        // Adiciona os botões ao menu lateral
        menu.add(Box.createVerticalStrut(10)); // Espaçador
        menu.add(configurarBotaoMenu(btnCadastrarAssociado));
        menu.add(Box.createVerticalStrut(10)); // Espaçador
        menu.add(configurarBotaoMenu(btnCadastroVenda));
        menu.add(Box.createVerticalStrut(10)); // Espaçador
        menu.add(configurarBotaoMenu(btnGerarNfVenda));

        menu.add(Box.createVerticalGlue()); // Empurra tudo para cima

        return menu;
    }

    /**
     * Helper para configurar o tamanho e alinhamento dos botões do menu
     */
    private Component configurarBotaoMenu(Button btn) {
        // Define o tamanho preferido/máximo do botão
        Dimension btnSize = new Dimension(190, 45);
        btn.setPreferredSize(btnSize);
        btn.setMinimumSize(btnSize);
        btn.setMaximumSize(btnSize);

        // Alinha o botão no centro do painel (BoxLayout)
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Define a fonte (opcional, mas melhora o visual)
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    // Main para testar
    public static void main(String[] args) {
        // Define o Look and Feel para uma aparência mais moderna (Nimbus)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se não achar, usa o padrão
        }

        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}