package br.edu.ifsp.hto.cooperativa.notafiscal.visao.ClassesBase;

import javax.swing.JLabel;

public class Label extends JLabel{
    public Label(String text){
        this.setText(text);
        this.setForeground(PaletaCores.verdeEscuro);
        this.setBackground(PaletaCores.branco);
    }
}
