package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaCadastroAssociado;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaCadastroVenda;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaGerarNotaFiscalVenda;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase.Tema;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

// Estende JInternalFrame E implementa a navegação do seu módulo
public abstract class ViewBase extends JInternalFrame implements NavegadorNotaFiscal {

    private JDesktopPane desktopPane; // Referência para abrir novas janelas
    private Cabecalho cabecalho;

    public ViewBase(String titulo, JDesktopPane desktopPane) {
        super("Módulo Nota Fiscal", true, true, true, true);
        this.desktopPane = desktopPane;
        setSize(1024, 768);
        setLayout(new BorderLayout());

        // --- Montagem do Layout Estilo Gustavo ---

        // 1. Menu Lateral
        add(new MenuLateralNotaFiscal(this), BorderLayout.WEST);

        // 2. Área Central (Cabeçalho + Conteúdo)
        JPanel painelDireito = new JPanel(new BorderLayout());

        // 2.1 Cabeçalho
        this.cabecalho = new Cabecalho(titulo);
        painelDireito.add(cabecalho, BorderLayout.NORTH);

        // 2.2 Conteúdo da Tela Filha
        JPanel containerConteudo = new JPanel(new BorderLayout());
        containerConteudo.setBackground(Tema.COR_FUNDO);
        containerConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Chama o método abstrato que suas telas vão implementar
        containerConteudo.add(getPainelConteudo(), BorderLayout.CENTER);

        painelDireito.add(containerConteudo, BorderLayout.CENTER);
        add(painelDireito, BorderLayout.CENTER);
    }

    // As telas filhas DEVEM implementar isso em vez de usar construtor
    protected abstract JPanel getPainelConteudo();

    // --- Implementação da Navegação ---

    private void trocarTela(JInternalFrame novaTela) {
        // Se quiser fechar a atual ao abrir a próxima (comportamento SPA)
        this.dispose();
        desktopPane.add(novaTela);
        novaTela.setVisible(true);
        try { novaTela.setSelected(true); } catch (Exception e) {}
    }

    @Override
    public void abrirCadastrarAssociado() {
        if (!(this instanceof TelaCadastroAssociado))
            trocarTela(new TelaCadastroAssociado(desktopPane));
    }

    @Override
    public void abrirCadastrarVenda() {
        if (!(this instanceof TelaCadastroVenda))
            trocarTela(new TelaCadastroVenda(desktopPane));
    }

    @Override
    public void abrirGerarNFe() {
        if (!(this instanceof TelaGerarNotaFiscalVenda))
            trocarTela(new TelaGerarNotaFiscalVenda(desktopPane));
    }

    // --- MÉTODOS ÚTEIS (Seus métodos antigos mantidos) ---

    protected GridBagConstraints baseGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    protected void addField(JPanel p, GridBagConstraints gbc, int row, String label, JComponent campo) {
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(Tema.FONTE_TEXTO); // Usa a fonte do tema
        p.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        p.add(campo, gbc);
    }

    protected void addDateField(JPanel p, GridBagConstraints gbc, int row, String label, JDateChooser campo) {
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(Tema.FONTE_TEXTO);
        p.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        p.add(campo, gbc);
    }

    protected void addTopGlue(JPanel p, GridBagConstraints gbc, int row) {
        gbc.gridy = row; gbc.gridx = 0; gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        p.add(Box.createVerticalGlue(), gbc);
    }

    protected String format(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }

    protected double parseDouble(String valor) {
        try { return Double.parseDouble(valor.replace(",", ".")); } catch (Exception e) { return 0.0; }
    }
}