package br.edu.ifsp.hto.cooperativa.estoque.visao.gerenciamento;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaProdutoCRUD {
    static ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
    static EspecieDAO especieDAO = EspecieDAO.getInstance();
    static ProdutoVO produtoDados = null;
    static JTextField textId = null;
    static JComboBox<String> comboEspecie = null;
    static JTextField textNome = null;
    static JTextArea textDescricao = null;
    static JTable tabelaRegistros = null;

    public static JInternalFrame gerarFrameInterno() {
        final JInternalFrame janela = new JInternalFrame("Gerenciar Produtos", true, true, true, true);
        janela.setSize(700, 500);
        janela.setLayout(new BorderLayout());

        // --------------------------
        // PAINEL DA ESQUERDA (FORM)
        // --------------------------
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelDados.setBackground(new Color(55, 61, 13));

        // Campo de ID
        JPanel painelId = new JPanel();
        painelId.setLayout(new BoxLayout(painelId, BoxLayout.Y_AXIS));
        painelId.setBackground(new Color(55, 61, 13));
        JLabel labelId = new JLabel("ID:");
        labelId.setForeground(Color.WHITE);
        textId = new JTextField();
        textId.setEditable(false);
        textId.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelId.add(labelId);
        painelId.add(textId);
        painelDados.add(painelId);
        painelDados.add(Box.createVerticalStrut(20));

        // Campo de Especie
        JPanel painelEspecie = new JPanel();
        painelEspecie.setLayout(new BoxLayout(painelEspecie, BoxLayout.Y_AXIS));
        painelEspecie.setBackground(new Color(55, 61, 13));
        JLabel labelEspecie = new JLabel("Espécie:");
        labelEspecie.setForeground(Color.WHITE);
        String[] opcoes = { "Opção 10", "Opção 11", "Opção 12", "Opção 13", "Opção 14"};
        comboEspecie = new JComboBox<>(opcoes);
        comboEspecie.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelEspecie.add(labelEspecie);
        painelEspecie.add(comboEspecie);
        painelDados.add(painelEspecie);
        painelDados.add(Box.createVerticalStrut(10));
        
        // Campo de Nome
        JPanel painelNome = new JPanel();
        painelNome.setLayout(new BoxLayout(painelNome, BoxLayout.Y_AXIS));
        painelNome.setBackground(new Color(55, 61, 13));
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setForeground(Color.WHITE);
        textNome = new JTextField();
        textNome.setEditable(true);
        textNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelNome.add(labelNome);
        painelNome.add(textNome);
        painelDados.add(painelNome);
        painelDados.add(Box.createVerticalStrut(20));

        // Campo de Descrição
        JPanel painelDescricao = new JPanel();
        painelDescricao.setLayout(new BoxLayout(painelDescricao, BoxLayout.Y_AXIS));
        painelDescricao.setBackground(new Color(55, 61, 13));
        JLabel labelDescricao = new JLabel("Descricao:");        
        labelDescricao.setForeground(Color.WHITE);
        textDescricao = new JTextArea(5, 28);
        textDescricao.setEditable(true);
        textDescricao.setLineWrap(true);
        textDescricao.setWrapStyleWord(true);
        textDescricao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        painelDescricao.add(labelDescricao);
        painelDescricao.add(textDescricao);
        painelDados.add(painelDescricao);
        painelDados.add(Box.createVerticalStrut(20));

        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> {
            produtoDados = new ProdutoVO(-1, especieDAO.buscarPorId(10), "", "");
            atualizarPainelDados();
            atualizarTabelaRegistros();
        });
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String especie = (String) comboEspecie.getSelectedItem();
            especie = especie.substring(6, especie.length());
            int especie_id = Integer.parseInt(especie); // converte para int
            produtoDados.setEspecie(especieDAO.buscarPorId(especie_id));
            produtoDados.setNome(textNome.getText());
            produtoDados.setDescricao(textDescricao.getText());
            if(produtoDados.getId() == -1){
                produtoDAO.inserir(produtoDados);
                atualizarPainelDados();
            } else {
                produtoDAO.atualizar(produtoDados);
            }
            atualizarTabelaRegistros();
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            produtoDAO.excluir(Integer.parseInt(textId.getText()));
            produtoDados = new ProdutoVO(-1, especieDAO.buscarPorId(10), "", "");
            atualizarPainelDados();
            atualizarTabelaRegistros();
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
        String[] colunas = { "ID", "Especie ID", "Nome", "Descrição" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<ProdutoVO> lista = produtoDAO.listarTodos();
        Object[][] dados = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            ProdutoVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getEspecie().getId();
            dados[i][2] = a.getNome();
            dados[i][3] = a.getDescricao();
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
        produtoDados = produtoDAO.buscarPorId(id);
        atualizarPainelDados();
    }
    
    private static void atualizarPainelDados(){
        textId.setText(""+produtoDados.getId());
        comboEspecie.setSelectedItem("Opção " + produtoDados.getEspecie().getId());
        textNome.setText(produtoDados.getNome());
        textDescricao.setText(produtoDados.getDescricao());
    }
    
    private static void atualizarTabelaRegistros(){
        // Cabeçalhos da tabela
        String[] colunas = { "ID", "Especie ID", "Nome", "Descrição" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<ProdutoVO> lista = produtoDAO.listarTodos();
        Object[][] dados = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            ProdutoVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getEspecie().getId();
            dados[i][2] = a.getNome();
            dados[i][3] = a.getDescricao();
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
