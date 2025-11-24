package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewBase extends JFrame {
    
    public ViewBase()
    {
       this.getContentPane().setBackground(PaletaCores.branco);        
    }
    
     // ===================== Utilitários =====================
    public GridBagConstraints baseGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.NORTHWEST; // esquerda/topo
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        return gbc;
    }

    public void addField(JPanel p, GridBagConstraints gbc, int y, String label, JTextField field) {
        gbc.gridy = y; gbc.gridx = 0; gbc.weightx = 0;
        p.add(new Label(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        field.setPreferredSize(new Dimension(260, 26));
        p.add(field, gbc);
    }
    
    public void addDateField(JPanel p, GridBagConstraints gbc, int y, String label, JDateChooser field) {
        gbc.gridy = y; gbc.gridx = 0; gbc.weightx = 0;
        p.add(new Label(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        field.setPreferredSize(new Dimension(260, 26));
        p.add(field, gbc);
    }

    public void addTopGlue(JPanel p, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        p.add(Box.createVerticalGlue(), gbc);
    }
    
    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s.replace(" ", "").replace(',', '.'));
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static String format(double v) {
        return String.format("%.2f", v);
    }
    
}
