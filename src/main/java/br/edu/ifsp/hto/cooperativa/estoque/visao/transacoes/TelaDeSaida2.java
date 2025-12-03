package br.edu.ifsp.hto.cooperativa.estoque.visao.transacoes;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.TipoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.TipoVO;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.Instant;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaDeSaida2 {
    static TipoDAO tipoDAO = TipoDAO.getInstance();
    static ArmazemDAO armazemDAO = ArmazemDAO.getInstance();
    static MovimentacaoDAO movimentacaoDAO = MovimentacaoDAO.getInstance();
    static ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
    static OrigemDAO origemDAO = OrigemDAO.getInstance();

    static MovimentacaoVO movimentacaoDados = null;
    
    static JComboBox<ArmazemVO> comboArmazem = null;
    static JComboBox<ProdutoVO> comboProduto = null;
    static JComboBox<TipoVO> comboTipo = null;
    static JTextField textQuantidade = null;
    
    static JTable tabelaRegistros = null;

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
        final JInternalFrame janela = new JInternalFrame("Saída de Produtos", true, true, true, true);
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
        comboArmazem = addComboBox("Armazém:", comboArmazem, armazemDAO, ArmazemVO.class, painelDados);
        comboProduto = addComboBox("Produto:", comboProduto, produtoDAO, ProdutoVO.class, painelDados);
        comboTipo = addComboBox("Tipo de Saída:", comboTipo, tipoDAO, TipoVO.class, painelDados);
        textQuantidade = addCampo("Quantidade:", textQuantidade, painelDados);

        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> {
            comboArmazem.setSelectedIndex(0);
            comboProduto.setSelectedIndex(0);
            comboTipo.setSelectedIndex(0);
            textQuantidade.setText("0.0");            
            ArmazemVO armazem = (ArmazemVO) comboArmazem.getSelectedItem();
            ProdutoVO produto = (ProdutoVO) comboProduto.getSelectedItem();
            TipoVO tipo = (TipoVO) comboTipo.getSelectedItem();
            float quantidade = Double.valueOf(textQuantidade.getText()).floatValue();
            movimentacaoDados = new MovimentacaoVO(-1, tipo, origemDAO.buscarPorId(3), produto, armazem, 1, quantidade, Timestamp.from(Instant.now()));
            atualizarPainelDados();
            atualizarTabelaRegistros();
        });
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            if(movimentacaoDados != null){
                movimentacaoDados.setArmazem((ArmazemVO) comboArmazem.getSelectedItem());
                movimentacaoDados.setProduto((ProdutoVO) comboProduto.getSelectedItem());
                movimentacaoDados.setTipo((TipoVO) comboTipo.getSelectedItem());
                movimentacaoDados.setQuantidade(Double.valueOf(textQuantidade.getText()).floatValue());                
                try{
                    if(movimentacaoDados.getId() == -1){
                        movimentacaoDAO.inserir(movimentacaoDados);
                        atualizarPainelDados();
                    } else {
                        movimentacaoDAO.atualizar(movimentacaoDados);
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                atualizarTabelaRegistros();
            }
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            if (movimentacaoDados != null){
                movimentacaoDAO.excluir(movimentacaoDados.getId());
                comboArmazem.setSelectedIndex(0);
                comboProduto.setSelectedIndex(0);
                comboTipo.setSelectedIndex(0);
                textQuantidade.setText("0.0");
                ArmazemVO armazem = (ArmazemVO) comboArmazem.getSelectedItem();
                ProdutoVO produto = (ProdutoVO) comboProduto.getSelectedItem();
                TipoVO tipo = (TipoVO) comboTipo.getSelectedItem();
                float quantidade = Double.valueOf(textQuantidade.getText()).floatValue();
                movimentacaoDados = new MovimentacaoVO(-1, tipo, origemDAO.buscarPorId(3), produto, armazem, 1, quantidade, Timestamp.from(Instant.now()));
                atualizarPainelDados();
                atualizarTabelaRegistros();
            }
        });

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);

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
        String[] colunas = { "ID", "Produto", "Armazém", "Quantidade", "Data" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<MovimentacaoVO> lista = movimentacaoDAO.listarSaidas(1);
        Object[][] dados = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            MovimentacaoVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getProduto();
            dados[i][2] = a.getArmazem();
            dados[i][3] = a.getQuantidade();
            dados[i][4] = a.getData();
        }

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaRegistros = new JTable(modelo);
        tabelaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Evento de clique
        tabelaRegistros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaRegistros.getSelectedRow();
                if (linha >= 0) {
                    int id = (int) tabelaRegistros.getValueAt(linha, 0);
                    onTabelaClick(id);  // chama a função ao clicar
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaRegistros);
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

    // Função chamada ao clicar um registro da tabela
    private static void onTabelaClick(int id) {
        movimentacaoDados = movimentacaoDAO.buscarPorId(id);
        atualizarPainelDados();
    }
    
    private static void atualizarPainelDados(){
        comboArmazem.setSelectedItem(movimentacaoDados.getArmazem());
        comboProduto.setSelectedItem(movimentacaoDados.getProduto());
        comboTipo.setSelectedItem(movimentacaoDados.getTipo());
        textQuantidade.setText(""+movimentacaoDados.getQuantidade());
    }
    
    private static void atualizarTabelaRegistros(){
        // Cabeçalhos da tabela
        String[] colunas = { "ID", "Produto", "Armazém", "Quantidade", "Data" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<MovimentacaoVO> lista = movimentacaoDAO.listarSaidas(1);
        Object[][] dados = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            MovimentacaoVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getProduto();
            dados[i][2] = a.getArmazem();
            dados[i][3] = a.getQuantidade();
            dados[i][4] = a.getData();
        }

        // Modelo de tabela não editável
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaRegistros.setModel(modelo);
    }
}
