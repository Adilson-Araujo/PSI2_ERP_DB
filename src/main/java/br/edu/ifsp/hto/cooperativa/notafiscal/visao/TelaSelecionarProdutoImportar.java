package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TelaSelecionarProdutoImportar extends JDialog {

    private JTable tabelaProdutos;
    private DefaultTableModel modeloProdutos;
    private JTextField txtQuantidade;
    private br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportar, btnCancelar;

    // Produto selecionado (para o mock funcionar)
    private String produtoSelecionado;
    private double valorUnitario;
    private int quantidade;

    public TelaSelecionarProdutoImportar(JFrame parent) {
        super(parent, "Selecionar Produto para Importar", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        modeloProdutos = new DefaultTableModel(new Object[]{"Nome", "Valor Unitário"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        // Mock
        modeloProdutos.addRow(new Object[]{"Alface", 0.99});
        modeloProdutos.addRow(new Object[]{"Tomate", 2.29});
        modeloProdutos.addRow(new Object[]{"Batata", 1.50});
        modeloProdutos.addRow(new Object[]{"Maçã", 9});
        modeloProdutos.addRow(new Object[]{"Manga", 7.00});

        tabelaProdutos = new JTable(modeloProdutos);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabelaProdutos);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Label("Quantidade:"));
        txtQuantidade = new JTextField("1", 6);
        bottomPanel.add(txtQuantidade);

        btnImportar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Selecionado");
        btnCancelar = new Button("Cancelar");
        bottomPanel.add(btnImportar);
        bottomPanel.add(btnCancelar);

        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Eventos
        btnImportar.addActionListener(e -> importarProduto());
        btnCancelar.addActionListener(e -> dispose());

        // Fechar com ESC
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void importarProduto() {
        int row = tabelaProdutos.getSelectedRow();
        if (row >= 0) {
            produtoSelecionado = String.valueOf(modeloProdutos.getValueAt(row, 0));
            valorUnitario = Double.parseDouble(modeloProdutos.getValueAt(row, 1).toString());
            try {
                quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!");
                return;
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
        }
    }

    // Getters para recuperar o produto selecionado
    public String getProdutoSelecionado() { return produtoSelecionado; }
    public double getValorUnitario() { return valorUnitario; }
    public int getQuantidade() { return quantidade; }

    public boolean produtoFoiSelecionado() {
        return produtoSelecionado != null;
    }

    public static void main(String[] args) {
        TelaSelecionarProdutoImportar tela = new TelaSelecionarProdutoImportar(null);
        tela.setVisible(true);
        if (tela.produtoFoiSelecionado()) {
            System.out.println("Produto: " + tela.getProdutoSelecionado());
            System.out.println("Qtd: " + tela.getQuantidade());
            System.out.println("Valor: " + tela.getValorUnitario());
        } else {
            System.out.println("Nenhum produto selecionado.");
        }
    }
}
