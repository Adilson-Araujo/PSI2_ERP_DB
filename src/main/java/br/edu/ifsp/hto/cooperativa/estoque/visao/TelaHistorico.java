package br.edu.ifsp.hto.cooperativa.estoque.visao;

import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaHistorico {
    static JPanel timelineContent;
    static ProdutoVO produto;
    static String tempo;
    static String tipo = "Todos";
    static ControleEstoque controle = ControleEstoque.getInstance();

    public static void main(String[] args) {

        JFrame janela = new JFrame("Histórico");
        janela.setSize(1200, 700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        /* MENU LATERAL */

        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelMenu.setBackground(new Color(55, 61, 13));

        String[] opcoes = {
                "Registro de Saída", "Registro de Produção", "Visão de Estoque",
                "Inventário", "Cadastro de Produto", "Histórico", "Relatório"
        };

        for (String opcao : opcoes) {
            painelMenu.add(criarBotao(opcao));
            painelMenu.add(Box.createVerticalStrut(10));
        }

        /* PAINEL CENTRAL */

        JPanel painelCentral = new JPanel(null);
        painelCentral.setBackground(new Color(244, 245, 247));
        painelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        /* CAMPO PRODUTO */

        JLabel lblProduto = new JLabel("Produto");
        lblProduto.setBounds(180, 40, 200, 20);

        List<ProdutoVO> produtos = controle.listarProdutos();
        JComboBox<ProdutoVO> comboProduto = new JComboBox<>(
            produtos.toArray(ProdutoVO[]::new)
        );
        comboProduto.setEditable(true);
        comboProduto.setBounds(180, 60, 260, 32);
        comboProduto.setBorder(new LineBorder(Color.GRAY));
        comboProduto.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ProdutoVO item = (ProdutoVO) e.getItem();
                setProduto(item);
            }
        });

        /* COMBOS */

        JComboBox<String> comboTempo = new JComboBox<>(new String[]{
                "Últimos 30 dias", "Últimos 7 dias", "Últimos 90 dias", "Há 1 ano"
        });
        comboTempo.setBounds(480, 60, 150, 32);
        comboTempo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();
                setTempo(item);
            }
        });

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{
                "Todos", "Entradas", "Saídas"
        });
        comboTipo.setBounds(640, 60, 150, 32);
        comboTipo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();
                setTipo(item);
            }
        });

        /* TIMELINE COM SCROLL - SEM FOR */

        timelineContent = new JPanel();
        timelineContent.setLayout(new BoxLayout(timelineContent, BoxLayout.Y_AXIS));
        timelineContent.setBackground(Color.WHITE);
        timelineContent.setBorder(new EmptyBorder(10, 10, 10, 10));

        // itens adicionados 100% manualmente (SEM FOR)
        timelineContent.add(criarLinhaTimeline("03/09/2025", "Entrada", "+10 unid.",
                new Color(50, 180, 70)));
        timelineContent.add(Box.createVerticalStrut(12));

        timelineContent.add(criarLinhaTimeline("02/09/2025", "Saída", "−5 unid.",
                new Color(180, 150, 20)));
        timelineContent.add(Box.createVerticalStrut(12));

        timelineContent.add(criarLinhaTimeline("01/09/2025", "Entrada", "+20 unid.",
                new Color(210, 60, 50)));
        timelineContent.add(Box.createVerticalStrut(12));

        // Mais dois apenas para garantir que o scroll apareça
        timelineContent.add(criarLinhaTimeline("30/08/2025", "Entrada", "+7 unid.",
                new Color(50, 180, 70)));
        timelineContent.add(Box.createVerticalStrut(12));

        timelineContent.add(criarLinhaTimeline("29/08/2025", "Saída", "−2 unid.",
                new Color(210, 60, 50)));
        timelineContent.add(Box.createVerticalStrut(12));

        JScrollPane timelineScroll = new JScrollPane(
                timelineContent,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        timelineScroll.setBounds(470, 150, 380, 400);
        timelineScroll.getVerticalScrollBar().setUnitIncrement(16);
        timelineScroll.setBorder(new LineBorder(new Color(200, 200, 200), 1));

        /* ADIÇÃO AO PAINEL */

        painelCentral.add(lblProduto);
        painelCentral.add(comboProduto);
        painelCentral.add(comboTempo);
        painelCentral.add(comboTipo);
        painelCentral.add(timelineScroll);

        janela.add(painelMenu, BorderLayout.WEST);
        janela.add(painelCentral, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    /* BOTÕES DO MENU */
    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        Dimension tamanho = new Dimension(130, 30);
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setFocusPainted(false);
        return botao;
    }

    /* LINHA DA TIMELINE */
    private static JPanel criarLinhaTimeline(String data, String tipo, String qtd, Color cor) {

        JPanel linha = new JPanel(null);
        linha.setPreferredSize(new Dimension(600, 80));
        linha.setBackground(Color.WHITE);

        JPanel circulo = new JPanel();
        circulo.setBackground(cor);
        circulo.setBounds(10, 10, 18, 18);
        circulo.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        circulo.setOpaque(true);

        JLabel lblData = new JLabel(data);
        lblData.setBounds(40, 5, 200, 20);

        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setBounds(40, 25, 200, 20);

        JLabel lblQtd = new JLabel(qtd);
        lblQtd.setBounds(40, 45, 200, 20);

        linha.add(circulo);
        linha.add(lblData);
        linha.add(lblTipo);
        linha.add(lblQtd);

        return linha;
    }
    
    private static void setProduto(ProdutoVO produto){
        TelaHistorico.produto = produto;
        recriarLista();
    }
    private static void setTempo(String tempo){
        TelaHistorico.tempo = tempo;
        recriarLista();
    }
    private static void setTipo(String tipo){
        TelaHistorico.tipo = tipo;
        recriarLista();
    }
    
    public static boolean temNoMaximoNDias(Timestamp ts, int nDias) {
        Instant instante = ts.toInstant();
        Instant limite = Instant.now().minus(Duration.ofDays(nDias));
        return !instante.isBefore(limite);
    }
    
    private static void recriarLista(){
        timelineContent.removeAll();
        Color cor;
        int temp = 0;
        switch (tempo) {
            case "Últimos 7 dias" -> temp = 7;
            case "Últimos 30 dias" -> temp = 30;
            case "Últimos 90 dias" -> temp = 90;
            case "Há 1 ano" -> temp = 365;
            default -> {
            }
        }
        
        List<MovimentacaoVO> movimentacoes = controle.listarMovimentacoes(1, produto.getId());
        for(int i = 0; i < movimentacoes.size(); i++){
            MovimentacaoVO movimentacao = movimentacoes.get(i);
            Timestamp dt = movimentacao.getData();
            String data = dt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            if (temNoMaximoNDias(dt, temp)) {
                String t = "Entrada";
                cor = new Color(50, 180, 70);
                if (movimentacao.getQuantidade() < 0){
                    t = "Saída";
                    cor = new Color(210, 60, 50);
                }
                
                if(tipo.equals("Todos") ||
                        (tipo.equals("Saídas") && movimentacao.getQuantidade() < 0) ||
                        (tipo.equals("Entradas") && movimentacao.getQuantidade() > 0)){
                    timelineContent.add(criarLinhaTimeline(data, t, ""+movimentacao.getQuantidade(), cor));
                    timelineContent.add(Box.createVerticalStrut(12));
                }
            }
        }
        
        timelineContent.revalidate();
        timelineContent.repaint();
    }
}
