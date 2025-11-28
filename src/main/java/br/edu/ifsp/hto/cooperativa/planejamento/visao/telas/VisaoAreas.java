package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
// IMPORTS DO SEU BACKEND
import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AreaVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter; // Import necessário para o clique do mouse
import java.awt.event.MouseEvent;   // Import necessário para o clique do mouse
import java.util.List;

public class VisaoAreas extends VisaoBase {

    // --- Controladores e Dados ---
    private final PlanejamentoControle controle = new PlanejamentoControle();
    private Integer idEmEdicao = null;

    // --- Componentes de Interface ---
    private JTextField txtNome;
    private JTextField txtPh;
    private JTextField txtAreaTotal;
    private JTextField txtAreaUtilizada;
    private JTable tabelaAreas;
    private DefaultTableModel modeloTabela;

    public VisaoAreas() {
        super("Gerenciamento de Áreas");
        carregarDados();
    }

    @Override
    protected JPanel getPainelConteudo() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 20));
        painelPrincipal.setBackground(Tema.COR_FUNDO);

        painelPrincipal.add(criarPainelFormulario(), BorderLayout.NORTH);
        painelPrincipal.add(criarPainelTabela(), BorderLayout.CENTER);

        return painelPrincipal;
    }

    // =================================================================================
    // FORMULÁRIO (JÁ COM A CORREÇÃO DO LAYOUT)
    // =================================================================================

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Tema.COR_BRANCA);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));

        // Inicializando campos
        txtNome = new JTextField(20);
        txtPh = new JTextField(10);
        txtAreaTotal = new JTextField(10);
        txtAreaUtilizada = new JTextField(10);

        // Adicionando campos
        // Linha 0 e 1
        adicionarCampo(painel, "Nome da Área:", txtNome, 0, 0, 2); 
        adicionarCampo(painel, "pH do Solo:", txtPh, 0, 2, 1);
        
        // Linha 2 e 3
        adicionarCampo(painel, "Área Total (ha):", txtAreaTotal, 1, 0, 1);
        adicionarCampo(painel, "Área Utilizada (ha):", txtAreaUtilizada, 1, 1, 1);

        // Adicionando a Barra de Botões (CORRIGIDO PARA GRIDY = 4)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4; // <--- AGORA ESTÁ NA POSIÇÃO CERTA
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
        painel.add(campo, gbc);
    }

    private JPanel criarBarraBotoes() {
        JPanel box = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        box.setOpaque(false);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(Color.LIGHT_GRAY);
        btnLimpar.addActionListener(e -> limparFormulario());

        JButton btnSalvar = new JButton("Salvar / Atualizar");
        btnSalvar.setBackground(Tema.COR_PRIMARIA);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSalvar.addActionListener(e -> salvar());

        box.add(btnLimpar);
        box.add(btnSalvar);
        return box;
    }

    // =================================================================================
    // TABELA (COM DUPLO CLIQUE)
    // =================================================================================

    private JScrollPane criarPainelTabela() {
        String[] colunas = { "ID", "Nome", "Área Total", "Área Utilizada", "pH" };
        
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaAreas = new JTable(modeloTabela);
        tabelaAreas.setRowHeight(25);
        tabelaAreas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabelaAreas.getTableHeader().setBackground(Tema.COR_SECUNDARIA);
        tabelaAreas.getTableHeader().setForeground(Tema.COR_TEXTO);
        tabelaAreas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        // Listener de Seleção (Um clique para preencher formulário)
        tabelaAreas.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabelaAreas.getSelectedRow() != -1) {
                preencherFormularioComSelecao();
            }
        });

        // --- NOVO: Listener de Mouse para Duplo Clique ---
        tabelaAreas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Verifica se foi duplo clique (clickCount == 2)
                if (e.getClickCount() == 2 && tabelaAreas.getSelectedRow() != -1) {
                    abrirDetalhesArea();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaAreas);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return scroll;
    }

    // Método que abre a nova tela
    private void abrirDetalhesArea() {
        int linha = tabelaAreas.getSelectedRow();
        // Coluna 1 é o Nome (0 é ID, 1 é Nome...)
        int idArea = (int) modeloTabela.getValueAt(linha, 0);
        
        // Fecha a tela atual
        this.dispose();
        
        // Abre a nova tela passando o nome
        new VisaoDetalhesArea(idArea).setVisible(true);
    }

    // =================================================================================
    // LÓGICA E REGRAS DE NEGÓCIO
    // =================================================================================

    private void salvar() {
        try {
            AreaVO area = new AreaVO();
            if (txtNome.getText().isEmpty()) throw new Exception("O Nome é obrigatório.");

            SessaoControlador sc = new SessaoControlador();
            int associadoId = sc.obterUsuarioLogado().associadoTO.associado.getId().intValue();

            area.setNome(txtNome.getText().trim());
            area.setAssociadoId(associadoId);
            area.setAreaTotal(Float.parseFloat(txtAreaTotal.getText().trim()));
            area.setAreaUtilizada(Float.parseFloat(txtAreaUtilizada.getText().trim()));
            area.setPh(Float.parseFloat(txtPh.getText().trim()));

            if (idEmEdicao == null) {
                controle.inserir(area);
                JOptionPane.showMessageDialog(this, "Área cadastrada com sucesso!");
            } else {
                area.setId(idEmEdicao);
                controle.atualizar(area);
                JOptionPane.showMessageDialog(this, "Área atualizada com sucesso!");
            }

            limparFormulario();
            carregarDados();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        SessaoControlador sc = new SessaoControlador();
        int associadoId = sc.obterUsuarioLogado().associadoTO.associado.getId().intValue();
        
        List<AreaVO> lista = controle.buscarAreasDoAssociado(associadoId);
        
        if (lista != null) {
            for (AreaVO a : lista) {
                modeloTabela.addRow(new Object[]{ 
                    a.getId(), 
                    a.getNome(), 
                    a.getAreaTotal(), 
                    a.getAreaUtilizada(), 
                    a.getPh() 
                });
            }
        }
    }

    private void preencherFormularioComSelecao() {
        int linha = tabelaAreas.getSelectedRow();
        idEmEdicao = (Integer) modeloTabela.getValueAt(linha, 0);
        
        txtNome.setText(modeloTabela.getValueAt(linha, 1).toString());
        txtAreaTotal.setText(modeloTabela.getValueAt(linha, 2).toString());
        txtAreaUtilizada.setText(modeloTabela.getValueAt(linha, 3).toString());
        txtPh.setText(modeloTabela.getValueAt(linha, 4).toString());
    }

    private void limparFormulario() {
        idEmEdicao = null;
        txtNome.setText("");
        txtPh.setText("");
        txtAreaTotal.setText("");
        txtAreaUtilizada.setText("");
        tabelaAreas.clearSelection();
    }
}