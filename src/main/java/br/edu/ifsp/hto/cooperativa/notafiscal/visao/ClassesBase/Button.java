package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import java.awt.Color;
import javax.swing.JButton;

public class Button extends JButton {
    public Color verdeEscuro = new Color(56, 61, 15);  

    public Button(String texto)
    {
        this.setText(texto);
        this.setForeground(PaletaCores.verdeEscuro);
        this.setBackground(Color.WHITE);
    }
}
