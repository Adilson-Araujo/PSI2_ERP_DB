package br.edu.ifsp.hto.cooperativa.financeiro.visao;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
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

// Imports das suas classes
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.OrdemProducaoDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.ProdutoPrecificadoTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPAVO;

public class VisaoProducao extends JInternalFrame {

    private JLabel lblPeriodo;
    private JLabel lblValorTotal;
    private JPanel painelLista;

    private JPanel painelFiltroDatas;
    private JDateChooser txtDataInicio;
    private JDateChooser txtDataFim;
    private JButton btnFiltrar;

    private DefaultPieDataset dataset;
    private ChartPanel chartPanel; // Promovido para atributo de classe

    // Singleton do Controle de Estoque
    private final ControleEstoque controleEstoque = ControleEstoque.getInstance();

    // Cores padrão do sistema
    Color verdeEscuro = new Color(55, 61, 13);
    Color verdeOliva = new Color(139, 143, 61);

    // ==================================================================================
    // PALETA DE CORES (A MESMA DA VISÃO PLANEJAMENTO)
    // ==================================================================================
    private final Color[] coresGrafico = {
        new Color(139, 143, 61), // Verde Oliva
        new Color(70, 130, 180), // Steel Blue
        new Color(220, 50, 50),  // Vermelho
        new Color(255, 165, 0),  // Laranja
        new Color(106, 90, 205), // Slate Blue
        new Color(60, 179, 113), // Sea Green
        new Color(218, 165, 32), // Goldenrod
        new Color(199, 21, 133)  // Violet Red
    };

    // Classe auxiliar para agrupar Quantidade e Valor no mesmo Map
    private class DadosAgrupados {
        double quantidadeKg = 0;
        double valorFinanceiro = 0;
    }

    public VisaoProducao(JDesktopPane desktop) {
        super("Produção Realizada", true, true, true, true);
        setSize(1000, 700);
        setVisible(true);
        desktop.add(this);
        this.toFront();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 40, 20, 40));

        // ====================================
        // TÍTULO
        // ====================================
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(verdeEscuro);
        painelTitulo.setBorder(new EmptyBorder(15, 10, 15, 10));

        JLabel titulo = new JLabel("Produção (Ordens Executadas)");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);

        // ====================================
        // PAINEL CENTRAL
        // ====================================
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);
        add(painelCentral, BorderLayout.CENTER);

        // ====================================
        // MODO GERAL / ESPECÍFICO
        // ====================================
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

        // ====================================
        // FILTRO DE DATAS
        // ====================================
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

        painelFiltroDatas.add(new JLabel("Início:"));
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

        // =========================================
        // EVENTOS
        // =========================================
        btnGeral.addActionListener(e -> {
            btnGeral.setBackground(verdeOliva);
            btnGeral.setForeground(Color.WHITE);

            btnEspec.setBackground(Color.WHITE);
            btnEspec.setForeground(Color.BLACK);

            lblPeriodo.setVisible(false);
            painelFiltroDatas.setVisible(false);

            carregarProducaoDoBanco(null, null);
        });

        btnEspec.addActionListener(e -> {
            btnEspec.setBackground(verdeOliva);
            btnEspec.setForeground(Color.WHITE);

            btnGeral.setBackground(Color.WHITE);
            btnGeral.setForeground(Color.BLACK);

            lblPeriodo.setVisible(true);
            painelFiltroDatas.setVisible(true);

            carregarProducaoDoBanco(null, null);
        });

        btnFiltrar.addActionListener(e -> {
            if (txtDataInicio.getDate() == null || txtDataFim.getDate() == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecione a data de início e a data de fim!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.util.Date ini = txtDataInicio.getDate();
            java.util.Date fim = txtDataFim.getDate();

            if (fim.before(ini)) {
                JOptionPane.showMessageDialog(this,
                        "A data final não pode ser anterior à inicial!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            carregarProducaoDoBanco(ini, fim);
        });

        // ====================================
        // GRÁFICO + LISTA
        // ====================================
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4;
        gbc.weighty = 1;

        JFreeChart donut = criarDonutChart();
        chartPanel = new ChartPanel(donut);
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

        // LISTA
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
        carregarProducaoDoBanco(null, null);
    }

    // ==================================================================
    // GRÁFICO
    // ==================================================================
    private JFreeChart criarDonutChart() {
        dataset = new DefaultPieDataset();
        dataset.setValue("Vazio", 1);

        JFreeChart chart = ChartFactory.createRingChart("", dataset, false, false, false);

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        
        // Configura vazio como transparente
        plot.setSectionPaint("Vazio", new Color(0, 0, 0, 0));
        
        plot.setSectionDepth(0.40);
        plot.setShadowPaint(null);
        plot.setLabelGenerator(null); // Remove labels do gráfico

        return chart;
    }

    // ==================================================================
    // CARREGAMENTO DO BANCO DE DADOS
    // ==================================================================
    private void carregarProducaoDoBanco(java.util.Date filtroInicio, java.util.Date filtroFim) {
        painelLista.removeAll();
        dataset.clear();
        
        // Mapa para agrupar (Nome do Plano -> Dados acumulados)
        Map<String, DadosAgrupados> agrupamento = new HashMap<>();
        double totalGeral = 0;

        // Abre conexão e passa para o DAO
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            OrdemProducaoDAO dao = new OrdemProducaoDAO(conn);
            List<OrdemProducaoVO> listaOrdens = dao.listarTodos();

            for (OrdemProducaoVO ordem : listaOrdens) {
                
                // Filtro de status "deletado"
                if (ordem.getStatus() != null && ordem.getStatus().equalsIgnoreCase("deletado")) {
                    continue;
                }

                // Define a data de referência
                java.util.Date dataRef = ordem.getDataExecucao();
                if (dataRef == null) dataRef = ordem.getDataFim();
                if (dataRef == null) continue;

                // Filtro de Datas
                if (filtroInicio != null && filtroFim != null) {
                    if (dataRef.before(filtroInicio) || dataRef.after(filtroFim)) {
                        continue;
                    }
                }

                // Cálculo do valor financeiro
                double valorItem = calcularValorOrdem(ordem, dataRef);
                double qtdItem = (ordem.getQuantidadeKg() != null) ? ordem.getQuantidadeKg() : 0;
                
                // Nome para agrupamento (Nome do Plano ou ID)
                String nomeExibicao = (ordem.getNomePlano() != null && !ordem.getNomePlano().isEmpty()) 
                        ? ordem.getNomePlano() 
                        : "Ordem " + ordem.getId();

                // AGRUPAMENTO DOS DADOS
                DadosAgrupados dados = agrupamento.getOrDefault(nomeExibicao, new DadosAgrupados());
                dados.quantidadeKg += qtdItem;
                dados.valorFinanceiro += valorItem;
                agrupamento.put(nomeExibicao, dados);
                
                totalGeral += valorItem;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao buscar produção: " + e.getMessage(), 
                "Erro de Banco", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // --- ATUALIZAÇÃO DO GRÁFICO E LISTA COM CORES ---
        
        // Obtém o plot para definir cores
        RingPlot plot = (RingPlot) chartPanel.getChart().getPlot();
        plot.clearSectionPaints(false);

        if (agrupamento.isEmpty()) {
             dataset.setValue("Vazio", 1);
        } else {
             int corIndex = 0;
             for (Map.Entry<String, DadosAgrupados> entry : agrupamento.entrySet()) {
                 String nome = entry.getKey();
                 DadosAgrupados dados = entry.getValue();

                 // 1. Escolhe a cor da paleta ciclicamente
                 Color corDaSecao = coresGrafico[corIndex % coresGrafico.length];

                 // 2. Define no dataset e FORÇA a cor no plot
                 // Nota: O gráfico pizza usa o Valor Financeiro para o tamanho da fatia
                 dataset.setValue(nome, dados.valorFinanceiro);
                 plot.setSectionPaint(nome, corDaSecao);

                 // 3. Adiciona na lista lateral
                 addItemLista(nome, dados.quantidadeKg, dados.valorFinanceiro, corDaSecao);

                 corIndex++;
             }
        }

        NumberFormat nfTotal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblValorTotal.setText(nfTotal.format(totalGeral));

        painelLista.revalidate();
        painelLista.repaint();
    }

    /**
     * Calcula o valor financeiro da ordem.
     */
    private double calcularValorOrdem(OrdemProducaoVO ordem, java.util.Date dataRef) {
        if (ordem.getQuantidadeKg() == null || ordem.getQuantidadeKg() <= 0) return 0;
        if (ordem.getEspecieId() == null) return 0;

        Timestamp ts = new Timestamp(dataRef.getTime());
        int especieIdInt = ordem.getEspecieId().intValue(); 

        ProdutoPrecificadoTO produtoPrecificado = controleEstoque.buscarPrecos(especieIdInt, ts);
        
        if (produtoPrecificado != null && produtoPrecificado.getPrecoPPA() != null) {
            double precoKg = produtoPrecificado.getPrecoPPA().getValor();
            return ordem.getQuantidadeKg() * precoKg;
        }

        return 0;
    }

    // Método atualizado para receber a COR e formatar igual ao Planejamento
    private void addItemLista(String texto, double quantidade, double valorFinanceiro, Color cor) {
        
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Bolinha colorida
        JPanel bolinha = new JPanel();
        bolinha.setPreferredSize(new Dimension(12, 12));
        bolinha.setBackground(cor);
        bolinha.setOpaque(true);

        // Formatação dos Textos
        NumberFormat nfQtd = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nfQtd.setMaximumFractionDigits(2);
        
        NumberFormat nfValor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Painel Vertical de Textos
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        JLabel lblNome = new JLabel(texto);
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblNome.setForeground(Color.DARK_GRAY);

        // Exibe: "150 Kg | R$ 5.000,00"
        JLabel lblDetalhes = new JLabel(nfQtd.format(quantidade) + " Kg  |  " + nfValor.format(valorFinanceiro));
        lblDetalhes.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblDetalhes.setForeground(Color.BLACK);

        textPanel.add(lblNome);
        textPanel.add(Box.createRigidArea(new Dimension(0, 3))); 
        textPanel.add(lblDetalhes);

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