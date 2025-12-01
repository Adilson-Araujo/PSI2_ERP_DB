package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.ViewBase;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
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


        setClosable(true);
        setResizable(true);

        // Painel principal
        setLayout(new BorderLayout());


        JPanel painelEsquerdo = new JPanel();
        JPanel painelDireito = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);
    }
}
