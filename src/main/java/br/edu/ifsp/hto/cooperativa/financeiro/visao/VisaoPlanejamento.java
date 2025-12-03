package br.edu.ifsp.hto.cooperativa.financeiro.visao;

import java.awt.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;

import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.PrecoPPADAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPAVO;

import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO;

public class VisaoPlanejamento extends JInternalFrame {

    private JLabel lblPeriodo;
    private JLabel lblValorTotal;
    private JPanel painelLista;
    
    private JPanel painelFiltroDatas;
    private JDateChooser txtDataInicio;
    private JDateChooser txtDataFim;
    private JButton btnFiltrar;

    private DefaultPieDataset dataset;
    private ChartPanel chartPanel; 

    private final PlanejamentoControle planejamentoControle = new PlanejamentoControle();

    // Cores padrão do sistema (mantidas para uso no título/botões)
    Color verdeEscuro = new Color(55, 61, 13);
    Color verdeOliva = new Color(139, 143, 61);

    // ==================================================================================
    // NOVA PALETA DE CORES PARA O GRÁFICO E LISTA
    // ==================================================================================
    private final Color[] coresGrafico = {
        new Color(139, 143, 61), // Verde Oliva (Padrão)
        new Color(70, 130, 180), // Steel Blue
        new Color(220, 50, 50),  // Tom de Vermelho
        new Color(255, 165, 0),  // Laranja
        new Color(106, 90, 205), // Slate Blue
        new Color(60, 179, 113), // Medium Sea Green
        new Color(218, 165, 32), // Goldenrod
        new Color(199, 21, 133)  // Medium Violet Red
    };

    public VisaoPlanejamento(JDesktopPane desktop) {

        super("Planejamentos", true, true, true, true);
        setSize(1000, 700);
        setVisible(true);
        desktop.add(this);
        this.toFront();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        ((JComponent)getContentPane()).setBorder(new EmptyBorder(20, 40, 20, 40));

        // ====================================
        //  PAINEL TÍTULO
        // ====================================
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(verdeEscuro);
        painelTitulo.setBorder(new EmptyBorder(15, 10, 15, 10));

        JLabel titulo = new JLabel("Planejamentos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);

        // =====================================================================
        // PAINEL CENTRAL
        // =====================================================================
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);
        add(painelCentral, BorderLayout.CENTER);

        // =====================================================================
        // BOTÕES GERAL / ESPECÍFICO
        // =====================================================================
        JPanel painelModo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        painelModo.setBackground(Color.WHITE);

        JButton btnGeral = new JButton("Geral");
        JButton btnEspec = new JButton("Específico");

        estilizarBotaoModo(btnGeral);
        estilizarBotaoModo(btnEspec);

        btnGeral.setBackground(verdeOliva);
        btnGeral.setForeground(Color.WHITE);

        painelModo.add(btnGeral);
        painelModo.add(btnEspec);

        lblPeriodo = new JLabel("Selecione o período desejado");
        lblPeriodo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPeriodo.setForeground(Color.GRAY);
        lblPeriodo.setVisible(false);

        JPanel painelTopoDireito = new JPanel(new BorderLayout());
        painelTopoDireito.setBackground(Color.WHITE);
        painelTopoDireito.add(painelModo, BorderLayout.WEST);
        painelTopoDireito.add(lblPeriodo, BorderLayout.SOUTH);

        painelCentral.add(painelTopoDireito, BorderLayout.NORTH);

        // =====================================================================
        // PAINEL FILTRO DE DATAS (ESPECÍFICO)
        // =====================================================================
        painelFiltroDatas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelFiltroDatas.setBackground(Color.WHITE);

        txtDataInicio = new JDateChooser();
        txtDataInicio.setPreferredSize(new Dimension(130, 30));

        txtDataFim = new JDateChooser();
        txtDataFim.setPreferredSize(new Dimension(130, 30));

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBackground(new Color(70, 130, 180));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setPreferredSize(new Dimension(100, 30));

        painelFiltroDatas.add(new JLabel("Inicio:"));
        painelFiltroDatas.add(txtDataInicio);
        painelFiltroDatas.add(new JLabel("Fim:"));
        painelFiltroDatas.add(txtDataFim);
        painelFiltroDatas.add(btnFiltrar);
        painelFiltroDatas.setVisible(false);

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(Color.WHITE);
        painelTopo.add(painelTopoDireito);
        painelTopo.add(painelFiltroDatas);

        painelCentral.add(painelTopo, BorderLayout.NORTH);

        // Eventos dos botões
        btnGeral.addActionListener(e -> {
            btnGeral.setBackground(verdeOliva);
            btnGeral.setForeground(Color.WHITE);

            btnEspec.setBackground(Color.WHITE);
            btnEspec.setForeground(Color.BLACK);

            lblPeriodo.setVisible(false);
            painelFiltroDatas.setVisible(false);

            carregarPlanosDoBanco(null, null);
        });

        btnEspec.addActionListener(e -> {
            btnEspec.setBackground(verdeOliva);
            btnEspec.setForeground(Color.WHITE);

            btnGeral.setBackground(Color.WHITE);
            btnGeral.setForeground(Color.BLACK);

            lblPeriodo.setVisible(true);
            painelFiltroDatas.setVisible(true);

            carregarPlanosDoBanco(null, null);
        });

        btnFiltrar.addActionListener(e -> {
            if(txtDataInicio.getDate() == null || txtDataFim.getDate() == null){
                JOptionPane.showMessageDialog(this, 
                    "Selecione a data de inicio e a data de fim!", 
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date dataIni = new Date(txtDataInicio.getDate().getTime());
            Date dataFim = new Date(txtDataFim.getDate().getTime());

            if(dataFim.before(dataIni)){
                JOptionPane.showMessageDialog(this, 
                    "A data final não pode ser anterior a data inicial",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            carregarPlanosDoBanco(dataIni, dataFim);
        });

        // =====================================================================
        // PAINEL DO GRÁFICO + LISTA (RESPONSIVO)
        // =====================================================================
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4;
        gbc.weighty = 1;

        // JFreeChart setup
        JFreeChart donutChart = criarDonutChart();
        chartPanel = new ChartPanel(donutChart); 
        chartPanel.setOpaque(false);
        chartPanel.setMouseWheelEnabled(false);
        chartPanel.setPopupMenu(null);

        lblValorTotal = new JLabel("R$ 0,00");
        lblValorTotal.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelDonut = new JPanel(new BorderLayout());
        painelDonut.setOpaque(false);
        painelDonut.add(chartPanel, BorderLayout.CENTER);
        painelDonut.add(lblValorTotal, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelConteudo.add(painelDonut, gbc);

        // ============================
        // LISTA LATERAL
        // ============================
        gbc.gridx = 1;
        gbc.weightx = 0.8;

        painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(Color.WHITE);
        painelLista.setBorder(new EmptyBorder(20, 0, 0, 0)); 

        JScrollPane scroll = new JScrollPane(painelLista);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(null); 
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        painelConteudo.add(scroll, gbc);

        painelCentral.add(painelConteudo, BorderLayout.CENTER);

        // Carregar dados iniciais
        carregarPlanosDoBanco(null, null);
    }

    private JFreeChart criarDonutChart() {
        dataset = new DefaultPieDataset();
        dataset.setValue("Vazio", 1);

        JFreeChart chart = ChartFactory.createRingChart(
                "", dataset, false, false, false
        );

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        
        // Configura a seção "Vazio" para ser transparente
        plot.setSectionPaint("Vazio", new Color(0, 0, 0, 0));

        plot.setSectionDepth(0.40);
        plot.setShadowPaint(null);
        plot.setLabelGenerator(null); // Remove rótulos do gráfico

        return chart;
    }

    private void carregarPlanosDoBanco(Date filtroInicio, Date filtroFim) {
        painelLista.removeAll();
        dataset.clear();

        List<PlanoVO> planos = planejamentoControle.listarPlanos();
        
        Map<String, Double> valoresPorPlano = new HashMap<>();
        double totalGeral = 0;

        for (PlanoVO p : planos) {
            if (filtroInicio != null && filtroFim != null) {
                if (p.getDataInicio() == null || p.getDataFim() == null) continue;
                if (p.getDataFim().before(filtroInicio) || p.getDataInicio().after(filtroFim)) continue;
            }

            double valor = calcularValorDoPlano(p);
            valoresPorPlano.merge(p.getNomePlano(), valor, Double::sum);
            totalGeral += valor;
        }

        // Obtemos o plot para definir as cores explicitamente
        RingPlot plot = (RingPlot) chartPanel.getChart().getPlot();
        // Limpa definições de cor anteriores
        plot.clearSectionPaints(false); 

        if (valoresPorPlano.isEmpty()) {
             dataset.setValue("Vazio", 1);
        } else {
             int corIndex = 0;
             for (Map.Entry<String, Double> entry : valoresPorPlano.entrySet()) {
                 String nomePlano = entry.getKey();
                 Double valor = entry.getValue();

                 // 1. Escolhe a próxima cor da paleta (usando módulo para ciclar)
                 Color corDaSecao = coresGrafico[corIndex % coresGrafico.length];

                 // 2. Define no dataset e FORÇA a cor no plot para essa chave
                 dataset.setValue(nomePlano, valor);
                 plot.setSectionPaint(nomePlano, corDaSecao);

                 // 3. Adiciona na lista lateral usando EXATAMENTE a mesma cor
                 addItemLista(nomePlano, valor, corDaSecao);

                 corIndex++;
             }
        }

        NumberFormat nfBr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblValorTotal.setText(nfBr.format(totalGeral));

        painelLista.revalidate();
        painelLista.repaint();
    }

    private double calcularValorDoPlano(PlanoVO plano) {
        ControleEstoque ce = ControleEstoque.getInstance();


        float quantidadeKg = ce.calcularQuantidade(plano.getEspecieId(), plano.getAreaCultivo());
        Timestamp dataInicio = new Timestamp(plano.getDataInicio().getTime());
        
        try {
            var precoTO = ce.buscarPrecos(plano.getEspecieId(), dataInicio);
            if(precoTO != null && precoTO.getPrecoPPA() != null) {
                 return quantidadeKg * precoTO.getPrecoPPA().getValor();
            }
        } catch (Exception e) {
             System.err.println("Erro ao buscar preço para o plano: " + plano.getNomePlano());
        }
        
        return 0;
    }

    private void addItemLista(String nome, Double valor, Color cor) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(new EmptyBorder(0, 0, 15, 0)); 

        // Bolinha colorida (agora usa a cor passada por parâmetro)
        JPanel bolinha = new JPanel();
        bolinha.setPreferredSize(new Dimension(12, 12));
        bolinha.setBackground(cor); // <--- USO DA COR CORRETA
        bolinha.setOpaque(true);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblNome.setForeground(Color.DARK_GRAY);
        
        JLabel lblValor = new JLabel(nf.format(valor));
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblValor.setForeground(Color.BLACK);

        textPanel.add(lblNome);
        textPanel.add(Box.createRigidArea(new Dimension(0, 3))); 
        textPanel.add(lblValor);

        JPanel bolinhaContainer = new JPanel(new GridBagLayout());
        bolinhaContainer.setBackground(Color.WHITE);
        bolinhaContainer.add(bolinha);
        
        itemPanel.add(bolinhaContainer);
        itemPanel.add(textPanel);

        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelLista.add(itemPanel);
    }

    private void estilizarBotaoModo(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setBorder(new LineBorder(Color.GRAY, 1, true));
        b.setPreferredSize(new Dimension(100, 30));
    }
}