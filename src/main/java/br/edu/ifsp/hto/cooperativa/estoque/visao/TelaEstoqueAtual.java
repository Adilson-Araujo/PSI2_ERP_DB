package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaEstoqueAtual {
    public static JInternalFrame gerarFrameInterno(){
        JInternalFrame janela = new JInternalFrame();
        
        JPanel painelControle;
        JPanel painelExibicao;
        JTextField campoProduto;
        JButton botaoExportar;
        JTable tabelaEstoque;
        JScrollPane scrollTabela;
        
        janela.setTitle("Visão – Estoque Atual");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        painelControle = new JPanel();
        painelControle.setLayout(new FlowLayout(FlowLayout.LEFT));

        campoProduto = new JTextField(25);
        botaoExportar = new JButton("Exportar");

        painelControle.add(new JLabel("Produto:"));
        painelControle.add(campoProduto);
        painelControle.add(botaoExportar);

        painelExibicao = new JPanel(new BorderLayout());

        String[] colunas = {
            "Produto",
            "Categoria",
            "Espécie",
            "Quantidade Atual"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaEstoque = new JTable(modeloTabela);
        tabelaEstoque.setRowHeight(22);

        scrollTabela = new JScrollPane(tabelaEstoque);
        painelExibicao.add(scrollTabela);
        
        janela.add(painelControle, BorderLayout.NORTH);
        janela.add(painelExibicao, BorderLayout.CENTER);
        
        janela.setVisible(true);
        return janela;
    }
}
