package br.edu.ifsp.hto.cooperativa.estoque.visao.transacoes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeTransferencia {

    public static JInternalFrame gerarFrameInterno() {
        
        final JInternalFrame janela = new JInternalFrame("Transferência de Produtos");
        janela.setSize(700, 500);
        janela.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
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

      
        JLabel lblTitulo = new JLabel("Transferência");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(108, 122, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelCentral.add(lblTitulo, gbc);

      
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

  
        JLabel lblArmazemOrigem = new JLabel("Armazém de Origem:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(lblArmazemOrigem, gbc);

        String[] armazens = {"Armazém Principal", "Armazém Secundário"};
        JComboBox<String> comboArmazemOrigem = new JComboBox<>(armazens);
        gbc.gridx = 1;
        painelCentral.add(comboArmazemOrigem, gbc);
        
    
        JLabel lblArmazemDestino = new JLabel("Armazém de Destino:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(lblArmazemDestino, gbc);

        JComboBox<String> comboArmazemDestino = new JComboBox<>(armazens);
        gbc.gridx = 1;
        painelCentral.add(comboArmazemDestino, gbc);

      
        JLabel lblProduto = new JLabel("Selecione o Produto:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCentral.add(lblProduto, gbc);

        String[] produtos = {"Arroz", "Feijão", "Macarrão", "Açúcar"};
        JComboBox<String> comboProdutos = new JComboBox<>(produtos);
        gbc.gridx = 1;
        painelCentral.add(comboProdutos, gbc);

     
        JLabel lblQuantidade = new JLabel("Quantidade:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelCentral.add(lblQuantidade, gbc);

        JTextField txtQuantidade = new JTextField(10);
        gbc.gridx = 1;
        painelCentral.add(txtQuantidade, gbc);

      
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        
        btnSalvar.setBackground(new Color(50, 50, 50));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(100, 30));

        btnExcluir.setPreferredSize(new Dimension(100, 30));
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0); 
        painelCentral.add(painelBotoes, gbc);

         String[] colunas = {"ID", "Produto", "Armazém", "Quantidade", "Data"};
        Object[][] dados = {
            {"1", "ALfacec", "Armazém Principal", "50", "16/11/2025"},
            {"2", "Milho", "Armazém Secundário", "30", "16/11/2025"}
        };
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tabela = new JTable(model);

        final JScrollPane painelTabela = new JScrollPane(tabela);
        painelTabela.setPreferredSize(new Dimension(600, 150));
        painelTabela.setBorder(BorderFactory.createTitledBorder("Registros Existentes"));

        painelTabela.setVisible(false);


        final JButton btnExpandir = new JButton("Mostrar Registros");
        
        gbc.gridy = 5; 
        gbc.insets = new Insets(100, 0, 0, 0);
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