package br.edu.ifsp.hto.cooperativa.producao.visao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.TalhaoDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO;
import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController;

import java.awt.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class TelaAdicionarTalhao extends JFrame {

    private final AreaVO area;

    public TelaAdicionarTalhao(AreaVO area) {
        this.area = area;
        setTitle("Adicionar Talhão - Área: " + (area != null ? area.getNome() : "--"));
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
        btnVoltar.addActionListener(e -> {
            // Volta para a TelaTalhao com a mesma área (recarregada)
            GerenciarAreaController controller = new GerenciarAreaController();
            AreaVO nova = controller.carregarAreaCompletaPorId(area.getId());
            if (nova != null) new TelaTalhao(nova).setVisible(true);
            dispose();
        });

        JLabel lblTitulo = new JLabel("Adicionar Talhão");
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
            // ======= Campo: Observações =======
            JLabel lblObs = new JLabel("Observações:");
            lblObs.setFont(new Font("Arial", Font.BOLD, 16));
            lblObs.setForeground(verdeEscuro);
            gbc.gridy = 4;
            painelForm.add(lblObs, gbc);

            JTextArea txtObs = new JTextArea(4, 30);
            txtObs.setLineWrap(true);
            txtObs.setWrapStyleWord(true);
            txtObs.setFont(new Font("Arial", Font.PLAIN, 14));
            JScrollPane spObs = new JScrollPane(txtObs);
            gbc.gridy = 5;
            painelForm.add(spObs, gbc);

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
        gbc.gridy = 6;
        painelForm.add(btnSalvar, gbc);

        // Ação do salvar: valida, insere via TalhaoDAO, e reabre TelaTalhao com dados atualizados
        btnSalvar.addActionListener(e -> {
            String nome = txtNomeTalhao.getText().trim();
            String areaStr = txtAreaTotal.getText().trim();
            String observ = txtObs.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome do talhão é obrigatório.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            BigDecimal areaTalhao;
            try {
                areaTalhao = new BigDecimal(areaStr.replace(',', '.'));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Área inválida. Use um número válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            TalhaoVO t = new TalhaoVO();
            t.setAreaId(area.getId());
            t.setNome(nome);
            t.setAreaTalhao(areaTalhao);
            t.setObservacoes(observ);
            t.setStatus("Ativo");

            TalhaoDAO dao = new TalhaoDAO();
            try {
                dao.inserir(t);
                JOptionPane.showMessageDialog(this, "Talhão criado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Recarrega área completa e reabre TelaTalhao
                GerenciarAreaController controller = new GerenciarAreaController();
                AreaVO nova = controller.carregarAreaCompletaPorId(area.getId());
                if (nova != null) new TelaTalhao(nova).setVisible(true);
                dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar talhão: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

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

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         TelaAdicionarTalhao tela = new TelaAdicionarTalhao();
    //         tela.setVisible(true);
    //     });
    // }
}
