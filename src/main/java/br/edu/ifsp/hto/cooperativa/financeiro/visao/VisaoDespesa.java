package br.edu.ifsp.hto.cooperativa.financeiro.visao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import br.edu.ifsp.hto.cooperativa.financeiro.controle.ControleDespesa;
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;

public class VisaoDespesa extends JInternalFrame {
    ControleDespesa despc = ControleDespesa.getInstance();

    public VisaoDespesa(JDesktopPane desktop) {
        super("Registro de Despesas", true, true, true, true); 
        // (title, closable, resizable, maximizable, iconifiable)

        setSize(900, 600);
        setVisible(true);

        // adiciona ao desktop
        desktop.add(this);
        try {
            setSelected(true);
        } catch (Exception e) {}

        // Painel principal (seu antigo JPanel inteiro aqui dentro)
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(new EmptyBorder(20, 40, 20, 40));

        Color verdeEscuro = new Color(55, 61, 13);
        Color verdeOliva = new Color(139, 143, 61);

        // ===== TÍTULO =====
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(verdeEscuro);
        painelTitulo.setBorder(new EmptyBorder(15, 10, 15, 10));

        JLabel titulo = new JLabel("Registro de Despesas");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelTitulo.add(titulo, BorderLayout.CENTER);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // ===== PAINEL CENTRAL =====
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setBorder(new LineBorder(verdeOliva, 3, true));
        painelCentral.setPreferredSize(new Dimension(700, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipo = new JLabel("Tipo/Categoria de Gasto:");
        JTextField txtTipo = criarCampoPlaceholder("Ex: Insumos agrícolas");

        JLabel lblDescricao = new JLabel("Descrição da Despesa:");
        JTextArea txtDescricao = new JTextArea();
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        scrollDescricao.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        aplicarPlaceholderArea(txtDescricao, "Descreva a despesa...");

        JLabel lblDestinatario = new JLabel("Destinatário:");
        JTextField txtDestinatario = criarCampoPlaceholder("Ex: Fornecedor ABC");

        JLabel lblValor = new JLabel("Valor Gasto:");
        JTextField txtValor = criarCampoPlaceholder("R$ 0,00");

        JLabel lblData = new JLabel("Data da Transação:");
        JTextField txtData = criarCampoPlaceholder("aaaa/mm/dd");

        JButton btnEnviar = new JButton("Enviar");
        JButton btnLimpar = new JButton("Limpar");

        // Layout
        gbc.gridx = 0; gbc.gridy = 0; painelCentral.add(lblTipo, gbc);
        gbc.gridy = 1; painelCentral.add(txtTipo, gbc);

        gbc.gridx = 1; gbc.gridy = 0; painelCentral.add(lblDescricao, gbc);
        gbc.gridy = 1; painelCentral.add(scrollDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = 2; painelCentral.add(lblDestinatario, gbc);
        gbc.gridy = 3; painelCentral.add(txtDestinatario, gbc);

        gbc.gridx = 0; gbc.gridy = 4; painelCentral.add(lblValor, gbc);
        gbc.gridy = 5; painelCentral.add(txtValor, gbc);

        gbc.gridx = 0; gbc.gridy = 6; painelCentral.add(lblData, gbc);
        gbc.gridy = 7; painelCentral.add(txtData, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.add(btnEnviar);
        painelBotoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        painelCentral.add(painelBotoes, gbc);

        // Botão limpar
        btnLimpar.addActionListener(e -> {
            txtTipo.setText("Ex: Insumos agrícolas");
            txtTipo.setForeground(Color.GRAY);
            txtDescricao.setText("Descreva a despesa...");
            txtDescricao.setForeground(Color.GRAY);
            txtDestinatario.setText("Ex: Fornecedor ABC");
            txtDestinatario.setForeground(Color.GRAY);
            txtValor.setText("R$ 0,00");
            txtValor.setForeground(Color.GRAY);
            txtData.setText("aaaa/mm/dd");
            txtData.setForeground(Color.GRAY);
        });

        btnEnviar.addActionListener(e -> {
            if (!txtTipo.getText().equals("Ex: Insumos agrícolas")) {
                if (!txtDescricao.getText().equals("Descreva a despesa...")) {
                    if (!txtDestinatario.getText().equals("Ex: Fornecedor ABC")) {
                        if (!txtValor.getText().equals("R$ 0.00")) {
                            if (!txtData.getText().equals("aaaa/mm/dd")) {
                                DespesaVO despesa = new DespesaVO(
                                    despc.buscarTodasDespesas().size() + 1,
                                    3,
                                    txtTipo.getText(),
                                    txtDestinatario.getText(),
                                    Double.parseDouble(txtValor.getText()),
                                    txtData.getText().replace('/', '-') + " 00:00:00",
                                    txtDescricao.getText()
                                );
                                despc.inserirDespesa(despesa);
                            } else {
                                txtData.setForeground(Color.RED);
                            }
                        } else {
                            txtValor.setForeground(Color.RED);
                        }
                    } else {
                        txtDestinatario.setForeground(Color.RED);
                    }
                } else {
                    txtDescricao.setForeground(Color.RED);
                }
            } else {
                txtTipo.setForeground(Color.RED);
            }
        });

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // adiciona painel à internalframe
        add(painelPrincipal);
    }

    // ==== MÉTODOS AUXILIARES ====

    private JTextField criarCampoPlaceholder(String placeholder) {
        JTextField campo = new JTextField(20);
        campo.setForeground(Color.GRAY);
        campo.setText(placeholder);

        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(placeholder);
                }
            }
        });

        return campo;
    }

    private void aplicarPlaceholderArea(JTextArea area, String placeholder) {
        area.setForeground(Color.GRAY);
        area.setText(placeholder);

        area.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(placeholder)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setForeground(Color.GRAY);
                    area.setText(placeholder);
                }
            }
        });
    }
}
