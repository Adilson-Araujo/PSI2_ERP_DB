package br.edu.ifsp.hto.cooperativa.notafiscal.visao;

import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.AssociadoControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.controlador.ClienteControlador;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.AssociadoTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dto.NotaFiscalEletronicaTO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalEletronicaVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.ViewBase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
import com.toedter.calendar.JDateChooser;


public class TelaCadastroVenda extends ViewBase {
    // Identificação
    private JDateChooser txtDataEmissao, txtDataSaida;
    private JTextField txtNumeroNF, txtSerie, txtNatOper;

    // Emitente
    private JTextField txtEmitCnpj, txtEmitRazao, txtEmitFantasia,
            txtEmitUf, txtEmitCidade, txtEmitBairro, txtEmitRua, txtEmitNumero, txtEmitCep;

    // Destinatário
    private JTextField txtDestCpfCnpj, txtDestRazao,
            txtDestUf, txtDestCidade, txtDestBairro, txtDestRua, txtDestNumero, txtDestCep;

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
        txtEmitCep = new JTextField(8);

        addField(p, gbc, 0, "CNPJ:", txtEmitCnpj);
        addField(p, gbc, 1, "Razão Social:", txtEmitRazao);
        addField(p, gbc, 2, "Nome Fantasia:", txtEmitFantasia);
        addField(p, gbc, 3, "UF:", txtEmitUf);
        addField(p, gbc, 4, "Cidade:", txtEmitCidade);
        addField(p, gbc, 5, "Bairro:", txtEmitBairro);
        addField(p, gbc, 6, "Rua:", txtEmitRua);
        addField(p, gbc, 7, "Número:", txtEmitNumero);
        addField(p, gbc, 8, "CEP", txtEmitCep);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Salvar Emitente");
        gbc.gridx = 0;
        gbc.gridy = 9;
        p.add(btnSalvar, gbc);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportarEmitente = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Emitente");
        gbc.gridx = 1;
        gbc.gridy = 9;
        p.add(btnImportarEmitente, gbc);
        btnImportarEmitente.addActionListener(e -> btnImportarEmitente_click());
        addTopGlue(p, gbc, 8);
        return p;
    }

    private void btnImportarEmitente_click() {
        try {
            AssociadoTO associado = null;
            var sessaoControlador = new SessaoControlador();
            var usuarioLogado = sessaoControlador.obterUsuarioLogado();
            if (usuarioLogado == null){
                if (txtEmitCnpj.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o CNPJ do emitente", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                var associadoControlador = new AssociadoControlador();
                associado = associadoControlador.obter(txtEmitCnpj.getText());
                if (associado == null)
                {
                    JOptionPane.showMessageDialog(this, "Emitente com cnpj: " + txtEmitCnpj.getText() + " não está cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            else {
                associado = usuarioLogado.associadoTO;
            }
            txtEmitCnpj.setText(associado.associado.getCnpj());
            txtEmitFantasia.setText(associado.associado.getNomeFantasia());
            txtEmitRazao.setText(associado.associado.getRazaoSocial());
            txtEmitUf.setText(associado.endereco.getEstado());
            txtEmitCep.setText(associado.endereco.getCep());
            txtEmitBairro.setText(associado.endereco.getBairro());
            txtEmitCidade.setText(associado.endereco.getCidade());
            txtEmitRua.setText(associado.endereco.getRua());
            txtEmitNumero.setText(associado.endereco.getNumero().toString());
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        txtDestCep = new JTextField(8);

        addField(p, gbc, 0, "CNPJ/CPF:", txtDestCpfCnpj);
        addField(p, gbc, 1, "Nome/Razão Social:", txtDestRazao);
        addField(p, gbc, 2, "UF:", txtDestUf);
        addField(p, gbc, 3, "Cidade:", txtDestCidade);
        addField(p, gbc, 4, "Bairro:", txtDestBairro);
        addField(p, gbc, 5, "Rua:", txtDestRua);
        addField(p, gbc, 6, "Número:", txtDestNumero);
        addField(p, gbc, 7, "CEP", txtDestCep);


        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnSalvar = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Salvar Destinatário");
        gbc.gridx = 0;
        gbc.gridy = 8;
        p.add(btnSalvar, gbc);

        br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button btnImportarDestinatario = new br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Button("Importar Destinatário");
        gbc.gridx = 1;
        gbc.gridy = 8;
        p.add(btnImportarDestinatario, gbc);

        btnImportarDestinatario.addActionListener(e -> btnImportarDestinatario_click());
        addTopGlue(p, gbc, 7);
        return p;
    }

    private void btnImportarDestinatario_click() {
        try{
            if (txtDestCpfCnpj.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Digite o CPF / CNPJ do destinatário", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            var clienteControlador = new ClienteControlador();
            var cliente = clienteControlador.obter(txtDestCpfCnpj.getText());
            if (cliente == null)
            {
                JOptionPane.showMessageDialog(this, "Destinatário / cliente com cpf ou cnpj: " + txtEmitCnpj.getText() + " não está cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            txtDestBairro.setText(cliente.endereco.getBairro());
            txtDestCep.setText(cliente.endereco.getCep());
            txtDestRua.setText(cliente.endereco.getRua());
            txtDestCidade.setText(cliente.endereco.getCidade());
            txtDestNumero.setText(cliente.endereco.getNumero().toString());
            txtDestUf.setText(cliente.endereco.getEstado());
            txtDestRazao.setText(cliente.cliente.getRazaoSocial());
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

        btnSalvar.addActionListener(e -> salvarNfe());
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

    private void salvarNfe(){
        var associadoControlador = new AssociadoControlador();
        var clienteControlador = new ClienteControlador();
        var notaFiscalEletronicaTO = new NotaFiscalEletronicaTO();
        notaFiscalEletronicaTO.notaFiscalEletronica = new NotaFiscalEletronicaVO();
        notaFiscalEletronicaTO.notaFiscalItens = new ArrayList<>();
        notaFiscalEletronicaTO.associadoTO = associadoControlador.obter(txtEmitCnpj.getText());
        if (notaFiscalEletronicaTO.associadoTO == null)
        {
            var associadoTO = new AssociadoTO();
            associadoTO.associado = new AssociadoVO();
            associadoTO.associado.setCnpj(txtEmitCnpj.getText());
            associadoTO.associado.setNomeFantasia(txtEmitFantasia.getText());
            associadoTO.associado.setRazaoSocial(txtEmitRazao.getText());
            associadoTO.associado.setAtivo(true);
            var enderecoEmit = new EnderecoVO("SP", txtEmitCidade.getText(), txtEmitBairro.getText(), txtEmitRua.getText(), Integer.parseInt(txtEmitNumero.getText()), txtDestCep.getText());
            associadoTO.endereco = enderecoEmit;
        }
        notaFiscalEletronicaTO.clienteTO = clienteControlador.obter(txtDestCpfCnpj.getText());
    }
}

