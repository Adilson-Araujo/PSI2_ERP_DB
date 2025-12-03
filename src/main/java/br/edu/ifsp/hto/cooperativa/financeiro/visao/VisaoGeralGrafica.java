package br.edu.ifsp.hto.cooperativa.financeiro.visao;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.VendaDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.VendaVO;

import br.edu.ifsp.hto.cooperativa.financeiro.modelo.dao.DespesaDAO;
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class VisaoGeralGrafica extends JInternalFrame {
 
    public VisaoGeralGrafica(JDesktopPane desktop) {
        super("Visão Geral Financeira", true, true, true, true);
        
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        setSize(900, 600);
        setVisible(true);

        desktop.add(this);

        // ===== PAINEL DE TÍTULO =====
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(new Color(55, 61, 13));
        painelTitulo.setBorder(new EmptyBorder(15, 10, 15, 10));

        JLabel titulo = new JLabel("Visão Geral Financeira (Receitas x Despesas)");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelTitulo.add(titulo, BorderLayout.CENTER);

        add(painelTitulo, BorderLayout.NORTH);

        // ====== BUSCAR DADOS DO BANCO ======
        DefaultCategoryDataset dataset = carregarDadosFinanceiros();

        // ===== CRIAÇÃO DO GRÁFICO =====
        JFreeChart grafico = ChartFactory.createLineChart(
                "Receitas x Despesas",
                "Mês",
                "Valor (R$)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        grafico.setBackgroundPaint(Color.WHITE);
        grafico.getTitle().setPaint(new Color(55, 61, 13));
        grafico.getTitle().setFont(new Font("SansSerif", Font.BOLD, 15));

        CategoryPlot plot = grafico.getCategoryPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        // ===== PAINEL DO GRÁFICO =====
        ChartPanel painelGrafico = new ChartPanel(grafico);
        painelGrafico.setPreferredSize(new Dimension(800, 400));
        painelGrafico.setBorder(new EmptyBorder(20, 0, 20, 0));
        painelGrafico.setBackground(Color.WHITE);

        add(painelGrafico, BorderLayout.CENTER);
    }

    // ========================================================
    // ======= FUNÇÃO PARA CARREGAR RECEITAS/DESPESAS =========
    // ========================================================
    private DefaultCategoryDataset carregarDadosFinanceiros() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Mapa para acumular valores por mês
        HashMap<Integer, Double> receitaMes = new HashMap<>();
        HashMap<Integer, Double> despesaMes = new HashMap<>();

        // ====== CARREGAR VENDAS (RECEITA) ======
        VendaDAO vendaDAO = new VendaDAO();
        List<VendaVO> vendas = vendaDAO.obterTodos(); //aqui antes era List

        for (VendaVO v : vendas) {
            if (v.getDataCompra() == null || v.getValorTotal() == null) continue;

            int mes = v.getDataCompra().getMonthValue();
            double valor = v.getValorTotal().doubleValue();

            receitaMes.put(mes, receitaMes.getOrDefault(mes, 0.0) + valor);
        }

        // ====== CARREGAR DESPESAS ======
        DespesaDAO despesaDAO = DespesaDAO.getInstance();
        ArrayList<DespesaVO> despesas = despesaDAO.buscarTodasDespesas();

        for (DespesaVO d : despesas) {
            try {
                LocalDateTime data = LocalDateTime.parse(d.buscaData_transacao().replace(" ", "T"));
                double valor = d.buscaValor_gasto();

                int mes = data.getMonthValue();
                despesaMes.put(mes, despesaMes.getOrDefault(mes, 0.0) + valor);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ===== ADD NO DATASET (JAN-DEZ) =====
        String[] nomesMeses = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};

        for (int mes = 1; mes <= 12; mes++) {
            dataset.addValue(receitaMes.getOrDefault(mes, 0.0), "Receita (Vendas)", nomesMeses[mes - 1]);
            dataset.addValue(despesaMes.getOrDefault(mes, 0.0), "Despesa", nomesMeses[mes - 1]);
        }

        return dataset;
    }
}
