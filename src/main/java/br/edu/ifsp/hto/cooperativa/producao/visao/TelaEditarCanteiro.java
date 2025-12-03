package br.edu.ifsp.hto.cooperativa.producao.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifsp.hto.cooperativa.producao.controle.GerenciarAreaController;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.CanteiroDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.OrdemProducaoDAO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;


public class TelaEditarCanteiro extends JFrame {

    private CanteiroVO canteiroAtual;
    private OrdemProducaoVO ordemAtual;
    private Long areaId;
    private GerenciarAreaController controller;

    // Campos do Canteiro
    private JTextField txtNome;
    private JTextField txtArea;
    private JComboBox<String> comboStatus;

    public TelaEditarCanteiro(CanteiroVO canteiro, Long areaId) {
        this.canteiroAtual = canteiro;
        this.areaId = areaId;
        this.controller = new GerenciarAreaController();
        
        // Carregar ordem de produção associada
        carregarOrdemProducao();

        setTitle("Editar Canteiro");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Fecha só a janela, não o app
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= CORES =======
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // ======= NAVBAR (Simulada ou Importada) =======
        // Se NavBarSuperior for um JPanel, pode adicionar:
        try {
            add(new NavBarSuperior(), BorderLayout.NORTH);
        } catch(Exception e) {
            // Caso não tenha a classe compilada no momento
            JPanel navPlaceholder = new JPanel();
            navPlaceholder.setBackground(verdeEscuro);
            navPlaceholder.setPreferredSize(new Dimension(1200, 60));
            add(navPlaceholder, BorderLayout.NORTH);
        }

        // ======= CONTEÚDO PRINCIPAL =======
        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        // ======= TOPO =======
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 50));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.addActionListener(e -> dispose()); // Fecha a janela
        painelTopo.add(btnVoltar, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Editar Canteiro: " + canteiro.getNome());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(verdeEscuro);
        painelTopo.add(lblTitulo, BorderLayout.EAST);

        conteudo.add(painelTopo, BorderLayout.NORTH);

        // ======= FORMULÁRIO =======
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        Font fonteCampo = new Font("Arial", Font.PLAIN, 16);

        // Campo Nome
        gbc.gridy++;
        painelForm.add(criarLabel("Nome do Canteiro:", verdeEscuro), gbc);
        gbc.gridy++;
        txtNome = criarCampo(fonteCampo, verdeEscuro);
        txtNome.setText(canteiro.getNome());
        painelForm.add(txtNome, gbc);

        // Campo Área
        gbc.gridy++;
        painelForm.add(criarLabel("Área (m²):", verdeEscuro), gbc);
        gbc.gridy++;
        txtArea = criarCampo(fonteCampo, verdeEscuro);
        txtArea.setText(String.valueOf(canteiro.getAreaCanteiroM2()));
        painelForm.add(txtArea, gbc);

        // Campo Status
        gbc.gridy++;
        painelForm.add(criarLabel("Status:", verdeEscuro), gbc);
        gbc.gridy++;
        comboStatus = new JComboBox<>(new String[]{"crescendo", "colheita", "finalizado", "problema"});
        comboStatus.setFont(fonteCampo);
        comboStatus.setSelectedItem(canteiro.getStatus());
        painelForm.add(comboStatus, gbc);

        // Botão Salvar
        gbc.gridy++;
        JButton btnSalvar = new JButton("SALVAR ALTERAÇÕES");
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setPreferredSize(new Dimension(250, 50));
        
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        
        painelForm.add(btnSalvar, gbc);

        conteudo.add(new JScrollPane(painelForm), BorderLayout.CENTER);
    }

    private void carregarOrdemProducao() {
        if (canteiroAtual != null && canteiroAtual.getOrdemProducaoId() != null) {
            try (Connection conn = ConnectionFactory.getConnection()) {
                OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
                this.ordemAtual = ordemDAO.buscarPorId(canteiroAtual.getOrdemProducaoId());
            } catch (Exception ex) {
                System.err.println("Erro ao carregar ordem de produção: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void salvarAlteracoes() {
        try {
            // Validar campos obrigatórios
            if (txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Nome do canteiro é obrigatório!", 
                    "Validação", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Atualizar dados do canteiro
            canteiroAtual.setNome(txtNome.getText().trim());
            BigDecimal novaArea = new BigDecimal(txtArea.getText().replace(",", "."));
            canteiroAtual.setAreaCanteiroM2(novaArea);
            canteiroAtual.setStatus((String) comboStatus.getSelectedItem());
            
            // Recalcular Kg automaticamente com base na área e espécie
            BigDecimal novoKg = BigDecimal.ZERO;
            if (ordemAtual != null && ordemAtual.getEspecieId() != null) {
                br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque controleEstoque = 
                    br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque.getInstance();
                float kgCalculado = controleEstoque.calcularQuantidade(
                    ordemAtual.getEspecieId().intValue(), 
                    novaArea.floatValue()
                );
                novoKg = BigDecimal.valueOf(kgCalculado);
            }
            canteiroAtual.setKgGerados(novoKg);

            // Salvar canteiro via DAO
            CanteiroDAO canteiroDAO = new CanteiroDAO();
            canteiroDAO.atualizar(canteiroAtual);
            
            // Atualizar ordem de produção com os mesmos valores de área e kg
            if (ordemAtual != null) {
                ordemAtual.setAreaCultivo(novaArea.doubleValue());
                ordemAtual.setQuantidadeKg(novoKg.doubleValue());
                
                // Salvar ordem de produção via DAO
                try (Connection conn = ConnectionFactory.getConnection()) {
                    OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
                    ordemDAO.atualizar(ordemAtual);
                }
            }

            JOptionPane.showMessageDialog(this, 
                "Canteiro atualizado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Recarregar a tela do canteiro com dados atualizados
            recarregarTelaCanteiro();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro: Valores numéricos inválidos! Verifique os campos de área e kg.", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao salvar alterações: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void recarregarTelaCanteiro() {
        try {
            if (areaId != null && canteiroAtual != null) {
                // Buscar dados atualizados
                CanteiroDAO canteiroDAO = new CanteiroDAO();
                CanteiroVO canteiroAtualizado = canteiroDAO.buscarPorId(canteiroAtual.getId().intValue());
                
                OrdemProducaoVO ordemAtualizada = null;
                if (canteiroAtualizado.getOrdemProducaoId() != null) {
                    try (Connection conn = ConnectionFactory.getConnection()) {
                        OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO(conn);
                        ordemAtualizada = ordemDAO.buscarPorId(canteiroAtualizado.getOrdemProducaoId());
                    }
                }
                
                // Preparar dados para TelaCanteiro
                String cultura = ordemAtualizada != null && ordemAtualizada.getNomeEspecie() != null 
                    ? ordemAtualizada.getNomeEspecie() 
                    : (ordemAtualizada != null && ordemAtualizada.getNomePlano() != null ? ordemAtualizada.getNomePlano() : "");
                String titulo = canteiroAtualizado.getNome();
                double areaM2 = canteiroAtualizado.getAreaCanteiroM2() != null ? canteiroAtualizado.getAreaCanteiroM2().doubleValue() : 0.0;
                double qtdKg = canteiroAtualizado.getKgGerados() != null ? canteiroAtualizado.getKgGerados().doubleValue() : 0.0;
                java.util.Date inicio = ordemAtualizada != null ? ordemAtualizada.getDataExecucao() : null;
                
                // Fechar esta tela
                dispose();
                
                // Fechar a tela de canteiro anterior (se houver)
                for (java.awt.Window window : java.awt.Window.getWindows()) {
                    if (window instanceof TelaCanteiro && window.isVisible()) {
                        window.dispose();
                    }
                }
                
                // Abrir nova tela de canteiro com dados atualizados
                TelaCanteiro telaCanteiro = new TelaCanteiro(cultura, titulo, inicio, areaM2, qtdKg, canteiroAtual.getId(), areaId);
                telaCanteiro.setVisible(true);
            } else {
                dispose();
            }
        } catch (Exception ex) {
            System.err.println("Erro ao recarregar tela do canteiro: " + ex.getMessage());
            ex.printStackTrace();
            dispose();
        }
    }

    private JLabel criarLabel(String texto, Color cor) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(cor);
        return lbl;
    }

    private JTextField criarCampo(Font fonte, Color borda) {
        JTextField txt = new JTextField();
        txt.setFont(fonte);
        txt.setPreferredSize(new Dimension(350, 40));
        txt.setBorder(BorderFactory.createLineBorder(borda, 1));
        return txt;
    }
}