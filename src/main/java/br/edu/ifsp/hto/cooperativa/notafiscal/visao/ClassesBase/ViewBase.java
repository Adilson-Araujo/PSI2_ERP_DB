package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;

// Agora herda de JInternalFrame para funcionar no DesktopPane
public abstract class ViewBase extends JInternalFrame {

    public ViewBase() {
        // Configurações: Título, Redimensionável, Fechável, Maximizável, Iconificável
        super("Sem Título", true, true, true, true);
        setSize(800, 600);

        // Janelas internas NÃO usam setLocationRelativeTo nem EXIT_ON_CLOSE.
        // Elas usam setVisible(true) na TelaPrincipal.
    }

    protected GridBagConstraints baseGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    protected void addField(JPanel p, GridBagConstraints gbc, int row, String label, JComponent campo) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        p.add(new Label(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        p.add(campo, gbc);
    }

    protected void addDateField(JPanel p, GridBagConstraints gbc, int row, String label, JDateChooser campo) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        p.add(new Label(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        p.add(campo, gbc);
    }

    protected void addTopGlue(JPanel p, GridBagConstraints gbc, int row) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weighty = 1.0; // Empurra tudo para cima
        gbc.gridwidth = 2;
        p.add(Box.createVerticalGlue(), gbc);
    }


    protected String format(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }

    protected double parseDouble(String valor) {
        try {
            return Double.parseDouble(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}