package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.ViewBase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import com.toedter.calendar.JDateChooser;


public class TelaCadastroVenda extends ViewBase {
    // Identificação
    private JDateChooser txtDataEmissao, txtDataSaida;
    private JTextField txtNumeroNF, txtSerie, txtNatOper;

    // Emitente
    private JTextField txtEmitCnpj, txtEmitRazao, txtEmitFantasia,
            txtEmitUf, txtEmitCidade, txtEmitBairro, txtEmitRua, txtEmitNumero;

    // Destinatário
    private JTextField txtDestCpfCnpj, txtDestRazao,
            txtDestUf, txtDestCidade, txtDestBairro, txtDestRua, txtDestNumero;

    // Produtos
    private JTextField txtProdCodigo, txtProdDescricao, txtProdNcm, txtProdCfop, txtProdQtd, txtProdVlrUnit;
    private JTable tabelaItens;
    private DefaultTableModel modeloItens;

    // Totais
    private JTextField txtBaseICMS, txtVlrICMS, txtBaseICMSST, txtVlrICMSST,
            txtValorProdutos, txtVlrFrete, txtVlrSeguro, txtDesconto, txtVlrIPI, txtValorTotalNF;
    
    
    public TelaCadastroVenda() {
        super();
        setTitle("Cadastro de Nota Fiscal Eletrônica (NF-e)");
        setSize(1000, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Identificação", criarPainelIdentificacao());
        abas.addTab("Emitente", criarPainelEmitente());
        abas.addTab("Destinatário", criarPainelDestinatario());
        abas.addTab("Produtos", criarPainelProdutos()); // restaurado
        abas.addTab("Totais", criarPainelTotais());

        add(abas);
    }

    // ===================== Painéis =====================
    private JPanel criarPainelIdentificacao() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtNumeroNF = new JTextField(12);
        txtSerie = new JTextField("001", 6);
        txtDataEmissao = new JDateChooser();
        txtDataSaida = new JDateChooser();
        txtNatOper = new JTextField(24);

        addField(p, gbc, 0, "Número NF:", txtNumeroNF);
        addField(p, gbc, 1, "Série:", txtSerie);
        addDateField(p, gbc, 2, "Data Emissão:", txtDataEmissao);
        addDateField(p, gbc, 3, "Data Saída:", txtDataSaida);
        addField(p, gbc, 4, "Natureza da Operação:", txtNatOper);
        txtSerie.setText("001");

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnGerar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Gerar Novo");
        gbc.gridx = 2;
        gbc.gridy = 0;
        p.add(btnGerar, gbc);

        addTopGlue(p, gbc, 5);
        return p;
    }

    private JPanel criarPainelEmitente() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtEmitCnpj = new JTextField(18);
        txtEmitRazao = new JTextField(28);
        txtEmitFantasia = new JTextField(24);
        txtEmitUf = new JTextField(2);
        txtEmitCidade = new JTextField(18);
        txtEmitBairro = new JTextField(18);
        txtEmitRua = new JTextField(24);
        txtEmitNumero = new JTextField(6);

        addField(p, gbc, 0, "CNPJ:", txtEmitCnpj);
        addField(p, gbc, 1, "Razão Social:", txtEmitRazao);
        addField(p, gbc, 2, "Nome Fantasia:", txtEmitFantasia);
        addField(p, gbc, 3, "UF:", txtEmitUf);
        addField(p, gbc, 4, "Cidade:", txtEmitCidade);
        addField(p, gbc, 5, "Bairro:", txtEmitBairro);
        addField(p, gbc, 6, "Rua:", txtEmitRua);
        addField(p, gbc, 7, "Número:", txtEmitNumero);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Salvar Emitente");
        gbc.gridx = 0;
        gbc.gridy = 8;
        p.add(btnSalvar, gbc);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Emitente");
        gbc.gridx = 1;
        gbc.gridy = 8;
        p.add(btnImportar, gbc);

        addTopGlue(p, gbc, 8);
        return p;
    }

    private JPanel criarPainelDestinatario() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtDestCpfCnpj = new JTextField(18);
        txtDestRazao = new JTextField(28);
        txtDestUf = new JTextField(2);
        txtDestCidade = new JTextField(18);
        txtDestBairro = new JTextField(18);
        txtDestRua = new JTextField(24);
        txtDestNumero = new JTextField(6);

        addField(p, gbc, 0, "CNPJ/CPF:", txtDestCpfCnpj);
        addField(p, gbc, 1, "Nome/Razão Social:", txtDestRazao);
        addField(p, gbc, 2, "UF:", txtDestUf);
        addField(p, gbc, 3, "Cidade:", txtDestCidade);
        addField(p, gbc, 4, "Bairro:", txtDestBairro);
        addField(p, gbc, 5, "Rua:", txtDestRua);
        addField(p, gbc, 6, "Número:", txtDestNumero);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Salvar Destinatário");
        gbc.gridx = 0;
        gbc.gridy = 7;
        p.add(btnSalvar, gbc);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Destinatário");
        gbc.gridx = 1;
        gbc.gridy = 7;
        p.add(btnImportar, gbc);

        addTopGlue(p, gbc, 7);
        return p;
    }

    private JPanel criarPainelProdutos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseGbc();

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Produto");
        gbc.gridx = 0;
        gbc.gridy = 6;
        form.add(btnImportar, gbc);
        
        btnImportar.addActionListener(e -> {
    TelaSelecionarProdutoImportar tela = new TelaSelecionarProdutoImportar(this);
    tela.setVisible(true);
    if (tela.produtoFoiSelecionado()) {
        modeloItens.addRow(new Object[]{
            "COD" + (modeloItens.getRowCount() + 1),
            tela.getProdutoSelecionado(),
            "", "", // NCM e CFOP podem ser preenchidos depois
            tela.getQuantidade(),
            String.format("%.2f", tela.getValorUnitario()),
            String.format("%.2f", tela.getQuantidade() * tela.getValorUnitario())
        });
        atualizarTotais();
    }
});

        modeloItens = new DefaultTableModel(new Object[]{"Código", "Descrição", "NCM", "CFOP", "Qtd", "Vlr Unit", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return c != 6; // Subtotal não editável
            }
        };
        tabelaItens = new JTable(modeloItens);

        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaItens.getColumnModel().getColumn(4).setCellRenderer(right);
        tabelaItens.getColumnModel().getColumn(5).setCellRenderer(right);
        tabelaItens.getColumnModel().getColumn(6).setCellRenderer(right);

        tabelaItens.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabelaItens.getColumnModel().getColumn(1).setPreferredWidth(280);
        tabelaItens.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabelaItens.getColumnModel().getColumn(3).setPreferredWidth(80);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabelaItens), BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarPainelTotais() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = baseGbc();

        txtBaseICMS = new JTextField("0.00", 12);
        txtVlrICMS = new JTextField("0.00", 12);
        txtBaseICMSST = new JTextField("0.00", 12);
        txtVlrICMSST = new JTextField("0.00", 12);
        txtValorProdutos = new JTextField("0.00", 12);
        txtVlrFrete = new JTextField("0.00", 12);
        txtVlrSeguro = new JTextField("0.00", 12);
        txtDesconto = new JTextField("0.00", 12);
        txtVlrIPI = new JTextField("0.00", 12);
        txtValorTotalNF = new JTextField("0.00", 12);
        txtValorTotalNF.setEditable(false);

        addField(p, gbc, 0, "Base Cálc. ICMS:", txtBaseICMS);
        addField(p, gbc, 1, "Valor ICMS:", txtVlrICMS);
        addField(p, gbc, 2, "Base Cálc. ICMS ST:", txtBaseICMSST);
        addField(p, gbc, 3, "Valor ICMS ST:", txtVlrICMSST);
        addField(p, gbc, 4, "Valor Produtos:", txtValorProdutos);
        addField(p, gbc, 5, "Frete:", txtVlrFrete);
        addField(p, gbc, 6, "Seguro:", txtVlrSeguro);
        addField(p, gbc, 7, "Desconto:", txtDesconto);
        addField(p, gbc, 8, "IPI:", txtVlrIPI);
        addField(p, gbc, 9, "Total NF:", txtValorTotalNF);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new Button("Salvar Nota Fiscal");
        gbc.gridx = 1; gbc.gridy = 10;
        p.add(btnSalvar, gbc);

        addTopGlue(p, gbc, 11);

        // Recalcular totais quando valores mudarem
        addRecalcOnChange(txtVlrFrete, txtVlrSeguro, txtDesconto, txtVlrIPI, txtVlrICMS, txtVlrICMSST);

        btnSalvar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Nota Fiscal salva (simulação)."));
        return p;
    }

    // ===================== Ações =====================
    private void adicionarProduto() {
        try {
            String cod = txtProdCodigo.getText().trim();
            String desc = txtProdDescricao.getText().trim();
            String ncm = txtProdNcm.getText().trim();
            String cfop = txtProdCfop.getText().trim();
            int qtd = Integer.parseInt(txtProdQtd.getText().trim());
            double vlr = Double.parseDouble(txtProdVlrUnit.getText().trim());
            double subtotal = qtd * vlr;

            modeloItens.addRow(new Object[]{cod, desc, ncm, cfop, qtd, format(vlr), format(subtotal)});
            limparCamposProduto();
            atualizarTotais();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar produto: " + ex.getMessage());
        }
    }

    private void removerProduto() {
        int row = tabelaItens.getSelectedRow();
        if (row >= 0) {
            modeloItens.removeRow(row);
            atualizarTotais();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.");
        }
    }

    private void atualizarTotais() {
        double valorProdutos = 0.0;
        for (int i = 0; i < modeloItens.getRowCount(); i++) {
            Object sub = modeloItens.getValueAt(i, 6);
            valorProdutos += parseDouble(String.valueOf(sub));
        }
        txtValorProdutos.setText(format(valorProdutos));

        double frete = parseDouble(txtVlrFrete.getText());
        double seguro = parseDouble(txtVlrSeguro.getText());
        double desconto = parseDouble(txtDesconto.getText());
        double ipi = parseDouble(txtVlrIPI.getText());
        double icms = parseDouble(txtVlrICMS.getText());
        double icmsst = parseDouble(txtVlrICMSST.getText());

        double total = valorProdutos + frete + seguro + ipi + icms + icmsst - desconto;
        txtValorTotalNF.setText(format(total));
    }
    
    private void limparCamposProduto() {
        txtProdCodigo.setText("");
        txtProdDescricao.setText("");
        txtProdNcm.setText("");
        txtProdCfop.setText("");
        txtProdQtd.setText("");
        txtProdVlrUnit.setText("");
        txtProdCodigo.requestFocus();
    }

    private void addRecalcOnChange(JTextField... fields) {
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { atualizarTotais(); }
            public void removeUpdate(DocumentEvent e) { atualizarTotais(); }
            public void changedUpdate(DocumentEvent e) { atualizarTotais(); }
        };
        for (JTextField f : fields) {
            f.getDocument().addDocumentListener(dl);
        }
    }
}

