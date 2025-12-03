package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.net.URI;
// import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.AtividadeDAO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.DAO.MaterialDAO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.MaterialNaAtividadeVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.MaterialVO;

import java.awt.*;

public class TelaAtividades extends JFrame {

    private String cultura;
    private String nomeCanteiro;
    private java.util.Date inicio;
    private double areaM2;
    private double qtdKg;
    private Long canteiroId;
    private Long areaId;
    private Integer atividadeId;

    public TelaAtividades(String cultura, String nomeCanteiro, java.util.Date inicio, double areaM2, double qtdKg, Long canteiroId, Long areaId) {
        this.cultura = cultura;
        this.nomeCanteiro = nomeCanteiro;
        this.inicio = inicio;
        this.areaM2 = areaM2;
        this.qtdKg = qtdKg;
        this.canteiroId = canteiroId;
        this.areaId = areaId;
        setTitle("Tela Atividades");
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
        JPanel conteudo = new JPanel();
        conteudo.setBackground(cinzaFundo);
        conteudo.setLayout(new GridBagLayout());
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(120, 45));
        btnVoltar.addActionListener(e -> {
            TelaCanteiro telaCanteiro = new TelaCanteiro(cultura, nomeCanteiro, inicio, areaM2, qtdKg, canteiroId, areaId);
            telaCanteiro.setVisible(true);
            TelaAtividades.this.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(btnVoltar, gbc);

        // Buscar atividades do canteiro do banco de dados
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        List<AtividadeNoCanteiroVO> atividades = atividadeDAO.buscarAtividadesDoCanteiro(canteiroId.intValue());
        
        // Valores padrão caso não haja atividades
        String nomeAtividade = "Sem atividades";
        String dataAtividadeStr = "N/A";
        String tempoGastoStr = "0.0h";
        
        if (atividades != null && !atividades.isEmpty()) {
            // Pega a primeira atividade (você pode adaptar para mostrar todas ou uma específica)
            AtividadeNoCanteiroVO atividadeNoCanteiro = atividades.get(0);
            nomeAtividade = atividadeNoCanteiro.getAtividadeVO().getNomeAtividade();
            this.atividadeId = atividadeNoCanteiro.getAtividadeVO().getId();
            
            // Formata data_atividade
            if (atividadeNoCanteiro.getDataAtividade() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataAtividadeStr = sdf.format(atividadeNoCanteiro.getDataAtividade());
            }
            
            // Formata tempo_gasto_horas
            float tempoGasto = atividadeNoCanteiro.getTempoGastoHoras();
            tempoGastoStr = String.format("%.1fh", tempoGasto);
        }

        JLabel lblTitulo = new JLabel("Atividade: " + nomeAtividade, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        JPanel painelResumo = new JPanel(new GridLayout(1, 2, 40, 20));
        painelResumo.setOpaque(false);
        String[] textos = {
                "Data da Atividade: " + dataAtividadeStr,
                "Tempo gasto: " + tempoGastoStr
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

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setOpaque(false);

        JLabel lblAFazer = new JLabel("Materiais Necessários");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 28));
        lblAFazer.setForeground(verdeEscuro);
        painelTitulo.add(lblAFazer, BorderLayout.WEST);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        painelBotoes.setOpaque(false);

        JButton btnAdicionarMaterial = new JButton("Adicionar material");
        btnAdicionarMaterial.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdicionarMaterial.setBackground(verdeClaro);
        btnAdicionarMaterial.setForeground(Color.BLACK);
        btnAdicionarMaterial.setFocusPainted(false);
        btnAdicionarMaterial.setPreferredSize(new Dimension(200, 38));
        btnAdicionarMaterial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdicionarMaterial.addActionListener(e -> {
            if (atividadeId != null) {
                abrirDialogAdicionarMaterial();
            } else {
                JOptionPane.showMessageDialog(
                    TelaAtividades.this,
                    "Nenhuma atividade disponível para adicionar material.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
        painelBotoes.add(btnAdicionarMaterial);

        JButton btnConcluir = new JButton("Marcar como concluído");
        btnConcluir.setFont(new Font("Arial", Font.BOLD, 16));
        btnConcluir.setBackground(verdeClaro);
        btnConcluir.setForeground(Color.BLACK);
        btnConcluir.setFocusPainted(false);
        btnConcluir.setPreferredSize(new Dimension(220, 38));
        btnConcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConcluir.addActionListener(e -> {
            if (atividadeId != null) {
                int confirm = JOptionPane.showConfirmDialog(
                    TelaAtividades.this,
                    "Deseja marcar esta atividade como concluída?",
                    "Confirmar Conclusão",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    atividadeDAO.concluirAtividadeDoCanteiro(canteiroId.intValue(), atividadeId);
                    JOptionPane.showMessageDialog(
                        TelaAtividades.this,
                        "Atividade marcada como concluída!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    // Volta para tela canteiro
                    TelaCanteiro telaCanteiro = new TelaCanteiro(cultura, nomeCanteiro, inicio, areaM2, qtdKg, canteiroId, areaId);
                    telaCanteiro.setVisible(true);
                    TelaAtividades.this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(
                    TelaAtividades.this,
                    "Nenhuma atividade disponível para concluir.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
        painelBotoes.add(btnConcluir);

        painelTitulo.add(painelBotoes, BorderLayout.EAST);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(painelTitulo, gbc);


        ImageIcon iconEdit = null;
        ImageIcon iconDelete = null;

        try {
            iconEdit = new ImageIcon(URI.create("https://img.icons8.com/ios-glyphs/24/edit--v1.png").toURL());
            iconDelete = new ImageIcon(URI.create("https://img.icons8.com/ios-glyphs/24/trash--v1.png").toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }



        // Buscar materiais da atividade do banco de dados
        String[] colunas = {"ID", "Nome", "Unidade Medida", "Quantidade", "Quantidade Utilizada"};
        
        MaterialDAO materialDAO = new MaterialDAO();
        List<MaterialNaAtividadeVO> materiaisDaAtividade = new java.util.ArrayList<>();
        
        if (atividadeId != null) {
            materiaisDaAtividade = materialDAO.buscarMateriaisDaAtividade(atividadeId);
        }
        
        // Preencher dados da tabela com os materiais reais
        Object[][] dados = new Object[materiaisDaAtividade.size()][5];
        for (int i = 0; i < materiaisDaAtividade.size(); i++) {
            MaterialNaAtividadeVO materialNaAtividade = materiaisDaAtividade.get(i);
            dados[i][0] = materialNaAtividade.getMaterial().getId();
            dados[i][1] = materialNaAtividade.getMaterial().getNome();
            dados[i][2] = materialNaAtividade.getMaterial().getUnidadeMedida();
            dados[i][3] = String.format("%.2f", materialNaAtividade.getMaterial().getQuantidade());
            dados[i][4] = String.format("%.2f", materialNaAtividade.getQuantidadeUtilizada());
        }

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.setRowHeight(38);
        tabela.getTableHeader().setBackground(verdeClaro);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(250);  // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150);  // Unidade Medida
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150);  // Quantidade
        tabela.getColumnModel().getColumn(4).setPreferredWidth(200);  // Quantidade Utilizada

        JScrollPane scrollTabela = new JScrollPane(tabela);
        gbc.gridy = 3;
        gbc.weighty = 1;
        conteudo.add(scrollTabela, gbc);

        // Botão atrelar material removido - funcionalidade será implementada posteriormente
    }

    /**
     * Abre um diálogo para adicionar um material à atividade
     */
    private void abrirDialogAdicionarMaterial() {
        JDialog dialog = new JDialog(this, "Adicionar Material", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e ComboBox para selecionar material
        JLabel lblMaterial = new JLabel("Material:");
        lblMaterial.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(lblMaterial, gbc);

        MaterialDAO materialDAO = new MaterialDAO();
        List<MaterialVO> materiais = materialDAO.listarTodos();
        
        JComboBox<String> comboMateriais = new JComboBox<>();
        for (MaterialVO material : materiais) {
            comboMateriais.addItem(material.getId() + " - " + material.getNome() + " (" + material.getUnidadeMedida() + ")");
        }
        comboMateriais.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        dialog.add(comboMateriais, gbc);

        // Label e TextField para quantidade utilizada
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        dialog.add(lblQuantidade, gbc);

        JTextField txtQuantidade = new JTextField();
        txtQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        dialog.add(txtQuantidade, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.addActionListener(e -> dialog.dispose());
        painelBotoes.add(btnCancelar);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(157, 170, 61));
        btnSalvar.addActionListener(e -> {
            try {
                // Validar entrada
                if (comboMateriais.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(dialog, "Selecione um material.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String quantidadeStr = txtQuantidade.getText().trim();
                if (quantidadeStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Informe a quantidade utilizada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                float quantidadeUtilizada = Float.parseFloat(quantidadeStr);
                if (quantidadeUtilizada <= 0) {
                    JOptionPane.showMessageDialog(dialog, "A quantidade deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Extrair ID do material selecionado
                String itemSelecionado = (String) comboMateriais.getSelectedItem();
                int materialId = Integer.parseInt(itemSelecionado.split(" - ")[0]);
                
                // Inserir material na atividade
                materialDAO.inserirMaterialNaAtividade(atividadeId, materialId, quantidadeUtilizada);
                
                JOptionPane.showMessageDialog(dialog, "Material adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                
                // Recarregar a tela para atualizar a tabela
                TelaAtividades novaTelaAtividades = new TelaAtividades(cultura, nomeCanteiro, inicio, areaM2, qtdKg, canteiroId, areaId);
                novaTelaAtividades.setVisible(true);
                TelaAtividades.this.dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Quantidade inválida. Use apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao adicionar material: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        painelBotoes.add(btnSalvar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        dialog.add(painelBotoes, gbc);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAtividades tela = new TelaAtividades("Tomate", "Canteiro A", new java.util.Date(), 10.0, 50.0, 1L, 1L);
            tela.setVisible(true);
        });
    }
}
