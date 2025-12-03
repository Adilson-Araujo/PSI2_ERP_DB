package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;
import br.edu.ifsp.hto.cooperativa.sessao.controlador.SessaoControlador;
// IMPORTS DO SEU BACKEND
import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.MaterialVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisaoMateriais extends VisaoBase {

    // --- Controladores e Dados ---
    // Reutiliza o controle existente, que já possui métodos para MaterialDAO
    private final PlanejamentoControle controle = new PlanejamentoControle();
    private Integer idEmEdicao = null;
    private final int associadoId; // Armazena o ID do associado logado

    // --- Componentes de Interface ---
    private JTextField txtNome;
    private JTextField txtQuantidade;
    private JTextField txtUnidadeMedida;
    private JTable tabelaMateriais;
    private DefaultTableModel modeloTabela;

    public VisaoMateriais(JDesktopPane parent) {
        super("Gerenciamento de Materiais", parent);
        this.parent = parent;
        
        // Obtém o ID do associado logado
        SessaoControlador sc = new SessaoControlador();
        this.associadoId = sc.obterUsuarioLogado().associadoTO.associado.getId().intValue();
        
        parent.add(this);
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
    // FORMULÁRIO
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
        txtQuantidade = new JTextField(10);
        txtUnidadeMedida = new JTextField(10);

        // Adicionando campos usando o método auxiliar
        // Linha 0: Nome
        adicionarCampo(painel, "Nome do Material:", txtNome, 0, 0, 2); 
        
        // Linha 1: Quantidade e Unidade de Medida
        adicionarCampo(painel, "Quantidade em Estoque:", txtQuantidade, 1, 0, 1);
        adicionarCampo(painel, "Unidade de Medida (Ex: kg, L, un):", txtUnidadeMedida, 1, 1, 1);

        // Adicionando a Barra de Botões
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4; // Posição para a barra de botões
        gbc.gridwidth = 3; 
        gbc.anchor = GridBagConstraints.EAST; 
        gbc.insets = new Insets(20, 0, 0, 0); 
        painel.add(criarBarraBotoes(), gbc);

        return painel;
    }

    /**
     * Auxiliar para adicionar Label e Campo (2 linhas por campo: Label e Input)
     */
    private void adicionarCampo(JPanel painel, String label, JComponent campo, int linha, int coluna, int largura) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Label
        gbc.gridy = linha * 2; 
        gbc.gridx = coluna;
        JLabel lbl = new JLabel(label);
        lbl.setFont(Tema.FONTE_TEXTO);
        painel.add(lbl, gbc);

        // Campo de Texto
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
    // TABELA
    // =================================================================================

    private JScrollPane criarPainelTabela() {
        String[] colunas = { "ID", "Nome", "Quantidade", "Unidade de Medida" };
        
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaMateriais = new JTable(modeloTabela);
        tabelaMateriais.setRowHeight(25);
        tabelaMateriais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabelaMateriais.getTableHeader().setBackground(Tema.COR_SECUNDARIA);
        tabelaMateriais.getTableHeader().setForeground(Tema.COR_TEXTO);
        tabelaMateriais.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        // Listener de Seleção para preencher formulário
        tabelaMateriais.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabelaMateriais.getSelectedRow() != -1) {
                preencherFormularioComSelecao();
            }
        });
        
        JScrollPane scroll = new JScrollPane(tabelaMateriais);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return scroll;
    }

    // =================================================================================
    // LÓGICA E REGRAS DE NEGÓCIO
    // =================================================================================

    private void salvar() {
        try {
            MaterialVO material = new MaterialVO();
            
            // Validações
            if (txtNome.getText().isEmpty()) throw new Exception("O Nome do Material é obrigatório.");
            if (txtQuantidade.getText().isEmpty()) throw new Exception("A Quantidade é obrigatória.");
            if (txtUnidadeMedida.getText().isEmpty()) throw new Exception("A Unidade de Medida é obrigatória.");

            // Popula o VO
            material.setNome(txtNome.getText().trim());
            material.setUnidadeMedida(txtUnidadeMedida.getText().trim());
            material.setAssociadoId(this.associadoId); // Atribui o ID do associado logado
            
            // Tenta converter os valores numéricos
            material.setQuantidade(Float.parseFloat(txtQuantidade.getText().trim().replace(",", ".")));

            if (idEmEdicao == null) {
                // Inserção
                controle.inserir(material); // Usa o método inserir(MaterialVO) do PlanejamentoControle
                JOptionPane.showMessageDialog(this, "Material cadastrado com sucesso!");
            } else {
                // Atualização
                material.setId(idEmEdicao);
                controle.atualizar(material); // Usa o método atualizar(MaterialVO) do PlanejamentoControle
                JOptionPane.showMessageDialog(this, "Material atualizado com sucesso!");
            }

            limparFormulario();
            carregarDados();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos (Quantidade)!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
        
        // O PlanejamentoControle não tem um método para buscar materiais POR ASSOCIADO,
        // mas usaremos o listarMateriais() por enquanto.
        // Se houver um MaterialDAO::buscarMateriaisDoAssociado(int), ele seria mais apropriado aqui.
        // Já que não foi fornecido, usarei o listarMateriais() para listar todos e filtrar na tela 
        // (OU ASSUMO que o listarMateriais() já retorna apenas os do associado, a depender da sua implementação no DAO).
        // Pela estrutura do seu código, vou assumir a listagem universal por enquanto, já que não temos o código DAO.
        
        // ATENÇÃO: Se o seu MaterialDAO tiver um método específico para o associado, use-o aqui.
        // Exemplo: List<MaterialVO> lista = controle.buscarMateriaisDoAssociado(associadoId);
        List<MaterialVO> listaCompleta = controle.listarMateriais(); // Lista todos

        if (listaCompleta != null) {
            for (MaterialVO m : listaCompleta) {
                // Filtrando APENAS os materiais do associado logado (necessário se listarMateriais for global)
                if (m.getAssociadoId() == this.associadoId) { 
                     modeloTabela.addRow(new Object[]{ 
                        m.getId(), 
                        m.getNome(), 
                        m.getQuantidade(), 
                        m.getUnidadeMedida()
                    });
                }
            }
        }
    }

    private void preencherFormularioComSelecao() {
        int linha = tabelaMateriais.getSelectedRow();
        if (linha == -1) return; // Garante que há uma linha selecionada

        idEmEdicao = (Integer) modeloTabela.getValueAt(linha, 0);
        
        txtNome.setText(modeloTabela.getValueAt(linha, 1).toString());
        // Formata a quantidade para garantir que seja exibida corretamente
        txtQuantidade.setText(modeloTabela.getValueAt(linha, 2).toString().replace(".", ",")); 
        txtUnidadeMedida.setText(modeloTabela.getValueAt(linha, 3).toString());
    }

    private void limparFormulario() {
        idEmEdicao = null;
        txtNome.setText("");
        txtQuantidade.setText("");
        txtUnidadeMedida.setText("");
        tabelaMateriais.clearSelection();
    }
}