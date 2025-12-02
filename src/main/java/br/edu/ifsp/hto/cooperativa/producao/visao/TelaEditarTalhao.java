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
import java.util.List;

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
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

public class TelaEditarTalhao extends JFrame {

    private TalhaoVO talhaoAtual;
    private Long areaId;
    private GerenciarAreaController controller;

    private JTextField txtNome;
    private JTextField txtArea;
    private JTextArea txtObservacoes;
    private JComboBox<String> comboStatus;

    public TelaEditarTalhao(TalhaoVO talhao, Long areaId) {
        
        this.talhaoAtual = talhao;
        this.areaId = areaId;
        this.controller = new GerenciarAreaController();

        setTitle("Editar Talhão");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= CORES =======
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // // ======= NAVBAR =======
        // try {
        //     add(new NavBarSuperior(), BorderLayout.NORTH);
        // } catch(Exception e) {
        //     JPanel navPlaceholder = new JPanel();
        //     navPlaceholder.setBackground(verdeEscuro);
        //     add(navPlaceholder, BorderLayout.NORTH);
        // }

        // ======= CONTEÚDO =======
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
        btnVoltar.addActionListener(e -> dispose());
        painelTopo.add(btnVoltar, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Editar Talhão: " + talhao.getNome());
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

        // Nome
        gbc.gridy = 0;
        painelForm.add(criarLabel("Identificação do Talhão:", verdeEscuro), gbc);
        gbc.gridy++;
        txtNome = criarCampo(fonteCampo, verdeEscuro);
        txtNome.setText(talhao.getNome());
        painelForm.add(txtNome, gbc);

        // Área
        gbc.gridy++;
        painelForm.add(criarLabel("Área Total (hectares ou m²):", verdeEscuro), gbc);
        gbc.gridy++;
        txtArea = criarCampo(fonteCampo, verdeEscuro);
        txtArea.setText(String.valueOf(talhao.getAreaTalhao()));
        painelForm.add(txtArea, gbc);

        // Status
        gbc.gridy++;
        painelForm.add(criarLabel("Status Operacional:", verdeEscuro), gbc);
        gbc.gridy++;
        comboStatus = new JComboBox<>(new String[]{"Ativo", "Inativo", "Em Manutenção", "Descanso"});
        comboStatus.setFont(fonteCampo);
        comboStatus.setSelectedItem(talhao.getStatus());
        painelForm.add(comboStatus, gbc);

        // Observações do Talhão
        gbc.gridy++;
        painelForm.add(criarLabel("Características do Solo/Obs do Talhão:", verdeEscuro), gbc);
        gbc.gridy++;
        txtObservacoes = new JTextArea(3, 20);
        txtObservacoes.setFont(fonteCampo);
        txtObservacoes.setText(talhao.getObservacoes());
        txtObservacoes.setBorder(BorderFactory.createLineBorder(verdeEscuro, 1));
        painelForm.add(new JScrollPane(txtObservacoes), gbc);

        // Botão Salvar
        gbc.gridy++;
        JButton btnSalvar = new JButton("SALVAR DADOS DO TALHÃO");
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setPreferredSize(new Dimension(300, 50));
        
        btnSalvar.addActionListener(e -> salvar());
        
        painelForm.add(btnSalvar, gbc);

        conteudo.add(new JScrollPane(painelForm), BorderLayout.CENTER);
    }

    private void salvar() {
        try {
            // Validar campos obrigatórios
            if (txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Nome do talhão é obrigatório!", 
                    "Validação", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Atualiza os dados do talhão no objeto
            talhaoAtual.setNome(txtNome.getText().trim());
            String areaTexto = txtArea.getText().replace(",", ".");
            talhaoAtual.setAreaTalhao(new BigDecimal(areaTexto));
            talhaoAtual.setObservacoes(txtObservacoes.getText().trim());
            talhaoAtual.setStatus((String) comboStatus.getSelectedItem());

            // Salva no banco de dados
            try (Connection conn = ConnectionFactory.getConnection()) {
                br.edu.ifsp.hto.cooperativa.producao.modelo.dao.TalhaoDAO talhaoDAO = 
                    new br.edu.ifsp.hto.cooperativa.producao.modelo.dao.TalhaoDAO();
                talhaoDAO.atualizar(talhaoAtual);
            }

            JOptionPane.showMessageDialog(this, 
                "Talhão atualizado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Recarregar a tela do talhão com dados atualizados
            recarregarTelaTalhao();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro: Valores numéricos inválidos! Verifique o campo de área.", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao salvar alterações: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void recarregarTelaTalhao() {
        try {
            if (areaId != null) {
                // Buscar área completa atualizada
                AreaVO area = controller.carregarAreaCompletaPorId(areaId);
                
                if (area != null) {
                    // Fechar esta tela
                    dispose();
                    
                    // Fechar a tela de talhão anterior (se houver)
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        if (window instanceof TelaTalhao && window.isVisible()) {
                            window.dispose();
                        }
                    }
                    
                    // Abrir nova tela de talhão com dados atualizados
                    TelaTalhao telaTalhao = new TelaTalhao(area);
                    telaTalhao.setVisible(true);
                } else {
                    dispose();
                }
            } else {
                dispose();
            }
        } catch (Exception ex) {
            System.err.println("Erro ao recarregar tela do talhão: " + ex.getMessage());
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