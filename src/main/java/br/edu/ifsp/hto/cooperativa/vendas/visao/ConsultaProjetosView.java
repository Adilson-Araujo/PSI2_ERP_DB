package br.edu.ifsp.hto.cooperativa.vendas.visao;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProjetoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultaProjetosView extends BaseView {

    private JTable tabela;
    private DefaultTableModel model;
    
    // Filtros
    private JTextField txtBusca;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;

    private final ProjetoDAO projetoDAO = new ProjetoDAO();

    public ConsultaProjetosView() {
        super("Consultar Projetos");
        add(criarPainel(), BorderLayout.CENTER);
        carregarProjetos();
    }

    private JPanel criarPainel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // --- PAINEL DE FILTROS ---
        JPanel filtrosPanel = new JPanel(new GridBagLayout());
        filtrosPanel.setBackground(Color.WHITE);
        filtrosPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Busca por Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        filtrosPanel.add(new JLabel("Nome do Projeto:"), gbc);

        gbc.gridy = 1;
        txtBusca = new JTextField();
        filtrosPanel.add(txtBusca, gbc);

        // 2. Data Início
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.2;
        filtrosPanel.add(new JLabel("De (Data Criação):"), gbc);

        gbc.gridy = 1;
        txtDataInicio = criarCampoData();
        filtrosPanel.add(txtDataInicio, gbc);

        // 3. Data Fim
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.2;
        filtrosPanel.add(new JLabel("Até (Data Criação):"), gbc);

        gbc.gridy = 1;
        txtDataFim = criarCampoData();
        filtrosPanel.add(txtDataFim, gbc);

        // 4. Botão Filtrar
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 0.1;
        JButton btnBuscar = new JButton("Filtrar");
        btnBuscar.setBackground(new Color(60, 141, 188)); // Azul ERP
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(e -> carregarProjetos());
        filtrosPanel.add(btnBuscar, gbc);

        // 5. Botão Limpar
        gbc.gridx = 4;
        JButton btnLimpar = new JButton("X");
        btnLimpar.setToolTipText("Limpar Filtros");
        btnLimpar.addActionListener(e -> limparFiltros());
        filtrosPanel.add(btnLimpar, gbc);

        mainPanel.add(filtrosPanel, BorderLayout.NORTH);

        // --- TABELA ---
        model = new DefaultTableModel(new Object[]{
                "ID", "Nome do Projeto", "Data Criação", "Data Final", "Orçamento (R$)"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabela = new JTable(model);
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scroll, BorderLayout.CENTER);

        return mainPanel;
    }

    private JFormattedTextField criarCampoData() {
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            return new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }

    private void carregarProjetos() {
        model.setRowCount(0);

        String termo = txtBusca.getText();
        LocalDate dtInicio = parseData(txtDataInicio.getText());
        LocalDate dtFim = parseData(txtDataFim.getText());

        List<ProjetoVO> lista = projetoDAO.filtrarAvancado(termo, dtInicio, dtFim);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (ProjetoVO p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNomeProjeto(),
                    p.getDataCriacao() != null ? p.getDataCriacao().format(dtf) : "-",
                    p.getDataFinal() != null ? p.getDataFinal().format(dtf) : "-",
                    p.getOrcamento() != null ? String.format("%.2f", p.getOrcamento()) : "0.00"
            });
        }
    }

    private void limparFiltros() {
        txtBusca.setText("");
        txtDataInicio.setValue(null);
        txtDataFim.setValue(null);
        carregarProjetos();
    }

    private LocalDate parseData(String dataStr) {
        if (dataStr == null || dataStr.trim().equals("_/_/____")) return null;
        try {
            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null;
        }
    }
}