package br.edu.ifsp.hto.cooperativa.producao.visao;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;

public class TelaGerenciarArea extends JInternalFrame {

    // Campo para guardar o ID do Associado
    private long associadoId; 
    private GerenciarAreaController controller;
    private JDesktopPane desktop;

    // 🔑 NOVO CONSTRUTOR NECESSÁRIO
    public TelaGerenciarArea(JDesktopPane desktop) {
        super("Gerenciar Área", true, true, true, true);
        this.desktop = desktop;
        // Busca o ID do associado logado na Sessão estática
        try {
            this.associadoId = Sessao.getAssociadoIdLogado(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado. Retornando ao login.", "Erro de Sessão", JOptionPane.ERROR_MESSAGE);
            return; // Impede a continuação se a sessão falhar
        }
        this.controller = new GerenciarAreaController();
        initComponents(); 
    }

    public void initComponents() {
        // 1. *** 🔑 CHAVE: Recuperar o associadoId da Sessão no início ***
        long associadoId;
        try {
            // Chama o método estático para obter o ID
            associadoId = Sessao.getAssociadoIdLogado(); 
        } catch (RuntimeException e) {
            // Tratar erro caso não haja usuário logado (o Sessao.getAssociadoIdLogado() lança RuntimeException)
            JOptionPane.showMessageDialog(this, e.getMessage() + ". Redirecionando para login.", "Erro de Sessão", JOptionPane.ERROR_MESSAGE);
            // new TelaLogin().setVisible(true); // Exemplo de redirecionamento
            dispose();
            return;
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 800);
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

            // 🔑 ADIÇÃO: Listener de Evento para o botão
            botao.addActionListener(e -> {
                try {
                    if (texto.equals("Tela inicial")) {
                        // AÇÃO CORRETA para o botão "Tela inicial"
                        TelaInicial tela = new TelaInicial(desktop);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        this.dispose(); // Fecha a tela atual (TelaGerenciarArea)
                    } else if (texto.equals("Área de plantio")) {
                        // Já está na TelaGerenciarArea, não faz nada ou apenas foca na tela
                        // Não é necessário navegar para si mesmo
                    } else if (texto.equals("Registrar problemas")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController(model);
                        TelaRegistrarProblemas tela = new TelaRegistrarProblemas(desktop, controller);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        this.dispose();
                    } else if (texto.equals("Relatório de produção")) {
                        br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel model = 
                            new br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel();
                        br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController controller = 
                            new br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController(model);
                        TelaRelatorioProducao tela = new TelaRelatorioProducao(desktop, controller);
                        desktop.add(tela);
                        tela.setVisible(true);
                        try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        this.dispose();
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
        // 🔑 Adicionar Ação ao Botão Voltar
        btnVoltar.addActionListener(e -> {
            // Fecha a tela atual
            dispose(); 

            // CORRIGIDO PARA USAR O CONSTRUTOR COM desktop
            TelaInicial tela = new TelaInicial(desktop);
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
        });

        JLabel lblTitulo = new JLabel("Gerenciar Área");
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

        JLabel lblSelecionar = new JLabel("Selecionar área");
        lblSelecionar.setFont(new Font("Arial", Font.BOLD, 16));
        lblSelecionar.setForeground(verdeEscuro);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelForm.add(lblSelecionar, gbc);

        // Carregar áreas do banco de dados
        GerenciarAreaController controller = new GerenciarAreaController();
        List<AreaVO> lista = controller.carregarAreas(associadoId);
        JComboBox<AreaVO> comboArea = new JComboBox<>(lista.toArray(new AreaVO[0]));


        comboArea.setFont(new Font("Arial", Font.PLAIN, 16));
        comboArea.setPreferredSize(new Dimension(350, 45));
        comboArea.setBackground(Color.WHITE);
        comboArea.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        gbc.gridy = 1;
        painelForm.add(comboArea, gbc);


        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(e -> {
            AreaVO areaIncompleta = (AreaVO) comboArea.getSelectedItem();
            
            if (areaIncompleta != null) {
                // Instancia o controller novamente
                GerenciarAreaController ctrl = new GerenciarAreaController();
                
                // *** 🔑 A CORREÇÃO ESTÁ AQUI: RECUPERAR A ÁREA COMPLETA PELO ID ***
                AreaVO areaCompleta = ctrl.carregarAreaCompletaPorId(areaIncompleta.getId());
                
                if (areaCompleta != null) {
                    TelaTalhao tela = new TelaTalhao(desktop, areaCompleta); // Abre a tela com a área COMPLETA
                    desktop.add(tela);
                    tela.setVisible(true);
                    try { tela.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar os detalhes da área.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(200, 50));
        gbc.gridy = 2;
        painelForm.add(btnSalvar, gbc);

        // Painel centralizado, mas levemente deslocado para cima
        JPanel painelCentralizado = new JPanel(new GridBagLayout());
        painelCentralizado.setOpaque(false);

        GridBagConstraints gbcCentro = new GridBagConstraints();
        gbcCentro.gridx = 0;
        gbcCentro.gridy = 0;
        gbcCentro.insets = new Insets(-100, 0, 0, 0); // valor negativo sobe o painel (ajuste conforme necessário)

        painelCentralizado.add(painelForm, gbcCentro);
        conteudo.add(painelCentralizado, BorderLayout.CENTER);

    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         // Exemplo de como funcionaria após um login real:
    //         new TelaGerenciarArea().setVisible(true);
    //     });
    // }

}
