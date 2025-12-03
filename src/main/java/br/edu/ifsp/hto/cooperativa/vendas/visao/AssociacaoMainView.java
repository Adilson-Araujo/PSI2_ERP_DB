package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ItemPedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.PedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;

public class AssociacaoMainView extends BaseView {

    private static final Color BG = new Color(0xE9, 0xE9, 0xE9);

    private JTable tabelaPedidos;
    private JTable tabelaItens;
    private DefaultTableModel pedidosModel;
    private DefaultTableModel itensModel;
    private JLabel lblTotal;

    // Filtros
    private TableRowSorter<DefaultTableModel> sorterPedidos;
    private JTextField txtFiltroProjeto;
    private JComboBox<String> cmbFiltroStatus;

    public AssociacaoMainView() {
        super("Associação - Pedidos");

        add(criarPainelPrincipal(), BorderLayout.CENTER);
        carregarPedidos();
    }

    private JPanel criarPainelPrincipal() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);

        panel.add(criarTitleBar("Associação — Pedidos Recebidos"), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(25, 25, 25, 25));

        center.add(criarCardPedidos());
        center.add(Box.createVerticalStrut(25));
        center.add(criarCardItens());

        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarCardPedidos() {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        // TOPO DO CARD: Título + Filtros na mesma linha ou abaixo
        JPanel topoCard = new JPanel(new BorderLayout());
        topoCard.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("Pedidos Cadastrados");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        topoCard.add(titulo, BorderLayout.WEST);
        
        // Adiciona Filtros
        topoCard.add(criarFiltrosPanel(), BorderLayout.EAST);
        topoCard.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        card.add(topoCard, BorderLayout.NORTH);

        String[] col = {"ID", "Projeto", "Data", "Total (R$)", "Status"};

        pedidosModel = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0) return Long.class;
                if(columnIndex == 3) return java.math.BigDecimal.class;
                return super.getColumnClass(columnIndex);
            }
        };

        tabelaPedidos = new JTable(pedidosModel);
        tabelaPedidos.setRowHeight(28);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Sorter
        sorterPedidos = new TableRowSorter<>(pedidosModel);
        tabelaPedidos.setRowSorter(sorterPedidos);

        tabelaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) carregarItens();
        });

        card.add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);

        return card;
    }

    private JPanel criarFiltrosPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.setBackground(Color.WHITE);

        // Filtro Projeto
        p.add(new JLabel("Projeto:"));
        txtFiltroProjeto = new JTextField(10);
        txtFiltroProjeto.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { aplicarFiltros(); }
            public void removeUpdate(DocumentEvent e) { aplicarFiltros(); }
            public void changedUpdate(DocumentEvent e) { aplicarFiltros(); }
        });
        p.add(txtFiltroProjeto);

        // Filtro Status
        p.add(new JLabel("Status:"));
        cmbFiltroStatus = new JComboBox<>();
        cmbFiltroStatus.addItem("Todos");
        cmbFiltroStatus.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) aplicarFiltros();
        });
        p.add(cmbFiltroStatus);

        return p;
    }

    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        // Texto Projeto (Coluna 1)
        String texto = txtFiltroProjeto.getText();
        if(!texto.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + texto, 1));
        }

        // Combo Status (Coluna 4)
        String status = (String) cmbFiltroStatus.getSelectedItem();
        if(status != null && !"Todos".equals(status)) {
            filtros.add(RowFilter.regexFilter("^" + status + "$", 4));
        }

        if(filtros.isEmpty()) sorterPedidos.setRowFilter(null);
        else sorterPedidos.setRowFilter(RowFilter.andFilter(filtros));
    }

    private JPanel criarCardItens() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Itens do Pedido Selecionado");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        card.add(titulo, BorderLayout.NORTH);

        String[] col = {"Produto", "Quantidade", "Valor Unitário", "Subtotal"};
        itensModel = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabelaItens = new JTable(itensModel);
        tabelaItens.setRowHeight(28);

        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotal.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(Color.WHITE);
        rodape.add(lblTotal);

        card.add(new JScrollPane(tabelaItens), BorderLayout.CENTER);
        card.add(rodape, BorderLayout.SOUTH);

        return card;
    }

    private void carregarPedidos() {
        pedidosModel.setRowCount(0);
        PedidoDAO dao = new PedidoDAO();
        List<PedidoVO> lista = dao.listarTodos();

        Set<String> statusUnicos = new HashSet<>();

        for (PedidoVO p : lista) {
            String dataStr = (p.getDataCriacao() != null) ? p.getDataCriacao().toLocalDate().toString() : "";
            
            // Tratamento null safe para descrição
            String statusDesc = p.getStatusDescricao();
            if(statusDesc == null) statusDesc = "Desconhecido";

            statusUnicos.add(statusDesc);

            pedidosModel.addRow(new Object[]{
                p.getId(),
                p.getProjetoId() != null ? p.getProjetoId() : "Avulso", 
                dataStr,
                p.getValorTotal(),
                statusDesc
            });
        }

        // Popula Combo Status
        cmbFiltroStatus.removeItemListener(cmbFiltroStatus.getItemListeners()[0]); // Pausa listener
        cmbFiltroStatus.removeAllItems();
        cmbFiltroStatus.addItem("Todos");
        statusUnicos.stream().sorted().forEach(cmbFiltroStatus::addItem);
        // Recria listener
        cmbFiltroStatus.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) aplicarFiltros();
        });
    }

    private void carregarItens() {
        itensModel.setRowCount(0);
        lblTotal.setText("Total: R$ 0,00");

        int linha = tabelaPedidos.getSelectedRow();
        if (linha == -1) return;
        
        // Conversão segura do índice da view para o model (necessário por causa do filtro!)
        int modelRow = tabelaPedidos.convertRowIndexToModel(linha);
        Long pedidoId = (Long) pedidosModel.getValueAt(modelRow, 0);

        ItemPedidoDAO itemDao = new ItemPedidoDAO(); 
        List<ItemPedidoVO> itens = itemDao.listarPorPedido(pedidoId);
        ProdutoDAO prodDAO = new ProdutoDAO();
        double total = 0;

        for (ItemPedidoVO item : itens) {
            ProdutoDAO.Produto prod = prodDAO.buscarPorId(item.getProdutoId());
            String nomeProduto = (prod != null) ? prod.getNome() : "Produto #" + item.getProdutoId();
            double subtotal = item.getValorTotal().doubleValue();
            total += subtotal;

            itensModel.addRow(new Object[]{
                    nomeProduto, item.getQuantidadeTotal(), item.getValorUnitario(), subtotal
            });
        }
        lblTotal.setText(String.format("Total: R$ %.2f", total));
    }
}