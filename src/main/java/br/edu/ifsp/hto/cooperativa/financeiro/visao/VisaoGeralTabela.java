package br.edu.ifsp.hto.cooperativa.financeiro.visao;


import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import br.edu.ifsp.hto.cooperativa.financeiro.controle.ControleDespesa;
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.VendaDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.VendaVO;

public class VisaoGeralTabela extends JInternalFrame {

    private JDateChooser dataInicioChooser;
    private JDateChooser dataFimChooser;
    private DefaultTableModel modeloOriginal;
    private JTable tabela;
    private DefaultTableCellRenderer rendererValores;

    public VisaoGeralTabela(JDesktopPane desktop) {
        // CONFIGURAÇÕES DO INTERNAL FRAME
        super("Relatório Financeiro", true, true, true, true);
        setSize(900, 700);
        setVisible(true);

        // Adiciona ao Desktop
        desktop.add(this);
        this.toFront();

        // ======================================
        //    CARREGAMENTO DE DADOS (OTIMIZADO)
        // ======================================
        VendaDAO vendaDAO = new VendaDAO();
        List<VendaVO> vendas = vendaDAO.obterTodos();

        List<VendaVO> vendasDoAssociado = new ArrayList<>();
        double totalReceita = 0d;

        for (VendaVO v : vendas) {
            if (v.getAssociadoId() == 1) {
                vendasDoAssociado.add(v);
                totalReceita += v.getValorTotal().doubleValue();
            }
        }

        List<DespesaVO> despesas = ControleDespesa
                .getInstance()
                .buscarDespesaPorIdDeAssociado(1);

        double totalDespesas = despesas.stream()
                .mapToDouble(DespesaVO::buscaValor_gasto)
                .sum();

        // ======================================
        //              INTERFACE
        // ======================================
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 30, 20, 30));
        setContentPane(root);

        root.add(criarTitulo(), BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);

        centro.add(criarPainelFiltros());
        centro.add(criarPainelValores(totalDespesas, totalReceita));
        centro.add(new JPanel()); 

        centro.add(criarTabela(vendasDoAssociado, despesas));

        root.add(centro, BorderLayout.CENTER);
    }


    // ======================================
    //               TÍTULO
    // ======================================
    private JPanel criarTitulo() {
        JLabel titulo = new JLabel("Relatório Financeiro");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(new Color(60, 60, 60));
        titulo.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        painel.add(titulo, BorderLayout.CENTER);
        return painel;
    }


    // ======================================
    //          PAINEL DE FILTROS
    // ======================================
    private JPanel criarPainelFiltros() {

        JPanel painelFiltros = new JPanel();
        painelFiltros.setLayout(new GridLayout(1, 3, 20, 10));
        painelFiltros.setBackground(Color.WHITE);
        painelFiltros.setBorder(new EmptyBorder(10, 0, 20, 0));

        // Data início
        dataInicioChooser = new JDateChooser();
        JPanel boxInicio = criarCampoData("Data de Início", dataInicioChooser);

        // Data fim
        dataFimChooser = new JDateChooser();
        JPanel boxFim = criarCampoData("Data de Fim", dataFimChooser);

        painelFiltros.add(boxInicio);
        painelFiltros.add(boxFim);

        //BOTÃO FILTRAR
        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.setPreferredSize(new Dimension(120, 37));
        botaoFiltrar.setBackground(new Color(70, 130, 180));
        botaoFiltrar.setForeground(Color.WHITE);
        botaoFiltrar.setFocusPainted(false);
        botaoFiltrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoFiltrar.addActionListener(e -> filtrarTabela());

        JPanel boxBotao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        boxBotao.setBackground(Color.WHITE);
        boxBotao.setBorder(new EmptyBorder(10, 0, 0, 0));
        boxBotao.add(botaoFiltrar);

        //BOTÃO RESET
        JButton botaoReset = new JButton("Mostrar Todos");
        botaoReset.setPreferredSize(new Dimension(150, 37));
        botaoReset.setBackground(new Color(70, 130, 180));
        botaoReset.setForeground(Color.WHITE);
        botaoReset.setFocusPainted(false);
        botaoReset.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoReset.addActionListener(e -> resetarTabela());

        JPanel boxReset = new JPanel(new FlowLayout(FlowLayout.CENTER));
        boxReset.setBackground(Color.WHITE);
        boxReset.setBorder(new EmptyBorder(10, 0, 0, 0));
        boxReset.add(botaoReset);

        painelFiltros.add(boxBotao);
        painelFiltros.add(boxReset);

        return painelFiltros;
    }

    private void resetarTabela() {
        tabela.setModel(modeloOriginal);
        tabela.setRowSorter(new TableRowSorter<>(modeloOriginal));
        aplicarRenderer();
    }

    private JPanel criarCampoData(String titulo, JDateChooser chooser) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(titulo);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setBorder(new EmptyBorder(0, 0, 5, 0));

        chooser.setDateFormatString("dd/MM/yyyy");
        chooser.setPreferredSize(new Dimension(200, 35));

        panel.add(label, BorderLayout.NORTH);
        panel.add(chooser, BorderLayout.CENTER);

        return panel;
    }


    // ======================================
    //           PAINEL DE VALORES
    // ======================================
    private JPanel criarPainelValores(double despesas, double receitaBruta) {

        double lucro = receitaBruta - despesas;

        JPanel painel = new JPanel(new GridLayout(1, 3, 20, 0));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(0, 0, 20, 0));

        painel.add(criarCard("Custo Total", "R$ " + despesas));
        painel.add(criarCard("Receita Total", "R$ " + receitaBruta));
        painel.add(criarCard("Lucro Total", "R$ " + lucro));

        return painel;
    }

    private JPanel criarCard(String titulo, String valor) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lt = new JLabel(titulo);
        lt.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel lv = new JLabel(valor);
        lv.setFont(new Font("SansSerif", Font.BOLD, 18));
        lv.setBorder(new EmptyBorder(5, 0, 0, 0));

        card.add(lt, BorderLayout.NORTH);
        card.add(lv, BorderLayout.CENTER);

        return card;

    }


    // ======================================
    //               TABELA
    // ======================================
    private JScrollPane criarTabela(List<VendaVO> vendas, List<DespesaVO> despesas) {

        // Modelo único
        modeloOriginal = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Data da Transação", "ValorTotal"},
                0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> Integer.class;
                    case 3 -> Double.class;
                    default -> String.class;
                };
            }
        };

        for (VendaVO v : vendas) {
            modeloOriginal.addRow(new Object[]{
                    v.getId(),
                    v.getAssociadoId(),
                    v.getDataCompra(),
                    v.getValorTotal().doubleValue()
            });
        }

        for (DespesaVO d : despesas) {
            modeloOriginal.addRow(new Object[]{
                    d.buscaId(),
                    d.buscaAssociado_id(),
                    d.buscaData_transacao(),
                    -d.buscaValor_gasto()
            });
        }

        tabela = new JTable(modeloOriginal);
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 12));

        
        rendererValores = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean sel, boolean foc, int linha, int coluna){
                Component c = super.getTableCellRendererComponent(tabela, valor, sel, foc, linha, coluna);

                setHorizontalAlignment(SwingConstants.CENTER);

                if(coluna == 3){
                    double v = ((Number) valor).doubleValue();
                    c.setForeground(v > 0 ? new Color(0, 120, 0) : Color.RED);
                }else{
                    c.setForeground(Color.black);
                }

                c.setBackground(sel ? new Color(220, 230, 255) : Color.white);
                return c;
            }
        };

        aplicarRenderer();

        tabela.setRowSorter(new TableRowSorter<>(modeloOriginal));
        return new JScrollPane(tabela);
    }

    private void aplicarRenderer(){
        for(int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(rendererValores);
        }
    }


    // ======================================================
    //                      FILTRO
    // ======================================================
    private void filtrarTabela() {

        java.util.Date inicio = dataInicioChooser.getDate();
        java.util.Date fim = dataFimChooser.getDate();

        if (inicio == null || fim == null) {
            JOptionPane.showMessageDialog(this, "Selecione as duas datas para filtrar.",
                    "Filtro inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long tInicio = inicio.getTime();
        long tFim = fim.getTime();

        DefaultTableModel modeloFiltrado = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Data da Transação", "Valor Total"}, 0
        );

        for (int i = 0; i < modeloOriginal.getRowCount(); i++) {

            Object dataObj = modeloOriginal.getValueAt(i, 2);
            java.util.Date data = parseData(dataObj);

            if (data == null) continue;

            long tData = data.getTime();

            if (tData >= tInicio && tData <= tFim) {
                modeloFiltrado.addRow(new Object[]{
                        modeloOriginal.getValueAt(i, 0),
                        modeloOriginal.getValueAt(i, 1),
                        modeloOriginal.getValueAt(i, 2),
                        modeloOriginal.getValueAt(i, 3)
                });
            }
        }

        tabela.setModel(modeloFiltrado);
        tabela.setRowSorter(new TableRowSorter<>(modeloFiltrado));
        aplicarRenderer();
    }

    private java.util.Date parseData(Object valor) {
        try {
            if (valor instanceof java.util.Date) {
                return (java.util.Date) valor;
            }

            String s = valor.toString();

            if (s.contains("T"))
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(s);

            if (s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);

            if (s.matches("\\d{4}-\\d{2}-\\d{2}"))
                return new SimpleDateFormat("yyyy-MM-dd").parse(s);

            if (s.matches("\\d{2}/\\d{2}/\\d{4}"))
                return new SimpleDateFormat("dd/MM/yyyy").parse(s);

            return null;

        } catch (Exception e) {
            return null;
        }
    }

}
