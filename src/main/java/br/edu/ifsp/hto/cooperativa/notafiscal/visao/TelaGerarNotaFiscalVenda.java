package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.PaletaCores;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.ViewBase;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaGerarNotaFiscalVenda extends ViewBase {

    private JComboBox<String> cbAssociado;
    private JDateChooser dtInicial, dtFinal;
    private br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnBuscar, btnGerarNFE;
    private JTable tabelaVendas;
    private DefaultTableModel modeloTabela;

    public TelaGerarNotaFiscalVenda() {
        super();
        setTitle("Gerar Nota Fiscal a partir de Vendas");
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        setLayout(new BorderLayout());

        // -------- PAINEL ESQUERDO (Filtros) --------
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new GridBagLayout());
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PaletaCores.verdeClaro, 1, true), // Borda verde clara
                "Filtros",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 12), // Fonte
                PaletaCores.verdeEscuro // Cor do texto
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        cbAssociado = new JComboBox<>(new String[]{"Selecione", "João da Silva", "Maria Oliveira"});
        
        dtInicial = new JDateChooser();
        dtInicial.setDateFormatString("dd/MM/yyyy");

        dtFinal = new JDateChooser();
        dtFinal.setDateFormatString("dd/MM/yyyy");

        btnBuscar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Buscar");

        // Layout
        c.gridx = 0; c.gridy = 0;
        painelEsquerdo.add(new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Label("Associado:"), c);
        c.gridx = 1;
        painelEsquerdo.add(cbAssociado, c);

        c.gridx = 0; c.gridy = 1;
        painelEsquerdo.add(new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Label("Data Inicial:"), c);
        c.gridx = 1;
        painelEsquerdo.add(dtInicial, c);

        c.gridx = 0; c.gridy = 2;
        painelEsquerdo.add(new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Label("Data Final:"), c);
        c.gridx = 1;
        painelEsquerdo.add(dtFinal, c);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        painelEsquerdo.add(btnBuscar, c);

        // -------- PAINEL DIREITO (Tabela) --------
        JPanel painelDireito = new JPanel(new BorderLayout());
        painelDireito.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PaletaCores.verdeClaro, 1, true),
                "Listagem de Vendas",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 12),
                PaletaCores.verdeEscuro
        ));

        modeloTabela = new DefaultTableModel(new Object[]{"Venda", "Vendedor Associado", "Valor", "Produto", "Quantidade"}, 0);
        tabelaVendas = new JTable(modeloTabela);

        // Mock de vendas
        modeloTabela.addRow(new Object[]{"Venda 1", "João da Silva", 50.0, "Alface", 100});
        modeloTabela.addRow(new Object[]{"Venda 2", "Maria Oliveira", 120.0, "Tomate", 200});

        JScrollPane scroll = new JScrollPane(tabelaVendas);

        btnGerarNFE = new Button("Gerar NFE");

        painelDireito.add(scroll, BorderLayout.CENTER);
        painelDireito.add(btnGerarNFE, BorderLayout.SOUTH);

        // -------- DIVISÃO PRINCIPAL --------
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);
    }
}
