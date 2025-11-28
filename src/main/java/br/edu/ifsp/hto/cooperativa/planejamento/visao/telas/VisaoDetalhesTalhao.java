package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AreaVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.TalhaoVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class VisaoDetalhesTalhao extends VisaoBase {

    // --- Controladores e Dados ---
    private final PlanejamentoControle controle = new PlanejamentoControle();
    private final int talhaoId;      
    private int areaIdVinculada;     
    private Integer idEmEdicao = null;

    // --- Componentes de Interface ---
    private JTextField txtNomePlano;
    private JTextField txtDescricao;
    private JTextField txtEspecieId;
    private JTextField txtAreaCultivo;
    private JTextField txtDataInicio; // Apenas Data Início agora
    private JTextField txtObservacoes;

    private JTable tabelaPlanos;
    private DefaultTableModel modeloTabela;

    private JDesktopPane parent;

    public VisaoDetalhesTalhao(int talhaoId, JDesktopPane parent) {
        super("Talhão: ", parent);
        this.parent = parent;
        parent.add(this);
        this.talhaoId = talhaoId;
        TalhaoVO talhao = controle.buscarTalhaoPorId(this.talhaoId);
        String nome = talhao.getNome();
        setTitulo("Talhão: " + nome);
        
        carregarInformacoesTalhao(); 
        carregarDados();             
    }

    private void carregarInformacoesTalhao() {
        TalhaoVO talhao = controle.buscarTalhaoPorId(this.talhaoId);
        if (talhao != null) {
            setTitle("Gerenciamento de Planos - Talhão: " + talhao.getNome());
            this.areaIdVinculada = talhao.getAreaId(); 
        } else {
            setTitle("Gerenciamento de Planos (Talhão não encontrado)");
            this.areaIdVinculada = -1; 
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
        JButton btnVoltar = new JButton("Voltar para Lista de Talhões");
        btnVoltar.addActionListener(e -> voltarParaTalhoes());
        
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSul.setBackground(Tema.COR_FUNDO);
        painelSul.add(btnVoltar);
        
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);

        return painelPrincipal;
    }

    private void voltarParaTalhoes() {
        this.dispose(); 
        if (this.areaIdVinculada != -1) {
            new VisaoDetalhesArea(this.areaIdVinculada, parent).setVisible(true);
        } else {
            new VisaoAreas(parent).setVisible(true);
        }
    }

    // =================================================================================
    // FORMULÁRIO (SEM DATA FIM)
    // =================================================================================

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Tema.COR_BRANCA);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));

        // Inicializando campos
        txtNomePlano = new JTextField(20);
        txtDescricao = new JTextField(20);
        txtEspecieId = new JTextField(10);
        txtAreaCultivo = new JTextField(10);
        txtDataInicio = new JTextField(10);
        txtObservacoes = new JTextField(20);

        // --- Layout ---
        // Linha 0
        adicionarCampo(painel, "Nome do Plano:", txtNomePlano, 0, 0, 1);
        adicionarCampo(painel, "Descrição:", txtDescricao, 0, 1, 2);

        // Linha 1
        adicionarCampo(painel, "ID Espécie:", txtEspecieId, 1, 0, 1);
        adicionarCampo(painel, "Área Cultivo (ha):", txtAreaCultivo, 1, 1, 1);
        
        // Linha 2
        adicionarCampo(painel, "Data Início (aaaa-mm-dd):", txtDataInicio, 2, 0, 1);
        // Data Fim removida daqui
        
        // Linha 3 (Observações agora ocupa tudo ou pode subir, mantive aqui para organização)
        adicionarCampo(painel, "Observações:", txtObservacoes, 3, 0, 3); 

        // --- Botões ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4; 
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

        JButton btnSalvar = new JButton("Salvar Plano");
        btnSalvar.setBackground(Tema.COR_PRIMARIA);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSalvar.addActionListener(e -> salvar());

        box.add(btnLimpar);
        box.add(btnSalvar);
        return box;
    }

    // =================================================================================
    // TABELA (SEM DATA FIM)
    // =================================================================================

    private JScrollPane criarPainelTabela() {
        // Removida coluna "Fim"
        String[] colunas = { "ID", "Nome", "Espécie", "Início", "Área", "Obs" };
        
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaPlanos = new JTable(modeloTabela);
        tabelaPlanos.setRowHeight(25);
        tabelaPlanos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabelaPlanos.getTableHeader().setBackground(Tema.COR_SECUNDARIA);
        tabelaPlanos.getTableHeader().setForeground(Tema.COR_TEXTO);
        tabelaPlanos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        tabelaPlanos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabelaPlanos.getSelectedRow() != -1) {
                preencherFormularioComSelecao();
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaPlanos);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return scroll;
    }

    // =================================================================================
    // LÓGICA E REGRAS DE NEGÓCIO
    // =================================================================================

    private void salvar() {
        try {
            PlanoVO plano = new PlanoVO();

            // Validações
            if (txtNomePlano.getText().isEmpty()) throw new Exception("O Nome do Plano é obrigatório.");
            if (txtEspecieId.getText().isEmpty()) throw new Exception("O ID da Espécie é obrigatório.");
            if (txtDataInicio.getText().isEmpty()) throw new Exception("A Data de Início é obrigatória.");

            // Preenchimento
            plano.setNomePlano(txtNomePlano.getText().trim());
            plano.setDescricao(txtDescricao.getText().trim());
            plano.setEspecieId(Integer.parseInt(txtEspecieId.getText().trim()));
            plano.setAreaCultivo(Float.parseFloat(txtAreaCultivo.getText().trim()));
            plano.setObservacoes(txtObservacoes.getText().trim());
            
            // Tratamento de Data Início
            try {
                plano.setDataInicio(Date.valueOf(txtDataInicio.getText().trim()));
            } catch (IllegalArgumentException e) {
                throw new Exception("Data inválida! Use o formato aaaa-mm-dd (ex: 2024-12-31)");
            }
            
            // VINCULAÇÃO
            plano.setTalhaoId(this.talhaoId);

            if (idEmEdicao == null) {
                controle.inserir(plano);
                JOptionPane.showMessageDialog(this, "Plano cadastrado com sucesso!");
            } else {
                plano.setId(idEmEdicao);
                controle.atualizar(plano);
                JOptionPane.showMessageDialog(this, "Plano atualizado com sucesso!");
            }

            limparFormulario();
            carregarDados();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos (IDs, Área)!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        
        List<PlanoVO> lista = controle.buscarPlanosDoTalhao(this.talhaoId);
        
        if (lista != null) {
            for (PlanoVO p : lista) {
                // Removido p.getDataFim() da linha
                modeloTabela.addRow(new Object[]{ 
                    p.getId(), 
                    p.getNomePlano(), 
                    p.getEspecieId(), 
                    p.getDataInicio(), 
                    p.getAreaCultivo(), 
                    p.getObservacoes() 
                });
            }
        }
    }

    private void preencherFormularioComSelecao() {
        int linha = tabelaPlanos.getSelectedRow();
        int id = (Integer) modeloTabela.getValueAt(linha, 0);
        
        PlanoVO plano = controle.buscarPlanoPorId(id);
        
        if (plano != null) {
            idEmEdicao = plano.getId();
            txtNomePlano.setText(plano.getNomePlano());
            txtDescricao.setText(plano.getDescricao());
            txtEspecieId.setText(String.valueOf(plano.getEspecieId()));
            txtAreaCultivo.setText(String.valueOf(plano.getAreaCultivo()));
            txtObservacoes.setText(plano.getObservacoes());
            
            txtDataInicio.setText(plano.getDataInicio() != null ? plano.getDataInicio().toString() : "");
            // Removido o setText da Data Fim
        }
    }

    private void limparFormulario() {
        idEmEdicao = null;
        txtNomePlano.setText("");
        txtDescricao.setText("");
        txtEspecieId.setText("");
        txtAreaCultivo.setText("");
        txtDataInicio.setText("");
        // Removido limpar Data Fim
        txtObservacoes.setText("");
        tabelaPlanos.clearSelection();
    }
}