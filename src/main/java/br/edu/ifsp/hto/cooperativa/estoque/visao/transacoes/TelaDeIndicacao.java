package br.edu.ifsp.hto.cooperativa.estoque.visao.transacoes;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaDeIndicacao {
    static ArmazemDAO armazemDAO = ArmazemDAO.getInstance();
    static MovimentacaoDAO movimentacaoDAO = MovimentacaoDAO.getInstance(); 
    static OrigemDAO origemDAO = OrigemDAO.getInstance(); 
    static MovimentacaoVO vendaDados = null;
    static List<MovimentacaoVO> transferenciasDados = new ArrayList<>();
    static float totalTransacoes = 0;

    static JComboBox<ArmazemVO> comboArmazem = null;
    static JTextField textQuantidade = null;
    
    static JTable tabelaTransacoes = null;
    static JTable tabelaVendas = null;

    public static JInternalFrame gerarFrameInterno() {
        final JInternalFrame janela = new JInternalFrame("Indicação de Venda", true, true, true, true);
        janela.setSize(700, 500);
        janela.setLayout(new BorderLayout());

        // --------------------------
        // PAINEL DA ESQUERDA (TABELA NOVAS TRANSACOES)
        // --------------------------
        JPanel painelTransacoes = new JPanel(new BorderLayout());
        painelTransacoes.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelTransacoes.setBackground(new Color(55, 61, 13));

        // Cabeçalhos da tabela
        String[] colunasTransacoes = { "Armazem", "Quantidade" };
        Object[][] dadosTransacoes = new Object[0][2];

        // Modelo de tabela não editável
        DefaultTableModel modeloTransacoes = new DefaultTableModel(dadosTransacoes, colunasTransacoes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaTransacoes = new JTable(modeloTransacoes);
        tabelaTransacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Evento de clique
        tabelaTransacoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaTransacoes.getSelectedRow();
                if (linha >= 0) {
                    comboArmazem.setSelectedItem(tabelaTransacoes.getValueAt(linha, 0));
                    textQuantidade.setText("" + tabelaTransacoes.getValueAt(linha, 1));
                }
            }
        });

        JScrollPane scrollTransacoes = new JScrollPane(tabelaTransacoes);
        painelTransacoes.add(scrollTransacoes, BorderLayout.CENTER);

        // --------------------------
        // PAINEL DA CENTRAL (FORM)
        // --------------------------
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelDados.setBackground(new Color(55, 61, 13));

        // Campo de Armazem
        JPanel painelArmazem = new JPanel();
        painelArmazem.setLayout(new BoxLayout(painelArmazem, BoxLayout.Y_AXIS));
        painelArmazem.setBackground(new Color(55, 61, 13));
        JLabel labelArmazem = new JLabel("Armazem:");
        labelArmazem.setForeground(Color.WHITE);
        List<ArmazemVO> armazens = armazemDAO.listarTodos();
        comboArmazem = new JComboBox<>(armazens.toArray(ArmazemVO[]::new));
        comboArmazem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelArmazem.add(labelArmazem);
        painelArmazem.add(comboArmazem);
        painelDados.add(painelArmazem);
        painelDados.add(Box.createVerticalStrut(10));
        
        // Campo de Quantidade
        JPanel painelQuantidade = new JPanel();
        painelQuantidade.setLayout(new BoxLayout(painelQuantidade, BoxLayout.Y_AXIS));
        painelQuantidade.setBackground(new Color(55, 61, 13));
        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setForeground(Color.WHITE);
        textQuantidade = new JTextField();
        textQuantidade.setEditable(true);
        textQuantidade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelQuantidade.add(labelQuantidade);
        painelQuantidade.add(textQuantidade);
        painelDados.add(painelQuantidade);
        painelDados.add(Box.createVerticalStrut(20));

        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            int i;
            float quantidade = (Double.valueOf(textQuantidade.getText())).floatValue();
            for(i = 0; i < transferenciasDados.size(); i++){
                MovimentacaoVO movimentacao = transferenciasDados.get(i);
                if(movimentacao.getArmazem().getId() == ((ArmazemVO) comboArmazem.getSelectedItem()).getId()){
                    totalTransacoes = totalTransacoes - movimentacao.getQuantidade() + quantidade;
                    movimentacao.setQuantidade(quantidade);
                    break;
                }
            }
            if (i == transferenciasDados.size()){
                    totalTransacoes += quantidade;
                    MovimentacaoVO movimentacao = new MovimentacaoVO(-1, vendaDados.getTipo(), origemDAO.buscarPorId(3), vendaDados.getProduto(), (ArmazemVO) comboArmazem.getSelectedItem(), 1, quantidade, Timestamp.from(Instant.now()));
                    transferenciasDados.add(movimentacao);
                }
            atualizarTabelaTransacoes();
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            for(int i = 0; i < transferenciasDados.size(); i++){
                MovimentacaoVO movimentacao = transferenciasDados.get(i);
                if(movimentacao.getArmazem().getId() == ((ArmazemVO) comboArmazem.getSelectedItem()).getId()){
                    totalTransacoes -= movimentacao.getQuantidade();
                    transferenciasDados.remove(i);
                    break;
                }
            }
            limpaPainelDados();
            atualizarTabelaTransacoes();
        });

        JButton btnAplicar = new JButton("Aplicar");
        btnAplicar.addActionListener(ev -> {
            if(totalTransacoes == vendaDados.getQuantidade()){
                for(int i = 0; i < transferenciasDados.size(); i++){
                    MovimentacaoVO movimentacao = transferenciasDados.get(i);
                    try {
                        movimentacaoDAO.inserir(movimentacao);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                movimentacaoDAO.excluir(vendaDados.getId());
                atualizarTabelaVendas();
                limpaPainelDados();
                transferenciasDados = new ArrayList<>();
                atualizarTabelaTransacoes();
            } else {
                System.out.println(totalTransacoes + " / " + vendaDados.getQuantidade());
            }
        });
        
        JButton btnDesconsiderar = new JButton("Desconsiderar");
        btnDesconsiderar.addActionListener(ev -> {
            try {
                movimentacaoDAO.excluir(vendaDados.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            limpaPainelDados();
            atualizarTabelaVendas();
        });
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAplicar);
        painelBotoes.add(btnDesconsiderar);

        painelDados.add(Box.createVerticalGlue());
        painelDados.add(painelBotoes);
        painelDados.add(painelBotoes);

        // --------------------------
        // PAINEL DA DIREITA (TABELA VENDAS)
        // --------------------------
        JPanel painelVendas = new JPanel(new BorderLayout());
        painelVendas.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelVendas.setBackground(new Color(55, 61, 13));

        // Cabeçalhos da tabela
        String[] colunasVendas = { "ID", "Produto", "Quantidade", "Data" };
        
        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<MovimentacaoVO> listaVendas = movimentacaoDAO.listarVendasIndicar(1);
        Object[][] dadosVendas = new Object[listaVendas.size()][4];

        for (int i = 0; i < listaVendas.size(); i++) {
            MovimentacaoVO a = listaVendas.get(i);
            dadosVendas[i][0] = a.getId();
            dadosVendas[i][1] = a.getProduto();
            dadosVendas[i][2] = a.getQuantidade();
            dadosVendas[i][3] = a.getData();
        }

        // Modelo de tabela não editável
        DefaultTableModel modeloVendas = new DefaultTableModel(dadosVendas, colunasVendas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaVendas = new JTable(modeloVendas);
        tabelaVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Evento de clique
        tabelaVendas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaVendas.getSelectedRow();
                if (linha >= 0) {
                    int id = (int) tabelaVendas.getValueAt(linha, 0);
                    onTabelaVendasClick(id);  // chama a função ao clicar
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaVendas);
        painelVendas.add(scroll, BorderLayout.CENTER);

        // --------------------------
        // DIVISÃO LADO A LADO
        // --------------------------
        // Primeiro split: painelTransacoes | painelDados
        JSplitPane splitEsquerda = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                painelTransacoes,
                painelDados
        );
        splitEsquerda.setDividerLocation(200);     // ajuste inicial
        splitEsquerda.setResizeWeight(0.3);        // % para o painelTransacoes
        splitEsquerda.setBorder(null);

        // Segundo split: (painelTransacoes | painelDados) | painelVendas
        JSplitPane splitFinal = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                splitEsquerda,
                painelVendas
        );
        splitFinal.setDividerLocation(450);        // posição geral
        splitFinal.setResizeWeight(0.66);          // prioriza o lado esquerdo
        splitFinal.setBorder(null);

        // Use splitFinal como o componente final
        janela.add(splitFinal, BorderLayout.CENTER);
        janela.setVisible(true);

        return janela;
    }

    // Função chamada ao clicar um registro da tabela
    private static void onTabelaVendasClick(int id) {
        vendaDados = movimentacaoDAO.buscarPorId(id);
        limpaPainelDados();
        transferenciasDados = new ArrayList<>();        
        atualizarTabelaTransacoes();
    }
    
    private static void limpaPainelDados(){
        comboArmazem.setSelectedIndex(0);
        textQuantidade.setText("");
    }
    
    private static void atualizarTabelaTransacoes(){
        // Cabeçalhos da tabela
        String[] colunasTransacoes = { "Armazem", "Quantidade" };
        Object[][] dadosTransacoes = new Object[transferenciasDados.size()][2];


        for (int i = 0; i < transferenciasDados.size(); i++) {
            MovimentacaoVO a = transferenciasDados.get(i);
            dadosTransacoes[i][0] = a.getArmazem();
            dadosTransacoes[i][1] = a.getQuantidade();
        }

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(dadosTransacoes, colunasTransacoes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaTransacoes.setModel(modelo);
    }
    
    private static void atualizarTabelaVendas(){        
        // Cabeçalhos da tabela
        String[] colunasVendas = { "ID", "Produto", "Quantidade", "Data" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<MovimentacaoVO> listaVendas = movimentacaoDAO.listarVendasIndicar(1);
        Object[][] dadosVendas = new Object[listaVendas.size()][4];

        for (int i = 0; i < listaVendas.size(); i++) {
            MovimentacaoVO a = listaVendas.get(i);
            dadosVendas[i][0] = a.getId();
            dadosVendas[i][1] = a.getProduto();
            dadosVendas[i][2] = a.getQuantidade();
            dadosVendas[i][3] = a.getData();
        }

        // Modelo de tabela não editável
        DefaultTableModel modeloVendas = new DefaultTableModel(dadosVendas, colunasVendas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaVendas.setModel(modeloVendas);
    }
}
