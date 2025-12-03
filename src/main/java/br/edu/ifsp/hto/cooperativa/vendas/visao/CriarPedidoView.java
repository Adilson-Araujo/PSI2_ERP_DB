package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import java.awt.Frame;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.AssociadoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.AssociadoItemPedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.PedidoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProdutoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProjetoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.AssociadoItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;

// NOVOS IMPORTS
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

public class CriarPedidoView extends BaseView {

    private static final Color BG = new Color(0xE9, 0xE9, 0xE9);
    
    // DEFINA AQUI OS IDs QUE ESTÃO NO SEU BANCO DE DADOS
    private static final short TIPO_ASSOCIACAO = 1;
    private static final short TIPO_PRODUTOR = 2;

    private JComboBox<Object> comboAssociado;
    private JComboBox<Object> comboProduto;
    private JComboBox<String> comboProjeto;

    private JTextField campoQuantidade;
    private JTextField campoValorUnitario;

    private JTable tabelaItens;
    private DefaultTableModel itensModel;

    private final List<ItemPedidoVO> itensPedido = new ArrayList<>();
    private BigDecimal totalPedido = BigDecimal.ZERO;

    public CriarPedidoView() {
        super("Criar Pedido");
        add(criarPainel(), BorderLayout.CENTER);

        // REFATORADO: Verificação de tipo via objeto UsuarioTO
        try {
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            if (usuario.usuarioVO.getTipoUsuario() == TIPO_ASSOCIACAO) {
                carregarAssociados();
                carregarProjetos();
            } else {
                configurarProdutor();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro de sessão: " + e.getMessage());
        }
        
        carregarProdutos(); 
        comboProduto.addActionListener(e -> atualizarPreco());
    }

    private JPanel criarPainel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);
        panel.add(criarTitleBar("Criar Pedido"), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(25, 25, 25, 25));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(criarCardFormulario());
        center.add(Box.createVerticalStrut(25));
        center.add(criarCardTabela());
        center.add(Box.createVerticalStrut(25));
        center.add(criarRodape());

        panel.add(center, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarCardFormulario() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Dados do Pedido");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 10, 20, 10);
        card.add(titulo, gbc);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; card.add(new JLabel("Cliente/Associado:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.5; 
        comboAssociado = new JComboBox<>();
        card.add(comboAssociado, gbc);

        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0; card.add(new JLabel("Projeto:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 0.5; 
        comboProjeto = new JComboBox<>();
        card.add(comboProjeto, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; card.add(new JLabel("Produto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 3; gbc.weightx = 1.0;
        comboProduto = new JComboBox<>();
        card.add(comboProduto, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; card.add(new JLabel("Quantidade Total:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.5; 
        campoQuantidade = new JTextField();
        card.add(campoQuantidade, gbc);

        gbc.gridx = 2; gbc.gridy = 3; gbc.weightx = 0; card.add(new JLabel("Valor Unitário (R$):"), gbc);
        gbc.gridx = 3; gbc.gridy = 3; gbc.weightx = 0.5; 
        campoValorUnitario = new JTextField();
        card.add(campoValorUnitario, gbc);

        // --- BOTÕES DA SEÇÃO DE FORMULÁRIO ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.setBackground(Color.WHITE);
        
        JButton btnAdd = new JButton("Adicionar Item");
        btnAdd.setBackground(new Color(60, 179, 113)); 
        btnAdd.setForeground(Color.WHITE);
        btnAdd.addActionListener(e -> adicionarItem());

        JButton btnRemove = new JButton("Remover Item");
        btnRemove.setBackground(new Color(220, 53, 69)); 
        btnRemove.setForeground(Color.WHITE);
        btnRemove.addActionListener(e -> removerItem());

        panelBotoes.add(btnAdd);
        panelBotoes.add(btnRemove);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        gbc.insets = new Insets(15, 10, 10, 10);
        card.add(panelBotoes, gbc);

        return card;
    }

    private JPanel criarCardTabela() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Itens do Pedido");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        card.add(titulo, BorderLayout.NORTH);

        // Tabela
        itensModel = new DefaultTableModel(new Object[]{
                "Produto", "Quantidade", "Valor Unitário", "Subtotal", "Produtores"
        }, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabelaItens = new JTable(itensModel);
        tabelaItens.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabelaItens);
        card.add(scroll, BorderLayout.CENTER);

        // --- NOVO PAINEL DE AÇÃO ABAIXO DA TABELA ---
        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAcoes.setBackground(Color.WHITE);

        JButton btnDistribuir = new JButton("Distribuir (Produtores)");
        btnDistribuir.addActionListener(e -> abrirDialogoDistribuicao());
        
        panelAcoes.add(btnDistribuir);
        card.add(panelAcoes, BorderLayout.SOUTH);

        return card;
    }

    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(BG);
        JButton btnSalvar = new JButton("Salvar Pedido");
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalvar.addActionListener(e -> salvarPedido());
        rodape.add(btnSalvar);
        return rodape;
    }
    
    // --- LÓGICA DE DISTRIBUIÇÃO (MANTIDA IGUAL) ---
    private void abrirDialogoDistribuicao() {
        int row = tabelaItens.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela abaixo para distribuir.");
            return;
        }

        ItemPedidoVO itemSelecionado = itensPedido.get(row);
        
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Distribuir Item", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(600, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Produtor"));
        
        JComboBox<Object> comboProdutores = new JComboBox<>();
        List<AssociadoDAO.Associado> produtores = new AssociadoDAO().listarTodos(); 
        produtores.forEach(comboProdutores::addItem);

        JTextField txtQtd = new JTextField(8);
        JButton btnAdd = new JButton("Adicionar");

        formPanel.add(new JLabel("Produtor:"));
        formPanel.add(comboProdutores);
        formPanel.add(new JLabel("Qtd:"));
        formPanel.add(txtQtd);
        formPanel.add(btnAdd);

        DefaultTableModel modelDist = new DefaultTableModel(new Object[]{"Produtor", "Qtd"}, 0);
        JTable tableDist = new JTable(modelDist);
        
        for(AssociadoItemPedidoVO vo : itemSelecionado.getDistribuicoes()) {
            String nome = "ID " + vo.getAssociadoId();
            for(var p : produtores) if(p.getId().equals(vo.getAssociadoId())) nome = p.getNomeFantasia();
            modelDist.addRow(new Object[]{ nome, vo.getQuantidadeAtribuida() });
        }

        btnAdd.addActionListener(ev -> {
            try {
                AssociadoDAO.Associado prod = (AssociadoDAO.Associado) comboProdutores.getSelectedItem();
                BigDecimal q = new BigDecimal(txtQtd.getText().replace(",", "."));
                
                BigDecimal atual = BigDecimal.ZERO;
                for(var v : itemSelecionado.getDistribuicoes()) atual = atual.add(v.getQuantidadeAtribuida());
                
                if(atual.add(q).compareTo(itemSelecionado.getQuantidadeTotal()) > 0) {
                    JOptionPane.showMessageDialog(dialog, "Soma excede o total do item!");
                    return;
                }

                AssociadoItemPedidoVO dist = new AssociadoItemPedidoVO();
                dist.setAssociadoId(prod.getId());
                dist.setQuantidadeAtribuida(q);
                
                itemSelecionado.getDistribuicoes().add(dist);
                modelDist.addRow(new Object[]{ prod.getNomeFantasia(), q });
                txtQtd.setText("");
                
                tabelaItens.setValueAt(itemSelecionado.getDistribuicoes().size() + " produtores", row, 4);

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Valor inválido.");
            }
        });

        JButton btnOk = new JButton("Concluir");
        btnOk.addActionListener(ev -> dialog.dispose());
        JPanel pnlOk = new JPanel(); pnlOk.add(btnOk);

        dialog.add(formPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(tableDist), BorderLayout.CENTER);
        dialog.add(pnlOk, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // ---------------- MÉTODOS DE NEGÓCIO ----------------

    private void atualizarPreco() {
        Object item = comboProduto.getSelectedItem();
        if (item instanceof ProdutoDAO.Produto) {
            ProdutoDAO.Produto p = (ProdutoDAO.Produto) item;
            if (p.getPreco() != null) {
                campoValorUnitario.setText(p.getPreco().toString());
            } else {
                campoValorUnitario.setText("0.00");
            }
        }
    }

    private void carregarAssociados() {
        var lista = new AssociadoDAO().listarTodos();
        comboAssociado.removeAllItems();
        for (var a : lista) comboAssociado.addItem(a);
    }

    private void configurarProdutor() {
        comboAssociado.removeAllItems();
        var dao = new AssociadoDAO();
        var todos = dao.listarTodos();
        
        // REFATORADO: Obtendo ID diretamente da Sessão oficial
        Long idSessao = Sessao.getAssociadoIdLogado();

        Object selecionado = null;
        for (var a : todos) {
            if (a.getId().equals(idSessao)) {
                selecionado = a;
                break;
            }
        }
        if (selecionado != null) {
            comboAssociado.addItem(selecionado);
            comboAssociado.setSelectedItem(selecionado);
        }
        comboAssociado.setEnabled(false);
        comboProjeto.removeAllItems();
        comboProjeto.addItem("Sem projeto");
        comboProjeto.setEnabled(false);
    }

    private void carregarProdutos() {
        var lista = new ProdutoDAO().listarTodos();
        comboProduto.removeAllItems();
        for (var p : lista) comboProduto.addItem(p);
        if (!lista.isEmpty()) atualizarPreco();
    }

    private void carregarProjetos() {
        var lista = new ProjetoDAO().listarTodos();
        comboProjeto.removeAllItems();
        comboProjeto.addItem("Sem projeto");
        for (var p : lista) comboProjeto.addItem(p.getNomeProjeto());
    }

    private void adicionarItem() {
        try {
            Object itemSelecionado = comboProduto.getSelectedItem();
            if (itemSelecionado == null || !(itemSelecionado instanceof ProdutoDAO.Produto)) {
                JOptionPane.showMessageDialog(this, "Selecione um produto válido.");
                return;
            }
            ProdutoDAO.Produto prod = (ProdutoDAO.Produto) itemSelecionado;
            String qtdStr = campoQuantidade.getText().replace(",", ".");
            String valStr = campoValorUnitario.getText().replace(",", ".");
            
            if(qtdStr.isEmpty() || valStr.isEmpty()) return;

            BigDecimal qtd = new BigDecimal(qtdStr);
            BigDecimal unit = new BigDecimal(valStr);
            BigDecimal subtotal = qtd.multiply(unit);

            ItemPedidoVO item = new ItemPedidoVO();
            item.setProdutoId(prod.getId());
            item.setQuantidadeTotal(qtd);
            item.setValorUnitario(unit);
            item.setValorTotal(subtotal);
            item.setDistribuicoes(new ArrayList<>()); 

            itensPedido.add(item);
            itensModel.addRow(new Object[]{
                    prod.getNome(), qtd, unit, subtotal, "Ninguém"
            });
            totalPedido = totalPedido.add(subtotal);
            campoQuantidade.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valores inválidos.");
        }
    }

    private void removerItem() {
        int row = tabelaItens.getSelectedRow();
        if (row == -1) return;
        ItemPedidoVO item = itensPedido.get(row);
        totalPedido = totalPedido.subtract(item.getValorTotal());
        itensPedido.remove(row);
        itensModel.removeRow(row);
    }

    private void voltarParaHome() {
        try {
            // REFATORADO: Lógica de retorno baseada no tipo numérico
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            if (usuario.usuarioVO.getTipoUsuario() == TIPO_ASSOCIACAO) {
                new AssociacaoMainView().setVisible(true);
            } else {
                new ProdutorMainView().setVisible(true);
            }
            dispose();
        } catch (Exception e) {
            dispose();
        }
    }

    private void salvarPedido() {
        if (itensPedido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione itens ao pedido.");
            return;
        }

        try {
            PedidoVO pedido = new PedidoVO();
            Long associadoId;
            Long projetoId = null;

            // REFATORADO: Lógica de obtenção do ID do Associado
            UsuarioTO usuario = Sessao.getUsuarioLogado();
            
            // Se NÃO for associação (ou seja, é produtor), pega o ID da sessão
            if (usuario.usuarioVO.getTipoUsuario() != TIPO_ASSOCIACAO) {
                associadoId = Sessao.getAssociadoIdLogado();
            } else {
                // Se for associação, pega o ID do combobox
                AssociadoDAO.Associado assoc = (AssociadoDAO.Associado) comboAssociado.getSelectedItem();
                if (assoc == null) return;
                associadoId = assoc.getId();
                
                String selProj = (String) comboProjeto.getSelectedItem();
                if (selProj != null && !"Sem projeto".equals(selProj)) {
                    var projeto = new ProjetoDAO().listarTodos().stream()
                            .filter(p -> p.getNomeProjeto().equals(selProj))
                            .findFirst().orElse(null);
                    if (projeto != null) projetoId = projeto.getId();
                }
            }

            pedido.setAssociadoId(associadoId);
            pedido.setProjetoId(projetoId);
            pedido.setDataCriacao(LocalDateTime.now());
            pedido.setStatusPedidoId(1L);
            pedido.setValorTotal(totalPedido);
            
            PedidoDAO dao = new PedidoDAO();
            Long id = dao.salvarPedido(pedido);

            if (id != null) {
                dao.salvarItens(id, itensPedido);
                List<ItemPedidoVO> itensBanco = dao.buscarItensPorPedido(id);
                AssociadoItemPedidoDAO distDao = new AssociadoItemPedidoDAO();

                for(ItemPedidoVO itemMemoria : itensPedido) {
                    for(ItemPedidoVO itemBanco : itensBanco) {
                        if(itemMemoria.getProdutoId().equals(itemBanco.getProdutoId()) &&
                           itemMemoria.getQuantidadeTotal().compareTo(itemBanco.getQuantidadeTotal()) == 0) {
                            
                            for(AssociadoItemPedidoVO dist : itemMemoria.getDistribuicoes()) {
                                dist.setItemPedidoId(itemBanco.getId());
                                distDao.salvarAtribuicao(dist);
                            }
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!");
                voltarParaHome();
                
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar pedido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}