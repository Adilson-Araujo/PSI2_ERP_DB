package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.ProdutoPrecificadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TelaSelecionarProdutoImportar extends JDialog {

    private JTable tabelaProdutos;
    private DefaultTableModel modeloProdutos;
    private JTextField txtQuantidade;
    private br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportar, btnCancelar;
    private List<ProdutoPrecificadoTO> listaProdutosCarregados = new ArrayList<>();

    // Produto selecionado
    private ProdutoPrecificadoTO produtoSelecionado;
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
        List<ProdutoPrecificadoTO> produtos = ControleEstoque.getInstance().listarPrecos(Timestamp.valueOf(LocalDateTime.now()));
        for(var produto : produtos){
            var nome = produto.getProduto().getNome();
            var preco = produto.getPrecoPPA().getValor();
            modeloProdutos.addRow(new Object[]{nome, preco});
            listaProdutosCarregados.add(produto);
        }

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
            produtoSelecionado = listaProdutosCarregados.get(row);

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

    public int getQuantidade() { return quantidade; }
    public ProdutoPrecificadoTO getProdutoSelecionado() {return produtoSelecionado;}

    public boolean produtoFoiSelecionado() {
        return produtoSelecionado != null;
    }
}
