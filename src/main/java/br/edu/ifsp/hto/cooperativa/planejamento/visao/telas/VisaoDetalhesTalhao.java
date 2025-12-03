package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.TalhaoVO;

// Importação do ControleEstoque e EspecieVO do módulo de estoque
import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Vector; // Necessário para o ComboBox


// Crie esta classe dentro do pacote onde for mais apropriado, 
// como br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO ou br.edu.ifsp.hto.cooperativa.planejamento.visao.telas

class EspecieComboItem {
    private final int id;
    private final String nome;

    public EspecieComboItem(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    // Este método é chamado pelo JComboBox para exibir o texto
    @Override
    public String toString() {
        return nome;
    }
}

public class VisaoDetalhesTalhao extends VisaoBase {

    // --- Controladores e Dados ---
    private final PlanejamentoControle controle = new PlanejamentoControle();
    // Adicionado o ControleEstoque para buscar as espécies
    private final int talhaoId;      
    private int areaIdVinculada;     
    private Integer idEmEdicao = null;

    // --- Componentes de Interface ---
    private JTextField txtNomePlano;
    private JTextField txtDescricao;
    // REMOVIDO: private JTextField txtEspecieId;
    // NOVO: JComboBox para selecionar a espécie
    private JComboBox<EspecieComboItem> cmbEspecie;
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
            // Assumindo que VisaoDetalhesArea existe e está importada/disponível
            new VisaoDetalhesArea(this.areaIdVinculada, parent).setVisible(true);
        } else {
            // Assumindo que VisaoAreas existe e está importada/disponível
            new VisaoAreas(parent).setVisible(true);
        }
    }

    // =================================================================================
    // FORMULÁRIO (COM JCOMBOBOX)
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
        txtAreaCultivo = new JTextField(10);
        txtDataInicio = new JTextField(10);
        txtObservacoes = new JTextField(20);
        
        // Inicializando e preenchendo o ComboBox
        cmbEspecie = new JComboBox<>();
        carregarEspeciesNoCombo();

        // --- Layout ---
        // Linha 0
        adicionarCampo(painel, "Nome do Plano:", txtNomePlano, 0, 0, 1);
        adicionarCampo(painel, "Descrição:", txtDescricao, 0, 1, 2);

        // Linha 1
        // Trocado txtEspecieId por cmbEspecie
        adicionarCampo(painel, "Espécie:", cmbEspecie, 1, 0, 1);
        adicionarCampo(painel, "Área Cultivo (ha):", txtAreaCultivo, 1, 1, 1);
        
        // Linha 2
        adicionarCampo(painel, "Data Início (aaaa-mm-dd):", txtDataInicio, 2, 0, 1);
        
        // Linha 3 (Observações)
        adicionarCampo(painel, "Observações:", txtObservacoes, 3, 0, 3); 

        // --- Botões ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4 * 2; // Ajuste para a nova estrutura de GridBagLayout
        gbc.gridwidth = 3; 
        gbc.anchor = GridBagConstraints.EAST; 
        gbc.insets = new Insets(20, 0, 0, 0); 
        painel.add(criarBarraBotoes(), gbc);

        return painel;
    }

    private void carregarEspeciesNoCombo() {
        ControleEstoque controleEstoque = ControleEstoque.getInstance();

        cmbEspecie.removeAllItems();
        try {
            List<EspecieVO> especies = controleEstoque.listarEspecies();
            if (especies != null && !especies.isEmpty()) {
                for (EspecieVO especie : especies) {
                    cmbEspecie.addItem(new EspecieComboItem(especie.getId(), especie.getNome()));
                }
            } else {
                cmbEspecie.addItem(new EspecieComboItem(-1, "Nenhuma Espécie Cadastrada"));
            }
        } catch (Exception e) {
            cmbEspecie.addItem(new EspecieComboItem(-2, "Erro ao Carregar Espécies"));
            JOptionPane.showMessageDialog(this, "Erro ao carregar espécies: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarCampo(JPanel painel, String label, JComponent campo, int linha, int coluna, int largura) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridy = linha * 2; 
        gbc.gridx = coluna * 2; // Ajuste para usar mais colunas para labels/campos
        JLabel lbl = new JLabel(label);
        lbl.setFont(Tema.FONTE_TEXTO);
        painel.add(lbl, gbc);

        gbc.gridy = (linha * 2) + 1; 
        gbc.gridx = coluna * 2;
        gbc.gridwidth = largura * 2; // Aumentar a largura para campos maiores
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
        // Coluna Espécie deve mostrar o ID ou o Nome. Mantive o ID por convenção do modelo.
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

            // Pega a espécie selecionada
            EspecieComboItem especieSelecionada = (EspecieComboItem) cmbEspecie.getSelectedItem();
            
            // Validações
            if (especieSelecionada == null || especieSelecionada.getId() <= 0) {
                throw new Exception("Selecione uma Espécie válida.");
            }
            if (txtNomePlano.getText().isEmpty()) throw new Exception("O Nome do Plano é obrigatório.");
            if (txtDataInicio.getText().isEmpty()) throw new Exception("A Data de Início é obrigatória.");

            // Preenchimento
            plano.setNomePlano(txtNomePlano.getText().trim());
            plano.setDescricao(txtDescricao.getText().trim());
            // Usa o ID do objeto selecionado no ComboBox
            plano.setEspecieId(especieSelecionada.getId()); 
            
            // Validação de AreaCultivo
            float areaCultivo = 0.0f;
            try {
                areaCultivo = Float.parseFloat(txtAreaCultivo.getText().trim());
                plano.setAreaCultivo(areaCultivo);
            } catch (NumberFormatException e) {
                 throw new Exception("Área Cultivo deve ser um número válido.");
            }
            
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
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos (Área)!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        
        List<PlanoVO> lista = controle.buscarPlanosDoTalhao(this.talhaoId);
        
        if (lista != null) {
            for (PlanoVO p : lista) {
                String nomeEspecie = null;                

                try {
                    nomeEspecie = ControleEstoque.getInstance().buscarEspeciePorId(p.getEspecieId()).getNome();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Removido p.getDataFim() da linha
                modeloTabela.addRow(new Object[]{ 
                    p.getId(), 
                    p.getNomePlano(), 
                    nomeEspecie == null ? p.getEspecieId() : nomeEspecie, // Mantém o ID na tabela
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
            txtAreaCultivo.setText(String.valueOf(plano.getAreaCultivo()));
            txtObservacoes.setText(plano.getObservacoes());
            
            // Lógica para selecionar o item correto no ComboBox
            selecionarEspecieNoCombo(plano.getEspecieId());
            
            txtDataInicio.setText(plano.getDataInicio() != null ? plano.getDataInicio().toString() : "");
        }
    }
    
    /**
     * Seleciona a EspecieComboItem no JComboBox que corresponde ao especieId.
     * @param especieId ID da espécie a ser selecionada.
     */
    private void selecionarEspecieNoCombo(int especieId) {
        ComboBoxModel<EspecieComboItem> model = cmbEspecie.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            EspecieComboItem item = model.getElementAt(i);
            if (item.getId() == especieId) {
                cmbEspecie.setSelectedItem(item);
                return;
            }
        }
        // Se não encontrar, limpa a seleção
        cmbEspecie.setSelectedIndex(-1);
    }

    private void limparFormulario() {
        idEmEdicao = null;
        txtNomePlano.setText("");
        txtDescricao.setText("");
        txtAreaCultivo.setText("");
        txtDataInicio.setText("");
        txtObservacoes.setText("");
        cmbEspecie.setSelectedIndex(-1); // Limpa a seleção do ComboBox
        tabelaPlanos.clearSelection();
    }
}