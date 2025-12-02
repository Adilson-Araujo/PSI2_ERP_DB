package br.edu.ifsp.hto.cooperativa.estoque.visao.gerenciamento;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.EspecieDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaEspecieCRUD {
    static ArmazemDAO armazemDAO = ArmazemDAO.getInstance();
    static ArmazemVO armazemDados = null;
    static JTextField textId = null;
    static JTextField textNome = null;
    static JComboBox<String> comboEndereco = null;
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

        // Campo de Endereço
        JPanel painelEndereco = new JPanel();
        painelEndereco.setLayout(new BoxLayout(painelEndereco, BoxLayout.Y_AXIS));
        painelEndereco.setBackground(new Color(55, 61, 13));
        JLabel labelEndereco = new JLabel("Endereços");
        labelEndereco.setForeground(Color.WHITE);
        String[] opcoes = { "Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5"};
        comboEndereco = new JComboBox<>(opcoes);
        comboEndereco.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        painelEndereco.add(labelEndereco);
        painelEndereco.add(comboEndereco);
        painelDados.add(painelEndereco);
        painelDados.add(Box.createVerticalStrut(10));

        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(55, 61, 13));

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> {
            armazemDados = new ArmazemVO(-1, "", 1);
            atualizarPainelDados();
            atualizarTabelaRegistros();
        });
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String endereco = (String) comboEndereco.getSelectedItem();
            int endereco_id = Character.getNumericValue(endereco.charAt(endereco.length() - 1)); // converte para int            
            armazemDados.setNome(textNome.getText());
            armazemDados.setEndereco(endereco_id);
            if(armazemDados.getId() == -1){
                armazemDAO.inserir(armazemDados);
                atualizarPainelDados();
            } else {
                armazemDAO.atualizar(armazemDados);
            }
            atualizarTabelaRegistros();
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            armazemDAO.excluir(Integer.parseInt(textId.getText()));
            armazemDados = new ArmazemVO(-1, "", 1);
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
        String[] colunas = { "ID", "Nome", "Endereço" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<ArmazemVO> lista = armazemDAO.listarTodos();
        Object[][] dados = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            ArmazemVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getNome();
            dados[i][2] = a.getEnderecoId(); // Trocar para nome do Endereço.
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
        armazemDados = armazemDAO.buscarPorId(id);
        atualizarPainelDados();
    }
    
    private static void atualizarPainelDados(){
        textId.setText(""+armazemDados.getId());
        textNome.setText(armazemDados.getNome());
        comboEndereco.setSelectedItem("Opção " + armazemDados.getEnderecoId());
    }
    
    private static void atualizarTabelaRegistros(){
        // Cabeçalhos da tabela
        String[] colunas = { "ID", "Nome", "Endereço" };

        // Converte a lista de ArmazemVO em matriz para o modelo da tabela
        List<ArmazemVO> lista = armazemDAO.listarTodos();
        Object[][] dados = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            ArmazemVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getNome();
            dados[i][2] = a.getEnderecoId(); // Trocar para nome do Endereço.
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
