package br.edu.ifsp.hto.cooperativa.estoque.visao.gerenciamento;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.CategoriaDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.CategoriaVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaEspecieCRUD {
    static EspecieDAO especieDAO = EspecieDAO.getInstance();
    static CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
    static EspecieVO especieDados = null;
    static CategoriaVO categoriaDados = null;
    static JTextField textId = null;
    static JComboBox<CategoriaVO> comboCategoria = null;
    static JTextField textNome = null;
    static JTextField textDescricao = null;
    static JTextField textTempoColheita = null;
    static JTextField textRendimentoM2 = null;
    
    static JTable tabelaRegistros = null;

    public static JInternalFrame gerarFrameInterno() {
        final JInternalFrame janela = new JInternalFrame("Gerenciar Especies", true, true, true, true);
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

        // Campo de Categoria
        JPanel painelCategoria = new JPanel();
        painelCategoria.setLayout(new BoxLayout(painelCategoria, BoxLayout.Y_AXIS));
        painelCategoria.setBackground(new Color(55, 61, 13));
        JLabel labelCategoria = new JLabel("Categoria");
        labelCategoria.setForeground(Color.WHITE);
        List<CategoriaVO> opcoes = categoriaDAO.listarTodas();
        comboCategoria = new JComboBox<>(opcoes.toArray(CategoriaVO[]::new));
        comboCategoria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelCategoria.add(labelCategoria);
        painelCategoria.add(comboCategoria);
        painelDados.add(painelCategoria);
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

        // Campo de Descricao
        JPanel painelDescricao = new JPanel();
        painelDescricao.setLayout(new BoxLayout(painelDescricao, BoxLayout.Y_AXIS));
        painelDescricao.setBackground(new Color(55, 61, 13));
        JLabel labelDescricao = new JLabel("Descricao:");
        labelDescricao.setForeground(Color.WHITE);
        textDescricao = new JTextField();
        textDescricao.setEditable(true);
        textDescricao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelDescricao.add(labelDescricao);
        painelDescricao.add(textDescricao);
        painelDados.add(painelDescricao);
        painelDados.add(Box.createVerticalStrut(20));

        // Campo de TempoColheita
        JPanel painelTempoColheita = new JPanel();
        painelTempoColheita.setLayout(new BoxLayout(painelTempoColheita, BoxLayout.Y_AXIS));
        painelTempoColheita.setBackground(new Color(55, 61, 13));
        JLabel labelTempoColheita = new JLabel("TempoColheita:");
        labelTempoColheita.setForeground(Color.WHITE);
        textTempoColheita = new JTextField();
        textTempoColheita.setEditable(true);
        textTempoColheita.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelTempoColheita.add(labelTempoColheita);
        painelTempoColheita.add(textTempoColheita);
        painelDados.add(painelTempoColheita);
        painelDados.add(Box.createVerticalStrut(20));

        // Campo de RendimentoM2
        JPanel painelRendimentoM2 = new JPanel();
        painelRendimentoM2.setLayout(new BoxLayout(painelRendimentoM2, BoxLayout.Y_AXIS));
        painelRendimentoM2.setBackground(new Color(55, 61, 13));
        JLabel labelRendimentoM2 = new JLabel("RendimentoM2:");
        labelRendimentoM2.setForeground(Color.WHITE);
        textRendimentoM2 = new JTextField();
        textRendimentoM2.setEditable(true);
        textRendimentoM2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelRendimentoM2.add(labelRendimentoM2);
        painelRendimentoM2.add(textRendimentoM2);
        painelDados.add(painelRendimentoM2);
        painelDados.add(Box.createVerticalStrut(20));
        
        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> {
            comboCategoria.setSelectedIndex(0);
            especieDados = new EspecieVO(-1, (CategoriaVO) comboCategoria.getSelectedItem(), "", "", 0, 0);
            atualizarPainelDados();
            atualizarTabelaRegistros();
        });
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            especieDados.setCategoria((CategoriaVO) comboCategoria.getSelectedItem());
            especieDados.setNome(textNome.getText());
            especieDados.setDescricao(textDescricao.getText());
            especieDados.setTempo_colheita((int) Integer.valueOf(textTempoColheita.getText()));
            especieDados.setRendimento_kg_m2(((Double) Double.parseDouble(textRendimentoM2.getText())).floatValue());
            if(especieDados.getId() == -1){
                especieDAO.inserir(especieDados);
                atualizarPainelDados();
            } else {
                especieDAO.atualizar(especieDados);
            }
            atualizarTabelaRegistros();
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            especieDAO.excluir(Integer.parseInt(textId.getText()));
            comboCategoria.setSelectedIndex(0);
            especieDados = new EspecieVO(-1, (CategoriaVO) comboCategoria.getSelectedItem(), "", "", 0, 0);
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
        String[] colunas = { "ID", "Categoria", "Nome", "Descrição", "Tempo Colheira", "Rendimento" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<EspecieVO> lista = especieDAO.listarTodas();
        Object[][] dados = new Object[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            EspecieVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getCategoria();
            dados[i][2] = a.getNome();
            dados[i][3] = a.getDescricao();
            dados[i][4] = a.getTempo_colheita();
            dados[i][5] = a.getRendimento_kg_m2();
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
        especieDados = especieDAO.buscarPorId(id);
        atualizarPainelDados();
    }
    
    private static void atualizarPainelDados(){
        textId.setText(""+especieDados.getId());
        comboCategoria.setSelectedItem(especieDados.getCategoria());
        textNome.setText(especieDados.getNome());
        textDescricao.setText(especieDados.getDescricao());
        textTempoColheita.setText(""+especieDados.getTempo_colheita());
        textRendimentoM2.setText(""+especieDados.getRendimento_kg_m2());
    }
    
    private static void atualizarTabelaRegistros(){
        // Cabeçalhos da tabela
        String[] colunas = { "ID", "Categoria", "Nome", "Descrição", "Tempo Colheira", "Rendimento" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<EspecieVO> lista = especieDAO.listarTodas();
        Object[][] dados = new Object[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            EspecieVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getCategoria();
            dados[i][2] = a.getNome();
            dados[i][3] = a.getDescricao();
            dados[i][4] = a.getTempo_colheita();
            dados[i][5] = a.getRendimento_kg_m2();
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
