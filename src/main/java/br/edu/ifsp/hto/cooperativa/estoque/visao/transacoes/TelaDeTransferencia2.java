package br.edu.ifsp.hto.cooperativa.estoque.visao.transacoes;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EstoqueAtualDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.TipoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EstoqueAtualVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.Instant;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaDeTransferencia2 {
    static TipoDAO tipoDAO = TipoDAO.getInstance();
    static ArmazemDAO armazemDAO = ArmazemDAO.getInstance();
    static MovimentacaoDAO movimentacaoDAO = MovimentacaoDAO.getInstance();
    static ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
    static OrigemDAO origemDAO = OrigemDAO.getInstance();
    static EstoqueAtualDAO estoqueAtualDAO = EstoqueAtualDAO.getInstance();
    
    static JComboBox<ArmazemVO> comboArmazemDe = null;
    static JComboBox<ArmazemVO> comboArmazemPara = null;
    static JComboBox<ProdutoVO> comboProduto = null;
    static JTextField textQuantidade = null;

    static JTable tabelaSaldo = null;

    public static JTextField addCampo(String txtLabel, JTextField textField, JPanel painelAdd){
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(55, 61, 13));
        JLabel label = new JLabel(txtLabel);
        label.setForeground(Color.WHITE);
        textField = new JTextField();
        textField.setEditable(true);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painel.add(label);
        painel.add(textField);
        painelAdd.add(painel);
        painelAdd.add(Box.createVerticalStrut(20));
        return textField;
    }
    
    public static <T> JComboBox addComboBox(
        String txtLabel,
        JComboBox<T> comboBox,
        Object dao,
        Class<T> tipo,
        JPanel painelAdd
    ) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(55, 61, 13));

        JLabel label = new JLabel(txtLabel);
        label.setForeground(Color.WHITE);

        // Chamar o método listarTodos() via reflexão
        List<T> lista;
        try {
            lista = (List<T>) dao.getClass().getMethod("listarTodos").invoke(dao);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        comboBox = new JComboBox<>(lista.toArray(size -> (T[]) java.lang.reflect.Array.newInstance(tipo, size)));
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        painel.add(label);
        painel.add(comboBox);

        painelAdd.add(painel);
        painelAdd.add(Box.createVerticalStrut(10));
        
        return comboBox;
    }
    
    public static JInternalFrame gerarFrameInterno() {
        final JInternalFrame janela = new JInternalFrame("Transferencia de Produtos", true, true, true, true);
        janela.setSize(700, 500);
        janela.setLayout(new BorderLayout());

        // --------------------------
        // PAINEL DA ESQUERDA (FORM)
        // --------------------------
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelDados.setBackground(new Color(55, 61, 13));
        
        // Campos
        comboArmazemDe = addComboBox("Armazem de Origem:", comboArmazemDe, armazemDAO, ArmazemVO.class, painelDados);
        comboArmazemPara = addComboBox("Armazém de Destino:", comboArmazemPara, armazemDAO, ArmazemVO.class, painelDados);
        comboProduto = addComboBox("Produto:", comboProduto, produtoDAO, ProdutoVO.class, painelDados);
        textQuantidade = addCampo("Quantidade:", textQuantidade, painelDados);

        comboArmazemDe.addActionListener(ev ->{
            atualizarTabelaRegistros();
        });
        
        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(e -> {
            ArmazemVO armazemOrigem = (ArmazemVO) comboArmazemDe.getSelectedItem();
            ArmazemVO armazemDestino = (ArmazemVO) comboArmazemPara.getSelectedItem();
            ProdutoVO produto = (ProdutoVO) comboProduto.getSelectedItem();
            float quantidade = Double.valueOf(textQuantidade.getText()).floatValue();

            MovimentacaoVO movimentacao1 = new MovimentacaoVO(-1, tipoDAO.buscarPorId(5), origemDAO.buscarPorId(3), produto, armazemOrigem, 1, quantidade, Timestamp.from(Instant.now()));
            MovimentacaoVO movimentacao2 = new MovimentacaoVO(-1, tipoDAO.buscarPorId(10), origemDAO.buscarPorId(3), produto, armazemDestino, 1, quantidade, Timestamp.from(Instant.now()));

            try{
                movimentacaoDAO.inserir(movimentacao1);
                movimentacaoDAO.inserir(movimentacao2);            
            } catch (Exception ex){
                ex.printStackTrace();
            }
            
            limpaPainelDados();
            atualizarTabelaRegistros();
        });
        

        painelBotoes.add(btnTransferir);

        painelDados.add(Box.createVerticalGlue());
        painelDados.add(painelBotoes);
        painelDados.add(painelBotoes);

        // --------------------------
        // PAINEL DA DIREITA (TABELA)
        // --------------------------
        JPanel painelRegistros = new JPanel(new BorderLayout());
        painelRegistros.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelRegistros.setBackground(new Color(55, 61, 13));
        
        // Cabeçalhos da tabela
        String[] colunas = { "Produto", "Quantidade", };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<EstoqueAtualVO> lista = estoqueAtualDAO.buscarArmazem(1, ((ArmazemVO) comboArmazemDe.getSelectedItem()).getId());
        Object[][] dados = new Object[lista.size()][2];

        for (int i = 0; i < lista.size(); i++) {
            EstoqueAtualVO a = lista.get(i);
            dados[i][0] = a.getProduto();
            dados[i][1] = a.getQuantidade();
        }

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaSaldo = new JTable(modelo);
        tabelaSaldo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabelaSaldo);
        painelRegistros.add(scroll, BorderLayout.CENTER);

        // --------------------------
        // DIVISÃO LADO A LADO
        // --------------------------
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelDados, painelRegistros);

        split.setDividerLocation(275);
        split.setResizeWeight(0.3);
        split.setBorder(null);

        janela.add(split, BorderLayout.CENTER);
        janela.setVisible(true);

        return janela;
    }
    
    private static void limpaPainelDados(){
        comboArmazemDe.setSelectedIndex(0);
        comboArmazemPara.setSelectedIndex(0);
        comboProduto.setSelectedIndex(0);
        textQuantidade.setText("0.0");
    }
    
    private static void atualizarTabelaRegistros(){
        // Cabeçalhos da tabela
        String[] colunas = { "Produto", "Quantidade", };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<EstoqueAtualVO> lista = estoqueAtualDAO.buscarArmazem(1, ((ArmazemVO) comboArmazemDe.getSelectedItem()).getId());
        Object[][] dados = new Object[lista.size()][2];

        for (int i = 0; i < lista.size(); i++) {
            EstoqueAtualVO a = lista.get(i);
            dados[i][0] = a.getProduto();
            dados[i][1] = a.getQuantidade();
        }

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaSaldo.setModel(modelo);
    }
}
