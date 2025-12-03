package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.PedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;

// 1. NOVO IMPORT
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;

public class ProdutorMainView extends BaseView {

    private static final Color BG = new Color(0xE9, 0xE9, 0xE9);

    private JTable tabelaPedidos;
    private JTable tabelaItens;
    private DefaultTableModel pedidosModel;
    private DefaultTableModel itensModel;
    private JLabel lblTotal;

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public ProdutorMainView() {
        super("Produtor — Meus Pedidos");

        add(criarPainelPrincipal(), BorderLayout.CENTER);
        carregarPedidos();
    }

    private JPanel criarPainelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);

        panel.add(criarTitleBar("Produtor — Meus Pedidos"), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(25, 25, 25, 25));

        center.add(criarCardPedidos());
        center.add(Box.createVerticalStrut(20));
        center.add(criarCardItens());
        center.add(Box.createVerticalStrut(15));
        center.add(criarRodape());

        panel.add(center, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarCardPedidos() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Meus Pedidos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        card.add(titulo, BorderLayout.NORTH);

        String[] col = {"ID", "Data", "Total (R$)", "Status"};
        pedidosModel = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabelaPedidos = new JTable(pedidosModel);
        tabelaPedidos.setRowHeight(26);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabelaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) carregarItens();
        });

        card.add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);
        return card;
    }

    private JPanel criarCardItens() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Itens do Pedido");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        card.add(titulo, BorderLayout.NORTH);

        String[] col = {"Produto", "Quantidade", "Valor Unitário", "Subtotal"};
        itensModel = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabelaItens = new JTable(itensModel);
        tabelaItens.setRowHeight(26);

        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTotal.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(Color.WHITE);
        rodape.add(lblTotal);

        card.add(new JScrollPane(tabelaItens), BorderLayout.CENTER);
        card.add(rodape, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarRodape() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(BG);

        JButton btnNovo = new JButton("Criar Pedido");
        btnNovo.addActionListener(e -> abrirCriarPedido());
        panel.add(btnNovo);

        return panel;
    }

    private void abrirCriarPedido() {
        new CriarPedidoView().setVisible(true);
        dispose();
    }

    // ---------------- CARREGAR DADOS ----------------

    private void carregarPedidos() {
        pedidosModel.setRowCount(0);

        // 2. REFATORAÇÃO: Obtendo ID de forma segura
        try {
            Long produtorId = Sessao.getAssociadoIdLogado();
            
            List<PedidoVO> pedidos = pedidoDAO.listarPorAssociado(produtorId);

            for (PedidoVO p : pedidos) {
                pedidosModel.addRow(new Object[]{
                        p.getId(),
                        p.getDataCriacao(),
                        p.getValorTotal(),
                        p.getStatusPedidoId()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar sessão: " + e.getMessage());
        }
    }

    private void carregarItens() {
        itensModel.setRowCount(0);
        lblTotal.setText("Total: R$ 0,00");

        int row = tabelaPedidos.getSelectedRow();
        if (row < 0) return;

        Long pedidoId = (Long) pedidosModel.getValueAt(row, 0);
        
        List<ItemPedidoVO> itens = pedidoDAO.buscarItensPorPedido(pedidoId);

        double total = 0;

        for (ItemPedidoVO it : itens) {
            var prod = produtoDAO.buscarPorId(it.getProdutoId());

            itensModel.addRow(new Object[]{
                    prod != null ? prod.getNome() : "Produto #" + it.getProdutoId(),
                    it.getQuantidadeTotal(),
                    it.getValorUnitario(),
                    it.getValorTotal()
            });

            total += it.getValorTotal().doubleValue();
        }

        lblTotal.setText(String.format("Total: R$ %.2f", total));
    }
}