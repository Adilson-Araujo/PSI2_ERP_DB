package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame; // <--- MUDOU DE JFrame PARA JInternalFrame
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

// Agora estende JInternalFrame
public abstract class BaseView extends JInternalFrame {

    private static final Color MENU_BG = new Color(0x3C, 0x40, 0x20);
    private static final Color MENU_BTN = new Color(0xF2, 0xF2, 0xF2);

    protected static final short TIPO_ASSOCIACAO = 1;
    protected static final short TIPO_PRODUTOR = 2;

    public BaseView(String titulo) {
        // Super(titulo, resizable, closable, maximizable, iconifiable)
        super(titulo, true, true, true, true);
        
        setSize(800, 600); // Tamanho padrão inicial (já que não é mais maximizado nativo)
        setLayout(new BorderLayout());

        // JInternalFrame não usa setDefaultCloseOperation(EXIT_ON_CLOSE) do mesmo jeito
        // O padrão já é DISPOSE_ON_CLOSE, então não precisa configurar explicitamente para fechar o app

        add(criarMenuLateral(), BorderLayout.WEST);
    }

    private JPanel criarMenuLateral() {
        JPanel panelLateral = new JPanel(new BorderLayout());
        panelLateral.setPreferredSize(new Dimension(180, 0));
        panelLateral.setBackground(MENU_BG);
        panelLateral.setBorder(new EmptyBorder(15, 5, 15, 5));

        JPanel panelBotoes = new JPanel(new GridLayout(0, 1, 0, 8));
        panelBotoes.setBackground(MENU_BG);
        
        // Removemos o botão HOME pois em Janela Interna geralmente não se "navega" para home, apenas fecha a janela
        // Mas se quiser manter, pode deixar.
        // JButton btnHome = criarBotao("Menu Inicial"); 
        JButton btnCriarPedido = criarBotao("Criar Pedido");
        JButton btnCriarProjeto = criarBotao("Criar Projeto");
        JButton btnConsultarPedidos = criarBotao("Consultar Pedido");
        JButton btnConsultarProjetos = criarBotao("Consultar Projetos");

        // btnHome.addActionListener(e -> abrirHome());
        

        try {
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            if (usuario.usuarioVO.getTipoUsuario() == TIPO_PRODUTOR) {
                btnCriarProjeto.setEnabled(false);
                btnConsultarProjetos.setEnabled(false);
            }
        } catch (Exception e) {
            btnCriarProjeto.setEnabled(false);
            btnConsultarProjetos.setEnabled(false);
        }

        // panelBotoes.add(btnHome);
        panelBotoes.add(btnCriarPedido);
        panelBotoes.add(btnCriarProjeto);
        panelBotoes.add(btnConsultarPedidos);
        panelBotoes.add(btnConsultarProjetos);

        panelLateral.add(panelBotoes, BorderLayout.NORTH);

        return panelLateral;
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(MENU_BTN);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(0, 40)); 
        return btn;
    }

    protected JPanel criarTitleBar(String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0x66, 0x66, 0x33));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lbl = new JLabel(titulo, JLabel.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 22));

        panel.add(lbl, BorderLayout.CENTER);
        return panel;
    }

    // O método abrirHome agora só fecha a janela interna
    private void abrirHome() {
        dispose();
    }
}