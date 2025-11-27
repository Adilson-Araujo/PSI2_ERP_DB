package br.edu.ifsp.hto.cooperativa.producao.visao;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController;
import br.edu.ifsp.hto.cooperativa.producao.modelo.Area;

public class TelaGerenciarArea extends JFrame {
    
    private long associadoId;

    public TelaGerenciarArea(long associadoId) {
        this.associadoId = associadoId;
        initComponents();
    }


    public void initComponents() {
        setTitle("Gerenciar Área");
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

        String[] botoes = { "Área de plantio", "Registrar problemas", "Relatório de produção" };
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
        List<Area> lista = controller.carregarAreas(associadoId);
        JComboBox<Area> comboArea = new JComboBox<>(lista.toArray(new Area[0]));


        comboArea.setFont(new Font("Arial", Font.PLAIN, 16));
        comboArea.setPreferredSize(new Dimension(350, 45));
        comboArea.setBackground(Color.WHITE);
        comboArea.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        gbc.gridy = 1;
        painelForm.add(comboArea, gbc);


        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(e -> {
            Area areaIncompleta = (Area) comboArea.getSelectedItem();
            
            if (areaIncompleta != null) {
                // Instancia o controller novamente
                GerenciarAreaController ctrl = new GerenciarAreaController();
                
                // *** 🔑 A CORREÇÃO ESTÁ AQUI: RECUPERAR A ÁREA COMPLETA PELO ID ***
                Area areaCompleta = ctrl.carregarAreaCompletaPorId(areaIncompleta.getId());
                
                if (areaCompleta != null) {
                    new TelaTalhao(areaCompleta).setVisible(true); // Abre a tela com a área COMPLETA
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaGerenciarArea tela = new TelaGerenciarArea(1); // só para testar
            tela.setVisible(true);
        });
    }

}
