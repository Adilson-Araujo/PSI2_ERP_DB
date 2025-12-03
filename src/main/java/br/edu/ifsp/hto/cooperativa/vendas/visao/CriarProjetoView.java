package br.edu.ifsp.hto.cooperativa.vendas.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.dao.ProjetoDAO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;
import br.edu.ifsp.hto.cooperativa.sessao.modelo.dto.UsuarioTO;

public class CriarProjetoView extends BaseView {

    private static final Color BG = new Color(0xE9, 0xE9, 0xE9);
    private JTextField campoNome;
    private JTextField campoOrcamento;
    private JFormattedTextField campoDataFinal;

    public CriarProjetoView() {
        super("Criar Projeto");
        add(criarPainel(), BorderLayout.CENTER);
    }

    private JPanel criarPainel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);
        panel.add(criarTitleBar("Criar Projeto"), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(25, 25, 25, 25));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(criarCardFormulario());
        center.add(Box.createVerticalStrut(25));
        center.add(criarRodape());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG);
        wrapper.add(center, BorderLayout.NORTH);
        panel.add(wrapper, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarCardFormulario() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        card.add(new JLabel("Nome do Projeto:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(0, 30));
        card.add(campoNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        card.add(new JLabel("Orçamento (R$):"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        campoOrcamento = new JTextField();
        campoOrcamento.setPreferredSize(new Dimension(0, 30));
        card.add(campoOrcamento, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        card.add(new JLabel("Data Final (dd/MM/yyyy):"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            campoDataFinal = new JFormattedTextField(mask);
        } catch (Exception e) {
            campoDataFinal = new JFormattedTextField();
        }
        campoDataFinal.setPreferredSize(new Dimension(0, 30));
        card.add(campoDataFinal, gbc);

        return card;
    }

    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(BG);
        
        JButton btnSalvar = new JButton("Salvar Projeto");
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalvar.addActionListener(e -> salvarProjeto());
        rodape.add(btnSalvar);

        return rodape;
    }

    private void salvarProjeto() {
        try {
            if (campoNome.getText().trim().isEmpty() || campoOrcamento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios.");
                return;
            }

            ProjetoVO projeto = new ProjetoVO();
            projeto.setNomeProjeto(campoNome.getText());
            projeto.setDataCriacao(LocalDateTime.now());
            projeto.setOrcamento(new BigDecimal(campoOrcamento.getText().replace(",", ".")));
            
            // 2. IMPORTANTE: Vincular o projeto ao associado logado (se seu VO tiver esse campo)
            // Se o seu ProjetoVO não tiver setAssociadoId, pode comentar a linha abaixo.
            // projeto.setAssociadoId(Sessao.getAssociadoIdLogado()); 

            String dataStr = campoDataFinal.getText();
            if (dataStr != null && !dataStr.contains("_")) {
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                projeto.setDataFinal(data.atTime(23, 59, 59));
            } else {
                projeto.setDataFinal(null);
            }

            ProjetoDAO dao = new ProjetoDAO();
            String msg = dao.adicionar(projeto);
            JOptionPane.showMessageDialog(this, msg);
            
            if (msg.contains("sucesso")) voltarParaHome();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void voltarParaHome() {
        try {
            // 3. REFATORAÇÃO DA LÓGICA DE RETORNO
            UsuarioTO usuarioLogado = Sessao.getUsuarioLogado();
            Short tipo = usuarioLogado.usuarioVO.getTipoUsuario();
            
            // IMPORTANTE: Confirme esses IDs no seu banco de dados!
            // Assumindo: 1 = Associação (Admin), 2 = Produtor (Comum)
            final short TIPO_ASSOCIACAO = 1; 
            
            if (tipo == TIPO_ASSOCIACAO) {
                new AssociacaoMainView().setVisible(true);
            } else {
                new ProdutorMainView().setVisible(true);
            }
            dispose();
            
        } catch (Exception e) {
            // Caso a sessão tenha expirado ou dê erro
            JOptionPane.showMessageDialog(this, "Erro de sessão: " + e.getMessage());
            dispose(); // Fecha para evitar estado inconsistente
        }
    }
}