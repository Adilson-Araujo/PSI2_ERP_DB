package br.edu.ifsp.hto.cooperativa.vendas.visao;

import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.PedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.StatusPedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.StatusPedidoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultaPedidosView extends BaseView {

    private JTable tabela;
    private DefaultTableModel model;
    
    // Componentes de Filtro
    private JTextField txtBusca;
    private JComboBox<StatusItem> cbStatus; // Wrapper simples para exibir no combo
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;

    private static final short TIPO_PRODUTOR = 1;
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final StatusPedidoDAO statusDAO = new StatusPedidoDAO();

    public ConsultaPedidosView() {
        super("Consultar Pedidos");
        add(criarPainel(), BorderLayout.CENTER);
        
        // Carrega os status no combo antes de buscar pedidos
        carregarComboStatus();
        carregarPedidos(); 
    }

    private JPanel criarPainel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // --- PAINEL DE FILTROS (TOPO) ---
        JPanel filtrosPanel = new JPanel(new GridBagLayout());
        filtrosPanel.setBackground(Color.WHITE);
        filtrosPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 1. Campo de Busca Texto
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.4;
        filtrosPanel.add(new JLabel("Buscar (Cliente/Projeto):"), gbc);
        
        gbc.gridy = 1;
        txtBusca = new JTextField();
        filtrosPanel.add(txtBusca, gbc);

        // 2. Combo Status
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.2;
        filtrosPanel.add(new JLabel("Status:"), gbc);

        gbc.gridy = 1;
        cbStatus = new JComboBox<>();
        filtrosPanel.add(cbStatus, gbc);

        // 3. Data Início
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.15;
        filtrosPanel.add(new JLabel("De (Data):"), gbc);

        gbc.gridy = 1;
        txtDataInicio = criarCampoData();
        filtrosPanel.add(txtDataInicio, gbc);

        // 4. Data Fim
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.15;
        filtrosPanel.add(new JLabel("Até (Data):"), gbc);

        gbc.gridy = 1;
        txtDataFim = criarCampoData();
        filtrosPanel.add(txtDataFim, gbc);

        // 5. Botão Buscar
        gbc.gridx = 4; gbc.gridy = 1; gbc.weightx = 0.1;
        JButton btnBuscar = new JButton("Filtrar");
        btnBuscar.setBackground(new Color(60, 141, 188)); // Azul estilo ERP
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(e -> carregarPedidos());
        filtrosPanel.add(btnBuscar, gbc);

        // 6. Botão Limpar
        gbc.gridx = 5; 
        JButton btnLimpar = new JButton("X");
        btnLimpar.setToolTipText("Limpar Filtros");
        btnLimpar.addActionListener(e -> limparFiltros());
        filtrosPanel.add(btnLimpar, gbc);

        mainPanel.add(filtrosPanel, BorderLayout.NORTH);

        // --- TABELA (CENTRO) ---
        model = new DefaultTableModel(new Object[]{
                "ID", "Associado", "Projeto", "Data", "Valor (R$)", "Status"
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

    private void carregarComboStatus() {
        cbStatus.removeAllItems();
        cbStatus.addItem(new StatusItem(0L, "Todos")); // Opção padrão
        
        try {
            List<StatusPedidoVO> lista = statusDAO.obterTodos();
            for (StatusPedidoVO s : lista) {
                cbStatus.addItem(new StatusItem(s.getId(), s.getDescricao()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarPedidos() {
        model.setRowCount(0);

        Long idProdutor = null;
        try {
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            if (usuario != null && usuario.usuarioVO.getTipoUsuario() == TIPO_PRODUTOR) {
                idProdutor = Sessao.getAssociadoIdLogado();
            }
        } catch (Exception e) {
            System.err.println("Erro sessão: " + e.getMessage());
        }

        // Pega os valores dos filtros
        String termo = txtBusca.getText();
        StatusItem statusSelecionado = (StatusItem) cbStatus.getSelectedItem();
        Long statusId = (statusSelecionado != null) ? statusSelecionado.id : null;
        
        LocalDate dtInicio = parseData(txtDataInicio.getText());
        LocalDate dtFim = parseData(txtDataFim.getText());

        // Chama o novo método do DAO
        List<PedidoVO> lista = pedidoDAO.filtrarAvancado(idProdutor, termo, statusId, dtInicio, dtFim);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (PedidoVO p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getAssociadoId(), // Ideal seria trazer o nome do associado no VO/SQL
                    (p.getProjetoId() == null || p.getProjetoId() == 0) ? "-" : p.getProjetoId(),
                    p.getDataCriacao() != null ? p.getDataCriacao().format(dtf) : "-",
                    String.format("%.2f", p.getValorTotal()),
                    p.getStatusDescricao()
            });
        }
    }

    private void limparFiltros() {
        txtBusca.setText("");
        cbStatus.setSelectedIndex(0);
        txtDataInicio.setValue(null);
        txtDataFim.setValue(null);
        carregarPedidos();
    }

    private LocalDate parseData(String dataStr) {
        if (dataStr == null || dataStr.trim().equals("_/_/____")) return null;
        try {
            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null; // Data inválida ignorada
        }
    }

    // Classe auxiliar interna para popular o ComboBox corretamente
    private static class StatusItem {
        Long id;
        String descricao;

        public StatusItem(Long id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            return descricao;
        }
    }
}