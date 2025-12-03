package br.edu.ifsp.hto.cooperativa.producao.visao;

import br.edu.ifsp.hto.cooperativa.producao.controle.*;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.CulturaDTO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.TipoProblemaDTO;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;

public class TelaRegistrarProblemas extends JFrame {

    private RegistrarProblemasController controller;

    // Cores
    private Color verdeEscuro = new Color(63, 72, 23);
    private Color verdeClaro = new Color(157, 170, 61);
    private Color cinzaFundo = new Color(235, 235, 235);
    private Color verdeLista = new Color(216, 222, 186);

    // Componentes que precisam ser acessados pelo Listener (Botão Salvar)
    private JComboBox<TipoProblemaDTO> comboProblema;
    private JList<CulturaDTO> listaCulturas;
    private JTextField txtQtd;
    private JFormattedTextField txtData;
    private JTextArea areaObs;

    public TelaRegistrarProblemas(RegistrarProblemasController controller) {
        this.controller = controller;

        configurarTela();
        montarLayout();
    }

    private void configurarTela() {
        setTitle("Registrar Problemas - Produção");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void montarLayout() {
        // ======= NAVBAR SUPERIOR =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL =======
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(verdeEscuro);
        menuLateral.setPreferredSize(new Dimension(220, getHeight()));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));

        menuLateral.add(Box.createVerticalStrut(30));

        JLabel tituloMenu = new JLabel("Produção", SwingConstants.CENTER);
        tituloMenu.setForeground(Color.WHITE);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 22));
        tituloMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(tituloMenu);

        menuLateral.add(Box.createVerticalStrut(40));

        String[] botoes = {"Área de plantio", "Registrar problemas", "Relatório de produção"};
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 15));
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 50));
            botao.setPreferredSize(new Dimension(180, 50));
            botao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 2));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            menuLateral.add(botao);
            menuLateral.add(Box.createVerticalStrut(15));
        }

        add(menuLateral, BorderLayout.WEST);

        // ======= CONTEÚDO CENTRAL =======
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- LINHA 0: TÍTULO ---
        JLabel lblTitulo = new JLabel("Registrar problemas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc); 

        // --- LINHA 1: LABELS ---
        JLabel lblProblema = new JLabel("Problema");
        lblProblema.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(20, 20, 0, 20); 
        conteudo.add(lblProblema, gbc);

        JLabel lblCultura = new JLabel("Ordem de Produção");
        lblCultura.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        conteudo.add(lblCultura, gbc);

        // --- LINHA 2: COMPONENTES (COMBO E LISTA) ---
        gbc.insets = new Insets(10, 20, 10, 20);

        // ATUALIZAÇÃO: Usando DTOs
        comboProblema = new JComboBox<>(controller.getListaProblemas());
        comboProblema.setFont(new Font("Arial", Font.PLAIN, 18));
        comboProblema.setPreferredSize(new Dimension(100, 45));
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.6;
        conteudo.add(comboProblema, gbc);

        // ATUALIZAÇÃO: Usando DTOs
        listaCulturas = new JList<>(controller.getListaCulturas());
        listaCulturas.setCellRenderer(new CulturaListRenderer());
        listaCulturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCulturas.setFixedCellHeight(70);
        
        JScrollPane scrollLista = new JScrollPane(listaCulturas);
        scrollLista.setPreferredSize(new Dimension(300, 100)); 
        scrollLista.setBorder(BorderFactory.createLineBorder(verdeEscuro));

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 6; 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; 
        conteudo.add(scrollLista, gbc);

        // --- LINHA 3: LABELS (QTD E DATA) ---
        gbc.gridheight = 1; 
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblQtd = new JLabel("Quantidade");
        lblQtd.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        conteudo.add(lblQtd, gbc);

        JLabel lblData = new JLabel("Data");
        lblData.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        conteudo.add(lblData, gbc);

        // --- LINHA 4: INPUTS ---
        txtQtd = new JTextField();
        txtQtd.setFont(new Font("Arial", Font.PLAIN, 18));
        txtQtd.setPreferredSize(new Dimension(100, 45));
        gbc.gridx = 0;
        gbc.gridy = 4;
        conteudo.add(txtQtd, gbc);

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(mascaraData);
            txtData.setFont(new Font("Arial", Font.PLAIN, 18));
            txtData.setPreferredSize(new Dimension(100, 45));
        } catch (ParseException e) {
            e.printStackTrace();
            txtData = new JFormattedTextField(); 
        }
        gbc.gridx = 1;
        gbc.gridy = 4;
        conteudo.add(txtData, gbc);

        // --- LINHA 5: OBS ---
        JLabel lblObs = new JLabel("Observações");
        lblObs.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        conteudo.add(lblObs, gbc);

        // --- LINHA 6: TEXTAREA ---
        areaObs = new JTextArea();
        areaObs.setFont(new Font("Arial", Font.PLAIN, 16));
        areaObs.setLineWrap(true);
        areaObs.setWrapStyleWord(true);
        JScrollPane scrollObs = new JScrollPane(areaObs);
        scrollObs.setPreferredSize(new Dimension(100, 150));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 0.5; 
        gbc.fill = GridBagConstraints.BOTH;
        conteudo.add(scrollObs, gbc);

        // --- LINHA 7: BOTÃO SALVAR ---
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setBorder(new RoundedBorder(20));
        btnSalvar.setPreferredSize(new Dimension(160, 50));

        // AÇÃO DO BOTÃO SALVAR
        btnSalvar.addActionListener(e -> salvarRegistro());

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 20, 20, 20); 
        conteudo.add(btnSalvar, gbc);
    }

    private void salvarRegistro() {
        TipoProblemaDTO problema = (TipoProblemaDTO) comboProblema.getSelectedItem();
        CulturaDTO cultura = listaCulturas.getSelectedValue();
        String qtd = txtQtd.getText();
        String data = txtData.getText();
        String obs = areaObs.getText();

        if (cultura == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma ordem de produção da lista!");
            return;
        }

        boolean sucesso = controller.salvarProblema(cultura, problema, qtd, data, obs);
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Problema registrado com sucesso!");
            // Limpar campos
            txtQtd.setText("");
            areaObs.setText("");
            txtData.setValue(null);
            listaCulturas.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique se os dados estão corretos.");
        }
    }

    private class CulturaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            
            // value.toString() chama o método do DTO que formata "NOME \n DATA"
            String texto = (value != null) ? value.toString().replace("\n", "<br>") : "";
            String html = "<html><div style='text-align: center; padding: 10px;'>" + 
                          texto + 
                          "</div></html>";

            JLabel label = (JLabel) super.getListCellRendererComponent(list, html, 
                                     index, isSelected, cellHasFocus);

            label.setBackground(verdeLista);
            label.setForeground(verdeEscuro);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            if (isSelected) {
                label.setBackground(verdeEscuro);
                label.setForeground(Color.WHITE);
            }

            if (index < list.getModel().getSize() - 1) {
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, verdeEscuro));
            } else {
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }

    private class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = this.radius + 1;
            insets.top = insets.bottom = this.radius + 2;
            return insets;
        }
    }
}       
    
