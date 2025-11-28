package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AreaVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.TalhaoVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisaoDetalhesArea extends VisaoBase {

    // --- Controladores e Dados ---
    private final PlanejamentoControle controle = new PlanejamentoControle();
    private int areaId; // ID da Área que "donos" desses talhões
    private Integer idEmEdicao = null;

    // --- Componentes de Interface (Baseados no TalhaoVO) ---
    private JTextField txtNome;
    private JTextField txtAreaTalhao;
    private JTextField txtStatus;
    private JTextField txtObservacoes;
    
    private JTable tabelaTalhoes;
    private DefaultTableModel modeloTabela;

    private JDesktopPane parent;

    public VisaoDetalhesArea(int areaId, JDesktopPane parent) {
        super("Area: ", parent);
        this.parent = parent;
        this.areaId = areaId;
        parent.add(this);
        AreaVO area = controle.buscarAreaPorId(this.areaId);
        String nome = area.getNome();
        setTitulo("Área: " + nome);

        
        atualizarTitulo();
        carregarDados();
    }

    private void atualizarTitulo() {
        AreaVO area = controle.buscarAreaPorId(this.areaId);
        if (area != null) {
            setTitle("Gerenciamento de Talhões - Área: " + area.getNome());
        } else {
            setTitle("Gerenciamento de Talhões");
        }
    }

    @Override
    protected JPanel getPainelConteudo() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 20));
        painelPrincipal.setBackground(Tema.COR_FUNDO);

        // 1. Formulário
        painelPrincipal.add(criarPainelFormulario(), BorderLayout.NORTH);

        // 2. Tabela
        painelPrincipal.add(criarPainelTabela(), BorderLayout.CENTER);
        
        // 3. Botão Voltar
        JButton btnVoltar = new JButton("Voltar para Lista de Áreas");
        btnVoltar.addActionListener(e -> abrirAreas());
        
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSul.setBackground(Tema.COR_FUNDO);
        painelSul.add(btnVoltar);
        
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);

        return painelPrincipal;
    }

    // =================================================================================
    // FORMULÁRIO
    // =================================================================================

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Tema.COR_BRANCA);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));

        // Inicializando os campos
        txtNome = new JTextField(20);
        txtAreaTalhao = new JTextField(10);
        txtStatus = new JTextField(15);
        txtObservacoes = new JTextField(20);

        // --- Layout dos Campos ---
        // Linha 0
        adicionarCampo(painel, "Nome do Talhão:", txtNome, 0, 0, 1);
        adicionarCampo(painel, "Tamanho (ha):", txtAreaTalhao, 0, 1, 1);
        
        // Linha 1
        adicionarCampo(painel, "Status:", txtStatus, 1, 0, 1);
        adicionarCampo(painel, "Observações:", txtObservacoes, 1, 1, 1);

        // --- Botões ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4; // Abaixo dos campos
        gbc.gridwidth = 3; 
        gbc.anchor = GridBagConstraints.EAST; 
        gbc.insets = new Insets(20, 0, 0, 0); 
        painel.add(criarBarraBotoes(), gbc);

        return painel;
    }

    private void adicionarCampo(JPanel painel, String label, JComponent campo, int linha, int coluna, int largura) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridy = linha * 2; 
        gbc.gridx = coluna;
        JLabel lbl = new JLabel(label);
        lbl.setFont(Tema.FONTE_TEXTO);
        painel.add(lbl, gbc);

        gbc.gridy = (linha * 2) + 1; 
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0; 
        painel.add(campo, gbc);
    }

    private JPanel criarBarraBotoes() {
        JPanel box = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        box.setOpaque(false);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(Color.LIGHT_GRAY);
        btnLimpar.addActionListener(e -> limparFormulario());

        JButton btnSalvar = new JButton("Salvar Talhão");
        btnSalvar.setBackground(Tema.COR_PRIMARIA);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSalvar.addActionListener(e -> salvar());

        box.add(btnLimpar);
        box.add(btnSalvar);
        return box;
    }

    // =================================================================================
    // TABELA
    // =================================================================================

    private JScrollPane criarPainelTabela() {
        String[] colunas = { "ID", "Nome", "Tamanho (ha)", "Status", "Observações" };
        
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaTalhoes = new JTable(modeloTabela);
        tabelaTalhoes.setRowHeight(25);
        tabelaTalhoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabelaTalhoes.getTableHeader().setBackground(Tema.COR_SECUNDARIA);
        tabelaTalhoes.getTableHeader().setForeground(Tema.COR_TEXTO);
        tabelaTalhoes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        // Evento de Seleção
        tabelaTalhoes.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabelaTalhoes.getSelectedRow() != -1) {
                preencherFormularioComSelecao();
            }
        });

        tabelaTalhoes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Se for duplo clique (clickCount == 2) e tiver linha selecionada
                if (e.getClickCount() == 2 && tabelaTalhoes.getSelectedRow() != -1) {
                    abrirDetalhesTalhao();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaTalhoes);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return scroll;
    }

    private void abrirDetalhesTalhao() {
        int linha = tabelaTalhoes.getSelectedRow();
        
        // Pega o ID do Talhão (que está na coluna 0)
        int idTalhao = (Integer) modeloTabela.getValueAt(linha, 0); 
        
        // Fecha a tela atual (Lista de Talhões)
        this.dispose();
        
        // Abre a tela de Planos passando o ID do Talhão
        new VisaoDetalhesTalhao(idTalhao, parent).setVisible(true); 
    }

    // =================================================================================
    // LÓGICA E REGRAS DE NEGÓCIO
    // =================================================================================

    private void salvar() {
        try {
            TalhaoVO talhao = new TalhaoVO();
            
            // Validações
            if (txtNome.getText().isEmpty()) throw new Exception("O Nome do talhão é obrigatório.");
            if (txtAreaTalhao.getText().isEmpty()) throw new Exception("O Tamanho é obrigatório.");

            // Preenchimento com base no VO
            talhao.setNome(txtNome.getText().trim());
            talhao.setAreaTalhao(Float.parseFloat(txtAreaTalhao.getText().trim()));
            talhao.setStatus(txtStatus.getText().trim());
            talhao.setObservacoes(txtObservacoes.getText().trim());
            
            // VINCULAÇÃO: O talhão pertence à área atual
            talhao.setAreaId(this.areaId);

            if (idEmEdicao == null) {
                controle.inserir(talhao);
                JOptionPane.showMessageDialog(this, "Talhão adicionado com sucesso!");
            } else {
                talhao.setId(idEmEdicao);
                controle.atualizar(talhao);
                JOptionPane.showMessageDialog(this, "Talhão atualizado com sucesso!");
            }

            limparFormulario();
            carregarDados();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tamanho deve ser um número válido (use ponto para decimais).", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        
        // Busca apenas os talhões desta área específica
        List<TalhaoVO> lista = controle.buscarTalhoesDaArea(this.areaId);
        
        if (lista != null) {
            for (TalhaoVO t : lista) {
                modeloTabela.addRow(new Object[]{ 
                    t.getId(), 
                    t.getNome(), 
                    t.getAreaTalhao(),
                    t.getStatus(),
                    t.getObservacoes()
                });
            }
        }
    }

    private void preencherFormularioComSelecao() {
        int linha = tabelaTalhoes.getSelectedRow();
        
        idEmEdicao = (Integer) modeloTabela.getValueAt(linha, 0);
        txtNome.setText(modeloTabela.getValueAt(linha, 1).toString());
        txtAreaTalhao.setText(modeloTabela.getValueAt(linha, 2).toString());
        
        Object status = modeloTabela.getValueAt(linha, 3);
        txtStatus.setText(status != null ? status.toString() : "");

        Object obs = modeloTabela.getValueAt(linha, 4);
        txtObservacoes.setText(obs != null ? obs.toString() : "");
    }

    private void limparFormulario() {
        idEmEdicao = null;
        txtNome.setText("");
        txtAreaTalhao.setText("");
        txtStatus.setText("");
        txtObservacoes.setText("");
        tabelaTalhoes.clearSelection();
    }
}