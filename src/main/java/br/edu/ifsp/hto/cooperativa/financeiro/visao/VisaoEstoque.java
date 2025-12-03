package br.edu.ifsp.hto.cooperativa.financeiro.visao;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EstoqueAtualDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.PrecoPPADAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.EstoqueTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EstoqueAtualVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPAVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * VisaoEstoque — agora um JInternalFrame
 *
 * Para abrir a partir do menu:
 *   new VisaoEstoque(desktop);
 */
public class VisaoEstoque extends JInternalFrame {

    // DAOs
    private final EstoqueAtualDAO estoqueDAO = EstoqueAtualDAO.getInstance();
    private final ArmazemDAO armazemDAO = ArmazemDAO.getInstance();
    private final PrecoPPADAO precoDAO = PrecoPPADAO.getInstance();

    // Dados da view
    private List<ArmazemResumo> resumoArmazens = new ArrayList<>();

    // Componentes UI
    private JPanel painelGraficos;
    private JPanel painelListaContainer;

    public VisaoEstoque(JDesktopPane desktop) {
        super("Visão de Estoque", true, true, true, true);
        setSize(1050, 650);
        setLocation(20, 20);

        // Adiciona o InternalFrame no DesktopPane
        desktop.add(this);
        setVisible(true);

        // Layout principal
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Visão do Estoque", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(new EmptyBorder(18, 0, 18, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel central = new JPanel(new BorderLayout());
        central.setBackground(Color.WHITE);
        central.setBorder(new EmptyBorder(10, 12, 10, 12));
        add(central, BorderLayout.CENTER);

        painelGraficos = new JPanel();
        painelGraficos.setLayout(new BoxLayout(painelGraficos, BoxLayout.Y_AXIS));
        painelGraficos.setBackground(Color.WHITE);

        painelListaContainer = new JPanel(new BorderLayout());
        painelListaContainer.setBackground(Color.WHITE);

        // Carrega dados + constrói UI
        carregarDadosEConstruirTela();

        // Adiciona à tela
        central.add(painelGraficos, BorderLayout.WEST);
        central.add(painelListaContainer, BorderLayout.CENTER);
    }


    // ==============================
    // LÓGICA ORIGINAL MANTIDA
    // ==============================

    private void carregarDadosEConstruirTela() {
        List<EstoqueTO> todos = estoqueDAO.listarEstoque(1);

        Map<Integer, ArmazemResumo> mapa = new LinkedHashMap<>();

        for (EstoqueTO ea : todos) {
            if (ea == null) continue;

            // ArmazemVO arm = ea.getArmazem();
            ProdutoVO produto = ea.getProduto();
            float quantidadeKg = ea.getQuantidade();

            // if (arm == null || produto == null) continue;

            // int armazemId = arm.getId();
            // String nome = arm.getNome();
            int especieId = produto.getEspecie() != null ? produto.getEspecie().getId() : -1;

            PrecoPPAVO preco = null;
            try {
                if (especieId > 0)
                    preco = precoDAO.buscarPorId(null ,especieId);
            } catch (Exception ignored) {
            }

            double valorKg = preco != null ? preco.getValor() : 0.0;
            double valorRegistro = quantidadeKg * valorKg;

            // ArmazemResumo atual = mapa.get(armazemId);
            // if (atual == null) {
            //     atual = new ArmazemResumo(armazemId, nome, quantidadeKg, valorRegistro);
            //     mapa.put(armazemId, atual);
            // } else {
            //     atual.totalKg += quantidadeKg;
            //     atual.totalValor += valorRegistro;
            // }
        }

        resumoArmazens = new ArrayList<>(mapa.values());
        resumoArmazens.sort(Comparator.comparing(r -> r.nome.toLowerCase()));

        DefaultPieDataset datasetVolume = new DefaultPieDataset();
        DefaultPieDataset datasetValor = new DefaultPieDataset();

        double totalVolume = 0.0;
        double totalValor = 0.0;

        for (ArmazemResumo r : resumoArmazens) {
            if (r.totalKg > 0) {
                datasetVolume.setValue(r.nome, r.totalKg);
                totalVolume += r.totalKg;
            }
            if (r.totalValor > 0) {
                datasetValor.setValue(r.nome, r.totalValor);
                totalValor += r.totalValor;
            }
        }

        painelGraficos.removeAll();
        painelGraficos.add(criarBlocoGraficoComCentro("Volume", datasetVolume, formatKg(totalVolume)));
        painelGraficos.add(Box.createVerticalStrut(18));
        painelGraficos.add(criarBlocoGraficoComCentro("Valor", datasetValor, formatCurrency(totalValor)));

        painelListaContainer.removeAll();
        painelListaContainer.add(criarPainelLista(), BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JPanel criarBlocoGraficoComCentro(String titulo, DefaultPieDataset dataset, String centroTexto) {
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBackground(Color.WHITE);

        JFreeChart chart = ChartFactory.createPieChart("", dataset, false, false, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelGenerator(null);

        @SuppressWarnings("unchecked")
        List<Comparable> keys = dataset.getKeys();
        for (Comparable key : keys) {
            plot.setSectionPaint(key.toString(), corPorNome(key.toString()));
        }

        plot.setInteriorGap(0);
        plot.setCircular(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 220));
        chartPanel.setOpaque(false);

        JPanel wrapper = new JPanel(null);
        wrapper.setPreferredSize(new Dimension(300, 220));
        wrapper.setOpaque(false);

        chartPanel.setBounds(0, 0, 300, 220);
        wrapper.add(chartPanel);

        JLabel lblCentro = new JLabel(centroTexto, SwingConstants.CENTER);
        lblCentro.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblCentro.setSize(200, 30);

        int cx = (wrapper.getPreferredSize().width - lblCentro.getWidth()) / 2;
        int cy = (wrapper.getPreferredSize().height - lblCentro.getHeight()) / 2 - 8;
        lblCentro.setLocation(cx, cy);

        wrapper.add(lblCentro);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTitulo.setBorder(new EmptyBorder(8, 0, 0, 0));

        bloco.add(wrapper);
        bloco.add(lblTitulo);

        return bloco;
    }

    private JScrollPane criarPainelLista() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(6, 6, 6, 6));

        if (resumoArmazens.isEmpty()) {
            JLabel vazia = new JLabel("Nenhum registro de estoque encontrado.");
            vazia.setFont(new Font("SansSerif", Font.PLAIN, 14));
            vazia.setBorder(new EmptyBorder(8, 8, 8, 8));
            painel.add(vazia);
        } else {
            for (ArmazemResumo r : resumoArmazens) {
                painel.add(criarItemLista(r));
                painel.add(Box.createVerticalStrut(12));
            }
        }

        JScrollPane scroll = new JScrollPane(painel);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scroll.setPreferredSize(new Dimension(380, 500));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scroll;
    }

    private JPanel criarItemLista(ArmazemResumo r) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(Color.WHITE);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        Color cor = corPorNome(r.nome);

        JPanel bolinha = new JPanel();
        bolinha.setPreferredSize(new Dimension(12, 12));
        bolinha.setBackground(cor);
        bolinha.setOpaque(true);
        bolinha.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 1, true));

        JPanel painelWest = new JPanel(new BorderLayout());
        painelWest.setBackground(Color.WHITE);
        painelWest.add(bolinha, BorderLayout.NORTH);
        painelWest.setBorder(new EmptyBorder(0, 6, 0, 8));

        JLabel lblNome = new JLabel(r.nome);
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);
        centro.add(lblNome);

        JLabel lblQtd = new JLabel(formatKg(r.totalKg));
        lblQtd.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel lblValor = new JLabel(formatCurrency(r.totalValor));
        lblValor.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel painelValores = new JPanel();
        painelValores.setLayout(new BoxLayout(painelValores, BoxLayout.Y_AXIS));
        painelValores.setBackground(Color.WHITE);
        painelValores.add(lblQtd);
        painelValores.add(lblValor);
        painelValores.setBorder(new EmptyBorder(4, 6, 4, 6));

        item.add(painelWest, BorderLayout.WEST);
        item.add(centro, BorderLayout.CENTER);
        item.add(painelValores, BorderLayout.EAST);

        return item;
    }

    private Color corPorNome(String nome) {
        int hash = nome == null ? 0 : nome.hashCode();
        int hueDeg = Math.abs(hash) % 360;
        float hue = hueDeg / 360f;
        return Color.getHSBColor(hue, 0.6f, 0.75f);
    }

    private String formatKg(double v) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return df.format(v) + " kg";
    }

    private String formatCurrency(double v) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "R$ " + df.format(v);
    }

    private static class ArmazemResumo {
        final int id;
        final String nome;
        double totalKg;
        double totalValor;

        ArmazemResumo(int id, String nome, double kg, double valor) {
            this.id = id;
            this.nome = nome;
            this.totalKg = kg;
            this.totalValor = valor;
        }
    }
}
