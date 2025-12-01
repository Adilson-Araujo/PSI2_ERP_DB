package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaInventario {
    public static JInternalFrame gerarFrameInterno(){
        JInternalFrame janela = new JInternalFrame();

        JPanel painelControle;
        JPanel painelBotoes;
        JScrollPane scrollTabela;

        JTextField campoProduto;
        JTextField campoArmazem;

        JButton botaoImportar;
        JButton botaoExportar;
        JButton botaoSalvar;

        JTable tabelaInventario;

        janela.setTitle("Visão – Inventário");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        painelControle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        campoProduto = new JTextField(20);
        campoArmazem = new JTextField(15);

        botaoImportar = new JButton("Importar");

        painelControle.add(new JLabel("Produto:"));
        painelControle.add(campoProduto);

        painelControle.add(new JLabel("Armazém:"));
        painelControle.add(campoArmazem);

        painelControle.add(botaoImportar);

        String[] colunas = {
                "Produto",
                "Categoria",
                "Espécie",
                "Armazém",
                "Quantidade"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaInventario = new JTable(modeloTabela);
        tabelaInventario.setRowHeight(22);

        scrollTabela = new JScrollPane(tabelaInventario);

        painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        botaoExportar = new JButton("Exportar");
        botaoSalvar = new JButton("Salvar");

        painelBotoes.add(botaoExportar);
        painelBotoes.add(botaoSalvar);
        
        janela.add(painelControle, BorderLayout.NORTH);
        janela.add(scrollTabela, BorderLayout.CENTER);
        janela.add(painelBotoes, BorderLayout.SOUTH);
        
        janela.setVisible(true);
        return janela;
    }
}
