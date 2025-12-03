package br.edu.ifsp.hto.cooperativa.estoque.visao.transacoes;

import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ArmazemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.MovimentacaoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.OrigemDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.dao.TipoDAO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.MovimentacaoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.Instant;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaDeEntrada {
    static MovimentacaoVO movimentacaoSelecionada = null;
    
    public static JInternalFrame gerarFrameInterno() {
        ControleEstoque controle = ControleEstoque.getInstance();
        MovimentacaoDAO movimentacaoDAO = MovimentacaoDAO.getInstance();
        TipoDAO tipoDAO = TipoDAO.getInstance();
        OrigemDAO origemDAO = OrigemDAO.getInstance();

        final JInternalFrame janela = new JInternalFrame("Entrada no Estoque", true, true, true, true);
        janela.setSize(700, 500); 
        janela.setLayout(new BorderLayout());


        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelMenu.setBackground(new Color(55, 61, 13));

        String[] opcoes = {"Registro de Saída", "Registro de Produção", "Visão de Estoque", "Inventário", "Cadastro de Produto", "Histórico", "Relatório"};
        for (String opcao : opcoes) {
            painelMenu.add(criarBotao(opcao));
            painelMenu.add(Box.createVerticalStrut(10));
        }


        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBorder(new EmptyBorder(20, 60, 20, 60));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

      
        JLabel lblTitulo = new JLabel("Adicionar");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(108, 122, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        painelCentral.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        
        JLabel lblArmazem = new JLabel("Selecione o Armazém:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(lblArmazem, gbc);

        List<ArmazemVO> armazens = controle.listarArmazens();
        JComboBox<ArmazemVO> comboArmazem = new JComboBox<>(
            armazens.toArray(ArmazemVO[]::new)
        );
        
        gbc.gridx = 1;
        painelCentral.add(comboArmazem, gbc);

        JLabel lblProduto = new JLabel("Selecione o Produto:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(lblProduto, gbc);

        List<ProdutoVO> produtos = controle.listarProdutos();
        JComboBox<ProdutoVO> comboProduto = new JComboBox<>(
            produtos.toArray(ProdutoVO[]::new)
        );
        
        gbc.gridx = 1;
        painelCentral.add(comboProduto, gbc);
  
        JLabel lblQuantidade = new JLabel("Quantidade:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCentral.add(lblQuantidade, gbc);

        JTextField txtQuantidade = new JTextField(10);
        gbc.gridx = 1;
        painelCentral.add(txtQuantidade, gbc);

     
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        
        btnSalvar.addActionListener(ev -> {
            ArmazemVO armazem = (ArmazemVO) comboArmazem.getSelectedItem();
            ProdutoVO produto = (ProdutoVO) comboProduto.getSelectedItem();
            float quantidade = (Double.valueOf(txtQuantidade.getText())).floatValue();
            MovimentacaoVO movimentacao = new MovimentacaoVO(-1, tipoDAO.buscarPorId(1), origemDAO.buscarPorId(3), produto, armazem, 1, quantidade, Timestamp.from(Instant.now()));
            try{
                if(movimentacao.getId() == -1){
                    movimentacaoDAO.inserir(movimentacao);
                } else {
                    movimentacaoDAO.atualizar(movimentacao);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnSalvar.setBackground(new Color(50, 50, 50)); 
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(100, 30));

        btnExcluir.setPreferredSize(new Dimension(100, 30));
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0); 
        painelCentral.add(painelBotoes, gbc);
        
        String[] colunas = {"ID", "Produto", "Armazém", "Quantidade", "Data"};

        List<MovimentacaoVO> lista = movimentacaoDAO.listarEntradas(1);
        Object[][] dados = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            MovimentacaoVO a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getProduto();
            dados[i][2] = a.getArmazem();
            dados[i][3] = a.getQuantidade();
            dados[i][4] = a.getData();
        }
        
        
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tabela = new JTable(model);
        
        // Evento de clique
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    int id = (int) tabela.getValueAt(linha, 0);
                    movimentacaoSelecionada = movimentacaoDAO.buscarPorId(id);
                    comboArmazem.setSelectedItem(movimentacaoSelecionada.getArmazem());
                    comboProduto.setSelectedItem(movimentacaoSelecionada.getProduto());
                    txtQuantidade.setText("" + movimentacaoSelecionada.getQuantidade());
                }
            }
        });

        final JScrollPane painelTabela = new JScrollPane(tabela);
        painelTabela.setPreferredSize(new Dimension(600, 150));
        painelTabela.setBorder(BorderFactory.createTitledBorder("Registros Existentes"));

        painelTabela.setVisible(false);


        final JButton btnExpandir = new JButton("Mostrar Registros");
        
        gbc.gridy = 5; 
        gbc.insets = new Insets(10, 0, 0, 0);
        painelCentral.add(btnExpandir, gbc);

        btnExpandir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean visivel = painelTabela.isVisible();
                
                painelTabela.setVisible(!visivel);

                if (visivel) {
                    btnExpandir.setText("Mostrar Registros");
                } else {
                    btnExpandir.setText("Ocultar Registros");
                }

                janela.pack();
                
                if (!visivel) {
                    janela.setMinimumSize(new Dimension(700, 600));
                }
            }
        });

        JPanel painelWrapper = new JPanel(new GridBagLayout());
        painelWrapper.add(painelCentral, new GridBagConstraints());


        janela.add(painelMenu, BorderLayout.WEST);
        janela.add(painelWrapper, BorderLayout.CENTER);
        janela.add(painelTabela, BorderLayout.SOUTH);
        
        janela.setVisible(true);
        return janela;
    }


    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        Dimension tamanho = new Dimension(130, 30);
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setMinimumSize(tamanho);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botao;
    }
}